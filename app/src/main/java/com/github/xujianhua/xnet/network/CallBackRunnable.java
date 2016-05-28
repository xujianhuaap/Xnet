package com.github.xujianhua.xnet.network;

import android.app.DownloadManager;

import com.github.xujianhua.xnet.bean.HttpRequest;
import com.github.xujianhua.xnet.bean.IResponse;
import com.github.xujianhua.xnet.excutor.MainThreadExcutor;
import com.github.xujianhua.xnet.network.listener.INetworkListener;
import com.github.xujianhua.xnet.network.listener.NetWorkListener;

/**
 * Created by xujianhua on 02/04/16.
 * 实现了此次网络请求的进程控制
 * 获得response
 * 异常捕获
 *
 */
public abstract class CallBackRunnable implements Runnable {
    private MainThreadExcutor mainThreadExcutor=new MainThreadExcutor();
    private INetworkListener listener=new NetWorkListener();
    @Override
    public void run() {
        mainThreadExcutor.execute(new Runnable() {
            @Override
            public void run() {
                listener.start();
            }
        });
        mainThreadExcutor.execute(new Runnable() {
            @Override
            public void run() {
                IResponse response=obtainResponse();
                listener.end();
            }
        });
    }
    public abstract IResponse obtainResponse();
}
