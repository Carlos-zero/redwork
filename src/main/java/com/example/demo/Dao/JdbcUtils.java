package com.example.demo.Dao;



import java.sql.*;

//这是一个工具类
public class JdbcUtils {
    //加载DB驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection conn = null;

    //获取Connection对象
    public static Connection getConnection() throws SQLException {

        final  String DBURL ="jdbc:mysql://localhost:3306/user?serverTimezone=GMT%2B8";     //定义mysql数据库的连接地址
        final  String DBUSER ="root";                                       //MySQL的连接用户名
        final  String DBPASS ="";                                           //MySQL数据库的连接密码
        if (conn == null || conn.isClosed()){                               //连接为null或者连接被关闭
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS);        //连接MySQL时，输入相应的内容
        }
        return conn;
    }

    public static void close(Connection conn, Statement stmt)throws SQLException{
        if (conn!=null&&conn.isClosed()){
            conn.close();
        }
        if (stmt!=null&&!stmt.isClosed()){
            stmt.close();
        }
    }
    public static void close(Connection conn, PreparedStatement stmt,ResultSet rs) throws SQLException{
        if (conn!=null&&!conn.isClosed()){
            conn.close();
        }
        if (stmt!=null&&!stmt.isClosed()){
            stmt.close();
        }
        if (rs!=null&&!rs.isClosed()){
            rs.close();
        }
    }
}
