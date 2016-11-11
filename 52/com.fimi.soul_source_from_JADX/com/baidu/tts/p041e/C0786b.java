package com.baidu.tts.p041e;

import com.tencent.connect.common.Constants;

/* renamed from: com.baidu.tts.e.b */
public enum C0786b {
    BV(Constants.VIA_RESULT_SUCCESS) {
    },
    AMR(Constants.VIA_TO_TYPE_QQ_GROUP) {
    },
    OPUS(Constants.VIA_SSO_LOGIN) {
    };
    
    private final String f4358d;

    private C0786b(String str) {
        this.f4358d = str;
    }

    public static C0786b m6732a(String str) {
        for (C0786b c0786b : C0786b.values()) {
            if (c0786b.m6733a().equals(str)) {
                return c0786b;
            }
        }
        return null;
    }

    public String m6733a() {
        return this.f4358d;
    }
}
