package com.hopu.mapper;

import com.hopu.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from t_user")
    List<User> findAll();

    void save(User user);

    @Select("select * from t_user where id=#{id}")
    User findById(Integer id);

    void update(User user);

    @Delete("delete from t_user where id=#{id}")
    void deleteById(Integer id);

    @Select("select * from t_user where username=#{username}")
    User findByUserName(String username);

    @Select("select * from t_user where username=#{username} and password=#{password}" )
    User findByUserNameAndPWD(@Param("username")String username,@Param("password")String password);
}
