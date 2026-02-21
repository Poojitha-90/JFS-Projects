import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out=response.getWriter();
    HttpSession session=request.getSession();
    // read the first form
    if(Integer.parseInt(request.getParameter("formno"))==2) {
      String email=request.getParameter("email").trim();
      String pass=request.getParameter("pass").trim();
      
      // storing form data into HttpSession object is called 
      // as Client State Persistence
      session.setAttribute("email", email);
      session.setAttribute("pass", pass);
      
      // sending HTML file from servlet to browser
      response.sendRedirect("./HTML3.html");
    }
  }

}