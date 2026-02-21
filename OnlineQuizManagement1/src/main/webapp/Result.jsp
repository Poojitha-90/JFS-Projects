<%
int score = (Integer) request.getAttribute("score");
int total = (Integer) request.getAttribute("total");
%>
<h2>Quiz Completed!</h2>
<p>Your Score: <%=score%> / <%=total%></p>
<a href="index.html">Take Again</a>