package p001b.p003a;

import p001b.p002b.C0115j;
import p001b.p002b.C0139n;

/* renamed from: b.a.b */
class C0118b extends Thread {
    final /* synthetic */ C0115j f124a;
    final /* synthetic */ C0139n f125b;
    final /* synthetic */ C0117a f126c;

    C0118b(C0117a c0117a, C0115j c0115j, C0139n c0139n) {
        this.f126c = c0117a;
        this.f124a = c0115j;
        this.f125b = c0139n;
    }

    public void run() {
        try {
            this.f124a.m129a(this.f125b);
        } finally {
            this.f126c.m151b();
        }
    }
}
