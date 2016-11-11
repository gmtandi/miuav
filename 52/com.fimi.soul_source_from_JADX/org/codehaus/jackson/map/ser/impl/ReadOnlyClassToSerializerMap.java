package org.codehaus.jackson.map.ser.impl;

import java.util.HashMap;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ser.SerializerCache.TypeKey;
import org.codehaus.jackson.type.JavaType;

public final class ReadOnlyClassToSerializerMap {
    protected final TypeKey _cacheKey;
    protected final JsonSerializerMap _map;

    private ReadOnlyClassToSerializerMap(JsonSerializerMap jsonSerializerMap) {
        this._cacheKey = new TypeKey(getClass(), false);
        this._map = jsonSerializerMap;
    }

    public static ReadOnlyClassToSerializerMap from(HashMap<TypeKey, JsonSerializer<Object>> hashMap) {
        return new ReadOnlyClassToSerializerMap(new JsonSerializerMap(hashMap));
    }

    public ReadOnlyClassToSerializerMap instance() {
        return new ReadOnlyClassToSerializerMap(this._map);
    }

    public JsonSerializer<Object> typedValueSerializer(Class<?> cls) {
        this._cacheKey.resetTyped((Class) cls);
        return this._map.find(this._cacheKey);
    }

    public JsonSerializer<Object> typedValueSerializer(JavaType javaType) {
        this._cacheKey.resetTyped(javaType);
        return this._map.find(this._cacheKey);
    }

    public JsonSerializer<Object> untypedValueSerializer(Class<?> cls) {
        this._cacheKey.resetUntyped((Class) cls);
        return this._map.find(this._cacheKey);
    }

    public JsonSerializer<Object> untypedValueSerializer(JavaType javaType) {
        this._cacheKey.resetUntyped(javaType);
        return this._map.find(this._cacheKey);
    }
}
