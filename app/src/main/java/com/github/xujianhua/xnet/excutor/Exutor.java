package com.github.xujianhua.xnet.excutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by xujianhua on 2016/3/21.
 */
public class Exutor {
    private static  Exutor exutor;
    private static final  int FIXED_SIZE=10;
    private ThreadPoolExecutor threadPoolExecutor;
    /***
     *
     * @return
     */
    public static Exutor getInstance(){
        if(exutor==null){
            exutor=new Exutor();
        }
        return null;
    }

    private Exutor() {
        threadPoolExecutor=(ThreadPoolExecutor) Executors.newFixedThreadPool(FIXED_SIZE);
        threadPoolExecutor.

    }
}
