package com.jiang.dao;

import com.jiang.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookDaoTestCase {
    @Autowired
    private BookDao bookDao;


    @Test
    void testGetById(){
        System.out.println(bookDao.selectById(1));
    }

    @Test
    void testSave(){
        Book book = new Book();
        book.setName("计算机网络 第2班");
        book.setType("网络技术");
        book.setDescription("网络技术学习");
        bookDao.insert(book);
    }

    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(3);
        book.setName("数据结构");
        book.setType("基础知识");
        book.setDescription("计算机基础知识");
        bookDao.updateById(book);
    }

    @Test
    void testDelete(){
        bookDao.deleteById(7);
    }

    @Test
    void testGetAll(){
        System.out.println(bookDao.selectList(null));
    }

    @Test
    void testGetPage(){
        System.out.println(bookDao.selectById(1));
    }

    @Test
    void testGetBy(){
        System.out.println(bookDao.selectById(1));
    }

}
