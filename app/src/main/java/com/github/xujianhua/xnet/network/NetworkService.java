package com.github.xujianhua.xnet.network;

import android.text.TextUtils;

import com.github.xujianhua.xnet.annotation.Api;
import com.github.xujianhua.xnet.annotation.Host;
import com.github.xujianhua.xnet.annotation.NetMethod;
import com.github.xujianhua.xnet.annotation.Param;
import com.github.xujianhua.xnet.bean.HttpRequest;
import com.github.xujianhua.xnet.bean.RequestMethod;
import com.github.xujianhua.xnet.util.LogUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
        T t= (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new XnetHandler(clazz));
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
            LogUtil.i(TAG,"Proxy name %1$s",clazz.getName());
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            HttpRequest request=new HttpRequest();
            RequestMethod netMethod=null;
            UrlTool.Builder builder=new UrlTool.Builder();

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
            }
            //httpMethod 中的参数
            Annotation[][] parameterAnnotations=method.getParameterAnnotations();
            Hashtable<String,String>paramter=new Hashtable<>();
            int i=0;
            for(Annotation[] a:parameterAnnotations){
                for(Annotation annotation:a){
                    i = setUrlField(args, paramter, i, annotation);
                }
            }
            builder.setParameter(paramter);
            String urlStr=UrlTool.generateUrlStr( builder.build());
            request.setUrl(urlStr);
            request.setRequestMethod(netMethod);
            //执行

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
    private static int setUrlField(Object[] args, Hashtable<String, String> paramter, int i, Annotation annotation) {
        if(annotation.annotationType()== Param.class){
            String key=((Param)annotation).value();
            LogUtil.d(TAG,"HttpMethod Paramter key\t %1$s,value \t %2$s",key,args[i]+"");
            if(TextUtils.isEmpty(key)){
                throw new RuntimeException("HashTable key can not null");
            }
            paramter.put(key,args[i]+"");
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
