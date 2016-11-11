package com.fimi.soul.module.droneFragment;

import android.os.Message;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.WeightedLatLng;
import com.fimi.kernel.C1099d;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p106b.C1434a;
import com.fimi.soul.drone.p106b.C1435b;
import java.util.concurrent.atomic.AtomicInteger;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.module.droneFragment.e */
public class C1691e extends C1099d {
    public static final int f8271a = 0;
    public static final int f8272b = 1;
    private static AtomicInteger f8273c;
    private static AtomicInteger f8274d;
    private static Marker f8275f;
    private static final C1691e f8276h;
    private C1433a f8277e;
    private AMap f8278g;
    private Runnable f8279i;
    private boolean f8280j;

    static {
        f8273c = new AtomicInteger(f8271a);
        f8274d = new AtomicInteger(f8272b);
        f8276h = new C1691e();
    }

    private C1691e() {
        this.f8279i = new C1692f(this);
        this.f8280j = true;
    }

    public static void m11076a(int i) {
        f8274d.set(i);
    }

    public static AtomicInteger m11078b() {
        return f8274d;
    }

    public static C1691e m11079c() {
        return f8276h;
    }

    public static AtomicInteger m11080d() {
        return f8273c;
    }

    public static void m11081f() {
        if (f8275f != null) {
            f8275f.destroy();
            f8275f = null;
        }
    }

    protected void m11084a(Message message) {
    }

    public void m11085a(C1433a c1433a) {
        if (!c1433a.ah().m10172g().isLightStream()) {
            this.f8277e = c1433a;
            this.f8278g = c1433a.m9579Z();
            if (c1433a.m9569P().m9995a() && c1433a.m9570Q() && ((c1433a.ag().latitude > WeightedLatLng.DEFAULT_INTENSITY && c1433a.ag().longitude > WeightedLatLng.DEFAULT_INTENSITY && f8273c.get() == 0) || f8274d.get() == 0)) {
                if (f8274d.get() == 0) {
                    f8274d.set(f8272b);
                    if (f8275f == null) {
                        f8275f = this.f8278g.addMarker(C1434a.m9624a(c1433a.ag()).icon(BitmapDescriptorFactory.fromBitmap(C1435b.m9629b((int) C1205R.drawable.handpiece_aspect_infor, c1433a.f6507c.getResources().getString(C1205R.string.flighthead), C2915a.f14760f, c1433a.f6507c))));
                    }
                    this.f8278g.animateCamera(CameraUpdateFactory.newLatLngZoom(c1433a.ag(), 18.0f));
                    if (f8275f != null && this.f8280j) {
                        this.f8280j = false;
                        m7685a().postDelayed(this.f8279i, 5000);
                    }
                } else if (f8275f != null) {
                    f8275f.setPosition(c1433a.ag());
                }
            } else if (f8275f != null) {
                f8275f.setPosition(c1433a.ag());
            }
        }
    }

    public void m11086b(int i) {
        f8273c.set(i);
    }

    public void m11087e() {
        this.f8280j = true;
    }
}
