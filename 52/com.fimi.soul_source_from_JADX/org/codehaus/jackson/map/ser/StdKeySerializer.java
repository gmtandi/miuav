package org.codehaus.jackson.map.ser;

import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.SerializerProvider;

public final class StdKeySerializer extends SerializerBase<Object> {
    static final StdKeySerializer instace;

    static {
        instace = new StdKeySerializer();
    }

    public StdKeySerializer() {
        super(Object.class);
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode("string");
    }

    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeFieldName(obj.getClass() == String.class ? (String) obj : obj.toString());
    }
}
