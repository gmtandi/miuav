package it.sephiroth.android.library.widget;

/* renamed from: it.sephiroth.android.library.widget.o */
class C2836o implements Runnable {
    final /* synthetic */ int f14569a;
    final /* synthetic */ int f14570b;
    final /* synthetic */ int f14571c;
    final /* synthetic */ C2833l f14572d;

    C2836o(C2833l c2833l, int i, int i2, int i3) {
        this.f14572d = c2833l;
        this.f14569a = i;
        this.f14570b = i2;
        this.f14571c = i3;
    }

    public void run() {
        this.f14572d.m16387a(this.f14569a, this.f14570b, this.f14571c);
    }
}
