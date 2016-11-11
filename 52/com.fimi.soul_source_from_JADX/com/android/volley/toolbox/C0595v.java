package com.android.volley.toolbox;

import android.graphics.Bitmap;
import com.android.volley.C0570r;
import com.android.volley.ag;
import java.util.LinkedList;

/* renamed from: com.android.volley.toolbox.v */
class C0595v {
    final /* synthetic */ C0590q f3649a;
    private final C0570r<?> f3650b;
    private Bitmap f3651c;
    private ag f3652d;
    private final LinkedList<C0597x> f3653e;

    public C0595v(C0590q c0590q, C0570r<?> c0570r, C0597x c0597x) {
        this.f3649a = c0590q;
        this.f3653e = new LinkedList();
        this.f3650b = c0570r;
        this.f3653e.add(c0597x);
    }

    public ag m5261a() {
        return this.f3652d;
    }

    public void m5262a(ag agVar) {
        this.f3652d = agVar;
    }

    public void m5263a(C0597x c0597x) {
        this.f3653e.add(c0597x);
    }

    public boolean m5264b(C0597x c0597x) {
        this.f3653e.remove(c0597x);
        if (this.f3653e.size() != 0) {
            return false;
        }
        this.f3650b.m5121l();
        return true;
    }
}
