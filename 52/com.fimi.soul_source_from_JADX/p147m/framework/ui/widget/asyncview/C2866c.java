package p147m.framework.ui.widget.asyncview;

import android.graphics.Bitmap;
import java.io.File;
import java.util.Vector;
import java.util.WeakHashMap;

/* renamed from: m.framework.ui.widget.asyncview.c */
public class C2866c {
    private static final int f14612a = 5;
    private static final int f14613b = 200;
    private static final int f14614c = 40;
    private static final int f14615d = 50;
    private static C2866c f14616e;
    private WeakHashMap<String, Bitmap> f14617f;
    private boolean f14618g;
    private Vector<C2867d> f14619h;
    private File f14620i;
    private C2871h[] f14621j;
    private Vector<C2867d> f14622k;

    private C2866c(String str) {
        this.f14619h = new Vector();
        this.f14622k = new Vector();
        this.f14621j = new C2871h[f14612a];
        this.f14617f = new WeakHashMap();
        this.f14620i = new File(str);
        if (!this.f14620i.exists()) {
            this.f14620i.mkdirs();
        }
        C2868e c2868e = new C2868e(this);
    }

    public static void m16537a() {
        if (f14616e == null) {
            throw new RuntimeException("Call BitmapProcessor.prepare(String) before start");
        }
        f14616e.f14618g = true;
    }

    public static synchronized void m16538a(String str) {
        synchronized (C2866c.class) {
            if (f14616e == null) {
                f14616e = new C2866c(str);
            }
        }
    }

    public static void m16539a(String str, C2865b c2865b) {
        if (f14616e == null) {
            throw new RuntimeException("Call BitmapProcessor.prepare(String) before start");
        } else if (str != null) {
            C2867d c2867d = new C2867d();
            c2867d.f14623a = str;
            c2867d.f14624b = c2865b;
            f14616e.f14619h.add(c2867d);
            if (f14616e.f14619h.size() > f14615d) {
                while (f14616e.f14619h.size() > f14614c) {
                    f14616e.f14619h.remove(0);
                }
            }
            C2866c.m16537a();
        }
    }

    public static Bitmap m16541b(String str) {
        if (f14616e != null) {
            return (Bitmap) f14616e.f14617f.get(str);
        }
        throw new RuntimeException("Call BitmapProcessor.prepare(String) before start");
    }

    public static void m16542b() {
        int i = 0;
        if (f14616e != null) {
            f14616e.f14618g = false;
            f14616e.f14619h.clear();
            while (i < f14616e.f14621j.length) {
                if (f14616e.f14621j[i] != null) {
                    f14616e.f14621j[i].interrupt();
                }
                i++;
            }
            f14616e = null;
        }
    }
}
