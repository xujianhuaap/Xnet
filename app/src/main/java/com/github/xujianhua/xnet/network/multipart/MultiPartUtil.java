package com.github.xujianhua.xnet.network.multipart;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by xujianhua on 2016/6/2.
 */
public class MultiPartUtil {
    public static final String BOUNDARY="----$end----------";
    public static final String CRLF="\r\n";
    public static final String PREFIX="--";

    public static void buildEnd(OutputStream outputstream){
        try {
            outputstream.write((MultiPartUtil.PREFIX+ MultiPartUtil.BOUNDARY+ MultiPartUtil.PREFIX).getBytes());
            outputstream.write(MultiPartUtil.CRLF.getBytes());
            outputstream.write(MultiPartUtil.CRLF.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void buildHeader(OutputStream outputstream,String name){
       buildHeader(outputstream,name,"text/plain");
    }
    public static void buildHeader(OutputStream outputstream,String name,String contentType){
        try {
            outputstream.write((MultiPartUtil.PREFIX+ MultiPartUtil.BOUNDARY+ MultiPartUtil.CRLF).getBytes());
            outputstream.write(("Content-Disposition: form-data;name="+name+ MultiPartUtil.CRLF).getBytes());
            buildContentType(outputstream,contentType);
            outputstream.write(MultiPartUtil.CRLF.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void buildContentType(OutputStream outputstream,String contentType){
        try {
            outputstream.write(("Content-Type: "+contentType+ MultiPartUtil.CRLF).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void buildContent(OutputStream outputstream,byte[] content){
        if(content.length>0){
            try {
                outputstream.write(content);
                outputstream.write(MultiPartUtil.CRLF.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
