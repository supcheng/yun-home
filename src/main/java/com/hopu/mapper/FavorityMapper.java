package com.hopu.mapper;

import com.hopu.domain.Favority;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface FavorityMapper {
    @Select("select * from t_favorites where room_id=#{roomId} and user_id=#{userId}")
    Favority findByRoomIdAndUserName(@Param("roomId") Integer roomId,@Param("userId") Integer userId);

    @Select("delete from t_favorites where room_id=#{roomId} and user_id=#{userId}")
    void cancleFavorite(@Param("roomId") Integer roomId,@Param("userId") Integer userId);

    @Insert("insert into t_favorites (room_id,user_id,create_time) values (#{roomId},#{userId},#{createTime})")
    void addFavorite(@Param("roomId") Integer roomId, @Param("userId") Integer userId, @Param("createTime") Date createTime);

    @Select("select * from t_favorites where user_id=#{userId}")
    List<Favority> findByUserId(Integer userId);
}
