package com.github.xujianhua.xnet;

import com.github.xujianhua.xnet.annotation.Api;
import com.github.xujianhua.xnet.annotation.Bitmap;
import com.github.xujianhua.xnet.annotation.Host;
import com.github.xujianhua.xnet.annotation.MultiPart;
import com.github.xujianhua.xnet.annotation.NetMethod;
import com.github.xujianhua.xnet.annotation.Param;
import com.github.xujianhua.xnet.bean.HttpResponse;
import com.github.xujianhua.xnet.bean.MimeType;
import com.github.xujianhua.xnet.bean.RequestMethod;
import com.github.xujianhua.xnet.network.listener.INetworkListener;

/**
 * Created by xujianhua on 20/03/16.
 */
@NetMethod(RequestMethod.GET)
public interface Test {
    //http://china.huanqiu.com/article/2016-03/8742213.html?from=bdwz
    @Api("/user/register")
    @Host("http://192.168.23.60:9090")
    @NetMethod(RequestMethod.POST)
    void httpPost(@Param("UserName") String name, @Param("Age")int age, @Param("Gender")boolean gender, INetworkListener listener);

    @Api("/user/register")
    @Host("http://192.168.23.60:9090")
    @NetMethod(RequestMethod.POST)
    @MultiPart(MimeType.IMAGE_JPEG)
    void httpPostBitmap(@Param("UserName")  String name, @Param("Age")int age, @Param("Gender")boolean gender,@Bitmap Bitmap bmp, INetworkListener listener);


}
