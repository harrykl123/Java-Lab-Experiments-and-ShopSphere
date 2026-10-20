package com.shopsphere.controller;

import com.shopsphere.dao.CarBookingDAO;
import com.shopsphere.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/my-bookings")
public class MyBookingsServlet extends HttpServlet {
    private final CarBookingDAO dao = new CarBookingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || !(session.getAttribute("user") instanceof User user)) {
            response.sendRedirect("login.jsp");
            return;
        }
        try {
            request.setAttribute("bookings", dao.findForUser(user.getId()));
            request.getRequestDispatcher("/my-bookings.jsp").forward(request, response);
        } catch (RuntimeException e) { throw new ServletException("Unable to load bookings.", e); }
    }
}
