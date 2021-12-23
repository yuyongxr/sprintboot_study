package com.jiang.dao.impl;

import com.jiang.dao.BookDao;
import org.springframework.stereotype.Repository;

//将这个dao实现类注册为一个spring的bean
@Repository
public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("book dao is running ......");
    }
}
