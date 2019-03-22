package com.example.demo.Servlet;

import com.example.demo.Service.Impl.UserServiceImpl;
import com.example.demo.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class AddServlet extends HttpServlet {
    //用于测试权限
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_name=req.getParameter("user_name");
        String password=req.getParameter("password");
        String email=req.getParameter("email");
        String telephone=req.getParameter("telephone");
        String role=req.getParameter("role");

        PrintWriter out=resp.getWriter();
        UserService userService=new UserServiceImpl();
        try {
            String log=userService.getGodResAddUser(user_name,password,email,telephone,role);
            out.write(log);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
