
package com.shopsphere.dao;

import com.shopsphere.model.CarBooking;
import com.shopsphere.util.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CarBookingDAO {

    public boolean create(int userId, int carId, Date preferredDate, String message) {

        String sql = """
                INSERT INTO car_bookings
                (user_id, car_id, preferred_date, message, status, total_amount)
                SELECT ?, p.id, ?, ?, 'PENDING', p.price
                FROM products p
                WHERE p.id = ?
                """;

        try (Connection c = DBConnection.getConnection();
                PreparedStatement p = c.prepareStatement(sql)) {

            p.setInt(1, userId);

            if (preferredDate == null) {
                p.setNull(2, Types.DATE);
            } else {
                p.setDate(2, preferredDate);
            }

            p.setString(3, message);
            p.setInt(4, carId);

            return p.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Could not create booking: " + e.getMessage(), e);
        }
    }

    public List<CarBooking> findForUser(int userId) {

        List<CarBooking> list = new ArrayList<>();

        String sql = """
                SELECT b.id,
                       p.name AS car_name,
                       b.preferred_date,
                       b.message,
                       b.status,
                       b.booking_date AS created_at
                FROM car_bookings b
                JOIN products p ON p.id = b.car_id
                WHERE b.user_id = ?
                ORDER BY b.booking_date DESC
                """;

        try (Connection c = DBConnection.getConnection();
                PreparedStatement p = c.prepareStatement(sql)) {

            p.setInt(1, userId);

            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    list.add(mapBooking(r));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Could not load bookings: " + e.getMessage(), e);
        }

        return list;
    }

    public List<CarBooking> findAll() {

        List<CarBooking> list = new ArrayList<>();

        String sql = """
                SELECT b.id,
                       p.name AS car_name,
                       b.preferred_date,
                       b.message,
                       b.status,
                       b.booking_date AS created_at
                FROM car_bookings b
                JOIN products p ON p.id = b.car_id
                ORDER BY b.booking_date DESC
                """;

        try (Connection c = DBConnection.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
                ResultSet r = p.executeQuery()) {

            while (r.next()) {
                list.add(mapBooking(r));
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Could not load admin bookings: " + e.getMessage(), e);
        }

        return list;
    }

    private CarBooking mapBooking(ResultSet r) throws SQLException {

        Date date = r.getDate("preferred_date");

        return new CarBooking(
                r.getInt("id"),
                r.getString("car_name"),
                date == null ? "" : date.toString(),
                r.getString("message"),
                r.getString("status"),
                r.getTimestamp("created_at"));
    }

    public boolean updateStatus(int bookingId, String status) {

        String sql = "UPDATE car_bookings SET status = ? WHERE id = ?";

        try (Connection c = DBConnection.getConnection();
                PreparedStatement p = c.prepareStatement(sql)) {

            p.setString(1, status);
            p.setInt(2, bookingId);

            return p.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Could not update booking status: " + e.getMessage(), e);
        }
    }
}
