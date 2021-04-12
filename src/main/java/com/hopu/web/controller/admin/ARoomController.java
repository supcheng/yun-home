package com.hopu.web.controller.admin;

import com.github.pagehelper.PageInfo;
import com.hopu.domain.Room;
import com.hopu.domain.User;
import com.hopu.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/admin/room")
public class ARoomController {

    @Autowired
    private RoomService roomService;
    @RequestMapping(value = "/list",name = "进入房屋管理界面")
    public String findAll(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          HttpServletRequest request){
        User loginUser=(User)request.getSession().getAttribute("loginUser");
        Integer userId=loginUser.getId();
        if ("管理员".equals(loginUser.getRole())){
            userId=null;
        }
        PageInfo<Room> pageInfo=roomService.findPage(pageNum,pageSize,null,null,null,userId);
        request.setAttribute("page" ,pageInfo);
        return "admin/system/room/room-list";
    }

    @RequestMapping("/toAddPage")
    public String toAddPage(){
        return "admin/system/room/room-add";
    }

    @RequestMapping("/add")
    public String add(Room room, MultipartFile[] roomImgs,HttpServletRequest request) throws IOException {
        User loginUser=(User)request.getSession().getAttribute("loginUser");
        room.setUserId(loginUser.getId());
        room.setRentStatus(-1);
        roomService.add(room,roomImgs);
        return "redirect:/admin/room/list";
    }

    @RequestMapping("/toUpdatePage")
    public String toUpdatePage(HttpServletRequest request,Integer id){
        Room room=roomService.findById(id);
        request.setAttribute("room",room);
        return "admin/system/room/room-update";
    }

    @RequestMapping("/update")
    public String update(Room room){
        roomService.update(room);
        return "redirect:/admin/room/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id){
        roomService.deleteById(id);
        return "redirect:/admin/room/list";
    }

    @RequestMapping("/up")
    public String up(Integer id){
        roomService.updateRoomRentStatus(id,0);
        return "redirect:/admin/room/list";
    }

    @RequestMapping("/down")
    public String down(Integer id){
        roomService.updateRoomRentStatus(id,-1);
        return "redirect:/admin/room/list";
    }
}
