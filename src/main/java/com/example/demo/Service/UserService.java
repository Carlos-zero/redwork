package com.example.demo.Service;

import com.example.demo.Been.User;

import java.sql.SQLException;

public interface UserService {
    //得到登录的用户对象
    User getUserLogin(String user_name,String password)throws SQLException;

    //get管理员修改数据结果
    String getAdminResUpData(String password,String email,String telephone,String user_name)throws SQLException;

    //get god 修改用户角色结果
    String getGodResUpRole(String user_name,String role)throws SQLException;

    //get god 添加用户结果
    String getGodResAddUser(String user_name,String password,String email,String telephone,String role)throws SQLException;

    //get god 删除用户结果
    String getGodResDelUser(String user_name)throws SQLException;
}
