package com.jiang.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShiroController {

    @RequestMapping("/")
    public String index(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model){

        //获取权限操作对象，利用这个对象来完成登录操作
        Subject subject = SecurityUtils.getSubject();

        //登录前，先清除历史用户登录的 登录信息，因为用户请求这个url一定是要完成登录，否者shiro会有缓存，用户不能登录
        subject.logout();

        //用户是否认证过（是否登录过），进入if表示用户没有认证过需要进行认证
        if(!subject.isAuthenticated()){

            //使用我们从页面传递来的账号和密码，生成用户的身份令牌
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            token.setRememberMe(true);
            //System.out.println("controller /login:"+username+password);

            try {
                /**
                 * 该方法表示自动登录，登录时，会自动调用我们Realm对象中的认证方法
                 * 如果登录失败，会抛出各种异常
                 */
                subject.login(token);
            } catch (UnknownAccountException e) {
                //e.printStackTrace();
                model.addAttribute("errorMessage","用户不存在");
                return "login";
            } catch (LockedAccountException e) {
                //e.printStackTrace();
                model.addAttribute("errorMessage","账号被锁定");
                return "login";
            } catch (IncorrectCredentialsException e) {
                //e.printStackTrace();
                model.addAttribute("errorMessage","密码错误");
                return "login";
            } catch (AuthenticationException e) {
                //e.printStackTrace();
                model.addAttribute("errorMessage","认证失败");
                return "login";
            }
        }

        return "redirect:/success";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        //登出当前用户，清空shiro当前用户的缓存
        subject.logout();
        return "redirect:/";
    }

    @RequestMapping("/success")
    public String loginSuccess(){
        return "success";
    }

    @RequestMapping("/noPermission")
    public String noPermission(){
        return "noPermission";
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping("/admin/test")
    public @ResponseBody String adminTest(){
        return "/admin/test请求";
    }

    @RequiresRoles(value = {"admin"})
    @RequiresPermissions(value = {"admin:add"})
    @RequestMapping("/admin/add")
    public @ResponseBody String adminAdd(){
        return "/admin/add请求";
    }

    //logical 默认为Logical.AND ，当权限是或的时候，需要手工修改为以下示例
    @RequiresRoles(value = {"admin","user"},logical = Logical.OR)
    @RequestMapping("/user/test")
    public @ResponseBody String userTest(){
        return "/user/test请求";
    }

    /**
     * 自定义异常，用于处理没有权限的异常,如不处理，会出现500的错误
     */
    @ExceptionHandler(value = {AuthorizationException.class})
    public String permission(Throwable throwable){
        return "noPermission";
    }
}
