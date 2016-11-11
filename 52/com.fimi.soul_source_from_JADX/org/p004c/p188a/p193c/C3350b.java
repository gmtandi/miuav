package org.p004c.p188a.p193c;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import org.p004c.p005e.C3340l;
import org.p004c.p005e.C3513k;
import org.p004c.p005e.C3515n;
import org.p004c.p005e.p007b.C3493a;
import org.p004c.p198b.C3455l;

/* renamed from: org.c.a.c.b */
public class C3350b {
    private C3515n f15855a;

    public C3350b(List<C3493a> list) {
        this(new C3349a(list).m18461a());
    }

    private C3350b(C3515n c3515n) {
        this.f15855a = c3515n;
    }

    public static C3350b m18462a(Class<?> cls) {
        return C3350b.m18463a(C3340l.m18428a((Class) cls));
    }

    public static C3350b m18463a(C3340l c3340l) {
        return new C3350b(new C3513k().m19128a(c3340l));
    }

    public int m18464a() {
        return this.f15855a.m19143d().size();
    }

    public String toString() {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new C3455l(new PrintStream(byteArrayOutputStream)).m18821a(this.f15855a);
        return byteArrayOutputStream.toString();
    }
}
