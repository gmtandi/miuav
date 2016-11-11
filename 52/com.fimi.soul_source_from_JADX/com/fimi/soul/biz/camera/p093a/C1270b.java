package com.fimi.soul.biz.camera.p093a;

import android.os.Message;
import com.fimi.kernel.C1099d;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p076b.C1152e;
import com.fimi.kernel.p076b.p079c.C1133a;
import com.fimi.kernel.p076b.p079c.C1136d;
import com.fimi.kernel.p076b.p080d.C1140c;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.biz.camera.p088b.C1269f;

/* renamed from: com.fimi.soul.biz.camera.a.b */
public class C1270b extends C1099d implements C1269f {
    private C1140c f5727a;
    private C1140c f5728b;

    public C1270b() {
        this.f5727a = (C1140c) C1189f.m8328a(C1152e.Socket);
        C1142e f = this.f5727a.m7888f();
        f.m7723a(C1314u.f5874a);
        f.m7722a((int) C1314u.f5877d);
        f.m7892b((int) C1142e.f5202b);
        f.m7894b(true);
        this.f5728b = (C1140c) C1189f.m8328a(C1152e.Socket);
        f = this.f5728b.m7888f();
        f.m7723a(C1314u.f5874a);
        f.m7722a((int) C1314u.f5876c);
        f.m7892b((int) C1142e.f5202b);
        f.m7894b(true);
    }

    protected void m8663a(Message message) {
    }

    public void m8664a(C1133a c1133a) {
        this.f5728b.m7882b(c1133a);
    }

    public void m8665a(C1136d c1136d) {
        this.f5727a.m7876a(c1136d);
    }

    public synchronized void m8666a(byte[] bArr) {
        m7687a(new C1271c(this, bArr));
    }

    public synchronized void m8667a(byte[] bArr, int i, int i2) {
        if (!this.f5728b.m7885b()) {
            this.f5728b.m7886d();
        }
        this.f5728b.m7879a(bArr, i, i2);
    }

    public void m8668b() {
        this.f5727a.m7889g();
        this.f5728b.m7889g();
    }

    public void m8669b(C1133a c1133a) {
        this.f5728b.m7875a(c1133a);
    }

    public void m8670b(C1136d c1136d) {
        this.f5727a.m7883b(c1136d);
    }

    public boolean m8671c() {
        return this.f5727a.m7885b();
    }

    public void m8672d() {
        this.f5727a.m7889g();
        this.f5727a.m7886d();
    }

    public void m8673e() {
        this.f5728b.m7889g();
        this.f5728b.m7886d();
    }
}
