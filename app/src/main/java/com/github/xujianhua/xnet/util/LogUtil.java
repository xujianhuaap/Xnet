package com.github.xujianhua.xnet.util;

import android.text.TextUtils;
import android.util.Log;

import com.github.xujianhua.xnet.BuildConfig;

/**
 * Created by xujianhua on 2016/3/17.
 */
public class LogUtil {
    public static boolean isDebug= BuildConfig.DEBUG;

    public static void d (String tag,String msg,Object... objects){
        if(TextUtils.isEmpty(msg)) throw new RuntimeException("msg can not null");
        if(isDebug){
            Log.d(tag,String.format(msg,objects));
        }

    }
    public static void i (String tag,String msg,Object... objects){
        if(TextUtils.isEmpty(msg)) throw new RuntimeException("msg can not null");
        if(isDebug){
            Log.i(tag,String.format(msg,objects));
        }

    }
    public static void e (String tag,String msg,Object... objects){
        if(TextUtils.isEmpty(msg)) throw new RuntimeException("msg can not null");
        if(isDebug){
            Log.e(tag,String.format(msg,objects));
        }

    }
    public static void v (String tag,String msg,Object... objects){
        if(TextUtils.isEmpty(msg)) throw new RuntimeException("msg can not null");
        if(isDebug){
            Log.v(tag,String.format(msg,objects));
        }

    }
    public static void w (String tag,String msg,Object... objects){
        if(TextUtils.isEmpty(msg)) throw new RuntimeException("msg can not null");
        if(isDebug){
            Log.w(tag,String.format(msg,objects));
        }
    }

}
