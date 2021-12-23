package com.jiang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
//extends BaseMapper 会自动集成各种操作代码
public interface BookDao extends BaseMapper<Book> {
//    @Select("select * from tbl_book where id = #{id}")
//    Book getById(Integer id);
}
