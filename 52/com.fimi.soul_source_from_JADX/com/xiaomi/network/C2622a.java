package com.xiaomi.network;

import com.xiaomi.common.logger.thrift.mfs.C2483b;
import com.xiaomi.network.UploadHostStatHelper.HttpRecordCallback;
import java.util.List;
import org.json.JSONException;

/* renamed from: com.xiaomi.network.a */
final class C2622a implements HttpRecordCallback {
    C2622a() {
    }

    public List<C2483b> m14883a() {
        try {
            return HostManager.sInstance.generateHostStats();
        } catch (JSONException e) {
            return null;
        }
    }

    public double m14884b() {
        Fallback fallbacksByHost = HostManager.sInstance.getFallbacksByHost("f3.mi-stat.gslb.mi-idc.com");
        return fallbacksByHost != null ? fallbacksByHost.m14849f() : 0.1d;
    }
}
