package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String USERNAME = "root";
    private static final String PASSWORD = "92abedud";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static Connection connection = null;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        Properties property = new Properties();
        property.setProperty("hibernate.connection.url", URL);
        property.setProperty("hibernate.connection.username", USERNAME);
        property.setProperty("hibernate.connection.password", PASSWORD);
        property.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        property.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        property.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        property.setProperty("hibernate.connection.pool_size", "1");
        property.setProperty("hibernate.current_session_context_class", "thread");
        try {
            if (sessionFactory == null) {
                sessionFactory = new Configuration()
                        .addProperties(property)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            }
        } catch (Exception e) {
            System.out.println("Ошибка подключения!");
        }
        return sessionFactory;
    }


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
