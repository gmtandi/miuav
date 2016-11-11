package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import com.amap.api.mapcore.bn.C0304a;
import com.amap.api.mapcore.util.ba.C0352a;
import java.lang.ref.WeakReference;

public abstract class bd {
    private ba f2185a;
    private C0352a f2186b;
    protected boolean f2187c;
    protected Resources f2188d;
    private boolean f2189e;
    private final Object f2190f;

    /* renamed from: com.amap.api.mapcore.util.bd.a */
    public class C0353a extends av<Boolean, Void, Bitmap> {
        final /* synthetic */ bd f2194a;
        private final WeakReference<C0304a> f2195e;

        public C0353a(bd bdVar, C0304a c0304a) {
            this.f2194a = bdVar;
            this.f2195e = new WeakReference(c0304a);
        }

        private C0304a m3576e() {
            C0304a c0304a = (C0304a) this.f2195e.get();
            return this == bd.m3551c(c0304a) ? c0304a : null;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected android.graphics.Bitmap m3577a(java.lang.Boolean... r7) {
            /*
            r6 = this;
            r1 = 0;
            r0 = 0;
            r0 = r7[r0];	 Catch:{ Throwable -> 0x0056 }
            r3 = r0.booleanValue();	 Catch:{ Throwable -> 0x0056 }
            r0 = r6.f2195e;	 Catch:{ Throwable -> 0x0056 }
            r0 = r0.get();	 Catch:{ Throwable -> 0x0056 }
            r0 = (com.amap.api.mapcore.bn.C0304a) r0;	 Catch:{ Throwable -> 0x0056 }
            if (r0 != 0) goto L_0x0014;
        L_0x0012:
            r0 = r1;
        L_0x0013:
            return r0;
        L_0x0014:
            r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0056 }
            r2.<init>();	 Catch:{ Throwable -> 0x0056 }
            r4 = r0.f1831a;	 Catch:{ Throwable -> 0x0056 }
            r2.append(r4);	 Catch:{ Throwable -> 0x0056 }
            r4 = "-";
            r2.append(r4);	 Catch:{ Throwable -> 0x0056 }
            r4 = r0.f1832b;	 Catch:{ Throwable -> 0x0056 }
            r2.append(r4);	 Catch:{ Throwable -> 0x0056 }
            r4 = "-";
            r2.append(r4);	 Catch:{ Throwable -> 0x0056 }
            r4 = r0.f1833c;	 Catch:{ Throwable -> 0x0056 }
            r2.append(r4);	 Catch:{ Throwable -> 0x0056 }
            r4 = r2.toString();	 Catch:{ Throwable -> 0x0056 }
            r2 = r6.f2194a;	 Catch:{ Throwable -> 0x0056 }
            r2 = r2.f2190f;	 Catch:{ Throwable -> 0x0056 }
            monitor-enter(r2);	 Catch:{ Throwable -> 0x0056 }
        L_0x003d:
            r5 = r6.f2194a;	 Catch:{ all -> 0x0053 }
            r5 = r5.f2187c;	 Catch:{ all -> 0x0053 }
            if (r5 == 0) goto L_0x005c;
        L_0x0043:
            r5 = r6.m3143d();	 Catch:{ all -> 0x0053 }
            if (r5 != 0) goto L_0x005c;
        L_0x0049:
            r5 = r6.f2194a;	 Catch:{ all -> 0x0053 }
            r5 = r5.f2190f;	 Catch:{ all -> 0x0053 }
            r5.wait();	 Catch:{ all -> 0x0053 }
            goto L_0x003d;
        L_0x0053:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x0053 }
            throw r0;	 Catch:{ Throwable -> 0x0056 }
        L_0x0056:
            r0 = move-exception;
            r0.printStackTrace();
            r0 = r1;
            goto L_0x0013;
        L_0x005c:
            monitor-exit(r2);	 Catch:{ all -> 0x0053 }
            r2 = r6.f2194a;	 Catch:{ Throwable -> 0x0056 }
            r2 = r2.f2185a;	 Catch:{ Throwable -> 0x0056 }
            if (r2 == 0) goto L_0x00b8;
        L_0x0065:
            r2 = r6.m3143d();	 Catch:{ Throwable -> 0x0056 }
            if (r2 != 0) goto L_0x00b8;
        L_0x006b:
            r2 = r6.m3576e();	 Catch:{ Throwable -> 0x0056 }
            if (r2 == 0) goto L_0x00b8;
        L_0x0071:
            r2 = r6.f2194a;	 Catch:{ Throwable -> 0x0056 }
            r2 = r2.f2189e;	 Catch:{ Throwable -> 0x0056 }
            if (r2 != 0) goto L_0x00b8;
        L_0x0079:
            r2 = r6.f2194a;	 Catch:{ Throwable -> 0x0056 }
            r2 = r2.f2185a;	 Catch:{ Throwable -> 0x0056 }
            r2 = r2.m3543b(r4);	 Catch:{ Throwable -> 0x0056 }
        L_0x0083:
            if (r3 == 0) goto L_0x00b6;
        L_0x0085:
            if (r2 != 0) goto L_0x00b6;
        L_0x0087:
            r3 = r6.m3143d();	 Catch:{ Throwable -> 0x0056 }
            if (r3 != 0) goto L_0x00b6;
        L_0x008d:
            r3 = r6.m3576e();	 Catch:{ Throwable -> 0x0056 }
            if (r3 == 0) goto L_0x00b6;
        L_0x0093:
            r3 = r6.f2194a;	 Catch:{ Throwable -> 0x0056 }
            r3 = r3.f2189e;	 Catch:{ Throwable -> 0x0056 }
            if (r3 != 0) goto L_0x00b6;
        L_0x009b:
            r2 = r6.f2194a;	 Catch:{ Throwable -> 0x0056 }
            r0 = r2.m3553a(r0);	 Catch:{ Throwable -> 0x0056 }
        L_0x00a1:
            if (r0 == 0) goto L_0x0013;
        L_0x00a3:
            r2 = r6.f2194a;	 Catch:{ Throwable -> 0x0056 }
            r2 = r2.f2185a;	 Catch:{ Throwable -> 0x0056 }
            if (r2 == 0) goto L_0x0013;
        L_0x00ab:
            r2 = r6.f2194a;	 Catch:{ Throwable -> 0x0056 }
            r2 = r2.f2185a;	 Catch:{ Throwable -> 0x0056 }
            r2.m3542a(r4, r0);	 Catch:{ Throwable -> 0x0056 }
            goto L_0x0013;
        L_0x00b6:
            r0 = r2;
            goto L_0x00a1;
        L_0x00b8:
            r2 = r1;
            goto L_0x0083;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.bd.a.a(java.lang.Boolean[]):android.graphics.Bitmap");
        }

        protected void m3579a(Bitmap bitmap) {
            try {
                if (m3143d() || this.f2194a.f2189e) {
                    bitmap = null;
                }
                C0304a e = m3576e();
                if (bitmap != null && !bitmap.isRecycled() && e != null) {
                    e.m3123a(bitmap);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        protected void m3581b(Bitmap bitmap) {
            super.m3139b((Object) bitmap);
            synchronized (this.f2194a.f2190f) {
                try {
                    this.f2194a.f2190f.notifyAll();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.util.bd.b */
    public class C0354b extends av<Object, Void, Void> {
        final /* synthetic */ bd f2196a;

        protected C0354b(bd bdVar) {
            this.f2196a = bdVar;
        }

        protected /* synthetic */ Object m3583a(Object[] objArr) {
            return m3584d(objArr);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected java.lang.Void m3584d(java.lang.Object... r2) {
            /*
            r1 = this;
            r0 = 0;
            r0 = r2[r0];	 Catch:{ Throwable -> 0x0014 }
            r0 = (java.lang.Integer) r0;	 Catch:{ Throwable -> 0x0014 }
            r0 = r0.intValue();	 Catch:{ Throwable -> 0x0014 }
            switch(r0) {
                case 0: goto L_0x000e;
                case 1: goto L_0x0019;
                case 2: goto L_0x001f;
                case 3: goto L_0x0025;
                default: goto L_0x000c;
            };	 Catch:{ Throwable -> 0x0014 }
        L_0x000c:
            r0 = 0;
            return r0;
        L_0x000e:
            r0 = r1.f2196a;	 Catch:{ Throwable -> 0x0014 }
            r0.m3560c();	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
        L_0x0014:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x000c;
        L_0x0019:
            r0 = r1.f2196a;	 Catch:{ Throwable -> 0x0014 }
            r0.m3558b();	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
        L_0x001f:
            r0 = r1.f2196a;	 Catch:{ Throwable -> 0x0014 }
            r0.m3561d();	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
        L_0x0025:
            r0 = r1.f2196a;	 Catch:{ Throwable -> 0x0014 }
            r0.m3562e();	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.bd.b.d(java.lang.Object[]):java.lang.Void");
        }
    }

    protected bd(Context context) {
        this.f2189e = false;
        this.f2187c = false;
        this.f2190f = new Object();
        this.f2188d = context.getResources();
    }

    public static void m3548a(C0304a c0304a) {
        C0353a c = m3551c(c0304a);
        if (c != null) {
            c.m3137a(true);
        }
    }

    private static C0353a m3551c(C0304a c0304a) {
        return c0304a != null ? c0304a.f1840j : null;
    }

    protected abstract Bitmap m3553a(Object obj);

    protected ba m3554a() {
        return this.f2185a;
    }

    public void m3555a(C0352a c0352a) {
        this.f2186b = c0352a;
        this.f2185a = ba.m3530a(this.f2186b);
        new C0354b(this).m3141c(Integer.valueOf(1));
    }

    public void m3556a(boolean z) {
        this.f2189e = z;
        m3559b(false);
    }

    public void m3557a(boolean z, C0304a c0304a) {
        if (c0304a != null) {
            Bitmap bitmap = null;
            try {
                if (this.f2185a != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(c0304a.f1831a);
                    stringBuilder.append("-");
                    stringBuilder.append(c0304a.f1832b);
                    stringBuilder.append("-");
                    stringBuilder.append(c0304a.f1833c);
                    bitmap = this.f2185a.m3540a(stringBuilder.toString());
                }
                if (bitmap != null) {
                    c0304a.m3123a(bitmap);
                    return;
                }
                C0353a c0353a = new C0353a(this, c0304a);
                c0304a.f1840j = c0353a;
                c0353a.m3134a(av.f1846d, (Object[]) new Boolean[]{Boolean.valueOf(z)});
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    protected void m3558b() {
        if (this.f2185a != null) {
            this.f2185a.m3541a();
        }
    }

    public void m3559b(boolean z) {
        synchronized (this.f2190f) {
            this.f2187c = z;
            if (!this.f2187c) {
                try {
                    this.f2190f.notifyAll();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    protected void m3560c() {
        if (this.f2185a != null) {
            this.f2185a.m3544b();
        }
    }

    protected void m3561d() {
        if (this.f2185a != null) {
            this.f2185a.m3545c();
        }
    }

    protected void m3562e() {
        if (this.f2185a != null) {
            this.f2185a.m3546d();
            this.f2185a = null;
        }
    }

    public void m3563f() {
        new C0354b(this).m3141c(Integer.valueOf(0));
    }

    public void m3564g() {
        new C0354b(this).m3141c(Integer.valueOf(3));
    }
}
