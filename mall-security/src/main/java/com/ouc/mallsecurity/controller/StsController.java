package com.ouc.mallsecurity.controller;

import com.ouc.mallcommon.AuthAccess;
import com.ouc.mallcommon.Result;
import com.ouc.mallsecurity.service.StsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class StsController {

    @Autowired
    private StsService stsService;

    /**
     * 登录之后的管理员获取访问 OSS 的临时 token
     * */
    @GetMapping("/getOssToken")
    @AuthAccess
    public Result getOssToken(){
        return Result.success( stsService.getOssToken() );
    }
}
