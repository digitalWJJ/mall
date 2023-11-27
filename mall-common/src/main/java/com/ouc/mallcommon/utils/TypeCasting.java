package com.ouc.mallcommon.utils;

import com.aliyuncs.exceptions.ClientException;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallcommon.dto.SplitProduct;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TypeCasting {
    public static Date stringToData(String s) {
        Date date=null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
            date = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static SplitProduct productToSplitProduct(Product product) throws ClientException {
        List<String> colors= List.of(product.getColor().toString().split("|"));
        List<String> configurations=List.of(product.getConfiguration().split("|"));
        List<String> productImages=new ArrayList<>();
        if(product.getProductImage1()!=null)productImages.add(OSS.getProductImgUrl(product.getProductImage1(),0));
        if(product.getProductImage2()!=null)productImages.add(OSS.getProductImgUrl(product.getProductImage2(),0));
        if(product.getProductImage3()!=null)productImages.add(OSS.getProductImgUrl(product.getProductImage3(),0));
        if(product.getProductImage4()!=null)productImages.add(OSS.getProductImgUrl(product.getProductImage4(),0));
        if(product.getProductImage5()!=null)productImages.add(OSS.getProductImgUrl(product.getProductImage5(),0));
        return new SplitProduct(product.getId(),product.getProductName(),product.getProductDescription(),product.getProductPrice(),configurations,colors,productImages,product.getCategory());
    }
}