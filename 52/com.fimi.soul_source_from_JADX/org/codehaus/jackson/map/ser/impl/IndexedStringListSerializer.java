package org.codehaus.jackson.map.ser.impl;

import java.util.List;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;

@JacksonStdImpl
public final class IndexedStringListSerializer extends StaticListSerializerBase<List<String>> implements ResolvableSerializer {
    protected JsonSerializer<String> _serializer;

    public IndexedStringListSerializer(BeanProperty beanProperty) {
        super(List.class, beanProperty);
    }

    private final void serializeContents(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        int i = 0;
        try {
            int size = list.size();
            while (i < size) {
                String str = (String) list.get(i);
                if (str == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    jsonGenerator.writeString(str);
                }
                i++;
            }
        } catch (Throwable e) {
            wrapAndThrow(serializerProvider, e, (Object) list, 0);
        }
    }

    private final void serializeUsingCustom(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        int i = 0;
        try {
            int size = list.size();
            JsonSerializer jsonSerializer = this._serializer;
            while (i < size) {
                String str = (String) list.get(i);
                if (str == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    jsonSerializer.serialize(str, jsonGenerator, serializerProvider);
                }
                i++;
            }
        } catch (Throwable e) {
            wrapAndThrow(serializerProvider, e, (Object) list, 0);
        }
    }

    protected JsonNode contentSchema() {
        return createSchemaNode("string", true);
    }

    public void resolve(SerializerProvider serializerProvider) {
        JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(String.class, this._property);
        if (!isDefaultSerializer(findValueSerializer)) {
            this._serializer = findValueSerializer;
        }
    }

    public void serialize(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeStartArray();
        if (this._serializer == null) {
            serializeContents(list, jsonGenerator, serializerProvider);
        } else {
            serializeUsingCustom(list, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }

    public void serializeWithType(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForArray(list, jsonGenerator);
        if (this._serializer == null) {
            serializeContents(list, jsonGenerator, serializerProvider);
        } else {
            serializeUsingCustom(list, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForArray(list, jsonGenerator);
    }
}
