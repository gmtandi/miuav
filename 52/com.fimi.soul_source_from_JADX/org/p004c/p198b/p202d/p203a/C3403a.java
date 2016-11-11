package org.p004c.p198b.p202d.p203a;

import org.p004c.p005e.C3507d;
import org.p004c.p005e.p007b.C3493a;
import org.p004c.p005e.p007b.C3495d;
import org.p004c.p187f.p192a.C3404g;
import org.p004c.p198b.C3398b;

/* renamed from: org.c.b.d.a.a */
public class C3403a {
    private final C3495d f15906a;
    private final C3507d f15907b;

    public C3403a(C3495d c3495d, C3507d c3507d) {
        this.f15906a = c3495d;
        this.f15907b = c3507d;
    }

    private void m18664a(C3404g c3404g) {
        for (Throwable a : c3404g.m18671a()) {
            m18666a(a);
        }
    }

    public void m18665a() {
        this.f15906a.m19064d(this.f15907b);
    }

    public void m18666a(Throwable th) {
        if (th instanceof C3404g) {
            m18664a((C3404g) th);
        } else {
            this.f15906a.m19054a(new C3493a(this.f15907b, th));
        }
    }

    public void m18667a(C3398b c3398b) {
        this.f15906a.m19058b(new C3493a(this.f15907b, c3398b));
    }

    public void m18668b() {
        this.f15906a.m19060b(this.f15907b);
    }

    public void m18669c() {
        this.f15906a.m19062c(this.f15907b);
    }
}
