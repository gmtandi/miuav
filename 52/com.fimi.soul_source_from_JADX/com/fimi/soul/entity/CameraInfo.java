package com.fimi.soul.entity;

public class CameraInfo {
    public Double focalLength;
    public boolean isInLandscapeOrientation;
    public String name;
    public Double overlap;
    public Double sensorHeight;
    public Double sensorResolution;
    public Double sensorWidth;
    public Double sidelap;

    public CameraInfo() {
        this.isInLandscapeOrientation = true;
    }

    public Double getSensorLateralSize() {
        return this.isInLandscapeOrientation ? this.sensorWidth : this.sensorHeight;
    }

    public Double getSensorLongitudinalSize() {
        return this.isInLandscapeOrientation ? this.sensorHeight : this.sensorWidth;
    }

    public String toString() {
        return "ImageWidth:" + this.sensorWidth + " ImageHeight:" + this.sensorHeight + " FocalLength:" + this.focalLength + " Overlap:" + this.overlap + " Sidelap:" + this.sidelap + " isInLandscapeOrientation:" + this.isInLandscapeOrientation;
    }
}
