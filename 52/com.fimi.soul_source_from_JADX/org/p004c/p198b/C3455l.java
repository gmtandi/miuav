package org.p004c.p198b;

import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.List;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.C3515n;
import org.p004c.p005e.p007b.C0133b;
import org.p004c.p005e.p007b.C3493a;
import org.p122a.p123a.C2915a;

/* renamed from: org.c.b.l */
public class C3455l extends C0133b {
    private final PrintStream f15971a;

    public C3455l(PrintStream printStream) {
        this.f15971a = printStream;
    }

    public C3455l(C3450g c3450g) {
        this(c3450g.m18809a());
    }

    private PrintStream m18817a() {
        return this.f15971a;
    }

    protected void m18818a(long j) {
        m18817a().println();
        m18817a().println("Time: " + m18822b(j));
    }

    public void m18819a(C3493a c3493a) {
        this.f15971a.append('E');
    }

    protected void m18820a(C3493a c3493a, String str) {
        m18817a().println(str + ") " + c3493a.m19045a());
        m18817a().print(c3493a.m19048d());
    }

    public void m18821a(C3515n c3515n) {
        m18818a(c3515n.m19142c());
        m18824b(c3515n);
        m18826c(c3515n);
    }

    protected String m18822b(long j) {
        return NumberFormat.getInstance().format(((double) j) / 1000.0d);
    }

    public void m18823b(C3507d c3507d) {
        this.f15971a.append('.');
    }

    protected void m18824b(C3515n c3515n) {
        List<C3493a> d = c3515n.m19143d();
        if (d.size() != 0) {
            if (d.size() == 1) {
                m18817a().println("There was " + d.size() + " failure:");
            } else {
                m18817a().println("There were " + d.size() + " failures:");
            }
            int i = 1;
            for (C3493a c3493a : d) {
                StringBuilder append = new StringBuilder().append(C2915a.f14760f);
                int i2 = i + 1;
                m18820a(c3493a, append.append(i).toString());
                i = i2;
            }
        }
    }

    public void m18825c(C3507d c3507d) {
        this.f15971a.append('I');
    }

    protected void m18826c(C3515n c3515n) {
        if (c3515n.m19145f()) {
            m18817a().println();
            m18817a().print("OK");
            m18817a().println(" (" + c3515n.m19140a() + " test" + (c3515n.m19140a() == 1 ? C2915a.f14760f : "s") + ")");
        } else {
            m18817a().println();
            m18817a().println("FAILURES!!!");
            m18817a().println("Tests run: " + c3515n.m19140a() + ",  Failures: " + c3515n.m19141b());
        }
        m18817a().println();
    }
}
