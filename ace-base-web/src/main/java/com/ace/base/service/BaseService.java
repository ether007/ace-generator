package com.ace.base.service;

import com.ace.base.model.Base;
import com.ace.base.model.BaseExample;
import java.util.List;

public interface BaseService {
    int save(Base data);

    int update(Base data);

    Base findById(Long id);

    int removeById(Long id);

    List<Base> findPageByExample(BaseExample example);

    long countByByExample(BaseExample example);
}