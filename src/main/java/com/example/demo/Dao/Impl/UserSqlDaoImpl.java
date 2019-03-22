package com.example.demo.Dao.Impl;

import com.example.demo.Been.User;
import com.example.demo.Dao.JdbcUtils;
import com.example.demo.Dao.UserSqlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//      获取用户信息
public class UserSqlDaoImpl implements UserSqlDao {
    public static JdbcUtils conn=new JdbcUtils();

    @Override
    public User login(String user_name, String password) throws SQLException {
        User user=null;
        Connection connection=conn.getConnection();
        PreparedStatement statement=null;
        String sql="select * from user where user_name=? and password=?";
        statement=connection.prepareStatement(sql);
        statement.setString(1,user_name);
        statement.setString(2,password);
        ResultSet rs=statement.executeQuery();
        if (rs.next()){
            user=new User();
            PreparedStatement statement1=null;
            int id=rs.getInt("id");
            String sql1 = "select * from role where pid=?";
            statement1=connection.prepareStatement(sql1);
            statement1.setInt(1,id);
            ResultSet rs1=statement1.executeQuery();
            if (rs1.next()){
                user.setId(id);
                user.setUser_name(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setTelephone(rs.getInt("telephone"));
                user.setRole(rs1.getString("role"));
            }
        }
        JdbcUtils.close(connection,statement,rs);
        return user;
    }
    //管理员更改用户数据
    public String updateData(String password,String email,String telephone,String user_name) throws SQLException{
        Connection connection=conn.getConnection();
        PreparedStatement statement=null;
        String sql="update user set password=?,email=?,telephone=? where user_name=?";
        statement=connection.prepareStatement(sql);
        statement.setString(1,password);
        statement.setString(2,email);
        statement.setString(3,telephone);
        statement.setString(4,user_name);
        Integer rs=statement.executeUpdate();
        String log;
        if (rs!=0){
            log="数据更新成功！";
        }else {
            log="数据更新失败！";
        }
        return log;
    }
    //更改用户角色
    public String updateRole(String user_name,String role)throws SQLException{
        Connection connection=conn.getConnection();
        PreparedStatement statement=null;
        String sql="select id from user where user_name=?";
        statement=connection.prepareStatement(sql);
        statement.setString(1,user_name);
        ResultSet rs=statement.executeQuery();
        String log;
        if (rs.next()){
            int id=rs.getInt("id");
            sql="update role set role=? where pid=?";
            statement=connection.prepareStatement(sql);
            statement.setString(1,role);
            statement.setInt(2,id);
            Integer rs1=statement.executeUpdate();
            if (rs1!=0){
                log="修改用户角色成功！";
            }else {
                log="修改用户角色失败！";
            }
        }else {
            log="修改用户角色失败！";
        }
        return log;
    }
    //添加用户
    public String insertUser(String user_name,String password,String email,String telephone,String role)throws SQLException{
        Connection connection=conn.getConnection();
        PreparedStatement statement=null;
        String log;
        String sql="insert into user(user_name,password,email,telephone)value(?,?,?,?)";
        statement=connection.prepareStatement(sql);
        statement.setString(1,user_name);
        statement.setString(2,password);
        statement.setString(3,email);
        statement.setString(4,telephone);
        statement.executeUpdate();
        sql="select @@identity newId";
        statement=connection.prepareStatement(sql);
        ResultSet rs=statement.executeQuery();
        Integer id=null;
        if (rs.next()){
            id=rs.getInt("newId");
        }else {
            log="添加用户失败！";
        }
        sql="insert into role(role,pid) value (?,?)";
        statement=connection.prepareStatement(sql);
        statement.setString(1,role);
        statement.setInt(2,id);
        Integer rs1=statement.executeUpdate();
        if (rs1!=0){
            log="添加用户成功！";
        }else {
            log="添加用户失败！";
        }
        return log;
    }

    //删除用户
    public String deleteUser(String user_name)throws SQLException{
        Connection connection=conn.getConnection();
        PreparedStatement statement=null;
        String log = null;
        String sql="select id from user where user_name=?";
        statement=connection.prepareStatement(sql);
        statement.setString(1,user_name);
        ResultSet rs=statement.executeQuery();
        while (rs.next()){
            int id=rs.getInt("id");
            sql="delete from user where id=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1,id);
            Integer rs1=statement.executeUpdate();
            sql="delete from role where pid=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1,id);
            Integer rs2=statement.executeUpdate();
            if (rs1!=0&&rs2!=0){
                log="删除用户成功！";
            }else{
                log="删除用户失败！";
            }
        }
        return log;
    }
}
