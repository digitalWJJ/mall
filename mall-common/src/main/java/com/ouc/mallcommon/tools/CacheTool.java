package com.ouc.mallcommon.tools;

import com.ouc.mallcommon.utils.RedisUtils;
import com.ouc.mallmbg.mapper.IndentMapper;
import com.ouc.mallmbg.mapper.ProductMapper;
import com.ouc.mallmbg.model.Indent;
import com.ouc.mallmbg.model.PageParam;
import com.ouc.mallmbg.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CacheTool {

    private static long expiration;

    private static ProductMapper productMapper;
    private static IndentMapper indentMapper;

    @Autowired
    private ProductMapper productMapperInit;

    @Autowired
    private IndentMapper indentMapperInit;

    private static final String productPrefixed = "product-";
    private static final String indentPrefixed = "indent-";

    @PostConstruct
    public void initCacheTool(){
        CacheTool.productMapper = productMapperInit;
        CacheTool.indentMapper = indentMapperInit;
    }

    /**
     * 获取指定的商品
     * @param productId 商品id
     * @return 有指定的id 就返回查询的结果, 没有的话就返回为空
     * */
    public static Product getProduct(int productId){
        Product product;
        String key = productPrefixed + productId;
        product = (Product) RedisUtils.get(key);
        // 如果 product 为空就从数据库中获取指定的 product
        if(product != null) return product;

        product = productMapper.selectByPrimaryKey(productId); // 如果没有就会返回null
        if(product != null) RedisUtils.set(key, product, expiration);
        return product;
    }

    /**
     * 分页查询指定页面的商品
     * @param pageParam 需要查询的页面的相关内容
     * @return 有指定的id 就返回查询的结果, 没有的话就返回为空
     * */
    public static List<Product> getProducts(PageParam pageParam){
        return productMapper.selectByPage(pageParam);
    }

    /**
     * 添加指定的商品
     * @param product 商品
     * @return 是否添加成功
     * */
    public static boolean addProduct(Product product){
        // 先添加到数据库中
        boolean insertResult = productMapper.insert(product) != 0;
        boolean putResult = false;
        // 添加到缓存中
        if(insertResult) putResult = RedisUtils.set(productPrefixed + product.getId(), product, expiration);

        return insertResult && putResult;
    }

    /**
     * 更新指定的商品
     * @param product 商品
     * @return true 表示成功 false 表示更新失败或者旧的缓存仍然存在
     * */
    public static boolean updateProduct(Product product){
        String key  = productPrefixed + product.getId();
        // 先删除缓存
        RedisUtils.del(key);
        // 在数据库中进行更新
        boolean isUpdate = productMapper.updateByPrimaryKeySelective(product) !=0;
        // 再删除一遍缓存
        RedisUtils.del(key);
        boolean isDel = RedisUtils.get(key) == null;
        return isUpdate && isDel;
    }

    /**
     * 删除指定的商品
     * @param productId 商品id
     * @return ture 表示删除成功 false 表示删除数据库失败或者删除旧的缓存失败
     * */
    public static boolean deleteProduct(int productId){
        String key = productPrefixed + productId;
        // 先删除缓存
        RedisUtils.del(key);
        // 在数据库中进行更新
        boolean isDBDel = productMapper.deleteByPrimaryKey(productId) != 0;
        // 再删除一遍缓存
        RedisUtils.del(key);
        boolean isCacheDel = RedisUtils.get(key) == null;
        return isDBDel && isCacheDel;
    }

    /**
     * 获取指定的订单
     * @param indentId 订单id
     * @return 有指定的id 就返回查询的结果, 没有的话就返回为空
     * */
    public static Indent getIndent(int indentId){
        Indent indent;
        String key = indentPrefixed + indentId;
        indent = (Indent) RedisUtils.get(key);
        // 如果 indent 为空就从数据库中获取指定的 indent
        if(indent != null) return indent;

        indent = indentMapper.selectByPrimaryKey(indentId); // 如果没有就会返回null
        if(indent != null) RedisUtils.set(key, indent, expiration);
        return indent;
    }

    /**
     * 分页查询指定页面的商品
     * @param pageParam 需要查询的页面的相关内容
     * @return 有指定的id 就返回查询的结果, 没有的话就返回为空
     * */
    public static List<Indent> getIndents(PageParam pageParam){
        return indentMapper.selectByPage(pageParam);
    }

    /**
     * 添加指定的订单
     * @param indent 订单
     * @return 是否添加成功
     * */
    public static boolean addIndent(Indent indent){
        // 先添加到数据库中
        boolean insertResult = indentMapper.insert(indent) != 0;
        boolean putResult = false;
        // 添加到缓存中
        if(insertResult) putResult = RedisUtils.set(indentPrefixed + indent.getId(), indent, expiration);
        return insertResult && putResult;
    }

    /**
     * 更新指定的订单
     * @param indent 订单
     * @return true 表示成功 false 表示更新失败或者旧的缓存仍然存在
     * */
    public static boolean updateIndent(Indent indent){
        String key  = indentPrefixed + indent.getId();
        // 先删除缓存
        RedisUtils.del(key);
        // 在数据库中进行更新
        boolean isUpdate = indentMapper.updateByPrimaryKeySelective(indent) !=0;
        // 再删除一遍缓存
        RedisUtils.del(key);
        boolean isDel = RedisUtils.get(key) == null;
        return isUpdate && isDel;
    }

    /**
     * 删除指定的订单
     * @param indentId 订单id
     * @return ture 表示删除成功 false 表示删除数据库失败或者删除旧的缓存失败
     * */
    public static boolean deleteIndent(int indentId){
        String key = indentPrefixed + indentId;
        // 先删除缓存
        RedisUtils.del(key);
        // 在数据库中进行更新
        boolean isDBDel = indentMapper.deleteByPrimaryKey(indentId) != 0;
        // 再删除一遍缓存
        RedisUtils.del(key);
        boolean isCacheDel = RedisUtils.get(key) == null;
        return isDBDel && isCacheDel;
    }

    @Value("${cache.expiration}")
    public void setExpiration(long expiration){ CacheTool.expiration = expiration; }

}
