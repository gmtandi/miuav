package org.codehaus.jackson.map.deser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.util.ObjectBuffer;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

@JacksonStdImpl
public class UntypedObjectDeserializer extends StdDeserializer<Object> {

    /* renamed from: org.codehaus.jackson.map.deser.UntypedObjectDeserializer.1 */
    /* synthetic */ class C35861 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonToken;

        static {
            $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_ARRAY.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_ARRAY.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_OBJECT.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    public UntypedObjectDeserializer() {
        super(Object.class);
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        switch (C35861.$SwitchMap$org$codehaus$jackson$JsonToken[jsonParser.getCurrentToken().ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return jsonParser.getText();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return deserializationContext.isEnabled(Feature.USE_BIG_INTEGER_FOR_INTS) ? jsonParser.getBigIntegerValue() : jsonParser.getNumberValue();
            case Type.BYTE /*3*/:
                return deserializationContext.isEnabled(Feature.USE_BIG_DECIMAL_FOR_FLOATS) ? jsonParser.getDecimalValue() : Double.valueOf(jsonParser.getDoubleValue());
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return Boolean.TRUE;
            case Type.INT /*5*/:
                return Boolean.FALSE;
            case Type.FLOAT /*6*/:
                return jsonParser.getEmbeddedObject();
            case Type.LONG /*7*/:
                return null;
            case Type.DOUBLE /*8*/:
                return mapArray(jsonParser, deserializationContext);
            case Type.ARRAY /*9*/:
            case Type.OBJECT /*10*/:
                return mapObject(jsonParser, deserializationContext);
            default:
                throw deserializationContext.mappingException(Object.class);
        }
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        switch (C35861.$SwitchMap$org$codehaus$jackson$JsonToken[jsonParser.getCurrentToken().ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return jsonParser.getText();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return deserializationContext.isEnabled(Feature.USE_BIG_INTEGER_FOR_INTS) ? jsonParser.getBigIntegerValue() : Integer.valueOf(jsonParser.getIntValue());
            case Type.BYTE /*3*/:
                return deserializationContext.isEnabled(Feature.USE_BIG_DECIMAL_FOR_FLOATS) ? jsonParser.getDecimalValue() : Double.valueOf(jsonParser.getDoubleValue());
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return Boolean.TRUE;
            case Type.INT /*5*/:
                return Boolean.FALSE;
            case Type.FLOAT /*6*/:
                return jsonParser.getEmbeddedObject();
            case Type.LONG /*7*/:
                return null;
            case Type.DOUBLE /*8*/:
            case Type.ARRAY /*9*/:
            case Type.OBJECT /*10*/:
                return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
            default:
                throw deserializationContext.mappingException(Object.class);
        }
    }

    protected List<Object> mapArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            return new ArrayList(4);
        }
        ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        int i = 0;
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        int i2 = 0;
        do {
            int i3;
            Object deserialize = deserialize(jsonParser, deserializationContext);
            i2++;
            if (i >= resetAndStart.length) {
                resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                i3 = 0;
            } else {
                i3 = i;
            }
            i = i3 + 1;
            resetAndStart[i3] = deserialize;
        } while (jsonParser.nextToken() != JsonToken.END_ARRAY);
        List<Object> arrayList = new ArrayList((i2 + (i2 >> 3)) + 1);
        leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i, (List) arrayList);
        return arrayList;
    }

    protected Map<String, Object> mapObject(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        if (currentToken != JsonToken.FIELD_NAME) {
            return new LinkedHashMap(4);
        }
        String text = jsonParser.getText();
        jsonParser.nextToken();
        Object deserialize = deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() != JsonToken.FIELD_NAME) {
            Map linkedHashMap = new LinkedHashMap(4);
            linkedHashMap.put(text, deserialize);
            return linkedHashMap;
        }
        String text2 = jsonParser.getText();
        jsonParser.nextToken();
        Object deserialize2 = deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() != JsonToken.FIELD_NAME) {
            linkedHashMap = new LinkedHashMap(4);
            linkedHashMap.put(text, deserialize);
            linkedHashMap.put(text2, deserialize2);
            return linkedHashMap;
        }
        linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(text, deserialize);
        linkedHashMap.put(text2, deserialize2);
        do {
            text = jsonParser.getText();
            jsonParser.nextToken();
            linkedHashMap.put(text, deserialize(jsonParser, deserializationContext));
        } while (jsonParser.nextToken() != JsonToken.END_OBJECT);
        return linkedHashMap;
    }
}
