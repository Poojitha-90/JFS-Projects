import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Servlet3")
public class Servlet3 extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out=response.getWriter();
    HttpSession session=request.getSession();
    // read the first form
    if(Integer.parseInt(request.getParameter("formno"))==3) {
      
      long mobile=Long.parseLong(request.getParameter("mobile").trim());
      String address=request.getParameter("address").trim();
      
      // we need to retrieve first and second form data from HttpSession object
      String fname=session.getAttribute("fname").toString();
      String lname=session.getAttribute("lname").toString();
      String email=session.getAttribute("email").toString();
      String pass=session.getAttribute("pass").toString();
      
      try {
        // establish connection to DB
        /*Class.forName("com.mysql.jdbc.Driver");
        Connection con=
            DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Servletdb", "root", "");*/
       Class.forName("oracle.jdbc.driver.OracleDriver");
   Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "padmaja", "padmaja");
        
     Statement stmt=con.createStatement();
        PreparedStatement pstmt=con.prepareStatement(
            "INSERT INTO regservlet VALUES (?,?,?,?,?,?,?)");
            
        // generate PK
        int regid=0;
        ResultSet rs=stmt.executeQuery("select max(regid) from regservlet");
        if(rs.next()) {
          regid=rs.getInt(1);
        }
        regid++;
        
        // insert record into DB
        pstmt.setInt(1, regid);
        pstmt.setString(2, fname);
        pstmt.setString(3, lname);
        pstmt.setString(4, email);
        pstmt.setString(5, pass);
        pstmt.setLong(6, mobile);
        pstmt.setString(7, address);
        int i=pstmt.executeUpdate();
        
        // send response to browser
        if(i==1)
          out.println("Registration Successful, Regid is: "+regid);
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
  }
}