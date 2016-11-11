package org.p004c.p198b.p200b;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import org.p184b.C3275p;
import org.p184b.C3295u;
import org.p184b.C3300k;
import org.p184b.C3315n;

/* renamed from: org.c.b.b.a */
public class C3394a<T extends Throwable> extends C3295u<T> {
    private final C3275p<T> f15890a;

    public C3394a(C3275p<T> c3275p) {
        this.f15890a = c3275p;
    }

    @C3315n
    public static <T extends Throwable> C3275p<T> m18635a(C3275p<T> c3275p) {
        return new C3394a(c3275p);
    }

    private String m18636b(Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    @C3315n
    public static <T extends Exception> C3275p<T> m18637b(C3275p<T> c3275p) {
        return new C3394a(c3275p);
    }

    protected void m18638a(T t, C3300k c3300k) {
        this.f15890a.m18106a(t, c3300k);
        c3300k.m18222a("\nStacktrace was: ");
        c3300k.m18222a(m18636b((Throwable) t));
    }

    public void m18639a(C3300k c3300k) {
        this.f15890a.m18104a(c3300k);
    }

    protected boolean m18640a(T t) {
        return this.f15890a.m18107a(t);
    }

    protected /* synthetic */ void m18641b(Object obj, C3300k c3300k) {
        m18638a((Throwable) obj, c3300k);
    }

    protected /* synthetic */ boolean m18642b(Object obj) {
        return m18640a((Throwable) obj);
    }
}
