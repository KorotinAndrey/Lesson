package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();

        // Создание таблицы
        userDao.createUsersTable();

        //Сохранение пользователя
        userDao.saveUser("Andrey", "Korotin", (byte) 31);
        userDao.saveUser("Anna", "Korotina", (byte) 31);

        //Получение всех пользователей
        List<User> users = userDao.getAllUsers();
        System.out.println("Все пользователи: ");
        for (User user : users) {
            System.out.println(user);
        }
        //Удаление пользователя по ID
        long userIdToRemove = 1;
        userDao.removeUserById(userIdToRemove);

        // Получение всех пользователей после удаления
        users = userDao.getAllUsers();
        System.out.println("All Users after removal:");
        for (User user : users) {
            System.out.println(user);
        }
        //Очистка таблицы
        userDao.cleanUsersTable();

        //Удаление таблицы
        userDao.dropUsersTable();
    }
}