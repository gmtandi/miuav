package com.p054c.p055a.p063b;

import android.graphics.Bitmap;
import android.os.Handler;
import com.p054c.p055a.p063b.p064a.C0897c;
import com.p054c.p055a.p063b.p064a.C0899e;
import com.p054c.p055a.p063b.p064a.C0900f;
import com.p054c.p055a.p063b.p064a.C0901g;
import com.p054c.p055a.p063b.p064a.C0903i;
import com.p054c.p055a.p063b.p066b.C0906d;
import com.p054c.p055a.p063b.p066b.C0910e;
import com.p054c.p055a.p063b.p068d.C0920c;
import com.p054c.p055a.p063b.p068d.C0923d;
import com.p054c.p055a.p063b.p069e.C0925a;
import com.p054c.p055a.p063b.p070f.C0930a;
import com.p054c.p055a.p063b.p070f.C0931b;
import com.p054c.p055a.p072c.C0947e;
import com.p054c.p055a.p072c.C0957d;
import com.p054c.p055a.p072c.C0958f;
import java.io.Closeable;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.c.a.b.r */
final class C0948r implements C0947e, Runnable {
    private static final String f4989f = "ImageLoader is paused. Waiting...  [%s]";
    private static final String f4990g = ".. Resume loading [%s]";
    private static final String f4991h = "Delay %d ms before loading...  [%s]";
    private static final String f4992i = "Start display image task [%s]";
    private static final String f4993j = "Image already is loading. Waiting... [%s]";
    private static final String f4994k = "...Get cached bitmap from memory after waiting. [%s]";
    private static final String f4995l = "Load image from network [%s]";
    private static final String f4996m = "Load image from disk cache [%s]";
    private static final String f4997n = "Resize image in disk cache [%s]";
    private static final String f4998o = "PreProcess image before caching in memory [%s]";
    private static final String f4999p = "PostProcess image before displaying [%s]";
    private static final String f5000q = "Cache image in memory [%s]";
    private static final String f5001r = "Cache image on disk [%s]";
    private static final String f5002s = "Process image before cache on disk [%s]";
    private static final String f5003t = "ImageAware is reused for another image. Task is cancelled. [%s]";
    private static final String f5004u = "ImageAware was collected by GC. Task is cancelled. [%s]";
    private static final String f5005v = "Task was interrupted [%s]";
    private static final String f5006w = "No stream for image [%s]";
    private static final String f5007x = "Pre-processor returned null [%s]";
    private static final String f5008y = "Post-processor returned null [%s]";
    private static final String f5009z = "Bitmap processor for disk cache returned null [%s]";
    private final C0944o f5010A;
    private final C0946q f5011B;
    private final Handler f5012C;
    private final C0939j f5013D;
    private final C0920c f5014E;
    private final C0920c f5015F;
    private final C0920c f5016G;
    private final C0906d f5017H;
    private final String f5018I;
    private final C0900f f5019J;
    private final boolean f5020K;
    private C0901g f5021L;
    final String f5022a;
    final C0925a f5023b;
    final C0924d f5024c;
    final C0930a f5025d;
    final C0931b f5026e;

    public C0948r(C0944o c0944o, C0946q c0946q, Handler handler) {
        this.f5021L = C0901g.NETWORK;
        this.f5010A = c0944o;
        this.f5011B = c0946q;
        this.f5012C = handler;
        this.f5013D = c0944o.f4969a;
        this.f5014E = this.f5013D.f4930p;
        this.f5015F = this.f5013D.f4933s;
        this.f5016G = this.f5013D.f4934t;
        this.f5017H = this.f5013D.f4931q;
        this.f5022a = c0946q.f4981a;
        this.f5018I = c0946q.f4982b;
        this.f5023b = c0946q.f4983c;
        this.f5019J = c0946q.f4984d;
        this.f5024c = c0946q.f4985e;
        this.f5025d = c0946q.f4986f;
        this.f5026e = c0946q.f4987g;
        this.f5020K = this.f5024c.m7312s();
    }

    private Bitmap m7517a(String str) {
        String str2 = str;
        return this.f5017H.m7224a(new C0910e(this.f5018I, str2, this.f5022a, this.f5019J, this.f5023b.m7317c(), m7529h(), this.f5024c));
    }

    private void m7519a(C0897c c0897c, Throwable th) {
        if (!this.f5020K && !m7537p() && !m7531j()) {
            C0948r.m7520a(new C0950t(this, c0897c, th), false, this.f5012C, this.f5010A);
        }
    }

    static void m7520a(Runnable runnable, boolean z, Handler handler, C0944o c0944o) {
        if (z) {
            runnable.run();
        } else if (handler == null) {
            c0944o.m7506a(runnable);
        } else {
            handler.post(runnable);
        }
    }

    private boolean m7521b() {
        AtomicBoolean d = this.f5010A.m7512d();
        if (d.get()) {
            synchronized (this.f5010A.m7513e()) {
                if (d.get()) {
                    C0958f.m7554a(f4989f, this.f5018I);
                    try {
                        this.f5010A.m7513e().wait();
                        C0958f.m7554a(f4990g, this.f5018I);
                    } catch (InterruptedException e) {
                        C0958f.m7562d(f5005v, this.f5018I);
                        return true;
                    }
                }
            }
        }
        return m7531j();
    }

    private boolean m7522b(int i, int i2) {
        File a = this.f5013D.f4929o.m7028a(this.f5022a);
        if (a != null && a.exists()) {
            Bitmap a2 = this.f5017H.m7224a(new C0910e(this.f5018I, C0923d.FILE.m7269b(a.getAbsolutePath()), this.f5022a, new C0900f(i, i2), C0903i.FIT_INSIDE, m7529h(), new C0934f().m7383a(this.f5024c).m7381a(C0899e.IN_SAMPLE_INT).m7396d()));
            if (!(a2 == null || this.f5013D.f4920f == null)) {
                C0958f.m7554a(f5002s, this.f5018I);
                a2 = this.f5013D.f4920f.m7402a(a2);
                if (a2 == null) {
                    C0958f.m7562d(f5009z, this.f5018I);
                }
            }
            Bitmap bitmap = a2;
            if (bitmap != null) {
                boolean a3 = this.f5013D.f4929o.m7029a(this.f5022a, bitmap);
                bitmap.recycle();
                return a3;
            }
        }
        return false;
    }

    private boolean m7523c() {
        if (!this.f5024c.m7299f()) {
            return false;
        }
        C0958f.m7554a(f4991h, Integer.valueOf(this.f5024c.m7305l()), this.f5018I);
        try {
            Thread.sleep((long) this.f5024c.m7305l());
            return m7531j();
        } catch (InterruptedException e) {
            C0958f.m7562d(f5005v, this.f5018I);
            return true;
        }
    }

    private boolean m7524c(int i, int i2) {
        if (m7537p() || m7531j()) {
            return false;
        }
        if (this.f5026e != null) {
            C0948r.m7520a(new C0949s(this, i, i2), false, this.f5012C, this.f5010A);
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap m7525d() {
        /*
        r7 = this;
        r1 = 0;
        r0 = r7.f5013D;	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r0.f4929o;	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r2 = r7.f5022a;	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r0.m7028a(r2);	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        if (r0 == 0) goto L_0x00d9;
    L_0x000d:
        r2 = r0.exists();	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        if (r2 == 0) goto L_0x00d9;
    L_0x0013:
        r2 = r0.length();	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r4 = 0;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x00d9;
    L_0x001d:
        r2 = "Load image from disk cache [%s]";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r4 = 0;
        r5 = r7.f5018I;	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r3[r4] = r5;	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        com.p054c.p055a.p072c.C0958f.m7554a(r2, r3);	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r2 = com.p054c.p055a.p063b.p064a.C0901g.DISC_CACHE;	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r7.f5021L = r2;	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r7.m7530i();	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r2 = com.p054c.p055a.p063b.p068d.C0923d.FILE;	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r0.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r2.m7269b(r0);	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r7.m7517a(r0);	 Catch:{ IllegalStateException -> 0x00a0, v -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
    L_0x003f:
        if (r0 == 0) goto L_0x004d;
    L_0x0041:
        r2 = r0.getWidth();	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 <= 0) goto L_0x004d;
    L_0x0047:
        r2 = r0.getHeight();	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 > 0) goto L_0x009f;
    L_0x004d:
        r2 = "Load image from network [%s]";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r4 = 0;
        r5 = r7.f5018I;	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3[r4] = r5;	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        com.p054c.p055a.p072c.C0958f.m7554a(r2, r3);	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r2 = com.p054c.p055a.p063b.p064a.C0901g.NETWORK;	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r7.f5021L = r2;	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r2 = r7.f5022a;	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r7.f5024c;	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.m7302i();	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r3 == 0) goto L_0x0084;
    L_0x0068:
        r3 = r7.m7526e();	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r3 == 0) goto L_0x0084;
    L_0x006e:
        r3 = r7.f5013D;	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.f4929o;	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r4 = r7.f5022a;	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.m7028a(r4);	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r3 == 0) goto L_0x0084;
    L_0x007a:
        r2 = com.p054c.p055a.p063b.p068d.C0923d.FILE;	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r2 = r2.m7269b(r3);	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
    L_0x0084:
        r7.m7530i();	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r0 = r7.m7517a(r2);	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r0 == 0) goto L_0x0099;
    L_0x008d:
        r2 = r0.getWidth();	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 <= 0) goto L_0x0099;
    L_0x0093:
        r2 = r0.getHeight();	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 > 0) goto L_0x009f;
    L_0x0099:
        r2 = com.p054c.p055a.p063b.p064a.C0897c.DECODING_ERROR;	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = 0;
        r7.m7519a(r2, r3);	 Catch:{ IllegalStateException -> 0x00d7, v -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
    L_0x009f:
        return r0;
    L_0x00a0:
        r0 = move-exception;
        r0 = r1;
    L_0x00a2:
        r2 = com.p054c.p055a.p063b.p064a.C0897c.NETWORK_DENIED;
        r7.m7519a(r2, r1);
        goto L_0x009f;
    L_0x00a8:
        r0 = move-exception;
        throw r0;
    L_0x00aa:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00ae:
        com.p054c.p055a.p072c.C0958f.m7555a(r1);
        r2 = com.p054c.p055a.p063b.p064a.C0897c.IO_ERROR;
        r7.m7519a(r2, r1);
        goto L_0x009f;
    L_0x00b7:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00bb:
        com.p054c.p055a.p072c.C0958f.m7555a(r1);
        r2 = com.p054c.p055a.p063b.p064a.C0897c.OUT_OF_MEMORY;
        r7.m7519a(r2, r1);
        goto L_0x009f;
    L_0x00c4:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00c8:
        com.p054c.p055a.p072c.C0958f.m7555a(r1);
        r2 = com.p054c.p055a.p063b.p064a.C0897c.UNKNOWN;
        r7.m7519a(r2, r1);
        goto L_0x009f;
    L_0x00d1:
        r1 = move-exception;
        goto L_0x00c8;
    L_0x00d3:
        r1 = move-exception;
        goto L_0x00bb;
    L_0x00d5:
        r1 = move-exception;
        goto L_0x00ae;
    L_0x00d7:
        r2 = move-exception;
        goto L_0x00a2;
    L_0x00d9:
        r0 = r1;
        goto L_0x003f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.c.a.b.r.d():android.graphics.Bitmap");
    }

    private boolean m7526e() {
        C0958f.m7554a(f5001r, this.f5018I);
        try {
            boolean f = m7527f();
            if (!f) {
                return f;
            }
            int i = this.f5013D.f4918d;
            int i2 = this.f5013D.f4919e;
            if (i <= 0 && i2 <= 0) {
                return f;
            }
            C0958f.m7554a(f4997n, this.f5018I);
            m7522b(i, i2);
            return f;
        } catch (Throwable e) {
            C0958f.m7555a(e);
            return false;
        }
    }

    private boolean m7527f() {
        boolean z = false;
        Closeable a = m7529h().m7253a(this.f5022a, this.f5024c.m7307n());
        if (a == null) {
            C0958f.m7562d(f5006w, this.f5018I);
        } else {
            try {
                z = this.f5013D.f4929o.m7030a(this.f5022a, a, this);
            } finally {
                C0957d.m7547a(a);
            }
        }
        return z;
    }

    private void m7528g() {
        if (!this.f5020K && !m7537p()) {
            C0948r.m7520a(new C0951u(this), false, this.f5012C, this.f5010A);
        }
    }

    private C0920c m7529h() {
        return this.f5010A.m7514f() ? this.f5015F : this.f5010A.m7515g() ? this.f5016G : this.f5014E;
    }

    private void m7530i() {
        m7532k();
        m7534m();
    }

    private boolean m7531j() {
        return m7533l() || m7535n();
    }

    private void m7532k() {
        if (m7533l()) {
            throw new C0952v(this);
        }
    }

    private boolean m7533l() {
        if (!this.f5023b.m7319e()) {
            return false;
        }
        C0958f.m7554a(f5004u, this.f5018I);
        return true;
    }

    private void m7534m() {
        if (m7535n()) {
            throw new C0952v(this);
        }
    }

    private boolean m7535n() {
        if (!(!this.f5018I.equals(this.f5010A.m7500a(this.f5023b)))) {
            return false;
        }
        C0958f.m7554a(f5003t, this.f5018I);
        return true;
    }

    private void m7536o() {
        if (m7537p()) {
            throw new C0952v(this);
        }
    }

    private boolean m7537p() {
        if (!Thread.interrupted()) {
            return false;
        }
        C0958f.m7554a(f5005v, this.f5018I);
        return true;
    }

    String m7538a() {
        return this.f5022a;
    }

    public boolean m7539a(int i, int i2) {
        return this.f5020K || m7524c(i, i2);
    }

    public void run() {
        if (!m7521b() && !m7523c()) {
            ReentrantLock reentrantLock = this.f5011B.f4988h;
            C0958f.m7554a(f4992i, this.f5018I);
            if (reentrantLock.isLocked()) {
                C0958f.m7554a(f4993j, this.f5018I);
            }
            reentrantLock.lock();
            try {
                m7530i();
                Bitmap a = this.f5013D.f4928n.m7073a(this.f5018I);
                if (a == null || a.isRecycled()) {
                    a = m7525d();
                    if (a != null) {
                        m7530i();
                        m7536o();
                        if (this.f5024c.m7297d()) {
                            C0958f.m7554a(f4998o, this.f5018I);
                            a = this.f5024c.m7308o().m7402a(a);
                            if (a == null) {
                                C0958f.m7562d(f5007x, this.f5018I);
                            }
                        }
                        if (a != null && this.f5024c.m7301h()) {
                            C0958f.m7554a(f5000q, this.f5018I);
                            this.f5013D.f4928n.m7075a(this.f5018I, a);
                        }
                    } else {
                        return;
                    }
                }
                this.f5021L = C0901g.MEMORY_CACHE;
                C0958f.m7554a(f4994k, this.f5018I);
                if (a != null && this.f5024c.m7298e()) {
                    C0958f.m7554a(f4999p, this.f5018I);
                    a = this.f5024c.m7309p().m7402a(a);
                    if (a == null) {
                        C0958f.m7562d(f5008y, this.f5018I);
                    }
                }
                m7530i();
                m7536o();
                reentrantLock.unlock();
                C0948r.m7520a(new C0919c(a, this.f5011B, this.f5010A, this.f5021L), this.f5020K, this.f5012C, this.f5010A);
            } catch (C0952v e) {
                m7528g();
            } finally {
                reentrantLock.unlock();
            }
        }
    }
}
