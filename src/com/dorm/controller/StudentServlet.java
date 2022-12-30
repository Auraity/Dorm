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

@WebServlet("/student.action")
public class StudentServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();//多态，面向接口编程的体现
    DormBuildService dormBuildService = new DormBuildServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        防止中文乱码
        request.setCharacterEncoding("utf-8");
//        1、接收请求
        String action=request.getParameter("action");
        if ("list".equals(action)){
//            查询所有用户
//            2、调用相应服务
            String searchType = request.getParameter("searchType");
            String keyword = request.getParameter("keyword");
            List<User> student=userService.getStudents(searchType,keyword);
            request.setAttribute("students",student);
//            3、返回页面
            request.setAttribute("mainRight","studentList.jsp");
            request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);
        }else if ("preAdd".equals(action)){//预添加
//            2、查所有宿舍楼的服务
            List<DormBuild> dormBuildList=dormBuildService.getAllDormBuild();
            request.setAttribute("builds",dormBuildList);

//            3、返回页面
            request.setAttribute("mainRight","studentAddOrUpdate.jsp");
            request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);
        }else if ("save".equals(action)){//添加业务
//            1、获取前端数据请求
            String id = request.getParameter("id");
            String stuCode = request.getParameter("stuCode");
            String name = request.getParameter("name");
            String passWord = request.getParameter("passWord");
            String sex = request.getParameter("sex");
            String tel = request.getParameter("tel");
            String dormBuildIds = request.getParameter("dormBuildId");
            System.out.println(dormBuildIds);
            String dormCode = request.getParameter("dormCode");

            if (id==null||id.equals("")){//保存
                User user=new User();
                user.setStuCode(stuCode);
                user.setName(name);
                user.setPassword(passWord);
                user.setSex(sex);
                user.setTel(tel);
                user.setDormCode(dormCode);
                user.setRoleId(2);
                user.setDisabled(0);
//            2、调用相应的服务
                userService.saveStudentAndBuilds(user,dormBuildIds);

            }else{//修改
//                1、修改宿舍楼管理员信息
                User user=userService.getDormManagerById(Integer.parseInt(id));
                user.setStuCode(stuCode);
                user.setName(name);
                user.setPassword(passWord);
                user.setSex(sex);
                user.setTel(tel);
                user.setDormCode(dormCode);
                userService.updateManager(user);
//                2、修改宿舍楼管理员管理的楼栋信息
//                2.1删除已存在的楼栋信息
                dormBuildService.deleteDormBuildsByUserId(user.getId());

//                2.2添加新的楼栋信息
//                dormBuildService.addDormBuildByUserId(user.getId(),dormBuildIds);
            }
//            3、返回页面
            response.sendRedirect(getServletContext().getContextPath()+"/student.action?action=list");

        }else if ("preUpdate".equals(action)){
//            1、接收请求的数据
            String id=request.getParameter("id");
//            2、调用相应服务
            User student=userService.getDormManagerById(Integer.parseInt(id));
            request.setAttribute("userUpdate",student);
//            获取页面所有的宿舍楼信息
            List<DormBuild> dormBuildList=dormBuildService.getAllDormBuild();
            request.setAttribute("builds",dormBuildList);

//            获取该用户管理的宿舍楼信息
            List<DormBuild> dormBuilds=dormBuildService.getDormBuildBuUserId(student.getId());
            ArrayList<Integer> userBuildIds=new ArrayList<>();
            for(DormBuild dormBuild: dormBuilds){
                userBuildIds.add(dormBuild.getId());
            }
            request.setAttribute("dormBuildId",userBuildIds);

//            3、返回页面
            request.setAttribute("mainRight","studentAddOrUpdate.jsp");
            request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);

        }else if ("deleteOrActive".equals(action)){//删除
            String id=request.getParameter("id");
            String disabled=request.getParameter("disabled");

            User user=userService.getDormManagerById(Integer.parseInt(id));
            user.setDisabled(Integer.parseInt(disabled));
            userService.updateManager(user);
//            3、返回页面
            response.sendRedirect(getServletContext().getContextPath()+"/student.action?action=list");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
