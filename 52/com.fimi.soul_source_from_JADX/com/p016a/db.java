package com.p016a;

import com.tencent.mm.sdk.platformtools.Util;

/* renamed from: com.a.db */
public class db extends dc<String, ct> {
    public db() {
        super(Util.BYTE_OF_MB);
    }

    protected int m1438a(String str, ct ctVar) {
        int i = 0;
        if (ctVar == null) {
            return i;
        }
        try {
            return (int) ctVar.m1393g();
        } catch (Throwable e) {
            ev.m1777a(e, "OfflineFileCache", "sizeOf");
            return i;
        }
    }

    protected void m1440a(boolean z, String str, ct ctVar, ct ctVar2) {
        if (ctVar != null) {
            try {
                ctVar.m1388b();
            } catch (Throwable e) {
                ev.m1777a(e, "OfflineFileCache", "entryRemoved");
            }
        }
        super.m1433a(z, str, ctVar, ctVar2);
    }
}
