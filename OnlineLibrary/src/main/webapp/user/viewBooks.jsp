<%@ page import="com.library.service.LibraryData" %>
<%@ page import="com.library.model.Book" %>
<%@ page import="com.library.model.User" %>
<%@ page import="java.util.*" %>
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
        input[type=submit] { padding: 5px 10px; margin-right: 5px; }
        h2 { margin-top: 20px; }
        p.welcome { margin-top: 20px; }
    </style>
</head>
<body>

<%
    User currentUser = (User) session.getAttribute("user");
    if(currentUser != null) {
%>
    <!-- Welcome and Dashboard -->
    <p class="welcome">
        Welcome, <b><%= currentUser.getName() %></b>! | 
        <a href="dashboard.jsp">Dashboard</a>
    </p>

    <h2>Available Books</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Available Qty</th>
            <th>Actions</th>
        </tr>
<%
        for(Book b : LibraryData.books){

            // Check how many copies the current user has borrowed
            int borrowedCount = 0;
            List<Book> borrowedBooks = LibraryData.userBorrowedBooks.get(currentUser.getEmail());
            if(borrowedBooks != null){
                for(Book ub : borrowedBooks){
                    if(ub.getId() == b.getId()){
                        borrowedCount++;
                    }
                }
            }
%>
        <tr>
            <td><%= b.getId() %></td>
            <td><%= b.getTitle() %></td>
            <td><%= b.getAuthor() %></td>
            <td><%= b.getQuantity() %></td>
            <td>
                <!-- Borrow Button -->
                <form style="display:inline" action="<%=request.getContextPath()%>/BorrowServlet" method="post">
                    <input type="hidden" name="bookId" value="<%= b.getId() %>">
                    <input type="submit" value="Borrow" <%= (b.getQuantity() == 0) ? "disabled" : "" %>>
                </form>

                <!-- Return Button -->
                <form style="display:inline" action="<%=request.getContextPath()%>/ReturnServlet" method="post">
                    <input type="hidden" name="bookId" value="<%= b.getId() %>">
                    <input type="submit" value="Return" <%= (borrowedCount == 0) ? "disabled" : "" %>>
                </form>

                <span>(Borrowed: <%= borrowedCount %>)</span>
            </td>
        </tr>
<%
        }
%>
    </table>
<%
    } else {
%>
    <p>Please <a href="<%=request.getContextPath()%>/login.jsp">login</a> to borrow or return books.</p>
<%
    }
%>

</body>
</html>
