package com.servlet;

import com.entity.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * 作者：wenqi
 * 日期: 2020/11/26 17:35
 * 描述:
 */
@WebServlet("/user.do")
public class UserServlet extends BaseServlet {

    UserService userService=new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String action =request.getParameter("action");
//        if ("login".equals(action)){
//            login(request,response);
//        }else {
//            register(request,response);
//        }
        super.doPost(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         super.doGet(request,response);

    }

    //注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //金句：防止中文乱码
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//
//        User user=new User(username,password,email);
//        System.out.println(user);
//        int result =userService.register(user);
//        System.out.println(result);

        //1 先判断上传的数据是否多段数据 （只有是多段的数据，才是文件上传的）
        if (ServletFileUpload.isMultipartContent(request)) {
            // 创建FileItemFactory 工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据的工具类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            User user = new User();
            try {
                //解析上传的数据 得到每一个表单项FileItem
                List<FileItem> list = servletFileUpload.parseRequest(request);
                //循环判断，每一个表单项 是普通类型 还是上传的文件
                for (FileItem fileItem :
                        list) {
                    if (fileItem.isFormField()) {
                        //普通表单项
                        System.out.println("普通表单项 name = " + fileItem.getFieldName());
                        //参数UTF-8解决乱码
                        System.out.println("value = " + fileItem.getString("UTF-8"));
                        String str = fileItem.getFieldName();
                        switch (str) {
                            case "username":
                                user.setUsername(fileItem.getString("UTF-8"));

                                break;
                            case "password":
                                user.setPassword(fileItem.getString("UTF-8"));
                                break;
                            case "email":
                                user.setEmail(fileItem.getString("UTF-8"));
                                break;
                        }
                    } else {
                        //上传的文件
                        System.out.println("表单项的name = " + fileItem.getFieldName());
                        System.out.println("上传的文件名:" + fileItem.getName());

                        StringBuilder sb = new StringBuilder("D:\\upload\\");
                        sb.append(new Date().getTime());
                        sb.append(fileItem.getName());
                        String path = sb.toString();
                        user.setImgpath(path);
                        fileItem.write(new File(path));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(user);
            userService.register(user);
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    }



    //登陆
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //金句：防止中文乱码
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //获取验证码
        String token =(String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        String code =request.getParameter("code");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user =userService.login(new User(username,password));

        if (user!=null&&token.equalsIgnoreCase(code)){

            HttpSession session=request.getSession();
            session.setAttribute("user",user);

            request.getRequestDispatcher("/message.do?action=queryList").forward(request,response);
        }else {
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }

    }

}
