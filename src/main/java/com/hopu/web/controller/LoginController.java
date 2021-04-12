package com.hopu.web.controller;

import com.hopu.domain.User;
import com.hopu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("toRegisterPage")
    public String toRegisterPage(){
        return "register";
    }


    @RequestMapping("/register")
    public String register(User user, Model model){
        try {
            userService.add(user);
            return "login";
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("msg","用户注册失败"+e.getMessage());
            return "register";
        }

    }

    @RequestMapping("/checkUserName")
    @ResponseBody
    public String checkUserName(String username){
        User user=userService.findByUserName(username);
        if (user==null){
            return "0";
        }else {
            return "1";
        }
    }

    @RequestMapping("/toLoginPage")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(User user, Model model, HttpServletRequest request){
        User user1=userService.login(user);
        if (user1!=null){
            request.getSession().setAttribute("loginUser",user1);
            return "redirect:/index.jsp";
        }else {
            model.addAttribute("loginError_msg","用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("loginUser");
        return "redirect:/index.jsp";
    }

    // 用户异步登录
    @RequestMapping("/asyncLogin")
    @ResponseBody
    public String asyncLogin(User user, Model model, HttpServletRequest request){
        User user1 =userService.login(user);
        if(user1!=null){
            request.getSession().setAttribute("loginUser",user1);
            return user1.getUsername();
        }else{
            return "-1";
        }
    }

}
