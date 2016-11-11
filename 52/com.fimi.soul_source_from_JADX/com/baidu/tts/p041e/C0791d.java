package com.baidu.tts.p041e;

import com.tencent.connect.common.Constants;

/* renamed from: com.baidu.tts.e.d */
public enum C0791d {
    GB18030("gb18030", Constants.VIA_RESULT_SUCCESS),
    BIG5("big5", Constants.VIA_TO_TYPE_QQ_GROUP),
    UTF8("utf-8", Constants.VIA_SSO_LOGIN),
    GBK("gbk", Constants.VIA_TO_TYPE_QZONE),
    UNICODE("unicode", Constants.VIA_SHARE_TYPE_TEXT);
    
    private final String f4389f;
    private final String f4390g;

    private C0791d(String str, String str2) {
        this.f4389f = str;
        this.f4390g = str2;
    }

    public static C0791d m6736a(String str) {
        for (C0791d c0791d : C0791d.values()) {
            if (c0791d.m6738b().equals(str)) {
                return c0791d;
            }
        }
        return null;
    }

    public String m6737a() {
        return this.f4389f;
    }

    public String m6738b() {
        return this.f4390g;
    }
}
