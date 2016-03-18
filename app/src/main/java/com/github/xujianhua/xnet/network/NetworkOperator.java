package com.github.xujianhua.xnet.network;

import android.text.TextUtils;

import com.github.xujianhua.xnet.bean.HttpRequest;
import com.github.xujianhua.xnet.bean.HttpResponse;
import com.github.xujianhua.xnet.bean.IRequest;
import com.github.xujianhua.xnet.bean.RequestMethod;
import com.github.xujianhua.xnet.util.ExceptionUtil;
import com.github.xujianhua.xnet.util.LogUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by xujianhua on 2016/3/17.
 */
public class NetworkOperator {
    public static final String TAG=NetworkOperator.class.getName();
    public static final int DEFAULT_READ_TIME_OUT=10000;
    public static final int DEFAULT_CONNECT_TIME_OUT=10000;

    public static HttpResponse perfermRequest(HttpRequest request, HashMap<String,String>headerOptions){
        HttpURLConnection connection=null;
        OutputStream outputStream=null;
        try {
            ExceptionUtil.nullExeption(request);
            String urlStr=request.getUrl();
            int readTimeOut=request.getReadTimeOut();
            int connectTimeOut=request.getConnectTimeOut();
            RequestMethod requestMethod=request.getRequestMethod();
            boolean isCache=request.isCache();

            ExceptionUtil.nullExeption(urlStr);
            URL url=new URL(request.getUrl());
            connection=(HttpURLConnection) url.openConnection();

            ExceptionUtil.nullExeption(connection);
            if(readTimeOut<0){
                readTimeOut=DEFAULT_READ_TIME_OUT;
            }
            if(connectTimeOut<0){
                connectTimeOut=DEFAULT_CONNECT_TIME_OUT;
            }
            ExceptionUtil.nullExeption(requestMethod);
            connection.setReadTimeout(readTimeOut);
            connection.setConnectTimeout(connectTimeOut);
            connection.setRequestMethod(requestMethod.getKey());
            connection.setDefaultUseCaches(isCache);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            if(headerOptions!=null&&!headerOptions.isEmpty()){
                Set<String> keys=headerOptions.keySet();
                Iterator<String> iterator=keys.iterator();
                while (iterator.hasNext()){
                    String key=iterator.next();
                    connection.addRequestProperty(key,headerOptions.get(key));
                }
            }

            connection.connect();
            int respCode=connection.getResponseCode();
            String msg=connection.getResponseMessage();
            outputStream=connection.getOutputStream();
            String contentType=connection.getContentType();
            ExceptionUtil.nullExeption(outputStream);
            byte[] buffer=new byte[1024];
            while (){

            }
            outputStream.write();
            outputStream.flush();
            outputStream.close();
            HttpResponse response=new HttpResponse(respCode,msg,contentType,outputStream);
            LogUtil.i(TAG,"request"+request.toString());
            LogUtil.i(TAG,"reponse"+response.toString());
            LogUtil.i(TAG,"----------------------------------");
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.i(TAG,e.getMessage());
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    return null;
                }
            }
        }
        return null;
    }
}
