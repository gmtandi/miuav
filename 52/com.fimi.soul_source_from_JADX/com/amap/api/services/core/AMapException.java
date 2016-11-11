package com.amap.api.services.core;

import org.p122a.p123a.C2915a;

public class AMapException extends Exception {
    public static final int ERROR_CODE_CONNECTION = 30;
    public static final int ERROR_CODE_FAILURE_AUTH = 32;
    public static final int ERROR_CODE_INVALID_PARAMETER = 24;
    public static final int ERROR_CODE_IO = 21;
    public static final int ERROR_CODE_NULL_PARAMETER = 25;
    public static final int ERROR_CODE_PROTOCOL = 29;
    public static final int ERROR_CODE_QUOTA = 35;
    public static final int ERROR_CODE_REQUEST = 36;
    public static final int ERROR_CODE_SERVER = 34;
    public static final int ERROR_CODE_SERVICE = 33;
    public static final int ERROR_CODE_SOCKET = 22;
    public static final int ERROR_CODE_SOCKE_TIME_OUT = 23;
    public static final int ERROR_CODE_UNKNOWN = 31;
    public static final int ERROR_CODE_UNKNOW_HOST = 27;
    public static final int ERROR_CODE_UNKNOW_SERVICE = 28;
    public static final int ERROR_CODE_URL = 26;
    public static final String ERROR_CONNECTION = "http\u8fde\u63a5\u5931\u8d25 - ConnectionException";
    public static final String ERROR_FAILURE_AUTH = "key\u9274\u6743\u5931\u8d25";
    public static final String ERROR_INVALID_PARAMETER = "\u65e0\u6548\u7684\u53c2\u6570 - IllegalArgumentException";
    public static final String ERROR_IO = "IO \u64cd\u4f5c\u5f02\u5e38 - IOException";
    public static final String ERROR_NULL_PARAMETER = "\u7a7a\u6307\u9488\u5f02\u5e38 - NullPointException";
    public static final String ERROR_PROTOCOL = "\u534f\u8bae\u89e3\u6790\u9519\u8bef - ProtocolException";
    public static final String ERROR_QUOTA = "\u8bf7\u6c42\u6b21\u6570\u8d85\u8fc7\u914d\u989d";
    public static final String ERROR_REQUEST = "\u8bf7\u6c42\u9519\u8bef";
    public static final String ERROR_SERVER = "\u670d\u52a1\u4e0d\u5b58\u5728\u6216\u670d\u52a1\u6b63\u5728\u7ef4\u62a4\u4e2d";
    public static final String ERROR_SERVICE = "\u670d\u52a1\u8fd4\u56de\u4fe1\u606f\u5931\u8d25";
    public static final String ERROR_SOCKET = "socket \u8fde\u63a5\u5f02\u5e38 - SocketException";
    public static final String ERROR_SOCKE_TIME_OUT = "socket \u8fde\u63a5\u8d85\u65f6 - SocketTimeoutException";
    public static final String ERROR_UNKNOWN = "\u672a\u77e5\u7684\u9519\u8bef";
    public static final String ERROR_UNKNOW_HOST = "\u672a\u77e5\u4e3b\u673a - UnKnowHostException";
    public static final String ERROR_UNKNOW_SERVICE = "\u670d\u52a1\u5668\u8fde\u63a5\u5931\u8d25 - UnknownServiceException";
    public static final String ERROR_URL = "url\u5f02\u5e38 - MalformedURLException";
    private String f2925a;
    private int f2926b;

    public AMapException() {
        this.f2925a = C2915a.f14760f;
        this.f2926b = 0;
    }

    public AMapException(String str) {
        this.f2925a = C2915a.f14760f;
        this.f2926b = 0;
        this.f2925a = str;
        m4443a(str);
    }

    private void m4443a(String str) {
        if (ERROR_IO.equals(str)) {
            this.f2926b = ERROR_CODE_IO;
        } else if (ERROR_SOCKET.equals(str)) {
            this.f2926b = ERROR_CODE_SOCKET;
        } else if (ERROR_SOCKE_TIME_OUT.equals(str)) {
            this.f2926b = ERROR_CODE_SOCKE_TIME_OUT;
        } else if (ERROR_INVALID_PARAMETER.equals(str)) {
            this.f2926b = ERROR_CODE_INVALID_PARAMETER;
        } else if (ERROR_NULL_PARAMETER.equals(str)) {
            this.f2926b = ERROR_CODE_NULL_PARAMETER;
        } else if (ERROR_URL.equals(str)) {
            this.f2926b = ERROR_CODE_URL;
        } else if (ERROR_UNKNOW_HOST.equals(str)) {
            this.f2926b = ERROR_CODE_UNKNOW_HOST;
        } else if (ERROR_UNKNOW_SERVICE.equals(str)) {
            this.f2926b = ERROR_CODE_UNKNOW_SERVICE;
        } else if (ERROR_PROTOCOL.equals(str)) {
            this.f2926b = ERROR_CODE_PROTOCOL;
        } else if (ERROR_CONNECTION.equals(str)) {
            this.f2926b = ERROR_CODE_CONNECTION;
        } else if (ERROR_UNKNOWN.equals(str)) {
            this.f2926b = ERROR_CODE_UNKNOWN;
        } else if (ERROR_FAILURE_AUTH.equals(str)) {
            this.f2926b = ERROR_CODE_FAILURE_AUTH;
        } else if (ERROR_SERVER.equals(str)) {
            this.f2926b = ERROR_CODE_SERVER;
        } else if (ERROR_QUOTA.equals(str)) {
            this.f2926b = ERROR_CODE_QUOTA;
        } else {
            this.f2926b = ERROR_CODE_REQUEST;
        }
    }

    public int getErrorCode() {
        return this.f2926b;
    }

    public String getErrorMessage() {
        return this.f2925a;
    }
}
