package com.github.xujianhua.xnet.bean;

import com.google.gson.Gson;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by xujianhua on 2016/3/18.
 */
public class Entity {

    /***
     *
     * @param httpResponse 资源
     * @return jsonStr
     */

    public static String toJson( HttpResponse httpResponse){
        if(httpResponse.getContentType().startsWith("text/")){
            if(httpResponse!=null){
                byte[] bytes= httpResponse.getDatas();
                if(bytes!=null){
                    try {
                        return new String(bytes,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
