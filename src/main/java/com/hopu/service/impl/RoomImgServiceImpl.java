package com.hopu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hopu.domain.RoomImg;
import com.hopu.mapper.RoomImgMapper;
import com.hopu.service.RoomImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomImgServiceImpl implements RoomImgService {
    @Autowired
    private RoomImgMapper roomImgMapper;

    @Override
    public PageInfo<RoomImg> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<RoomImg> roomImgList = roomImgMapper.findAll();
        return new PageInfo(roomImgList);
    }

    @Override
    public List<RoomImg> findByRoomId(Integer roomId) {
        return roomImgMapper.findByRoomId(roomId);
    }


}
