package com.github.xujianhua.xnet.network.listener;

/**
 * Created by xujianhua on 19/03/16.
 */
public interface INetworkListener {
    public void success();
    public void failure();
    public int update();
}
