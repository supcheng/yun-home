package com.hopu.service;

import com.github.pagehelper.PageInfo;
import com.hopu.domain.Room;
import org.springframework.web.multipart.MultipartFile;

public interface RoomService {
    PageInfo<Room> findPage(Integer pageNum, Integer pageSize,Integer rentStatus,Integer regionId,String rent,Integer userId);
    void add(Room room, MultipartFile[] roomImgs);
    Room findById(Integer id);
    void update(Room room);
    void deleteById(Integer id);
    void updateRoomRentStatus(Integer id,int rentStatus);
    PageInfo<Room> findFavorityByPage(Integer pageNum,Integer pageSize,Integer userId);

}
