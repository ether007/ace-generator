package com.ace.base.service.impl;

import com.ace.base.dao.BaseMapper;
import com.ace.base.model.Base;
import com.ace.base.model.BaseExample;
import com.ace.base.service.BaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService {
    @Autowired
    private BaseMapper mapper;

    public int save(Base orderModel) {
         return mapper.insertSelective(orderModel);
    }

    public int update(Base orderModel) {
         return mapper.updateByPrimaryKeySelective(orderModel);
    }

    public Base findById(Long id) {
         return mapper.selectByPrimaryKey(id);
    }

    public int removeById(Long id) {
         return mapper.deleteByPrimaryKey(id);
    }

    public List<Base> findPageByExample(BaseExample orderModelExample) {
         return mapper.selectByExample(orderModelExample);
    }

    public long countByByExample(BaseExample orderModelExample) {
         return mapper.countByExample(orderModelExample);
    }
}