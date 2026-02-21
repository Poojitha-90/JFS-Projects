package com.library.service;

import com.library.model.Book;
import com.library.model.User;
import java.util.*;

public class LibraryData {
    // List of all users
    public static List<User> users = new ArrayList<>();
    
    // List of all books
    public static List<Book> books = new ArrayList<>();
    
    // Map to track borrowed books per user (key = user email)
    public static Map<String, List<Book>> userBorrowedBooks = new HashMap<>();
    
    static {
        // Default admin account
        users.add(new User("Admin", "admin@library.com", "admin123", "admin"));
        
        // Sample books
        books.add(new Book(1, "Java Basics", "Author A", 5));
        books.add(new Book(2, "Data Structures", "Author B", 3));
        books.add(new Book(3, "Web Development", "Author C", 4));
        books.add(new Book(4, "Database Systems", "Author D", 2));
        // Add more sample books as needed
    }
}
