package com.servlet;

import com.entity.Message;
import com.entity.User;
import com.service.MessageService;
import com.service.impl.MessageServiceImpl;
import com.utils.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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


    public void download(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        //金句：防止中文乱码
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //设置文件名
        String fileName = "惊喜.jpg";
        //将响应的类型设置为图片
        response.setContentType("image/jpeg");
        //金句：下载文件中文乱码解决
        // 把中文名进行UTF-8 编码操作。
        String str = "attachment; fileName=" + URLEncoder.encode(fileName, "UTF-8");
        // 然后把编码后的字符串设置到响应头中
        //金句：文件下载
        response.setHeader("Content-Disposition", str);
        //如果要读取工程外部 硬盘里的资源 建议使用FileInputStream 以及 BufferedInputStream
        InputStream fileIn = new FileInputStream(new File(path));
        InputStream in = new BufferedInputStream(fileIn);
        //使用ServletContext获取输入流 只能获取到工程内部的资源
//        InputStream in = getServletContext().getResourceAsStream();
        OutputStream out = response.getOutputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = in.read(buff)) > -1) {
            out.write(buff, 0, len);
            out.flush();
        }
        out.close();
        in.close();
    }



}
