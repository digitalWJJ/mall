package com.ouc.mallsecurity.controller;

import com.ouc.mallcommon.Result;
import com.ouc.mallcommon.annotation.AuthAccess;
import com.ouc.mallsecurity.utils.RsaUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EnDeController {

    @RequestMapping("/getPublicKey")
    @AuthAccess
    public Result getPublicKey(){
        return Result.success(RsaUtil.getPublicKey());
    }
}
