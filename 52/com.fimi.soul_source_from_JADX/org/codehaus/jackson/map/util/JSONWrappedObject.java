package org.codehaus.jackson.map.util;

import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializableWithType;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

public class JSONWrappedObject implements JsonSerializableWithType {
    protected final String _prefix;
    protected final JavaType _serializationType;
    protected final String _suffix;
    protected final Object _value;

    public JSONWrappedObject(String str, String str2, Object obj) {
        this(str, str2, obj, (JavaType) null);
    }

    @Deprecated
    public JSONWrappedObject(String str, String str2, Object obj, Class<?> cls) {
        this._prefix = str;
        this._suffix = str2;
        this._value = obj;
        this._serializationType = cls == null ? null : TypeFactory.defaultInstance().constructType((Type) cls);
    }

    public JSONWrappedObject(String str, String str2, Object obj, JavaType javaType) {
        this._prefix = str;
        this._suffix = str2;
        this._value = obj;
        this._serializationType = javaType;
    }

    public String getPrefix() {
        return this._prefix;
    }

    public JavaType getSerializationType() {
        return this._serializationType;
    }

    public String getSuffix() {
        return this._suffix;
    }

    public Object getValue() {
        return this._value;
    }

    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._prefix != null) {
            jsonGenerator.writeRaw(this._prefix);
        }
        if (this._value == null) {
            serializerProvider.defaultSerializeNull(jsonGenerator);
        } else if (this._serializationType != null) {
            serializerProvider.findTypedValueSerializer(this._serializationType, true, null).serialize(this._value, jsonGenerator, serializerProvider);
        } else {
            serializerProvider.findTypedValueSerializer(this._value.getClass(), true, null).serialize(this._value, jsonGenerator, serializerProvider);
        }
        if (this._suffix != null) {
            jsonGenerator.writeRaw(this._suffix);
        }
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        serialize(jsonGenerator, serializerProvider);
    }
}
