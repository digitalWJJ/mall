package com.ouc.mallmbg.mapper;

import com.ouc.mallmbg.model.UserAddress;
import com.ouc.mallmbg.model.UserAddressExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserAddressMapper {
    long countByExample(UserAddressExample example);

    int deleteByExample(UserAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserAddress row);

    int insertSelective(UserAddress row);

    List<UserAddress> selectByExample(UserAddressExample example);

    UserAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") UserAddress row, @Param("example") UserAddressExample example);

    int updateByExample(@Param("row") UserAddress row, @Param("example") UserAddressExample example);

    int updateByPrimaryKeySelective(UserAddress row);

    int updateByPrimaryKey(UserAddress row);
}