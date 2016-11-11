package org.p004c.p187f.p208b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.p004c.p187f.p192a.C3528l;

/* renamed from: org.c.f.b.d */
public class C3537d {
    private final String f16085a;
    private final C3528l f16086b;
    private final List<Object> f16087c;

    public C3537d(String str, C3528l c3528l, List<Object> list) {
        C3537d.m19243a(str, "The name is missing.");
        C3537d.m19243a(c3528l, "The test class is missing.");
        C3537d.m19243a(list, "The parameters are missing.");
        this.f16085a = str;
        this.f16086b = c3528l;
        this.f16087c = Collections.unmodifiableList(new ArrayList(list));
    }

    private static void m19243a(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public String m19244a() {
        return this.f16085a;
    }

    public C3528l m19245b() {
        return this.f16086b;
    }

    public List<Object> m19246c() {
        return this.f16087c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        C3537d c3537d = (C3537d) obj;
        return this.f16085a.equals(c3537d.f16085a) && this.f16087c.equals(c3537d.f16087c) && this.f16086b.equals(c3537d.f16086b);
    }

    public int hashCode() {
        return (14747 * (((this.f16085a.hashCode() + 14747) * 14747) + this.f16086b.hashCode())) + this.f16087c.hashCode();
    }

    public String toString() {
        return this.f16086b.m19222e() + " '" + this.f16085a + "' with parameters " + this.f16087c;
    }
}
