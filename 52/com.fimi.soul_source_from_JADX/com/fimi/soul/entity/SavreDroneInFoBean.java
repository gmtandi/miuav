package com.fimi.soul.entity;

import com.fimi.soul.drone.p107c.p108a.C1465c;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class SavreDroneInFoBean implements Serializable {
    private ConcurrentHashMap<String, C1465c> hashMap;

    public SavreDroneInFoBean() {
        this.hashMap = new ConcurrentHashMap();
    }

    public ConcurrentHashMap<String, C1465c> getHashMap() {
        return this.hashMap;
    }
}
