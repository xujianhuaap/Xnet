package com.github.xujianhua.xnet.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by xujianhua on 2016/3/17.
 */
public class Xnet {
    private Gson gson;
    /***
     *
     * @return 单例模式
     */
    public static Xnet getInstance(){
        return new Xnet();
    }
    private Xnet(){
       gson=createGson();
    }
    private Gson createGson(){
        Gson gson=new GsonBuilder().create();
        return gson;
    }

    public Gson getGson() {
        return gson;
    }

}
