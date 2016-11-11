package org.p004c.p005e.p007b;

import org.p004c.p005e.C3507d;
import org.p004c.p005e.C3515n;

@C3494c
/* renamed from: org.c.e.b.n */
final class C3505n extends C0133b {
    private final C0133b f16038a;
    private final Object f16039b;

    C3505n(C0133b c0133b, Object obj) {
        this.f16038a = c0133b;
        this.f16039b = obj;
    }

    public void m19074a(C3493a c3493a) {
        synchronized (this.f16039b) {
            this.f16038a.m228a(c3493a);
        }
    }

    public void m19075a(C3507d c3507d) {
        synchronized (this.f16039b) {
            this.f16038a.m229a(c3507d);
        }
    }

    public void m19076a(C3515n c3515n) {
        synchronized (this.f16039b) {
            this.f16038a.m230a(c3515n);
        }
    }

    public void m19077b(C3493a c3493a) {
        synchronized (this.f16039b) {
            this.f16038a.m231b(c3493a);
        }
    }

    public void m19078b(C3507d c3507d) {
        synchronized (this.f16039b) {
            this.f16038a.m232b(c3507d);
        }
    }

    public void m19079c(C3507d c3507d) {
        synchronized (this.f16039b) {
            this.f16038a.m233c(c3507d);
        }
    }

    public void m19080d(C3507d c3507d) {
        synchronized (this.f16039b) {
            this.f16038a.m234d(c3507d);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3505n)) {
            return false;
        }
        return this.f16038a.equals(((C3505n) obj).f16038a);
    }

    public int hashCode() {
        return this.f16038a.hashCode();
    }

    public String toString() {
        return this.f16038a.toString() + " (with synchronization wrapper)";
    }
}
