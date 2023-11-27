package com.ouc.mallsecurity.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.ouc.mallcommon.exception.ServiceException;
import com.ouc.mallmbg.mapper.UserMapper;
import com.ouc.mallmbg.model.User;
import com.ouc.mallmbg.model.UserExample;
import com.ouc.mallsecurity.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /*
    * 通过邮箱检索用户
    * */
    @Override
    public User findByUserEmail(String email){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserEmailEqualTo(email);
        User user = (User) userMapper.selectByExample(userExample);
        if(ObjUtil.isEmpty(user)) throw new ServiceException(406, "该用户不存在");
        return user;
    }

    @Override
    public void addUser(User user){
        userMapper.insert(user);
    }

    @Override
    public boolean pwdValidate(String userEmail, String userPwd){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserEmailEqualTo(userEmail);
        userExample.createCriteria().andUserPwdEqualTo(userPwd);
        User result = (User) userMapper.selectByExample(userExample);
        return result != null;
    }
}
