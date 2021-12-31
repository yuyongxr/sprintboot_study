package com.jiang.springboot_14_shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;

/**
 *
 */

public class MyRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token  = (UsernamePasswordToken) authenticationToken;

        //使用传递来的token获取用户提供的username
        String username = token.getUsername();

        //使用传递来的token获取用户提供的password
        String password = new String(token.getPassword());

        //System.out.println(username + ":" + password);

        /**
         * 认证账号，这里应该从数据库中获取数据
         * 如果进入if，表示账号不存在，抛出账号 不存在 的异常
         */
        if(!"admin".equals(username) && !"zhangsan".equals(username)){
            throw new UnknownAccountException();//抛出 不存在的账号 异常
        }

        /**
         * 认证账号，这里应该从数据库中获取数据来进行逻辑判断，判断当前账号是否可用
         * IP是否允许等等，根据不同的逻辑抛出不同的异常
         */
        if("zhangsan".equals(username)){
            throw new LockedAccountException();//抛出 账号被锁定 异常
        }

        /**
         * 这里的加密意义不大，真正加密应该在前端加密，
         * 创建加密方式
         */
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(2);

        //为当前realm设置加密方式
        this.setCredentialsMatcher(hashedCredentialsMatcher);

        //为了测试需要，对123456进行加密，随后会和用户提交的密码 加密后进行对比
        //若数据库中的密码也没有加密，也需要通过此方法进行加密后对比，一般情况下，数据库中存储的应该是加密后的密码
        Object obj = new SimpleHash("MD5","123456","",2);

        /**
         * 创建密码认证对象，由shiro自动认证密码
         * 参数1 用户提交的username，或数据库中的username
         * 参数2 数据库中读取出来的密码
         * 参数3 为当前realm的名字
         * 如果密码认证成功，返回一个用户身份对象
         * 如果认证失败shiro会抛出一个 incorrectCredentialsException  异常
         */
        return new SimpleAuthenticationInfo(username,obj,getName());
    }
}
