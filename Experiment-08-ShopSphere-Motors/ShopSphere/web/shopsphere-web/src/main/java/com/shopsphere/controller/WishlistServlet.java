package com.shopsphere.controller;

import com.shopsphere.dao.WishlistDAO;
import com.shopsphere.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/wishlist")
public class WishlistServlet extends HttpServlet {
    private final WishlistDAO dao = new WishlistDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || !(session.getAttribute("user") instanceof User user)) {
            response.sendRedirect("login.jsp");
            return;
        }
        try {
            request.setAttribute("cars", dao.findByUser(user.getId()));
            request.getRequestDispatcher("/wishlist.jsp").forward(request, response);
        } catch (RuntimeException e) { throw new ServletException("Unable to load wishlist.", e); }
    }

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
            String action = request.getParameter("action");
            if ("remove".equals(action)) dao.remove(user.getId(), carId);
            else dao.add(user.getId(), carId);
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer != null && referer.contains(request.getContextPath())
                    ? referer : "wishlist");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid car ID.");
        }
    }
}
