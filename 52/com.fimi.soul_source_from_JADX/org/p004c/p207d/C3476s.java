package org.p004c.p207d;

import java.util.List;
import org.p004c.C3520e;
import org.p004c.p005e.C3507d;
import org.p004c.p187f.p192a.C3377k;
import org.p004c.p198b.C3398b;

/* renamed from: org.c.d.s */
public abstract class C3476s implements C3460r {
    private void m18966a(Throwable th, C3507d c3507d, List<Throwable> list) {
        try {
            m18977a(th, c3507d);
        } catch (Throwable th2) {
            list.add(th2);
        }
    }

    private void m18967a(C3398b c3398b, C3507d c3507d, List<Throwable> list) {
        try {
            if (c3398b instanceof C3520e) {
                m18980a((C3520e) c3398b, c3507d);
            } else {
                m18978a(c3398b, c3507d);
            }
        } catch (Throwable th) {
            list.add(th);
        }
    }

    private void m18971a(C3507d c3507d, List<Throwable> list) {
        try {
            m18982c(c3507d);
        } catch (Throwable th) {
            list.add(th);
        }
    }

    private void m18973b(C3507d c3507d, List<Throwable> list) {
        try {
            m18979a(c3507d);
        } catch (Throwable th) {
            list.add(th);
        }
    }

    private void m18975c(C3507d c3507d, List<Throwable> list) {
        try {
            m18981b(c3507d);
        } catch (Throwable th) {
            list.add(th);
        }
    }

    public C3377k m18976a(C3377k c3377k, C3507d c3507d) {
        return new C3480t(this, c3507d, c3377k);
    }

    protected void m18977a(Throwable th, C3507d c3507d) {
    }

    @Deprecated
    protected void m18978a(C3398b c3398b, C3507d c3507d) {
    }

    protected void m18979a(C3507d c3507d) {
    }

    protected void m18980a(C3520e c3520e, C3507d c3507d) {
        m18978a((C3398b) c3520e, c3507d);
    }

    protected void m18981b(C3507d c3507d) {
    }

    protected void m18982c(C3507d c3507d) {
    }
}
