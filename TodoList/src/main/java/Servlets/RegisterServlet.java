package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Register;
import DAO.TodoDAO;
import DAO.TodoDAOImpl;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  response.setContentType("text/html");
  PrintWriter out = response.getWriter();

  // read form data
  String fname = request.getParameter("FName").trim();
  String lname = request.getParameter("LName").trim();
  String email = request.getParameter("Email").trim();
  String pass = request.getParameter("Pass").trim();
  long mobile = Long.parseLong(request.getParameter("Mobile").trim());
  String address = request.getParameter("Address").trim();

  // call the dao method
  TodoDAO dao = TodoDAOImpl.getInstance();
  Register r = new Register(0, fname, lname, email, pass, mobile, address);
  int i = dao.register(r);
  if (i > 0)
   response.sendRedirect("Login.jsp");
  else
   out.println("registration Failed");
 }
}