package org.codehaus.jackson.map;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;

public abstract class TypeDeserializer {
    public abstract Object deserializeTypedFromAny(JsonParser jsonParser, DeserializationContext deserializationContext);

    public abstract Object deserializeTypedFromArray(JsonParser jsonParser, DeserializationContext deserializationContext);

    public abstract Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext);

    public abstract Object deserializeTypedFromScalar(JsonParser jsonParser, DeserializationContext deserializationContext);

    public abstract String getPropertyName();

    public abstract TypeIdResolver getTypeIdResolver();

    public abstract As getTypeInclusion();
}
