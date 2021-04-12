package com.hopu.service;

import com.github.pagehelper.PageInfo;
import com.hopu.domain.User;
import com.hopu.mapper.UserMapper;

import java.util.List;

public interface UserService {

     PageInfo<User> findPage(Integer pageNum, Integer pageSize);
     void add(User user);

     User findById(Integer id);

     void update(User user);

     void deleteById(Integer id);

     User findByUserName(String username);

     User login(User user);
}
