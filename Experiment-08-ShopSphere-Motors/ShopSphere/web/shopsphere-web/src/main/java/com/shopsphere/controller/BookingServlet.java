package com.shopsphere.controller;

import com.shopsphere.dao.CarBookingDAO;
import com.shopsphere.dao.CarDAO;
import com.shopsphere.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/book-car")
public class BookingServlet extends HttpServlet {
    private final CarDAO carDAO = new CarDAO();
    private final CarBookingDAO bookingDAO = new CarBookingDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || !(session.getAttribute("user") instanceof User user)) {
            response.sendRedirect("login.jsp");
            return;
        }
        try {
            int carId = Integer.parseInt(request.getParameter("carId"));
            if (carDAO.findById(carId) == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Car not found.");
                return;
            }
            String dateText = request.getParameter("preferredDate");
            Date date = (dateText == null || dateText.isBlank()) ? null : Date.valueOf(dateText);
            String message = request.getParameter("message");
            if (message != null && message.length() > 500) message = message.substring(0, 500);
            bookingDAO.create(user.getId(), carId, date, message);
            response.sendRedirect("my-bookings?success=true");
        } catch (IllegalArgumentException e) {
            response.sendRedirect("car-details?id=" + request.getParameter("carId") + "&error=date");
        } catch (RuntimeException e) {
            throw new ServletException("Unable to create booking. Check that the migration SQL has been run.", e);
        }
    }
}
