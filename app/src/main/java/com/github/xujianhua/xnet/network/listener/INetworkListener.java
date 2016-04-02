package com.github.xujianhua.xnet.network.listener;

import com.github.xujianhua.xnet.bean.HttpResponse;

/**
 * Created by xujianhua on 19/03/16.
 */
public interface INetworkListener {
    public void start();
    public void end();
    public void success(int code, HttpResponse response);
    public void failure();
}
