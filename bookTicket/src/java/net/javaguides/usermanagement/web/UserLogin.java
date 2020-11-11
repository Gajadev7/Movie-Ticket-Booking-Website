package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import net.javaguides.usermanagement.dao.UserDao;
import net.javaguides.usermanagement.model.Password;
import net.javaguides.usermanagement.model.User;

@WebServlet("/signin")
public class UserLogin extends HttpServlet {
    private UserDao userDao;

    /**
     *
     */
    @Override
    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        String storedPW = "";
        String userName = "";
        boolean loggedIn = false;
        
        User user = new User();
        user.setemail(email);
        
        
        try {
            storedPW = userDao.getStoredPassword(user);
            loggedIn = Password.check(password,storedPW);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            userName = userDao.findUser(user);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
       if(loggedIn == true){
           Integer userID=0;
            String getUserID = "select * from user where email = ?";
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
            session.setAttribute("userName", userName);
            session.setAttribute("userID", userID);
             RequestDispatcher rd = request.getRequestDispatcher("index.jsp"); 
             rd.forward(request,response);
        
        }
        else{
            request.setAttribute("message", "*Password does not match");
            RequestDispatcher rt = request.getRequestDispatcher("userLogin.jsp"); 
            rt.forward(request,response);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void printSQLException(SQLException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
