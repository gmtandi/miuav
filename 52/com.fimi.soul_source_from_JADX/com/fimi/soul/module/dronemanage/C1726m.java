package com.fimi.soul.module.dronemanage;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.fimi.soul.drone.C1433a;
import java.lang.ref.WeakReference;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.module.dronemanage.m */
public class C1726m {
    private Context f8500a;
    private C1738z f8501b;
    private C1717d f8502c;
    private C1735v f8503d;
    private C1722i f8504e;
    private WeakReference<Context> f8505f;
    private WeakReference<C1433a> f8506g;

    public C1726m(C1433a c1433a, FragmentActivity fragmentActivity, FlightMapFragment flightMapFragment) {
        this.f8505f = new WeakReference(fragmentActivity);
        this.f8506g = new WeakReference(c1433a);
        this.f8500a = (Context) this.f8505f.get();
        m11253a(this.f8500a, c1433a.m9579Z(), (C1433a) this.f8506g.get(), flightMapFragment);
    }

    private void m11253a(Context context, AMap aMap, C1433a c1433a, FlightMapFragment flightMapFragment) {
        this.f8501b = new C1738z(c1433a, context, flightMapFragment);
        this.f8502c = new C1717d(aMap, context, c1433a);
        this.f8503d = new C1735v(aMap, context, c1433a);
        this.f8504e = new C1722i(aMap, context, c1433a);
        this.f8504e.m11246a(this.f8501b);
        this.f8504e.m11245a(this.f8502c);
    }

    public void m11254a() {
        this.f8501b.m11305d();
        this.f8503d.m11275c();
        this.f8502c.m11214b();
        if (this.f8502c.m11217d().size() < 1) {
            Toast.makeText(this.f8500a, "\u8bf7\u8bbe\u7f6e\u6307\u5b9a\u822a\u70b9", 0);
        }
    }

    public void m11255a(int i) {
        if (aj.m11198a() == 1 || aj.m11198a() == 6 || aj.m11198a() == 7 || i == 6) {
            this.f8501b.m11304c(i);
        } else if (aj.m11198a() == 2) {
            this.f8502c.m11218e();
        } else if (aj.m11198a() == 3) {
            this.f8503d.m11277e();
        }
        this.f8501b.m11311k();
        this.f8502c.m11220g();
        this.f8503d.m11279g();
        m11262g().m11242a();
        m11262g().m11249d();
    }

    public void m11256a(int i, LatLng latLng) {
        switch (aj.m11198a()) {
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f8502c.m11212a(latLng);
                if (i == 4) {
                    this.f8502c.m11210a();
                }
            case Type.BYTE /*3*/:
                this.f8503d.m11271a(latLng);
            case Type.FLOAT /*6*/:
                this.f8501b.m11296a(latLng, 0);
            default:
        }
    }

    public void m11257b() {
        this.f8501b.m11305d();
        this.f8502c.m11216c();
        this.f8503d.m11273b();
    }

    public void m11258c() {
        this.f8501b.m11303c();
        this.f8502c.m11216c();
        this.f8503d.m11275c();
    }

    public void m11259d() {
        this.f8501b.m11305d();
        this.f8502c.m11216c();
        this.f8503d.m11275c();
    }

    public void m11260e() {
        this.f8501b.m11310j();
        this.f8502c.m11219f();
        this.f8503d.m11278f();
        this.f8504e.m11247b();
    }

    public C1717d m11261f() {
        return this.f8502c;
    }

    public C1722i m11262g() {
        return this.f8504e;
    }

    public C1735v m11263h() {
        return this.f8503d;
    }

    public C1738z m11264i() {
        return this.f8501b;
    }

    public void m11265j() {
        this.f8504e.m11248c();
    }
}
