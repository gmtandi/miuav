package org.p004c.p187f.p192a;

import java.lang.reflect.Field;
import java.util.Comparator;

/* renamed from: org.c.f.a.n */
class C3530n implements Comparator<Field> {
    private C3530n() {
    }

    public int m19226a(Field field, Field field2) {
        return field.getName().compareTo(field2.getName());
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m19226a((Field) obj, (Field) obj2);
    }
}
