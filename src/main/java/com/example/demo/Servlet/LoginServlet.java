package com.example.demo.Servlet;

import com.example.demo.Been.User;
import com.example.demo.Service.Impl.UserServiceImpl;
import com.example.demo.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    //用于登录
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        //接受请求参数
        String user_name=request.getParameter("user_name");
        String password=request.getParameter("password");

        PrintWriter out=response.getWriter();
        UserService userService=new UserServiceImpl();
        try {
            User user=userService.getUserLogin(user_name,password);
            out.write(user.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
