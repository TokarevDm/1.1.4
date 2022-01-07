package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

       UserService us = new UserServiceImpl();

       us.createUsersTable();

       us.saveUser("Donatelo", "Staff", (byte) 21);
       us.saveUser("Rafael", "Sais", (byte) 21);
       us.saveUser("Michelangelo", "Nunchucks", (byte) 21);
       us.saveUser("Leonardo", "Swords", (byte) 21);

       System.out.println(us.getAllUsers());

       us.cleanUsersTable();

       us.dropUsersTable();

    }
}
