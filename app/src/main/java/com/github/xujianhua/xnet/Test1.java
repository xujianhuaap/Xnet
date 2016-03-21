package com.github.xujianhua.xnet;

import com.github.xujianhua.xnet.annotation.Api;

/**
 * Created by xujianhua on 20/03/16.
 */
public interface Test1 {
    @Api("123")
    void httpPost(String url);
}
