package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE if not exists user (\n" +
                    "  `ID` INT NOT NULL AUTO_INCREMENT, \n" +
                    "  `Name` VARCHAR(45) NOT NULL,\n" +
                    "  `LastName` VARCHAR(45) NOT NULL,\n" +
                    "  `Age` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`));").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Ошибка создания");
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE user;").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Ошибка удаления");
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Ошибка добавления User");
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Ошибка удаления 1");
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        List<User> userList = null;
        try {
            session.beginTransaction();
            userList = session.createQuery("from User", User.class).getResultList();
            session.getTransaction().commit();
            return userList;
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("DELETE FROM user;").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Ошибка удаления в таблице");
        } finally {
            session.close();
        }
    }
}
