package com.cqupt.utils;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/ssms?serverTimezone=UTC&charcaterEncoding=utf8&useUincode=true&useSSL=false&autoReconnect=true";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    public static Connection conn = null;

    public static void connect(){
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        if(conn==null){
            connect();
        }
        return conn;
    }

    public static void close(ResultSet rs,PreparedStatement ps ,Connection conn){
        try {
            if(rs!=null) {
                rs.close();
            }
            if(ps!=null){
                rs.close();
            }
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
