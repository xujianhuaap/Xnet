package com.github.xujianhua.xnet;

import com.github.xujianhua.xnet.annotation.Api;
import com.github.xujianhua.xnet.annotation.Host;
import com.github.xujianhua.xnet.annotation.NetMethod;
import com.github.xujianhua.xnet.annotation.Param;
import com.github.xujianhua.xnet.bean.HttpResponse;
import com.github.xujianhua.xnet.bean.RequestMethod;

/**
 * Created by xujianhua on 20/03/16.
 */

public interface Test {
    //http://china.huanqiu.com/article/2016-03/8742213.html?from=bdwz
    @Api("/article/2016-03/8742213.html")
    @Host("http://china.huanqiu.com")
    @NetMethod(RequestMethod.POST)
    void httpPost(@Param("from") String from,@Param("to")String to, HttpResponse httpResponse);
}
