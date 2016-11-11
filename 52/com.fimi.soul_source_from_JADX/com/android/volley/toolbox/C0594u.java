package com.android.volley.toolbox;

import java.util.Iterator;

/* renamed from: com.android.volley.toolbox.u */
class C0594u implements Runnable {
    final /* synthetic */ C0590q f3648a;

    C0594u(C0590q c0590q) {
        this.f3648a = c0590q;
    }

    public void run() {
        for (C0595v c0595v : this.f3648a.f3638e.values()) {
            Iterator it = c0595v.f3653e.iterator();
            while (it.hasNext()) {
                C0597x c0597x = (C0597x) it.next();
                if (c0597x.f3656c != null) {
                    if (c0595v.m5261a() == null) {
                        c0597x.f3655b = c0595v.f3651c;
                        c0597x.f3656c.m5155a(c0597x, false);
                    } else {
                        c0597x.f3656c.m5047a(c0595v.m5261a());
                    }
                }
            }
        }
        this.f3648a.f3638e.clear();
        this.f3648a.f3640g = null;
    }
}
