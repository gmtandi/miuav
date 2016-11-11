package org.p004c.p188a.p195e.p196a;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: org.c.a.e.a.g */
public class C3364g extends AssertionError {
    private static final long serialVersionUID = 1;

    public C3364g(Throwable th, String str, Object... objArr) {
        super(String.format("%s(%s)", new Object[]{str, C3364g.m18516a(", ", objArr)}));
        initCause(th);
    }

    private static String m18514a(Object obj) {
        try {
            return String.valueOf(obj);
        } catch (Throwable th) {
            return "[toString failed]";
        }
    }

    public static String m18515a(String str, Collection<Object> collection) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            stringBuilder.append(C3364g.m18514a(it.next()));
            if (it.hasNext()) {
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }

    public static String m18516a(String str, Object... objArr) {
        return C3364g.m18515a(str, Arrays.asList(objArr));
    }

    public boolean equals(Object obj) {
        return (obj instanceof C3364g) && toString().equals(obj.toString());
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
