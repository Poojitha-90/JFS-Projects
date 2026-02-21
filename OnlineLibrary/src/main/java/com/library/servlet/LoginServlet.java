package com.library.servlet;

import com.library.model.User;
import com.library.service.LibraryData;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User loginUser = null;
        for(User u : LibraryData.users){
            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                loginUser = u;
                break;
            }
        }

        if(loginUser != null){
            HttpSession session = request.getSession();
            session.setAttribute("user", loginUser);
            if(loginUser.getRole().equals("admin")){
                response.sendRedirect("admin/dashboard.jsp");
            } else {
                response.sendRedirect("user/dashboard.jsp");
            }
        } else {
            response.getWriter().println("Invalid email or password!");
        }
    }
}
