<%@ page import="java.util.*" %>
<%
List<Map<String, String>> questions = (List<Map<String, String>>) request.getAttribute("questions");
int i = 1;
%>

<form action="ResultServlet" method="post">
<input type="hidden" name="total" value="<%=questions.size()%>">
<% for (Map<String, String> q : questions) { %>
    <b>Q<%=i%>. <%=q.get("question")%></b><br>
    <input type="radio" name="ans<%=i%>" value="<%=q.get("option1")%>"> <%=q.get("option1")%><br>
    <input type="radio" name="ans<%=i%>" value="<%=q.get("option2")%>"> <%=q.get("option2")%><br>
    <input type="radio" name="ans<%=i%>" value="<%=q.get("option3")%>"> <%=q.get("option3")%><br>
    <input type="radio" name="ans<%=i%>" value="<%=q.get("option4")%>"> <%=q.get("option4")%><br>
    <input type="hidden" name="correct<%=i%>" value="<%=q.get("correct_answer")%>">
    <br>

<% i++; } %>
<input type="submit" value="Submit Quiz">
</form>