package com.ace.base.service.impl;

import com.ace.base.dao.UserMapper;
import com.ace.base.model.User;
import com.ace.base.model.UserExample;
import com.ace.base.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    public int save(User orderModel) {
         return mapper.insertSelective(orderModel);
    }

    public int update(User orderModel) {
         return mapper.updateByPrimaryKeySelective(orderModel);
    }

    public User findById(Long id) {
         return mapper.selectByPrimaryKey(id);
    }

    public int removeById(Long id) {
         return mapper.deleteByPrimaryKey(id);
    }

    public List<User> findPageByExample(UserExample orderModelExample) {
         return mapper.selectByExample(orderModelExample);
    }

    public long countByByExample(UserExample orderModelExample) {
         return mapper.countByExample(orderModelExample);
    }
}