package org.codehaus.jackson.map.ser.impl;

import java.lang.reflect.Type;
import java.util.Collection;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.SerializerBase;

public abstract class StaticListSerializerBase<T extends Collection<?>> extends SerializerBase<T> {
    protected final BeanProperty _property;

    protected StaticListSerializerBase(Class<?> cls, BeanProperty beanProperty) {
        super(cls, false);
        this._property = beanProperty;
    }

    protected abstract JsonNode contentSchema();

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        JsonNode createSchemaNode = createSchemaNode("array", true);
        createSchemaNode.put("items", contentSchema());
        return createSchemaNode;
    }
}
