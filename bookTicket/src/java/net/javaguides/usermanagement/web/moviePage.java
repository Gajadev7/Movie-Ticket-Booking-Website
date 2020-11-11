package net.javaguides.usermanagement.web;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.javaguides.usermanagement.model.Theatre;

public class moviePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         HttpSession session = request.getSession();
        
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String timenow = (String) (tf.format(now));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter monthf = DateTimeFormatter.ofPattern("MM");
        LocalDateTime today = LocalDateTime.now();
        String todate = (String) (df.format(today));
        Integer intmonth = Integer.parseInt(monthf.format(today));
        
        String month = getMonthForInt(intmonth-1);
        String date = todate+", "+month;
        String slot10 = "09" + ":" + "55" + ":" + "00";
        String slot15 = "14" + ":" + "55" + ":" + "00";
        String slot20 = "19" + ":" + "55" + ":" + "00";
        String timeslot = "";
        if (compareTime(timenow, slot10) > 0) {
            timeslot = "10:00 am";
        } else if (compareTime(timenow, slot15) > 0) {
            timeslot = "3:00 pm";
        } else if (compareTime(timenow, slot20) > 0) {
            timeslot = "8:00 pm";
        } else {
            timeslot = "No slots available on ";
        }

        session.setAttribute("timeslot",timeslot);
        session.setAttribute("date",date);
        Integer movieID = Integer.parseInt(request.getParameter("movieID"));

        String getmovieDetails = "Select * from moviedb where movieID = ?";
        String getTheatres = "Select * from theatre where movieID = ?";
        Integer mpmovieID = 0;
        String mpMovieName = "";
        String mpMovieImageUrl = "";
        String mpLikedBy = "";
        String mpMovieDescription = "";
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(chooseCity.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "root", "");
                PreparedStatement preparedStatement = connection.prepareStatement(getmovieDetails)) {
            preparedStatement.setInt(1, movieID);
            System.out.println(preparedStatement);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                mpmovieID= Integer.parseInt(rs.getString(1));
                mpMovieName = rs.getString(2);
                mpMovieImageUrl = rs.getString(4);
                mpLikedBy = rs.getString(3);
                mpMovieDescription = rs.getString(6);
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "root", "");
                PreparedStatement preparedStatement = connection.prepareStatement(getTheatres)) {

            preparedStatement.setInt(1, movieID);

            System.out.println(preparedStatement);
            ArrayList<Theatre> theatre = new ArrayList<Theatre>();
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                theatre.add(new Theatre(rs.getInt(1), rs.getString(2)));
            }

            session.setAttribute("theatreData", theatre);

        } catch (SQLException e) {

            printSQLException(e);
        }
        session.setAttribute("mpmovieID",mpmovieID);
        session.setAttribute("mpMovieName", mpMovieName);
        session.setAttribute("mpMovieImageUrl", mpMovieImageUrl);
        session.setAttribute("mpLikedBy", mpLikedBy);
        session.setAttribute("mpMovieDescription", mpMovieDescription);
        response.sendRedirect("moviePage.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }
    public static long compareTime(String startTimeStr, String endTimeStr) {

		LocalDate today = LocalDate.now();
		String startTimeStrT = today + " " + startTimeStr;
		String endTimeStrT = today + " " + endTimeStr;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                Duration d = null;
        
		try {
                        
			LocalDateTime startTime = LocalDateTime.parse(startTimeStrT,formatter);
			LocalDateTime endTime = LocalDateTime.parse(endTimeStrT, formatter);

			d = Duration.between(startTime, endTime);

			System.out.println("dur " + d.getSeconds());
                        
			
		} catch (DateTimeParseException e) {
			System.out.println("Invalid Input" + e.getMessage());

		}
                return d.getSeconds();
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void printSQLException(SQLException e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
