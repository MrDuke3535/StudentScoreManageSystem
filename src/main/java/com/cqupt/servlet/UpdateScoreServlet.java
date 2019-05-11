package com.cqupt.servlet;

import com.cqupt.service.ScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update")
public class UpdateScoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stuId = request.getParameter("stuId");
        String name = request.getParameter("name");
        String dataStructure = request.getParameter("dataStructure");
        String java = request.getParameter("java");
        PrintWriter out = response.getWriter();
        ScoreService scoreService = new ScoreService();
        String result = scoreService.updateScore(stuId,name,dataStructure,java);
        out.println(result);
    }
}
