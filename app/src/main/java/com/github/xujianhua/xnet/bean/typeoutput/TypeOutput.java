package com.github.xujianhua.xnet.bean.typeoutput;

import com.github.xujianhua.xnet.bean.MimeType;

import java.io.OutputStream;

/**
 * Created by xujianhua on 28/05/16.
 */
public class TypeOutput {
    private MimeType mimeType;
    private byte[] content;

    public MimeType getMimeType() {
        return mimeType;
    }

    public void setMimeType(MimeType mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
