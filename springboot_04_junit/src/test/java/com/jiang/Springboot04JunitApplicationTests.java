package com.jiang;

import com.jiang.dao.BookDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//如果测试类不在引导类的同目录下，必须通过classes指定引导类，否则测试类找不到
@SpringBootTest//(classes = Springboot04JunitApplication.class)
class Springboot04JunitApplicationTests {
    //1、注入你要测试的对象
    @Autowired   //自动装配，重点，必须使用自动装配注入
    private BookDao bookDao;

    @Test
    void contextLoads() {
        //执行测试对象的方法
        bookDao.save();
    }

}
