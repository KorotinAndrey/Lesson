package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
         UserService userService = new UserServiceImpl();

         String testName = "Andrey";
         String testLastName = "Korotin";
         byte testAge = 31;
    }
}