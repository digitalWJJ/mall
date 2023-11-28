package com.ouc.mallorder.controller;

import com.ouc.mallorder.BaseMapperTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@WebAppConfiguration
public class IndentControllerTest extends BaseMapperTest {

    @Autowired
    public WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMVC()
    {
        mockMvc= MockMvcBuilders.webAppContextSetup(webContext).build();
    }
    @Test
    public void contextsLoad()
    {
    }
    @Test
    public void getOrdersInMycart() {

    }

    @Test
    public void deleteOrderInMyCart() {

    }

    @Test
    public void changeOrderAmountInMyCart() {
    }

    @Test
    public void confirmBuyOrderInMyCart() {
    }
}