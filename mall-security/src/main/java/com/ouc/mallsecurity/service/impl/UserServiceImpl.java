package com.ouc.mallsecurity.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.ouc.mallcommon.exception.ServiceException;
import com.ouc.mallmbg.mapper.UserMapper;
import com.ouc.mallmbg.model.User;
import com.ouc.mallmbg.model.UserExample;
import com.ouc.mallsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        List<User> result = userMapper.selectByExample(userExample);
        if( result.size() == 1) return result.get(0);
        else if(result.size() == 0) throw new ServiceException(406, "该用户不存在");
        else throw new ServiceException(500, "数据库用户信息错误");
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
        int count = userMapper.selectByExample(userExample).size();
        if( count == 0 )  return false;
        else if(count == 1) return true;
        else throw new ServiceException(500, "数据库用户信息错误");
    }

    @Override
    public boolean isUserExist(String email) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserEmailEqualTo(email);
        int count = userMapper.selectByExample(userExample).size();
        if( count == 0 )  return false;
        else if(count == 1) return true;
        else throw new ServiceException(500, "数据库用户信息错误");
    }
}
