package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/index.html");
            return;
        }

        String username = (String) session.getAttribute("username");

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Student Home</title></head>");
        out.println("<body>");

        out.println("<h1>Student Home</h1>");
        out.println("<h2>Welcome " + username + "</h2>");
        out.println("<p>You are logged in successfully.</p>");

        out.println("<a href='" + request.getContextPath() + "/student.html'>Student Form</a><br>");
        out.println("<a href='" + request.getContextPath() + "/cookie'>Cookie Demo</a><br>");
        out.println("<a href='" + request.getContextPath() + "/logout'>Logout</a>");

        out.println("</body>");
        out.println("</html>");
    }
}