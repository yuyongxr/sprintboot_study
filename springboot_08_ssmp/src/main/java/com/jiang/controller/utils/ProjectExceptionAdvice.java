package com.jiang.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler
    public R doException(Exception e){
        //记录日志
        //通知开发
        //通知运维
        e.printStackTrace();
        return new R("服务器故障，请稍后再试");
    }
}
