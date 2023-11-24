package com.ouc.mallsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel {
    /*
    * 目标邮箱
    * */
    private String tos;

    /*
    * 主题
    * */
    private String subject;

    /*
    * 内容
    * */
    private String content;
}
