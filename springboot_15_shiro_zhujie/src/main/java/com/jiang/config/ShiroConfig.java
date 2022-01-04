package com.jiang.config;

import com.jiang.realm.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
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
        filerChainMap.put("/static/**","anon"); // js目录下，均不需要认证
        filerChainMap.put("/logout","logout");
        // roles[admin]表示该资源需要admin角色的授权才能访问,注意，这里为角色，所有的授权均基于角色授权
//        filerChainMap.put("/admin/**","authc,roles[admin]");
//        filerChainMap.put("/user/**","authc,roles[user]");
//        filerChainMap.put("/**","authc");

        shiroFilter.setFilterChainDefinitionMap(filerChainMap);

        return shiroFilter;
    }

    /**
     * 开启shiro注解支持
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 开启spring AOP支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

