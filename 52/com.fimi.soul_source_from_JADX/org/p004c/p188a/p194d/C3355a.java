package org.p004c.p188a.p194d;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.p004c.p187f.C3321o;
import org.p004c.p187f.p192a.C3384i;

/* renamed from: org.c.a.d.a */
public class C3355a extends C3321o {
    public C3355a(Class<?> cls, C3384i c3384i) {
        super(c3384i, cls, C3355a.m18476a(cls.getClasses()));
    }

    private static Class<?>[] m18476a(Class<?>[] clsArr) {
        List arrayList = new ArrayList(clsArr.length);
        for (Class cls : clsArr) {
            if (!Modifier.isAbstract(cls.getModifiers())) {
                arrayList.add(cls);
            }
        }
        return (Class[]) arrayList.toArray(new Class[arrayList.size()]);
    }
}
