package com.servlet;

import com.entity.Message;
import com.entity.User;
import com.service.MessageService;
import com.service.UserService;
import com.service.impl.MessageServiceImpl;
import com.service.impl.UserServiceImpl;
import com.utils.ConstData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 作者：wenqi
 * 日期: 2020/11/26 21:59
 * 描述:
 */
@WebServlet("/message.do")
public class MessageServlet extends BaseServlet {

    MessageService messageService =null;
    UserService userService=null;

    public MessageServlet() {
        messageService =new MessageServiceImpl();
        userService=new UserServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request,response);
    }

    public void send(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("mtitle");
        String email = request.getParameter("email");
        String mcontent = request.getParameter("mcontent");
        //接收方user
        //TODO:email唯一性确认
        User toUser = userService.queryUserByEmail(email);
        String msg = null;
        if (toUser == null) {
            //email不存在
            msg = "email不存在！";
//            response.sendRedirect(getServletContext().getContextPath() + "/send.jsp");
        } else {
            Message message = new Message();
            message.setmTitle(title);
            //发送方user
            User fromUser = (User) request.getSession().getAttribute("user");
            message.setFromUid(fromUser.getId());
            message.setToUid(toUser.getId());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstData.DATE_FORMAT_STR);
            message.setCreateTime(simpleDateFormat.format(new Date()));
            message.setmContent(mcontent);
            message.setReadFlag(ConstData.MESSAGE_ISREAD_UNREAD);
            int result = messageService.insert(message);
            if (result > 0) {
                msg = "发送成功！";
            } else {
                msg = "发送失败!";
            }
        }
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
//
    }

    public void queryList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        User user= (User) request.getSession().getAttribute("user");
//        String path = user.getImgpath();
//        System.out.println(path);
//        FileUtils.singleDownload(request,response,path,user.getUsername());
        List<Message> messages=messageService.queryMessageByToUid(user.getId());
        request.setAttribute("messages",messages);
        System.out.println(messages);
        request.getRequestDispatcher("/list.jsp").forward(request,response);


    }

    //删除消息
    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Integer messageId = Integer.valueOf(id);
        int result = messageService.delete(messageId);
        //当删除完毕后 直接刷新当前消息列表
        queryList(request, response);
    }

    //查看消息详情
    public void queryDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Integer messageId = Integer.valueOf(id);
        Message message = messageService.queryMessageById(messageId);
        //查看消息
        message.setReadFlag(ConstData.MESSAGE_ISREAD_READ);
        messageService.update(message);
        Integer fromId = message.getFromUid();
        User user = userService.queryUserById(fromId);
        request.setAttribute("message", message);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/details.jsp").forward(request, response);
    }





}
