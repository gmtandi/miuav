package com.tencent.connect.auth;

public class QQToken {
    public static final int AUTH_QQ = 2;
    public static final int AUTH_QZONE = 3;
    public static final int AUTH_WEB = 1;
    private String f11422a;
    private String f11423b;
    private String f11424c;
    private int f11425d;
    private long f11426e;

    public QQToken(String str) {
        this.f11425d = AUTH_WEB;
        this.f11426e = -1;
        this.f11422a = str;
    }

    public String getAccessToken() {
        return this.f11423b;
    }

    public String getAppId() {
        return this.f11422a;
    }

    public int getAuthSource() {
        return this.f11425d;
    }

    public long getExpireTimeInSecond() {
        return this.f11426e;
    }

    public String getOpenId() {
        return this.f11424c;
    }

    public boolean isSessionValid() {
        return this.f11423b != null && System.currentTimeMillis() < this.f11426e;
    }

    public void setAccessToken(String str, String str2) {
        this.f11423b = str;
        this.f11426e = 0;
        if (str2 != null) {
            this.f11426e = System.currentTimeMillis() + (Long.parseLong(str2) * 1000);
        }
    }

    public void setAppId(String str) {
        this.f11422a = str;
    }

    public void setAuthSource(int i) {
        this.f11425d = i;
    }

    public void setOpenId(String str) {
        this.f11424c = str;
    }
}
