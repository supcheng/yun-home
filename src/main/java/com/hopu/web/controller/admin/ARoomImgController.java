package com.hopu.web.controller.admin;


import com.github.pagehelper.PageInfo;
import com.hopu.domain.RoomImg;
import com.hopu.service.RoomImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/roomImg")
public class ARoomImgController {
    @Autowired
    private RoomImgService roomImgService;

    @RequestMapping(value = "/list",name = "进入房屋图片管理界面")
    public String findAll(HttpServletRequest request,
                          @RequestParam(defaultValue = "1")Integer pageNum,
                          @RequestParam(defaultValue = "10")Integer pageSize){
        PageInfo<RoomImg> pageInfo=roomImgService.findPage(pageNum,pageSize);
        request.setAttribute("page",pageInfo);
        return "admin/system/roomImg/roomImg-list";
    }


}
