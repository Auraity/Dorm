package com.dorm.dao.impl;

import com.dorm.dao.RecordDao;
import com.dorm.pojo.DormBuild;
import com.dorm.pojo.Record;
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
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<Record> records=new ArrayList<>();
        try {
//            1、注册驱动和获取连接
            conn= DBUtils.getConnection();

//            2、数据库操作对象
            String sql="SELECT * FROM tb_record";
            ps=conn.prepareStatement(sql);//预编译

//            3、执行sql语句
            rs=ps.executeQuery();

//            4、处理查询结果集
            while (rs.next()){
                Record record=new Record();
                record.setId(rs.getInt(1));
                record.setStudentId(rs.getInt("student_id"));
                record.setDate(rs.getDate("date"));
                record.setRemark(rs.getString("remark"));
                record.setDisabled(rs.getInt("disabled"));
                records.add(record);
            }
            return records;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,conn);
        }
        return null;
    }
}