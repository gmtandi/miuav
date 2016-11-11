package org.p004c.p188a.p189a;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: org.c.a.a.i */
class C3333i extends C3324b {
    public C3333i(List<Class<?>> list) {
        this(new HashSet(list));
    }

    public C3333i(Set<Class<?>> set) {
        super(true, null, true, set);
    }

    public String m18405a() {
        return "excludes " + super.m18388a();
    }
}
