package cn.xuziao.faceprocess.dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {
    public void testConnection() throws ClassNotFoundException, SQLException {
        final String TAG = "LoginActivity";
        Log.d(TAG, "Logkdajsflkjklads");
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://rm-2vc40ra5724w7e66weo.mysql.cn-chengdu.rds.aliyuncs.com:3306/user_info";
        String login_username = "login";
        String login_password = "xuziao_SQL";
        Connection connection = DriverManager.getConnection(url, login_username, login_password);
        String sql = "select username from base_info";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            System.out.println("nini");
        }
    }
}
