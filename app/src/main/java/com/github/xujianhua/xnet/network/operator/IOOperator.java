package com.github.xujianhua.xnet.network.operator;

import com.github.xujianhua.xnet.network.Xnet;
import com.github.xujianhua.xnet.util.ExceptionUtil;
import com.github.xujianhua.xnet.util.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by xujianhua on 19/03/16.
 */
public class IOOperator {
    public static final String TAG=IOOperator.class.getName();

    public static byte[] openInputStream(InputStream inputStream){
        ExceptionUtil.nullExeption(inputStream);
        byte[] buffer=new byte[1024];
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        int length;
        try {
            while ((length=inputStream.read(buffer))>-1){
                baos.write(buffer,0,length);
            }
            byte[] bytes=baos.toByteArray();
            baos.flush();
            inputStream.close();
            baos.close();
//            LogUtil.i(TAG,"IOOperator openInputStream result"+new String(bytes, Charset.forName("UTF-8")));
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
