package com.fimi.soul.entity;

import java.io.Serializable;

public class AddPlaneback implements Serializable {
    private int charge_count;
    private String cloud_deck_ID;
    private String fieldNames;
    private String fly_control_ID;
    private String note;
    private int pk;
    private long planeID;
    private String receiver_control_ID;
    private String remote_control_ID;
    private int userID;

    public int getCharge_count() {
        return this.charge_count;
    }

    public String getCloud_deck_ID() {
        return this.cloud_deck_ID;
    }

    public String getFieldNames() {
        return this.fieldNames;
    }

    public String getFly_control_ID() {
        return this.fly_control_ID;
    }

    public String getNote() {
        return this.note;
    }

    public int getPk() {
        return this.pk;
    }

    public long getPlaneID() {
        return this.planeID;
    }

    public String getReceiver_control_ID() {
        return this.receiver_control_ID;
    }

    public String getRemote_control_ID() {
        return this.remote_control_ID;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setCharge_count(int i) {
        this.charge_count = i;
    }

    public void setCloud_deck_ID(String str) {
        this.cloud_deck_ID = str;
    }

    public void setFieldNames(String str) {
        this.fieldNames = str;
    }

    public void setFly_control_ID(String str) {
        this.fly_control_ID = str;
    }

    public void setNote(String str) {
        this.note = str;
    }

    public void setPk(int i) {
        this.pk = i;
    }

    public void setPlaneID(long j) {
        this.planeID = j;
    }

    public void setReceiver_control_ID(String str) {
        this.receiver_control_ID = str;
    }

    public void setRemote_control_ID(String str) {
        this.remote_control_ID = str;
    }

    public void setUserID(int i) {
        this.userID = i;
    }

    public String toString() {
        return "AddPlaneback [fieldNames=" + this.fieldNames + ", pk=" + this.pk + ", userID=" + this.userID + ", note=" + this.note + ", remote_control_ID=" + this.remote_control_ID + ", planeID=" + this.planeID + ", charge_count=" + this.charge_count + ", cloud_deck_ID=" + this.cloud_deck_ID + ", fly_control_ID=" + this.fly_control_ID + ", receiver_control_ID=" + this.receiver_control_ID + "]";
    }
}
