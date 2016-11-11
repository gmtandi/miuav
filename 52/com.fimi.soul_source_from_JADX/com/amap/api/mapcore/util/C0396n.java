package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Bundle;
import com.amap.api.mapcore.util.ac.C0335a;
import com.amap.api.mapcore.util.ah.C0341a;
import com.amap.api.maps.AMap;
import java.io.IOException;

/* renamed from: com.amap.api.mapcore.util.n */
public class C0396n extends dp implements C0335a {
    private ac f2507a;
    private ae f2508b;
    private ag f2509c;
    private Context f2510e;
    private Bundle f2511f;
    private AMap f2512g;
    private boolean f2513h;

    public C0396n(ag agVar, Context context) {
        this.f2511f = new Bundle();
        this.f2513h = false;
        this.f2509c = agVar;
        this.f2510e = context;
    }

    public C0396n(ag agVar, Context context, AMap aMap) {
        this(agVar, context);
        this.f2512g = aMap;
    }

    private String m4169f() {
        return bj.m3634b(this.f2510e);
    }

    private void m4170g() {
        this.f2507a = new ac(new ad(this.f2509c.getUrl(), m4169f(), this.f2509c.m3429y(), 1, this.f2509c.m3430z()), this.f2509c.getUrl(), this.f2510e, this.f2509c);
        this.f2507a.m3380a((C0335a) this);
        this.f2508b = new ae(this.f2509c, this.f2509c);
        if (!this.f2513h) {
            this.f2507a.m3379a();
        }
    }

    public void m4171a() {
        if (this.f2509c.m3428x()) {
            this.f2509c.m3424a(C0341a.file_io_exception);
            return;
        }
        try {
            m4170g();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void m4172b() {
        this.f2513h = true;
        if (this.f2507a != null) {
            this.f2507a.m3384c();
        } else {
            m4022e();
        }
        if (this.f2508b != null) {
            this.f2508b.m3409a();
        }
    }

    public void m4173c() {
        this.f2512g = null;
        if (this.f2511f != null) {
            this.f2511f.clear();
            this.f2511f = null;
        }
    }

    public void m4174d() {
        if (this.f2508b != null) {
            this.f2508b.m3410b();
        }
    }
}
