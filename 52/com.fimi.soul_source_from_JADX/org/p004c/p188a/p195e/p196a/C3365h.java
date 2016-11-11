package org.p004c.p188a.p195e.p196a;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.p004c.p187f.p192a.C3524d;
import org.p004c.p187f.p192a.C3528l;
import org.p004c.p188a.p195e.C3366a;
import org.p004c.p188a.p195e.C3369b;
import org.p004c.p188a.p195e.C3370c;
import org.p004c.p188a.p195e.C3371d;

/* renamed from: org.c.a.e.a.h */
public class C3365h extends C3357a {
    public C3365h(C3528l c3528l) {
        super(c3528l);
    }

    protected Collection<C3524d> m18517b(C3371d c3371d) {
        Collection<C3524d> b = super.m18489b(c3371d);
        String a = ((C3370c) c3371d.m18542e(C3370c.class)).m18527a();
        Collection arrayList = new ArrayList();
        for (C3524d c3524d : b) {
            if (Arrays.asList(((C3369b) c3524d.m19184a(C3369b.class)).m18525a()).contains(a)) {
                arrayList.add(c3524d);
            }
        }
        return arrayList;
    }

    protected Collection<Field> m18518c(C3371d c3371d) {
        Collection<Field> c = super.m18490c(c3371d);
        String a = ((C3370c) c3371d.m18542e(C3370c.class)).m18527a();
        Collection arrayList = new ArrayList();
        for (Field field : c) {
            if (Arrays.asList(((C3366a) field.getAnnotation(C3366a.class)).m18521a()).contains(a)) {
                arrayList.add(field);
            }
        }
        return arrayList;
    }

    protected Collection<Field> m18519d(C3371d c3371d) {
        Collection<Field> d = super.m18491d(c3371d);
        String a = ((C3370c) c3371d.m18542e(C3370c.class)).m18527a();
        Collection arrayList = new ArrayList();
        for (Field field : d) {
            if (Arrays.asList(((C3369b) field.getAnnotation(C3369b.class)).m18525a()).contains(a)) {
                arrayList.add(field);
            }
        }
        return arrayList;
    }

    protected Collection<C3524d> m18520e(C3371d c3371d) {
        Collection<C3524d> e = super.m18492e(c3371d);
        String a = ((C3370c) c3371d.m18542e(C3370c.class)).m18527a();
        Collection arrayList = new ArrayList();
        for (C3524d c3524d : e) {
            if (Arrays.asList(((C3366a) c3524d.m19184a(C3366a.class)).m18521a()).contains(a)) {
                arrayList.add(c3524d);
            }
        }
        return arrayList;
    }
}
