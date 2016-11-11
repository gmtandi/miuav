package org.codehaus.jackson.map.jsontype.impl;

import java.util.EnumMap;
import java.util.EnumSet;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

public class ClassNameIdResolver extends TypeIdResolverBase {
    public ClassNameIdResolver(JavaType javaType, TypeFactory typeFactory) {
        super(javaType, typeFactory);
    }

    protected final String _idFrom(Object obj, Class<?> cls) {
        Class superclass;
        if (Enum.class.isAssignableFrom(cls) && !cls.isEnum()) {
            superclass = cls.getSuperclass();
        }
        String name = superclass.getName();
        if (!name.startsWith("java.util")) {
            return name;
        }
        if (obj instanceof EnumSet) {
            return TypeFactory.defaultInstance().constructCollectionType(EnumSet.class, ClassUtil.findEnumType((EnumSet) obj)).toCanonical();
        } else if (obj instanceof EnumMap) {
            return TypeFactory.defaultInstance().constructMapType(EnumMap.class, ClassUtil.findEnumType((EnumMap) obj), Object.class).toCanonical();
        } else {
            String substring = name.substring(9);
            return ((substring.startsWith(".Arrays$") || substring.startsWith(".Collections$")) && name.indexOf("List") >= 0) ? "java.util.ArrayList" : name;
        }
    }

    public Id getMechanism() {
        return Id.CLASS;
    }

    public String idFromValue(Object obj) {
        return _idFrom(obj, obj.getClass());
    }

    public String idFromValueAndType(Object obj, Class<?> cls) {
        return _idFrom(obj, cls);
    }

    public void registerSubtype(Class<?> cls, String str) {
    }

    public JavaType typeFromId(String str) {
        if (str.indexOf(60) > 0) {
            return TypeFactory.fromCanonical(str);
        }
        try {
            return this._typeFactory.constructSpecializedType(this._baseType, Class.forName(str, true, Thread.currentThread().getContextClassLoader()));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Invalid type id '" + str + "' (for id type 'Id.class'): no such class found");
        } catch (Throwable e2) {
            throw new IllegalArgumentException("Invalid type id '" + str + "' (for id type 'Id.class'): " + e2.getMessage(), e2);
        }
    }
}
