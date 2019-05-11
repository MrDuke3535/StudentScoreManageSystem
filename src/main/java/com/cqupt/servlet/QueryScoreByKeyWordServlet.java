package com.cqupt.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cqupt.pojo.Student;
import com.cqupt.service.ScoreService;
import com.cqupt.utils.SortUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/search")
public class QueryScoreByKeyWordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyWord = request.getParameter("keyWord");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        PrintWriter out = response.getWriter();
        if(keyWord!=null||!keyWord.trim().equals("")){
            ScoreService scoreService = new ScoreService();
            List<Student> studentList = scoreService.queryScoreByKeyWord(keyWord.trim());
            studentList = SortUtil.sortStudent(studentList,name,type);
            out.println(JSON.toJSONString(studentList));
        }else {
            out.println("error");
        }
    }
}
