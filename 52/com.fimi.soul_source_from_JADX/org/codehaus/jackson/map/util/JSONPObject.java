package org.codehaus.jackson.map.util;

import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializableWithType;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

public class JSONPObject implements JsonSerializableWithType {
    protected final String _function;
    protected final JavaType _serializationType;
    protected final Object _value;

    public JSONPObject(String str, Object obj) {
        this(str, obj, (JavaType) null);
    }

    @Deprecated
    public JSONPObject(String str, Object obj, Class<?> cls) {
        this._function = str;
        this._value = obj;
        this._serializationType = cls == null ? null : TypeFactory.defaultInstance().constructType((Type) cls);
    }

    public JSONPObject(String str, Object obj, JavaType javaType) {
        this._function = str;
        this._value = obj;
        this._serializationType = javaType;
    }

    public String getFunction() {
        return this._function;
    }

    public JavaType getSerializationType() {
        return this._serializationType;
    }

    public Object getValue() {
        return this._value;
    }

    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeRaw(this._function);
        jsonGenerator.writeRaw('(');
        if (this._value == null) {
            serializerProvider.defaultSerializeNull(jsonGenerator);
        } else if (this._serializationType != null) {
            serializerProvider.findTypedValueSerializer(this._serializationType, true, null).serialize(this._value, jsonGenerator, serializerProvider);
        } else {
            serializerProvider.findTypedValueSerializer(this._value.getClass(), true, null).serialize(this._value, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeRaw(')');
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        serialize(jsonGenerator, serializerProvider);
    }
}
