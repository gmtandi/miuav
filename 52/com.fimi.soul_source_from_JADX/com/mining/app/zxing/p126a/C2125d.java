package com.mining.app.zxing.p126a;

import android.os.IBinder;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: com.mining.app.zxing.a.d */
public final class C2125d {
    private static final String f11178a;
    private static final Object f11179b;
    private static final Method f11180c;

    static {
        f11178a = C2125d.class.getSimpleName();
        f11179b = C2125d.m13092c();
        f11180c = C2125d.m13088a(f11179b);
        if (f11179b == null) {
            Log.v(f11178a, "This device does supports control of a flashlight");
        } else {
            Log.v(f11178a, "This device does not support control of a flashlight");
        }
    }

    private C2125d() {
    }

    private static Class<?> m13085a(String str) {
        Class<?> cls = null;
        try {
            cls = Class.forName(str);
        } catch (ClassNotFoundException e) {
        } catch (Throwable e2) {
            Log.w(f11178a, "Unexpected error while finding class " + str, e2);
        }
        return cls;
    }

    private static Object m13086a(Method method, Object obj, Object... objArr) {
        Object obj2 = null;
        try {
            obj2 = method.invoke(obj, objArr);
        } catch (Throwable e) {
            Log.w(f11178a, "Unexpected error while invoking " + method, e);
        } catch (InvocationTargetException e2) {
            Log.w(f11178a, "Unexpected error while invoking " + method, e2.getCause());
        } catch (Throwable e3) {
            Log.w(f11178a, "Unexpected error while invoking " + method, e3);
        }
        return obj2;
    }

    private static Method m13087a(Class<?> cls, String str, Class<?>... clsArr) {
        Method method = null;
        try {
            method = cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
        } catch (Throwable e2) {
            Log.w(f11178a, "Unexpected error while finding method " + str, e2);
        }
        return method;
    }

    private static Method m13088a(Object obj) {
        if (obj == null) {
            return null;
        }
        return C2125d.m13087a(obj.getClass(), "setFlashlightEnabled", Boolean.TYPE);
    }

    public static void m13089a() {
        C2125d.m13090a(true);
    }

    private static void m13090a(boolean z) {
        if (f11179b != null) {
            C2125d.m13086a(f11180c, f11179b, Boolean.valueOf(z));
        }
    }

    public static void m13091b() {
        C2125d.m13090a(false);
    }

    private static Object m13092c() {
        Class a = C2125d.m13085a("android.os.ServiceManager");
        if (a == null) {
            return null;
        }
        Method a2 = C2125d.m13087a(a, "getService", String.class);
        if (a2 == null || C2125d.m13086a(a2, null, "hardware") == null) {
            return null;
        }
        Class a3 = C2125d.m13085a("android.os.IHardwareService$Stub");
        if (a3 == null) {
            return null;
        }
        Method a4 = C2125d.m13087a(a3, "asInterface", IBinder.class);
        if (a4 == null) {
            return null;
        }
        return C2125d.m13086a(a4, null, r1);
    }
}
