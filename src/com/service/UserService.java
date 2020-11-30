package com.service;

import com.entity.User;

import java.util.List;

/**
 * 作者：wenqi
 * 日期: 2020/11/26 17:15
 * 描述:
 */
public interface UserService {

    int register(User user);

    User login(User user);

    int insert(User user);

    int update(User user);

    int delete(Integer id);

    List<User> queryAll();

    User queryUserById(Integer id);

    User queryUserByEmail(String email);

    public User queryUserByUsername(String username);

}
