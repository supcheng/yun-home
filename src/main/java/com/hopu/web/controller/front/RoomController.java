package com.hopu.web.controller.front;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.hopu.domain.*;
import com.hopu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/front/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private UserService userService;
    @Autowired
    private RoomImgService roomImgService;
    @Autowired
    FavorityService favorityService;

    /**
     * 前台分页显示房屋信息
     */
    @RequestMapping(value = "/list", name = "分页查询所有房屋信息页面")
    public String findAll(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(value = "regionId", required = false) Integer regionId,
                          @RequestParam(value = "regionName", required = false) String regionName,
                          @RequestParam(value = "rent", required = false) String rent,
                          HttpServletRequest request) {
        int rentStatus = 0;
        PageInfo<Room> pageInfo = roomService.findPage(pageNum, pageSize, rentStatus,regionId,rent,null);
        request.setAttribute("page", pageInfo);
        request.setAttribute("pageNum", pageNum);
        request.setAttribute("pageSize", pageSize);

        List<Region> regions = regionService.findAll();
        request.setAttribute("regions", regions);
        request.setAttribute("regionId", regionId);
        request.setAttribute("regionName", regionName);
        if (rent != null) {
            if ("0_500".equals(rent)) {
                request.setAttribute("rent", "500元以下");
            } else if ("4500_999999".equals(rent)) {
                request.setAttribute("rent", "4500元以上");
            } else if (StringUtil.isNotEmpty(rent)) {
                request.setAttribute("rent", rent + "元");
            }
        }
        return "front/room_list";
    }

    @RequestMapping("/toRoomDetails")
    public String toRoomDetailsPage(Integer id,HttpServletRequest request){
        Room room =roomService.findById(id);
        List<RoomImg> roomImgs = roomImgService.findByRoomId(room.getId());
        room.setRoomImgList(roomImgs);

        User user = userService.findById(room.getUserId());
        room.setUser(user);

        Region region = regionService.findById(room.getRegionId());
        room.setRegion(region);

        // 存储在域对象中
        request.setAttribute("room",room);

        return "front/room_details";
    }

    @RequestMapping("/ifFavorite")
    @ResponseBody
    public String ifFavorite(Integer roomId,HttpServletRequest request){
        Object loginUser=request.getSession().getAttribute("loginUser");
        if(loginUser==null){
            return "-1";
        }else {
            User user=(User)loginUser;
            boolean ifFavority= favorityService.findIfFavority(roomId,user.getId());
            if(ifFavority){
                // 表示已经收藏
                return "1";
            }else {
                return "0"; // 表示登录，但未收藏
            }

        }
    }

    // 取消收藏
    @RequestMapping("/cancleFavorite")
    @ResponseBody
    public String cancleFavorite(Integer roomId,HttpServletRequest request){
        try {
            User loginUser = (User) request.getSession().getAttribute("loginUser");
            favorityService.cancleFavorite(roomId,loginUser.getId());
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }
    // 添加收藏
    @RequestMapping("/addFavorite")
    @ResponseBody
    public String addFavorite(Integer roomId,HttpServletRequest request){
        try {
            User loginUser = (User) request.getSession().getAttribute("loginUser");
            favorityService.addFavorite(roomId,loginUser.getId());
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }



}
