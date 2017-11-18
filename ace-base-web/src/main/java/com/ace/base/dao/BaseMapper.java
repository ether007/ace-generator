package com.ace.base.dao;

import com.ace.base.model.Base;
import com.ace.base.model.BaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseMapper {
    long countByExample(BaseExample example);

    int deleteByExample(BaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Base record);

    int insertSelective(Base record);

    List<Base> selectByExample(BaseExample example);

    Base selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Base record, @Param("example") BaseExample example);

    int updateByExample(@Param("record") Base record, @Param("example") BaseExample example);

    int updateByPrimaryKeySelective(Base record);

    int updateByPrimaryKey(Base record);
}