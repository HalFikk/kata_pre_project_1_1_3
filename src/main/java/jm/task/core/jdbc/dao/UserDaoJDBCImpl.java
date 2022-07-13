package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            //statement.execute("CREATE DATABASE users;");
            statement.execute("CREATE TABLE user (\n" +
                    "  `ID` INT NOT NULL AUTO_INCREMENT, \n" +
                    "  `Name` VARCHAR(45) NOT NULL,\n" +
                    "  `LastName` VARCHAR(45) NOT NULL,\n" +
                    "  `Age` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`));");
        } catch (SQLException e) {
            System.out.println("Такая таблица существует!");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("DROP TABLE user;");
            //statement.execute("DROP DATABASE users;");
        } catch (SQLException e) {
            System.out.println("Такого пользователя не существует!");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO user(Name, LastName, Age) VALUES(?, ?, ?);";
        try (PreparedStatement statement = Util.getConnection()
                .prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString( 2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Не удалось сохранить пользователя!");
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM user WHERE ID = ?;";
        try (PreparedStatement statement = Util.getConnection()
                .prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Не удалось удалить пользователя!");
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user;");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("Name"),
                        resultSet.getString("LastName"), (byte) resultSet.getInt("Age"));
                user.setId(resultSet.getLong("ID"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("При получении произошла ошибка!");
        }
        return users;

    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("DELETE FROM user;");
        } catch (SQLException e) {
            System.out.println("Не удалось удалить таблицу!");
        }
    }
}
