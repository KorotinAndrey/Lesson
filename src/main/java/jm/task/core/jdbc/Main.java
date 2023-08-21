package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        User user = new User("Andrey", "Korotin", (byte) 31);
        userDao.saveUser(user);

        System.out.println(user.getId());


    }
}