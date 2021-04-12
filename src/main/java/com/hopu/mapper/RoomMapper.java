package com.hopu.mapper;

import com.hopu.domain.Room;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RoomMapper {

    List<Room> findAll(@Param("rentStatus")Integer rentStatus,
                       @Param("regionId") Integer regionId,
                       @Param("beginRent") int beginRent,
                       @Param("endRent") int endRent,
                       @Param("userId") Integer userId);

    void save(Room room);

    @Select("select * from t_room where id=#{id}")
    Room findById(Integer id);

    void update(Room room);

    @Delete("delete from t_room where id=#{id}")
    void deleteById(Integer id);

    @Update("update t_room set rent_status=#{rentStatus} where id=#{id}")
    void updateRoom(@Param("id")Integer id,@Param("rentStatus")int rentStatus);

    List<Room> findAllByIds(List<Integer> roomIdList);
}
