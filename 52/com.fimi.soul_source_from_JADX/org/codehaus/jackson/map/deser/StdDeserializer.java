package org.codehaus.jackson.map.deser;

import com.fimi.kernel.p084e.C1186y;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.open.GameAppOperation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.codehaus.jackson.Base64Variants;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonParser.NumberType;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.io.NumberInput;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ResolvableDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.TokenBuffer;
import org.p122a.p123a.C2915a;

public abstract class StdDeserializer<T> extends JsonDeserializer<T> {
    protected final Class<?> _valueClass;

    /* renamed from: org.codehaus.jackson.map.deser.StdDeserializer.1 */
    /* synthetic */ class C35851 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonParser$NumberType;
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonToken;

        static {
            $SwitchMap$org$codehaus$jackson$JsonParser$NumberType = new int[NumberType.values().length];
            try {
                $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[NumberType.INT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[NumberType.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public final class AtomicBooleanDeserializer extends StdScalarDeserializer<AtomicBoolean> {
        public AtomicBooleanDeserializer() {
            super(AtomicBoolean.class);
        }

        public AtomicBoolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return new AtomicBoolean(_parseBooleanPrimitive(jsonParser, deserializationContext));
        }
    }

    public class AtomicReferenceDeserializer extends StdScalarDeserializer<AtomicReference<?>> implements ResolvableDeserializer {
        protected final BeanProperty _property;
        protected final JavaType _referencedType;
        protected JsonDeserializer<?> _valueDeserializer;

        public AtomicReferenceDeserializer(JavaType javaType, BeanProperty beanProperty) {
            super(AtomicReference.class);
            this._referencedType = javaType;
            this._property = beanProperty;
        }

        public AtomicReference<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return new AtomicReference(this._valueDeserializer.deserialize(jsonParser, deserializationContext));
        }

        public void resolve(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider) {
            this._valueDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, this._referencedType, this._property);
        }
    }

    @JacksonStdImpl
    public class BigDecimalDeserializer extends StdScalarDeserializer<BigDecimal> {
        public BigDecimalDeserializer() {
            super(BigDecimal.class);
        }

        public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                return jsonParser.getDecimalValue();
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return null;
                }
                try {
                    return new BigDecimal(trim);
                } catch (IllegalArgumentException e) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid representation");
                }
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    @JacksonStdImpl
    public class BigIntegerDeserializer extends StdScalarDeserializer<BigInteger> {
        public BigIntegerDeserializer() {
            super(BigInteger.class);
        }

        public BigInteger deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                switch (C35851.$SwitchMap$org$codehaus$jackson$JsonParser$NumberType[jsonParser.getNumberType().ordinal()]) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        return BigInteger.valueOf(jsonParser.getLongValue());
                }
            } else if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                return jsonParser.getDecimalValue().toBigInteger();
            } else {
                if (currentToken != JsonToken.VALUE_STRING) {
                    throw deserializationContext.mappingException(this._valueClass);
                }
            }
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return null;
            }
            try {
                return new BigInteger(trim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid representation");
            }
        }
    }

    public abstract class PrimitiveOrWrapperDeserializer<T> extends StdScalarDeserializer<T> {
        final T _nullValue;

        protected PrimitiveOrWrapperDeserializer(Class<T> cls, T t) {
            super(cls);
            this._nullValue = t;
        }

        public final T getNullValue() {
            return this._nullValue;
        }
    }

    @JacksonStdImpl
    public final class BooleanDeserializer extends PrimitiveOrWrapperDeserializer<Boolean> {
        public BooleanDeserializer(Class<Boolean> cls, Boolean bool) {
            super(cls, bool);
        }

        public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseBoolean(jsonParser, deserializationContext);
        }

        public Boolean deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return _parseBoolean(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public final class ByteDeserializer extends PrimitiveOrWrapperDeserializer<Byte> {
        public ByteDeserializer(Class<Byte> cls, Byte b) {
            super(cls, b);
        }

        public Byte deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
            if (_parseIntPrimitive >= -128 && _parseIntPrimitive <= Opcodes.LAND) {
                return Byte.valueOf((byte) _parseIntPrimitive);
            }
            throw deserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 8-bit value");
        }
    }

    @JacksonStdImpl
    public class CalendarDeserializer extends StdScalarDeserializer<Calendar> {
        Class<? extends Calendar> _calendarClass;

        public CalendarDeserializer() {
            this(null);
        }

        public CalendarDeserializer(Class<? extends Calendar> cls) {
            super(Calendar.class);
            this._calendarClass = cls;
        }

        public Calendar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            Date _parseDate = _parseDate(jsonParser, deserializationContext);
            if (_parseDate == null) {
                return null;
            }
            if (this._calendarClass == null) {
                return deserializationContext.constructCalendar(_parseDate);
            }
            try {
                Calendar calendar = (Calendar) this._calendarClass.newInstance();
                calendar.setTimeInMillis(_parseDate.getTime());
                return calendar;
            } catch (Throwable e) {
                throw deserializationContext.instantiationException(this._calendarClass, e);
            }
        }
    }

    @JacksonStdImpl
    public final class CharacterDeserializer extends PrimitiveOrWrapperDeserializer<Character> {
        public CharacterDeserializer(Class<Character> cls, Character ch) {
            super(cls, ch);
        }

        public Character deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                int intValue = jsonParser.getIntValue();
                if (intValue >= 0 && intValue <= Util.MASK_16BIT) {
                    return Character.valueOf((char) intValue);
                }
            } else if (currentToken == JsonToken.VALUE_STRING) {
                String text = jsonParser.getText();
                if (text.length() == 1) {
                    return Character.valueOf(text.charAt(0));
                }
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    @JacksonStdImpl
    public final class ClassDeserializer extends StdScalarDeserializer<Class<?>> {
        public ClassDeserializer() {
            super(Class.class);
        }

        public Class<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
                String text = jsonParser.getText();
                if (text.indexOf(46) < 0) {
                    if ("int".equals(text)) {
                        return Integer.TYPE;
                    }
                    if ("long".equals(text)) {
                        return Long.TYPE;
                    }
                    if ("float".equals(text)) {
                        return Float.TYPE;
                    }
                    if ("double".equals(text)) {
                        return Double.TYPE;
                    }
                    if ("boolean".equals(text)) {
                        return Boolean.TYPE;
                    }
                    if ("byte".equals(text)) {
                        return Byte.TYPE;
                    }
                    if ("char".equals(text)) {
                        return Character.TYPE;
                    }
                    if ("short".equals(text)) {
                        return Short.TYPE;
                    }
                    if ("void".equals(text)) {
                        return Void.TYPE;
                    }
                }
                try {
                    return Class.forName(jsonParser.getText());
                } catch (Throwable e) {
                    throw deserializationContext.instantiationException(this._valueClass, e);
                }
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    @JacksonStdImpl
    public final class DoubleDeserializer extends PrimitiveOrWrapperDeserializer<Double> {
        public DoubleDeserializer(Class<Double> cls, Double d) {
            super(cls, d);
        }

        public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseDouble(jsonParser, deserializationContext);
        }

        public Double deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return _parseDouble(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public final class FloatDeserializer extends PrimitiveOrWrapperDeserializer<Float> {
        public FloatDeserializer(Class<Float> cls, Float f) {
            super(cls, f);
        }

        public Float deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseFloat(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public final class IntegerDeserializer extends PrimitiveOrWrapperDeserializer<Integer> {
        public IntegerDeserializer(Class<Integer> cls, Integer num) {
            super(cls, num);
        }

        public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseInteger(jsonParser, deserializationContext);
        }

        public Integer deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return _parseInteger(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public final class LongDeserializer extends PrimitiveOrWrapperDeserializer<Long> {
        public LongDeserializer(Class<Long> cls, Long l) {
            super(cls, l);
        }

        public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseLong(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public final class NumberDeserializer extends StdScalarDeserializer<Number> {
        public NumberDeserializer() {
            super(Number.class);
        }

        public Number deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                return deserializationContext.isEnabled(Feature.USE_BIG_INTEGER_FOR_INTS) ? jsonParser.getBigIntegerValue() : jsonParser.getNumberValue();
            } else {
                if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                    return deserializationContext.isEnabled(Feature.USE_BIG_DECIMAL_FOR_FLOATS) ? jsonParser.getDecimalValue() : Double.valueOf(jsonParser.getDoubleValue());
                } else {
                    if (currentToken == JsonToken.VALUE_STRING) {
                        String trim = jsonParser.getText().trim();
                        try {
                            if (trim.indexOf(46) >= 0) {
                                return deserializationContext.isEnabled(Feature.USE_BIG_DECIMAL_FOR_FLOATS) ? new BigDecimal(trim) : new Double(trim);
                            } else {
                                if (deserializationContext.isEnabled(Feature.USE_BIG_INTEGER_FOR_INTS)) {
                                    return new BigInteger(trim);
                                }
                                long parseLong = Long.parseLong(trim);
                                return (parseLong > 2147483647L || parseLong < -2147483648L) ? Long.valueOf(parseLong) : Integer.valueOf((int) parseLong);
                            }
                        } catch (IllegalArgumentException e) {
                            throw deserializationContext.weirdStringException(this._valueClass, "not a valid number");
                        }
                    }
                    throw deserializationContext.mappingException(this._valueClass);
                }
            }
        }

        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            switch (C35851.$SwitchMap$org$codehaus$jackson$JsonToken[jsonParser.getCurrentToken().ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                case Type.BYTE /*3*/:
                    return deserialize(jsonParser, deserializationContext);
                default:
                    return typeDeserializer.deserializeTypedFromScalar(jsonParser, deserializationContext);
            }
        }
    }

    @JacksonStdImpl
    public final class ShortDeserializer extends PrimitiveOrWrapperDeserializer<Short> {
        public ShortDeserializer(Class<Short> cls, Short sh) {
            super(cls, sh);
        }

        public Short deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseShort(jsonParser, deserializationContext);
        }
    }

    public class SqlDateDeserializer extends StdScalarDeserializer<java.sql.Date> {
        public SqlDateDeserializer() {
            super(java.sql.Date.class);
        }

        public java.sql.Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            Date _parseDate = _parseDate(jsonParser, deserializationContext);
            return _parseDate == null ? null : new java.sql.Date(_parseDate.getTime());
        }
    }

    public class StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
        public StackTraceElementDeserializer() {
            super(StackTraceElement.class);
        }

        public StackTraceElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
                JsonToken nextValue;
                String str = C2915a.f14760f;
                String str2 = C2915a.f14760f;
                String str3 = C2915a.f14760f;
                int i = -1;
                while (true) {
                    nextValue = jsonParser.nextValue();
                    if (nextValue == JsonToken.END_OBJECT) {
                        return new StackTraceElement(str, str2, str3, i);
                    }
                    String currentName = jsonParser.getCurrentName();
                    if ("className".equals(currentName)) {
                        str = jsonParser.getText();
                    } else if ("fileName".equals(currentName)) {
                        str3 = jsonParser.getText();
                    } else if ("lineNumber".equals(currentName)) {
                        if (!nextValue.isNumeric()) {
                            break;
                        }
                        i = jsonParser.getIntValue();
                    } else if ("methodName".equals(currentName)) {
                        str2 = jsonParser.getText();
                    } else if (!"nativeMethod".equals(currentName)) {
                        handleUnknownProperty(jsonParser, deserializationContext, this._valueClass, currentName);
                    }
                }
                throw JsonMappingException.from(jsonParser, "Non-numeric token (" + nextValue + ") for property 'lineNumber'");
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    @JacksonStdImpl
    public final class StringDeserializer extends StdScalarDeserializer<String> {
        public StringDeserializer() {
            super(String.class);
        }

        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                return jsonParser.getText();
            }
            if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object embeddedObject = jsonParser.getEmbeddedObject();
                return embeddedObject == null ? null : embeddedObject instanceof byte[] ? Base64Variants.getDefaultVariant().encode((byte[]) embeddedObject, false) : embeddedObject.toString();
            } else if (currentToken.isScalarValue()) {
                return jsonParser.getText();
            } else {
                throw deserializationContext.mappingException(this._valueClass);
            }
        }

        public String deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return deserialize(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public class TokenBufferDeserializer extends StdScalarDeserializer<TokenBuffer> {
        public TokenBufferDeserializer() {
            super(TokenBuffer.class);
        }

        public TokenBuffer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
            tokenBuffer.copyCurrentStructure(jsonParser);
            return tokenBuffer;
        }
    }

    protected StdDeserializer(Class<?> cls) {
        this._valueClass = cls;
    }

    protected StdDeserializer(JavaType javaType) {
        this._valueClass = javaType == null ? null : javaType.getRawClass();
    }

    protected static final double parseDouble(String str) {
        return NumberInput.NASTY_SMALL_DOUBLE.equals(str) ? Double.MIN_NORMAL : Double.parseDouble(str);
    }

    protected final Boolean _parseBoolean(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
        }
        if (currentToken == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
        }
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            return jsonParser.getIntValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
        } else {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if ("true".equals(trim)) {
                    return Boolean.TRUE;
                }
                if ("false".equals(trim) || trim.length() == 0) {
                    return Boolean.FALSE;
                }
                throw deserializationContext.weirdStringException(this._valueClass, "only \"true\" or \"false\" recognized");
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    protected final boolean _parseBooleanPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (currentToken == JsonToken.VALUE_FALSE) {
            return false;
        }
        if (currentToken == JsonToken.VALUE_NULL) {
            return false;
        }
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            return jsonParser.getIntValue() != 0;
        } else {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if ("true".equals(trim)) {
                    return true;
                }
                if ("false".equals(trim) || trim.length() == 0) {
                    return Boolean.FALSE.booleanValue();
                }
                throw deserializationContext.weirdStringException(this._valueClass, "only \"true\" or \"false\" recognized");
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    protected Date _parseDate(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        try {
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                return new Date(jsonParser.getLongValue());
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                return trim.length() == 0 ? null : deserializationContext.parseDate(trim);
            } else {
                throw deserializationContext.mappingException(this._valueClass);
            }
        } catch (IllegalArgumentException e) {
            throw deserializationContext.weirdStringException(this._valueClass, "not a valid representation (error: " + e.getMessage() + ")");
        }
    }

    protected final Double _parseDouble(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Double.valueOf(jsonParser.getDoubleValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return null;
            }
            switch (trim.charAt(0)) {
                case GameAppOperation.SHARE_PRIZE_TITLE_MAX_LENGTH /*45*/:
                    if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        return Double.valueOf(Double.NEGATIVE_INFINITY);
                    }
                case 'I':
                    if ("Infinity".equals(trim) || "INF".equals(trim)) {
                        return Double.valueOf(Double.POSITIVE_INFINITY);
                    }
                case 'N':
                    if ("NaN".equals(trim)) {
                        return Double.valueOf(Double.NaN);
                    }
                    break;
            }
            try {
                return Double.valueOf(parseDouble(trim));
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid Double value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        } else {
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    protected final double _parseDoublePrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getDoubleValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return 0.0d;
            }
            switch (trim.charAt(0)) {
                case GameAppOperation.SHARE_PRIZE_TITLE_MAX_LENGTH /*45*/:
                    if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        return Double.NEGATIVE_INFINITY;
                    }
                case 'I':
                    if ("Infinity".equals(trim) || "INF".equals(trim)) {
                        return Double.POSITIVE_INFINITY;
                    }
                case 'N':
                    if ("NaN".equals(trim)) {
                        return Double.NaN;
                    }
                    break;
            }
            try {
                return parseDouble(trim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid double value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return 0.0d;
        } else {
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    protected final Float _parseFloat(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Float.valueOf(jsonParser.getFloatValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return null;
            }
            switch (trim.charAt(0)) {
                case GameAppOperation.SHARE_PRIZE_TITLE_MAX_LENGTH /*45*/:
                    if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        return Float.valueOf(Float.NEGATIVE_INFINITY);
                    }
                case 'I':
                    if ("Infinity".equals(trim) || "INF".equals(trim)) {
                        return Float.valueOf(Float.POSITIVE_INFINITY);
                    }
                case 'N':
                    if ("NaN".equals(trim)) {
                        return Float.valueOf(Float.NaN);
                    }
                    break;
            }
            try {
                return Float.valueOf(Float.parseFloat(trim));
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid Float value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        } else {
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    protected final float _parseFloatPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getFloatValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return 0.0f;
            }
            switch (trim.charAt(0)) {
                case GameAppOperation.SHARE_PRIZE_TITLE_MAX_LENGTH /*45*/:
                    if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        return Float.NEGATIVE_INFINITY;
                    }
                case 'I':
                    if ("Infinity".equals(trim) || "INF".equals(trim)) {
                        return Float.POSITIVE_INFINITY;
                    }
                case 'N':
                    if ("NaN".equals(trim)) {
                        return Float.NaN;
                    }
                    break;
            }
            try {
                return Float.parseFloat(trim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid float value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return 0.0f;
        } else {
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    protected final int _parseIntPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getIntValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            try {
                int length = trim.length();
                if (length <= 9) {
                    return length != 0 ? NumberInput.parseInt(trim) : 0;
                } else {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong >= -2147483648L && parseLong <= 2147483647L) {
                        return (int) parseLong;
                    }
                    throw deserializationContext.weirdStringException(this._valueClass, "Overflow: numeric value (" + trim + ") out of range of int (" + C1186y.f5353a + " - " + Integer.MAX_VALUE + ")");
                }
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid int value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return 0;
        } else {
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    protected final Integer _parseInteger(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Integer.valueOf(jsonParser.getIntValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            try {
                int length = trim.length();
                if (length <= 9) {
                    return length != 0 ? Integer.valueOf(NumberInput.parseInt(trim)) : null;
                } else {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong >= -2147483648L && parseLong <= 2147483647L) {
                        return Integer.valueOf((int) parseLong);
                    }
                    throw deserializationContext.weirdStringException(this._valueClass, "Overflow: numeric value (" + trim + ") out of range of Integer (" + C1186y.f5353a + " - " + Integer.MAX_VALUE + ")");
                }
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid Integer value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        } else {
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    protected final Long _parseLong(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Long.valueOf(jsonParser.getLongValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return null;
            }
            try {
                return Long.valueOf(NumberInput.parseLong(trim));
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid Long value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        } else {
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    protected final long _parseLongPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getLongValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return 0;
            }
            try {
                return NumberInput.parseLong(trim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid long value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return 0;
        } else {
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    protected final Short _parseShort(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Short.valueOf(jsonParser.getShortValue());
        }
        int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
        if (_parseIntPrimitive >= -32768 && _parseIntPrimitive <= 32767) {
            return Short.valueOf((short) _parseIntPrimitive);
        }
        throw deserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 16-bit value");
    }

    protected final short _parseShortPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
        if (_parseIntPrimitive >= -32768 && _parseIntPrimitive <= 32767) {
            return (short) _parseIntPrimitive;
        }
        throw deserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 16-bit value");
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    protected JsonDeserializer<Object> findDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) {
        return deserializerProvider.findValueDeserializer(deserializationConfig, javaType, beanProperty);
    }

    public Class<?> getValueClass() {
        return this._valueClass;
    }

    public JavaType getValueType() {
        return null;
    }

    protected void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) {
        if (obj == null) {
            obj = getValueClass();
        }
        if (!deserializationContext.handleUnknownProperty(jsonParser, this, obj, str)) {
            reportUnknownProperty(deserializationContext, obj, str);
            jsonParser.skipChildren();
        }
    }

    protected boolean isDefaultSerializer(JsonDeserializer<?> jsonDeserializer) {
        return (jsonDeserializer == null || jsonDeserializer.getClass().getAnnotation(JacksonStdImpl.class) == null) ? false : true;
    }

    protected void reportUnknownProperty(DeserializationContext deserializationContext, Object obj, String str) {
        if (deserializationContext.isEnabled(Feature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            throw deserializationContext.unknownFieldException(obj, str);
        }
    }
}
