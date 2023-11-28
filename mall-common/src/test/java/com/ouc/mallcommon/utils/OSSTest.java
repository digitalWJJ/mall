package com.ouc.mallcommon.utils;

import com.aliyuncs.exceptions.ClientException;
import com.ouc.mallcommon.MallCommonApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallCommonApplication.class)
public class OSSTest {

    @org.junit.Test
    public void uploadImg() throws IOException, ClientException {
        String filePath="C:\\Users\\Lenovo\\Desktop\\room_img\\10.jpg";
        byte[] content = Files.readAllBytes(Paths.get(filePath));
        OSS.uploadImg("10.jpg",content,0);
    }
}