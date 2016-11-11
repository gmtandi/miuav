package org.codehaus.jackson.map.deser;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonParser.NumberType;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

abstract class BaseNodeDeserializer<N extends JsonNode> extends StdDeserializer<N> {

    /* renamed from: org.codehaus.jackson.map.deser.BaseNodeDeserializer.1 */
    /* synthetic */ class C35831 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonToken;

        static {
            $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_OBJECT.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_ARRAY.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    public BaseNodeDeserializer(Class<N> cls) {
        super((Class) cls);
    }

    protected void _handleDuplicateField(String str, ObjectNode objectNode, JsonNode jsonNode, JsonNode jsonNode2) {
    }

    protected void _reportProblem(JsonParser jsonParser, String str) {
        throw new JsonMappingException(str, jsonParser.getTokenLocation());
    }

    protected final JsonNode deserializeAny(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
        switch (C35831.$SwitchMap$org$codehaus$jackson$JsonToken[jsonParser.getCurrentToken().ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return deserializeObject(jsonParser, deserializationContext);
            case Type.BYTE /*3*/:
                return deserializeArray(jsonParser, deserializationContext);
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return nodeFactory.textNode(jsonParser.getText());
            case Type.INT /*5*/:
                NumberType numberType = jsonParser.getNumberType();
                return (numberType == NumberType.BIG_INTEGER || deserializationContext.isEnabled(Feature.USE_BIG_INTEGER_FOR_INTS)) ? nodeFactory.numberNode(jsonParser.getBigIntegerValue()) : numberType == NumberType.INT ? nodeFactory.numberNode(jsonParser.getIntValue()) : nodeFactory.numberNode(jsonParser.getLongValue());
            case Type.FLOAT /*6*/:
                return (jsonParser.getNumberType() == NumberType.BIG_DECIMAL || deserializationContext.isEnabled(Feature.USE_BIG_DECIMAL_FOR_FLOATS)) ? nodeFactory.numberNode(jsonParser.getDecimalValue()) : nodeFactory.numberNode(jsonParser.getDoubleValue());
            case Type.LONG /*7*/:
                return nodeFactory.booleanNode(true);
            case Type.DOUBLE /*8*/:
                return nodeFactory.booleanNode(false);
            case Type.ARRAY /*9*/:
                return nodeFactory.nullNode();
            default:
                throw deserializationContext.mappingException(getValueClass());
        }
    }

    protected final ArrayNode deserializeArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
        ArrayNode arrayNode = deserializationContext.getNodeFactory().arrayNode();
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            arrayNode.add(deserializeAny(jsonParser, deserializationContext));
        }
        return arrayNode;
    }

    protected final ObjectNode deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext) {
        ObjectNode objectNode = deserializationContext.getNodeFactory().objectNode();
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            JsonNode deserializeAny = deserializeAny(jsonParser, deserializationContext);
            JsonNode put = objectNode.put(currentName, deserializeAny);
            if (put != null) {
                _handleDuplicateField(currentName, objectNode, put, deserializeAny);
            }
            currentToken = jsonParser.nextToken();
        }
        return objectNode;
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }
}
