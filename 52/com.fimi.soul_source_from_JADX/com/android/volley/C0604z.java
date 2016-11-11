package com.android.volley;

/* renamed from: com.android.volley.z */
public class C0604z<T> {
    public final T f3687a;
    public final C0555c f3688b;
    public final ag f3689c;
    public boolean f3690d;

    private C0604z(ag agVar) {
        this.f3690d = false;
        this.f3687a = null;
        this.f3688b = null;
        this.f3689c = agVar;
    }

    private C0604z(T t, C0555c c0555c) {
        this.f3690d = false;
        this.f3687a = t;
        this.f3688b = c0555c;
        this.f3689c = null;
    }

    public static <T> C0604z<T> m5292a(ag agVar) {
        return new C0604z(agVar);
    }

    public static <T> C0604z<T> m5293a(T t, C0555c c0555c) {
        return new C0604z(t, c0555c);
    }

    public boolean m5294a() {
        return this.f3689c == null;
    }
}
