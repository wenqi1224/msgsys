package com.test;

import com.entity.User;
import com.google.gson.Gson;
import org.junit.Test;


/**
 * 作者：Cd3Au
 * 日期: 2020/11/30 9:52
 * 描述:
 */
public class JSONTest {

    public static void main(String[] args) {
        User person=new User("小刚","F");
        Gson gson=new Gson();
        //person->JSON对象
        String personJSONStr=gson.toJson(person);
        System.out.println(personJSONStr);

        //JSON对象->字符串
        User person1=gson.fromJson(personJSONStr,User.class);
        System.out.println("p对象="+person1);
    }

}

