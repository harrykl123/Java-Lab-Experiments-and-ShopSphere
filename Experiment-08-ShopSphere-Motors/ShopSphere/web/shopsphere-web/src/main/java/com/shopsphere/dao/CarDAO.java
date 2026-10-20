package com.shopsphere.dao;

import com.shopsphere.model.Car;
import com.shopsphere.util.DBConnection;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    private static final String SELECT = """
        SELECT p.id, p.category_id, p.name, p.description, p.price, p.stock,
               p.image, p.sku, c.name AS category_name
        FROM products p JOIN categories c ON c.id = p.category_id
        """;

    private Car map(ResultSet rs) throws SQLException {
        return new Car(rs.getInt("id"), rs.getInt("category_id"), rs.getString("name"),
                rs.getString("description"), rs.getBigDecimal("price"), rs.getInt("stock"),
                rs.getString("image"), rs.getString("sku"), rs.getString("category_name"));
    }

    public List<Car> findCars(String keyword, String category, BigDecimal maxPrice) {
        List<Car> cars = new ArrayList<>();
        StringBuilder sql = new StringBuilder(SELECT)
                .append(" WHERE LOWER(c.name) IN ('suv','sedan','hatchback','luxury')");
        List<Object> params = new ArrayList<>();
        if (keyword != null && !keyword.isBlank()) {
            sql.append(" AND (LOWER(p.name) LIKE ? OR LOWER(p.description) LIKE ? OR LOWER(p.sku) LIKE ?)");
            String term = "%" + keyword.trim().toLowerCase() + "%";
            params.add(term); params.add(term); params.add(term);
        }
        if (category != null && !category.isBlank() && !"ALL".equalsIgnoreCase(category)) {
            sql.append(" AND LOWER(c.name) = ?");
            params.add(category.trim().toLowerCase());
        }
        if (maxPrice != null) {
            sql.append(" AND p.price <= ?");
            params.add(maxPrice);
        }
        sql.append(" ORDER BY p.id DESC");
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                Object value = params.get(i);
                if (value instanceof BigDecimal bd) ps.setBigDecimal(i + 1, bd);
                else ps.setString(i + 1, value.toString());
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) cars.add(map(rs));
            }
        } catch (SQLException e) { throw new RuntimeException("Could not load cars.", e); }
        return cars;
    }

    public Car findById(int id) {
        String sql = SELECT + " WHERE p.id = ? AND LOWER(c.name) IN ('suv','sedan','hatchback','luxury')";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() ? map(rs) : null; }
        } catch (SQLException e) { throw new RuntimeException("Could not load car details.", e); }
    }

    public List<Car> findAllCarsForAdmin() {
        List<Car> cars = new ArrayList<>();
        String sql = SELECT + " WHERE LOWER(c.name) IN ('suv','sedan','hatchback','luxury') ORDER BY p.id DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) cars.add(map(rs));
        } catch (SQLException e) { throw new RuntimeException("Could not load inventory.", e); }
        return cars;
    }

    public boolean addCar(Car car) {
        String sql = "INSERT INTO products (category_id,name,description,price,stock,image,sku) VALUES (?,?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, car.getCategoryId());
            ps.setString(2, car.getName());
            ps.setString(3, car.getDescription());
            ps.setBigDecimal(4, car.getPrice());
            ps.setInt(5, car.getStock());
            ps.setString(6, car.getImage());
            ps.setString(7, car.getSku());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) { throw new RuntimeException("Could not add car. Check category ID and unique SKU.", e); }
    }

    public boolean deleteCar(int id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "DELETE FROM products WHERE id = ? AND category_id IN (SELECT id FROM categories WHERE LOWER(name) IN ('suv','sedan','hatchback','luxury'))")) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) { throw new RuntimeException("Could not delete car. It may be linked to an order.", e); }
    }
}
