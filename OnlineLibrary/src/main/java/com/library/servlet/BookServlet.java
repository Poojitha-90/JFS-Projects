package com.library.servlet;

import com.library.model.Book;
import com.library.service.LibraryData;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class BookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        int id = LibraryData.books.size() + 1;
        LibraryData.books.add(new Book(id, title, author, quantity));

        response.sendRedirect("viewBooks.jsp"); // shows all books
    }
}
