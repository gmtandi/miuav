package org.codehaus.jackson.map.deser;

import java.util.EnumMap;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;

public final class EnumMapDeserializer extends StdDeserializer<EnumMap<?, ?>> {
    final EnumResolver<?> _enumResolver;
    final JsonDeserializer<Object> _valueDeserializer;

    public EnumMapDeserializer(EnumResolver<?> enumResolver, JsonDeserializer<Object> jsonDeserializer) {
        super(EnumMap.class);
        this._enumResolver = enumResolver;
        this._valueDeserializer = jsonDeserializer;
    }

    private EnumMap<?, ?> constructMap() {
        return new EnumMap(this._enumResolver.getEnumClass());
    }

    public EnumMap<?, ?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            throw deserializationContext.mappingException(EnumMap.class);
        }
        EnumMap<?, ?> constructMap = constructMap();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            Enum findEnum = this._enumResolver.findEnum(jsonParser.getCurrentName());
            if (findEnum == null) {
                throw deserializationContext.weirdStringException(this._enumResolver.getEnumClass(), "value not one of declared Enum instance names");
            }
            constructMap.put(findEnum, jsonParser.nextToken() == JsonToken.VALUE_NULL ? null : this._valueDeserializer.deserialize(jsonParser, deserializationContext));
        }
        return constructMap;
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }
}
