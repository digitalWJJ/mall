package com.ouc.malladmin.service.impl;

import com.ouc.malladmin.service.UserService;
import com.ouc.mallcommon.tools.CacheTool;
import com.ouc.mallmbg.mapper.UserMapper;
import com.ouc.mallmbg.model.PageParam;
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
    public List<User> getusers(PageParam pageParam){
        List<User> users = CacheTool.getUsers(pageParam);
        return users;
    }
    @Override
    public User getuser(Integer id){
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }
    @Override
    public void deleteuser(Integer id){
        userMapper.deleteByPrimaryKey(id);
    }
    @Override
    public int adduser(User user){
        int j = userMapper.insert(user);
        return j;
    }
    @Override
    public int updateuser(User user){
        int j = userMapper.updateByPrimaryKeySelective(user);
        return j;
    }
}
