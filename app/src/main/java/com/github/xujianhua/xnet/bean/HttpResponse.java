package com.github.xujianhua.xnet.bean;

import java.io.OutputStream;

/**
 * Created by xujianhua on 2016/3/17.
 */
public class HttpResponse implements IResponse {
    private int statusCode;
    private String message;
    private String contentType;
    private byte[] datas;


    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    public byte[] getDatas() {
        return datas;
    }

    public void setDatas(byte[] datas) {
        this.datas = datas;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpResponse(int statusCode, String message, String contentType) {
        this.statusCode = statusCode;
        this.message = message;
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
