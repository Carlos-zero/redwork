package com.example.demo.Dao;

import com.example.demo.Been.User;

import java.sql.SQLException;

public interface UserSqlDao {
    //用户登录数据库验证,并返回用户所有信息
    User login(String user_name,String password)throws SQLException;

    //管理员更改用户数据
    String updateData(String password,String email,String telephone,String user_name) throws SQLException;

    //更改用户角色
    String updateRole(String user_name,String role)throws SQLException;

    //添加用户
    String insertUser(String user_name,String password,String email,String telephone,String role)throws SQLException;

    //删除用户
    String deleteUser(String user_name)throws SQLException;

}
