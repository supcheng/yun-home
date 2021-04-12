package com.hopu.web.controller.admin;

import com.github.pagehelper.PageInfo;
import com.hopu.domain.User;
import com.hopu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/user")
public class AUserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list",name = "进入用户界面")
    public String findAll(HttpServletRequest request,
                          @RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "5") Integer pageSize){
        PageInfo<User> pageInfo = userService.findPage(pageNum,pageSize);
        request.setAttribute("page",pageInfo);
        return "admin/system/user/user-list";

    }
    @RequestMapping("/toAddPage")
    public String toAddPage(){
        return "admin/system/user/user-add";
    }

    @RequestMapping("/add")
    public String add(User user,HttpServletRequest request){
        user.setRole("管理员");
        userService.add(user);
        return "redirect:/admin/user/list";
    }

    @RequestMapping("/toUpdatePage")
    public String toUpdatePage(Integer id,HttpServletRequest request){
        User user=userService.findById(id);
        request.setAttribute("user",user);
        return "admin/system/user/user-update";
    }

    @RequestMapping("/update")
    public String update(User user,HttpServletRequest request){
        userService.update(user);
        return "redirect:/admin/user/list";
    }
    @RequestMapping("/delete")
    public String delete(Integer id){
        userService.deleteById(id);
        return "redirect:/admin/user/list";
    }


}
