package com.github.xujianhua.xnet.bean;

/**
 * Created by xujianhua on 2016/3/17.
 */
public class HttpRequest implements IRequest {
    private String url;
    private RequestMethod requestMethod;
    private int readTimeOut=-1;
    private int connectTimeOut=-1;
    private boolean isCache;

    public boolean isCache() {
        return isCache;
    }

    public void setCache(boolean cache) {
        isCache = cache;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public int getReadTimeOut() {
        return readTimeOut;
    }

    public void setReadTimeOut(int readTimeOut) {
        this.readTimeOut = readTimeOut;
    }

    public int getConnectTimeOut() {
        return connectTimeOut;
    }

    public void setConnectTimeOut(int connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "url='" + url + '\'' +
                ", requestMethod=" + requestMethod +
                ", readTimeOut=" + readTimeOut +
                ", connectTimeOut=" + connectTimeOut +
                ", isCache=" + isCache +
                '}';
    }
}
