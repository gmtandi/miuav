package org.codehaus.jackson.map.deser;

import java.util.HashMap;
import org.codehaus.jackson.map.AnnotationIntrospector;

public final class EnumResolver<T extends Enum<T>> {
    protected final Class<T> _enumClass;
    protected final T[] _enums;
    protected final HashMap<String, T> _enumsById;

    private EnumResolver(Class<T> cls, T[] tArr, HashMap<String, T> hashMap) {
        this._enumClass = cls;
        this._enums = tArr;
        this._enumsById = hashMap;
    }

    public static <ET extends Enum<ET>> EnumResolver<ET> constructFor(Class<ET> cls, AnnotationIntrospector annotationIntrospector) {
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        if (enumArr == null) {
            throw new IllegalArgumentException("No enum constants for class " + cls.getName());
        }
        HashMap hashMap = new HashMap();
        for (Enum enumR : enumArr) {
            hashMap.put(annotationIntrospector.findEnumValue(enumR), enumR);
        }
        return new EnumResolver(cls, enumArr, hashMap);
    }

    public static EnumResolver<?> constructUnsafe(Class<?> cls, AnnotationIntrospector annotationIntrospector) {
        return constructFor(cls, annotationIntrospector);
    }

    public static EnumResolver<?> constructUnsafeUsingToString(Class<?> cls) {
        return constructUsingToString(cls);
    }

    public static <ET extends Enum<ET>> EnumResolver<ET> constructUsingToString(Class<ET> cls) {
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        HashMap hashMap = new HashMap();
        int length = enumArr.length;
        while (true) {
            length--;
            if (length < 0) {
                return new EnumResolver(cls, enumArr, hashMap);
            }
            Enum enumR = enumArr[length];
            hashMap.put(enumR.toString(), enumR);
        }
    }

    public T findEnum(String str) {
        return (Enum) this._enumsById.get(str);
    }

    public T getEnum(int i) {
        return (i < 0 || i >= this._enums.length) ? null : this._enums[i];
    }

    public Class<T> getEnumClass() {
        return this._enumClass;
    }

    public int lastValidIndex() {
        return this._enums.length - 1;
    }
}
