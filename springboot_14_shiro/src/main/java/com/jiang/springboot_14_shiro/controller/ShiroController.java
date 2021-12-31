package com.jiang.springboot_14_shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping("/nopermission")
    public String noPermission(){
        return "nopermission";
    }

    @RequestMapping("/admin/test")
    public @ResponseBody String adminTest(){
        return "/admin/test请求";
    }

    @RequestMapping("/user/test")
    public @ResponseBody String userTest(){
        return "/user/test请求";
    }
}
