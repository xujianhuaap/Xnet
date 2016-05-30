package com.github.xujianhua.xnet.network;

import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.github.xujianhua.xnet.annotation.Api;
import com.github.xujianhua.xnet.annotation.Bitmap;
import com.github.xujianhua.xnet.annotation.File;
import com.github.xujianhua.xnet.annotation.Host;
import com.github.xujianhua.xnet.annotation.MultiPart;
import com.github.xujianhua.xnet.annotation.NetMethod;
import com.github.xujianhua.xnet.annotation.Param;
import com.github.xujianhua.xnet.bean.HttpRequest;
import com.github.xujianhua.xnet.bean.HttpResponse;
import com.github.xujianhua.xnet.bean.MimeType;
import com.github.xujianhua.xnet.bean.RequestMethod;
import com.github.xujianhua.xnet.bean.TypeOutput;
import com.github.xujianhua.xnet.excutor.Exutor;
import com.github.xujianhua.xnet.network.listener.INetworkListener;
import com.github.xujianhua.xnet.network.operator.NetworkOperator;
import com.github.xujianhua.xnet.util.LogUtil;
import com.github.xujianhua.xnet.util.Test1;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by xujianhua on 20/03/16.
 */
public class NetworkService {
    public static final  String TAG=NetworkService.class.getName();
    /****
     * 获得一个网络执行的类的实例
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getService(Class<T> clazz){
        isValidateService(clazz);
        T t= (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz, Test1.class}, new XnetHandler(clazz));
        return t;
    }

    public static <T> void isValidateService(Class<T> clazz){
        if(!clazz.isInterface()){
            throw new RuntimeException("clazz must be interface");
        }else{
            if(clazz.getInterfaces().length>0){
                throw new RuntimeException("interface must not have parent");
            }
        }

    }

    /***
     * 网络请求的方法
     */
    public static class XnetHandler<T> implements InvocationHandler{
        private Class<T> clazz;

        public XnetHandler(Class<T> clazz) {
            this.clazz = clazz;
            LogUtil.i(TAG, "Proxy name %1$s", clazz.getName());
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            final HttpRequest request=new HttpRequest();
            request.setBody(new TypeOutput());
            RequestMethod netMethod=null;
            UrlTool.Builder builder=new UrlTool.Builder();
            MimeType mimeType=null;

            //Service 的注解
            Annotation[] interfaceAnnotation=clazz.getAnnotations();
            for(Annotation a:interfaceAnnotation){
                netMethod = getRequestMethod(netMethod, a);
            }
            //service 中http方法的注解
            Annotation[] methodAnnotations=method.getAnnotations();
            if(methodAnnotations==null){
                new RuntimeException("Service must have Annotations");
            }
            for(Annotation annotation:methodAnnotations){
                setUrlHostAndPath(builder, annotation);
                netMethod=getRequestMethod(netMethod,annotation);
                if(annotation.annotationType()== MultiPart.class){
                    mimeType=((MultiPart)annotation).value();
                    TypeOutput typeoutput=request.getBody();
                    if(typeoutput==null){
                        typeoutput=new TypeOutput();
                    }
                    typeoutput.setMimeType(mimeType);
                    request.setBody(typeoutput);
                    request.setMultiPart(true);
                }
            }
            //httpMethod 中的参数
            Annotation[][] parameterAnnotations=method.getParameterAnnotations();
            Hashtable<String,Object>paramter=new Hashtable<>();
            int i=0;
            for(Annotation[] a:parameterAnnotations){
                for(Annotation annotation:a){
                    i = parseParamterAnnotation(args, paramter, i, annotation);
                    i=parseMultiPartAnnotation(args,request,i,annotation);
                }
            }
            builder.setParameter(paramter);
            String urlStr=UrlTool.generateUrlStr( builder.build());
            request.setUrl(urlStr);
            request.setRequestMethod(netMethod);

            //设置网络状态变化监听器
            Object callBack=args[args.length-1];
            INetworkListener networkListener=null;
            if(callBack instanceof INetworkListener) {
                networkListener = (INetworkListener) callBack;
            }
            //开启新的线程来执行网络请求和响应
            Exutor.getInstance().excute(new CallBackRunnable(networkListener) {
                @Override
                public HttpResponse obtainResponse() {
                    //设置相关Header
                    HashMap<String,String> headers=new HashMap<String, String>();
                    headers.put("APP_ID","app");
                    headers.put("APP_VERSION","v1.0");

                    return NetworkOperator.perfermRequest(request,headers);
                }
            });

            return null;
        }
    }

    /***
     *
     * @param netMethod
     * @param a
     * @return
     */
    private static RequestMethod getRequestMethod(RequestMethod netMethod, Annotation a) {
        if(a.annotationType()==NetMethod.class){
            netMethod=((NetMethod)a).value();
            LogUtil.i(TAG,"HttpService NetMethod\t"+((NetMethod)a).value());
        }
        return netMethod;
    }

    /***
     *
     * @param args
     * @param paramter
     * @param i
     * @param annotation
     * @return
     */
    private static int parseParamterAnnotation(Object[] args, Hashtable<String, Object> paramter, int i, Annotation annotation) {
        if(annotation.annotationType()== Param.class){
            String key=((Param)annotation).value();
            LogUtil.d(TAG,"HttpMethod Paramter key\t %1$s,value \t %2$s",key,args[i]+"");
            if(TextUtils.isEmpty(key)){
                throw new RuntimeException("HashTable key can not null");
            }
            paramter.put(key,args[i]);
            i++;
        }
        return i;

    }

    /***
     *
     * @param args
     * @param request
     * @param i
     * @param annotation
     * @return
     */
    private static int parseMultiPartAnnotation(Object[] args,HttpRequest request,int i, Annotation annotation){
        //设置要上传的文件或Bitmap
        if(annotation.annotationType()== Bitmap.class){
            Object obj=args[i];
            if(obj instanceof android.graphics.Bitmap){
                android.graphics.Bitmap bitmap=(android.graphics.Bitmap)obj;
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG,100,baos);
                TypeOutput typeOutput=request.getBody();
                if(typeOutput==null){
                    typeOutput=new TypeOutput();
                }
                typeOutput.setContent(baos.toByteArray());
                typeOutput.setMimeType(MimeType.IMAGE_PNG);
                request.setBody(typeOutput);
            }
            i++;
        }
        if(annotation.annotationType()== File.class){
            Object obj=args[i];
            if(obj instanceof java.io.File){
                java.io.File file=(java.io.File) obj;
                try {
                    FileInputStream fileInputStream= new FileInputStream(file);
                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
                    byte[] buffer=new byte[1024*3];
                    int cnt=0;
                    while ((cnt=fileInputStream.read(buffer))!=-1){
                        baos.write(buffer,0,cnt);
                    }
                    TypeOutput typeOutput=request.getBody();
                    if(typeOutput==null){
                        typeOutput=new TypeOutput();
                    }
                    typeOutput.setContent(baos.toByteArray());
                    request.setBody(typeOutput);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
        return i;
    }

    /***
     *
     * @param builder
     * @param annotation
     */
    private static void setUrlHostAndPath(UrlTool.Builder builder, Annotation annotation) {
        if(annotation.annotationType()==Api.class){
            builder.setPath(((Api)annotation).value());
            LogUtil.i(TAG,"HttpMethod Api\t"+((Api)annotation).value());
        }
        if(annotation.annotationType()== Host.class){
            builder.setHost(((Host)annotation).value());
            LogUtil.i(TAG,"HtttpMethod Host\t"+((Host)annotation).value());
        }
    }
}
