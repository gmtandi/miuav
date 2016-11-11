package org.p004c.p198b.p202d;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.p007b.C3493a;
import org.p004c.p005e.p007b.C3495d;
import org.p004c.p198b.C3398b;

@Deprecated
/* renamed from: org.c.b.d.k */
public class C3439k {
    private final Object f15955a;
    private final C3495d f15956b;
    private final C3507d f15957c;
    private C3446r f15958d;

    public C3439k(Object obj, C3446r c3446r, C3495d c3495d, C3507d c3507d) {
        this.f15955a = obj;
        this.f15956b = c3495d;
        this.f15957c = c3507d;
        this.f15958d = c3446r;
    }

    private void m18769a(long j) {
        m18773a(new C3440l(this, j));
    }

    private void m18770d() {
        try {
            for (Method invoke : this.f15958d.m18801e()) {
                invoke.invoke(this.f15955a, new Object[0]);
            }
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (C3398b e2) {
            throw new C3431c();
        } catch (Throwable th) {
            m18774a(th);
            C3431c c3431c = new C3431c();
        }
    }

    private void m18771e() {
        for (Method invoke : this.f15958d.m18802f()) {
            try {
                invoke.invoke(this.f15955a, new Object[0]);
            } catch (InvocationTargetException e) {
                m18774a(e.getTargetException());
            } catch (Throwable th) {
                m18774a(th);
            }
        }
    }

    public void m18772a() {
        if (this.f15958d.m18796a()) {
            this.f15956b.m19062c(this.f15957c);
            return;
        }
        this.f15956b.m19060b(this.f15957c);
        try {
            long b = this.f15958d.m18798b();
            if (b > 0) {
                m18769a(b);
            } else {
                m18775b();
            }
            this.f15956b.m19064d(this.f15957c);
        } catch (Throwable th) {
            this.f15956b.m19064d(this.f15957c);
        }
    }

    public void m18773a(Runnable runnable) {
        try {
            m18770d();
            runnable.run();
            m18771e();
        } catch (C3431c e) {
            m18771e();
        } catch (Exception e2) {
            throw new RuntimeException("test should never throw an exception to this level");
        } catch (Throwable th) {
            m18771e();
        }
    }

    protected void m18774a(Throwable th) {
        this.f15956b.m19054a(new C3493a(this.f15957c, th));
    }

    public void m18775b() {
        m18773a(new C3442n(this));
    }

    protected void m18776c() {
        Throwable targetException;
        try {
            this.f15958d.m18795a(this.f15955a);
            if (this.f15958d.m18800d()) {
                m18774a(new AssertionError("Expected exception: " + this.f15958d.m18799c().getName()));
            }
        } catch (InvocationTargetException e) {
            targetException = e.getTargetException();
            if (!(targetException instanceof C3398b)) {
                if (!this.f15958d.m18800d()) {
                    m18774a(targetException);
                } else if (this.f15958d.m18797a(targetException)) {
                    m18774a(new Exception("Unexpected exception, expected<" + this.f15958d.m18799c().getName() + "> but was<" + targetException.getClass().getName() + ">", targetException));
                }
            }
        } catch (Throwable targetException2) {
            m18774a(targetException2);
        }
    }
}
