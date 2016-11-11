package org.p004c.p005e.p007b;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.C3515n;

/* renamed from: org.c.e.b.d */
public class C3495d {
    private final List<C0133b> f16020a;
    private volatile boolean f16021b;

    public C3495d() {
        this.f16020a = new CopyOnWriteArrayList();
        this.f16021b = false;
    }

    private void m19051a(List<C0133b> list, List<C3493a> list2) {
        if (!list2.isEmpty()) {
            new C3500h(this, list, list2).m19065a();
        }
    }

    public void m19053a() {
        this.f16021b = true;
    }

    public void m19054a(C3493a c3493a) {
        m19051a(this.f16020a, Arrays.asList(new C3493a[]{c3493a}));
    }

    public void m19055a(C0133b c0133b) {
        if (c0133b == null) {
            throw new NullPointerException("Cannot add a null listener");
        }
        this.f16020a.add(m19061c(c0133b));
    }

    public void m19056a(C3507d c3507d) {
        new C3497e(this, c3507d).m19065a();
    }

    public void m19057a(C3515n c3515n) {
        new C3498f(this, c3515n).m19065a();
    }

    public void m19058b(C3493a c3493a) {
        new C3501i(this, c3493a).m19065a();
    }

    public void m19059b(C0133b c0133b) {
        if (c0133b == null) {
            throw new NullPointerException("Cannot remove a null listener");
        }
        this.f16020a.remove(m19061c(c0133b));
    }

    public void m19060b(C3507d c3507d) {
        if (this.f16021b) {
            throw new C3504m();
        }
        new C3499g(this, c3507d).m19065a();
    }

    C0133b m19061c(C0133b c0133b) {
        return c0133b.getClass().isAnnotationPresent(C3494c.class) ? c0133b : new C3505n(c0133b, this);
    }

    public void m19062c(C3507d c3507d) {
        new C3502j(this, c3507d).m19065a();
    }

    public void m19063d(C0133b c0133b) {
        if (c0133b == null) {
            throw new NullPointerException("Cannot add a null listener");
        }
        this.f16020a.add(0, m19061c(c0133b));
    }

    public void m19064d(C3507d c3507d) {
        new C3503k(this, c3507d).m19065a();
    }
}
