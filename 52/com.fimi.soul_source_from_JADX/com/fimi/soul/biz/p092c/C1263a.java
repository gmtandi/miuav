package com.fimi.soul.biz.p092c;

import android.location.Location;
import android.widget.ImageButton;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.LatLng;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1433a;
import java.lang.ref.WeakReference;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.biz.c.a */
public class C1263a {
    private static C1263a f5713e;
    public volatile C1266d f5714a;
    private ImageButton f5715b;
    private C1433a f5716c;
    private int f5717d;
    private WeakReference<ImageButton> f5718f;
    private volatile boolean f5719g;

    public C1263a() {
        this.f5714a = C1266d.DRONE;
        this.f5717d = 0;
        this.f5719g = true;
    }

    public static C1263a m8645a() {
        if (f5713e == null) {
            f5713e = new C1263a();
        }
        return f5713e;
    }

    public void m8646a(int i) {
        if (this.f5715b != null && this.f5717d != i) {
            this.f5715b.setImageResource(i);
            this.f5717d = i;
        }
    }

    public void m8647a(ImageButton imageButton, C1433a c1433a) {
        this.f5718f = new WeakReference(imageButton);
        this.f5715b = (ImageButton) this.f5718f.get();
        this.f5716c = c1433a;
        m8650b();
    }

    public void m8648a(C1266d c1266d) {
        this.f5714a = c1266d;
    }

    public void m8649a(boolean z) {
        this.f5719g = z;
    }

    public void m8650b() {
        if (!this.f5716c.m9570Q() || this.f5716c.m9617t().m10359c() < 3) {
            m8648a(C1266d.PEOPLE);
            m8646a((int) C1205R.drawable.locationbutton);
        } else if (this.f5714a == C1266d.DRONEPRESS) {
            m8648a(C1266d.DRONEPRESS);
            m8646a((int) C1205R.mipmap.btn_fly_location_plane_checked);
        } else {
            m8648a(C1266d.DRONE);
            m8646a((int) C1205R.mipmap.btn_fly_location_plane_normal);
        }
    }

    public C1266d m8651c() {
        return this.f5714a;
    }

    public void m8652d() {
        AMap Z = this.f5716c.m9579Z();
        if (Z != null) {
            switch (C1265c.f5721a[this.f5714a.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    m8646a((int) C1205R.mipmap.btn_fly_location_plane_checked);
                    m8648a(C1266d.DRONEPRESS);
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    Location b = this.f5716c.m9592b();
                    if (b != null) {
                        Z.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(b.getLatitude(), b.getLongitude()), 18.0f));
                    }
                case Type.BYTE /*3*/:
                    m8646a((int) C1205R.mipmap.btn_fly_location_plane_normal);
                    m8648a(C1266d.DRONE);
                default:
            }
        }
    }

    public void m8653e() {
        AMap Z = this.f5716c.m9579Z();
        if (Z != null) {
            LatLng ag = this.f5716c.ag();
            if (ag != null) {
                Z.animateCamera(CameraUpdateFactory.changeLatLng(ag), 300, new C1264b(this));
            }
        }
    }

    public boolean m8654f() {
        return this.f5719g;
    }
}
