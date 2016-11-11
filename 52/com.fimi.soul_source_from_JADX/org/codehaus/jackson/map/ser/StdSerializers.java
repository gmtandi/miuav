package org.codehaus.jackson.map.ser;

import com.tencent.open.SocialConstants;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonSerializable;
import org.codehaus.jackson.map.JsonSerializableWithType;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.schema.JsonSerializableSchema;
import org.codehaus.jackson.util.TokenBuffer;

public class StdSerializers {

    public abstract class NonTypedScalarSerializer<T> extends ScalarSerializerBase<T> {
        protected NonTypedScalarSerializer(Class<T> cls) {
            super(cls);
        }

        public final void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            serialize(t, jsonGenerator, serializerProvider);
        }
    }

    @JacksonStdImpl
    public final class BooleanSerializer extends NonTypedScalarSerializer<Boolean> {
        final boolean _forPrimitive;

        public BooleanSerializer(boolean z) {
            super(Boolean.class);
            this._forPrimitive = z;
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("boolean", !this._forPrimitive);
        }

        public void serialize(Boolean bool, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeBoolean(bool.booleanValue());
        }
    }

    @JacksonStdImpl
    public final class CalendarSerializer extends ScalarSerializerBase<Calendar> {
        public static final CalendarSerializer instance;

        static {
            instance = new CalendarSerializer();
        }

        public CalendarSerializer() {
            super(Calendar.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode(serializerProvider.isEnabled(Feature.WRITE_DATES_AS_TIMESTAMPS) ? "number" : "string", true);
        }

        public void serialize(Calendar calendar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            serializerProvider.defaultSerializeDateValue(calendar.getTimeInMillis(), jsonGenerator);
        }
    }

    @JacksonStdImpl
    public final class DoubleSerializer extends NonTypedScalarSerializer<Double> {
        static final DoubleSerializer instance;

        static {
            instance = new DoubleSerializer();
        }

        public DoubleSerializer() {
            super(Double.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("number", true);
        }

        public void serialize(Double d, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(d.doubleValue());
        }
    }

    @JacksonStdImpl
    public final class FloatSerializer extends ScalarSerializerBase<Float> {
        static final FloatSerializer instance;

        static {
            instance = new FloatSerializer();
        }

        public FloatSerializer() {
            super(Float.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("number", true);
        }

        public void serialize(Float f, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(f.floatValue());
        }
    }

    @JacksonStdImpl
    public final class IntLikeSerializer extends ScalarSerializerBase<Number> {
        static final IntLikeSerializer instance;

        static {
            instance = new IntLikeSerializer();
        }

        public IntLikeSerializer() {
            super(Number.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("integer", true);
        }

        public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(number.intValue());
        }
    }

    @JacksonStdImpl
    public final class IntegerSerializer extends NonTypedScalarSerializer<Integer> {
        public IntegerSerializer() {
            super(Integer.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("integer", true);
        }

        public void serialize(Integer num, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(num.intValue());
        }
    }

    @JacksonStdImpl
    public final class LongSerializer extends ScalarSerializerBase<Long> {
        static final LongSerializer instance;

        static {
            instance = new LongSerializer();
        }

        public LongSerializer() {
            super(Long.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("number", true);
        }

        public void serialize(Long l, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(l.longValue());
        }
    }

    @JacksonStdImpl
    public final class NumberSerializer extends ScalarSerializerBase<Number> {
        public static final NumberSerializer instance;

        static {
            instance = new NumberSerializer();
        }

        public NumberSerializer() {
            super(Number.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("number", true);
        }

        public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (number instanceof BigDecimal) {
                jsonGenerator.writeNumber((BigDecimal) number);
            } else if (number instanceof BigInteger) {
                jsonGenerator.writeNumber((BigInteger) number);
            } else if (number instanceof Integer) {
                jsonGenerator.writeNumber(number.intValue());
            } else if (number instanceof Long) {
                jsonGenerator.writeNumber(number.longValue());
            } else if (number instanceof Double) {
                jsonGenerator.writeNumber(number.doubleValue());
            } else if (number instanceof Float) {
                jsonGenerator.writeNumber(number.floatValue());
            } else if ((number instanceof Byte) || (number instanceof Short)) {
                jsonGenerator.writeNumber(number.intValue());
            } else {
                jsonGenerator.writeNumber(number.toString());
            }
        }
    }

    @JacksonStdImpl
    public final class SerializableSerializer extends SerializerBase<JsonSerializable> {
        protected static final SerializableSerializer instance;

        static {
            instance = new SerializableSerializer();
        }

        private SerializableSerializer() {
            super(JsonSerializable.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            String schemaObjectPropertiesDefinition;
            String str = null;
            JsonNode createObjectNode = createObjectNode();
            String str2 = "any";
            if (type != null) {
                Class rawClass = TypeFactory.type(type).getRawClass();
                if (rawClass.isAnnotationPresent(JsonSerializableSchema.class)) {
                    JsonSerializableSchema jsonSerializableSchema = (JsonSerializableSchema) rawClass.getAnnotation(JsonSerializableSchema.class);
                    String schemaType = jsonSerializableSchema.schemaType();
                    schemaObjectPropertiesDefinition = !"##irrelevant".equals(jsonSerializableSchema.schemaObjectPropertiesDefinition()) ? jsonSerializableSchema.schemaObjectPropertiesDefinition() : null;
                    if ("##irrelevant".equals(jsonSerializableSchema.schemaItemDefinition())) {
                        str2 = schemaType;
                    } else {
                        str = jsonSerializableSchema.schemaItemDefinition();
                        str2 = schemaType;
                    }
                    createObjectNode.put(SocialConstants.PARAM_TYPE, str2);
                    if (schemaObjectPropertiesDefinition != null) {
                        try {
                            createObjectNode.put("properties", (JsonNode) new ObjectMapper().readValue(schemaObjectPropertiesDefinition, JsonNode.class));
                        } catch (Throwable e) {
                            throw new IllegalStateException(e);
                        }
                    }
                    if (str != null) {
                        try {
                            createObjectNode.put("items", (JsonNode) new ObjectMapper().readValue(str, JsonNode.class));
                        } catch (Throwable e2) {
                            throw new IllegalStateException(e2);
                        }
                    }
                    return createObjectNode;
                }
            }
            schemaObjectPropertiesDefinition = null;
            createObjectNode.put(SocialConstants.PARAM_TYPE, str2);
            if (schemaObjectPropertiesDefinition != null) {
                createObjectNode.put("properties", (JsonNode) new ObjectMapper().readValue(schemaObjectPropertiesDefinition, JsonNode.class));
            }
            if (str != null) {
                createObjectNode.put("items", (JsonNode) new ObjectMapper().readValue(str, JsonNode.class));
            }
            return createObjectNode;
        }

        public void serialize(JsonSerializable jsonSerializable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonSerializable.serialize(jsonGenerator, serializerProvider);
        }

        public final void serializeWithType(JsonSerializable jsonSerializable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            if (jsonSerializable instanceof JsonSerializableWithType) {
                ((JsonSerializableWithType) jsonSerializable).serializeWithType(jsonGenerator, serializerProvider, typeSerializer);
            } else {
                serialize(jsonSerializable, jsonGenerator, serializerProvider);
            }
        }
    }

    @JacksonStdImpl
    public final class SerializableWithTypeSerializer extends SerializerBase<JsonSerializableWithType> {
        protected static final SerializableWithTypeSerializer instance;

        static {
            instance = new SerializableWithTypeSerializer();
        }

        private SerializableWithTypeSerializer() {
            super(JsonSerializableWithType.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            String schemaObjectPropertiesDefinition;
            String str = null;
            JsonNode createObjectNode = createObjectNode();
            String str2 = "any";
            if (type != null) {
                Class rawClass = TypeFactory.rawClass(type);
                if (rawClass.isAnnotationPresent(JsonSerializableSchema.class)) {
                    JsonSerializableSchema jsonSerializableSchema = (JsonSerializableSchema) rawClass.getAnnotation(JsonSerializableSchema.class);
                    String schemaType = jsonSerializableSchema.schemaType();
                    schemaObjectPropertiesDefinition = !"##irrelevant".equals(jsonSerializableSchema.schemaObjectPropertiesDefinition()) ? jsonSerializableSchema.schemaObjectPropertiesDefinition() : null;
                    if ("##irrelevant".equals(jsonSerializableSchema.schemaItemDefinition())) {
                        str2 = schemaType;
                    } else {
                        str = jsonSerializableSchema.schemaItemDefinition();
                        str2 = schemaType;
                    }
                    createObjectNode.put(SocialConstants.PARAM_TYPE, str2);
                    if (schemaObjectPropertiesDefinition != null) {
                        try {
                            createObjectNode.put("properties", (JsonNode) new ObjectMapper().readValue(schemaObjectPropertiesDefinition, JsonNode.class));
                        } catch (Throwable e) {
                            throw new IllegalStateException(e);
                        }
                    }
                    if (str != null) {
                        try {
                            createObjectNode.put("items", (JsonNode) new ObjectMapper().readValue(str, JsonNode.class));
                        } catch (Throwable e2) {
                            throw new IllegalStateException(e2);
                        }
                    }
                    return createObjectNode;
                }
            }
            schemaObjectPropertiesDefinition = null;
            createObjectNode.put(SocialConstants.PARAM_TYPE, str2);
            if (schemaObjectPropertiesDefinition != null) {
                createObjectNode.put("properties", (JsonNode) new ObjectMapper().readValue(schemaObjectPropertiesDefinition, JsonNode.class));
            }
            if (str != null) {
                createObjectNode.put("items", (JsonNode) new ObjectMapper().readValue(str, JsonNode.class));
            }
            return createObjectNode;
        }

        public void serialize(JsonSerializableWithType jsonSerializableWithType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonSerializableWithType.serialize(jsonGenerator, serializerProvider);
        }

        public final void serializeWithType(JsonSerializableWithType jsonSerializableWithType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            jsonSerializableWithType.serializeWithType(jsonGenerator, serializerProvider, typeSerializer);
        }
    }

    @JacksonStdImpl
    public final class SqlDateSerializer extends ScalarSerializerBase<Date> {
        public SqlDateSerializer() {
            super(Date.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("string", true);
        }

        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeString(date.toString());
        }
    }

    @JacksonStdImpl
    public final class SqlTimeSerializer extends ScalarSerializerBase<Time> {
        public SqlTimeSerializer() {
            super(Time.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("string", true);
        }

        public void serialize(Time time, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeString(time.toString());
        }
    }

    @JacksonStdImpl
    public final class StringSerializer extends NonTypedScalarSerializer<String> {
        public StringSerializer() {
            super(String.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("string", true);
        }

        public void serialize(String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeString(str);
        }
    }

    @JacksonStdImpl
    public final class TokenBufferSerializer extends SerializerBase<TokenBuffer> {
        public TokenBufferSerializer() {
            super(TokenBuffer.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("any", true);
        }

        public void serialize(TokenBuffer tokenBuffer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            tokenBuffer.serialize(jsonGenerator);
        }

        public final void serializeWithType(TokenBuffer tokenBuffer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            typeSerializer.writeTypePrefixForScalar(tokenBuffer, jsonGenerator);
            serialize(tokenBuffer, jsonGenerator, serializerProvider);
            typeSerializer.writeTypeSuffixForScalar(tokenBuffer, jsonGenerator);
        }
    }

    @JacksonStdImpl
    public final class UtilDateSerializer extends ScalarSerializerBase<java.util.Date> {
        public static final UtilDateSerializer instance;

        static {
            instance = new UtilDateSerializer();
        }

        public UtilDateSerializer() {
            super(java.util.Date.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode(serializerProvider.isEnabled(Feature.WRITE_DATES_AS_TIMESTAMPS) ? "number" : "string", true);
        }

        public void serialize(java.util.Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            serializerProvider.defaultSerializeDateValue(date, jsonGenerator);
        }
    }

    protected StdSerializers() {
    }
}
