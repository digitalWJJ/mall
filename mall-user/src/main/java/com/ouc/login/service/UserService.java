package com.ouc.login.service;

import com.ouc.mallmbg.model.Indent;
import com.ouc.mallmbg.model.User;

import java.util.List;

public interface UserService {
    User getItem(int id);
    int updatePassword(int id,String password);



    int updateUserInfo(int id,String  userName, String gender, String stateMessage);
}
