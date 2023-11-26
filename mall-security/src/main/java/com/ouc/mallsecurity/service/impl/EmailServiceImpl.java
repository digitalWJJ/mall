package com.ouc.mallsecurity.service.impl;

import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import com.ouc.mallcommon.exception.ServiceException;
import com.ouc.mallsecurity.model.EmailModel;
import com.ouc.mallsecurity.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.email}")
    private String email;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private String port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;

    @Override
    public void send(EmailModel emailModel) {


        MailAccount account = new MailAccount();

        account.setHost(host);
        account.setPort(Integer.parseInt(port));
        // 设置发送人邮箱
        account.setFrom(email);
        // 设置发送人名称
        account.setUser(username);
        // 设置发送授权码
        account.setPass(password);
        account.setAuth(true);
        // ssl方式发送
        account.setSslEnable(true);
        // 使用安全连接
        account.setStarttlsEnable(true);

        try{
//            int size = emailModel.getTos();
            Mail.create(account)
                    .setTos(emailModel.getTos())
                    .setTitle(emailModel.getSubject())
                    .setContent(emailModel.getContent())
                    .setHtml(true)
                    .setUseGlobalSession(false)
                    .send();
        } catch (Exception e){
            throw new ServiceException(500,e.getMessage());
        }
    }
}
