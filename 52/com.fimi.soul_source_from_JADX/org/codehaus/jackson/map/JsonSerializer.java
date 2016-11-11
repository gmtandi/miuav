package org.codehaus.jackson.map;

import org.codehaus.jackson.JsonGenerator;

public abstract class JsonSerializer<T> {

    public abstract class None extends JsonSerializer<Object> {
    }

    public Class<T> handledType() {
        return null;
    }

    public abstract void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

    public void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        serialize(t, jsonGenerator, serializerProvider);
    }
}
