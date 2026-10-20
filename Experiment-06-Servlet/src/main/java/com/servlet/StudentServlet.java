package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String branch = request.getParameter("branch");
        String email = request.getParameter("email");
        String age = request.getParameter("age");

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<body>");

        out.println("<h1>Student Details</h1>");

        out.println("<p>Name: " + name + "</p>");
        out.println("<p>Branch: " + branch + "</p>");
        out.println("<p>Email: " + email + "</p>");
        out.println("<p>Age: " + age + "</p>");

        out.println("<br>");
        out.println("<a href='home'>Back to Home</a>");

        out.println("</body>");
        out.println("</html>");
    }
}