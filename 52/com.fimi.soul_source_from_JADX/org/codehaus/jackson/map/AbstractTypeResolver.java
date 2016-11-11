package org.codehaus.jackson.map;

import org.codehaus.jackson.type.JavaType;

public abstract class AbstractTypeResolver {
    public JavaType findTypeMapping(DeserializationConfig deserializationConfig, JavaType javaType) {
        return null;
    }

    public JavaType resolveAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) {
        return null;
    }
}
