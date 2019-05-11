package com.cqupt.servlet;

import com.alibaba.fastjson.JSON;
import com.cqupt.pojo.Student;
import com.cqupt.service.ScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/sortedscore")
public class SortScoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        ScoreService scoreService = new ScoreService();
        List<Student> studentList = scoreService.getSortedScore(name,type);
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(studentList));
    }
}
