package com.tencent.open.p134b;

import com.tencent.open.utils.Global;
import com.tencent.open.utils.OpenConfig;

/* renamed from: com.tencent.open.b.e */
public class C2342e {
    public static int m13789a() {
        int i = OpenConfig.getInstance(Global.getContext(), null).getInt("Common_HttpRetryCount");
        return i == 0 ? 2 : i;
    }

    public static int m13790a(String str) {
        int i = OpenConfig.getInstance(Global.getContext(), str).getInt("Common_BusinessReportFrequency");
        return i == 0 ? 100 : i;
    }
}
