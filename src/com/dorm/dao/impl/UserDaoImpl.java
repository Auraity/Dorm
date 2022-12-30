package com.dorm.dao.impl;

import com.dorm.dao.UserDao;
import com.dorm.pojo.User;
import com.dorm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.dorm.utils.DBUtils.close;

public class UserDaoImpl implements UserDao {
    @Override
    public User findUserByStuCodeAndPassword(String stuCode, String password) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="select * from tb_user where stu_code=? and password =? and disabled=0";
            ps=conn.prepareStatement(sql);//预编译

//            3、执行sql语句
            ps.setString(1,stuCode);
            ps.setString(2,password);
            rs=ps.executeQuery();

//            4、处理查询结果集
            while (rs.next()){
//                将查询的结果封装到User实体中
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setStuCode(rs.getString("stu_code"));
                user.setDormCode(rs.getString("dorm_Code"));
                user.setSex(rs.getString("sex"));
                user.setTel(rs.getString("tel"));
                user.setDormBuildId(rs.getInt("dormBuildId"));
                user.setRoleId(rs.getInt("role_id"));
                user.setCreateUserId(rs.getInt("create_user_id"));
                user.setDisabled(rs.getInt("disabled"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,conn);
        }
        return null;
    }

    @Override
    public List<User> findDormManager() {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<User> users=new ArrayList<>();
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="select * from tb_user where role_id=1";
            ps=conn.prepareStatement(sql);//预编译

//            3、执行sql语句
            rs=ps.executeQuery();

//            4、处理查询结果集
            while (rs.next()){
//                将查询的结果封装到User实体中
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setStuCode(rs.getString("stu_code"));
                user.setDormCode(rs.getString("dorm_Code"));
                user.setSex(rs.getString("sex"));
                user.setTel(rs.getString("tel"));
                user.setDormBuildId(rs.getInt("dormBuildId"));
                user.setRoleId(rs.getInt("role_id"));
                user.setCreateUserId(rs.getInt("create_user_id"));
                user.setDisabled(rs.getInt("disabled"));
                users.add(user);

            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,conn);
        }
        return null;
    }


    @Override
    public List<User> findDormManager(String sql) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<User> users=new ArrayList<>();
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            ps=conn.prepareStatement(sql);//预编译

//            3、执行sql语句
            rs=ps.executeQuery();

//            4、处理查询结果集
            while (rs.next()){
//                将查询的结果封装到User实体中
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setStuCode(rs.getString("stu_code"));
                user.setDormCode(rs.getString("dorm_Code"));
                user.setSex(rs.getString("sex"));
                user.setTel(rs.getString("tel"));
                user.setDormBuildId(rs.getInt("dormBuildId"));
                user.setRoleId(rs.getInt("role_id"));
                user.setCreateUserId(rs.getInt("create_user_id"));
                user.setDisabled(rs.getInt("disabled"));
                users.add(user);

            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,conn);
        }
        return null;
    }

    @Override
    public Integer saveManager(User user) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="insert into tb_user(name,password,stu_code,sex,tel,role_id,create_user_id,disabled) value (?,?,?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//预编译
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getStuCode());
            ps.setString(4,user.getSex());
            ps.setString(5,user.getTel());
            ps.setInt(6,user.getRoleId());
            ps.setInt(7,user.getCreateUserId());
            ps.setInt(8,user.getDisabled());
//            3、执行sql语句
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,conn);
        }
        return null;
    }

    @Override
    public String findUserStuCodeMax(){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="SELECT LPAD(IFNULL(MAX(stu_code),0000)+1,4,0) FROM tb_user;";
            ps=conn.prepareStatement(sql);//预编译
//            3、执行sql语句
            rs=ps.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,conn);
        }
        return null;
    }

    @Override
    public User findDormManagerById(int id) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="select * from tb_user where id=?";
            ps=conn.prepareStatement(sql);//预编译
            ps.setInt(1,id);

//            3、执行sql语句
            rs=ps.executeQuery();

//            4、处理查询结果集
            while (rs.next()){
//                将查询的结果封装到User实体中
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setStuCode(rs.getString("stu_code"));
                user.setDormCode(rs.getString("dorm_Code"));
                user.setSex(rs.getString("sex"));
                user.setTel(rs.getString("tel"));
                user.setDormBuildId(rs.getInt("dormBuildId"));
                user.setRoleId(rs.getInt("role_id"));
                user.setCreateUserId(rs.getInt("create_user_id"));
                user.setDisabled(rs.getInt("disabled"));
                return user;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                close(rs,ps,conn);
            }
            return null;
    }

    @Override
    public void updateUser(User user) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="update tb_user set name=?,password=?,sex=?,tel=?,disabled=? where id=?";
            ps=conn.prepareStatement(sql);//预编译
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getTel());
            ps.setInt(5,user.getDisabled());
            ps.setInt(6,user.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(null,ps,conn);
        }
    }

    @Override
    public List<User> findStudent() {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<User> users=new ArrayList<>();
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="SELECT * FROM tb_user WHERE role_id=2";
            ps=conn.prepareStatement(sql);//预编译

//            3、执行sql语句
            rs=ps.executeQuery();

//            4、处理查询结果集
            while (rs.next()){
//                将查询的结果封装到User实体中
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setStuCode(rs.getString("stu_code"));
                user.setDormCode(rs.getString("dorm_Code"));
                user.setSex(rs.getString("sex"));
                user.setTel(rs.getString("tel"));
                user.setDormBuildId(rs.getInt("dormBuildId"));
                user.setRoleId(rs.getInt("role_id"));
                user.setCreateUserId(rs.getInt("create_user_id"));
                user.setDisabled(rs.getInt("disabled"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,conn);
        }
        return null;
    }

    @Override
    public Integer saveStudent(User user) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="INSERT INTO tb_user(name,password,stu_code,dorm_Code,sex,tel,role_id,disabled) VALUE (?,?,?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//预编译
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getStuCode());
            ps.setString(4,user.getDormCode());
            ps.setString(5,user.getSex());
            ps.setString(6,user.getTel());
            ps.setInt(7,user.getRoleId());
            ps.setInt(8,user.getDisabled());
//            3、执行sql语句
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,conn);
        }
        return null;
    }

    @Override
    public String findOldPassword(Integer id) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<User> users=new ArrayList<>();
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="SELECT password FROM tb_user WHERE id=?";
            ps=conn.prepareStatement(sql);//预编译
            ps.setInt(1,id);
//            3、执行sql语句
            rs=ps.executeQuery();

//            4、处理查询结果集
            if (rs.next()) {
//                将查询的结果封装到User实体中
//                User user = new User();
//                user.setPassword(rs.getString("password"));
                String pwd=rs.toString();
                return pwd;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,conn);
        }
        return null;
    }

    @Override
    public void setNewPassword(String newPassword,Integer id) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="update tb_user set password=? where id=?";
            ps=conn.prepareStatement(sql);//预编译
            ps.setString(1,newPassword);
            ps.setInt(2,id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(null,ps,conn);
        }
    }
}
