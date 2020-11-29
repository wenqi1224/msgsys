package com.service;

import com.entity.Message;

import java.util.List;

/**
 * 作者：wenqi
 * 日期: 2020/11/26 21:51
 * 描述:
 */
public interface MessageService {
    //根据收信人的ID查询邮件
    List<Message> queryMessageByToUid(Integer id);

    //根据ID查询邮件的详情
    Message queryMessageById(Integer id);


    int insert(Message message);

    int update(Message message);

    int delete(Integer id);

}
