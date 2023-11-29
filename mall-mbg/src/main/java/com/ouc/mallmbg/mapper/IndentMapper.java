package com.ouc.mallmbg.mapper;

import com.ouc.mallmbg.model.Indent;
import com.ouc.mallmbg.model.IndentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IndentMapper {
    long countByExample(IndentExample example);

    int deleteByExample(IndentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Indent row);

    int insertSelective(Indent row);

    List<Indent> selectByExample(IndentExample example);

    Indent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Indent row, @Param("example") IndentExample example);

    int updateByExample(@Param("row") Indent row, @Param("example") IndentExample example);

    int updateByPrimaryKeySelective(Indent row);

    int updateByPrimaryKey(Indent row);
}