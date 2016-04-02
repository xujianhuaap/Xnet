package com.github.xujianhua.xnet.excutor;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;

import java.util.concurrent.Executor;


/**
 * Created by xujianhua on 02/04/16.
 * 将工作线程的任务转到主线程执行
 */
public  class MainThreadExcutor implements Executor{
    private Handler handler=new Handler(Looper.getMainLooper());

    @Override
    public void execute(Runnable command) {
        handler.post(command);
    }

}
