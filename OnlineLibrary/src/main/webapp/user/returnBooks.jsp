<%@ page import="com.library.service.LibraryData" %>
<%@ page import="com.library.model.Book" %>
<%@ page import="com.library.model.User" %>
<%@ page import="java.util.*" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Return Borrowed Books</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin-top: 20px; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        input[type=submit] { padding: 5px 10px; }
        h2 { margin-top: 20px; }
    </style>
</head>
<body>

<h2>Return Borrowed Books</h2>

<%
    User currentUser = (User) session.getAttribute("user");

    if(currentUser != null) {
        String email = currentUser.getEmail();
        List<Book> borrowed = LibraryData.userBorrowedBooks.get(email);

        if(borrowed != null && !borrowed.isEmpty()) {
%>
            <table>
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Return</th>
                </tr>
<%
            for(Book b : borrowed) {
%>
                <tr>
                    <td><%= b.getTitle() %></td>
                    <td><%= b.getAuthor() %></td>
                    <td>
                        <form action="../ReturnServlet" method="post">
                            <input type="hidden" name="bookId" value="<%= b.getId() %>">
                            <input type="submit" value="Return">
                        </form>
                    </td>
                </tr>
<%
            }
%>
            </table>
<%
        } else {
%>
            <p>No borrowed books found.</p>
<%
        }
    } else {
%>
    <p>Please <a href="../login.jsp">login</a> first to view borrowed books.</p>
<%
    }
%>

</body>
</html>
