package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Cookie Demo</title></head>");
        out.println("<body>");

        out.println("<h1>Cookie Demo</h1>");

        out.println("<form action='" + request.getContextPath() + "/cookie' method='post'>");

        out.println("<label>Enter Username:</label>");
        out.println("<input type='text' name='username' required>");

        out.println("<br><br>");

        out.println("<button type='submit'>Create Cookie</button>");

        out.println("</form>");

        out.println("<br>");

        out.println("<a href='" + request.getContextPath() + "/home'>Back to Home</a>");

        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");

        Cookie cookie = new Cookie("username", username);

        cookie.setMaxAge(60 * 60);

        response.addCookie(cookie);

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Cookie Created</title></head>");
        out.println("<body>");

        out.println("<h1>Cookie Created Successfully</h1>");
        out.println("<p>Username: " + username + "</p>");

        out.println("<br>");

        out.println("<a href='" + request.getContextPath() + "/home'>Back to Home</a>");

        out.println("</body>");
        out.println("</html>");
    }
}