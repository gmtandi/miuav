package org.p122a.p137b;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: org.a.b.e */
class C3271e implements Comparator {
    private C3271e() {
    }

    public int compare(Object obj, Object obj2) {
        return (obj == null && obj2 == null) ? 0 : obj == null ? -1 : obj2 == null ? 1 : obj instanceof List ? C3269c.m18092a((List) obj, (List) obj2) : obj instanceof Set ? C3269c.m18094a((Set) obj, (Set) obj2) : obj instanceof Map ? C3269c.m18093a((Map) obj, (Map) obj2) : obj instanceof byte[] ? C3269c.m18096a((byte[]) obj, (byte[]) obj2) : C3269c.m18089a((Comparable) obj, (Comparable) obj2);
    }
}
