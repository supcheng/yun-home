package com.hopu.service;

import com.github.pagehelper.PageInfo;
import com.hopu.domain.RoomImg;

import java.util.List;

public interface RoomImgService {
    PageInfo<RoomImg> findPage(Integer pageNum,Integer pageSize);
    List<RoomImg> findByRoomId(Integer roomId);
}
