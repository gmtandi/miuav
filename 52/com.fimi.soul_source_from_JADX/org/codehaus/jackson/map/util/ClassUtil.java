package org.codehaus.jackson.map.util;

import com.fimi.soul.media.player.FimiMediaMeta;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

public final class ClassUtil {

    class EnumTypeLocator {
        static final EnumTypeLocator instance;
        private final Field enumMapTypeField;
        private final Field enumSetTypeField;

        static {
            instance = new EnumTypeLocator();
        }

        private EnumTypeLocator() {
            this.enumSetTypeField = locateField(EnumSet.class, "elementType", Class.class);
            this.enumMapTypeField = locateField(EnumMap.class, "elementType", Class.class);
        }

        private Object get(Object obj, Field field) {
            try {
                return field.get(obj);
            } catch (Throwable e) {
                throw new IllegalArgumentException(e);
            }
        }

        private static Field locateField(Class<?> cls, String str, Class<?> cls2) {
            int i;
            int length;
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName()) && field.getType() == cls2) {
                    break;
                }
            }
            Field field2 = null;
            if (field2 == null) {
                length = declaredFields.length;
                i = 0;
                Field field3 = field2;
                while (i < length) {
                    field2 = declaredFields[i];
                    if (field2.getType() != cls2) {
                        field2 = field3;
                    } else if (field3 != null) {
                        return null;
                    }
                    i++;
                    field3 = field2;
                }
                field2 = field3;
            }
            if (field2 == null) {
                return field2;
            }
            try {
                field2.setAccessible(true);
                return field2;
            } catch (Throwable th) {
                return field2;
            }
        }

        public Class<? extends Enum<?>> enumTypeFor(EnumMap<?, ?> enumMap) {
            if (this.enumMapTypeField != null) {
                return (Class) get(enumMap, this.enumMapTypeField);
            }
            throw new IllegalStateException("Can not figure out type for EnumMap (odd JDK platform?)");
        }

        public Class<? extends Enum<?>> enumTypeFor(EnumSet<?> enumSet) {
            if (this.enumSetTypeField != null) {
                return (Class) get(enumSet, this.enumSetTypeField);
            }
            throw new IllegalStateException("Can not figure out type for EnumSet (odd JDK platform?)");
        }
    }

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

    public static String canBeABeanType(Class<?> cls) {
        return cls.isAnnotation() ? "annotation" : cls.isArray() ? "array" : cls.isEnum() ? "enum" : cls.isPrimitive() ? "primitive" : null;
    }

    public static void checkAndFixAccess(Member member) {
        AccessibleObject accessibleObject = (AccessibleObject) member;
        try {
            accessibleObject.setAccessible(true);
        } catch (SecurityException e) {
            if (!accessibleObject.isAccessible()) {
                throw new IllegalArgumentException("Can not access " + member + " (from class " + member.getDeclaringClass().getName() + "; failed to set access: " + e.getMessage());
            }
        }
    }

    public static <T> T createInstance(Class<T> cls, boolean z) {
        Constructor findConstructor = findConstructor(cls, z);
        if (findConstructor == null) {
            throw new IllegalArgumentException("Class " + cls.getName() + " has no default (no arg) constructor");
        }
        try {
            return findConstructor.newInstance(new Object[0]);
        } catch (Throwable e) {
            unwrapAndThrowAsIAE(e, "Failed to instantiate class " + cls.getName() + ", problem: " + e.getMessage());
            return null;
        }
    }

    public static Object defaultValue(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return Integer.valueOf(0);
        }
        if (cls == Long.TYPE) {
            return Long.valueOf(0);
        }
        if (cls == Boolean.TYPE) {
            return Boolean.FALSE;
        }
        if (cls == Double.TYPE) {
            return Double.valueOf(0.0d);
        }
        if (cls == Float.TYPE) {
            return Float.valueOf(0.0f);
        }
        if (cls == Byte.TYPE) {
            return Byte.valueOf((byte) 0);
        }
        if (cls == Short.TYPE) {
            return Short.valueOf((short) 0);
        }
        if (cls == Character.TYPE) {
            return Character.valueOf('\u0000');
        }
        throw new IllegalArgumentException("Class " + cls.getName() + " is not a primitive type");
    }

    public static <T> Constructor<T> findConstructor(Class<T> cls, boolean z) {
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (z) {
                checkAndFixAccess(declaredConstructor);
                return declaredConstructor;
            } else if (Modifier.isPublic(declaredConstructor.getModifiers())) {
                return declaredConstructor;
            } else {
                throw new IllegalArgumentException("Default constructor for " + cls.getName() + " is not accessible (non-public?): not allowed to try modify access via Reflection: can not instantiate type");
            }
        } catch (NoSuchMethodException e) {
            return null;
        } catch (Throwable e2) {
            unwrapAndThrowAsIAE(e2, "Failed to find default constructor of class " + cls.getName() + ", problem: " + e2.getMessage());
            return null;
        }
    }

    public static Class<? extends Enum<?>> findEnumType(Class<?> cls) {
        return cls.getSuperclass() != Enum.class ? cls.getSuperclass() : cls;
    }

    public static Class<? extends Enum<?>> findEnumType(Enum<?> enumR) {
        Class<? extends Enum<?>> cls = enumR.getClass();
        return cls.getSuperclass() != Enum.class ? cls.getSuperclass() : cls;
    }

    public static Class<? extends Enum<?>> findEnumType(EnumMap<?, ?> enumMap) {
        return !enumMap.isEmpty() ? findEnumType((Enum) enumMap.keySet().iterator().next()) : EnumTypeLocator.instance.enumTypeFor((EnumMap) enumMap);
    }

    public static Class<? extends Enum<?>> findEnumType(EnumSet<?> enumSet) {
        return !enumSet.isEmpty() ? findEnumType((Enum) enumSet.iterator().next()) : EnumTypeLocator.instance.enumTypeFor((EnumSet) enumSet);
    }

    public static List<Class<?>> findSuperTypes(Class<?> cls, Class<?> cls2) {
        return findSuperTypes(cls, cls2, new ArrayList());
    }

    public static List<Class<?>> findSuperTypes(Class<?> cls, Class<?> cls2, List<Class<?>> list) {
        _addSuperTypes(cls, cls2, list, false);
        return list;
    }

    public static String getClassDescription(Object obj) {
        if (obj == null) {
            return FimiMediaMeta.IJKM_VAL_TYPE__UNKNOWN;
        }
        return (obj instanceof Class ? (Class) obj : obj.getClass()).getName();
    }

    public static Throwable getRootCause(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    public static boolean hasGetterSignature(Method method) {
        if (Modifier.isStatic(method.getModifiers())) {
            return false;
        }
        Class[] parameterTypes = method.getParameterTypes();
        return (parameterTypes == null || parameterTypes.length == 0) && Void.TYPE != method.getReturnType();
    }

    public static boolean isCollectionMapOrArray(Class<?> cls) {
        return cls.isArray() || Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls);
    }

    public static boolean isConcrete(Class<?> cls) {
        return (cls.getModifiers() & 1536) == 0;
    }

    public static boolean isConcrete(Member member) {
        return (member.getModifiers() & 1536) == 0;
    }

    public static String isLocalType(Class<?> cls) {
        try {
            if (cls.getEnclosingMethod() != null) {
                return "local/anonymous";
            }
            if (!(cls.getEnclosingClass() == null || Modifier.isStatic(cls.getModifiers()))) {
                return "non-static member class";
            }
            return null;
        } catch (SecurityException e) {
        } catch (NullPointerException e2) {
        }
    }

    public static boolean isProxyType(Class<?> cls) {
        if (Proxy.isProxyClass(cls)) {
            return true;
        }
        String name = cls.getName();
        return name.startsWith("net.sf.cglib.proxy.") || name.startsWith("org.hibernate.proxy.");
    }

    public static void throwAsIAE(Throwable th) {
        throwAsIAE(th, th.getMessage());
    }

    public static void throwAsIAE(Throwable th, String str) {
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        } else {
            throw new IllegalArgumentException(str, th);
        }
    }

    public static void throwRootCause(Throwable th) {
        Throwable rootCause = getRootCause(th);
        if (rootCause instanceof Exception) {
            throw ((Exception) rootCause);
        }
        throw ((Error) rootCause);
    }

    public static void unwrapAndThrowAsIAE(Throwable th) {
        throwAsIAE(getRootCause(th));
    }

    public static void unwrapAndThrowAsIAE(Throwable th, String str) {
        throwAsIAE(getRootCause(th), str);
    }

    public static Class<?> wrapperType(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return Integer.class;
        }
        if (cls == Long.TYPE) {
            return Long.class;
        }
        if (cls == Boolean.TYPE) {
            return Boolean.class;
        }
        if (cls == Double.TYPE) {
            return Double.class;
        }
        if (cls == Float.TYPE) {
            return Float.class;
        }
        if (cls == Byte.TYPE) {
            return Byte.class;
        }
        if (cls == Short.TYPE) {
            return Short.class;
        }
        if (cls == Character.TYPE) {
            return Character.class;
        }
        throw new IllegalArgumentException("Class " + cls.getName() + " is not a primitive type");
    }
}
