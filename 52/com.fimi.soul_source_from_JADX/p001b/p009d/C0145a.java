package p001b.p009d;

import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.Enumeration;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;
import p001b.p002b.C0115j;
import p001b.p002b.C0125b;
import p001b.p002b.C0137l;
import p001b.p002b.C0138m;
import p001b.p002b.C0139n;
import p001b.p008c.C0142a;

/* renamed from: b.d.a */
public class C0145a implements C0138m {
    PrintStream f167a;
    int f168b;

    public C0145a(PrintStream printStream) {
        this.f168b = 0;
        this.f167a = printStream;
    }

    void m354a() {
        m364b().println();
        m364b().println("<RETURN> to continue");
    }

    protected void m355a(long j) {
        m364b().println();
        m364b().println("Time: " + m365b(j));
    }

    public void m356a(C0115j c0115j) {
    }

    public void m357a(C0115j c0115j, C0125b c0125b) {
        m364b().print("F");
    }

    public void m358a(C0115j c0115j, Throwable th) {
        m364b().print("E");
    }

    protected void m359a(C0137l c0137l) {
        m364b().print(C0142a.m328i(c0137l.m291c()));
    }

    public void m360a(C0137l c0137l, int i) {
        m367b(c0137l, i);
        m359a(c0137l);
    }

    protected void m361a(C0139n c0139n) {
        m363a(c0139n.m306b(), c0139n.m299a(), XiaomiOAuthConstants.EXTRA_ERROR_CODE_2);
    }

    synchronized void m362a(C0139n c0139n, long j) {
        m355a(j);
        m361a(c0139n);
        m368b(c0139n);
        m369c(c0139n);
    }

    protected void m363a(Enumeration<C0137l> enumeration, int i, String str) {
        if (i != 0) {
            if (i == 1) {
                m364b().println("There was " + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str + ":");
            } else {
                m364b().println("There were " + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str + "s:");
            }
            int i2 = 1;
            while (enumeration.hasMoreElements()) {
                m360a((C0137l) enumeration.nextElement(), i2);
                i2++;
            }
        }
    }

    public PrintStream m364b() {
        return this.f167a;
    }

    protected String m365b(long j) {
        return NumberFormat.getInstance().format(((double) j) / 1000.0d);
    }

    public void m366b(C0115j c0115j) {
        m364b().print(".");
        int i = this.f168b;
        this.f168b = i + 1;
        if (i >= 40) {
            m364b().println();
            this.f168b = 0;
        }
    }

    protected void m367b(C0137l c0137l, int i) {
        m364b().print(i + ") " + c0137l.m289a());
    }

    protected void m368b(C0139n c0139n) {
        m363a(c0139n.m310d(), c0139n.m309c(), "failure");
    }

    protected void m369c(C0139n c0139n) {
        if (c0139n.m314h()) {
            m364b().println();
            m364b().print("OK");
            m364b().println(" (" + c0139n.m311e() + " test" + (c0139n.m311e() == 1 ? C2915a.f14760f : "s") + ")");
        } else {
            m364b().println();
            m364b().println("FAILURES!!!");
            m364b().println("Tests run: " + c0139n.m311e() + ",  Failures: " + c0139n.m309c() + ",  Errors: " + c0139n.m299a());
        }
        m364b().println();
    }
}
