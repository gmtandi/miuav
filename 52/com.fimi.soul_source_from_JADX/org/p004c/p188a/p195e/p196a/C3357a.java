package org.p004c.p188a.p195e.p196a;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.p004c.p187f.p192a.C3523b;
import org.p004c.p187f.p192a.C3524d;
import org.p004c.p187f.p192a.C3528l;
import org.p004c.p188a.p195e.C3356e;
import org.p004c.p188a.p195e.C3359g;
import org.p004c.p188a.p195e.C3366a;
import org.p004c.p188a.p195e.C3369b;
import org.p004c.p188a.p195e.C3371d;

/* renamed from: org.c.a.e.a.a */
public class C3357a extends C3356e {
    private final C3528l f15859a;

    public C3357a(C3528l c3528l) {
        this.f15859a = c3528l;
    }

    private Object m18478a(Field field) {
        try {
            return field.get(null);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("unexpected: field from getClass doesn't exist on object");
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("unexpected: getFields returned an inaccessible field");
        }
    }

    private void m18479a(Class<?> cls, C3371d c3371d, String str, List<C3359g> list, Object obj) {
        if (cls.isArray()) {
            m18481a(c3371d, str, (List) list, obj);
        } else if (Iterable.class.isAssignableFrom(cls)) {
            m18480a(c3371d, str, (List) list, (Iterable) obj);
        }
    }

    private void m18480a(C3371d c3371d, String str, List<C3359g> list, Iterable<?> iterable) {
        int i = 0;
        for (Object next : iterable) {
            if (c3371d.m18537a(next)) {
                list.add(C3359g.m18493a(str + "[" + i + "]", next));
            }
            i++;
        }
    }

    private void m18481a(C3371d c3371d, String str, List<C3359g> list, Object obj) {
        for (int i = 0; i < Array.getLength(obj); i++) {
            Object obj2 = Array.get(obj, i);
            if (c3371d.m18537a(obj2)) {
                list.add(C3359g.m18493a(str + "[" + i + "]", obj2));
            }
        }
    }

    private void m18482a(C3371d c3371d, List<C3359g> list) {
        for (C3524d c3524d : m18489b(c3371d)) {
            Class i = c3524d.m19197i();
            if ((i.isArray() && c3371d.m18539b(i.getComponentType())) || Iterable.class.isAssignableFrom(i)) {
                try {
                    m18479a(i, c3371d, c3524d.m19190b(), list, c3524d.m19183a(null, new Object[0]));
                } catch (Throwable th) {
                    Object obj = th;
                    C3369b c3369b = (C3369b) c3524d.m19184a(C3369b.class);
                    if (c3369b != null && C3357a.m18485b(c3369b.m18526b(), obj)) {
                        return;
                    }
                }
            }
        }
    }

    private void m18484b(C3371d c3371d, List<C3359g> list) {
        for (C3524d c3524d : m18492e(c3371d)) {
            if (c3371d.m18536a(c3524d.m19195e())) {
                list.add(new C3360c(null));
            }
        }
    }

    private static boolean m18485b(Class<?>[] clsArr, Object obj) {
        for (Class isAssignableFrom : clsArr) {
            if (isAssignableFrom.isAssignableFrom(obj.getClass())) {
                return true;
            }
        }
        return false;
    }

    private void m18486c(C3371d c3371d, List<C3359g> list) {
        for (Field field : m18491d(c3371d)) {
            m18479a(field.getType(), c3371d, field.getName(), list, m18478a(field));
        }
    }

    private void m18487d(C3371d c3371d, List<C3359g> list) {
        for (Field field : m18490c(c3371d)) {
            Object a = m18478a(field);
            if (c3371d.m18537a(a)) {
                list.add(C3359g.m18493a(field.getName(), a));
            }
        }
    }

    public List<C3359g> m18488a(C3371d c3371d) {
        List arrayList = new ArrayList();
        m18487d(c3371d, arrayList);
        m18486c(c3371d, arrayList);
        m18484b(c3371d, arrayList);
        m18482a(c3371d, arrayList);
        return arrayList;
    }

    protected Collection<C3524d> m18489b(C3371d c3371d) {
        return this.f15859a.m19217b(C3369b.class);
    }

    protected Collection<Field> m18490c(C3371d c3371d) {
        List<C3523b> c = this.f15859a.m19220c(C3366a.class);
        Collection<Field> arrayList = new ArrayList();
        for (C3523b d : c) {
            arrayList.add(d.m19178d());
        }
        return arrayList;
    }

    protected Collection<Field> m18491d(C3371d c3371d) {
        List<C3523b> c = this.f15859a.m19220c(C3369b.class);
        Collection<Field> arrayList = new ArrayList();
        for (C3523b d : c) {
            arrayList.add(d.m19178d());
        }
        return arrayList;
    }

    protected Collection<C3524d> m18492e(C3371d c3371d) {
        return this.f15859a.m19217b(C3366a.class);
    }
}
