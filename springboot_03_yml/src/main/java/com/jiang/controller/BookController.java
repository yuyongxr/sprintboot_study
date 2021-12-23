package com.jiang.controller;

import com.jiang.MyDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @Value("${server.port}")
    private String port;

    //使用自动装配将所有的配置文件中的数据封装到一个Environment中
    @Autowired
    private Environment env;

    @Autowired
    private MyDataBase myDataBase;

    @GetMapping
    public String getById(){
        System.out.print("springboot is running ......yml\n");
        System.out.print("server.port:"+port);
        System.out.print("-------------------------------------------\n");
        System.out.print(env.getProperty("server.port"));
        System.out.print("-------------------------------------------\n");
        System.out.println(myDataBase);
        return "springboot is running ......yml , \nserver.port:"+port;
    }
}
