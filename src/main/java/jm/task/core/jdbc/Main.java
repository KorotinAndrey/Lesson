package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Коротин", "Андрей", (byte) 31);
        userService.saveUser("Афанасьев", "Олег", (byte) 28);
        userService.saveUser("Плюшкина", "Анна", (byte) 30);
        userService.saveUser("Джордж", "Мартин", (byte) 74);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}