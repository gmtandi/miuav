package org.codehaus.jackson.map.deser;

import java.util.EnumSet;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.TypeDeserializer;

public final class EnumSetDeserializer extends StdDeserializer<EnumSet<?>> {
    final Class<Enum> _enumClass;
    final EnumDeserializer _enumDeserializer;

    public EnumSetDeserializer(EnumResolver enumResolver) {
        super(EnumSet.class);
        this._enumDeserializer = new EnumDeserializer(enumResolver);
        this._enumClass = enumResolver.getEnumClass();
    }

    private EnumSet constructSet() {
        return EnumSet.noneOf(this._enumClass);
    }

    public EnumSet<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.isExpectedStartArrayToken()) {
            EnumSet<?> constructSet = constructSet();
            while (true) {
                JsonToken nextToken = jsonParser.nextToken();
                if (nextToken == JsonToken.END_ARRAY) {
                    return constructSet;
                }
                if (nextToken == JsonToken.VALUE_NULL) {
                    break;
                }
                constructSet.add(this._enumDeserializer.deserialize(jsonParser, deserializationContext));
            }
            throw deserializationContext.mappingException(this._enumClass);
        }
        throw deserializationContext.mappingException(EnumSet.class);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }
}
