package com.ouc.malladmin.utils;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ToMultipartFileUtils {
    public MultipartFile tomutipartfile(String path){
        File file = new File("/admin/product/images/" + path);
        try{
            MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), null, new FileInputStream(file));
            return multipartFile;
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}