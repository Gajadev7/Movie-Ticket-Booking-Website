package net.javaguides.usermanagement.web;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.javaguides.usermanagement.model.bookedSeats;

public class bookTicket extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer cityID;
        cityID = (Integer) session.getAttribute("cityID");
        Integer theatreID = Integer.parseInt(request.getParameter("theatreID"));
        Integer movieID = (Integer) session.getAttribute("mpmovieID");

        String date = (String) session.getAttribute("date");
        String time = (String) session.getAttribute("timeslot");
        String theatreName = "";
        String getTheatreDetails = "Select * from theatre where theatreID = ?";

        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "root", "");
                PreparedStatement preparedStatement = connection.prepareStatement(getTheatreDetails)) {
            preparedStatement.setInt(1, theatreID);
            System.out.println(preparedStatement);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                theatreName = rs.getString(2);
            }
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        //checking whether table exists
        String checkingTableExistence = "SELECT * FROM `information_schema`.`INNODB_SYS_TABLES` WHERE  name = 'ticket/" + cityID + theatreID + movieID + "' LIMIT 1";
        String createbookingTable = "CREATE TABLE `ticket`.`" + cityID + theatreID + movieID + "` ( `bookingID` INT AUTO_INCREMENT NOT NULL , `date` VARCHAR(50) NOT NULL , `timeslot` VARCHAR(50) NOT NULL , `noOfSeats` INT NOT NULL , `userID` INT NOT NULL, PRIMARY KEY (`bookingID`)) ENGINE = InnoDB;";
        String createbookedSeatsTable = "CREATE TABLE `ticket`.`" + cityID + theatreID + movieID + "bookedSeats" + "` ( `bookingID` INT NOT NULL , `bookedSeats` VARCHAR(3) NOT NULL) ENGINE = InnoDB;";
        String checkDateTime = "Select * from `" + cityID + theatreID + movieID + "` where date = ? and timeslot = ?";
        String selectBookedSeats = "Select * from `ticket`.`" + cityID + theatreID + movieID + "bookedseats`";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "root", "");
                PreparedStatement preparedStatement1 = connection.prepareStatement(checkingTableExistence)) {
            System.out.println(preparedStatement1);
            rs1 = preparedStatement1.executeQuery();
            //System.out.println(rs1.next());
            if (rs1.next()) {
                //String checkDateTime = "Select * from '" + cityID + theatreID + movieID + "' where date = ? & time = ?";
                PreparedStatement preparedStatement2 = connection.prepareStatement(checkDateTime);
                preparedStatement2.setString(1, date);
                preparedStatement2.setString(2, time);
                System.out.println(preparedStatement2);
                rs2=preparedStatement2.executeQuery();
                if (!rs2.next()) {
                    String truncatebookingTable = "Truncate table `"+cityID+theatreID+movieID+"`";
                    String truncatebookedSeatTable = "Truncate table `"+cityID+theatreID+movieID+"bookedSeats`";
                    PreparedStatement preparedStatement3 = connection.prepareStatement(truncatebookingTable);
                    System.out.println(preparedStatement3);
                    preparedStatement3.executeUpdate();
                    PreparedStatement preparedStatement4 = connection.prepareStatement(truncatebookedSeatTable);
                    System.out.println(preparedStatement4);
                    preparedStatement4.executeUpdate();
                }
                PreparedStatement preparedStatement5 = connection.prepareStatement(selectBookedSeats);
                System.out.println(preparedStatement5);
                rs3 = preparedStatement5.executeQuery();
                ArrayList<bookedSeats> bs = new ArrayList<bookedSeats>();
                while (rs3.next()) {
                    bs.add(new bookedSeats(rs3.getString(2)));
                }
                Gson gson = new Gson();
                String bookedSeatData = gson.toJson(bs);
                
                session.setAttribute("bookedSeatData", bookedSeatData);
            } else {
                PreparedStatement preparedStatement6 = connection.prepareStatement(createbookingTable);
                {
                    System.out.println(preparedStatement6);
                    preparedStatement6.executeUpdate();
                }
                PreparedStatement preparedStatement7 = connection.prepareStatement(createbookedSeatsTable);
                {
                    System.out.println(preparedStatement7);
                    preparedStatement7.executeUpdate();
                }

            }
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        session.setAttribute("chosenTheatre",theatreName);
        session.setAttribute("bookingCity", cityID);
        session.setAttribute("bookingTheatre", theatreID);
        session.setAttribute("bookingMovie", movieID);
        response.sendRedirect("bookTicket.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String seatsString = request.getParameter("seats");
        String[] seats = seatsString.split(",",0);
        Integer cityID = (Integer) session.getAttribute("bookingCity");
        Integer theatreID = (Integer) session.getAttribute("bookingTheatre");
        Integer movieID = (Integer) session.getAttribute("bookingMovie");

        Integer userID = (Integer)(session.getAttribute("userID"));
        
        String date = (String) session.getAttribute("date");
        String time = (String) session.getAttribute("timeslot");
        
        
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        
        Integer noOfSeats = seats.length;
        String insertbookingDetails = "Insert into `" + cityID + theatreID + movieID + "` (date,timeslot,noOfSeats,userID)values(?,?,?,?)";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "root", "");
            PreparedStatement preparedStatement1 = connection.prepareStatement(insertbookingDetails)){
            preparedStatement1.setString(1, date);
            preparedStatement1.setString(2, time);
            preparedStatement1.setInt(3, noOfSeats);
            preparedStatement1.setInt(4, userID);
            System.out.println(preparedStatement1);
            preparedStatement1.executeUpdate();
        }
        catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        
        
        String gettingBookingId = "select * from `"+ cityID + theatreID + movieID +"` ORDER BY bookingID DESC LIMIT 1";
        Integer bookingID = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "root", "");
            PreparedStatement preparedStatement2 = connection.prepareStatement(gettingBookingId)){
            System.out.println(preparedStatement2);
            rs1 = preparedStatement2.executeQuery();
            if(rs1.next()){
                bookingID = rs1.getInt(1);
            }
        }
        catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        
        String insertbookedSeatsDetails = "Insert into `" + cityID + theatreID + movieID + "bookedSeats` values(?,?)";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "root", "");
            PreparedStatement preparedStatement2 = connection.prepareStatement(insertbookedSeatsDetails)){
            for(int i=0; i<noOfSeats; i++){
                preparedStatement2.setInt(1, bookingID);
                preparedStatement2.setString(2, seats[i]);
                System.out.println(preparedStatement2);
                preparedStatement2.executeUpdate();
            }
        }
        catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        
        session.setAttribute("bookingID", bookingID);
        session.setAttribute("bookedCityID", cityID);
        session.setAttribute("bookedTheatreID", theatreID);
        session.setAttribute("bookedMovieID", movieID);
        response.sendRedirect("ticketPage.jsp");
    }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void printSQLException(SQLException e) {
        throw new UnsupportedOperationException(e); //To change body of generated methods, choose Tools | Templates.
    }

}
