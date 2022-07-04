package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String USERNAME = "root";
    private static final String PASSWORD = "92abedud";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver error!");
            System.exit(-1);
        } catch (SQLException e) {
            System.out.println("Connection error");
            System.exit(-1);
        }
        return connection;
    }



}
