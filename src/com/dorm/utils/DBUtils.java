package com.dorm.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtils {
    private static DataSource dataSource;
    static {
//        读取文件初始化连接参数
        Properties properties = new Properties();
        InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");

        try {
            properties.load(inputStream);//加载配置文件
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 获取数据库连接
         *
         * @return 数据库连接对象
         * @throws sql异常对象
         */
    }
        public static Connection getConnection() throws SQLException {
            return dataSource.getConnection();
        }

    /**
     * 释放资源
     * @param rs  查询结果集
     * @param stmt 数据库操作对象
     * @param conn 数据库连接对象
     */
        public static void close(ResultSet rs, Statement stmt,Connection conn){
            try {
                if (rs !=null){
                    rs.close();
                }
                if (stmt !=null){
                    stmt.close();
                }
                if (conn !=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
