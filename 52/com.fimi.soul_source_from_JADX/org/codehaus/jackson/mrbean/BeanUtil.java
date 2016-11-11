package org.codehaus.jackson.mrbean;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BeanUtil {
    private static void _addSuperTypes(Class<?> cls, Class<?> cls2, Collection<Class<?>> collection, boolean z) {
        if (cls != cls2 && cls != null && cls != Object.class) {
            if (z) {
                if (!collection.contains(cls)) {
                    collection.add(cls);
                } else {
                    return;
                }
            }
            for (Class _addSuperTypes : cls.getInterfaces()) {
                _addSuperTypes(_addSuperTypes, cls2, collection, true);
            }
            _addSuperTypes(cls.getSuperclass(), cls2, collection, true);
        }
    }

    public static List<Class<?>> findSuperTypes(Class<?> cls, Class<?> cls2) {
        return findSuperTypes(cls, cls2, new ArrayList());
    }

    public static List<Class<?>> findSuperTypes(Class<?> cls, Class<?> cls2, List<Class<?>> list) {
        _addSuperTypes(cls, cls2, list, false);
        return list;
    }

    protected static boolean isConcrete(Member member) {
        return (member.getModifiers() & 1536) == 0;
    }
}
