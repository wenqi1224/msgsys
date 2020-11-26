package com.test;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.User;

/**
 * 作者：wenqi
 * 日期: 2020/11/26 19:12
 * 描述:
 */
public class Test {
    public static void main(String[] args) {

        UserDaoImpl userDao=new UserDaoImpl();
        User user=new User("1","2","3");
        int a =userDao.insert(user);
        System.out.println(a);
    }
}
