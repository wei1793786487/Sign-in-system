package com.hqgml.utlis;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCutils {
    private static DataSource ds; //datasourse是数据库连接池里面的东西

    static {
        try {
            Properties pp = new Properties();
            pp.load(JDBCutils.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(pp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close(ResultSet rs, Statement st, Connection cc) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (cc != null) {
                    try {
                        cc.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
    public static void close( Statement st, Connection cc) {
      close(null,st,cc);
    }
    public static DataSource getDataSource(){

        return ds;
    }
}