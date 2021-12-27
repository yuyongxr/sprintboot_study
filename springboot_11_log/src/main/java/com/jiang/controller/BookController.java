package com.jiang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //用注解的形式 创建log对象，来源lombok，替换了我们写的baseclass类
@RestController
@RequestMapping("/books")
//public class BookController extends BaseClass {
public class BookController {
    //创建日志
//    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @GetMapping
    public String getById(){
        log.debug("debug ......");
        log.info("info ......");
        log.warn("warn ......");
        log.error("error ......");


        return "springboot is running ......2";
    }
}
