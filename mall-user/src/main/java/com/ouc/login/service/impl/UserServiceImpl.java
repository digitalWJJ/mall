package com.ouc.login.service.impl;

import com.ouc.login.service.UserService;
import com.ouc.mallmbg.mapper.IndentMapper;
import com.ouc.mallmbg.mapper.UserMapper;
import com.ouc.mallmbg.model.Indent;
import com.ouc.mallmbg.model.IndentExample;
import com.ouc.mallmbg.model.User;
import com.ouc.mallmbg.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User getItem(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updatePassword(int id, String password) {
        User user=new User();
        user.setId(id);
        user.setUserPwd(password);
        return userMapper.updateByPrimaryKeySelective(user);
    }



    @Override
    public int updateUserInfo(int id, String userName, String gender, String stateMessage, String userImg) {
       User user=new User();
       user.setId(id);
       user.setUserName(userName);
       user.setGender(gender);
       user.setStateMessage(stateMessage);
       user.setUserImage(userImg);
       return userMapper.updateByPrimaryKeySelective(user);
    }
}
