package com.p017b.p019b;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: com.b.b.e */
class C0646e<T, V> extends C0617d<T, V> {
    private static final String f3921a = "get";
    private static final String f3922b = "is";
    private static final String f3923c = "set";
    private Method f3924d;
    private Method f3925e;
    private Field f3926f;

    public C0646e(Class<T> cls, Class<V> cls2, String str) {
        Class type;
        super(cls2, str);
        char toUpperCase = Character.toUpperCase(str.charAt(0));
        String str2 = toUpperCase + str.substring(1);
        String str3 = f3921a + str2;
        try {
            this.f3925e = cls.getMethod(str3, (Class[]) null);
        } catch (NoSuchMethodException e) {
            try {
                this.f3925e = cls.getDeclaredMethod(str3, (Class[]) null);
                this.f3925e.setAccessible(true);
            } catch (NoSuchMethodException e2) {
                str3 = f3922b + str2;
                try {
                    this.f3925e = cls.getMethod(str3, (Class[]) null);
                } catch (NoSuchMethodException e3) {
                    try {
                        this.f3925e = cls.getDeclaredMethod(str3, (Class[]) null);
                        this.f3925e.setAccessible(true);
                    } catch (NoSuchMethodException e4) {
                        this.f3926f = cls.getField(str);
                        type = this.f3926f.getType();
                        if (!m5708a((Class) cls2, type)) {
                            throw new C0645c("Underlying type (" + type + ") " + "does not match Property type (" + cls2 + ")");
                        }
                        return;
                    } catch (NoSuchFieldException e5) {
                        throw new C0645c("No accessor method or field found for property with name " + str);
                    }
                }
            }
        }
        type = this.f3925e.getReturnType();
        if (m5708a((Class) cls2, type)) {
            str2 = f3923c + str2;
            try {
                this.f3924d = cls.getDeclaredMethod(str2, new Class[]{type});
                this.f3924d.setAccessible(true);
                return;
            } catch (NoSuchMethodException e6) {
                return;
            }
        }
        throw new C0645c("Underlying type (" + type + ") " + "does not match Property type (" + cls2 + ")");
    }

    private boolean m5708a(Class<V> cls, Class cls2) {
        return cls2 != cls ? cls2.isPrimitive() ? (cls2 == Float.TYPE && cls == Float.class) || ((cls2 == Integer.TYPE && cls == Integer.class) || ((cls2 == Boolean.TYPE && cls == Boolean.class) || ((cls2 == Long.TYPE && cls == Long.class) || ((cls2 == Double.TYPE && cls == Double.class) || ((cls2 == Short.TYPE && cls == Short.class) || ((cls2 == Byte.TYPE && cls == Byte.class) || (cls2 == Character.TYPE && cls == Character.class))))))) : false : true;
    }

    public V m5709a(T t) {
        V invoke;
        if (this.f3925e != null) {
            try {
                invoke = this.f3925e.invoke(t, (Object[]) null);
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        } else if (this.f3926f != null) {
            try {
                invoke = this.f3926f.get(t);
            } catch (IllegalAccessException e3) {
                throw new AssertionError();
            }
        } else {
            throw new AssertionError();
        }
        return invoke;
    }

    public void m5710a(T t, V v) {
        if (this.f3924d != null) {
            try {
                this.f3924d.invoke(t, new Object[]{v});
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        } else if (this.f3926f != null) {
            try {
                this.f3926f.set(t, v);
            } catch (IllegalAccessException e3) {
                throw new AssertionError();
            }
        } else {
            throw new UnsupportedOperationException("Property " + m5398b() + " is read-only");
        }
    }

    public boolean m5711a() {
        return this.f3924d == null && this.f3926f == null;
    }
}
