package com.dao.impl;

import com.dao.BaseDao;
import com.dao.MessageDao;
import com.entity.Message;

import java.util.List;

/**
 * 作者：wenqi
 * 日期: 2020/11/26 20:54
 * 描述:
 */
public class MessageDaoImpl extends BaseDao implements MessageDao {
    @Override
    public int insert(Message message) {
        String sql ="INSERT INTO T_msg (fromuid,touid,mtitle,mcontent,readFlag,createtime) VALUES (?,?,?,?,?,?) ";
        return update(sql,message.getFromUid(),message.getToUid(),message.getmTitle(),message.getmContent(),message.getReadFlag(),message.getCreateTime());
    }

    @Override
    public int update(Message message) {
        String sql ="UPDATE `T_msg` SET fromuid=?,touid=?,mtitle=?,mcontent=?,readFlag=?,createtime=? WHERE id = ? ";
        return update(sql,message.getFromUid(),message.getToUid(),message.getmTitle(),message.getmContent(),message.getReadFlag(),message.getCreateTime(),message.getId());
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM T_msg WHERE id =?";
        return update(sql,id);
    }


    @Override
    public List<Message> queryAll() {
        String sql = "SELECT * FROM T_msg ";
        return queryForList(Message.class,sql);
    }

    @Override
    public Message queryMessageById(Integer id) {
        String sql = "SELECT * FROM T_msg WHERE id =? ";
        return queryForOne(Message.class,sql,id);
    }

    @Override
    public List<Message> queryMessageByToUid(Integer id) {
        String sql = "SELECT * FROM T_msg WHERE touid =? ";
        return queryForList(Message.class,sql,id);
    }
}
