package com.ouc.mallcommon.utils;

import com.ouc.mallcommon.dto.SplitProduct;
import com.ouc.mallmbg.model.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TypeCastingTest {

    @Test
    public void productToSplitProduct() {
        String color="红|白|绿|青";
        List<String> colors= List.of(color.split("\\|",0));
        for(String item:colors)
        {
            System.out.println(item);
        }
    }

    @Test
    public void splitProductToProduct() {
        List<String> configuration=new ArrayList<>();
        configuration.add("配置一");
        configuration.add("配置二");
        configuration.add("配置三");
        List<String> color=new ArrayList<>();
        color.add("颜色1");
        color.add("颜色2");
        color.add("颜色3");
        List<String> productImage=new ArrayList<>();
        productImage.add("图片1");
        productImage.add("图片2");
        productImage.add("图片3");
        SplitProduct splitProduct=new SplitProduct(1,"电脑","这是一台电脑", BigDecimal.valueOf(1000.02),configuration,
                color,productImage,"电脑");
        Product product=TypeCasting.splitProductToProduct(splitProduct);
        System.out.println(product.toString());
    }
}