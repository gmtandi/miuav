package com.fimi.soul.entity;

import java.io.Serializable;

public class Plane implements Serializable {
    private static final long serialVersionUID = 1;
    private String flyControlID;
    private long id;
    private String note;
    private String rcIC;
    private User user;
    private String version;

    public String getFlyControlID() {
        return this.flyControlID;
    }

    public long getId() {
        return this.id;
    }

    public String getNote() {
        return this.note;
    }

    public String getRcIC() {
        return this.rcIC;
    }

    public User getUser() {
        return this.user;
    }

    public String getVersion() {
        return this.version;
    }

    public void setFlyControlID(String str) {
        this.flyControlID = str;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setNote(String str) {
        this.note = str;
    }

    public void setRcIC(String str) {
        this.rcIC = str;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
