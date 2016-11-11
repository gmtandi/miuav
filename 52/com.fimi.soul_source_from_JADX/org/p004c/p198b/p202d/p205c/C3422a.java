package org.p004c.p198b.p202d.p205c;

import org.p004c.p187f.p192a.C3377k;
import org.p004c.p198b.C3398b;

/* renamed from: org.c.b.d.c.a */
public class C3422a extends C3377k {
    private final C3377k f15925a;
    private final Class<? extends Throwable> f15926b;

    public C3422a(C3377k c3377k, Class<? extends Throwable> cls) {
        this.f15925a = c3377k;
        this.f15926b = cls;
    }

    public void m18710a() {
        Object obj;
        try {
            this.f15925a.m18589a();
            obj = 1;
        } catch (C3398b e) {
            throw e;
        } catch (Throwable th) {
            if (this.f15926b.isAssignableFrom(th.getClass())) {
                obj = null;
            } else {
                Exception exception = new Exception("Unexpected exception, expected<" + this.f15926b.getName() + "> but was<" + th.getClass().getName() + ">", th);
            }
        }
        if (obj != null) {
            throw new AssertionError("Expected exception: " + this.f15926b.getName());
        }
    }
}
