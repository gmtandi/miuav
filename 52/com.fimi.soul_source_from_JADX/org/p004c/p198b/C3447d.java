package org.p004c.p198b;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.p004c.C3459c;
import org.p122a.p123a.C2915a;

/* renamed from: org.c.b.d */
public abstract class C3447d {
    private int m18803a(Object obj, Object obj2, String str) {
        if (obj == null) {
            C3459c.m18848a(str + "expected array was null");
        }
        if (obj2 == null) {
            C3459c.m18848a(str + "actual array was null");
        }
        int length = Array.getLength(obj2);
        int length2 = Array.getLength(obj);
        if (length != length2) {
            C3459c.m18848a(str + "array lengths differed, expected.length=" + length2 + " actual.length=" + length);
        }
        return length2;
    }

    private boolean m18804a(Object obj) {
        return obj != null && obj.getClass().isArray();
    }

    protected abstract void m18805a(Object obj, Object obj2);

    public void m18806a(String str, Object obj, Object obj2) {
        int i = 0;
        if (obj != obj2) {
            if (!Arrays.deepEquals(new Object[]{obj}, new Object[]{obj2})) {
                String str2 = str == null ? C2915a.f14760f : str + ": ";
                int a = m18803a(obj, obj2, str2);
                while (i < a) {
                    Object obj3 = Array.get(obj, i);
                    Object obj4 = Array.get(obj2, i);
                    if (m18804a(obj3) && m18804a(obj4)) {
                        try {
                            m18806a(str, obj3, obj4);
                        } catch (C3393a e) {
                            e.m18634a(i);
                            throw e;
                        }
                    }
                    try {
                        m18805a(obj3, obj4);
                    } catch (AssertionError e2) {
                        throw new C3393a(str2, e2, i);
                    }
                    i++;
                }
            }
        }
    }
}
