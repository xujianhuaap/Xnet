package com.github.xujianhua.xnet.annotation;

import com.github.xujianhua.xnet.bean.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xujianhua on 2016/3/21.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NetMethod {
     RequestMethod value();
}
