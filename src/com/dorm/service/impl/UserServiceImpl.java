package com.dorm.service.impl;

import com.dorm.dao.DormBuildDao;
import com.dorm.dao.UserDao;
import com.dorm.dao.impl.DormBuildDaoImpl;
import com.dorm.dao.impl.UserDaoImpl;
import com.dorm.pojo.DormBuild;
import com.dorm.pojo.User;
import com.dorm.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao=new UserDaoImpl();//多态，面向接口编程的体现
    DormBuildDao dormBuildDao=new DormBuildDaoImpl();

    @Override
    public User findUserByStuCodeAndPassword(String stuCode, String password) {
        return userDao.findUserByStuCodeAndPassword(stuCode,password);
    }

    @Override
    public List<User> findDormManager() {
//        1、获取宿舍楼管理员信息
        List<User> dormManagers=userDao.findDormManager();
        for (User dormManager:dormManagers){
//            2、获取宿舍楼管理员管理的宿舍楼信息
            List<DormBuild> dormBuilds=dormBuildDao.findDormBuildsByUserId(dormManager.getId());
            dormManager.setDormBuilds(dormBuilds);
        }
        return dormManagers;
    }

    @Override
    public List<User> findDormManager(String searchType, String keyword) {
        StringBuffer sql = new StringBuffer("select * from tb_user where role_id=1 ");
        if (keyword!=null && !keyword.equals("")){
//            按照名称查找
            if ("name".equals(searchType)){
                sql.append(" and name like '%"+keyword.trim()+"%'");
            }else if ("sex".equals(searchType)){
                sql.append(" and sex like '%"+keyword.trim()+"%'");
            }else if ("tel".equals(searchType)){
                sql.append(" and tel like '%"+keyword.trim()+"%'");
            }
        }
        List<User> dormManagers=userDao.findDormManager(sql.toString());
        for (User dormManager: dormManagers){
            List<DormBuild> dormBuilds=dormBuildDao.findDormBuildsByUserId(dormManager.getId());
            dormManager.setDormBuilds(dormBuilds);
        }
        return dormManagers;
    }

    @Override
    public void saveManagerAndBuilds(User user, String[] dormBuildIds) {
//        1、保存宿舍楼管理员信息
        String stuCodeMax=userDao.findUserStuCodeMax();
        user.setStuCode(stuCodeMax);
        Integer userId=userDao.saveManager(user);
        System.out.println("当前插入主键"+userId);
//        2、保存保存宿舍楼管理员管理的宿舍楼信息
        dormBuildDao.saveManagerAndBuilds(userId,dormBuildIds);
    }

    @Override
    public User getDormManagerById(int id) {
        return userDao.findDormManagerById(id);
    }

    @Override
    public void updateManager(User user) {
        userDao.updateUser(user);
    }

    @Override
    public List<User> getStudents() {
//        1、获取学生信息
        List<User> students=userDao.findStudent();
        for (User student:students){
//            2、获取学生所在的宿舍楼信息
            List<DormBuild> dormBuilds=dormBuildDao.findDormBuildsByBuildId(student.getId());
                student.setDormBuilds(dormBuilds);
        }
        return students;
    }

    @Override
    public List<User> getStudents(String searchType, String keyword) {
        StringBuffer sql = new StringBuffer("select * from tb_user where role_id=2 ");
        if (keyword!=null && !keyword.equals("")){
//            按照名称查找
            if ("name".equals(searchType)){
                sql.append(" and name like '%"+keyword.trim()+"%'");
            }else if ("stu_code".equals(searchType)){
                sql.append(" and stu_code like '%"+keyword.trim()+"%'");
            }else if ("dorm_Code".equals(searchType)){
                sql.append(" and dorm_Code like '%"+keyword.trim()+"%'");
            }else if ("sex".equals(searchType)){
                sql.append(" and sex like '%"+keyword.trim()+"%'");
            }else if ("tel".equals(searchType)){
                sql.append(" and tel like '%"+keyword.trim()+"%'");
            }
        }
        List<User> dorms=userDao.findDormManager(sql.toString());
        for (User dorm: dorms){
            List<DormBuild> dormBuilds=dormBuildDao.findDormBuildsByBuildId(dorm.getId());
            dorm.setDormBuilds(dormBuilds);
        }
        return dorms;
    }

    @Override
    public void saveStudentAndBuilds(User user, String dormBuildIds) {
//        1、保存宿舍楼管理员信息
        String stuCodeMax=userDao.findUserStuCodeMax();
        user.setStuCode(stuCodeMax);
        Integer userId=userDao.saveStudent(user);
        System.out.println("当前插入主键"+userId);
//        2、保存保存宿舍楼管理员管理的宿舍楼信息
        dormBuildDao.saveStudentAndBuilds(userId,dormBuildIds);
    }

    @Override
    public int changePassword(String oldPassword, String newPassword, Integer id) {
        String oldPwd=userDao.findOldPassword(id);
        if (oldPwd.equals(oldPassword)){
            return userDao.setNewPassword(newPassword,id);
        }else{
            return -1;
        }
    }
}
