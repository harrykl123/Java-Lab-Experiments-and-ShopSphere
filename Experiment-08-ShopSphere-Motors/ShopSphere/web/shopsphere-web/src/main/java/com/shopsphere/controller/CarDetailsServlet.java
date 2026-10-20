package com.shopsphere.controller;

import com.shopsphere.dao.CarDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/car-details")
public class CarDetailsServlet extends HttpServlet {
    private final CarDAO carDAO = new CarDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            var car = carDAO.findById(id);
            if (car == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Car not found.");
                return;
            }
            request.setAttribute("car", car);
            request.getRequestDispatcher("/car-details.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid car ID.");
        } catch (RuntimeException e) {
            throw new ServletException("Unable to load car details.", e);
        }
    }
}
