package com.dao;

import com.entity.Message;
import com.entity.User;

import java.util.List;

/**
 * 作者：wenqi
 * 日期: 2020/11/26 15:56
 * 描述:
 */
public interface MessageDao {

    int insert(Message message);

    int update(Message message);

    int delete(Integer id);

    List<Message> queryAll();

    Message queryMessageById(Integer id);

    //根据收信人的ID查询邮件
    List<Message> queryMessageByToUid(Integer id);
}
