import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        // read the first form
        if (Integer.parseInt(request.getParameter("formno")) == 1) {
         
         
         
            String fname = request.getParameter("fname").trim();
            String lname = request.getParameter("lname").trim();

            // storing form data into HttpSession object 
            session.setAttribute("fname", fname);
            session.setAttribute("lname", lname);

            // sending HTML file from servlet to browser
            response.sendRedirect("./HTML2.html");
        }
    }
}