package p001b.p002b;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/* renamed from: b.b.l */
public class C0137l {
    protected C0115j f150a;
    protected Throwable f151b;

    public C0137l(C0115j c0115j, Throwable th) {
        this.f150a = c0115j;
        this.f151b = th;
    }

    public C0115j m289a() {
        return this.f150a;
    }

    public Throwable m290b() {
        return this.f151b;
    }

    public String m291c() {
        Writer stringWriter = new StringWriter();
        m290b().printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public String m292d() {
        return m290b().getMessage();
    }

    public boolean m293e() {
        return m290b() instanceof C0125b;
    }

    public String toString() {
        return this.f150a + ": " + this.f151b.getMessage();
    }
}
