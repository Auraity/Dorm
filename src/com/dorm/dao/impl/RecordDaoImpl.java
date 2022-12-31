package com.dorm.dao.impl;

import com.dorm.dao.RecordDao;
import com.dorm.pojo.DormBuild;
import com.dorm.pojo.Record;
import com.dorm.pojo.User;
import com.dorm.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.dorm.utils.DBUtils.close;

public class RecordDaoImpl implements RecordDao {
    @Override
    public List<Record> findAllRecords() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Record> records = new ArrayList<>();
        try {
//            1、注册驱动和获取连接
            conn = DBUtils.getConnection();

//            2、数据库操作对象
            String sql = "SELECT * FROM tb_record r,tb_user u,tb_dormbuild d where r.student_id = u.id and u.dormbuildId = d.id";
            ps = conn.prepareStatement(sql);//预编译

//            3、执行sql语句
            rs = ps.executeQuery();

//            4、处理查询结果集
            while (rs.next()) {
                Record record = new Record();
                record.setId(rs.getInt(1));
                record.setStudentId(rs.getInt("student_id"));
                record.setDate(rs.getDate("date"));
                record.setRemark(rs.getString("remark"));
                record.setDisabled(rs.getInt("disabled"));
                record.setUser(new User(rs.getString("name"),
                        rs.getString("stu_code"),
                        rs.getString("sex"),
                        rs.getString("dorm_code"),
                        new DormBuild(rs.getString("d.name"))));
                records.add(record);
            }
            return records;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, conn);
        }
        return null;
    }

    @Override
    public List<Record> findRecordsBySearchType(String startDate, String endDate, String dormBuildId, String searchType, String keyword) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Record> records = new ArrayList<>();
        try {
//            1、注册驱动和获取连接
            conn = DBUtils.getConnection();
//            2、数据库操作对象
            String sql = "select * from tb_record r,tb_user u,tb_dormbuild d where r.student_id = u.id and u.dormbuildId = d.id and u." + searchType + " like ? ";
            ps = conn.prepareStatement(sql);  //预编译
            ps.setString(1,'%'+keyword+'%');
//            3、执行sql语句
            rs = ps.executeQuery();
//            4、处理查询结果集
            while (rs.next()) {
                Record record = new Record();
                record.setId(rs.getInt(1));
                record.setStudentId(rs.getInt("student_id"));
                record.setDate(rs.getDate("date"));
                record.setRemark(rs.getString("remark"));
                record.setDisabled(rs.getInt("disabled"));
                record.setUser(new User(rs.getString("name"),
                        rs.getString("stu_code"),
                        rs.getString("sex"),
                        rs.getString("dorm_code"),
                        new DormBuild(rs.getString("d.name"))));
                records.add(record);
            }
            return records;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, conn);
        }
        return null;
    }

    @Override
    public Record findRecordById(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "select r.*,u.stu_code from tb_record r,tb_user u where r.id = ? and r.student_id = u.id";
            ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            rs = ps.executeQuery();
            if (rs.next()){
                User user = new User();
                user.setStuCode(rs.getString("u.stu_code"));
                return new Record(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getInt(5),
                        user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,ps,conn);
        }
        return null;
    }

    @Override
    public int updateRecord(String id, String stuCode, String date, String remark) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "update tb_user u,tb_record r set u.stu_code = ?,r.date = ?,r.remark = ? where u.id = r.student_id and r.id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,stuCode);
            ps.setString(2,date);
            ps.setString(3,remark);
            ps.setString(4,id);
            int i = ps.executeUpdate();
            return i;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(null,ps,conn);
        }
    }

    @Override
    public int addRecord(String stuCode, String date, String remark) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "insert into tb_record(student_id,date,remark) values (?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,getStudentID(stuCode));
            ps.setString(2,date);
            ps.setString(3,remark);
            int i = ps.executeUpdate();
            return i;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(null,ps,conn);
        }
    }
    private String getStudentID(String stuCode){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "select id from tb_user where stu_code = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,stuCode);
            rs = ps.executeQuery();
            if (rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,ps,conn);
        }
        return null;
    }
}
