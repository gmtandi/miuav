package com.p054c.p055a.p063b.p068d;

import com.facebook.common.util.UriUtil;
import com.tencent.mm.sdk.message.RMsgInfo;
import java.util.Locale;
import org.p122a.p123a.C2915a;

/* renamed from: com.c.a.b.d.d */
public enum C0923d {
    HTTP(UriUtil.HTTP_SCHEME),
    HTTPS(UriUtil.HTTPS_SCHEME),
    FILE(UriUtil.LOCAL_FILE_SCHEME),
    CONTENT(RMsgInfo.COL_CONTENT),
    ASSETS("assets"),
    DRAWABLE("drawable"),
    UNKNOWN(C2915a.f14760f);
    
    private String f4851h;
    private String f4852i;

    private C0923d(String str) {
        this.f4851h = str;
        this.f4852i = str + "://";
    }

    public static C0923d m7267a(String str) {
        if (str != null) {
            for (C0923d c0923d : C0923d.values()) {
                if (c0923d.m7268d(str)) {
                    return c0923d;
                }
            }
        }
        return UNKNOWN;
    }

    private boolean m7268d(String str) {
        return str.toLowerCase(Locale.US).startsWith(this.f4852i);
    }

    public String m7269b(String str) {
        return this.f4852i + str;
    }

    public String m7270c(String str) {
        if (m7268d(str)) {
            return str.substring(this.f4852i.length());
        }
        throw new IllegalArgumentException(String.format("URI [%1$s] doesn't have expected scheme [%2$s]", new Object[]{str, this.f4851h}));
    }
}
