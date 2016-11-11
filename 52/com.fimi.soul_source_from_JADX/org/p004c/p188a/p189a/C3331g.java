package org.p004c.p188a.p189a;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.p004c.C3383a;
import org.p004c.C3457b;
import org.p004c.C3550f;
import org.p004c.C3561g;
import org.p004c.p187f.p192a.C3524d;
import org.p004c.p190g.C3330a;

/* renamed from: org.c.a.a.g */
public final class C3331g extends C3330a {
    private static final Set<Class<? extends Annotation>> f15838a;

    static {
        f15838a = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Class[]{C3561g.class, C3457b.class, C3550f.class, C3383a.class})));
    }

    private void m18402a(List<Exception> list, Class<?> cls) {
        list.add(new Exception(String.format("@%s can not be combined with @Category", new Object[]{cls.getSimpleName()})));
    }

    public List<Exception> m18403a(C3524d c3524d) {
        List arrayList = new ArrayList();
        for (Annotation annotation : c3524d.m19189a()) {
            for (Class cls : f15838a) {
                if (annotation.annotationType().isAssignableFrom(cls)) {
                    m18402a(arrayList, cls);
                }
            }
        }
        return Collections.unmodifiableList(arrayList);
    }
}
