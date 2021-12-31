package com.jiang.springboot_14_shiro.config;

import com.jiang.springboot_14_shiro.realm.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public MyRealm myRealm(){
        return new MyRealm();
    }

    /**
     * 配置安全管理器
     * @return SecurityManager
     */
    @Bean
    public SecurityManager securityManager(Realm myRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        shiroFilter.setLoginUrl("/"); //转向到登录请求的
        shiroFilter.setSuccessUrl("/success"); //认证成功
        shiroFilter.setUnauthorizedUrl("/noPermission"); //未授权

        Map<String,String> filerChainMap = new LinkedHashMap<>();

        filerChainMap.put("/login","anon");
        filerChainMap.put("/logout","logout");

        filerChainMap.put("/admin/**","authc");
        filerChainMap.put("/user/**","authc");
        filerChainMap.put("/**","authc");

        shiroFilter.setFilterChainDefinitionMap(filerChainMap);

        return shiroFilter;
    }
}
