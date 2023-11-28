package com.ouc.malladmin.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
@Component
public class SaveFileUtils {
    @Autowired
    GetType getType;
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
