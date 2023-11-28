package com.ouc.mallproduct;

import com.ouc.mallmbg.mapper.IndentMapper;
import com.ouc.mallmbg.model.Indent;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallproduct.service.ProductService;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallProductApplication.class)
public class ProductApplicationTests extends BaseMapperTest {
    @Resource
    private ProductService productService;

    @Autowired
    private IndentMapper indentMapper;
    @Test
    public void contextLoads() {
    }

//    @Test
//    public void addOneProductTest()
//    {
//        SqlSession sqlSession=getSqlSession();
//        try {
//            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
//            Product product = new Product();
//            product.setId(14);
//            product.setProductName("笔记本电脑");
//            product.setProductDescription("这是台电脑");
//            product.setProductPrice(BigDecimal.valueOf(100));
//            int result = productMapper.insert(product);
//            Assert.isTrue(result==1,"返回数量错误");
//        }
//        finally {
//            sqlSession.commit();
//            sqlSession.close();
//        }
//    }
    @Test
    public void getOneProductTest()
    {
           Product product1=productService.getItem(14);
           Assert.isTrue(product1!=null,"未获取到商品");
           Assert.isTrue(product1.getProductName().equals("笔记本电脑"),"商品名错误");
           Assert.isTrue(product1.getProductDescription().equals("这是台电脑"),"商品名称错误");
           Assert.isTrue(product1.getProductPrice().compareTo(BigDecimal.valueOf(100))==0,"商品价格错误");
    }
    @Test
    public void addOneProductIntoMyCartTest()
    {
        SqlSession sqlSession=getSqlSession();
        try {
//            OrderMapper orderMapper=sqlSession.getMapper(OrderMapper.class);
            Indent order = new Indent();
            order.setColor("黑");
            order.setProductId(14);
            order.setAmount(3);
            order.setConfiguration("cpu 12400f");
            order.setCommitTime(new Date(System.currentTimeMillis()));
            order.setStatus("待下单");
            int result=indentMapper.insert(order);

            Assert.isTrue(result==1,"插入数量正确");
        }
        finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}