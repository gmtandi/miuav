package com.tencent.open;

import android.location.Location;
import com.tencent.map.p129a.p130a.C2224b;
import com.tencent.map.p129a.p130a.C2226d;
import com.tencent.open.C2354c.C2290a;
import com.tencent.open.p133a.C2333f;

/* renamed from: com.tencent.open.d */
public class C2355d extends C2224b {
    private C2290a f12068a;

    public C2355d(C2290a c2290a) {
        super(1, 0, 0, 8);
        this.f12068a = c2290a;
    }

    public void m13811a(int i) {
        C2333f.m13757c(C2333f.f12014d, "location: onStatusUpdate = " + i);
        super.m13353a(i);
    }

    public void m13812a(C2226d c2226d) {
        C2333f.m13757c(C2333f.f12014d, "location: onLocationUpdate = " + c2226d);
        super.m13354a(c2226d);
        if (c2226d != null) {
            Location location = new Location("passive");
            location.setLatitude(c2226d.f11536b);
            location.setLongitude(c2226d.f11537c);
            if (this.f12068a != null) {
                this.f12068a.onLocationUpdate(location);
            }
        }
    }

    public void m13813a(byte[] bArr, int i) {
        super.m13355a(bArr, i);
    }
}
