package org.p004c.p188a.p195e;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: org.c.a.e.d */
public class C3371d {
    private static final Map<Class<?>, Class<?>> f15865a;
    private final Class<?> f15866b;
    private final Annotation[] f15867c;

    static {
        f15865a = C3371d.m18534c();
    }

    private C3371d(Class<?> cls, Annotation[] annotationArr) {
        this.f15866b = cls;
        this.f15867c = annotationArr;
    }

    private <T extends Annotation> T m18528a(Annotation[] annotationArr, Class<T> cls, int i) {
        if (i == 0) {
            return null;
        }
        for (Annotation annotation : annotationArr) {
            Annotation annotation2;
            if (cls.isInstance(annotation2)) {
                return (Annotation) cls.cast(annotation2);
            }
            annotation2 = m18528a(annotation2.annotationType().getAnnotations(), (Class) cls, i - 1);
            if (annotation2 != null) {
                return (Annotation) cls.cast(annotation2);
            }
        }
        return null;
    }

    public static ArrayList<C3371d> m18529a(Method method) {
        return C3371d.m18530a(method.getParameterTypes(), method.getParameterAnnotations());
    }

    private static ArrayList<C3371d> m18530a(Class<?>[] clsArr, Annotation[][] annotationArr) {
        ArrayList<C3371d> arrayList = new ArrayList();
        for (int i = 0; i < clsArr.length; i++) {
            arrayList.add(new C3371d(clsArr[i], annotationArr[i]));
        }
        return arrayList;
    }

    public static List<C3371d> m18531a(Constructor<?> constructor) {
        return C3371d.m18530a(constructor.getParameterTypes(), constructor.getParameterAnnotations());
    }

    private static <T> void m18532a(Map<T, T> map, T t, T t2) {
        map.put(t, t2);
        map.put(t2, t);
    }

    private boolean m18533a(Class<?> cls, Class<?> cls2) {
        return f15865a.containsKey(cls2) ? cls.isAssignableFrom((Class) f15865a.get(cls2)) : false;
    }

    private static Map<Class<?>, Class<?>> m18534c() {
        Map hashMap = new HashMap();
        C3371d.m18532a(hashMap, Boolean.TYPE, (Object) Boolean.class);
        C3371d.m18532a(hashMap, Byte.TYPE, (Object) Byte.class);
        C3371d.m18532a(hashMap, Short.TYPE, (Object) Short.class);
        C3371d.m18532a(hashMap, Character.TYPE, (Object) Character.class);
        C3371d.m18532a(hashMap, Integer.TYPE, (Object) Integer.class);
        C3371d.m18532a(hashMap, Long.TYPE, (Object) Long.class);
        C3371d.m18532a(hashMap, Float.TYPE, (Object) Float.class);
        C3371d.m18532a(hashMap, Double.TYPE, (Object) Double.class);
        return Collections.unmodifiableMap(hashMap);
    }

    public Class<?> m18535a() {
        return this.f15866b;
    }

    public boolean m18536a(Class<?> cls) {
        return this.f15866b.isAssignableFrom(cls) || m18533a(this.f15866b, (Class) cls);
    }

    public boolean m18537a(Object obj) {
        return obj == null ? !this.f15866b.isPrimitive() : m18536a(obj.getClass());
    }

    public List<Annotation> m18538b() {
        return Arrays.asList(this.f15867c);
    }

    public boolean m18539b(Class<?> cls) {
        return cls.isAssignableFrom(this.f15866b) || m18533a((Class) cls, this.f15866b) || m18536a((Class) cls);
    }

    public boolean m18540c(Class<? extends Annotation> cls) {
        return m18542e(cls) != null;
    }

    public <T extends Annotation> T m18541d(Class<T> cls) {
        return m18528a(this.f15867c, (Class) cls, 3);
    }

    public <T extends Annotation> T m18542e(Class<T> cls) {
        for (Annotation annotation : m18538b()) {
            if (cls.isInstance(annotation)) {
                return (Annotation) cls.cast(annotation);
            }
        }
        return null;
    }
}
