package com.service.impl;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.User;
import com.service.UserService;

import java.util.List;

/**
 * 作者：wenqi
 * 日期: 2020/11/26 17:24
 * 描述:
 */
public class UserServiceImpl implements UserService {

    UserDao userDao=new UserDaoImpl();

    @Override
    public int register(User user) {
        return userDao.insert(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByNameAndPassword(user);
    }

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }

    @Override
    public int delete(Integer id) {
        return userDao.delete(id);
    }

    @Override
    public List<User> queryAll() {
        return userDao.queryAll();
    }

    @Override
    public User queryUserById(Integer id) {
        return userDao.queryUserById(id);
    }

    @Override
    public User queryUserByEmail(String email) {
        return userDao.queryUserByEmail(email);
    }
}
