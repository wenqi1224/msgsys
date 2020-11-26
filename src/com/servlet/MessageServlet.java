package com.servlet;

import com.entity.Message;
import com.entity.User;
import com.service.MessageService;
import com.service.impl.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 作者：wenqi
 * 日期: 2020/11/26 21:59
 * 描述:
 */
@WebServlet("/message.do")
public class MessageServlet extends BaseServlet {

    MessageService messageService =null;

    public MessageServlet() {
        messageService =new MessageServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      super.doGet(request,response);
    }

    public void queryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user= (User) request.getSession().getAttribute("user");
        List<Message> messages=messageService.queryMessageByToUid(user.getId());
        request.setAttribute("messages",messages);
        System.out.println(messages);
        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }



}
