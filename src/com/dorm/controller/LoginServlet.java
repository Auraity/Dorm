package com.dorm.controller;

import com.dorm.pojo.User;
import com.dorm.service.UserService;
import com.dorm.service.impl.UserServiceImpl;
import com.dorm.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();//多态，面向接口编程的体现

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        防止中文乱码
        request.setCharacterEncoding("utf-8");

//      接收用户输入的登录信息
        String stuCode=request.getParameter("stuCode");
        String password=request.getParameter("password");
        String remember=request.getParameter("remember");

//        根据用户输入的账户和密码查询数据库
        User user=userService.findUserByStuCodeAndPassword(stuCode,password);
        if (user==null){
//            用户输入账户或密码错误
            request.setAttribute("error","输入的信息有误，请重试");
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else {
//            账户密码正确
//            1、将用户信息存到session域中
            request.getSession().setAttribute("session_user",user);
//            2、用户勾选了“记住我”将用户信息存入cookie中
            if (remember!=null&&remember.equals("remember-me")){
                CookieUtils.addCookie("cookie_stuCode_pwd",7*24*3600,request,response,stuCode,password);
            }
            request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
