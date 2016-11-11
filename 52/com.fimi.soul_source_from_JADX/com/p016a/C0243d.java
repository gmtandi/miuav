package com.p016a;

import com.amap.api.location.AMapLocation;
import com.xiaomi.mipush.sdk.MiPushClient;

/* renamed from: com.a.d */
class C0243d {
    double f819a;
    double f820b;
    long f821c;
    float f822d;
    float f823e;
    int f824f;
    String f825g;
    final /* synthetic */ C0238a f826h;

    C0243d(C0238a c0238a, AMapLocation aMapLocation, int i) {
        this.f826h = c0238a;
        this.f819a = aMapLocation.getLatitude();
        this.f820b = aMapLocation.getLongitude();
        this.f821c = aMapLocation.getTime();
        this.f822d = aMapLocation.getAccuracy();
        this.f823e = aMapLocation.getSpeed();
        this.f824f = i;
        this.f825g = aMapLocation.getProvider();
    }

    public boolean equals(Object obj) {
        try {
            C0243d c0243d = (C0243d) obj;
            return this.f819a == c0243d.f819a && this.f820b == c0243d.f820b;
        } catch (Throwable th) {
            return false;
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.f819a);
        stringBuffer.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
        stringBuffer.append(this.f820b);
        stringBuffer.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
        stringBuffer.append(this.f822d);
        stringBuffer.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
        stringBuffer.append(this.f821c);
        stringBuffer.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
        stringBuffer.append(this.f823e);
        stringBuffer.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
        stringBuffer.append(this.f824f);
        stringBuffer.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
        stringBuffer.append(this.f825g);
        return stringBuffer.toString();
    }
}
