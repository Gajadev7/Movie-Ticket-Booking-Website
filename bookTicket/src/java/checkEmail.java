

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class checkEmail extends HttpServlet {
     
     
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            String email = request.getParameter("email");
            
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;  
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            try{
                
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket","root","");
                ps = conn.prepareStatement("select * from user where email = ?");
                ps.setString(1, email);
                rs = ps.executeQuery();
                
                if(rs.next()){
                    out.println(1);
                }
                else{
                    out.println(0);
                }
                ps.close();
                rs.close();
                conn.close();
            }
            catch(Exception e){
                    out.println(e);
            }
           
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
