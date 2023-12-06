package com.ouc.malladmin.utils;

import com.ouc.mallcommon.dto.SplitProduct;
import com.ouc.mallcommon.utils.TypeCasting;
import com.ouc.mallmbg.model.Product;

import java.util.ArrayList;
import java.util.List;

public class TosplitproductUtil {
    public static List<SplitProduct> getSplitproducts(List<Product> products){
        List<SplitProduct> splitProducts = new ArrayList<>();
        try {
            for(int i = 0; i < products.size(); i++){
                splitProducts.add(TypeCasting.productToSplitProduct(products.get(i)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return splitProducts;
    }
    public static SplitProduct getsplitproduct(Product product){
        SplitProduct splitProduct = new SplitProduct();
        try {
            splitProduct = TypeCasting.productToSplitProduct(product);
        }catch (Exception e){
            e.printStackTrace();
        }
        return splitProduct;
    }
}
