package com.baidu.tts.p041e;

import com.fimi.soul.utils.C1972l;
import com.tencent.connect.common.Constants;

/* renamed from: com.baidu.tts.e.c */
public enum C0790c {
    BV_16K("16K", Constants.VIA_RESULT_SUCCESS),
    AMR_6K6("6K6", Constants.VIA_RESULT_SUCCESS),
    AMR_8K85("16K", Constants.VIA_TO_TYPE_QQ_GROUP),
    AMR_12K65("16K", Constants.VIA_SSO_LOGIN),
    AMR_14K25("16K", Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP),
    AMR_15K85("16K", Constants.VIA_TO_TYPE_QZONE),
    AMR_18K25("16K", Constants.VIA_SHARE_TYPE_TEXT),
    AMR_19K85("16K", Constants.VIA_SHARE_TYPE_INFO),
    AMR_23K05("16K", C1972l.f10193l),
    AMR_23K85("16K", C1972l.f10194m),
    OPUS_8K("16K", Constants.VIA_RESULT_SUCCESS),
    OPUS_16K("16K", Constants.VIA_TO_TYPE_QQ_GROUP),
    OPUS_18K("16K", Constants.VIA_SSO_LOGIN),
    OPUS_20K("16K", Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP),
    OPUS_24K("16K", Constants.VIA_TO_TYPE_QZONE),
    OPUS_32K("16K", Constants.VIA_SHARE_TYPE_TEXT),
    MP3_8K("16K", Constants.VIA_RESULT_SUCCESS),
    MP3_11K("16K", Constants.VIA_TO_TYPE_QQ_GROUP),
    MP3_16K("16K", Constants.VIA_SSO_LOGIN),
    MP3_24K("16K", Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP),
    MP3_32K("16K", Constants.VIA_TO_TYPE_QZONE);
    
    private final String f4381v;
    private final String f4382w;

    private C0790c(String str, String str2) {
        this.f4381v = str;
        this.f4382w = str2;
    }

    public static C0790c m6734a(String str) {
        for (C0790c c0790c : C0790c.values()) {
            if (c0790c.m6735a().equals(str)) {
                return c0790c;
            }
        }
        return null;
    }

    public String m6735a() {
        return this.f4382w;
    }
}
