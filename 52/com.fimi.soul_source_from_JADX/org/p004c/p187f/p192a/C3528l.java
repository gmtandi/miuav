package org.p004c.p187f.p192a;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.p004c.C3459c;
import org.p004c.C3550f;
import org.p004c.C3561g;
import org.p004c.p198b.C3451h;

/* renamed from: org.c.f.a.l */
public class C3528l implements C3521a {
    private static final C3530n f16076a;
    private static final C3531o f16077b;
    private final Class<?> f16078c;
    private final Map<Class<? extends Annotation>, List<C3524d>> f16079d;
    private final Map<Class<? extends Annotation>, List<C3523b>> f16080e;

    static {
        f16076a = new C3530n();
        f16077b = new C3531o();
    }

    public C3528l(Class<?> cls) {
        this.f16078c = cls;
        if (cls == null || cls.getConstructors().length <= 1) {
            Map linkedHashMap = new LinkedHashMap();
            Map linkedHashMap2 = new LinkedHashMap();
            m19214a(linkedHashMap, linkedHashMap2);
            this.f16079d = C3528l.m19206a(linkedHashMap);
            this.f16080e = C3528l.m19206a(linkedHashMap2);
            return;
        }
        throw new IllegalArgumentException("Test class can only have one constructor");
    }

    private static <T> List<T> m19205a(Map<Class<? extends Annotation>, List<T>> map, Class<? extends Annotation> cls, boolean z) {
        if (!map.containsKey(cls) && z) {
            map.put(cls, new ArrayList());
        }
        List<T> list = (List) map.get(cls);
        return list == null ? Collections.emptyList() : list;
    }

    private static <T extends C3522c<T>> Map<Class<? extends Annotation>, List<T>> m19206a(Map<Class<? extends Annotation>, List<T>> map) {
        Map linkedHashMap = new LinkedHashMap();
        for (Entry entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey(), Collections.unmodifiableList((List) entry.getValue()));
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    protected static <T extends C3522c<T>> void m19207a(T t, Map<Class<? extends Annotation>, List<T>> map) {
        Annotation[] a = t.m19162a();
        int length = a.length;
        int i = 0;
        while (i < length) {
            Class annotationType = a[i].annotationType();
            List a2 = C3528l.m19205a((Map) map, annotationType, true);
            if (!t.m19163a(a2)) {
                if (C3528l.m19210e(annotationType)) {
                    a2.add(0, t);
                } else {
                    a2.add(t);
                }
                i++;
            } else {
                return;
            }
        }
    }

    private <T> List<T> m19208b(Map<?, List<T>> map) {
        Collection linkedHashSet = new LinkedHashSet();
        for (List addAll : map.values()) {
            linkedHashSet.addAll(addAll);
        }
        return new ArrayList(linkedHashSet);
    }

    private static Field[] m19209d(Class<?> cls) {
        Field[] declaredFields = cls.getDeclaredFields();
        Arrays.sort(declaredFields, f16076a);
        return declaredFields;
    }

    private static boolean m19210e(Class<? extends Annotation> cls) {
        return cls.equals(C3550f.class) || cls.equals(C3561g.class);
    }

    private static List<Class<?>> m19211f(Class<?> cls) {
        List arrayList = new ArrayList();
        Class superclass;
        while (superclass != null) {
            arrayList.add(superclass);
            superclass = superclass.getSuperclass();
        }
        return arrayList;
    }

    public <T extends Annotation> T m19212a(Class<T> cls) {
        return this.f16078c == null ? null : this.f16078c.getAnnotation(cls);
    }

    public <T> List<T> m19213a(Object obj, Class<? extends Annotation> cls, Class<T> cls2) {
        List<T> arrayList = new ArrayList();
        for (C3523b a : m19220c(cls)) {
            try {
                Object a2 = a.m19171a(obj);
                if (cls2.isInstance(a2)) {
                    arrayList.add(cls2.cast(a2));
                }
            } catch (Throwable e) {
                throw new RuntimeException("How did getFields return a field we couldn't access?", e);
            }
        }
        return arrayList;
    }

    protected void m19214a(Map<Class<? extends Annotation>, List<C3524d>> map, Map<Class<? extends Annotation>, List<C3523b>> map2) {
        for (Class cls : C3528l.m19211f(this.f16078c)) {
            for (Method c3524d : C3451h.m18812a(cls)) {
                C3528l.m19207a(new C3524d(c3524d), (Map) map);
            }
            for (Field c3523b : C3528l.m19209d(cls)) {
                C3528l.m19207a(new C3523b(c3523b), (Map) map2);
            }
        }
    }

    public Annotation[] m19215a() {
        return this.f16078c == null ? new Annotation[0] : this.f16078c.getAnnotations();
    }

    public List<C3524d> m19216b() {
        List<C3524d> b = m19208b(this.f16079d);
        Collections.sort(b, f16077b);
        return b;
    }

    public List<C3524d> m19217b(Class<? extends Annotation> cls) {
        return Collections.unmodifiableList(C3528l.m19205a(this.f16079d, (Class) cls, false));
    }

    public <T> List<T> m19218b(Object obj, Class<? extends Annotation> cls, Class<T> cls2) {
        List<T> arrayList = new ArrayList();
        for (C3524d c3524d : m19217b((Class) cls)) {
            try {
                if (cls2.isAssignableFrom(c3524d.m19197i())) {
                    arrayList.add(cls2.cast(c3524d.m19183a(obj, new Object[0])));
                }
            } catch (Throwable th) {
                RuntimeException runtimeException = new RuntimeException("Exception in " + c3524d.m19190b(), th);
            }
        }
        return arrayList;
    }

    public List<C3523b> m19219c() {
        return m19208b(this.f16080e);
    }

    public List<C3523b> m19220c(Class<? extends Annotation> cls) {
        return Collections.unmodifiableList(C3528l.m19205a(this.f16080e, (Class) cls, false));
    }

    public Class<?> m19221d() {
        return this.f16078c;
    }

    public String m19222e() {
        return this.f16078c == null ? "null" : this.f16078c.getName();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return this.f16078c == ((C3528l) obj).f16078c;
    }

    public Constructor<?> m19223f() {
        Constructor[] constructors = this.f16078c.getConstructors();
        C3459c.m18878b(1, (long) constructors.length);
        return constructors[0];
    }

    public boolean m19224g() {
        return Modifier.isPublic(this.f16078c.getModifiers());
    }

    public boolean m19225h() {
        return this.f16078c.isMemberClass() && !Modifier.isStatic(this.f16078c.getModifiers());
    }

    public int hashCode() {
        return this.f16078c == null ? 0 : this.f16078c.hashCode();
    }
}
