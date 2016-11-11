package org.p004c.p005e.p007b;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import org.p004c.p005e.C3507d;

/* renamed from: org.c.e.b.a */
public class C3493a implements Serializable {
    private static final long serialVersionUID = 1;
    private final C3507d f16018a;
    private final Throwable f16019b;

    public C3493a(C3507d c3507d, Throwable th) {
        this.f16019b = th;
        this.f16018a = c3507d;
    }

    public String m19045a() {
        return this.f16018a.m19091a();
    }

    public C3507d m19046b() {
        return this.f16018a;
    }

    public Throwable m19047c() {
        return this.f16019b;
    }

    public String m19048d() {
        Writer stringWriter = new StringWriter();
        m19047c().printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public String m19049e() {
        return m19047c().getMessage();
    }

    public String toString() {
        return m19045a() + ": " + this.f16019b.getMessage();
    }
}
