package com.cqupt.servlet;

import com.cqupt.service.ScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add")
public class AddScoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String stuId = request.getParameter("stuId");
        String name = request.getParameter("name");
        String dataStructure = request.getParameter("dataStructure");
        String java = request.getParameter("java");
        if(stuId==null||stuId.trim().equals("")||name==null||name.trim().equals("")
                ||dataStructure==null||dataStructure.trim().equals("")||java==null||
                java.trim().equals("")){
            out.println("你这个黑客！！！滚");
        }else {
            ScoreService scoreService = new ScoreService();
            String result = scoreService.addScoere(stuId,name,dataStructure,java);
            out.println(result);
        }

    }
}
