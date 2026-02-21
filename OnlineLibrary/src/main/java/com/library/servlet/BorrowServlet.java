package com.library.servlet;

import com.library.model.Book;
import com.library.model.User;
import com.library.service.LibraryData;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class BorrowServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user != null) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));

            // Find the book in library
            for(Book b : LibraryData.books){
                if(b.getId() == bookId && b.getQuantity() > 0){
                    // Reduce quantity in library
                    b.setQuantity(b.getQuantity() - 1);

                    // Add to user's borrowed list
                    LibraryData.userBorrowedBooks.putIfAbsent(user.getEmail(), new ArrayList<>());
                    LibraryData.userBorrowedBooks.get(user.getEmail()).add(
                            new Book(b.getId(), b.getTitle(), b.getAuthor(), 1)
                    ); // track one copy borrowed
                    break;
                }
            }
        }
        response.sendRedirect("user/viewBooks.jsp"); // redirect to updated page
    }
}
