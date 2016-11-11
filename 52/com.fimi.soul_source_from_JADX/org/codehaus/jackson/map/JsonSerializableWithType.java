package org.codehaus.jackson.map;

import org.codehaus.jackson.JsonGenerator;

public interface JsonSerializableWithType extends JsonSerializable {
    void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer);
}
