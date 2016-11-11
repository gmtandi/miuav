package com.fimi.kernel.p076b.p078b;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.fimi.kernel.b.b.k */
public class C1122k implements C1119h {
    private static int f5165c;
    private static C1122k f5166d;
    private static List<C1113b> f5167e;
    List<String> f5168a;
    private C1126n f5169b;
    private C1127o f5170f;
    private Context f5171g;
    private String f5172h;

    static {
        f5165c = 3;
    }

    private C1122k() {
    }

    public static synchronized C1122k m7798a(Context context) {
        C1122k c1122k;
        synchronized (C1122k.class) {
            if (f5166d == null) {
                f5166d = new C1122k();
                f5166d.f5171g = context;
            }
            f5166d.f5171g = context;
            c1122k = f5166d;
        }
        return c1122k;
    }

    private boolean m7800b(C1113b c1113b) {
        c1113b.m7775a(c1113b);
        String m = c1113b.m7791m();
        for (C1113b m2 : f5167e) {
            if (m2.m7791m().equals(m)) {
                f5167e.remove(c1113b);
                if (this.f5170f != null) {
                    this.f5170f.m7818a(f5167e, false, c1113b);
                }
                return true;
            }
        }
        return false;
    }

    public synchronized C1113b m7803a(Context context, String str) {
        C1113b c1113b;
        C1113b c1113b2 = null;
        synchronized (this) {
            if (str == null) {
                c1113b = null;
            } else {
                if (f5167e != null) {
                    int i = 0;
                    while (i < f5167e.size()) {
                        if (((C1113b) f5167e.get(i)).m7779b().equals(str) || ((C1113b) f5167e.get(i)).m7791m().equals(str)) {
                            c1113b = (C1113b) f5167e.get(i);
                            break;
                        }
                        i++;
                    }
                    c1113b = null;
                    if (c1113b == null) {
                        c1113b2 = c1113b;
                    }
                } else {
                    f5167e = new ArrayList();
                }
                if (this.f5168a == null || this.f5168a.size() > 0) {
                    this.f5168a = C1131s.m7844a(context).m7847a();
                }
                if (this.f5168a != null && this.f5168a.size() > 0) {
                    for (String str2 : this.f5168a) {
                        if (!str2.endsWith(str)) {
                            if (str2.equals(str)) {
                            }
                        }
                        c1113b = C1131s.m7844a(context).m7846a(context, str2, new C1124l(this));
                        break;
                    }
                }
                c1113b = c1113b2;
                if (c1113b != null) {
                    f5167e.add(c1113b);
                    c1113b.m7780b((C1119h) this);
                    if (this.f5170f != null) {
                        this.f5170f.m7818a(f5167e, true, c1113b);
                    }
                }
            }
        }
        return c1113b;
    }

    public void m7804a() {
        if (f5167e != null && f5167e.size() == 0) {
            C1131s.m7844a(this.f5171g).m7855c();
        }
    }

    public void m7805a(C1115d c1115d, C1113b c1113b) {
        if (c1115d == C1115d.Completed && !c1113b.m7791m().equals(this.f5172h)) {
            this.f5172h = c1113b.m7791m();
            int b = m7810b();
            if (this.f5169b != null) {
                this.f5169b.m7817a(c1113b, b);
            }
            m7811c();
        }
    }

    public void m7806a(C1126n c1126n) {
        this.f5169b = c1126n;
    }

    public void m7807a(C1127o c1127o) {
        this.f5170f = c1127o;
    }

    public synchronized void m7808a(String str, String str2, long j, Boolean bool, String str3) {
        if (f5167e == null) {
            f5167e = new ArrayList();
        }
        for (int size = f5167e.size() - 1; size >= 0; size--) {
            if (((C1113b) f5167e.get(size)).m7791m().equals(str)) {
                ((C1113b) f5167e.get(size)).m7775a((C1113b) f5167e.get(size));
                f5167e.remove(size);
                C1131s.m7844a(this.f5171g).m7856d(str);
                break;
            }
        }
        C1113b a = C1113b.m7743a(this.f5171g, str, str2, j, bool, str3, new C1125m(this));
        if (a != null) {
            f5167e.add(a);
        }
    }

    public boolean m7809a(C1113b c1113b) {
        if (c1113b == null) {
            return false;
        }
        C1131s.m7844a(this.f5171g).m7856d(c1113b.m7791m());
        return m7800b(c1113b);
    }

    public synchronized int m7810b() {
        int i;
        i = 0;
        for (C1113b g : f5167e) {
            i = g.m7785g() == C1115d.Downloading ? i + 1 : i;
        }
        return i;
    }

    public void m7811c() {
        if (m7810b() < f5165c) {
            for (C1113b c1113b : f5167e) {
                if (c1113b.m7785g() == C1115d.Wait) {
                    c1113b.m7781c();
                    return;
                }
            }
        }
    }

    public void m7812d() {
        if (f5167e != null && f5167e.size() > 0) {
            for (C1113b c1113b : f5167e) {
                if (c1113b.m7785g() == C1115d.Downloading || c1113b.m7785g() == C1115d.Wait) {
                    c1113b.m7783e();
                }
            }
        }
    }

    public void m7813e() {
        if (f5167e != null && f5167e.size() > 0) {
            for (C1113b c1113b : f5167e) {
                if (c1113b.m7785g() == C1115d.Pause) {
                    if (m7810b() < f5165c) {
                        c1113b.m7781c();
                    } else {
                        c1113b.m7782d();
                    }
                }
            }
        }
    }
}
