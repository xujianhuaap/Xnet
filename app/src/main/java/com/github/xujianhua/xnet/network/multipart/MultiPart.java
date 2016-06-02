package com.github.xujianhua.xnet.network.multipart;

import com.github.xujianhua.xnet.bean.MimeType;
import com.github.xujianhua.xnet.util.ExceptionUtil;

import java.io.OutputStream;

/**
 * Created by xujianhua on 2016/6/2.
 */
public class MultiPart {
    /***
     *
     * @param outputstream
     * @param fileName
     * @param content
     */
    public static void buildFile(OutputStream outputstream, String fileName, byte[] content, MimeType mimeType){
        ExceptionUtil.nullExeption(outputstream);
        ExceptionUtil.nullExeption(fileName);
        MultiPartUtil.buildHeader(outputstream,"FileName");
        MultiPartUtil.buildContent(outputstream,fileName.getBytes());
        MultiPartUtil.buildHeader(outputstream,"Content",mimeType.getValue());
        MultiPartUtil.buildContent(outputstream,content);
        MultiPartUtil.buildEnd(outputstream);
    }
    public static void buildBitmap(OutputStream outputstream,int bitmapSize, byte[] content, MimeType mimeType){
        ExceptionUtil.nullExeption(outputstream);
        MultiPartUtil.buildHeader(outputstream,"BitmapSize");
        MultiPartUtil.buildContent(outputstream,(bitmapSize+"").getBytes());
        MultiPartUtil.buildHeader(outputstream,"Bitmap",mimeType.getValue());
        MultiPartUtil.buildContent(outputstream,content);
        MultiPartUtil.buildEnd(outputstream);
    }


}
