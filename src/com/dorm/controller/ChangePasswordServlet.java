package com.dorm.controller;

import com.dorm.pojo.User;
import com.dorm.service.UserService;
import com.dorm.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/password.action")
public class ChangePasswordServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        防止中文乱码
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User session_user = (User) session.getAttribute("session_user");
//        1、接收请求
        String action=request.getParameter("action");
        if ("preChange".equals(action)){
            request.setAttribute("mainRight","passwordChange.jsp");
            request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);
        }else if("change".equals(action)){
            int id=session_user.getId();
            String oldPassword=request.getParameter("oldPassword");
            String newPassword=request.getParameter("newPassword");
            System.out.println(id);
            System.out.println(oldPassword);
            System.out.println(newPassword);
            int i = userService.changePassword(oldPassword, newPassword, id);
            System.out.println(i);
            if (i>0){
                request.setAttribute("error","修改成功");
            }else if (i==-1){
                request.setAttribute("error","旧密码错误");
            }else{
                request.setAttribute("error","修改失败");
            }
//            3、返回页面
            request.setAttribute("mainRight","passwordChange.jsp");
            request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
