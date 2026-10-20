package com.shopsphere.model;

import java.sql.Timestamp;

public class CarBooking {
    private int id;
    private String carName;
    private String preferredDate;
    private String message;
    private String status;
    private Timestamp createdAt;

    public CarBooking(int id, String carName, String preferredDate, String message, String status, Timestamp createdAt) {
        this.id = id; this.carName = carName; this.preferredDate = preferredDate;
        this.message = message; this.status = status; this.createdAt = createdAt;
    }
    public int getId() { return id; }
    public String getCarName() { return carName; }
    public String getPreferredDate() { return preferredDate; }
    public String getMessage() { return message; }
    public String getStatus() { return status; }
    public Timestamp getCreatedAt() { return createdAt; }
}
