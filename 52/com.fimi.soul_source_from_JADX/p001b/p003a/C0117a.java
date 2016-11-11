package p001b.p003a;

import p001b.p002b.C0115j;
import p001b.p002b.C0116p;
import p001b.p002b.C0136k;
import p001b.p002b.C0139n;

/* renamed from: b.a.a */
public class C0117a extends C0116p {
    private volatile int f123a;

    public C0117a(Class<? extends C0136k> cls) {
        super((Class) cls);
    }

    public C0117a(Class<? extends C0136k> cls, String str) {
        super((Class) cls, str);
    }

    public C0117a(String str) {
        super(str);
    }

    public void m149a(C0115j c0115j, C0139n c0139n) {
        new C0118b(this, c0115j, c0139n).start();
    }

    public void m150a(C0139n c0139n) {
        this.f123a = 0;
        super.m143a(c0139n);
        f_();
    }

    public synchronized void m151b() {
        this.f123a++;
        notifyAll();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    synchronized void f_() {
        /*
        r2 = this;
        monitor-enter(r2);
    L_0x0001:
        r0 = r2.f123a;	 Catch:{ all -> 0x0010 }
        r1 = r2.m147d();	 Catch:{ all -> 0x0010 }
        if (r0 >= r1) goto L_0x000e;
    L_0x0009:
        r2.wait();	 Catch:{ InterruptedException -> 0x000d }
        goto L_0x0001;
    L_0x000d:
        r0 = move-exception;
    L_0x000e:
        monitor-exit(r2);
        return;
    L_0x0010:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.f_():void");
    }
}
