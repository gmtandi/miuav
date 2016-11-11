package com.fimi.soul.entity;

public class UpdateDroneInforBean {
    private DroneInfoBean mDroneInfoBean;
    private String planeID;
    private String userID;

    public String getPlaneID() {
        return this.planeID;
    }

    public String getUserID() {
        return this.userID;
    }

    public DroneInfoBean getmDroneInfoBean() {
        return this.mDroneInfoBean;
    }

    public void setPlaneID(String str) {
        this.planeID = str;
    }

    public void setUserID(String str) {
        this.userID = str;
    }

    public void setmDroneInfoBean(DroneInfoBean droneInfoBean) {
        this.mDroneInfoBean = droneInfoBean;
    }
}
