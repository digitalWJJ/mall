package com.ouc.malladmin.service;

import com.ouc.mallmbg.model.PageParam;
import com.ouc.mallmbg.model.User;
import com.ouc.mallmbg.model.UserExample;

import java.util.List;

public interface UserService {
    List<User> getusers(PageParam pageParam);
    User getuser(Integer id);
    void deleteuser(Integer id);
    int adduser(User user);
    int updateuser(User user);
}
