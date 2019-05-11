package com.cqupt.dao;

import com.cqupt.pojo.Student;
import com.cqupt.pojo.User;
import com.cqupt.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public String getPasswordByUserMame(String userName){
        Connection conn = JdbcUtil.getConnection();
        String sql = "select password from user where userName = ?";
        String password = null;
        PreparedStatement ps  = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,userName);
            rs = ps.executeQuery();
            if(rs.next()){
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

}
