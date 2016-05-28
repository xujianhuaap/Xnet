package com.github.xujianhua.xnet.annotation;

import com.github.xujianhua.xnet.bean.MimeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xujianhua on 28/05/16.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiPart {
    MimeType value();
}
