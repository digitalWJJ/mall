package com.ouc.malladmin.service;

import com.ouc.mallmbg.model.Order;
import com.ouc.mallmbg.model.User;
import com.ouc.mallmbg.model.UserExample;

import java.util.List;

public interface UserService {
    List<User> getusers(UserExample userExample);
    User getuser(Integer id);
    void deleteuser(Integer id);
}
