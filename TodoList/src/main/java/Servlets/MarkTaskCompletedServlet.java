package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.TodoDAO;
import DAO.TodoDAOImpl;


@WebServlet("/MarkTaskCompletedServlet")
public class MarkTaskCompletedServlet extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out=response.getWriter();
    HttpSession session=request.getSession();
    ServletContext context=getServletContext();
    
    int regId=Integer.parseInt(request.getParameter("regId"));
    int taskId=Integer.parseInt(request.getParameter("taskId"));
    
    TodoDAO dao=TodoDAOImpl.getInstance();
    boolean flag=dao.markTaskCompleted(regId, taskId);
    if(flag)
      context.getRequestDispatcher("/ViewTasks.jsp").forward(request, response);
    else
      out.println("TX Failed, Task not marked");
  }

}