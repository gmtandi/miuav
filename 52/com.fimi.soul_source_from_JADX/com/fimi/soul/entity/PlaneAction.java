package com.fimi.soul.entity;

import java.io.Serializable;
import java.util.Date;

public class PlaneAction implements Serializable {
    private static final long serialVersionUID = 1;
    private Date allTime;
    private double averageSpeed;
    private double distance;
    private String location;
    private double maxSpeed;
    private String note;
    private Plane plane;
    private String status;
    private User user;

    public Date getAllTime() {
        return this.allTime;
    }

    public double getAverageSpeed() {
        return this.averageSpeed;
    }

    public double getDistance() {
        return this.distance;
    }

    public String getLocation() {
        return this.location;
    }

    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    public String getNote() {
        return this.note;
    }

    public Plane getPlane() {
        return this.plane;
    }

    public String getStatus() {
        return this.status;
    }

    public User getUser() {
        return this.user;
    }

    public void setAllTime(Date date) {
        this.allTime = date;
    }

    public void setAverageSpeed(double d) {
        this.averageSpeed = d;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public void setMaxSpeed(double d) {
        this.maxSpeed = d;
    }

    public void setNote(String str) {
        this.note = str;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
