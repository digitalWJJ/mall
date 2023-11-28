package com.ouc.malladmin.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class GetTypeUtil {
    public String gettype(String base){
            String type = StringUtils.substringBetween(base, "/", ";");
            return '.' + type;
        }
    }
