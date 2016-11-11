package org.p004c.p198b.p202d;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.p007b.C3493a;
import org.p004c.p005e.p007b.C3495d;
import org.p004c.p198b.C3398b;

@Deprecated
/* renamed from: org.c.b.d.a */
public class C3407a {
    private C3495d f15909a;
    private C3445q f15910b;
    private C3507d f15911c;
    private final Runnable f15912d;

    public C3407a(C3495d c3495d, C3445q c3445q, C3507d c3507d, Runnable runnable) {
        this.f15909a = c3495d;
        this.f15910b = c3445q;
        this.f15911c = c3507d;
        this.f15912d = runnable;
    }

    private void m18674c() {
        try {
            for (Method invoke : this.f15910b.m18790b()) {
                invoke.invoke(null, new Object[0]);
            }
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (C3398b e2) {
            throw new C3431c();
        } catch (Throwable th) {
            m18677a(th);
            C3431c c3431c = new C3431c();
        }
    }

    private void m18675d() {
        for (Method invoke : this.f15910b.m18791c()) {
            try {
                invoke.invoke(null, new Object[0]);
            } catch (InvocationTargetException e) {
                m18677a(e.getTargetException());
            } catch (Throwable th) {
                m18677a(th);
            }
        }
    }

    protected void m18676a() {
        this.f15912d.run();
    }

    protected void m18677a(Throwable th) {
        this.f15909a.m19054a(new C3493a(this.f15911c, th));
    }

    public void m18678b() {
        try {
            m18674c();
            m18676a();
        } catch (C3431c e) {
        } finally {
            m18675d();
        }
    }
}
