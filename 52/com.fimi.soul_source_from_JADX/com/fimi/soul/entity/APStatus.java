package com.fimi.soul.entity;

import java.io.Serializable;

public class APStatus implements Serializable {
    private String ssid;

    public String getSsid() {
        return this.ssid;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }
}
