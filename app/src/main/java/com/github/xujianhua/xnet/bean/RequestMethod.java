package com.github.xujianhua.xnet.bean;

/**
 * Created by xujianhua on 2016/3/17.
 */
public enum  RequestMethod {


    OPTIONS("OPTIONS",1),
    GET("GET",2),
    HEAD("HEAD",3),
    POST("POST",4),
    PUT("PUT",5),
    DELETE("DELETE",6),
    TRACE("TRACE",7);
    
    private String key;
    private int value;

    RequestMethod(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
