package com.hopu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hopu.domain.User;
import com.hopu.mapper.UserMapper;
import com.hopu.service.UserService;
import com.hopu.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> list =userMapper.findAll();
        return new PageInfo(list);
    }

    @Override
    public void add(User user) {
        String password=user.getPassword();
        password= MD5Util.encodeByMd5(password);
        user.setPassword(password);
        userMapper.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public User login(User user) {
        String password=user.getPassword();
        password=MD5Util.encodeByMd5(password);
        return userMapper.findByUserNameAndPWD(user.getUsername(),password);
    }
}
