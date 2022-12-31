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
        String action=request.getParameter("action");
        if ("list".equals(action)){
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String dormBuildId = request.getParameter("dormBuildId");
            String searchType = request.getParameter("searchType");
            String keyword = request.getParameter("keyword");

            List<Record> records=recordService.findRecordsBySearchType(startDate,endDate,dormBuildId,searchType,keyword);
           request.setAttribute("records",records);

            request.setAttribute("mainRight","recordList.jsp");
            request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);
        }else if ("save".equals(action)){
            String id = request.getParameter("id");
            String stuCode = request.getParameter("stuCode");
            String date = request.getParameter("date");
            String remark = request.getParameter("remark");
            System.out.println(id);
            if (!"".equals(id) && id != null){
                int i = recordService.updateRecord(id,stuCode,date,remark);
            }else{
                int i = recordService.addRecord(stuCode,date,remark);
            }
            // System.out.println(i);
            showRecordList(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        防止中文乱码
        request.setCharacterEncoding("utf-8");
//        1、接收请求
        String action=request.getParameter("action");

        if ("list".equals(action)){
            showRecordList(request, response);
        }else if ("preUpdate".equals(action)){
            String id = request.getParameter("id");
            Record record = recordService.findRecordById(id);
            request.setAttribute("record",record);
            request.setAttribute("mainRight","recordAddOrUpdate.jsp");
            request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);
        }else if("preAdd".equals(action)){
            request.setAttribute("mainRight","recordAddOrUpdate.jsp");
            request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);
        }
    }

    private void showRecordList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Record> records=recordService.getAllRecords();
        request.setAttribute("records",records);
        request.setAttribute("mainRight","recordList.jsp");
        request.getRequestDispatcher("/jsp/main.jsp").forward(request, response);
    }
}
