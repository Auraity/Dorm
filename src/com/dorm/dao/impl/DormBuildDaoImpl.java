package com.dorm.dao.impl;

import com.dorm.dao.DormBuildDao;
import com.dorm.pojo.DormBuild;
import com.dorm.pojo.User;
import com.dorm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.dorm.utils.DBUtils.close;

public class DormBuildDaoImpl implements DormBuildDao {
    @Override
    public List<DormBuild> findDormBuildsByUserId(Integer id) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<DormBuild> dormBuilds=new ArrayList<>();
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="SELECT td.* FROM tb_manage_dormbuild AS tmd,tb_dormbuild AS td WHERE td.id= tmd.dormBuild_id AND user_id=?";
            ps=conn.prepareStatement(sql);//预编译

//            3、执行sql语句
            ps.setInt(1,id);
            rs=ps.executeQuery();

//            4、处理查询结果集
            while (rs.next()){
                DormBuild dormBuild=new DormBuild();
                dormBuild.setId(rs.getInt(1));
                dormBuild.setName(rs.getString("name"));
                dormBuild.setRemark(rs.getString("remark"));
                dormBuild.setDisabled(rs.getInt("disabled"));
                dormBuilds.add(dormBuild);
            }
            return dormBuilds;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,conn);
        }
        return null;
    }

    @Override
    public List<DormBuild> findAllDormBuild() {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<DormBuild> dormBuilds=new ArrayList<>();
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="SELECT * FROM tb_dormbuild";
            ps=conn.prepareStatement(sql);//预编译

//            3、执行sql语句
            rs=ps.executeQuery();

//            4、处理查询结果集
            while (rs.next()){
                DormBuild dormBuild=new DormBuild();
                dormBuild.setId(rs.getInt(1));
                dormBuild.setName(rs.getString("name"));
                dormBuild.setRemark(rs.getString("remark"));
                dormBuild.setDisabled(rs.getInt("disabled"));
                dormBuilds.add(dormBuild);
            }
            return dormBuilds;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,conn);
        }
        return null;
    }

    @Override
    public void saveManagerAndBuilds(Integer userId, String[] dormBuildIds) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="insert into tb_manage_dormbuild(user_id,dormBuild_id) value(?,?)";
            ps=conn.prepareStatement(sql);//预编译
            for (String dormBuildId:dormBuildIds) {
                ps.setInt(1,userId);
                ps.setInt(2,Integer.parseInt(dormBuildId));
//                将sql语句添加到批处理
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(null,ps,conn);
        }
    }

    @Override
    public void deleteDormBuildsByUserId(Integer id) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="delete from tb_manage_dormbuild where user_id=?";
            ps=conn.prepareStatement(sql);//预编译
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(null,ps,conn);
        }
    }

    @Override
    public List<DormBuild> findDormBuilds(String id) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<DormBuild> dormBuilds=new ArrayList<>();
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="select * from tb_dormbuild where name=?";
            ps=conn.prepareStatement(sql);//预编译
            ps.setString(1,id);

//            3、执行sql语句
            rs=ps.executeQuery();

//            4、处理查询结果集
            while (rs.next()){
//                将查询的结果封装到User实体中
                DormBuild dormBuild=new DormBuild();
                dormBuild.setId(rs.getInt("id"));
                dormBuild.setName(rs.getString("name"));
                dormBuild.setRemark(rs.getString("remark"));
                dormBuild.setDisabled(rs.getInt("disabled"));
                dormBuilds.add(dormBuild);
            }
            return dormBuilds;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,conn);
        }
        return null;
    }

    @Override
    public void saveDormBuild(DormBuild dormBuild) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="insert into tb_dormbuild(name,remark,disabled) value (?,?,?)";
            ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//预编译
            ps.setString(1,dormBuild.getName());
            ps.setString(2,dormBuild.getRemark());
            ps.setInt(3,dormBuild.getDisabled());
//            3、执行sql语句
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,conn);
        }
    }

    @Override
    public DormBuild findDormBuildById(int id) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="select * from tb_dormbuild where id=?";
            ps=conn.prepareStatement(sql);//预编译
            ps.setInt(1,id);

//            3、执行sql语句
            rs=ps.executeQuery();

//            4、处理查询结果集
            while (rs.next()){
//                将查询的结果封装到User实体中
                DormBuild dormBuild=new DormBuild();
                dormBuild.setId(rs.getInt("id"));
                dormBuild.setName(rs.getString("name"));
                dormBuild.setRemark(rs.getString("remark"));
                dormBuild.setDisabled(rs.getInt("disabled"));
                return dormBuild;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,conn);
        }
        return null;
    }

    @Override
    public void updateDormBuild(DormBuild dormBuild) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="update tb_dormbuild set name=?,remark=?,disabled=? where id=?";
            ps=conn.prepareStatement(sql);//预编译
            ps.setString(1,dormBuild.getName());
            ps.setString(2,dormBuild.getRemark());
            ps.setInt(3,dormBuild.getDisabled());
            ps.setInt(4,dormBuild.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(null,ps,conn);
        }
    }

    @Override
    public List<DormBuild> findDormBuildsByBuildId(Integer id) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<DormBuild> dormBuilds=new ArrayList<>();
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="select tb_dormbuild.* from tb_user left join tb_dormbuild on tb_dormbuild.id=tb_user.dormBuildId where tb_user.id=?;";
            ps=conn.prepareStatement(sql);//预编译
//            3、执行sql语句
            ps.setInt(1,id);
            rs=ps.executeQuery();

//            4、处理查询结果集
            while (rs.next()){
                DormBuild dormBuild=new DormBuild();
                dormBuild.setId(rs.getInt(1));
                dormBuild.setName(rs.getString("name"));
                dormBuild.setRemark(rs.getString("remark"));
                dormBuild.setDisabled(rs.getInt("disabled"));
                dormBuilds.add(dormBuild);
            }
            return dormBuilds;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,conn);
        }
        return null;
    }

    @Override
    public void saveStudentAndBuilds(Integer userId, String dormBuildIds) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="insert into tb_manage_dormbuild(user_id,dormBuild_id) value(?,?)";
            ps=conn.prepareStatement(sql);//预编译
                ps.setInt(1,userId);
                ps.setInt(2,Integer.parseInt(dormBuildIds));
//                将sql语句添加到批处理
                ps.addBatch();
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(null,ps,conn);
        }
    }
}
