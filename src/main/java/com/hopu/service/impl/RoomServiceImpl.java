package com.hopu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.hopu.domain.Favority;
import com.hopu.domain.Room;
import com.hopu.domain.RoomImg;
import com.hopu.mapper.RoomImgMapper;
import com.hopu.mapper.RoomMapper;
import com.hopu.service.FavorityService;
import com.hopu.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomImgMapper roomImgMapper;

    @Autowired
    private FavorityService favorityService;

    @Override
    public PageInfo<Room> findPage(Integer pageNum, Integer pageSize,Integer rentStatus,Integer regionId,String rent,Integer userId) {
        PageHelper.startPage(pageNum,pageSize);
        if(regionId!=null&&regionId==0){
            regionId=null;
        }
        int beginRent=0;
        int endRent=99999999;
        if(StringUtil.isNotEmpty(rent)){
            String[] rents=rent.split("_");
            beginRent=Integer.parseInt(rents[0]);
            endRent=Integer.parseInt(rents[1]);
        }
        List<Room> list = roomMapper.findAll(rentStatus,regionId,beginRent,endRent,userId);
        return new PageInfo(list);
    }

    @Override
    public void add(Room room, MultipartFile[] uploadFiles) {
        roomMapper.save(room);
        File file =new File("D:\\soft\\nginx-1.18.0\\html");
        for (MultipartFile multipartFile:uploadFiles){
            String newFileName= UUID.randomUUID()+multipartFile.getOriginalFilename();
            try {
                multipartFile.transferTo(new File(file,newFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            roomImgMapper.add(room.getId(),newFileName);
        }
    }

    @Override
    public Room findById(Integer id) {
        return roomMapper.findById(id);
    }

    @Override
    public void update(Room room) {
        room.setRentStatus(-1);
        roomMapper.update(room);
    }

    @Override
    public void deleteById(Integer id) {
        roomMapper.deleteById(id);
        List<RoomImg> roomImgList=roomImgMapper.findByRoomId(id);
        roomImgMapper.deleteByRoomId(id);
        roomImgList.forEach(roomImg -> {
            String img= roomImg.getImg();
            File file=new File("D:\\soft\\nginx-1.18.0\\html",img);
            if (file.exists()){
                file.delete();
            }
        });
    }

    @Override
    public void updateRoomRentStatus(Integer id, int rentStatus) {
        roomMapper.updateRoom(id,rentStatus);

    }

    @Override
    public PageInfo<Room> findFavorityByPage(Integer pageNum, Integer pageSize, Integer userId) {
        List<Favority> favorities=favorityService.findByUserId(userId);
        List<Integer> roomIdList=favorities.stream().map(favority -> favority.getRoomId()).collect(Collectors.toList());
        if(roomIdList==null || roomIdList.size()<=0){
            return null;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Room> list = roomMapper.findAllByIds(roomIdList);
        list.forEach(room -> {
            List<RoomImg> roomImgList = roomImgMapper.findByRoomId(room.getId());
            room.setRoomImgList(roomImgList);
        });
        return new PageInfo(list);

    }


}
