
package net.javaguides.usermanagement.web;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.javaguides.usermanagement.model.movies;
public class chooseCity extends HttpServlet {

    
   

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String cityName = request.getParameter("city");
        
        String getCityDetails = "Select * from city where cityName = ?";
        Integer cityID = 0;
        String cityUrl = "";
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(chooseCity.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(getCityDetails)) {
            
            preparedStatement.setString(1, cityName);
            
            System.out.println(preparedStatement);
            
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                cityID = rs.getInt(1);
                cityUrl = rs.getString(3);
            }
            
            
            

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        
        String getCityMovies = "Select * from moviedb where cityID = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(getCityMovies)) {
            
            preparedStatement.setInt(1, cityID);
            
            System.out.println(preparedStatement);
            ArrayList<movies> mov = new ArrayList<movies>();
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                mov.add(new movies(rs.getInt(1),rs.getString(2),rs.getString(4),rs.getString(3)));
            }
            
            session.setAttribute("data", mov); 
            

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        
        session.setAttribute("cityID",cityID);
        session.setAttribute("cityName", cityName);
        session.setAttribute("cityUrl",cityUrl);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp"); 
        rd.forward(request,response);
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void printSQLException(SQLException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
