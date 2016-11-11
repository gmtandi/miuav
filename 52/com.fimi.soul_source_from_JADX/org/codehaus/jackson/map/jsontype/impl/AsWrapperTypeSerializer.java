package org.codehaus.jackson.map.jsontype.impl;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;

public class AsWrapperTypeSerializer extends TypeSerializerBase {
    public AsWrapperTypeSerializer(TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        super(typeIdResolver, beanProperty);
    }

    public As getTypeInclusion() {
        return As.WRAPPER_OBJECT;
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart(this._idResolver.idFromValue(obj));
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator, Class<?> cls) {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart(this._idResolver.idFromValueAndType(obj, cls));
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectFieldStart(this._idResolver.idFromValue(obj));
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, Class<?> cls) {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectFieldStart(this._idResolver.idFromValueAndType(obj, cls));
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(this._idResolver.idFromValue(obj));
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, Class<?> cls) {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(this._idResolver.idFromValueAndType(obj, cls));
    }

    public void writeTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();
    }

    public void writeTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeEndObject();
    }
}
