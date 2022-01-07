package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
            Statement stmt = connection.createStatement()) {

            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100) NOT NULL, lastName VARCHAR(100) NOT NULL, age INT NOT NULL);");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
            Statement stmt = connection.createStatement()) {

            stmt.executeUpdate("DROP TABLE IF EXISTS user");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement prStmt = connection.prepareStatement("INSERT INTO user(name, lastname, age) VALUES(?, ?, ?)")) {


            prStmt.setString(1, name);
            prStmt.setString(2, lastName);
            prStmt.setByte(3, age);

            prStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User с именем – "+ name +" добавлен в базу данных ");
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement prStmt = connection.prepareStatement("DELETE FROM user WHERE id=?")) {

            prStmt.setLong(1, id);

            prStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement()) {

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
        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.executeUpdate("DELETE FROM user");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
