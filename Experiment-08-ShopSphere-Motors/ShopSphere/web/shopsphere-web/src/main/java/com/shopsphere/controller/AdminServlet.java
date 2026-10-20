package com.shopsphere.controller;

import com.shopsphere.dao.CarBookingDAO;
import com.shopsphere.dao.CarDAO;
import com.shopsphere.model.Car;
import com.shopsphere.model.User;
import com.shopsphere.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private final CarDAO carDAO = new CarDAO();
    private final CarBookingDAO bookingDAO = new CarBookingDAO();

    private boolean requireAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || !(session.getAttribute("user") instanceof User user)
                || !"ADMIN".equalsIgnoreCase(user.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Admin access required.");
            return false;
        }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!requireAdmin(request, response)) return;
        try {
            request.setAttribute("cars", carDAO.findAllCarsForAdmin());
            request.setAttribute("bookings", bookingDAO.findAll());
            List<String[]> categories = new ArrayList<>();
            try (Connection c = DBConnection.getConnection();
                 PreparedStatement p = c.prepareStatement(
                         "SELECT id,name FROM categories WHERE LOWER(name) IN ('suv','sedan','hatchback','luxury') ORDER BY name");
                 ResultSet r = p.executeQuery()) {
                while (r.next()) categories.add(new String[]{String.valueOf(r.getInt(1)), r.getString(2)});
            }
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        } catch (RuntimeException | SQLException e) {
            throw new ServletException("Unable to load admin dashboard.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!requireAdmin(request, response)) return;
        request.setCharacterEncoding("UTF-8");
        try {
            String action = request.getParameter("action");
            if ("addCar".equals(action)) {
                Car car = new Car();
                car.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
                car.setName(request.getParameter("name").trim());
                car.setDescription(request.getParameter("description"));
                car.setPrice(new BigDecimal(request.getParameter("price")));
                car.setStock(Integer.parseInt(request.getParameter("stock")));
                car.setImage(request.getParameter("image"));
                car.setSku(request.getParameter("sku").trim());
                if (car.getName().isBlank() || car.getSku().isBlank() || car.getPrice().signum() < 0 || car.getStock() < 0) {
                    response.sendRedirect("admin?error=invalid");
                    return;
                }
                carDAO.addCar(car);
            } else if ("deleteCar".equals(action)) {
                carDAO.deleteCar(Integer.parseInt(request.getParameter("carId")));
            } else if ("updateBooking".equals(action)) {
                String status = request.getParameter("status");
                if (!List.of("PENDING", "CONFIRMED", "CANCELLED", "COMPLETED").contains(status)) {
                    response.sendRedirect("admin?error=status");
                    return;
                }
                bookingDAO.updateStatus(Integer.parseInt(request.getParameter("bookingId")), status);
            }
            response.sendRedirect("admin?success=true");
        } catch (RuntimeException e) {
            throw new ServletException("Admin action failed. Check submitted values and database schema.", e);
        }
    }
}
