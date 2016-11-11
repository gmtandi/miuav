package com.baidu.tts.chainofresponsibility.logger;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.baidu.tts.chainofresponsibility.logger.e */
public class C0756e {
    private List<String> f4211a;
    private List<String> f4212b;

    public C0756e() {
        this.f4211a = new ArrayList();
        this.f4212b = new ArrayList();
    }

    public void m6543a() {
        if (this.f4211a != null) {
            this.f4211a.clear();
        }
        if (this.f4212b != null) {
            this.f4212b.clear();
        }
    }

    public void m6544a(C0751c c0751c, Void voidR, C0755d c0755d) {
        Object obj;
        String b = c0751c.m6523b();
        String c = c0751c.m6525c();
        String d = c0751c.m6526d();
        d = d != null ? "(" + d + ")" + c : c;
        if (this.f4212b == null || this.f4212b.isEmpty()) {
            int i = 1;
        } else if (this.f4212b.contains(b)) {
            obj = null;
        } else {
            obj = 1;
            for (String c2 : this.f4212b) {
                Object obj2 = (c2 == null || d == null || !d.contains(c2)) ? obj : null;
                obj = obj2;
            }
        }
        if (!(this.f4211a == null || this.f4211a.isEmpty())) {
            if (this.f4211a.contains(b)) {
                obj = 1;
            } else {
                for (String c22 : this.f4211a) {
                    if (!(c22 == null || d == null)) {
                        obj = d.contains(c22) ? 1 : null;
                    }
                }
            }
        }
        if (obj != null) {
            Log.println(c0751c.m6520a(), "bdtts-" + b, d);
        }
    }

    public void m6545a(String str) {
        if (this.f4211a != null && !this.f4211a.contains(str)) {
            this.f4211a.add(str);
        }
    }

    public void m6546a(List<String> list) {
        if (this.f4211a != null) {
            this.f4211a.addAll(list);
        }
    }

    public void m6547b(String str) {
        if (this.f4212b != null && !this.f4212b.contains(str)) {
            this.f4212b.add(str);
        }
    }
}
