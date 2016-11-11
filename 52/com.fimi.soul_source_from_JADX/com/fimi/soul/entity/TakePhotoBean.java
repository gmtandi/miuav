package com.fimi.soul.entity;

import java.io.Serializable;

public class TakePhotoBean implements Serializable {
    private byte CMD_ID;
    private short Climb_Angle;
    private byte Mode;
    private byte Opration_Code;
    private short Route_Length;
    private short Route_Speed;

    public byte getCMD_ID() {
        return this.CMD_ID;
    }

    public short getClimb_Angle() {
        return this.Climb_Angle;
    }

    public byte getMode() {
        return this.Mode;
    }

    public byte getOpration_Code() {
        return this.Opration_Code;
    }

    public short getRoute_Length() {
        return this.Route_Length;
    }

    public short getRoute_Speed() {
        return this.Route_Speed;
    }

    public void setCMD_ID(byte b) {
        this.CMD_ID = b;
    }

    public void setClimb_Angle(short s) {
        this.Climb_Angle = s;
    }

    public void setMode(byte b) {
        this.Mode = b;
    }

    public void setOpration_Code(byte b) {
        this.Opration_Code = b;
    }

    public void setRoute_Length(short s) {
        this.Route_Length = s;
    }

    public void setRoute_Speed(short s) {
        this.Route_Speed = s;
    }
}
