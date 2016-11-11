package org.p004c.p188a.p195e.p196a;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.p004c.p187f.p192a.C3528l;
import org.p004c.p188a.p195e.C3356e;
import org.p004c.p188a.p195e.C3359g;
import org.p004c.p188a.p195e.C3371d;
import org.p004c.p188a.p195e.C3372f;

/* renamed from: org.c.a.e.a.d */
public class C3361d {
    private final List<C3359g> f15861a;
    private final List<C3371d> f15862b;
    private final C3528l f15863c;

    private C3361d(List<C3359g> list, List<C3371d> list2, C3528l c3528l) {
        this.f15862b = list2;
        this.f15861a = list;
        this.f15863c = c3528l;
    }

    private List<C3359g> m18498a(C3371d c3371d) {
        Class a = c3371d.m18535a();
        return a.isEnum() ? new C3363f(a).m18513a(c3371d) : (a.equals(Boolean.class) || a.equals(Boolean.TYPE)) ? new C3362e().m18512a(c3371d) : Collections.emptyList();
    }

    public static C3361d m18499a(Method method, C3528l c3528l) {
        List a = C3371d.m18531a(c3528l.m19223f());
        a.addAll(C3371d.m18529a(method));
        return new C3361d(new ArrayList(), a, c3528l);
    }

    private C3356e m18500a(Class<? extends C3356e> cls) {
        for (Constructor constructor : cls.getConstructors()) {
            Class[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].equals(C3528l.class)) {
                return (C3356e) constructor.newInstance(new Object[]{this.f15863c});
            }
        }
        return (C3356e) cls.newInstance();
    }

    private C3356e m18501b(C3371d c3371d) {
        C3372f c3372f = (C3372f) c3371d.m18541d(C3372f.class);
        return c3372f != null ? m18500a(c3372f.m18543a()) : new C3357a(this.f15863c);
    }

    private int m18502g() {
        return C3371d.m18531a(this.f15863c.m19223f()).size();
    }

    public C3361d m18503a(C3359g c3359g) {
        List arrayList = new ArrayList(this.f15861a);
        arrayList.add(c3359g);
        return new C3361d(arrayList, this.f15862b.subList(1, this.f15862b.size()), this.f15863c);
    }

    public boolean m18504a() {
        return this.f15862b.size() == 0;
    }

    public Object[] m18505a(int i, int i2) {
        Object[] objArr = new Object[(i2 - i)];
        for (int i3 = i; i3 < i2; i3++) {
            objArr[i3 - i] = ((C3359g) this.f15861a.get(i3)).m18494a();
        }
        return objArr;
    }

    public Object[] m18506a(boolean z) {
        Object[] objArr = new Object[this.f15861a.size()];
        for (int i = 0; i < objArr.length; i++) {
            objArr[i] = ((C3359g) this.f15861a.get(i)).m18495b();
        }
        return objArr;
    }

    public C3371d m18507b() {
        return (C3371d) this.f15862b.get(0);
    }

    public List<C3359g> m18508c() {
        C3371d b = m18507b();
        List<C3359g> a = m18501b(b).m18477a(b);
        return a.size() == 0 ? m18498a(b) : a;
    }

    public Object[] m18509d() {
        return m18505a(0, m18502g());
    }

    public Object[] m18510e() {
        return m18505a(m18502g(), this.f15861a.size());
    }

    public Object[] m18511f() {
        return m18505a(0, this.f15861a.size());
    }
}
