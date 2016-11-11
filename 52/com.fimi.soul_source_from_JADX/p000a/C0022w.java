package p000a;

import java.util.concurrent.Callable;

/* renamed from: a.w */
final class C0022w implements Runnable {
    final /* synthetic */ ae f102a;
    final /* synthetic */ Callable f103b;

    C0022w(ae aeVar, Callable callable) {
        this.f102a = aeVar;
        this.f103b = callable;
    }

    public void run() {
        try {
            this.f102a.m9b(this.f103b.call());
        } catch (Exception e) {
            this.f102a.m8b(e);
        }
    }
}
