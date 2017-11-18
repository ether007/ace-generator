package com.ace.base.service;

import com.ace.base.model.User;
import com.ace.base.model.UserExample;
import java.util.List;

public interface UserService {
    int save(User data);

    int update(User data);

    User findById(Long id);

    int removeById(Long id);

    List<User> findPageByExample(UserExample example);

    long countByByExample(UserExample example);
}