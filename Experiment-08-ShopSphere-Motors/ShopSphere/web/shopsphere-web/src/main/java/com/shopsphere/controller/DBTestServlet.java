package com.shopsphere.controller;

import com.shopsphere.util.DBConnection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/db-test")
public class DBTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");

        try (Connection connection = DBConnection.getConnection()) {

            response.getWriter().println("""
                    <html>
                    <head>
                        <title>ShopSphere DB Test</title>
                    </head>
                    <body>
                        <h1>ShopSphere</h1>
                        <h2>Database Connection Successful!</h2>
                        <p>MySQL connection is working.</p>
                    </body>
                    </html>
                    """);

        } catch (Exception e) {

            response.getWriter().println("""
                    <html>
                    <head>
                        <title>ShopSphere DB Test</title>
                    </head>
                    <body>
                        <h1>Database Connection Failed</h1>
                        <pre>
                    """ + e.getMessage() + """
                        </pre>
                    </body>
                    </html>
                    """);
        }
    }
}