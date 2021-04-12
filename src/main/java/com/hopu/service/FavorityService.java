package com.hopu.service;

import com.hopu.domain.Favority;

import java.util.List;

public interface FavorityService {
    boolean findIfFavority(Integer roomId,Integer userId);
    void cancleFavorite(Integer roomId,Integer userId);
    void addFavorite(Integer roomId,Integer userId);
    List<Favority> findByUserId(Integer userId);
}
