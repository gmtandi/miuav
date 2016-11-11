package com.xiaomi.smack;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.xiaomi.smack.packet.C2694d;
import com.xiaomi.smack.util.C2718g;
import com.xiaomi.smack.util.C2720h;
import java.io.Writer;

/* renamed from: com.xiaomi.smack.q */
class C2705q {
    private Writer f13389a;
    private C2711t f13390b;

    protected C2705q(C2711t c2711t) {
        this.f13390b = c2711t;
        this.f13389a = c2711t.j;
    }

    private void m15292b(C2694d c2694d) {
        synchronized (this.f13389a) {
            try {
                String a = c2694d.m15228a();
                this.f13389a.write(a + "\r\n");
                this.f13389a.flush();
                Object o = c2694d.m15240o();
                if (!TextUtils.isEmpty(o)) {
                    C2720h.m15369a(this.f13390b.n, o, (long) C2720h.m15366a(a), false, System.currentTimeMillis());
                }
            } catch (Throwable e) {
                throw new C2723w(e);
            }
        }
    }

    void m15293a() {
        this.f13390b.g.clear();
    }

    public void m15294a(C2694d c2694d) {
        m15292b(c2694d);
        this.f13390b.m15165c(c2694d);
    }

    public void m15295b() {
        synchronized (this.f13389a) {
            this.f13389a.write("</stream:stream>");
            this.f13389a.flush();
        }
    }

    void m15296c() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<stream:stream");
        stringBuilder.append(" xmlns=\"xm\"");
        stringBuilder.append(" xmlns:stream=\"xm\"");
        stringBuilder.append(" to=\"").append(this.f13390b.m15166d()).append("\"");
        stringBuilder.append(" version=\"105\"");
        stringBuilder.append(" model=\"").append(C2718g.m15358a(Build.MODEL)).append("\"");
        stringBuilder.append(" os=\"").append(C2718g.m15358a(VERSION.INCREMENTAL)).append("\"");
        stringBuilder.append(" connpt=\"").append(C2718g.m15358a(this.f13390b.m15168f())).append("\"");
        stringBuilder.append(" host=\"").append(this.f13390b.m15332e()).append("\"");
        stringBuilder.append(">");
        this.f13389a.write(stringBuilder.toString());
        this.f13389a.flush();
    }

    public void m15297d() {
        synchronized (this.f13389a) {
            try {
                this.f13389a.write(this.f13390b.m15334u() + "\r\n");
                this.f13389a.flush();
                this.f13390b.m15336w();
            } catch (Throwable e) {
                throw new C2723w(e);
            }
        }
    }
}
