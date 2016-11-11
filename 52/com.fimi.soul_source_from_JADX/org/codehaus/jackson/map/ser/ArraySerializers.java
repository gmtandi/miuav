package org.codehaus.jackson.map.ser;

import com.tencent.open.SocialConstants;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;

public final class ArraySerializers {

    public abstract class AsArraySerializer<T> extends ContainerSerializerBase<T> {
        protected final BeanProperty _property;
        protected final TypeSerializer _valueTypeSerializer;

        protected AsArraySerializer(Class<T> cls, TypeSerializer typeSerializer, BeanProperty beanProperty) {
            super(cls);
            this._valueTypeSerializer = typeSerializer;
            this._property = beanProperty;
        }

        public final void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeStartArray();
            serializeContents(t, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndArray();
        }

        protected abstract void serializeContents(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

        public final void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            typeSerializer.writeTypePrefixForArray(t, jsonGenerator);
            serializeContents(t, jsonGenerator, serializerProvider);
            typeSerializer.writeTypeSuffixForArray(t, jsonGenerator);
        }
    }

    @JacksonStdImpl
    public final class BooleanArraySerializer extends AsArraySerializer<boolean[]> {
        public BooleanArraySerializer() {
            super(boolean[].class, null, null);
        }

        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            JsonNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("boolean"));
            return createSchemaNode;
        }

        public void serializeContents(boolean[] zArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            for (boolean writeBoolean : zArr) {
                jsonGenerator.writeBoolean(writeBoolean);
            }
        }
    }

    @JacksonStdImpl
    public final class ByteArraySerializer extends SerializerBase<byte[]> {
        public ByteArraySerializer() {
            super(byte[].class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            JsonNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("string"));
            return createSchemaNode;
        }

        public void serialize(byte[] bArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeBinary(bArr);
        }

        public void serializeWithType(byte[] bArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            typeSerializer.writeTypePrefixForScalar(bArr, jsonGenerator);
            jsonGenerator.writeBinary(bArr);
            typeSerializer.writeTypeSuffixForScalar(bArr, jsonGenerator);
        }
    }

    @JacksonStdImpl
    public final class CharArraySerializer extends SerializerBase<char[]> {
        public CharArraySerializer() {
            super(char[].class);
        }

        private final void _writeArrayContents(JsonGenerator jsonGenerator, char[] cArr) {
            int length = cArr.length;
            for (int i = 0; i < length; i++) {
                jsonGenerator.writeString(cArr, i, 1);
            }
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            JsonNode createSchemaNode = createSchemaNode("array", true);
            JsonNode createSchemaNode2 = createSchemaNode("string");
            createSchemaNode2.put(SocialConstants.PARAM_TYPE, "string");
            createSchemaNode.put("items", createSchemaNode2);
            return createSchemaNode;
        }

        public void serialize(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (serializerProvider.isEnabled(Feature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                jsonGenerator.writeStartArray();
                _writeArrayContents(jsonGenerator, cArr);
                jsonGenerator.writeEndArray();
                return;
            }
            jsonGenerator.writeString(cArr, 0, cArr.length);
        }

        public void serializeWithType(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            if (serializerProvider.isEnabled(Feature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                typeSerializer.writeTypePrefixForArray(cArr, jsonGenerator);
                _writeArrayContents(jsonGenerator, cArr);
                typeSerializer.writeTypeSuffixForArray(cArr, jsonGenerator);
                return;
            }
            typeSerializer.writeTypePrefixForScalar(cArr, jsonGenerator);
            jsonGenerator.writeString(cArr, 0, cArr.length);
            typeSerializer.writeTypeSuffixForScalar(cArr, jsonGenerator);
        }
    }

    @JacksonStdImpl
    public final class DoubleArraySerializer extends AsArraySerializer<double[]> {
        public DoubleArraySerializer() {
            super(double[].class, null, null);
        }

        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            JsonNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("number"));
            return createSchemaNode;
        }

        public void serializeContents(double[] dArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            for (double writeNumber : dArr) {
                jsonGenerator.writeNumber(writeNumber);
            }
        }
    }

    @JacksonStdImpl
    public final class FloatArraySerializer extends AsArraySerializer<float[]> {
        public FloatArraySerializer() {
            this(null);
        }

        public FloatArraySerializer(TypeSerializer typeSerializer) {
            super(float[].class, typeSerializer, null);
        }

        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new FloatArraySerializer(typeSerializer);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            JsonNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("number"));
            return createSchemaNode;
        }

        public void serializeContents(float[] fArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            for (float writeNumber : fArr) {
                jsonGenerator.writeNumber(writeNumber);
            }
        }
    }

    @JacksonStdImpl
    public final class IntArraySerializer extends AsArraySerializer<int[]> {
        public IntArraySerializer() {
            super(int[].class, null, null);
        }

        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            JsonNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("integer"));
            return createSchemaNode;
        }

        public void serializeContents(int[] iArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            for (int writeNumber : iArr) {
                jsonGenerator.writeNumber(writeNumber);
            }
        }
    }

    @JacksonStdImpl
    public final class LongArraySerializer extends AsArraySerializer<long[]> {
        public LongArraySerializer() {
            this(null);
        }

        public LongArraySerializer(TypeSerializer typeSerializer) {
            super(long[].class, typeSerializer, null);
        }

        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new LongArraySerializer(typeSerializer);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            JsonNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("number", true));
            return createSchemaNode;
        }

        public void serializeContents(long[] jArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            for (long writeNumber : jArr) {
                jsonGenerator.writeNumber(writeNumber);
            }
        }
    }

    @JacksonStdImpl
    public final class ShortArraySerializer extends AsArraySerializer<short[]> {
        public ShortArraySerializer() {
            this(null);
        }

        public ShortArraySerializer(TypeSerializer typeSerializer) {
            super(short[].class, typeSerializer, null);
        }

        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new ShortArraySerializer(typeSerializer);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            JsonNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("integer"));
            return createSchemaNode;
        }

        public void serializeContents(short[] sArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            for (int writeNumber : sArr) {
                jsonGenerator.writeNumber(writeNumber);
            }
        }
    }

    @JacksonStdImpl
    public final class StringArraySerializer extends AsArraySerializer<String[]> implements ResolvableSerializer {
        protected JsonSerializer<Object> _elementSerializer;

        public StringArraySerializer(BeanProperty beanProperty) {
            super(String[].class, null, beanProperty);
        }

        private void serializeContentsSlow(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) {
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                if (strArr[i] == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    jsonSerializer.serialize(strArr[i], jsonGenerator, serializerProvider);
                }
            }
        }

        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            JsonNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("string"));
            return createSchemaNode;
        }

        public void resolve(SerializerProvider serializerProvider) {
            JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(String.class, this._property);
            if (findValueSerializer != null && findValueSerializer.getClass().getAnnotation(JacksonStdImpl.class) == null) {
                this._elementSerializer = findValueSerializer;
            }
        }

        public void serializeContents(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            int length = strArr.length;
            if (length != 0) {
                if (this._elementSerializer != null) {
                    serializeContentsSlow(strArr, jsonGenerator, serializerProvider, this._elementSerializer);
                    return;
                }
                for (int i = 0; i < length; i++) {
                    if (strArr[i] == null) {
                        jsonGenerator.writeNull();
                    } else {
                        jsonGenerator.writeString(strArr[i]);
                    }
                }
            }
        }
    }

    private ArraySerializers() {
    }
}
