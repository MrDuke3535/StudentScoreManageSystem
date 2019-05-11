package com.cqupt.dao;

import com.cqupt.pojo.Student;
import com.cqupt.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    public List<Student> getScoreList(){
        Connection conn = JdbcUtil.getConnection();
        String sql = "select id,name,data_structure as dataStructure,java from score";
        PreparedStatement ps =  null;
        ResultSet rs = null;
        List<Student> studentList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Student student = new Student();
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setDataStructure(rs.getInt("dataStructure"));
                student.setJava(rs.getInt("java"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public int addScore(Student stu){
        Connection conn = JdbcUtil.getConnection();
        String sql = "insert into score(id,name,data_structure,java) values(?,?,?,?)";
        PreparedStatement ps = null;
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,stu.getId());
            ps.setString(2,stu.getName());
            ps.setInt(3,stu.getDataStructure());
            ps.setInt(4,stu.getJava());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Student getStudentById(String id){
        String sql = "select id,name,data_structure as dataStructure,java from score where id = ?";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student stu = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                stu = new Student();
                stu.setId(rs.getString("id"));
                stu.setName(rs.getString("name"));
                stu.setDataStructure(rs.getInt("dataStructure"));
                stu.setJava(rs.getInt("java"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stu;
    }

    public int updateScore(Student student){
        String sql = "update score set name=?,data_structure=?,java=? where id = ?";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,student.getName());
            ps.setInt(2,student.getDataStructure());
            ps.setInt(3,student.getJava());
            ps.setString(4,student.getId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteScore(String stuId){
        String sql = "delete from score where id = ?";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,stuId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Student> queryScoreByKeyWord(String keyWord){
        String sql = "select id,name,data_structure as dataStructure,java from score where id = ? or name like ?";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> studentList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,keyWord);
            ps.setString(2,"%"+keyWord+"%");
            rs = ps.executeQuery();
            while (rs.next()){
                Student stu = new Student();
                stu.setId(rs.getString("id"));
                stu.setName(rs.getString("name"));
                stu.setDataStructure(rs.getInt("dataStructure"));
                stu.setJava(rs.getInt("java"));
                studentList.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

}
