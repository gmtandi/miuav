package com.amap.api.mapcore.util;

import com.amap.api.services.core.AMapException;
import org.p122a.p123a.C2915a;

public class dd {
    private static dd f2413a;

    public static dd m4003a() {
        if (f2413a == null) {
            f2413a = new dd();
        }
        return f2413a;
    }

    protected dl m4004a(dj djVar, boolean z) {
        bk e;
        try {
            m4007c(djVar);
            return new df(djVar.f2114g, djVar.f2115h, djVar.f2116i == null ? null : djVar.f2116i, z).m4015a(djVar.m3437f(), djVar.m3436c(), djVar.m3438g());
        } catch (bk e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new bk(AMapException.ERROR_UNKNOWN);
        }
    }

    public byte[] m4005a(dj djVar) {
        bk e;
        try {
            dl a = m4004a(djVar, true);
            return a != null ? a.f2434a : null;
        } catch (bk e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new bk(AMapException.ERROR_UNKNOWN);
        }
    }

    public byte[] m4006b(dj djVar) {
        bk e;
        try {
            dl a = m4004a(djVar, false);
            return a != null ? a.f2434a : null;
        } catch (bk e2) {
            throw e2;
        } catch (Throwable th) {
            cb.m3809a(th, "BaseNetManager", "makeSyncPostRequest");
            e2 = new bk(AMapException.ERROR_UNKNOWN);
        }
    }

    protected void m4007c(dj djVar) {
        if (djVar == null) {
            throw new bk("requeust is null");
        } else if (djVar.m3431a() == null || C2915a.f14760f.equals(djVar.m3431a())) {
            throw new bk("request url is empty");
        }
    }
}
