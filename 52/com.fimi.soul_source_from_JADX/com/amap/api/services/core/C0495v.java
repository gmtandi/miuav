package com.amap.api.services.core;

/* renamed from: com.amap.api.services.core.v */
public class C0495v extends Exception {
    private String f3169a;
    private int f3170b;

    public C0495v(String str) {
        super(str);
        this.f3169a = AMapException.ERROR_UNKNOWN;
        this.f3170b = -1;
        m4866a(str);
    }

    private void m4866a(String str) {
        if (AMapException.ERROR_IO.equals(str)) {
            this.f3170b = 21;
        } else if (AMapException.ERROR_SOCKET.equals(str)) {
            this.f3170b = 22;
        } else if (AMapException.ERROR_SOCKE_TIME_OUT.equals(str)) {
            this.f3170b = 23;
        } else if (AMapException.ERROR_INVALID_PARAMETER.equals(str)) {
            this.f3170b = 24;
        } else if (AMapException.ERROR_NULL_PARAMETER.equals(str)) {
            this.f3170b = 25;
        } else if (AMapException.ERROR_URL.equals(str)) {
            this.f3170b = 26;
        } else if (AMapException.ERROR_UNKNOW_HOST.equals(str)) {
            this.f3170b = 27;
        } else if (AMapException.ERROR_UNKNOW_SERVICE.equals(str)) {
            this.f3170b = 28;
        } else if (AMapException.ERROR_PROTOCOL.equals(str)) {
            this.f3170b = 29;
        } else if (AMapException.ERROR_CONNECTION.equals(str)) {
            this.f3170b = 30;
        } else if (AMapException.ERROR_UNKNOWN.equals(str)) {
            this.f3170b = 31;
        } else if (AMapException.ERROR_FAILURE_AUTH.equals(str)) {
            this.f3170b = 32;
        } else if ("requeust is null".equals(str)) {
            this.f3170b = 1;
        } else if ("request url is empty".equals(str)) {
            this.f3170b = 2;
        } else if ("response is null".equals(str)) {
            this.f3170b = 3;
        } else if ("thread pool has exception".equals(str)) {
            this.f3170b = 4;
        } else if ("sdk name is invalid".equals(str)) {
            this.f3170b = 5;
        } else if ("sdk info is null".equals(str)) {
            this.f3170b = 6;
        } else if ("sdk packages is null".equals(str)) {
            this.f3170b = 7;
        } else if ("\u7ebf\u7a0b\u6c60\u4e3a\u7a7a".equals(str)) {
            this.f3170b = 8;
        } else {
            this.f3170b = -1;
        }
    }

    public String m4867a() {
        return this.f3169a;
    }
}
