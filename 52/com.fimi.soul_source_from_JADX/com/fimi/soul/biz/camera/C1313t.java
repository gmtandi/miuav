package com.fimi.soul.biz.camera;

import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.camera.p088b.C1267c;
import com.fimi.soul.biz.camera.p094c.C1278n;
import com.fimi.soul.biz.camera.p094c.C1279b;
import com.fimi.soul.biz.camera.p094c.C1281d;
import com.fimi.soul.biz.camera.p094c.C1287j;
import com.fimi.soul.biz.camera.p094c.C1291o;
import com.fimi.soul.biz.camera.p094c.C1292p;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.fimi.soul.biz.camera.t */
public class C1313t extends C1299f {
    private C1281d f5842d;
    private C1287j f5843e;
    private C1291o f5844f;
    private C1278n f5845g;
    private C1292p f5846h;
    private C1279b f5847i;

    public C1313t(C1267c c1267c) {
        super(c1267c);
        this.f5842d = new C1281d(this);
        this.f5843e = new C1287j(this);
        this.f5844f = new C1291o(this);
        this.f5845g = new C1278n(this);
        this.f5846h = new C1292p(this);
        m8833a(this.f5842d);
        m8833a(this.f5843e);
        m8833a(this.f5844f);
        m8833a(this.f5845g);
        m8833a(this.f5846h);
        this.f5847i = new C1279b(this);
        m8833a(this.f5847i);
    }

    public C1292p m8872q() {
        return this.f5846h;
    }

    public C1281d m8873r() {
        return this.f5842d;
    }

    public C1287j m8874s() {
        return this.f5843e;
    }

    public C1291o m8875t() {
        return this.f5844f;
    }

    public C1278n m8876u() {
        return C1276b.m8680a().m8697b() ? this.f5845g : this.f5847i;
    }

    public void m8877v() {
        m8873r().m8742a(C1314u.bv, new SimpleDateFormat(C1236a.f5614l).format(new Date()));
    }
}
