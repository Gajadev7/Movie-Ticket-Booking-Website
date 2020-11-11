<%@page import="java.util.ArrayList"%>
<%@page import="net.javaguides.usermanagement.model.bookedSeats"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket</title>
    </head>
    <body>
        <h1></h1>
        <%
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null; 
                ResultSet rs1 = null; 
                
                
                Integer bookingID = (Integer)session.getAttribute("bookingID");
                Integer cityID = (Integer)session.getAttribute("bookedCityID") ;
                Integer theatreID = (Integer)session.getAttribute("bookedTheatreID") ;
                Integer movieID = (Integer)session.getAttribute("bookedMovieID") ;
                Integer userID = (Integer)session.getAttribute("userID");         
                
                String[] seats = {};
                Integer i=0;
                String userName = (String)session.getAttribute("userName");
                String cityName = (String)session.getAttribute("cityName");
                String theatreName = (String)session.getAttribute("chosenTheatre");
                String movieName = "";
                String date = (String)session.getAttribute("date");
                String timeslot = (String)session.getAttribute("timeslot");
                String movieImageUrl = "";
                ArrayList<bookedSeats> gbs = new ArrayList<bookedSeats>();
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket","root","");
                    ps = conn.prepareStatement("select * from moviedb where movieID = ?");
                    ps.setInt(1, movieID);
                    rs = ps.executeQuery();
                    if (rs.next()){
                       movieName = rs.getString(2);
                       movieImageUrl = rs.getString(4);
                    }
                    ps = conn.prepareStatement("select bookedSeats from `"+cityID+theatreID+movieID+"bookedSeats` where bookingID = ?");
                    ps.setInt(1, bookingID);
                    rs1 = ps.executeQuery();
                    while (rs1.next()) {
                            gbs.add(new bookedSeats(rs1.getString(1)));
                            i++;
                    }
                    
                    System.out.println(gbs);
                }
                catch(Exception e){
                    out.println(e+"hello");
                }
                ps.close();
                rs.close();
                conn.close();
            
        %>
        
        <h3><%= userName %></h3>
        <h3><%= cityName %></h3>
        <h3><%= theatreName %></h3>
        <h3><%= movieName %></h3>
        <h3><%= date %></h3>
        <h3><%= timeslot %></h3>
        <img src="<%=movieImageUrl%>" width="150" height="150">
        <h3>Booked Seats : </h3>
        <%  for( bookedSeats bs : gbs) { %>
        <span><%=bs.getbookedSeat()%></span>
        <%}%>
    </body>
</html>
