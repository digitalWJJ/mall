package com.ouc.malladmin.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class SaveFileUtil {
    @Autowired
    GetTypeUtil getType;
    public String savefile(String productImage){
        if(productImage != null) {
            UUID uuid = UUID.randomUUID();
            String uuiD = uuid.toString();
            String suffix = uuid + getType.gettype(productImage);
            return suffix;
        }
        return "错误";
    }

}
