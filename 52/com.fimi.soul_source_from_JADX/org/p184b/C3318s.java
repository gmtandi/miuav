package org.p184b;

/* renamed from: org.b.s */
public class C3318s extends C3301a {
    private final Appendable f15825b;

    public C3318s() {
        this(new StringBuilder());
    }

    public C3318s(Appendable appendable) {
        this.f15825b = appendable;
    }

    public static String m18312b(C3274r c3274r) {
        return new C3318s().m18236a(c3274r).toString();
    }

    public static String m18313c(C3274r c3274r) {
        return C3318s.m18312b(c3274r);
    }

    protected void m18314a(char c) {
        try {
            this.f15825b.append(c);
        } catch (Throwable e) {
            throw new RuntimeException("Could not write description", e);
        }
    }

    protected void m18315b(String str) {
        try {
            this.f15825b.append(str);
        } catch (Throwable e) {
            throw new RuntimeException("Could not write description", e);
        }
    }

    public String toString() {
        return this.f15825b.toString();
    }
}
