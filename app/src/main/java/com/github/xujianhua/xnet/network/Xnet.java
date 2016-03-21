package com.github.xujianhua.xnet.network;


import com.github.xujianhua.xnet.util.ExceptionUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xujianhua on 2016/3/17.
 */
public class Xnet {
    private Gson gson;
    private String charCoding;
    private static Xnet xNet;
    private ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap();//可扩展的线程安全的

    /***
     * 开启网络请求的类，里面是网络请求的方法
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>T create(Class<T> clazz){
        ExceptionUtil.nullExeption(clazz);
        T t=(T)xNet.concurrentHashMap.get(clazz.getName());
        if(t!=null){
            return t;
        }else {
            t= NetworkService.getService(clazz);
            xNet.concurrentHashMap.put(clazz.getName(),t);
            return t;
        }
    }
    /***
     *
     * @return 单例模式
     */
    public static Xnet getInstance(){
        if(xNet==null){
            xNet=new Xnet();
        }
        return xNet;
    }
    private Xnet(){
        new Xnet("utf-8");
    }
    private Xnet(String charCoding){
        gson=createGson();
        this.charCoding=charCoding;
    }
    private Gson createGson(){
        Gson gson=new GsonBuilder().create();
        return gson;
    }

    public Gson getGson() {
        return gson;
    }

    public String getCharCoding() {
        return charCoding;
    }

}
