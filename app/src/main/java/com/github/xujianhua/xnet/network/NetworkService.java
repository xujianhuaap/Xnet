package com.github.xujianhua.xnet.network;

import com.github.xujianhua.xnet.util.LogUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
        T t= (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new XnetHandler());
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
    public static class XnetHandler implements InvocationHandler{

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            LogUtil.i(TAG,"NetworkService Method name %1$s,　Argument num %1$d",method.getName(),args.length);
            LogUtil.i(TAG,"Proxy name %1$s",proxy.getClass().getName());
            return null;
        }
    }
}
