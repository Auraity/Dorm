package com.dorm.controller;

import com.dorm.pojo.DormBuild;
import com.dorm.pojo.Record;
import com.dorm.service.RecordService;
import com.dorm.service.impl.RecordServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/record.action")
public class RecordServlet extends HttpServlet {
    RecordService recordService=new RecordServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        防止中文乱码
        request.setCharacterEncoding("utf-8");
//        1、接收请求
        String action=request.getParameter("action");
        if ("list".equals(action)){
//            查询所有用户
//            2、调用相应服务//
            List<Record> records=recordService.getAllRecords();
//            request.setAttribute("records",records);
//            3、返回页面
            request.setAttribute("mainRight","recordList.jsp");
            request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
