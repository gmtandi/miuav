package org.codehaus.jackson.map.util;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.io.SerializedString;
import org.codehaus.jackson.map.AnnotationIntrospector;

public final class EnumValues {
    private final EnumMap<?, SerializedString> _values;

    private EnumValues(Map<Enum<?>, SerializedString> map) {
        this._values = new EnumMap(map);
    }

    public static EnumValues construct(Class<Enum<?>> cls, AnnotationIntrospector annotationIntrospector) {
        return constructFromName(cls, annotationIntrospector);
    }

    public static EnumValues constructFromName(Class<Enum<?>> cls, AnnotationIntrospector annotationIntrospector) {
        Enum[] enumArr = (Enum[]) ClassUtil.findEnumType((Class) cls).getEnumConstants();
        if (enumArr != null) {
            Map hashMap = new HashMap();
            for (Enum enumR : enumArr) {
                hashMap.put(enumR, new SerializedString(annotationIntrospector.findEnumValue(enumR)));
            }
            return new EnumValues(hashMap);
        }
        throw new IllegalArgumentException("Can not determine enum constants for Class " + cls.getName());
    }

    public static EnumValues constructFromToString(Class<Enum<?>> cls, AnnotationIntrospector annotationIntrospector) {
        Enum[] enumArr = (Enum[]) ClassUtil.findEnumType((Class) cls).getEnumConstants();
        if (enumArr != null) {
            Map hashMap = new HashMap();
            for (Enum enumR : enumArr) {
                hashMap.put(enumR, new SerializedString(enumR.toString()));
            }
            return new EnumValues(hashMap);
        }
        throw new IllegalArgumentException("Can not determine enum constants for Class " + cls.getName());
    }

    public SerializedString serializedValueFor(Enum<?> enumR) {
        return (SerializedString) this._values.get(enumR);
    }

    @Deprecated
    public String valueFor(Enum<?> enumR) {
        SerializedString serializedString = (SerializedString) this._values.get(enumR);
        return serializedString == null ? null : serializedString.getValue();
    }

    public Collection<SerializedString> values() {
        return this._values.values();
    }
}
