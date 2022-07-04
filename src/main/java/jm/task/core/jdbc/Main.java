package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();
        User user1 = new User("Alex", "Loktaev", (byte)26);
        User user2 = new User("Ivan", "Sidirov", (byte)18);
        User user3 = new User("Oleg", "Konov", (byte)33);
        User user4 = new User("Roman", "Nosov", (byte)46);
        service.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User с именем - " + user1.getName() + " добавлен в базу данных");
        service.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User с именем - " + user2.getName() + " добавлен в базу данных");
        service.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User с именем - " + user3.getName() + " добавлен в базу данных");
        service.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("User с именем - " + user4.getName() + " добавлен в базу данных");
        System.out.println(service.getAllUsers());
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
