package com.jiang.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    void testGetPage(){ //有条件的分页查询
        String name = "网络";
        LambdaQueryWrapper<Book> qw = new LambdaQueryWrapper<Book>();
        qw.like(name!=null,Book::getName,name);

        IPage<Book> page = new Page<Book>(1,5); //创建一个分页
        bookDao.selectPage(page,qw);
        System.out.println(page.getCurrent()); //当前页
        System.out.println(page.getPages()); //总页数
        System.out.println(page.getSize()); //每页的条数
        System.out.println(page.getRecords()); //本次查询的返回值
        System.out.println(page.getTotal()); //总条数
    }

    @Test
    void testGetBy(){
//        select * from tpl_book where name like "1"
        QueryWrapper<Book> qw = new QueryWrapper<Book>();
        qw.like("name","网络");
        bookDao.selectList(qw);
    }

    @Test
    void testGetBy2(){
        String name = "网络";
//        String name = null;
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<Book>();
//        if (name != null) lqw.like(Book::getName,name);
        lqw.like(name!=null,Book::getName,name);
        bookDao.selectList(lqw);
    }
}
