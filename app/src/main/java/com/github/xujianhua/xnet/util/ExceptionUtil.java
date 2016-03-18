package com.github.xujianhua.xnet.util;

import android.text.TextUtils;

/**
 * Created by xujianhua on 2016/3/17.
 */
public class ExceptionUtil {
    public static void nullExeption(Object object){
        if(object instanceof String){
            if(TextUtils.isEmpty((String)object)){
                throw new RuntimeException("argument can not null");
            }
        }else{
            if(object==null){
                throw new RuntimeException("argument can not null");
            }
        }
    }
}
