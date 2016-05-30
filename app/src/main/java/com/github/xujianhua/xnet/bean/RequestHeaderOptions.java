package com.github.xujianhua.xnet.bean;

/**
 * Created by xujianhua on 2016/3/17.
 */
public class RequestHeaderOptions {
    /**
     *
     * image/gif,image/x-xbitmap,image/jpeg,application/x-shockwave-flash,application/vnd.ms-excel,application/vnd.ms-powerpoint,application/msword,（CRLF）
     */
    public static final String ACCEPT="Accept";
    /**
     *Accept-Charset: iso-8859-5, unicode-1-1;q=0.8(CRLF)
     */
    public static final String ACCEPT_CHARSET="Accept-Charset";
    /**
     *Accept-Encoding:gzip,deflate (CRLF)
     */
    public static final String ACCEPT_ENCODEING="Accept-Encoding";
    /**
     *Accept-Language: da, en-gb;q=0.8, en;q=0.7(CRLF)
     */
    public static final String ACCEPT_LANGUAGE="Accept-Language";
    /***
     * From: webmaster@w3.org
     */
    public static final String FROM="From";
    /***
     * Host:www.w3.org
     * 默认端口 80
     */
    public static final String HOST="Host";
    /***
     * 将客户端的浏览器信息发给服务端
     *User-Agent:Mozilla/4.0(compatible;MSIE6.0;Windows NT 5.0) (CRLF)
     */
    public static final String USER_AGENT="User-Agent";
    /***
     * 在接受到来自服务端的401响应时，将Authirization交给服务器验证，有三种基本认证方式
     * 基本认证（basic)摘要认证（digest)扩展认证。
     * 1>Get /index.html HTTP/1.0
     * Host:www.google.com
     * 2>在客户端访问受保护资源后，服务端进行401响应返回信息如下：
     *   HTTP/1.0 401 Unauthorised
     *   Server: SokEvo/1.0
     *   WWW-Authenticate: Basic realm="google.com"
     * 3>客户端从新请求客户端
     * Get /index.html HTTP/1.0
     * Host:www.google.com
     * Authorization: Basic xxxxxxxxxxxxxxxxxxxxxxxxxxxx
     * xxx表示用Base64加密后的用户名和密码
     */
    public static final String AUTHORIZATION="Authorization";
    /***
     *
     */
    public static final String PROXY_AUTHORIZATION="Proxy-Authorization";
    /***
     * 实体包头设置
     * Content-Type: text/html; charset=ISO-8859-4
     */
    public static final String CONTENT_TYPE="Contnet-Type";
    /***
     * 实体包头设置
     * Content-Length: 3495
     */
    public static final String CONTENT_LENGTH="Content-Length";
    /***
     * Connection:keep-alive(CRLF)
     * Connection: close(CRLF)
     */
    public static final String CONNECTION="Connection";
    /***
     *  Allow: GET, HEAD, PUT(CRLF)
     */
    public static final String ALLOW="Allow";
    /***
     *
     */
    public static final String TANSFER_CODING="Transfer-Coding";

}
