package org.p184b.p185a;

import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p184b.C3295u;
import org.p184b.C3300k;

/* renamed from: org.b.a.t */
public abstract class C3296t extends C3295u<String> {
    protected final String f15807a;

    protected C3296t(String str) {
        this.f15807a = str;
    }

    public void m18205a(String str, C3300k c3300k) {
        c3300k.m18222a("was \"").m18222a(str).m18222a("\"");
    }

    public void m18206a(C3300k c3300k) {
        c3300k.m18222a("a string ").m18222a(m18208b()).m18222a(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).m18221a(this.f15807a);
    }

    protected abstract boolean m18207a(String str);

    protected abstract String m18208b();

    public /* synthetic */ void m18209b(Object obj, C3300k c3300k) {
        m18205a((String) obj, c3300k);
    }

    public /* synthetic */ boolean m18210b(Object obj) {
        return m18211c((String) obj);
    }

    public boolean m18211c(String str) {
        return m18207a(str);
    }
}
