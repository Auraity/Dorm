package com.dorm.controller;

import com.dorm.pojo.DormBuild;
import com.dorm.pojo.User;
import com.dorm.service.DormBuildService;
import com.dorm.service.UserService;
import com.dorm.service.impl.DormBuildServiceImpl;
import com.dorm.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/dormBuild.action")
public class DormBuildServlet extends HttpServlet {
    DormBuildService dormBuildService=new DormBuildServiceImpl();//多态，面向接口编程的体现
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        防止中文乱码
        request.setCharacterEncoding("utf-8");
//        1、接收请求
        String action=request.getParameter("action");
        if ("list".equals(action)){
            String id = request.getParameter("id");
//            查询所有用户
//            2、调用相应服务
            List<DormBuild> dormBuilds = new ArrayList<>();
            if (id == null) {
                dormBuilds = dormBuildService.getAllDormBuild();
            }else{
                dormBuilds.add(dormBuildService.findDormBuildById(Integer.parseInt(id)));
            }
            request.setAttribute("builds",dormBuilds);
            request.setAttribute("buildSelects",dormBuilds);

//            String id = request.getParameter("id");
//            List<DormBuild> dormBuildByName=dormBuildService.getDormBuilds(id);
//            request.setAttribute("builds",dormBuildByName);
//            3、返回页面
            request.setAttribute("mainRight","dormBuildList.jsp");
            request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);
        }else if ("preAdd".equals(action)) {//预添加
//            2、查所有宿舍楼的服务
            List<DormBuild> dormBuildList = dormBuildService.getAllDormBuild();
            request.setAttribute("builds", dormBuildList);

//            3、返回页面
            request.setAttribute("mainRight", "dormBuildAddOrUpdate.jsp");
            request.getRequestDispatcher("/jsp/main.jsp").forward(request, response);
        }else if("save".equals(action)){//添加
            String id=request.getParameter("id");
            String name=request.getParameter("name");
            String remark=request.getParameter("remark");

            if (id==null||id.equals("")){//保存
                DormBuild dormBuild=new DormBuild();
                dormBuild.setName(name);
                dormBuild.setRemark(remark);
                dormBuild.setDisabled(0);
//                2、调用相应的服务
                dormBuildService.saveDormBuild(dormBuild);
            }else{//修改
                DormBuild dormBuild=dormBuildService.getDormBuildById(Integer.parseInt(id));
                dormBuild.setName(name);
                dormBuild.setRemark(remark);
                dormBuildService.updateDormBuild(dormBuild);
            }
//            3、返回页面
            response.sendRedirect(getServletContext().getContextPath()+"/dormBuild.action?action=list");
        }else if ("preUpdate".equals(action)){
//            1、接收请求的数据
            String id=request.getParameter("id");
//            2、调用相应服务
            DormBuild dormBuild=dormBuildService.getDormBuildById(Integer.parseInt(id));
            request.setAttribute("build",dormBuild);

//            3、返回页面
            request.setAttribute("mainRight","dormBuildAddOrUpdate.jsp");
            request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);

        }else if ("deleteOrActive".equals(action)){//删除
            String id=request.getParameter("id");
            String disabled=request.getParameter("disabled");

            DormBuild dormBuild=dormBuildService.getDormBuildById(Integer.parseInt(id));
            dormBuild.setDisabled(Integer.parseInt(disabled));
            dormBuildService.updateDormBuild(dormBuild);
//            3、返回页面
            response.sendRedirect(getServletContext().getContextPath()+"/dormBuild.action?action=list");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
