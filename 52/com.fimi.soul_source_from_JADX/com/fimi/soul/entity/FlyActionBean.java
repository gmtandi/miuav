package com.fimi.soul.entity;

import com.amap.api.maps.model.LatLng;
import java.io.Serializable;

public class FlyActionBean implements Serializable {
    public int ModelType;
    private int Opration_Code;
    private int Para1;
    private int drawableRes;
    private int height;
    private int index;
    private boolean isCanExcute;
    private boolean isCanclick;
    private LatLng latLng;
    private int ridus;
    private int speek;
    private short start_point_agle;
    private int styleInfo;
    private int type;
    private int yaw_mode;

    public FlyActionBean() {
        this.height = 30;
        this.Opration_Code = 16;
        this.speek = 1;
        this.ridus = 5;
        this.Para1 = 3;
        this.isCanExcute = true;
    }

    public int getDrawableRes() {
        return this.drawableRes;
    }

    public int getHeight() {
        return this.height;
    }

    public int getIndex() {
        return this.index;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public int getModelType() {
        return this.ModelType;
    }

    public int getOpration_Code() {
        return this.Opration_Code;
    }

    public int getPara1() {
        return this.Para1;
    }

    public int getRidus() {
        return this.ridus;
    }

    public int getSpeek() {
        return this.speek;
    }

    public short getStart_point_agle() {
        return this.start_point_agle;
    }

    public int getStyleInfo() {
        return this.styleInfo;
    }

    public int getType() {
        return this.type;
    }

    public int getYaw_mode() {
        return this.yaw_mode;
    }

    public boolean isCanExcute() {
        return this.isCanExcute;
    }

    public boolean isCanclick() {
        return this.isCanclick;
    }

    public void setCanExcute(boolean z) {
        this.isCanExcute = z;
    }

    public void setCanclick(boolean z) {
        this.isCanclick = z;
    }

    public void setDrawableRes(int i) {
        this.drawableRes = i;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public void setModelType(int i) {
        this.ModelType = i;
    }

    public void setOpration_Code(int i) {
        this.Opration_Code = i;
    }

    public void setPara1(int i) {
        this.Para1 = i;
    }

    public void setRidus(int i) {
        this.ridus = i;
    }

    public void setSpeek(int i) {
        this.speek = i;
    }

    public void setStart_point_agle(short s) {
        this.start_point_agle = s;
    }

    public void setStyleInfo(int i) {
        this.styleInfo = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setYaw_mode(int i) {
        this.yaw_mode = i;
    }

    public String toString() {
        return "FlyActionBean{styleInfo=" + this.styleInfo + ", height=" + this.height + ", latLng=" + this.latLng + ", drawableRes=" + this.drawableRes + ", index=" + this.index + ", Opration_Code=" + this.Opration_Code + ", speek=" + this.speek + ", ridus=" + this.ridus + ", start_point_agle=" + this.start_point_agle + ", yaw_mode=" + this.yaw_mode + ", Para1=" + this.Para1 + ", ModelType=" + this.ModelType + ", isCanclick=" + this.isCanclick + ", isCanExcute=" + this.isCanExcute + ", type=" + this.type + '}';
    }
}
