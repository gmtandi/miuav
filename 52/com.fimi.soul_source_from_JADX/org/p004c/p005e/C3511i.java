package org.p004c.p005e;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.p004c.p187f.p192a.C3526f;
import org.p004c.p198b.C3402c;

/* renamed from: org.c.e.i */
class C3511i {
    private final List<String> f16052a;
    private final List<Class<?>> f16053b;
    private final List<Throwable> f16054c;

    C3511i() {
        this.f16052a = new ArrayList();
        this.f16053b = new ArrayList();
        this.f16054c = new ArrayList();
    }

    public static C3511i m19111a(String[] strArr) {
        C3511i c3511i = new C3511i();
        c3511i.m19115d(strArr);
        return c3511i;
    }

    private C3340l m19112a(Throwable th) {
        return C3340l.m18430a(C3511i.class, th);
    }

    private C3340l m19113a(C3340l c3340l) {
        try {
            C3340l c3340l2 = c3340l;
            for (String a : this.f16052a) {
                c3340l2 = c3340l2.m18436a(C3508e.m19106a(c3340l2, a));
            }
            return c3340l2;
        } catch (Throwable e) {
            return m19112a(e);
        }
    }

    private String[] m19114a(String[] strArr, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        while (i != i2) {
            arrayList.add(strArr[i]);
            i++;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private void m19115d(String[] strArr) {
        m19120c(m19119b(strArr));
    }

    public List<String> m19116a() {
        return Collections.unmodifiableList(this.f16052a);
    }

    public C3340l m19117a(C3336a c3336a) {
        return this.f16054c.isEmpty() ? m19113a(C3340l.m18431a(c3336a, (Class[]) this.f16053b.toArray(new Class[this.f16053b.size()]))) : m19112a(new C3526f(this.f16054c));
    }

    public List<Class<?>> m19118b() {
        return Collections.unmodifiableList(this.f16053b);
    }

    String[] m19119b(String... strArr) {
        int i = 0;
        while (i != strArr.length) {
            String str = strArr[i];
            if (str.equals("--")) {
                return m19114a(strArr, i + 1, strArr.length);
            }
            if (!str.startsWith("--")) {
                return m19114a(strArr, i, strArr.length);
            }
            if (str.startsWith("--filter=") || str.equals("--filter")) {
                int i2;
                Object obj;
                if (str.equals("--filter")) {
                    i2 = i + 1;
                    if (i2 >= strArr.length) {
                        this.f16054c.add(new C3512j(str + " value not specified"));
                        break;
                    }
                    obj = strArr[i2];
                } else {
                    i2 = i;
                    String substring = str.substring(str.indexOf(61) + 1);
                }
                this.f16052a.add(obj);
                i = i2;
            } else {
                this.f16054c.add(new C3512j("JUnit knows nothing about the " + str + " option"));
            }
            i++;
        }
        return new String[0];
    }

    void m19120c(String[] strArr) {
        for (String str : strArr) {
            try {
                this.f16053b.add(C3402c.m18663a(str));
            } catch (Throwable e) {
                this.f16054c.add(new IllegalArgumentException("Could not find class [" + str + "]", e));
            }
        }
    }
}
