package com.ouc.mallsecurity.service;

import com.ouc.mallmbg.model.User;

public interface UserService {
    /*
    * 通过邮箱检索用户
    * */
    User findByUserEmail(String email);

    /*
    * 添加一个新用户
    * */
    void addUser(User user);

    /*
    * 验证用户密码是否正确
    * */
    boolean pwdValidate(String userEmail, String userPwd);
}
