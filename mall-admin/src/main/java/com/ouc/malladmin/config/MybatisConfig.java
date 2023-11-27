package com.ouc.malladmin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.ouc.mallmbg.mapper")
public class MybatisConfig {
}
