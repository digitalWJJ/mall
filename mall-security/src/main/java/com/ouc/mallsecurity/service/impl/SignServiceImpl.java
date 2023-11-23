package com.ouc.mallsecurity.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.ouc.mallcommon.exception.ServiceException;
import com.ouc.mallcommon.utils.RedisUtils;
import com.ouc.mallcommon.utils.TokenUtils;
import com.ouc.mallmbg.model.User;
import com.ouc.mallsecurity.model.CodeModel;
import com.ouc.mallsecurity.model.EmailModel;
import com.ouc.mallsecurity.model.PwdModel;
import com.ouc.mallsecurity.model.SignModel;
import com.ouc.mallsecurity.service.EmailService;
import com.ouc.mallsecurity.service.SignService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService {

    @Value("${code.expiration}")
    private Long expiration;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private EmailService emailService;

//    @Autowired
//    public SignServiceImpl(){
//        this.emailService = new EmailServiceImpl();
//        this.userService = new UserServiceImpl();
//    }

    @Override
    public void sendEmailCode(String email) {
        // 获取发送邮箱验证码的html模板
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig(("templates"),TemplateConfig.ResourceMode.CLASSPATH)); // 在类路径下的 templates 文件夹下搜索
        Template template = engine.getTemplate("email-code.ftl");


        // TODO 此处的逻辑还需要再优化 其实应该是把原来的删除掉 然后发一个新的

        String code = RandomUtil.randomNumbers(6); // 生成6位的随机验证码
        if(!RedisUtils.set(email, code, expiration)){
            throw new ServiceException(500,"后台缓存服务异常");
        }
        System.out.println(code);

        emailService.send(new EmailModel(email,"邮箱验证码", template.render(Dict.create().set("code",code))));
    }

    @Override
    public String signInByPwd(PwdModel pwdModel) {
        // 检验密码和确认密码是否一致
        if( !StrUtil.equals(pwdModel.getPwd(), pwdModel.getConfirmPwd()) ) throw new ServiceException(401,"密码不一致");
        // 判断密码是否和数据库中的一致
        User user = userService.findByUserEmail(pwdModel.getEmail());
        if( !StrUtil.equals(user.getUserPwd(), pwdModel.getPwd()) ) throw new ServiceException(401, "账户名或者密码错误");
        // 生成token
        return TokenUtils.genToken(String.valueOf(user.getId()), user.getUserPwd());
    }

    @Override
    public String signInByCode(CodeModel codeModel) {
        // 通过 email 获取缓存中的code
        String codeCache = (String) RedisUtils.get(codeModel.getEmail());
        if(StrUtil.isBlank(codeCache) || !StrUtil.equals(codeCache, codeModel.getCode())){
            throw new ServiceException(401, "无效验证码");
        }
        else {
            RedisUtils.del(codeModel.getEmail());
        }
        // 获取对应的user
        User user = userService.findByUserEmail(codeModel.getEmail());
        // 生成token
        return TokenUtils.genToken(String.valueOf(user.getId()), user.getUserPwd());
    }

    @Override
    public void signUp(SignModel signModel) {
        // 通过 email 获取缓存中的code
        String codeCache = (String) RedisUtils.get(signModel.getEmail());
        if(StrUtil.isBlank(codeCache) || !StrUtil.equals(codeCache, signModel.getCode())){
            throw new ServiceException(401, "无效验证码");
        }
        else {
            RedisUtils.del(signModel.getEmail());
        }

        // 检查数据库中是否已经存在该用户
        User user = userService.findByUserEmail(signModel.getEmail());
        if(user != null) throw new ServiceException(401,"该用户已存在");
        // 新建一个用户 并赋予最基本的信息
        user = new User();
        user.setUserEmail(signModel.getEmail());
        user.setUserPwd(signModel.getPwd());
        // 向数据库中添加一个新用户
        userService.addUser(user);
    }
}

























