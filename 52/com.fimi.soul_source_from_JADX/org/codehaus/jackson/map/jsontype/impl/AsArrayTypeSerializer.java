package org.codehaus.jackson.map.jsontype.impl;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;

public class AsArrayTypeSerializer extends TypeSerializerBase {
    public AsArrayTypeSerializer(TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        super(typeIdResolver, beanProperty);
    }

    public As getTypeInclusion() {
        return As.WRAPPER_ARRAY;
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeStartArray();
        jsonGenerator.writeString(this._idResolver.idFromValue(obj));
        jsonGenerator.writeStartArray();
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator, Class<?> cls) {
        jsonGenerator.writeStartArray();
        jsonGenerator.writeString(this._idResolver.idFromValueAndType(obj, cls));
        jsonGenerator.writeStartArray();
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeStartArray();
        jsonGenerator.writeString(this._idResolver.idFromValue(obj));
        jsonGenerator.writeStartObject();
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, Class<?> cls) {
        jsonGenerator.writeStartArray();
        jsonGenerator.writeString(this._idResolver.idFromValueAndType(obj, cls));
        jsonGenerator.writeStartObject();
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeStartArray();
        jsonGenerator.writeString(this._idResolver.idFromValue(obj));
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, Class<?> cls) {
        jsonGenerator.writeStartArray();
        jsonGenerator.writeString(this._idResolver.idFromValueAndType(obj, cls));
    }

    public void writeTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndArray();
    }

    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndArray();
    }

    public void writeTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeEndArray();
    }
}
