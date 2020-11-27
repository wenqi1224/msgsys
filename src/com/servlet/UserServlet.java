package com.servlet;

import com.entity.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         super.doGet(request,response);
    }

    //注册
    public void register(HttpServletRequest request, HttpServletResponse response){

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user=new User(username,password,email);
        System.out.println(user);
        int result =userService.register(user);
        System.out.println(result);

    }

    //登陆
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

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
