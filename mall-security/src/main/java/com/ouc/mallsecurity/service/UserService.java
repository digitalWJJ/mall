package com.ouc.mallsecurity.service;

import com.ouc.mallmbg.model.User;

public interface UserService {
    /**
     * 通过邮箱检索用户
     *
     * @return 有用户就返回 user 没有就抛出 ServiceException
     * */
    User findByUserEmail(String email);

    /**
     * 添加一个新用户
     *
     * @param user 需要被添加的用户对象
     * */
    void addUser(User user);

    /**
     * 验证用户密码是否正确
     *
     * @return true or false
     * */
    boolean pwdValidate(String userEmail, String userPwd);

    /**
     * 验证是否存在指定邮箱的用户
     *
     * @param email 邮箱
     * */
    boolean isUserExist(String email);
}
