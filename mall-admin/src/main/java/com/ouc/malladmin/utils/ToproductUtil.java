package com.ouc.malladmin.utils;

import com.ouc.mallcommon.dto.SplitProduct;
import com.ouc.mallmbg.model.Product;

public class ToproductUtil {
    public static Product toproduct(SplitProduct splitProduct){
        Product product = new Product();
        product.setProductName(splitProduct.getProductName());
        product.setProductDescription(splitProduct.getProductDescription());
        product.setProductPrice(splitProduct.getProductPrice());
        String configurations = splitProduct.getConfiguration().get(0);
        for (int i = 0; i < splitProduct.getConfiguration().size(); i++){
            configurations = configurations + '|' + splitProduct.getConfiguration().get(i);
        }
        product.setConfiguration(configurations);
        String colors = splitProduct.getColor().get(0);
        for (int i = 0; i < splitProduct.getColor().size(); i++){
            colors = colors +  '|' + splitProduct.getColor().get(i);
        }
        product.setColor(colors);
        if(splitProduct.getProductImage() != null){
            if(!splitProduct.getProductImage().isEmpty()) product.setProductImage1(splitProduct.getProductImage().get(0));
            if(1 < splitProduct.getProductImage().size()) product.setProductImage2(splitProduct.getProductImage().get(1));
            if(2 < splitProduct.getProductImage().size()) product.setProductImage3(splitProduct.getProductImage().get(2));
            if(3 < splitProduct.getProductImage().size()) product.setProductImage4(splitProduct.getProductImage().get(3));
            if(4 < splitProduct.getProductImage().size()) product.setProductImage5(splitProduct.getProductImage().get(4));
        }
        product.setCategory(splitProduct.getCategory());
        return product;
    }
}
