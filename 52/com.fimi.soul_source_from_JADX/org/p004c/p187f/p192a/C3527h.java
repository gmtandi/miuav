package org.p004c.p187f.p192a;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;

/* renamed from: org.c.f.a.h */
class C3527h {
    private final Method f16075a;

    C3527h(Method method) {
        this.f16075a = method;
    }

    private void m19200a(GenericArrayType genericArrayType, List<Throwable> list) {
        m19202a(genericArrayType.getGenericComponentType(), (List) list);
    }

    private void m19201a(ParameterizedType parameterizedType, List<Throwable> list) {
        for (Type a : parameterizedType.getActualTypeArguments()) {
            m19202a(a, (List) list);
        }
    }

    private void m19202a(Type type, List<Throwable> list) {
        if (type instanceof TypeVariable) {
            list.add(new Exception("Method " + this.f16075a.getName() + "() contains unresolved type variable " + type));
        } else if (type instanceof ParameterizedType) {
            m19201a((ParameterizedType) type, (List) list);
        } else if (type instanceof WildcardType) {
            m19203a((WildcardType) type, (List) list);
        } else if (type instanceof GenericArrayType) {
            m19200a((GenericArrayType) type, (List) list);
        }
    }

    private void m19203a(WildcardType wildcardType, List<Throwable> list) {
        int i = 0;
        for (Type a : wildcardType.getUpperBounds()) {
            m19202a(a, (List) list);
        }
        Type[] lowerBounds = wildcardType.getLowerBounds();
        int length = lowerBounds.length;
        while (i < length) {
            m19202a(lowerBounds[i], (List) list);
            i++;
        }
    }

    void m19204a(List<Throwable> list) {
        for (Type a : this.f16075a.getGenericParameterTypes()) {
            m19202a(a, (List) list);
        }
    }
}
