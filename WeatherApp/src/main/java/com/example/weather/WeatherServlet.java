package com.example.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/weather")
public class WeatherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String API_KEY = "91e96058ab7aa7a66f737ba5aa852da1"; // Replace with working API key

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("city");
        if (city == null || city.trim().isEmpty()) {
            // Minimalist error response for missing city
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<p style='font-family: Times New Roman, serif; text-align: center; color: #8D6E63; margin-top: 50px; font-size: 1.2em;'>⚠️ Please provide a city.</p>");
            return;
        }

        String urlString = "https://api.openweathermap.org/data/2.5/weather?q="
                + java.net.URLEncoder.encode(city, "UTF-8")
                + "&appid=" + API_KEY + "&units=metric";

        response.setContentType("text/html;charset=UTF-8");
        try {
            // ... (API call and parsing remains the same) ...
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder apiResponse = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                apiResponse.append(inputLine);
            }
            in.close();
            conn.disconnect();

            String responseStr = apiResponse.toString();
            
            // Handle City Not Found (404) error - STYLED TO MATCH BEIGE/BROWN
            if (responseStr.contains("\"cod\":\"404\"")) {
                StringBuilder errorOut = new StringBuilder();
                errorOut.append("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>City Not Found</title>");
                errorOut.append("<style>body { font-family: 'Times New Roman', serif; background: linear-gradient(to bottom, #F5F5DC, #FAF0E6); display:flex; justify-content:center; align-items:center; min-height:100vh; margin:0; }");
                errorOut.append(".error-box { background-color: #FFFFFF; padding: 40px; border-radius: 12px; box-shadow: 0 5px 15px rgba(0,0,0,0.1); text-align:center; border: 1px solid #BCAAA4; }");
                errorOut.append(".error-box h2 { color: #8D6E63; margin-bottom: 10px; font-weight: 300; }");
                errorOut.append(".error-box p { font-size: 1.1em; color: #4B3832; }");
                errorOut.append("a { text-decoration:none; color:#FFFFFF; background-color:#A1887F; padding:10px 20px; border-radius:6px; display:inline-block; margin-top:20px; font-weight: bold; transition: 0.3s; }");
                errorOut.append("a:hover { background-color:#8D6E63; }</style></head><body>");
                errorOut.append("<div class='error-box'>");
                errorOut.append("<h2>City Not Found...</h2>");
                errorOut.append("<p>Could not retrieve data for **" + city + "**.</p>");
                errorOut.append("<a href='index.jsp'>Search Again</a>");
                errorOut.append("</div></body></html>");
                response.getWriter().println(errorOut.toString());
                return;
            }

            // Manual parsing (extract main data)
            String temp = responseStr.split("\"temp\":")[1].split(",")[0];
            String feels_like = responseStr.split("\"feels_like\":")[1].split(",")[0];
            String humidity = responseStr.split("\"humidity\":")[1].split(",")[0];
            String description = responseStr.split("\"description\":\"")[1].split("\"")[0];

            // Print full HTML with calm background and styled box - STYLED TO MATCH BEIGE/BROWN
            StringBuilder out = new StringBuilder();
            out.append("<!DOCTYPE html>");
            out.append("<html>");
            out.append("<head>");
            out.append("<meta charset='UTF-8'>");
            out.append("<title>Weather Result</title>");
            out.append("<style>");
            // Background is now the warm beige gradient
            out.append("body { font-family: 'Times New Roman', serif; background: linear-gradient(to bottom, #F5F5DC, #FAF0E6); display:flex; justify-content:center; align-items:center; min-height:100vh; margin:0; }");
            out.append(".weather-box { background-color: #FFFFFF; padding: 45px 55px; border-radius: 12px; box-shadow: 0 8px 25px rgba(0,0,0,0.15); text-align:center; border: 1px solid #E0E0E0; }");
            out.append(".weather-box h2 { color: #8D6E63; margin-bottom: 20px; font-size: 32px; border-bottom: 2px solid #E0E0E0; padding-bottom: 15px; font-weight: 300; letter-spacing: 2px; }");
            out.append(".weather-box p { margin:15px 0; font-size:1.2em; color:#4B3832; }");
            out.append(".weather-box strong { color: #A1887F; font-weight: bold; font-size: 1.1em; }");
            out.append(".temperature-data { font-size: 1.5em; color: #4B3832; font-weight: bold; margin: 20px 0; }");
            out.append("a { text-decoration:none; color:#FFFFFF; background-color:#A1887F; padding:12px 25px; border-radius:6px; display:inline-block; margin-top:30px; transition: 0.3s; font-weight: bold; letter-spacing: 1px; }");
            out.append("a:hover { background-color:#8D6E63; transform: translateY(-1px); }");
            out.append("</style>");
            out.append("</head>");
            out.append("<body>");
            out.append("<div class='weather-box'>");
            out.append("<h2>" + city.toUpperCase() + "</h2>");
            out.append("<p class='temperature-data'>Current Temp: " + temp + " °C</p>");
            out.append("<p>Feels Like: <strong>" + feels_like + " °C</strong></p>");
            out.append("<p>Humidity: <strong>" + humidity + " %</strong></p>");
            out.append("<p>Weather: <strong>" + description + "</strong></p>");
            out.append("<a href='index.jsp'>New Search</a>");
            out.append("</div>");
            out.append("</body>");
            out.append("</html>");

            response.getWriter().println(out.toString());

        } catch (Exception e) {
            // Styled generic error response - STYLED TO MATCH BEIGE/BROWN
            StringBuilder errorOut = new StringBuilder();
            errorOut.append("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Error</title>");
            errorOut.append("<style>body { font-family: 'Times New Roman', serif; background: linear-gradient(to bottom, #F5F5DC, #FAF0E6); display:flex; justify-content:center; align-items:center; min-height:100vh; margin:0; }");
            errorOut.append(".error-box { background-color: #FFFFFF; padding: 40px; border-radius: 12px; box-shadow: 0 5px 15px rgba(0,0,0,0.1); text-align:center; border: 1px solid #BCAAA4; }");
            errorOut.append(".error-box h2 { color: #8D6E63; margin-bottom: 10px; font-weight: 300; }");
            errorOut.append(".error-box p { font-size: 1.1em; color: #4B3832; }");
            errorOut.append("a { text-decoration:none; color:#FFFFFF; background-color:#A1887F; padding:10px 20px; border-radius:6px; display:inline-block; margin-top:20px; font-weight: bold; transition: 0.3s; }");
            errorOut.append("a:hover { background-color:#8D6E63; }</style></head><body>");
            errorOut.append("<div class='error-box'>");
            errorOut.append("<h2>An Application Error Occurred</h2>");
            errorOut.append("<p>Please try again or check your API key.</p>");
            errorOut.append("<a href='index.jsp'>Go Back</a>");
            errorOut.append("</div></body></html>");
            response.getWriter().println(errorOut.toString());
        }
    }
}