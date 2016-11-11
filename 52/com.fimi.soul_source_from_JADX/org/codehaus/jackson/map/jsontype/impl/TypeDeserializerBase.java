package org.codehaus.jackson.map.jsontype.impl;

import java.util.HashMap;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.type.JavaType;

public abstract class TypeDeserializerBase extends TypeDeserializer {
    protected final JavaType _baseType;
    protected final HashMap<String, JsonDeserializer<Object>> _deserializers;
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;

    protected TypeDeserializerBase(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        this._baseType = javaType;
        this._idResolver = typeIdResolver;
        this._property = beanProperty;
        this._deserializers = new HashMap();
    }

    protected final JsonDeserializer<Object> _findDeserializer(DeserializationContext deserializationContext, String str) {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this._deserializers) {
            jsonDeserializer = (JsonDeserializer) this._deserializers.get(str);
            if (jsonDeserializer == null) {
                JavaType typeFromId = this._idResolver.typeFromId(str);
                if (typeFromId == null) {
                    throw deserializationContext.unknownTypeException(this._baseType, str);
                }
                if (this._baseType != null && this._baseType.getClass() == typeFromId.getClass()) {
                    typeFromId = this._baseType.narrowBy(typeFromId.getRawClass());
                }
                jsonDeserializer = deserializationContext.getDeserializerProvider().findValueDeserializer(deserializationContext.getConfig(), typeFromId, this._property);
                this._deserializers.put(str, jsonDeserializer);
            }
        }
        return jsonDeserializer;
    }

    public String baseTypeName() {
        return this._baseType.getRawClass().getName();
    }

    public String getPropertyName() {
        return null;
    }

    public TypeIdResolver getTypeIdResolver() {
        return this._idResolver;
    }

    public abstract As getTypeInclusion();

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[').append(getClass().getName());
        stringBuilder.append("; base-type:").append(this._baseType);
        stringBuilder.append("; id-resolver: ").append(this._idResolver);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
