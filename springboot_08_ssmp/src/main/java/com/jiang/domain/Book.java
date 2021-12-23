package com.jiang.domain;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;


//lombok 自动创建实体类的各种方法
@Data
//@AllArgsConstructor  //全部参数的构造方法
//@NoArgsConstructor  //无参数的构造方法
public class Book {
    private Integer id;
    private String name;
    private String type;
    private String description;
}
