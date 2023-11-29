package com.ouc.mallsecurity.service;

import com.ouc.mallsecurity.model.EmailModel;

public interface EmailService {

    /**
    * 发送邮件
    * @param emailModel 发送的邮箱对象
    * */
    void send(EmailModel emailModel);
}
