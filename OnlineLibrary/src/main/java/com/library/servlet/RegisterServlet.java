package com.library.servlet;

import com.library.model.User;
import com.library.service.LibraryData;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Default role = user
        LibraryData.users.add(new User(name, email, password, "user"));
        response.getWriter().println("Registration successful! <a href='login.jsp'>Login here</a>");
    }
}
