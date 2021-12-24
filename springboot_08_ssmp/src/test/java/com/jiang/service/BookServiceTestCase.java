package com.jiang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiang.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookServiceTestCase {

    @Autowired
    private IBookService bookService;

    @Test
    void testSave(){
        Book book = new Book();
        book.setName("操作系统");
        book.setType("基础知识");
        book.setDescription("操作系统的基础知识");
        bookService.save(book);
    }

    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(5);
        book.setName("操作系统1");
        book.setType("基础知识");
        book.setDescription("操作系统的基础知识");
        bookService.updateById(book);
    }

    @Test
    void testDelete(){
        bookService.removeById(4);
    }

    @Test
    void  testGetById(){
        System.out.println(bookService.getById(5));
    }

    @Test
    void testGetAll(){
        System.out.println(bookService.list());
    }

    @Test
    void testGetPage(){
//        bookService.getPage(1,3);
        IPage<Book> page = new Page<Book>(1,3);
        bookService.page(page);
    }
}
