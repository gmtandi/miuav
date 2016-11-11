package com.fimi.soul.entity;

public class DroneModelType {
    public static final int VCM_AUTO_LANDING = 3;
    public static final int VCM_AUTO_TAKE_OFF = 2;
    public static final int VCM_EMERGENCY_LANDING = 9;
    public static final int VCM_EMERGENCY_RTH = 8;
    public static final int VCM_FLY_TO = 4;
    public static final int VCM_INTEREST_POINT = 5;
    public static final int VCM_MISSION = 6;
    public static final int VCM_NORMAL = 1;
    public static final int VCM_NULL = 0;
    public static final int VCM_RTH = 7;
    public static final int VCM_SELFIE = 10;
    public static final int VEHICLE_CTRL_TYPE_ACRO = 4;
    public static final int VEHICLE_CTRL_TYPE_ASSITED = 3;
    public static final int VEHICLE_CTRL_TYPE_ATTI = 0;
    public static final int VEHICLE_CTRL_TYPE_GLOBAL_AUTO = 1;
    public static final int VEHICLE_CTRL_TYPE_LOCAL_AUTO = 2;
    private int ctrlMode;
    private int ctrlType;

    public boolean isEnforcementAtti() {
        return this.ctrlType == 0;
    }

    public boolean isEnterModel() {
        return (this.ctrlMode == VEHICLE_CTRL_TYPE_ASSITED && this.ctrlType == VEHICLE_CTRL_TYPE_GLOBAL_AUTO) ? true : (this.ctrlMode == VCM_RTH && this.ctrlType == VEHICLE_CTRL_TYPE_GLOBAL_AUTO) || this.ctrlMode == VCM_EMERGENCY_RTH;
    }

    public boolean isExceAction() {
        return this.ctrlMode == VCM_MISSION || this.ctrlMode == VEHICLE_CTRL_TYPE_ACRO || this.ctrlMode == VCM_INTEREST_POINT || this.ctrlMode == VCM_SELFIE;
    }

    public boolean isGps() {
        return this.ctrlType == VEHICLE_CTRL_TYPE_GLOBAL_AUTO;
    }

    public boolean isInWayPoint() {
        return this.ctrlMode == VCM_MISSION;
    }

    public boolean isLightStream() {
        return this.ctrlType == VEHICLE_CTRL_TYPE_LOCAL_AUTO;
    }

    public boolean isTakePhotoSelf() {
        return this.ctrlMode == VCM_SELFIE;
    }

    public boolean judgeNoAction() {
        return (this.ctrlMode == VCM_MISSION || this.ctrlMode == VEHICLE_CTRL_TYPE_ACRO || this.ctrlMode == VCM_INTEREST_POINT || this.ctrlMode == VCM_SELFIE) ? false : true;
    }

    public boolean judgeOpenDrawLayout() {
        return this.ctrlMode == VEHICLE_CTRL_TYPE_GLOBAL_AUTO || this.ctrlMode == 0 || this.ctrlMode == VEHICLE_CTRL_TYPE_LOCAL_AUTO;
    }

    public void setCtrlMode(int i) {
        this.ctrlMode = i;
    }

    public void setCtrlType(int i) {
        this.ctrlType = i;
    }
}
