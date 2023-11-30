package com.ouc.malladmin.utils;

import com.aliyuncs.exceptions.ClientException;
import com.ouc.mallcommon.Result;
import com.ouc.mallcommon.utils.OSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Base64;
import com.aliyuncs.exceptions.ClientException;

import java.util.UUID;
@Component
public class SaveFileUtil {
    @Autowired
    GetTypeUtil getType;
    public String savefile(String productImage){
        if(productImage != null) {
            UUID uuid = UUID.randomUUID();
            String uuiD = uuid.toString();
//            byte[] bytes = Base64.getDecoder().decode(productImage);
            return uuiD + getType.gettype(productImage);
        }
        return "错误";
    }

}
