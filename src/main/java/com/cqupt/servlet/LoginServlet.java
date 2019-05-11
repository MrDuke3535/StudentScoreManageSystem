package com.cqupt.servlet;

import com.cqupt.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/checkAccount")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberPassword = request.getParameter("rememberPassword");
        LoginService loginService = new LoginService();
        PrintWriter out = response.getWriter();
        if(loginService.checkAccount(userName,password)){
            request.getSession().setAttribute("user",userName);
            request.getSession().setMaxInactiveInterval(7200);//两小时
            if(rememberPassword!=null&&rememberPassword.equals("yes")){//如何记住密码就用cookie记住密码
                Cookie userNameCookie = new Cookie("userName",userName);
                Cookie passwordCookie = new Cookie("password",password);
                Cookie rememberCookie = new Cookie("rememberPassword",rememberPassword);
                userNameCookie.setMaxAge(864000);//10天
                passwordCookie.setMaxAge(864000);
                rememberCookie.setMaxAge(864000);
                response.addCookie(userNameCookie);
                response.addCookie(passwordCookie);
                response.addCookie(rememberCookie);
            }else{
                Cookie[] cookies = request.getCookies();
                if(cookies!=null&&cookies.length>0){
                    for(Cookie cookie:cookies){
                        if(cookie.getName().equals("userName")||cookie.getName().equals("password")||cookie.getName().equals("rememberPassword")){
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                    }
                }
            }
            out.print("success");
        }else{
            out.print("fail");
        }
//        Enumeration<String> params = request.getParameterNames();
//        while(params.hasMoreElements()){
//            String paramName = params.nextElement();
//            System.out.println(paramName+":"+request.getParameter(paramName));
//        }
    }
}
