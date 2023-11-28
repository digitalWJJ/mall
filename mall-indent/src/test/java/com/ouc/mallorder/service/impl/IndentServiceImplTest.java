package com.ouc.mallorder.service.impl;

import com.ouc.mallorder.BaseMapperTest;
import com.ouc.mallorder.MallOrderApplication;
import com.ouc.mallorder.service.IndentService;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallOrderApplication.class)
public class IndentServiceImplTest extends BaseMapperTest {

    @Resource
    public IndentService indentService;
    @Test
    public void contextsLoad()
    {
    }

    @Test
    public void deleteProduct() {}

    @Test
    public void changeProductAmount() {}

    @Test
    public void confirmBuyOrder() {}
}