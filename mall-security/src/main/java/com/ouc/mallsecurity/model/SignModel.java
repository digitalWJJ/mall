package com.ouc.mallsecurity.model;

import lombok.Data;

@Data
public class SignModel {
    private String email;
    private String pwd;
    private String confirmPwd;
    private String code;
}
