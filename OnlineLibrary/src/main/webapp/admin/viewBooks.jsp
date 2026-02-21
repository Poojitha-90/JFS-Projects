<%@ page import="com.library.service.LibraryData" %>
<%@ page import="com.library.model.Book" %>
<%@ page import="com.library.model.User" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Available Books</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin-top: 20px; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        input[type=submit] { padding: 5px 10px; }
        h2 { margin-top: 20px; }
    </style>
</head>
<body>

<h2>Available Books</h2>

<%
    User currentUser = (User) session.getAttribute("user");

    if(currentUser != null) {
%>
    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Quantity</th>
            <th>Action</th>
        </tr>
<%
        for(Book b : LibraryData.books) {
%>
        <tr>
            <td><%= b.getId() %></td>
            <td><%= b.getTitle() %></td>
            <td><%= b.getAuthor() %></td>
            <td><%= b.getQuantity() %></td>
            <td>
                <form action="../BorrowServlet" method="post">
                    <input type="hidden" name="bookId" value="<%= b.getId() %>">
                    <input type="submit" value="Borrow" <%= (b.getQuantity() == 0) ? "disabled" : "" %>>
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
    <p>Please <a href="../login.jsp">login</a> first to view available books.</p>
<%
    }
%>

</body>
</html>
