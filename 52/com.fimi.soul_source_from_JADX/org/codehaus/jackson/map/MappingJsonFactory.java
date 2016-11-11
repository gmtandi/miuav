package org.codehaus.jackson.map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.format.InputAccessor;
import org.codehaus.jackson.format.MatchStrength;

public class MappingJsonFactory extends JsonFactory {
    public MappingJsonFactory() {
        this(null);
    }

    public MappingJsonFactory(ObjectMapper objectMapper) {
        super(objectMapper);
        if (objectMapper == null) {
            setCodec(new ObjectMapper((JsonFactory) this));
        }
    }

    public final ObjectMapper getCodec() {
        return (ObjectMapper) this._objectCodec;
    }

    public String getFormatName() {
        return JsonFactory.FORMAT_NAME_JSON;
    }

    public MatchStrength hasFormat(InputAccessor inputAccessor) {
        return hasJSONFormat(inputAccessor);
    }
}
