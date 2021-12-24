package com.jiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SSMPApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSMPApplication.class, args);
        //args 用来接收从外部获取参数，如临时属性，该参数可以去掉，去掉后就不会从命令行接收参数了
    }

}
