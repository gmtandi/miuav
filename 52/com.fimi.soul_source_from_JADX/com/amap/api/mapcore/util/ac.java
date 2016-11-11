package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.ah.C0341a;
import com.amap.api.mapcore.util.de.C0336a;
import com.fimi.kernel.C1154b;
import java.io.File;
import java.io.IOException;

public class ac implements C0336a {
    ad f2080a;
    long f2081b;
    long f2082c;
    long f2083d;
    boolean f2084e;
    C0405x f2085f;
    long f2086g;
    C0335a f2087h;
    private Context f2088i;
    private ah f2089j;
    private String f2090k;
    private de f2091l;
    private C0406y f2092m;

    /* renamed from: com.amap.api.mapcore.util.ac.a */
    public interface C0335a {
        void m3365d();
    }

    public ac(ad adVar, String str, Context context, ah ahVar) {
        this.f2080a = null;
        this.f2081b = 0;
        this.f2082c = 0;
        this.f2084e = true;
        this.f2086g = 0;
        this.f2085f = C0405x.m4223a(context.getApplicationContext());
        this.f2080a = adVar;
        this.f2088i = context;
        this.f2090k = str;
        this.f2089j = ahVar;
        m3373g();
    }

    private void m3370a(int i) {
        System.err.println("Error Code : " + i);
    }

    private void m3371a(long j) {
        if (this.f2083d > 0 && this.f2089j != null) {
            this.f2089j.m3423a(this.f2083d, j);
            this.f2086g = System.currentTimeMillis();
        }
    }

    private void m3372f() {
        dj aiVar = new ai(this.f2090k);
        aiVar.m3432a(1800000);
        aiVar.m3435b(1800000);
        this.f2091l = new de(aiVar, this.f2081b, this.f2082c);
        this.f2092m = new C0406y(this.f2080a.m3388b() + File.separator + this.f2080a.m3389c(), this.f2081b);
    }

    private void m3373g() {
        if (this.f2085f.m4238f(this.f2080a.m3391e())) {
            this.f2084e = false;
            m3378l();
            return;
        }
        this.f2081b = 0;
        this.f2082c = 0;
    }

    private boolean m3374h() {
        return new File(new StringBuilder().append(this.f2080a.m3388b()).append(File.separator).append(this.f2080a.m3389c()).toString()).length() >= 10;
    }

    private void m3375i() {
        if (bm.f2214a != 1) {
            int i = 0;
            while (i < 3) {
                try {
                    if (!bm.m3660b(this.f2088i, bj.m3642e())) {
                        i++;
                    } else {
                        return;
                    }
                } catch (Throwable th) {
                    ce.m3829a(th, "SiteFileFetch", "authOffLineDownLoad");
                    th.printStackTrace();
                }
            }
        }
    }

    private void m3376j() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f2080a != null && currentTimeMillis - this.f2086g > 500) {
            m3377k();
            this.f2086g = currentTimeMillis;
            m3371a(this.f2081b);
        }
    }

    private void m3377k() {
        this.f2085f.m4231a(this.f2080a.m3391e(), this.f2080a.m3390d(), this.f2083d, this.f2081b, this.f2082c);
    }

    private boolean m3378l() {
        if (!this.f2085f.m4238f(this.f2080a.m3391e())) {
            return false;
        }
        this.f2083d = (long) this.f2085f.m4236d(this.f2080a.m3391e());
        long[] a = this.f2085f.m4233a(this.f2080a.m3391e(), 0);
        this.f2081b = a[0];
        this.f2082c = a[1];
        return true;
    }

    public void m3379a() {
        try {
            if (bj.m3640c(this.f2088i)) {
                m3375i();
                if (bm.f2214a == 1) {
                    if (!m3374h()) {
                        this.f2084e = true;
                    }
                    if (this.f2084e) {
                        this.f2083d = m3383b();
                        if (this.f2083d == -1) {
                            af.m3415a("File Length is not known!");
                        } else if (this.f2083d == -2) {
                            af.m3415a("File is not access!");
                        } else {
                            this.f2082c = this.f2083d;
                        }
                        this.f2081b = 0;
                    }
                    if (this.f2089j != null) {
                        this.f2089j.m3425m();
                    }
                    m3372f();
                    this.f2091l.m4009a(this);
                } else if (this.f2089j != null) {
                    this.f2089j.m3424a(C0341a.amap_exception);
                }
            } else if (this.f2089j != null) {
                this.f2089j.m3424a(C0341a.network_exception);
            }
        } catch (Throwable e) {
            ce.m3829a(e, "SiteFileFetch", C1154b.f5230a);
            if (this.f2089j != null) {
                this.f2089j.m3424a(C0341a.amap_exception);
            }
        } catch (IOException e2) {
            if (this.f2089j != null) {
                this.f2089j.m3424a(C0341a.file_io_exception);
            }
        }
    }

    public void m3380a(C0335a c0335a) {
        this.f2087h = c0335a;
    }

    public void m3381a(Throwable th) {
        if (this.f2089j != null) {
            this.f2089j.m3424a(C0341a.network_exception);
        }
        if (!(th instanceof IOException) && this.f2092m != null) {
            this.f2092m.m4240a();
        }
    }

    public void m3382a(byte[] bArr, long j) {
        try {
            this.f2092m.m4239a(bArr);
            this.f2081b = j;
            m3376j();
        } catch (Throwable e) {
            e.printStackTrace();
            ce.m3829a(e, "fileAccessI", "fileAccessI.write(byte[] data)");
            if (this.f2089j != null) {
                this.f2089j.m3424a(C0341a.file_io_exception);
            }
            if (this.f2091l != null) {
                this.f2091l.m4008a();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long m3383b() {
        /*
        r5 = this;
        r2 = -1;
        r0 = new java.net.URL;
        r1 = r5.f2080a;
        r1 = r1.m3387a();
        r0.<init>(r1);
        r0 = r0.openConnection();
        r0 = (java.net.HttpURLConnection) r0;
        r1 = "User-Agent";
        r3 = com.amap.api.mapcore.C0330s.f2071d;
        r0.setRequestProperty(r1, r3);
        r1 = r0.getResponseCode();
        r3 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r1 < r3) goto L_0x0027;
    L_0x0021:
        r5.m3370a(r1);
        r0 = -2;
    L_0x0026:
        return r0;
    L_0x0027:
        r1 = 1;
    L_0x0028:
        r3 = r0.getHeaderFieldKey(r1);
        if (r3 == 0) goto L_0x0043;
    L_0x002e:
        r4 = "Content-Length";
        r4 = r3.equalsIgnoreCase(r4);
        if (r4 == 0) goto L_0x0040;
    L_0x0036:
        r0 = r0.getHeaderField(r3);
        r0 = java.lang.Integer.parseInt(r0);
    L_0x003e:
        r0 = (long) r0;
        goto L_0x0026;
    L_0x0040:
        r1 = r1 + 1;
        goto L_0x0028;
    L_0x0043:
        r0 = r2;
        goto L_0x003e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ac.b():long");
    }

    public void m3384c() {
        if (this.f2091l != null) {
            this.f2091l.m4008a();
        }
    }

    public void m3385d() {
        if (this.f2089j != null) {
            this.f2089j.m3427o();
        }
        m3377k();
    }

    public void m3386e() {
        m3376j();
        if (this.f2089j != null) {
            this.f2089j.m3426n();
        }
        if (this.f2092m != null) {
            this.f2092m.m4240a();
        }
        if (this.f2087h != null) {
            this.f2087h.m3365d();
        }
    }
}
