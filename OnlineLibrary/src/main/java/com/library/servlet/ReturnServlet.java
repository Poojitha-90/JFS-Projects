package com.library.servlet;

import com.library.model.Book;
import com.library.model.User;
import com.library.service.LibraryData;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class ReturnServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user != null) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));

            List<Book> borrowed = LibraryData.userBorrowedBooks.get(user.getEmail());
            if(borrowed != null) {
                // Find first borrowed copy and remove it
                for(int i=0; i<borrowed.size(); i++){
                    Book b = borrowed.get(i);
                    if(b.getId() == bookId){
                        borrowed.remove(i); // remove one copy
                        
                        // Add back to library quantity
                        for(Book libBook : LibraryData.books){
                            if(libBook.getId() == bookId){
                                libBook.setQuantity(libBook.getQuantity() + 1);
                                break;
                            }
                        }
                        break; // only remove one copy at a time
                    }
                }
            }
        }
        response.sendRedirect("user/viewBooks.jsp"); // redirect to updated page
    }
}
