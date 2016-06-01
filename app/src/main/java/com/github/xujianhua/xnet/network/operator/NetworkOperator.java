package com.github.xujianhua.xnet.network.operator;


import com.github.xujianhua.xnet.bean.HttpRequest;
import com.github.xujianhua.xnet.bean.HttpResponse;
import com.github.xujianhua.xnet.bean.RequestHeaderOptions;
import com.github.xujianhua.xnet.bean.RequestMethod;
import com.github.xujianhua.xnet.bean.TypeOutput;
import com.github.xujianhua.xnet.util.ExceptionUtil;
import com.github.xujianhua.xnet.util.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
    public static final String BOUNDARY="----$end----------";
    public static final String CRLF="\r\n";
    public static final String PREFIX="--";

    public static HttpResponse perfermRequest(HttpRequest request, HashMap<String,String>headerOptions){
        HttpURLConnection connection=null;
        InputStream inputStream=null;
        OutputStream outputstream=null;
        try {
            ExceptionUtil.nullExeption(request);
            String urlStr=request.getUrl();
            int readTimeOut=request.getReadTimeOut();
            int connectTimeOut=request.getConnectTimeOut();
            RequestMethod requestMethod=request.getRequestMethod();
            boolean isCache=request.isCache();
            boolean isMultiPart=request.isMultiPart();
            TypeOutput typeOutput=request.getBody();

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
            //设置相关的header
            if(headerOptions!=null&&!headerOptions.isEmpty()){
                Set<String> keys=headerOptions.keySet();
                Iterator<String> iterator=keys.iterator();
                while (iterator.hasNext()){
                    String key=iterator.next();
                    connection.addRequestProperty(key,headerOptions.get(key));
                }
                if(isMultiPart){
                    connection.setRequestProperty(RequestHeaderOptions.CONTENT_TYPE,"multipart/form-data;boundary="+BOUNDARY);
                    if(typeOutput!=null){
                        byte[] contents=typeOutput.getContent();
                        if(contents.length>0){
                            connection.setChunkedStreamingMode(0);
                        }
                    }
                }
            }

            //Multipart
            if(isMultiPart){
                outputstream=connection.getOutputStream();
                byte[] content=typeOutput.getContent();
                outputstream.write((PREFIX+BOUNDARY+CRLF).getBytes());
                outputstream.write(("Content-Type: text/plain"+CRLF).getBytes());
                outputstream.write(("Content-Disposition: form-data;name=novel"+CRLF+CRLF).getBytes());
//                outputstream.write("平凡的世界.pdf".getBytes());
//                outputstream.write(CRLF.getBytes());
//                outputstream.write((PREFIX+BOUNDARY+CRLF).getBytes());
//                outputstream.write(("Content-Disposition: form-data;name=content;filename=平凡的世界"+CRLF).getBytes());
//                outputstream.write( ("Content-Type: application/octet-stream"+CRLF).getBytes());
//                outputstream.write( ("Content-Transfer-Encoding: binary"+CRLF+CRLF ).getBytes());
                outputstream.write(content);
                LogUtil.i(TAG,"multipart content"+content.length);
                outputstream.write(CRLF.getBytes());
                outputstream.write((PREFIX+BOUNDARY+PREFIX).getBytes());
                outputstream.write(CRLF.getBytes());
                outputstream.flush();
//            outputstream.close();
            }
            int respCode=connection.getResponseCode();
            if(respCode!=HttpURLConnection.HTTP_OK){
                inputStream=connection.getErrorStream();
            }else {
                inputStream=connection.getInputStream();
            }
            String msg=connection.getResponseMessage();
            String contentType=connection.getContentType();
            ExceptionUtil.nullExeption(inputStream);
            byte[] bytes= IOOperator.openInputStream(inputStream);
            HttpResponse response=new HttpResponse(respCode,msg,contentType,bytes);
            LogUtil.i(TAG,"request"+request.toString());
            LogUtil.i(TAG,"reponse"+response.toString());
            LogUtil.i(TAG,"----------------------------------");
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.disconnect();
            }

        }
        return null;
    }
}
