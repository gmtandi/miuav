package com.p016a;

import com.amap.api.services.core.AMapException;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.a.fm */
public class fm extends Exception {
    private String f1210a;
    private int f1211b;

    public fm(String str) {
        super(str);
        this.f1210a = AMapException.ERROR_UNKNOWN;
        this.f1211b = -1;
        this.f1210a = str;
        m1832a(str);
    }

    private void m1832a(String str) {
        if (AMapException.ERROR_IO.equals(str)) {
            this.f1211b = 21;
        } else if (AMapException.ERROR_SOCKET.equals(str)) {
            this.f1211b = 22;
        } else if (AMapException.ERROR_SOCKE_TIME_OUT.equals(str)) {
            this.f1211b = 23;
        } else if (AMapException.ERROR_INVALID_PARAMETER.equals(str)) {
            this.f1211b = 24;
        } else if (AMapException.ERROR_NULL_PARAMETER.equals(str)) {
            this.f1211b = 25;
        } else if (AMapException.ERROR_URL.equals(str)) {
            this.f1211b = 26;
        } else if (AMapException.ERROR_UNKNOW_HOST.equals(str)) {
            this.f1211b = 27;
        } else if (AMapException.ERROR_UNKNOW_SERVICE.equals(str)) {
            this.f1211b = 28;
        } else if (AMapException.ERROR_PROTOCOL.equals(str)) {
            this.f1211b = 29;
        } else if (AMapException.ERROR_CONNECTION.equals(str)) {
            this.f1211b = 30;
        } else if (AMapException.ERROR_UNKNOWN.equals(str)) {
            this.f1211b = 31;
        } else if (AMapException.ERROR_FAILURE_AUTH.equals(str)) {
            this.f1211b = 32;
        } else if ("requeust is null".equals(str)) {
            this.f1211b = 1;
        } else if ("request url is empty".equals(str)) {
            this.f1211b = 2;
        } else if ("response is null".equals(str)) {
            this.f1211b = 3;
        } else if ("thread pool has exception".equals(str)) {
            this.f1211b = 4;
        } else if ("sdk name is invalid".equals(str)) {
            this.f1211b = 5;
        } else if ("sdk info is null".equals(str)) {
            this.f1211b = 6;
        } else if ("sdk packages is null".equals(str)) {
            this.f1211b = 7;
        } else if ("\u7ebf\u7a0b\u6c60\u4e3a\u7a7a".equals(str)) {
            this.f1211b = 8;
        } else if ("\u83b7\u53d6\u5bf9\u8c61\u9519\u8bef".equals(str)) {
            this.f1211b = Opcodes.LSUB;
        } else {
            this.f1211b = -1;
        }
    }

    public int m1833a() {
        return this.f1211b;
    }
}
