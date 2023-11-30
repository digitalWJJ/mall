package com.ouc.malladmin.service.impl;

import com.ouc.malladmin.service.UserService;
import com.ouc.mallmbg.mapper.UserMapper;
import com.ouc.mallmbg.model.User;
import com.ouc.mallmbg.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getusers(UserExample userExample){
        List<User> users = new ArrayList<User>();
        users = userMapper.selectByExample(userExample);
        return users;
    }
    @Override
    public User getuser(Integer id){
        User user = new User();
        user = userMapper.selectByPrimaryKey(id);
        return user;
    }
    @Override
    public void deleteuser(Integer id){
        userMapper.deleteByPrimaryKey(id);
    }
}
