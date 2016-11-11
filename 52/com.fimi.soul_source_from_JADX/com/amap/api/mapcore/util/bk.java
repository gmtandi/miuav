package com.amap.api.mapcore.util;

import com.amap.api.services.core.AMapException;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class bk extends Exception {
    private String f2207a;
    private int f2208b;

    public bk(String str) {
        super(str);
        this.f2207a = AMapException.ERROR_UNKNOWN;
        this.f2208b = -1;
        this.f2207a = str;
        m3643a(str);
    }

    private void m3643a(String str) {
        if (AMapException.ERROR_IO.equals(str)) {
            this.f2208b = 21;
        } else if (AMapException.ERROR_SOCKET.equals(str)) {
            this.f2208b = 22;
        } else if (AMapException.ERROR_SOCKE_TIME_OUT.equals(str)) {
            this.f2208b = 23;
        } else if (AMapException.ERROR_INVALID_PARAMETER.equals(str)) {
            this.f2208b = 24;
        } else if (AMapException.ERROR_NULL_PARAMETER.equals(str)) {
            this.f2208b = 25;
        } else if (AMapException.ERROR_URL.equals(str)) {
            this.f2208b = 26;
        } else if (AMapException.ERROR_UNKNOW_HOST.equals(str)) {
            this.f2208b = 27;
        } else if (AMapException.ERROR_UNKNOW_SERVICE.equals(str)) {
            this.f2208b = 28;
        } else if (AMapException.ERROR_PROTOCOL.equals(str)) {
            this.f2208b = 29;
        } else if (AMapException.ERROR_CONNECTION.equals(str)) {
            this.f2208b = 30;
        } else if (AMapException.ERROR_UNKNOWN.equals(str)) {
            this.f2208b = 31;
        } else if (AMapException.ERROR_FAILURE_AUTH.equals(str)) {
            this.f2208b = 32;
        } else if ("requeust is null".equals(str)) {
            this.f2208b = 1;
        } else if ("request url is empty".equals(str)) {
            this.f2208b = 2;
        } else if ("response is null".equals(str)) {
            this.f2208b = 3;
        } else if ("thread pool has exception".equals(str)) {
            this.f2208b = 4;
        } else if ("sdk name is invalid".equals(str)) {
            this.f2208b = 5;
        } else if ("sdk info is null".equals(str)) {
            this.f2208b = 6;
        } else if ("sdk packages is null".equals(str)) {
            this.f2208b = 7;
        } else if ("\u7ebf\u7a0b\u6c60\u4e3a\u7a7a".equals(str)) {
            this.f2208b = 8;
        } else if ("\u83b7\u53d6\u5bf9\u8c61\u9519\u8bef".equals(str)) {
            this.f2208b = Opcodes.LSUB;
        } else {
            this.f2208b = -1;
        }
    }

    public String m3644a() {
        return this.f2207a;
    }

    public int m3645b() {
        return this.f2208b;
    }
}
