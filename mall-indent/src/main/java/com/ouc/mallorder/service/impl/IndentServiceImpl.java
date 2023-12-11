package com.ouc.mallorder.service.impl;

import com.ouc.mallcommon.utils.TokenUtils;
import com.ouc.mallmbg.mapper.IndentMapper;
import com.ouc.mallmbg.mapper.ProductMapper;
import com.ouc.mallmbg.model.Indent;
import com.ouc.mallmbg.model.IndentExample;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallorder.service.IndentService;
import dto.ProductIdsAndOtherInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class IndentServiceImpl implements IndentService {
    @Resource
    private IndentMapper indentMapper;

    @Resource
    private ProductMapper productMapper;

    @Override
    public Indent getItem(int id) {
        return indentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteItem(int id) {
        return indentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteList(List<Integer> ids) {
        IndentExample indentExample=new IndentExample();
        indentExample.createCriteria().andIdIn(ids);
        return indentMapper.deleteByExample(indentExample);
    }

    @Override
    public int updateAfterBuy(ProductIdsAndOtherInfo productIdsAndOtherInfo) {
        IndentExample indentExample=new IndentExample();
        indentExample.createCriteria().andIdIn(productIdsAndOtherInfo.getIndentIds());
        Indent indent=new Indent();
        indent.setIndentStatus("已付款");
        indent.setAddress(productIdsAndOtherInfo.getAddress());
        indent.setPhoneNumber(productIdsAndOtherInfo.getPhoneNumber());
        indent.setReceiverName(productIdsAndOtherInfo.getReceiverName());
        return indentMapper.updateByExampleSelective(indent,indentExample);
    }

    @Override
    public int updateAmount(int id, int amount) {
        Indent indent= indentMapper.selectByPrimaryKey(id);
        if(indent==null)return 0;
        indent.setAmount(amount);
        return indentMapper.updateByPrimaryKey(indent);
    }

    @Override
    public List<Indent> getList(int id) {
        IndentExample indentExample=new IndentExample();
        IndentExample.Criteria criteria=indentExample.createCriteria();
        criteria.andUserIdEqualTo(id);
        List<Indent> indentList= indentMapper.selectByExample(indentExample);
        criteria.andIndentStatusEqualTo("待下单");
        return indentList;
    }

    @Override
    public List<Indent> getAllList(int id) {
        IndentExample indentExample=new IndentExample();
        indentExample.createCriteria().andUserIdEqualTo(id);
        return indentMapper.selectByExample(indentExample);
    }

    @Override
    public List<Indent> getListByIds(List<Integer> ids) {
        IndentExample indentExample=new IndentExample();
        indentExample.createCriteria().andIdIn(ids);
        return indentMapper.selectByExample(indentExample);
    }



    @Override
    public int create(int productId, String color, String configuration, int amount) {
        Indent indent=new Indent();
        indent.setColor(color);
        indent.setProductId(productId);
        Product product=productMapper.selectByPrimaryKey(productId);
        indent.setTotalPrice(product.getProductPrice().multiply(BigDecimal.valueOf(amount)));
        indent.setAmount(amount);
        indent.setConfiguration(configuration);
        indent.setUserId(TokenUtils.getCurrentUser().getId());
        indent.setCommitTime(new Date(System.currentTimeMillis()));
        indent.setIndentStatus("待下单");
        return indentMapper.insert(indent);

    }
}
