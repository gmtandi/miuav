package com.fimi.soul.entity;

import java.io.Serializable;
import java.util.Date;

public class MomentAction implements Serializable {
    private static final long serialVersionUID = 1;
    private double curHight;
    private double curLatitude;
    private double curLongitude;
    private double curSpeed;
    private Date curTime;
    private PlaneAction planeAction;

    public double getCurHight() {
        return this.curHight;
    }

    public double getCurLatitude() {
        return this.curLatitude;
    }

    public double getCurLongitude() {
        return this.curLongitude;
    }

    public double getCurSpeed() {
        return this.curSpeed;
    }

    public Date getCurTime() {
        return this.curTime;
    }

    public PlaneAction getPlaneAction() {
        return this.planeAction;
    }

    public void setCurHight(double d) {
        this.curHight = d;
    }

    public void setCurLatitude(double d) {
        this.curLatitude = d;
    }

    public void setCurLongitude(double d) {
        this.curLongitude = d;
    }

    public void setCurSpeed(double d) {
        this.curSpeed = d;
    }

    public void setCurTime(Date date) {
        this.curTime = date;
    }

    public void setPlaneAction(PlaneAction planeAction) {
        this.planeAction = planeAction;
    }
}
