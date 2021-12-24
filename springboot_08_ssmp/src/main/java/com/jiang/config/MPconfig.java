package com.jiang.config;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//创建拦截器加载器，new 内建的拦截器，并添加，否则MP不会开启分页查询，固定格式
@Configuration
public class MPconfig {
    @Bean
    public MybatisPlusInterceptor  mybatisPlusInterceptor(){
        MybatisPlusInterceptor  interceptor = new MybatisPlusInterceptor ();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
