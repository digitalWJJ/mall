package com.ouc.malladmin.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class SaveFileUtils {
    public String savefile(MultipartFile productImage){
        if(productImage != null) {
            UUID uuid = UUID.randomUUID();
            String uuiD = uuid.toString();
            String filename = productImage.getOriginalFilename();
            String suffix = "localhost://admin/resources/images/" + uuiD + filename.substring(filename.lastIndexOf("."));
            File newfile = new File(suffix);
            try {
                productImage.transferTo(newfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return suffix;
        }
    }
}
