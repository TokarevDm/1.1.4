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
        try {
            Statement stmt = Util.connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100) NOT NULL, lastName VARCHAR(100) NOT NULL, age INT NOT NULL);");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Statement stmt = Util.connection.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS user");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement prStmt = Util.connection.prepareStatement("INSERT INTO user VALUES(?, ?, ?)");

            prStmt.setString(1, name);
            prStmt.setString(2, lastName);
            prStmt.setByte(3, age);

            prStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement prStmt = Util.connection.prepareStatement("DELETE FROM user WHERE id=?");
            prStmt.setLong(1, id);

            prStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            Statement stmt = Util.connection.createStatement();
            ResultSet rst = stmt.executeQuery("SELECT * FROM user");

            while(rst.next()) {
                User user = new User();

                user.setId(rst.getLong("id"));
                user.setName(rst.getString("name"));
                user.setLastName(rst.getString("lastName"));
                user.setAge(rst.getByte("age"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try {
            Statement stm = Util.connection.createStatement();
            stm.executeUpdate("DELETE FROM user");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
