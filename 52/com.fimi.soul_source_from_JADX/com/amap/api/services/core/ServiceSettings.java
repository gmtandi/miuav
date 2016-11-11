package com.amap.api.services.core;

import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import com.p054c.p055a.p063b.p068d.C0921a;

public class ServiceSettings {
    public static final String CHINESE = "zh-CN";
    public static final String ENGLISH = "en";
    public static final int HTTP = 1;
    public static final int HTTPS = 2;
    private static ServiceSettings f2948c;
    private String f2949a;
    private int f2950b;
    private int f2951d;
    private int f2952e;

    private ServiceSettings() {
        this.f2949a = CHINESE;
        this.f2950b = HTTP;
        this.f2951d = C0921a.f4833b;
        this.f2952e = C0921a.f4833b;
    }

    public static ServiceSettings getInstance() {
        if (f2948c == null) {
            f2948c = new ServiceSettings();
        }
        return f2948c;
    }

    public int getConnectionTimeOut() {
        return this.f2951d;
    }

    public String getLanguage() {
        return this.f2949a;
    }

    public int getProtocol() {
        return this.f2950b;
    }

    public int getSoTimeOut() {
        return this.f2952e;
    }

    public void setApiKey(String str) {
        C0497x.m4876a(str);
    }

    public void setConnectionTimeOut(int i) {
        if (i < FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS) {
            this.f2951d = FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS;
        } else if (i > 30000) {
            this.f2951d = 30000;
        } else {
            this.f2951d = i;
        }
    }

    public void setLanguage(String str) {
        if (ENGLISH.equals(str) || CHINESE.equals(str)) {
            this.f2949a = str;
        }
    }

    public void setProtocol(int i) {
        this.f2950b = i;
    }

    public void setSoTimeOut(int i) {
        if (i < FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS) {
            this.f2952e = FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS;
        } else if (i > 30000) {
            this.f2952e = 30000;
        } else {
            this.f2952e = i;
        }
    }
}
