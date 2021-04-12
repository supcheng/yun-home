package com.hopu.web.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.hopu.domain.Region;
import com.hopu.domain.Room;
import com.hopu.service.RegionService;
import com.hopu.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/region")
public class ARegionController {
    @Autowired
    private RegionService regionService;
    /**
     * 分页查询所有房屋区域信息
     */
    @RequestMapping(value="/findAll")
    @ResponseBody
    public List<Region> findAll(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          HttpServletRequest request){
        List<Region> regionList =regionService.findAll();

        return regionList;
    }

}
