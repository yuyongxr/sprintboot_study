package com.jiang.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * AuthenticatingRealm 该抽象类只能做认证，不能做授权，授权需要继承其他的类
 * AuthorizingRealm 该抽象类既能认证、也能授权，继承该类，需要实现doGetAuthorizationInfo（）方法，该方法在每次需要授权的时候都会自动调用
 */

public class MyRealm extends AuthorizingRealm {
    /**
     * 认证方法
     * @param authenticationToken
     * @return SimpleAuthenticationInfo类型
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token  = (UsernamePasswordToken) authenticationToken;

        //使用传递来的token获取用户提供的username
        String username = token.getUsername();

        //使用传递来的token获取用户提供的password
        String password = new String(token.getPassword());

        System.out.println(username + ":" + password);

        /**
         * 认证账号，这里应该从数据库中获取数据,这里只是模拟一下
         * 如果进入if，表示账号不存在，抛出账号 不存在 的异常
         */
        if(!"admin".equals(username) && !"zhangsan".equals(username) && !"user".equals(username)){
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
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
//        hashedCredentialsMatcher.setHashIterations(1);
//
//        //为当前realm设置加密方式
//        this.setCredentialsMatcher(hashedCredentialsMatcher);

        //为了测试需要，对123456进行加密，随后会和用户提交的密码 加密后进行对比
        //若数据库中的密码也没有加密，也需要通过此方法进行加密后对比，一般情况下，数据库中存储的应该是加密后的密码
//        Object obj = new SimpleHash("MD5","123456","",1);
//        System.out.println(obj);

        /**
         * 创建密码认证对象，由shiro自动认证密码
         * 参数1 用户提交的username，或数据库中的username
         * 参数2 数据库中读取出来的密码
         * 参数3 为当前realm的名字
         * 如果密码认证成功，返回一个用户身份对象
         * 如果认证失败shiro会抛出一个 incorrectCredentialsException  异常
         */
        return new SimpleAuthenticationInfo(username,"e10adc3949ba59abbe56e057f20f883e",getName());
    }


    /**
     * 该方法在需要授权的时候会自动调用
     * 这里应该查询数据库，获取当前用户的所有角色和授权，并设置到shiro中
     * 注意 在真实环境中，每次访问都会从数据库中查询并赋值给set集合，这样效率不高，需要优化该代码
     * @param principals
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //System.out.println("--------------授权-------------");
        Set<String> roles = new HashSet<String>();//定义一个Set集合，用来存放当前用户的角色，最后设置到simpleAuthorizationInfo返回值中
        Object primaryPrincipal = principals.getPrimaryPrincipal();//获取当前用户

        //这段用来授权，在实际环境中，所有的角色应该从数据库中取出，然后再和用户角色表对比，看该用户是否拥有该角色
        //这里只是根据用户来简单模拟一下，admin用户为admin角色，user为user角色
        if ("admin".equals(primaryPrincipal)){
            roles.add("admin");
            roles.add("user");
        }
        if ("user".equals(primaryPrincipal)){
            roles.add("user");
        }

        Set<String> permission = new HashSet<>();
        if ("admin".equals(primaryPrincipal)){
            permission.add("admin:add");
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);//将存储角色信息的SET集合，设置到simpleAuthorizationInfo中
        simpleAuthorizationInfo.setStringPermissions(permission);//设置用户子权限
        return simpleAuthorizationInfo;
    }
}
