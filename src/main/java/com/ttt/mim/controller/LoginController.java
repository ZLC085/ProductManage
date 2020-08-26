package com.ttt.mim.controller;

import com.ttt.mim.common.utils.CookieUtils;
import com.ttt.mim.domain.User;
import com.ttt.mim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 登录 Controller
 *
 * @author zl
 * @date 2019/8/5 16:00
 */

@Controller
@RequestMapping("/user")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService, HttpServletRequest request) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping("/checkLogin")
    //@ResponseBody
    public String Login(String username,
                        String password,
                        Model model,
                        HttpServletResponse response) {
        System.out.println(username);
        System.out.println(password);
        //调用service方法
        User user = userService.checkLogin(username, password);

        //若有user则添加到model里并且跳转到成功页面
        if (user != null) {
            model.addAttribute("user", user);

            //将用户名和用户id存入cookie
            CookieUtils cookieUtils = new CookieUtils();
            cookieUtils.addCookie(response, "userName", user.getUsername());
            cookieUtils.addCookie(response, "userId", user.getId().toString());

            return "success";
        }
        return "fail";
    }

}
