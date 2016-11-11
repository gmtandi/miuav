package org.codehaus.jackson.map.deser;

import java.lang.reflect.Type;
import java.util.HashMap;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ArrayBuilders.BooleanBuilder;
import org.codehaus.jackson.map.util.ArrayBuilders.ByteBuilder;
import org.codehaus.jackson.map.util.ArrayBuilders.DoubleBuilder;
import org.codehaus.jackson.map.util.ArrayBuilders.FloatBuilder;
import org.codehaus.jackson.map.util.ArrayBuilders.IntBuilder;
import org.codehaus.jackson.map.util.ArrayBuilders.LongBuilder;
import org.codehaus.jackson.map.util.ArrayBuilders.ShortBuilder;
import org.codehaus.jackson.map.util.ObjectBuffer;
import org.codehaus.jackson.type.JavaType;

public class ArrayDeserializers {
    static final ArrayDeserializers instance;
    HashMap<JavaType, JsonDeserializer<Object>> _allDeserializers;

    abstract class ArrayDeser<T> extends StdDeserializer<T> {
        protected ArrayDeser(Class<T> cls) {
            super((Class) cls);
        }

        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    final class BooleanDeser extends ArrayDeser<boolean[]> {
        public BooleanDeser() {
            super(boolean[].class);
        }

        private final boolean[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (deserializationContext.isEnabled(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new boolean[]{_parseBooleanPrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        public boolean[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            BooleanBuilder booleanBuilder = deserializationContext.getArrayBuilders().getBooleanBuilder();
            Object obj = (boolean[]) booleanBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                int i2;
                boolean _parseBooleanPrimitive = _parseBooleanPrimitive(jsonParser, deserializationContext);
                if (i >= obj.length) {
                    i2 = 0;
                    obj = (boolean[]) booleanBuilder.appendCompletedChunk(obj, i);
                } else {
                    i2 = i;
                }
                i = i2 + 1;
                obj[i2] = _parseBooleanPrimitive;
            }
            return (boolean[]) booleanBuilder.completeAndClearBuffer(obj, i);
        }
    }

    @JacksonStdImpl
    final class ByteDeser extends ArrayDeser<byte[]> {
        public ByteDeser() {
            super(byte[].class);
        }

        private final byte[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (deserializationContext.isEnabled(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                byte byteValue;
                JsonToken currentToken = jsonParser.getCurrentToken();
                if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                    byteValue = jsonParser.getByteValue();
                } else if (currentToken != JsonToken.VALUE_NULL) {
                    throw deserializationContext.mappingException(this._valueClass.getComponentType());
                } else {
                    byteValue = (byte) 0;
                }
                return new byte[]{byteValue};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        public byte[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                return jsonParser.getBinaryValue(deserializationContext.getBase64Variant());
            }
            if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object embeddedObject = jsonParser.getEmbeddedObject();
                if (embeddedObject == null) {
                    return null;
                }
                if (embeddedObject instanceof byte[]) {
                    return (byte[]) embeddedObject;
                }
            }
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ByteBuilder byteBuilder = deserializationContext.getArrayBuilders().getByteBuilder();
            Object obj = (byte[]) byteBuilder.resetAndStart();
            int i = 0;
            while (true) {
                JsonToken nextToken = jsonParser.nextToken();
                if (nextToken == JsonToken.END_ARRAY) {
                    return (byte[]) byteBuilder.completeAndClearBuffer(obj, i);
                }
                byte byteValue;
                int i2;
                if (nextToken == JsonToken.VALUE_NUMBER_INT || nextToken == JsonToken.VALUE_NUMBER_FLOAT) {
                    byteValue = jsonParser.getByteValue();
                } else if (nextToken != JsonToken.VALUE_NULL) {
                    break;
                } else {
                    byteValue = (byte) 0;
                }
                if (i >= obj.length) {
                    i2 = 0;
                    obj = (byte[]) byteBuilder.appendCompletedChunk(obj, i);
                } else {
                    i2 = i;
                }
                i = i2 + 1;
                obj[i2] = byteValue;
            }
            throw deserializationContext.mappingException(this._valueClass.getComponentType());
        }
    }

    @JacksonStdImpl
    final class CharDeser extends ArrayDeser<char[]> {
        public CharDeser() {
            super(char[].class);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public char[] deserialize(org.codehaus.jackson.JsonParser r6, org.codehaus.jackson.map.DeserializationContext r7) {
            /*
            r5 = this;
            r4 = 0;
            r0 = r6.getCurrentToken();
            r1 = org.codehaus.jackson.JsonToken.VALUE_STRING;
            if (r0 != r1) goto L_0x001b;
        L_0x0009:
            r1 = r6.getTextCharacters();
            r2 = r6.getTextOffset();
            r3 = r6.getTextLength();
            r0 = new char[r3];
            java.lang.System.arraycopy(r1, r2, r0, r4, r3);
        L_0x001a:
            return r0;
        L_0x001b:
            r1 = r6.isExpectedStartArrayToken();
            if (r1 == 0) goto L_0x0079;
        L_0x0021:
            r0 = new java.lang.StringBuilder;
            r1 = 64;
            r0.<init>(r1);
        L_0x0028:
            r1 = r6.nextToken();
            r2 = org.codehaus.jackson.JsonToken.END_ARRAY;
            if (r1 == r2) goto L_0x0070;
        L_0x0030:
            r2 = org.codehaus.jackson.JsonToken.VALUE_STRING;
            if (r1 == r2) goto L_0x003b;
        L_0x0034:
            r0 = java.lang.Character.TYPE;
            r0 = r7.mappingException(r0);
            throw r0;
        L_0x003b:
            r1 = r6.getText();
            r2 = r1.length();
            r3 = 1;
            if (r2 == r3) goto L_0x0068;
        L_0x0046:
            r0 = new java.lang.StringBuilder;
            r0.<init>();
            r2 = "Can not convert a JSON String of length ";
            r0 = r0.append(r2);
            r1 = r1.length();
            r0 = r0.append(r1);
            r1 = " into a char element of char array";
            r0 = r0.append(r1);
            r0 = r0.toString();
            r0 = org.codehaus.jackson.map.JsonMappingException.from(r6, r0);
            throw r0;
        L_0x0068:
            r1 = r1.charAt(r4);
            r0.append(r1);
            goto L_0x0028;
        L_0x0070:
            r0 = r0.toString();
            r0 = r0.toCharArray();
            goto L_0x001a;
        L_0x0079:
            r1 = org.codehaus.jackson.JsonToken.VALUE_EMBEDDED_OBJECT;
            if (r0 != r1) goto L_0x00af;
        L_0x007d:
            r0 = r6.getEmbeddedObject();
            if (r0 != 0) goto L_0x0085;
        L_0x0083:
            r0 = 0;
            goto L_0x001a;
        L_0x0085:
            r1 = r0 instanceof char[];
            if (r1 == 0) goto L_0x008e;
        L_0x0089:
            r0 = (char[]) r0;
            r0 = (char[]) r0;
            goto L_0x001a;
        L_0x008e:
            r1 = r0 instanceof java.lang.String;
            if (r1 == 0) goto L_0x0099;
        L_0x0092:
            r0 = (java.lang.String) r0;
            r0 = r0.toCharArray();
            goto L_0x001a;
        L_0x0099:
            r1 = r0 instanceof byte[];
            if (r1 == 0) goto L_0x00af;
        L_0x009d:
            r1 = org.codehaus.jackson.Base64Variants.getDefaultVariant();
            r0 = (byte[]) r0;
            r0 = (byte[]) r0;
            r0 = r1.encode(r0, r4);
            r0 = r0.toCharArray();
            goto L_0x001a;
        L_0x00af:
            r0 = r5._valueClass;
            r0 = r7.mappingException(r0);
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.map.deser.ArrayDeserializers.CharDeser.deserialize(org.codehaus.jackson.JsonParser, org.codehaus.jackson.map.DeserializationContext):char[]");
        }
    }

    @JacksonStdImpl
    final class DoubleDeser extends ArrayDeser<double[]> {
        public DoubleDeser() {
            super(double[].class);
        }

        private final double[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (deserializationContext.isEnabled(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new double[]{_parseDoublePrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        public double[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            DoubleBuilder doubleBuilder = deserializationContext.getArrayBuilders().getDoubleBuilder();
            Object obj = (double[]) doubleBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                int i2;
                double _parseDoublePrimitive = _parseDoublePrimitive(jsonParser, deserializationContext);
                if (i >= obj.length) {
                    i2 = 0;
                    obj = (double[]) doubleBuilder.appendCompletedChunk(obj, i);
                } else {
                    i2 = i;
                }
                i = i2 + 1;
                obj[i2] = _parseDoublePrimitive;
            }
            return (double[]) doubleBuilder.completeAndClearBuffer(obj, i);
        }
    }

    @JacksonStdImpl
    final class FloatDeser extends ArrayDeser<float[]> {
        public FloatDeser() {
            super(float[].class);
        }

        private final float[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (deserializationContext.isEnabled(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new float[]{_parseFloatPrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        public float[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            FloatBuilder floatBuilder = deserializationContext.getArrayBuilders().getFloatBuilder();
            Object obj = (float[]) floatBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                int i2;
                float _parseFloatPrimitive = _parseFloatPrimitive(jsonParser, deserializationContext);
                if (i >= obj.length) {
                    i2 = 0;
                    obj = (float[]) floatBuilder.appendCompletedChunk(obj, i);
                } else {
                    i2 = i;
                }
                i = i2 + 1;
                obj[i2] = _parseFloatPrimitive;
            }
            return (float[]) floatBuilder.completeAndClearBuffer(obj, i);
        }
    }

    @JacksonStdImpl
    final class IntDeser extends ArrayDeser<int[]> {
        public IntDeser() {
            super(int[].class);
        }

        private final int[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (deserializationContext.isEnabled(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new int[]{_parseIntPrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        public int[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            IntBuilder intBuilder = deserializationContext.getArrayBuilders().getIntBuilder();
            Object obj = (int[]) intBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                int i2;
                int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
                if (i >= obj.length) {
                    i2 = 0;
                    obj = (int[]) intBuilder.appendCompletedChunk(obj, i);
                } else {
                    i2 = i;
                }
                i = i2 + 1;
                obj[i2] = _parseIntPrimitive;
            }
            return (int[]) intBuilder.completeAndClearBuffer(obj, i);
        }
    }

    @JacksonStdImpl
    final class LongDeser extends ArrayDeser<long[]> {
        public LongDeser() {
            super(long[].class);
        }

        private final long[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (deserializationContext.isEnabled(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new long[]{_parseLongPrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        public long[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            LongBuilder longBuilder = deserializationContext.getArrayBuilders().getLongBuilder();
            Object obj = (long[]) longBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                int i2;
                long _parseLongPrimitive = _parseLongPrimitive(jsonParser, deserializationContext);
                if (i >= obj.length) {
                    i2 = 0;
                    obj = (long[]) longBuilder.appendCompletedChunk(obj, i);
                } else {
                    i2 = i;
                }
                i = i2 + 1;
                obj[i2] = _parseLongPrimitive;
            }
            return (long[]) longBuilder.completeAndClearBuffer(obj, i);
        }
    }

    @JacksonStdImpl
    final class ShortDeser extends ArrayDeser<short[]> {
        public ShortDeser() {
            super(short[].class);
        }

        private final short[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (deserializationContext.isEnabled(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new short[]{_parseShortPrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        public short[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ShortBuilder shortBuilder = deserializationContext.getArrayBuilders().getShortBuilder();
            Object obj = (short[]) shortBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                int i2;
                short _parseShortPrimitive = _parseShortPrimitive(jsonParser, deserializationContext);
                if (i >= obj.length) {
                    i2 = 0;
                    obj = (short[]) shortBuilder.appendCompletedChunk(obj, i);
                } else {
                    i2 = i;
                }
                i = i2 + 1;
                obj[i2] = _parseShortPrimitive;
            }
            return (short[]) shortBuilder.completeAndClearBuffer(obj, i);
        }
    }

    @JacksonStdImpl
    final class StringDeser extends ArrayDeser<String[]> {
        public StringDeser() {
            super(String[].class);
        }

        private final String[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (deserializationContext.isEnabled(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                String[] strArr = new String[1];
                strArr[0] = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null : jsonParser.getText();
                return strArr;
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        public String[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
            Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
            int i = 0;
            while (true) {
                JsonToken nextToken = jsonParser.nextToken();
                if (nextToken != JsonToken.END_ARRAY) {
                    int i2;
                    String text = nextToken == JsonToken.VALUE_NULL ? null : jsonParser.getText();
                    if (i >= resetAndStart.length) {
                        resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                        i2 = 0;
                    } else {
                        i2 = i;
                    }
                    i = i2 + 1;
                    resetAndStart[i2] = text;
                } else {
                    String[] strArr = (String[]) leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i, String.class);
                    deserializationContext.returnObjectBuffer(leaseObjectBuffer);
                    return strArr;
                }
            }
        }
    }

    static {
        instance = new ArrayDeserializers();
    }

    private ArrayDeserializers() {
        this._allDeserializers = new HashMap();
        add(Boolean.TYPE, new BooleanDeser());
        add(Byte.TYPE, new ByteDeser());
        add(Short.TYPE, new ShortDeser());
        add(Integer.TYPE, new IntDeser());
        add(Long.TYPE, new LongDeser());
        add(Float.TYPE, new FloatDeser());
        add(Double.TYPE, new DoubleDeser());
        add(String.class, new StringDeser());
        add(Character.TYPE, new CharDeser());
    }

    private void add(Class<?> cls, JsonDeserializer<?> jsonDeserializer) {
        this._allDeserializers.put(TypeFactory.defaultInstance().constructType((Type) cls), jsonDeserializer);
    }

    public static HashMap<JavaType, JsonDeserializer<Object>> getAll() {
        return instance._allDeserializers;
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }
}
