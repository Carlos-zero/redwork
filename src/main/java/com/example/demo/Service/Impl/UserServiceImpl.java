package com.example.demo.Service.Impl;

import com.example.demo.Been.User;
import com.example.demo.Dao.Impl.UserSqlDaoImpl;
import com.example.demo.Dao.UserSqlDao;
import com.example.demo.Annotation.Role;
import com.example.demo.Service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private static final UserSqlDao USER_SQL_DAO;
    private static User user=new User();

    static {
        USER_SQL_DAO = new UserSqlDaoImpl();
    }

    @Override
    public User getUserLogin(String user_name, String password) throws SQLException {
        user=USER_SQL_DAO.login(user_name, password);
        return user;
    }

    public User getUser(){
        return user;
    }

    @Role(need_role = "admin")
    @Override
    public String getAdminResUpData(String password, String email, String telephone, String user_name) throws SQLException {
        String result=USER_SQL_DAO.updateData(password, email, telephone, user_name);
        return result;
    }

    @Role(need_role = "god")
    @Override
    public String getGodResUpRole(String user_name, String role) throws SQLException {
        String result=USER_SQL_DAO.updateRole(user_name, role);
        return result;
    }

    @Role(need_role = "god")
    @Override
    public String getGodResAddUser(String user_name, String password, String email, String telephone, String role) throws SQLException {
        String result=USER_SQL_DAO.insertUser(user_name, password, email, telephone, role);
        return result;
    }

    @Role(need_role = "god")
    @Override
    public String getGodResDelUser(String user_name) throws SQLException {
        String result=USER_SQL_DAO.deleteUser(user_name);
        return result;
    }
}
