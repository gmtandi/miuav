package com.fimi.soul.biz.p100g;

import com.fimi.kernel.p083d.C1160b;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.ai;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.biz.g.i */
public class C1342i implements C1234i {
    private C1433a f5978a;
    private C1160b f5979b;
    private boolean f5980c;
    private int f5981d;
    private int f5982e;
    private boolean f5983f;

    public C1342i(C1433a c1433a) {
        this.f5978a = c1433a;
        c1433a.m9590a((C1234i) this);
        this.f5979b = C1160b.m7953b(c1433a.f6507c);
    }

    private synchronized void m8988a(ai aiVar) {
        boolean z = true;
        synchronized (this) {
            if ((aiVar.m10288g() & 15) != 1) {
                z = false;
            }
            if (!(this.f5980c || !z || this.f5978a.aa())) {
                switch (this.f5982e) {
                    case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                        m8992a(m8990a((int) C1205R.string.can_takeOff_ATTi));
                        break;
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        m8992a(m8990a((int) C1205R.string.can_takeOff_GLOBAL));
                        break;
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        m8992a(m8990a((int) C1205R.string.can_takeOff_Local));
                        break;
                }
            }
            if (!(!this.f5980c || z || this.f5978a.aa())) {
                if (this.f5978a.aj().m10321i()) {
                    m8992a(m8990a((int) C1205R.string.can_takeOff_ATTi));
                } else {
                    m8992a(m8990a((int) C1205R.string.self_error_result));
                }
            }
            if (this.f5980c != z) {
                this.f5980c = z;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m8989c() {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.f5983f;	 Catch:{ all -> 0x0028 }
        if (r0 != 0) goto L_0x000c;
    L_0x0005:
        r0 = 1;
        r2.f5983f = r0;	 Catch:{ all -> 0x0028 }
        r0 = r2.f5982e;	 Catch:{ all -> 0x0028 }
        r2.f5981d = r0;	 Catch:{ all -> 0x0028 }
    L_0x000c:
        r0 = r2.f5981d;	 Catch:{ all -> 0x0028 }
        r1 = r2.f5982e;	 Catch:{ all -> 0x0028 }
        if (r0 == r1) goto L_0x001b;
    L_0x0012:
        r0 = r2.f5982e;	 Catch:{ all -> 0x0028 }
        switch(r0) {
            case 0: goto L_0x001d;
            case 1: goto L_0x002b;
            case 2: goto L_0x0036;
            default: goto L_0x0017;
        };	 Catch:{ all -> 0x0028 }
    L_0x0017:
        r0 = r2.f5982e;	 Catch:{ all -> 0x0028 }
        r2.f5981d = r0;	 Catch:{ all -> 0x0028 }
    L_0x001b:
        monitor-exit(r2);
        return;
    L_0x001d:
        r0 = 2131558540; // 0x7f0d008c float:1.8742399E38 double:1.0531298467E-314;
        r0 = r2.m8990a(r0);	 Catch:{ all -> 0x0028 }
        r2.m8992a(r0);	 Catch:{ all -> 0x0028 }
        goto L_0x0017;
    L_0x0028:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
    L_0x002b:
        r0 = 2131558541; // 0x7f0d008d float:1.87424E38 double:1.053129847E-314;
        r0 = r2.m8990a(r0);	 Catch:{ all -> 0x0028 }
        r2.m8992a(r0);	 Catch:{ all -> 0x0028 }
        goto L_0x0017;
    L_0x0036:
        r0 = 2131558542; // 0x7f0d008e float:1.8742403E38 double:1.0531298477E-314;
        r0 = r2.m8990a(r0);	 Catch:{ all -> 0x0028 }
        r2.m8992a(r0);	 Catch:{ all -> 0x0028 }
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fimi.soul.biz.g.i.c():void");
    }

    public String m8990a(int i) {
        return this.f5978a.f6507c.getResources().getString(i);
    }

    public void m8991a() {
        this.f5978a.m9594b((C1234i) this);
        this.f5980c = false;
    }

    public void m8992a(String str) {
        this.f5979b.m7959a(str);
    }

    public void m8993b() {
        this.f5978a.m9590a((C1234i) this);
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (C1343j.f5984a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f5982e = c1433a.ah().m10169d();
                ai q = c1433a.m9614q();
                m8989c();
                m8988a(q);
            default:
        }
    }
}
