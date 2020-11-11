package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.javaguides.usermanagement.model.User;

public class UserDao {

    public int registerUser(User user) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO user" +
            "  (full_name, email, phone_number, password) VALUES " +
            " (?, ?, ?, ?);";
        
        
        
        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "root", "");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            
            preparedStatement.setString(1, user.getfullName());
            preparedStatement.setString(2, user.getemail());
            preparedStatement.setString(3, user.getphoneNumber());
            preparedStatement.setString(4, user.getPassword());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }
    
    public String getStoredPassword(User user) throws ClassNotFoundException {
        String SELECT_USERS_SQL = "SELECT * FROM user WHERE email=?";
        

        Class.forName("com.mysql.jdbc.Driver");
        String password = "";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "root", "");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)) {
            ResultSet rs = null;
            preparedStatement.setString(1, user.getemail());
           
            

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                password = rs.getString(5);
            }
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return password;
        
    }
    
    public String findUser(User user) throws ClassNotFoundException {
        String FIND_USER_SQL = "SELECT * FROM user WHERE email=?";
        String result = "";

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "root", "");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_SQL)) {
            ResultSet rs = null;
            preparedStatement.setString(1, user.getemail());
           
            

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                result = rs.getString(2);
            }
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
        
    }
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}