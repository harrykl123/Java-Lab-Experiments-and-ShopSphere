package com.shopsphere.controller;

import com.shopsphere.dao.CarDAO;
import com.shopsphere.model.Car;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/cars")
public class CarsServlet extends HttpServlet {
    private final CarDAO carDAO = new CarDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String keyword = request.getParameter("keyword");
            String category = request.getParameter("category");
            String maxPriceText = request.getParameter("maxPrice");
            BigDecimal maxPrice = null;
            if (maxPriceText != null && !maxPriceText.isBlank()) {
                try {
                    maxPrice = new BigDecimal(maxPriceText);
                    if (maxPrice.signum() < 0) maxPrice = null;
                } catch (NumberFormatException ignored) { maxPrice = null; }
            }
            List<Car> cars = carDAO.findCars(keyword, category, maxPrice);
            request.setAttribute("cars", cars);
            request.setAttribute("keyword", keyword == null ? "" : keyword);
            request.setAttribute("category", category == null ? "ALL" : category);
            request.setAttribute("maxPrice", maxPriceText == null ? "" : maxPriceText);
            request.getRequestDispatcher("/cars.jsp").forward(request, response);
        } catch (RuntimeException e) {
            throw new ServletException("Unable to load cars. Check the database categories and products.", e);
        }
    }
}
