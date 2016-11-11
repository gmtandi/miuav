package com.android.volley;

/* renamed from: com.android.volley.f */
public class C0558f implements ad {
    public static final int f3521a = 2500;
    public static final int f3522b = 0;
    public static final float f3523c = 1.0f;
    private int f3524d;
    private int f3525e;
    private final int f3526f;
    private final float f3527g;

    public C0558f() {
        this(f3521a, f3522b, f3523c);
    }

    public C0558f(int i, int i2, float f) {
        this.f3524d = i;
        this.f3526f = i2;
        this.f3527g = f;
    }

    public int m5076a() {
        return this.f3524d;
    }

    public void m5077a(ag agVar) {
        this.f3525e++;
        this.f3524d = (int) (((float) this.f3524d) + (((float) this.f3524d) * this.f3527g));
        if (!m5080d()) {
            throw agVar;
        }
    }

    public int m5078b() {
        return this.f3525e;
    }

    public float m5079c() {
        return this.f3527g;
    }

    protected boolean m5080d() {
        return this.f3525e <= this.f3526f;
    }
}
