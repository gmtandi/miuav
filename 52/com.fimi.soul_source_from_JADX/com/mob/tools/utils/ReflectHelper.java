package com.mob.tools.utils;

import com.mob.tools.gui.CachePool;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ReflectHelper {
    private static CachePool<String, Constructor<?>> cachedConstr;
    private static CachePool<String, Method> cachedMethod;
    private static HashMap<String, Class<?>> classMap;
    private static HashSet<String> packageSet;

    /* renamed from: com.mob.tools.utils.ReflectHelper.1 */
    final class C21791 implements InvocationHandler {
        final /* synthetic */ HashMap val$proxyHandler;

        C21791(HashMap hashMap) {
            this.val$proxyHandler = hashMap;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) {
            ReflectRunnable reflectRunnable = (ReflectRunnable) this.val$proxyHandler.get(method.getName());
            if (reflectRunnable != null) {
                return reflectRunnable.run(objArr);
            }
            throw new NoSuchMethodException();
        }
    }

    public interface ReflectRunnable {
        Object run(Object obj);
    }

    static {
        packageSet = new HashSet();
        packageSet.add("java.lang");
        packageSet.add("java.io");
        packageSet.add("java.nio");
        packageSet.add("java.net");
        packageSet.add("java.util");
        packageSet.add("com.mob.tools");
        packageSet.add("com.mob.tools.gui");
        packageSet.add("com.mob.tools.log");
        packageSet.add("com.mob.tools.network");
        packageSet.add("com.mob.tools.utils");
        classMap = new HashMap();
        cachedMethod = new CachePool(25);
        cachedConstr = new CachePool(5);
    }

    public static Object createProxy(HashMap<String, ReflectRunnable> hashMap, Class<?>... clsArr) {
        return Proxy.newProxyInstance(hashMap.getClass().getClassLoader(), clsArr, new C21791(hashMap));
    }

    public static Class<?> getClass(String str) {
        Class<?> importedClass = getImportedClass(str);
        if (importedClass == null) {
            try {
                importedClass = Class.forName(str);
                if (importedClass != null) {
                    classMap.put(str, importedClass);
                }
            } catch (Throwable th) {
            }
        }
        return importedClass;
    }

    private static synchronized Class<?> getImportedClass(String str) {
        Class<?> cls;
        synchronized (ReflectHelper.class) {
            cls = (Class) classMap.get(str);
            if (cls == null) {
                Iterator it = packageSet.iterator();
                while (it.hasNext()) {
                    try {
                        importClass(((String) it.next()) + "." + str);
                    } catch (Throwable th) {
                    }
                    cls = (Class) classMap.get(str);
                    if (cls != null) {
                        break;
                    }
                }
            }
        }
        return cls;
    }

    public static <T> T getInstanceField(Object obj, String str) {
        try {
            return onGetInstanceField(obj, str);
        } catch (Throwable th) {
            if (!(th instanceof NoSuchFieldException)) {
                Throwable th2 = new Throwable("className: " + obj.getClass() + ", fieldName: " + str, th);
            }
        }
    }

    public static <T> T getStaticField(String str, String str2) {
        try {
            return onGetStaticField(str, str2);
        } catch (Throwable th) {
            if (!(th instanceof NoSuchFieldException)) {
                Throwable th2 = new Throwable("className: " + str + ", fieldName: " + str2, th);
            }
        }
    }

    private static Class<?>[] getTypes(Object[] objArr) {
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i] == null ? null : objArr[i].getClass();
        }
        return clsArr;
    }

    public static String importClass(String str) {
        return importClass(null, str);
    }

    public static synchronized String importClass(String str, String str2) {
        String str3;
        synchronized (ReflectHelper.class) {
            if (str2.endsWith(".*")) {
                packageSet.add(str2.substring(0, str2.length() - 2));
                str3 = "*";
            } else {
                Class cls = Class.forName(str2);
                str3 = str == null ? cls.getSimpleName() : str;
                classMap.put(str3, cls);
            }
        }
        return str3;
    }

    public static <T> T invokeInstanceMethod(Object obj, String str, Object... objArr) {
        try {
            return onInvokeInstanceMethod(obj, str, objArr);
        } catch (Throwable th) {
            if (!(th instanceof NoSuchMethodException)) {
                Throwable th2 = new Throwable("className: " + obj.getClass() + ", methodName: " + str, th);
            }
        }
    }

    public static <T> T invokeStaticMethod(String str, String str2, Object... objArr) {
        try {
            return onInvokeStaticMethod(str, str2, objArr);
        } catch (Throwable th) {
            if (!(th instanceof NoSuchMethodException)) {
                Throwable th2 = new Throwable("className: " + str + ", methodName: " + str2, th);
            }
        }
    }

    private static boolean matchParams(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        int i = 0;
        while (i < clsArr.length) {
            if (clsArr2[i] != null && !primitiveEquals(clsArr[i], clsArr2[i]) && !clsArr[i].isAssignableFrom(clsArr2[i])) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static Object newArray(String str, Object... objArr) {
        int[] iArr;
        int i = 0;
        int i2 = 0;
        String str2 = str;
        while (str2.startsWith("[")) {
            i2++;
            str2 = str2.substring(1);
        }
        if (i2 == objArr.length) {
            int[] iArr2 = new int[i2];
            while (i < i2) {
                try {
                    iArr2[i] = Integer.parseInt(String.valueOf(objArr[i]));
                    i++;
                } catch (Throwable th) {
                    iArr = null;
                }
            }
            iArr = iArr2;
        } else {
            iArr = null;
        }
        if (iArr != null) {
            Class importedClass = "B".equals(str2) ? Byte.TYPE : "S".equals(str2) ? Short.TYPE : "I".equals(str2) ? Integer.TYPE : "J".equals(str2) ? Long.TYPE : "F".equals(str2) ? Float.TYPE : "D".equals(str2) ? Double.TYPE : "Z".equals(str2) ? Boolean.TYPE : "C".equals(str2) ? Character.TYPE : getImportedClass(str2);
            if (importedClass != null) {
                return Array.newInstance(importedClass, iArr);
            }
        }
        throw new NoSuchMethodException("className: [" + str + ", methodName: <init>");
    }

    public static Object newInstance(String str, Object... objArr) {
        try {
            return onNewInstance(str, objArr);
        } catch (Throwable th) {
            if (!(th instanceof NoSuchMethodException)) {
                Throwable th2 = new Throwable("className: " + str + ", methodName: <init>", th);
            }
        }
    }

    private static Object onGetElement(Object obj, String str) {
        if ("length".equals(str)) {
            return Integer.valueOf(Array.getLength(obj));
        }
        if (str.startsWith("[") && str.endsWith("]")) {
            int parseInt;
            try {
                parseInt = Integer.parseInt(str.substring(1, str.length() - 1));
            } catch (Throwable th) {
                parseInt = -1;
            }
            if (parseInt != -1) {
                return Array.get(obj, parseInt);
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str);
    }

    private static <T> T onGetInstanceField(Object obj, String str) {
        if (obj.getClass().isArray()) {
            return onGetElement(obj, str);
        }
        ArrayList arrayList = new ArrayList();
        for (Class cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            arrayList.add(cls);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Field declaredField;
            try {
                declaredField = ((Class) it.next()).getDeclaredField(str);
            } catch (Throwable th) {
                declaredField = null;
            }
            if (declaredField != null && !Modifier.isStatic(declaredField.getModifiers())) {
                declaredField.setAccessible(true);
                return declaredField.get(obj);
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str);
    }

    private static <T> T onGetStaticField(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        for (Class importedClass = getImportedClass(str); importedClass != null; importedClass = importedClass.getSuperclass()) {
            arrayList.add(importedClass);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Field declaredField;
            try {
                declaredField = ((Class) it.next()).getDeclaredField(str2);
            } catch (Throwable th) {
                declaredField = null;
            }
            if (declaredField != null && Modifier.isStatic(declaredField.getModifiers())) {
                declaredField.setAccessible(true);
                return declaredField.get(null);
            }
        }
        throw new NoSuchFieldException("className: " + str + ", fieldName: " + str2);
    }

    private static <T> T onInvokeInstanceMethod(Object obj, String str, Object... objArr) {
        Class cls = obj.getClass();
        String str2 = cls.getName() + "#" + str + "#" + objArr.length;
        Method method = (Method) cachedMethod.get(str2);
        Class[] types = getTypes(objArr);
        if (method == null || Modifier.isStatic(method.getModifiers()) || !matchParams(method.getParameterTypes(), types)) {
            ArrayList arrayList = new ArrayList();
            for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                arrayList.add(cls2);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                for (Method method2 : ((Class) it.next()).getDeclaredMethods()) {
                    if (method2.getName().equals(str) && !Modifier.isStatic(method2.getModifiers()) && matchParams(method2.getParameterTypes(), types)) {
                        cachedMethod.put(str2, method2);
                        method2.setAccessible(true);
                        if (method2.getReturnType() != Void.TYPE) {
                            return method2.invoke(obj, objArr);
                        }
                        method2.invoke(obj, objArr);
                        return null;
                    }
                }
            }
            throw new NoSuchMethodException("className: " + obj.getClass() + ", methodName: " + str);
        }
        method.setAccessible(true);
        if (method.getReturnType() != Void.TYPE) {
            return method.invoke(obj, objArr);
        }
        method.invoke(obj, objArr);
        return null;
    }

    private static <T> T onInvokeStaticMethod(String str, String str2, Object... objArr) {
        String str3 = str + "#" + str2 + "#" + objArr.length;
        Method method = (Method) cachedMethod.get(str3);
        Class[] types = getTypes(objArr);
        if (method != null && Modifier.isStatic(method.getModifiers()) && matchParams(method.getParameterTypes(), types)) {
            method.setAccessible(true);
            if (method.getReturnType() != Void.TYPE) {
                return method.invoke(null, objArr);
            }
            method.invoke(null, objArr);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Class importedClass = getImportedClass(str); importedClass != null; importedClass = importedClass.getSuperclass()) {
            arrayList.add(importedClass);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            for (Method method2 : ((Class) it.next()).getDeclaredMethods()) {
                if (method2.getName().equals(str2) && Modifier.isStatic(method2.getModifiers()) && matchParams(method2.getParameterTypes(), types)) {
                    cachedMethod.put(str3, method2);
                    method2.setAccessible(true);
                    if (method2.getReturnType() != Void.TYPE) {
                        return method2.invoke(null, objArr);
                    }
                    method2.invoke(null, objArr);
                    return null;
                }
            }
        }
        throw new NoSuchMethodException("className: " + str + ", methodName: " + str2);
    }

    private static Object onNewInstance(String str, Object... objArr) {
        if (str.startsWith("[")) {
            return newArray(str, objArr);
        }
        String str2 = str + "#" + objArr.length;
        Constructor constructor = (Constructor) cachedConstr.get(str2);
        Class[] types = getTypes(objArr);
        if (constructor == null || !matchParams(constructor.getParameterTypes(), types)) {
            for (Constructor constructor2 : getImportedClass(str).getDeclaredConstructors()) {
                if (matchParams(constructor2.getParameterTypes(), types)) {
                    cachedConstr.put(str2, constructor2);
                    constructor2.setAccessible(true);
                    return constructor2.newInstance(objArr);
                }
            }
            throw new NoSuchMethodException("className: " + str + ", methodName: <init>");
        }
        constructor.setAccessible(true);
        return constructor.newInstance(objArr);
    }

    private static void onSetElement(Object obj, String str, Object obj2) {
        Object obj3 = null;
        if (str.startsWith("[") && str.endsWith("]")) {
            int parseInt;
            try {
                parseInt = Integer.parseInt(str.substring(1, str.length() - 1));
            } catch (Throwable th) {
                parseInt = -1;
            }
            if (parseInt != -1) {
                String name = obj.getClass().getName();
                while (name.startsWith("[")) {
                    name = name.substring(1);
                }
                Class cls = obj2.getClass();
                if ("B".equals(name)) {
                    if (cls == Byte.class) {
                        Array.set(obj, parseInt, obj2);
                        return;
                    }
                } else if ("S".equals(name)) {
                    if (cls == Short.class) {
                        obj3 = obj2;
                    } else if (cls == Byte.class) {
                        obj3 = Short.valueOf((short) ((Byte) obj2).byteValue());
                    }
                    if (obj3 != null) {
                        Array.set(obj, parseInt, obj3);
                        return;
                    }
                } else if ("I".equals(name)) {
                    if (cls == Integer.class) {
                        obj3 = obj2;
                    } else if (cls == Short.class) {
                        obj3 = Integer.valueOf(((Short) obj2).shortValue());
                    } else if (cls == Byte.class) {
                        obj3 = Integer.valueOf(((Byte) obj2).byteValue());
                    }
                    if (obj3 != null) {
                        Array.set(obj, parseInt, obj3);
                        return;
                    }
                } else if ("J".equals(name)) {
                    if (cls == Long.class) {
                        obj3 = obj2;
                    } else if (cls == Integer.class) {
                        obj3 = Long.valueOf((long) ((Integer) obj2).intValue());
                    } else if (cls == Short.class) {
                        obj3 = Long.valueOf((long) ((Short) obj2).shortValue());
                    } else if (cls == Byte.class) {
                        obj3 = Long.valueOf((long) ((Byte) obj2).byteValue());
                    }
                    if (obj3 != null) {
                        Array.set(obj, parseInt, obj3);
                        return;
                    }
                } else if ("F".equals(name)) {
                    if (cls == Float.class) {
                        obj3 = obj2;
                    } else if (cls == Long.class) {
                        obj3 = Float.valueOf((float) ((Long) obj2).longValue());
                    } else if (cls == Integer.class) {
                        obj3 = Float.valueOf((float) ((Integer) obj2).intValue());
                    } else if (cls == Short.class) {
                        obj3 = Float.valueOf((float) ((Short) obj2).shortValue());
                    } else if (cls == Byte.class) {
                        obj3 = Float.valueOf((float) ((Byte) obj2).byteValue());
                    }
                    if (obj3 != null) {
                        Array.set(obj, parseInt, obj3);
                        return;
                    }
                } else if ("D".equals(name)) {
                    Object valueOf = cls == Double.class ? obj2 : cls == Float.class ? Double.valueOf((double) ((Float) obj2).floatValue()) : cls == Long.class ? Double.valueOf((double) ((Long) obj2).longValue()) : cls == Integer.class ? Double.valueOf((double) ((Integer) obj2).intValue()) : cls == Short.class ? Double.valueOf((double) ((Short) obj2).shortValue()) : cls == Byte.class ? Double.valueOf((double) ((Byte) obj2).byteValue()) : null;
                    if (valueOf != null) {
                        Array.set(obj, parseInt, valueOf);
                        return;
                    }
                } else if ("Z".equals(name)) {
                    if (cls == Boolean.class) {
                        Array.set(obj, parseInt, obj2);
                        return;
                    }
                } else if ("C".equals(name)) {
                    if (cls == Character.class) {
                        Array.set(obj, parseInt, obj2);
                        return;
                    }
                } else if (name.equals(cls.getName())) {
                    Array.set(obj, parseInt, obj2);
                    return;
                }
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str + ", value: " + String.valueOf(obj2));
    }

    private static void onSetInstanceField(Object obj, String str, Object obj2) {
        if (obj.getClass().isArray()) {
            onSetElement(obj, str, obj2);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Class cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            arrayList.add(cls);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Field declaredField;
            try {
                declaredField = ((Class) it.next()).getDeclaredField(str);
            } catch (Throwable th) {
                declaredField = null;
            }
            if (declaredField != null && !Modifier.isStatic(declaredField.getModifiers())) {
                declaredField.setAccessible(true);
                declaredField.set(obj, obj2);
                return;
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str + ", value: " + String.valueOf(obj2));
    }

    private static void onSetStaticField(String str, String str2, Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Class importedClass = getImportedClass(str); importedClass != null; importedClass = importedClass.getSuperclass()) {
            arrayList.add(importedClass);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Field declaredField;
            try {
                declaredField = ((Class) it.next()).getDeclaredField(str2);
            } catch (Throwable th) {
                declaredField = null;
            }
            if (declaredField != null && Modifier.isStatic(declaredField.getModifiers())) {
                declaredField.setAccessible(true);
                declaredField.set(null, obj);
                return;
            }
        }
        throw new NoSuchFieldException("className: " + str + ", fieldName: " + str2 + ", value: " + String.valueOf(obj));
    }

    private static boolean primitiveEquals(Class<?> cls, Class<?> cls2) {
        return (cls == Byte.TYPE && cls2 == Byte.class) || ((cls == Short.TYPE && (cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Character.TYPE && (cls2 == Character.class || cls2 == Short.class || cls2 == Byte.class)) || ((cls == Integer.TYPE && (cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Long.TYPE && (cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Float.TYPE && (cls2 == Float.class || cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Double.TYPE && (cls2 == Double.class || cls2 == Float.class || cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || (cls == Boolean.TYPE && cls2 == Boolean.class)))))));
    }

    public static void setInstanceField(Object obj, String str, Object obj2) {
        try {
            onSetInstanceField(obj, str, obj2);
        } catch (Throwable th) {
            if (!(th instanceof NoSuchFieldException)) {
                Throwable th2 = new Throwable("className: " + obj.getClass() + ", fieldName: " + str + ", value: " + String.valueOf(obj2), th);
            }
        }
    }

    public static void setStaticField(String str, String str2, Object obj) {
        try {
            onSetStaticField(str, str2, obj);
        } catch (Throwable th) {
            if (!(th instanceof NoSuchFieldException)) {
                Throwable th2 = new Throwable("className: " + str + ", fieldName: " + str2 + ", value: " + String.valueOf(obj), th);
            }
        }
    }
}
