package org.codehaus.jackson.map;

import org.codehaus.jackson.JsonParser;

public abstract class JsonDeserializer<T> {

    public abstract class None extends JsonDeserializer<Object> {
    }

    public abstract T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext);

    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, T t) {
        throw new UnsupportedOperationException();
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    public T getNullValue() {
        return null;
    }
}
