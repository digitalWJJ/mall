package com.ouc.malladmin.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GetType {
    public String gettype(String base){
            String type = StringUtils.substringBetween(base, "/", ";");
            return type;
        }
    }
