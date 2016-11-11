package p001b.p009d;

import java.io.PrintStream;
import org.p122a.p123a.C2915a;
import p001b.p002b.C0115j;
import p001b.p002b.C0116p;
import p001b.p002b.C0136k;
import p001b.p002b.C0139n;
import p001b.p008c.C0142a;
import p001b.p008c.C0144c;

/* renamed from: b.d.b */
public class C0146b extends C0142a {
    public static final int f169e = 0;
    public static final int f170f = 1;
    public static final int f171g = 2;
    private C0145a f172h;

    public C0146b() {
        this(System.out);
    }

    public C0146b(C0145a c0145a) {
        this.f172h = c0145a;
    }

    public C0146b(PrintStream printStream) {
        this(new C0145a(printStream));
    }

    public static void m370a(Class<? extends C0136k> cls) {
        C0146b.m372c(new C0116p((Class) cls));
    }

    public static void m371b(String[] strArr) {
        try {
            if (!new C0146b().m381c(strArr).m314h()) {
                System.exit(f170f);
            }
            System.exit(f169e);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(f171g);
        }
    }

    public static C0139n m372c(C0115j c0115j) {
        return new C0146b().m382e(c0115j);
    }

    public static void m373d(C0115j c0115j) {
        new C0146b().m374a(c0115j, true);
    }

    public C0139n m374a(C0115j c0115j, boolean z) {
        C0139n f = m383f();
        f.m305a(this.f172h);
        long currentTimeMillis = System.currentTimeMillis();
        c0115j.m129a(f);
        this.f172h.m362a(f, System.currentTimeMillis() - currentTimeMillis);
        m380b(z);
        return f;
    }

    protected C0139n m375a(String str, String str2, boolean z) {
        return m374a(C0116p.m130a(m345g(str).asSubclass(C0136k.class), str2), z);
    }

    public void m376a(int i, C0115j c0115j, Throwable th) {
    }

    public void m377a(C0145a c0145a) {
        this.f172h = c0145a;
    }

    public void m378a(String str) {
    }

    public void m379b(String str) {
    }

    protected void m380b(boolean z) {
        if (z) {
            this.f172h.m354a();
            try {
                System.in.read();
            } catch (Exception e) {
            }
        }
    }

    public C0139n m381c(String[] strArr) {
        String str = C2915a.f14760f;
        String str2 = C2915a.f14760f;
        int i = f169e;
        boolean z = false;
        while (i < strArr.length) {
            if (strArr[i].equals("-wait")) {
                z = true;
            } else if (strArr[i].equals("-c")) {
                i += f170f;
                str = m342d(strArr[i]);
            } else if (strArr[i].equals("-m")) {
                i += f170f;
                str2 = strArr[i];
                int lastIndexOf = str2.lastIndexOf(46);
                str = str2.substring(f169e, lastIndexOf);
                str2 = str2.substring(lastIndexOf + f170f);
            } else if (strArr[i].equals("-v")) {
                System.err.println("JUnit " + C0144c.m352a() + " by Kent Beck and Erich Gamma");
            } else {
                str = strArr[i];
            }
            i += f170f;
        }
        if (str.equals(C2915a.f14760f)) {
            throw new Exception("Usage: TestRunner [-wait] testCaseName, where name is the name of the TestCase class");
        }
        try {
            return !str2.equals(C2915a.f14760f) ? m375a(str, str2, z) : m374a(m340c(str), z);
        } catch (Exception e) {
            throw new Exception("Could not create and run test suite: " + e);
        }
    }

    public C0139n m382e(C0115j c0115j) {
        return m374a(c0115j, false);
    }

    protected C0139n m383f() {
        return new C0139n();
    }

    protected void m384f(String str) {
        System.err.println(str);
        System.exit(f170f);
    }
}
