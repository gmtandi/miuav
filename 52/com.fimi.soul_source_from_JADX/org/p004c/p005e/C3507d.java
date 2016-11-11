package org.p004c.p005e;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: org.c.e.d */
public class C3507d implements Serializable {
    public static final C3507d f16042a;
    public static final C3507d f16043b;
    private static final Pattern f16044c;
    private static final long serialVersionUID = 1;
    private final Collection<C3507d> f16045d;
    private final String f16046e;
    private final Serializable f16047f;
    private final Annotation[] f16048g;
    private volatile Class<?> f16049h;

    static {
        f16044c = Pattern.compile("([\\s\\S]*)\\((.*)\\)");
        f16042a = new C3507d(null, "No Tests", new Annotation[0]);
        f16043b = new C3507d(null, "Test mechanism", new Annotation[0]);
    }

    private C3507d(Class<?> cls, String str, Serializable serializable, Annotation... annotationArr) {
        this.f16045d = new ConcurrentLinkedQueue();
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("The display name must not be empty.");
        } else if (serializable == null) {
            throw new IllegalArgumentException("The unique id must not be null.");
        } else {
            this.f16049h = cls;
            this.f16046e = str;
            this.f16047f = serializable;
            this.f16048g = annotationArr;
        }
    }

    private C3507d(Class<?> cls, String str, Annotation... annotationArr) {
        this(cls, str, str, annotationArr);
    }

    private String m19082a(int i, String str) {
        Matcher matcher = f16044c.matcher(toString());
        return matcher.matches() ? matcher.group(i) : str;
    }

    private static String m19083a(String str, String str2) {
        return String.format("%s(%s)", new Object[]{str, str2});
    }

    public static C3507d m19084a(Class<?> cls) {
        return new C3507d(cls, cls.getName(), cls.getAnnotations());
    }

    public static C3507d m19085a(Class<?> cls, String str) {
        return new C3507d(cls, C3507d.m19083a(str, cls.getName()), new Annotation[0]);
    }

    public static C3507d m19086a(Class<?> cls, String str, Annotation... annotationArr) {
        return new C3507d(cls, C3507d.m19083a(str, cls.getName()), annotationArr);
    }

    public static C3507d m19087a(String str, Serializable serializable, Annotation... annotationArr) {
        return new C3507d(null, str, serializable, annotationArr);
    }

    public static C3507d m19088a(String str, String str2, Serializable serializable) {
        return new C3507d(null, C3507d.m19083a(str2, str), serializable, new Annotation[0]);
    }

    public static C3507d m19089a(String str, String str2, Annotation... annotationArr) {
        return new C3507d(null, C3507d.m19083a(str2, str), annotationArr);
    }

    public static C3507d m19090a(String str, Annotation... annotationArr) {
        return new C3507d(null, str, annotationArr);
    }

    public String m19091a() {
        return this.f16046e;
    }

    public void m19092a(C3507d c3507d) {
        this.f16045d.add(c3507d);
    }

    public <T extends Annotation> T m19093b(Class<T> cls) {
        for (Annotation annotation : this.f16048g) {
            if (annotation.annotationType().equals(cls)) {
                return (Annotation) cls.cast(annotation);
            }
        }
        return null;
    }

    public ArrayList<C3507d> m19094b() {
        return new ArrayList(this.f16045d);
    }

    public boolean m19095c() {
        return !m19096d();
    }

    public boolean m19096d() {
        return this.f16045d.isEmpty();
    }

    public int m19097e() {
        if (m19096d()) {
            return 1;
        }
        int i = 0;
        for (C3507d e : this.f16045d) {
            i = e.m19097e() + i;
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C3507d)) {
            return false;
        }
        return this.f16047f.equals(((C3507d) obj).f16047f);
    }

    public boolean m19098f() {
        return equals(f16042a);
    }

    public C3507d m19099g() {
        return new C3507d(this.f16049h, this.f16046e, this.f16048g);
    }

    public Collection<Annotation> m19100h() {
        return Arrays.asList(this.f16048g);
    }

    public int hashCode() {
        return this.f16047f.hashCode();
    }

    public Class<?> m19101i() {
        Class<?> cls = null;
        if (this.f16049h != null) {
            return this.f16049h;
        }
        String j = m19102j();
        if (j == null) {
            return cls;
        }
        try {
            this.f16049h = Class.forName(j, false, getClass().getClassLoader());
            return this.f16049h;
        } catch (ClassNotFoundException e) {
            return cls;
        }
    }

    public String m19102j() {
        return this.f16049h != null ? this.f16049h.getName() : m19082a(2, toString());
    }

    public String m19103k() {
        return m19082a(1, null);
    }

    public String toString() {
        return m19091a();
    }
}
