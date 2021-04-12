package com.hopu.mapper;

import com.hopu.domain.RoomImg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoomImgMapper {

    @Insert("INSERT INTO t_room_img(img,room_id) VALUES (#{s},#{id})")
    void add(@Param("id") Integer id, @Param("s") String s);


    @Select("select * from t_room_img where room_id=#{id}")
    List<RoomImg> findByRoomId(Integer id);

    @Delete("delete from t_room_img where room_id=#{id}")
    void deleteByRoomId(Integer id);

    List<RoomImg> findAll();

}
