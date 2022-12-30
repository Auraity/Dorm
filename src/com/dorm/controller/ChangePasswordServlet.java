package com.dorm.controller;

import com.dorm.pojo.User;
import com.dorm.service.UserService;
import com.dorm.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/password.action")
public class ChangePasswordServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        防止中文乱码
        request.setCharacterEncoding("utf-8");
//        1、接收请求
        String action=request.getParameter("action");
        if ("preChange".equals(action)){
//            User user=new User();
//            2、调用相应服务
//            String id=request.getParameter("id");
//            String oldPassword=request.getParameter("oldPassword");
//            String newPassword=request.getParameter("newPassword");
//            userService.changePassword(oldPassword,newPassword,Integer.parseInt(id));
//            3、返回页面
            request.setAttribute("mainRight","passwordChange.jsp");
            request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
