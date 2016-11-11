package org.codehaus.jackson.map;

import org.codehaus.jackson.JsonGenerator;

@Deprecated
public interface JsonSerializable {
    void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider);
}
