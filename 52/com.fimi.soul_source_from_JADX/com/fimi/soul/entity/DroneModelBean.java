package com.fimi.soul.entity;

import com.fimi.soul.drone.C1433a;
import java.util.Observable;

public class DroneModelBean extends Observable {
    private int ctrlType;
    private int currentFlightModel;
    private C1433a drone;
    private int flightModel;
    private boolean isComTakeOFF;

    public DroneModelBean(C1433a c1433a) {
        this.isComTakeOFF = true;
        this.drone = c1433a;
    }

    public int getCurrentFlightModel() {
        return this.currentFlightModel;
    }

    public int getFlightModel() {
        return this.flightModel;
    }

    public boolean isComTakeOFF() {
        return this.isComTakeOFF;
    }

    public void setComTakeOFF(boolean z) {
        this.isComTakeOFF = z;
    }

    public void setFlightModel(int i, int i2) {
        this.currentFlightModel = this.flightModel;
        this.ctrlType = i2;
        if ((this.flightModel == 7 || this.flightModel == 3) && ((i2 == 2 || i2 == 1) && i == 1 && !this.drone.aa())) {
            setChanged();
            notifyObservers();
        }
        if (this.flightModel == 2 && this.drone.aa() && this.isComTakeOFF && i == 1) {
            this.isComTakeOFF = false;
            setChanged();
            notifyObservers();
        }
        this.flightModel = i;
    }
}
