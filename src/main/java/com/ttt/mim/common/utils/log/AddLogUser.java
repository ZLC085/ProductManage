package com.ttt.mim.common.utils.log;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AddLogUser {

    /**
     * @Description 要执行的操作内容比如：用户：XXX登录成功
     **/
    String operation();
}