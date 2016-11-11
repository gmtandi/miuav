package com.mining.app.zxing.p126a;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import com.fimi.soul.module.setting.newhand.C1873o;
import java.io.IOException;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;

/* renamed from: com.mining.app.zxing.a.c */
public final class C2124c {
    static final int f11159a;
    private static final String f11160b;
    private static final int f11161c = 240;
    private static final int f11162d = 240;
    private static final int f11163e = 480;
    private static final int f11164f = 360;
    private static C2124c f11165g;
    private final Context f11166h;
    private final C2123b f11167i;
    private Camera f11168j;
    private Rect f11169k;
    private Rect f11170l;
    private Rect f11171m;
    private boolean f11172n;
    private boolean f11173o;
    private final boolean f11174p;
    private final C2127f f11175q;
    private final C2122a f11176r;
    private int f11177s;

    static {
        int parseInt;
        f11160b = C2124c.class.getSimpleName();
        try {
            parseInt = Integer.parseInt(VERSION.SDK);
        } catch (NumberFormatException e) {
            parseInt = C1873o.ak;
        }
        f11159a = parseInt;
    }

    private C2124c(Context context) {
        this.f11166h = context;
        this.f11167i = new C2123b(context);
        this.f11174p = Integer.parseInt(VERSION.SDK) > 3;
        this.f11175q = new C2127f(this.f11167i, this.f11174p);
        this.f11176r = new C2122a();
    }

    public static C2124c m13071a() {
        return f11165g;
    }

    public static void m13072a(Context context) {
        if (f11165g == null) {
            f11165g = new C2124c(context);
        }
    }

    public int m13073a(int i) {
        return (i * 58) / Opcodes.IDIV;
    }

    public C2126e m13074a(byte[] bArr, int i, int i2) {
        Rect f = m13083f();
        int c = this.f11167i.m13069c();
        String d = this.f11167i.m13070d();
        switch (c) {
            case Segment.TOKENS_PER_SEGMENT /*16*/:
            case Opcodes.SIPUSH /*17*/:
                return new C2126e(bArr, i, i2, f.left, f.top, f.width(), f.height());
            default:
                if ("yuv420p".equals(d)) {
                    return new C2126e(bArr, i, i2, f.left, f.top, f.width(), f.height());
                }
                throw new IllegalArgumentException("Unsupported picture format: " + c + '/' + d);
        }
    }

    public void m13075a(Handler handler, int i) {
        if (this.f11168j != null && this.f11173o) {
            this.f11175q.m13096a(handler, i);
            if (this.f11174p) {
                this.f11168j.setOneShotPreviewCallback(this.f11175q);
            } else {
                this.f11168j.setPreviewCallback(this.f11175q);
            }
        }
    }

    public void m13076a(SurfaceHolder surfaceHolder) {
        if (this.f11168j == null) {
            this.f11168j = Camera.open();
            if (this.f11168j == null) {
                throw new IOException();
            }
            this.f11168j.setPreviewDisplay(surfaceHolder);
            if (!this.f11172n) {
                this.f11172n = true;
                this.f11167i.m13065a(this.f11168j);
            }
            this.f11167i.m13068b(this.f11168j);
            C2125d.m13091b();
        }
    }

    public int m13077b(int i) {
        return (i * 42) / SmileConstants.TOKEN_PREFIX_SMALL_INT;
    }

    public void m13078b() {
        if (this.f11168j != null) {
            C2125d.m13091b();
            this.f11168j.release();
            this.f11168j = null;
        }
    }

    public void m13079b(Handler handler, int i) {
        if (this.f11168j != null && this.f11173o) {
            this.f11176r.m13057a(handler, i);
            this.f11168j.autoFocus(this.f11176r);
        }
    }

    public void m13080c() {
        if (this.f11168j != null && !this.f11173o) {
            this.f11168j.startPreview();
            this.f11173o = true;
        }
    }

    public void m13081d() {
        if (this.f11168j != null && this.f11173o) {
            if (!this.f11174p) {
                this.f11168j.setPreviewCallback(null);
            }
            this.f11168j.stopPreview();
            this.f11175q.m13096a(null, f11159a);
            this.f11176r.m13057a(null, f11159a);
            this.f11173o = false;
        }
    }

    public Rect m13082e() {
        Point b = this.f11167i.m13067b();
        if (this.f11169k == null) {
            if (this.f11168j == null) {
                return null;
            }
            int a = m13073a(b.x);
            this.f11177s = m13077b(b.y);
            int i = (b.x - a) / 2;
            int i2 = ((b.y - this.f11177s) - a) / 2;
            this.f11169k = new Rect(i, i2, i + a, a + i2);
            Log.d(f11160b, "Calculated framing rect: " + this.f11169k);
        }
        return this.f11169k;
    }

    public Rect m13083f() {
        if (this.f11171m == null) {
            Rect rect = new Rect(m13082e());
            Point a = this.f11167i.m13064a();
            Point b = this.f11167i.m13067b();
            rect.left = (rect.left * a.y) / b.x;
            rect.right = (rect.right * a.y) / b.x;
            rect.top = (rect.top * a.x) / b.y;
            rect.bottom = (a.x * rect.bottom) / b.y;
            this.f11171m = rect;
        }
        return this.f11171m;
    }

    public Context m13084g() {
        return this.f11166h;
    }
}
