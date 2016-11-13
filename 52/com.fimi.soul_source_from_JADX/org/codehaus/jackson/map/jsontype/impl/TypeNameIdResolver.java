package org.codehaus.jackson.map.jsontype.impl;

import java.util.Collection;
import java.util.HashMap;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.type.JavaType;

public class TypeNameIdResolver extends TypeIdResolverBase {
    protected final MapperConfig<?> _config;
    protected final HashMap<String, JavaType> _idToType;
    protected final HashMap<String, String> _typeToId;

    protected TypeNameIdResolver(MapperConfig<?> mapperConfig, JavaType javaType, HashMap<String, String> hashMap, HashMap<String, JavaType> hashMap2) {
        super(javaType, mapperConfig.getTypeFactory());
        this._config = mapperConfig;
        this._typeToId = hashMap;
        this._idToType = hashMap2;
    }

    protected static String _defaultTypeId(Class<?> cls) {
        String name = cls.getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf < 0 ? name : name.substring(lastIndexOf + 1);
    }

    public static TypeNameIdResolver construct(MapperConfig<?> mapperConfig, JavaType javaType, Collection<NamedType> collection, boolean z, boolean z2) {
        if (z == z2) {
            throw new IllegalArgumentException();
        }
        HashMap hashMap = z ? new HashMap() : null;
        HashMap hashMap2 = z2 ? new HashMap() : null;
        if (collection != null) {
            for (NamedType namedType : collection) {
                Object name;
                Class type = namedType.getType();
                if (namedType.hasName()) {
                    name = namedType.getName();
                } else {
                    String _defaultTypeId = _defaultTypeId(type);
                }
                if (z) {
                    hashMap.put(type.getName(), name);
                }
                if (z2) {
                    JavaType javaType2 = (JavaType) hashMap2.get(name);
                    if (javaType2 == null || !type.isAssignableFrom(javaType2.getRawClass())) {
                        hashMap2.put(name, mapperConfig.constructType(type));
                    }
                }
            }
        }
        return new TypeNameIdResolver(mapperConfig, javaType, hashMap, hashMap2);
    }

    public Id getMechanism() {
        return Id.NAME;
    }

    public String idFromValue(Object obj) {
        String str;
        Class cls = obj.getClass();
        String name = cls.getName();
        synchronized (this._typeToId) {
            str = (String) this._typeToId.get(name);
            if (str == null) {
                if (this._config.isAnnotationProcessingEnabled()) {
                    str = this._config.getAnnotationIntrospector().findTypeName(((BasicBeanDescription) this._config.introspectClassAnnotations(cls)).getClassInfo());
                }
                if (str == null) {
                    str = _defaultTypeId(cls);
                }
                this._typeToId.put(name, str);
            }
        }
        return str;
    }

    public String idFromValueAndType(Object obj, Class<?> cls) {
        return idFromValue(obj);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[').append(getClass().getName());
        stringBuilder.append("; id-to-type=").append(this._idToType);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public JavaType typeFromId(String str) {
        return (JavaType) this._idToType.get(str);
    }
}
