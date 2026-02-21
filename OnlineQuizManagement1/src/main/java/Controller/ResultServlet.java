package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResultServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int score = 0;
        int total = Integer.parseInt(request.getParameter("total"));

        for (int i = 1; i <= total; i++) {
            String correct = request.getParameter("correct" + i);
            String answer = request.getParameter("ans" + i);
            if (correct != null && correct.equals(answer)) {
                score++;
            }
        }

        request.setAttribute("score", score);
        request.setAttribute("total", total);
        RequestDispatcher rd = request.getRequestDispatcher("/Result.jsp");
        rd.forward(request, response);
    }
}