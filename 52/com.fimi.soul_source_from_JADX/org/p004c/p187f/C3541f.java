package org.p004c.p187f;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.p004c.p005e.C3319s;
import org.p004c.p187f.p192a.C3524d;
import org.p004c.p187f.p192a.C3528l;
import org.p004c.p187f.p208b.C3535c;
import org.p004c.p187f.p208b.C3536b;
import org.p004c.p187f.p208b.C3537d;

/* renamed from: org.c.f.f */
public class C3541f extends C3321o {
    private static final C3535c f16094a;
    private static final List<C3319s> f16095b;
    private final List<C3319s> f16096c;

    static {
        f16094a = new C3536b();
        f16095b = Collections.emptyList();
    }

    public C3541f(Class<?> cls) {
        super((Class) cls, f16095b);
        this.f16096c = Collections.unmodifiableList(m19250a(m19253b(), ((C3543h) m19255j().m19184a(C3543h.class)).m19259a(), m19254b(cls)));
    }

    private List<C3537d> m19249a(Iterable<Object> iterable, String str) {
        int i = 0;
        List<C3537d> arrayList = new ArrayList();
        for (Object a : iterable) {
            int i2 = i + 1;
            arrayList.add(m19251a(str, i, a));
            i = i2;
        }
        return arrayList;
    }

    private List<C3319s> m19250a(Iterable<Object> iterable, String str, C3535c c3535c) {
        try {
            List<C3537d> a = m19249a(iterable, str);
            List<C3319s> arrayList = new ArrayList();
            for (C3537d a2 : a) {
                arrayList.add(c3535c.m19241a(a2));
            }
            return arrayList;
        } catch (ClassCastException e) {
            throw m19256k();
        }
    }

    private C3537d m19251a(String str, int i, Object obj) {
        Object[] objArr;
        if (obj instanceof Object[]) {
            objArr = (Object[]) obj;
        } else {
            objArr = new Object[]{obj};
        }
        return C3541f.m19252a(m18348g(), str, i, objArr);
    }

    private static C3537d m19252a(C3528l c3528l, String str, int i, Object[] objArr) {
        return new C3537d("[" + MessageFormat.format(str.replaceAll("\\{index\\}", Integer.toString(i)), objArr) + "]", c3528l, Arrays.asList(objArr));
    }

    private Iterable<Object> m19253b() {
        Object a = m19255j().m19183a(null, new Object[0]);
        if (a instanceof Iterable) {
            return (Iterable) a;
        }
        if (a instanceof Object[]) {
            return Arrays.asList((Object[]) a);
        }
        throw m19256k();
    }

    private C3535c m19254b(Class<?> cls) {
        C3544i c3544i = (C3544i) cls.getAnnotation(C3544i.class);
        return c3544i == null ? f16094a : (C3535c) c3544i.m19260a().newInstance();
    }

    private C3524d m19255j() {
        for (C3524d c3524d : m18348g().m19217b(C3543h.class)) {
            if (c3524d.m19169g() && c3524d.m19170h()) {
                return c3524d;
            }
        }
        throw new Exception("No public static parameters method on class " + m18348g().m19222e());
    }

    private Exception m19256k() {
        String e = m18348g().m19222e();
        String b = m19255j().m19190b();
        return new Exception(MessageFormat.format("{0}.{1}() must return an Iterable of arrays.", new Object[]{e, b}));
    }

    protected List<C3319s> m19257c() {
        return this.f16096c;
    }
}
