package com.hopu.service.impl;

import com.hopu.domain.Favority;
import com.hopu.mapper.FavorityMapper;
import com.hopu.service.FavorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FavorityServiceImpl implements FavorityService {
    @Autowired
    FavorityMapper favorityMapper;
    @Override
    public boolean findIfFavority(Integer roomId, Integer userId) {
        Favority favority=favorityMapper.findByRoomIdAndUserName(roomId,userId);
        if (favority==null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void cancleFavorite(Integer roomId, Integer userId) {
        favorityMapper.cancleFavorite(roomId,userId);
    }

    @Override
    public void addFavorite(Integer roomId, Integer userId) {
        Favority favority=favorityMapper.findByRoomIdAndUserName(roomId,userId);

        if (favority==null){
            favorityMapper.addFavorite(roomId,userId,new Date());

        }
    }

    @Override
    public List<Favority> findByUserId(Integer userId) {
        return favorityMapper.findByUserId(userId);
    }
}
