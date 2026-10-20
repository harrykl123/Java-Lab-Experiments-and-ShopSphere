package com.shopsphere.dao;

import com.shopsphere.model.Car;
import com.shopsphere.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAO {
    public boolean add(int userId, int carId) {
        String sql = "INSERT IGNORE INTO wishlist (user_id, product_id) VALUES (?, ?)";
        try (Connection c = DBConnection.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, userId); p.setInt(2, carId);
            return p.executeUpdate() >= 0;
        } catch (SQLException e) { throw new RuntimeException("Could not update wishlist.", e); }
    }

    public boolean remove(int userId, int carId) {
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement("DELETE FROM wishlist WHERE user_id=? AND product_id=?")) {
            p.setInt(1, userId); p.setInt(2, carId);
            return p.executeUpdate() >= 0;
        } catch (SQLException e) { throw new RuntimeException("Could not update wishlist.", e); }
    }

    public List<Car> findByUser(int userId) {
        List<Car> list = new ArrayList<>();
        String sql = """
            SELECT p.id,p.category_id,p.name,p.description,p.price,p.stock,p.image,p.sku,c.name AS category_name
            FROM wishlist w JOIN products p ON p.id=w.product_id
            JOIN categories c ON c.id=p.category_id
            WHERE w.user_id=? AND LOWER(c.name) IN ('suv','sedan','hatchback','luxury')
            ORDER BY w.created_at DESC
            """;
        try (Connection c = DBConnection.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, userId);
            try (ResultSet r = p.executeQuery()) {
                while (r.next()) list.add(new Car(r.getInt("id"),r.getInt("category_id"),r.getString("name"),
                        r.getString("description"),r.getBigDecimal("price"),r.getInt("stock"),
                        r.getString("image"),r.getString("sku"),r.getString("category_name")));
            }
        } catch (SQLException e) { throw new RuntimeException("Could not load wishlist.", e); }
        return list;
    }
}
