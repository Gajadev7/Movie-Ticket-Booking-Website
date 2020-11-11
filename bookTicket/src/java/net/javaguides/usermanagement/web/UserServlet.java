package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.javaguides.usermanagement.dao.UserDao;
import net.javaguides.usermanagement.model.User;
import net.javaguides.usermanagement.model.Password;


@WebServlet("/register")
public class UserServlet extends HttpServlet {
    
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phoneNumber = (request.getParameter("phoneNumber"));
        String password = request.getParameter("password");
        String hashedPassword = "";
        try {
            hashedPassword = Password.getSaltedHash(password);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user = new User();
        user.setfullName(fullName);
        user.setemail(email);
        user.setphoneNumber(phoneNumber);
        user.setPassword(hashedPassword);

        try {
            userDao.registerUser(user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Integer userID=0;
        String getUserID = "select id from user where email = ?";
        ResultSet rs = null;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "root", "");
                PreparedStatement preparedStatement = connection.prepareStatement(getUserID)) {
            preparedStatement.setString(1, email);
            System.out.println(preparedStatement);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                userID = rs.getInt(1);
            }
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        
        
        HttpSession session = request.getSession();
        session.setAttribute("userName", fullName);
        session.setAttribute("userID", userID);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp"); 
        rd.forward(request,response);
       
        
    }

    private void printSQLException(SQLException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}