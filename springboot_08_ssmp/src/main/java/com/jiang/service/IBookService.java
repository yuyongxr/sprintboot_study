package com.jiang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.domain.Book;


//extends IService<Book> MP快速实现
public interface IBookService extends IService<Book> {

    IPage<Book> getPage(Integer currentPage,Integer pageSize);
}
