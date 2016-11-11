package org.codehaus.jackson.map.ext;

import java.util.Arrays;
import java.util.Collection;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.StdDeserializer;
import org.codehaus.jackson.map.deser.StdScalarDeserializer;
import org.codehaus.jackson.map.util.Provider;
import org.codehaus.jackson.smile.SmileConstants;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class JodaDeserializers implements Provider<StdDeserializer<?>> {

    /* renamed from: org.codehaus.jackson.map.ext.JodaDeserializers.1 */
    /* synthetic */ class C35871 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonToken;

        static {
            $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    abstract class JodaDeserializer<T> extends StdScalarDeserializer<T> {
        static final DateTimeFormatter _localDateTimeFormat;

        static {
            _localDateTimeFormat = ISODateTimeFormat.localDateOptionalTimeParser();
        }

        protected JodaDeserializer(Class<T> cls) {
            super(cls);
        }

        protected DateTime parseLocal(JsonParser jsonParser) {
            String trim = jsonParser.getText().trim();
            return trim.length() == 0 ? null : _localDateTimeFormat.parseDateTime(trim);
        }
    }

    public class DateMidnightDeserializer extends JodaDeserializer<DateMidnight> {
        public DateMidnightDeserializer() {
            super(DateMidnight.class);
        }

        public DateMidnight deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (jsonParser.isExpectedStartArrayToken()) {
                jsonParser.nextToken();
                int intValue = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue2 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue3 = jsonParser.getIntValue();
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return new DateMidnight(intValue, intValue2, intValue3);
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "after DateMidnight ints");
            }
            switch (C35871.$SwitchMap$org$codehaus$jackson$JsonToken[jsonParser.getCurrentToken().ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    return new DateMidnight(jsonParser.getLongValue());
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    DateTime parseLocal = parseLocal(jsonParser);
                    return parseLocal == null ? null : parseLocal.toDateMidnight();
                default:
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Array, Number or String");
            }
        }
    }

    public class DateTimeDeserializer<T extends ReadableInstant> extends JodaDeserializer<T> {
        public DateTimeDeserializer(Class<T> cls) {
            super(cls);
        }

        public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                return new DateTime(jsonParser.getLongValue(), DateTimeZone.UTC);
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                return trim.length() == 0 ? null : new DateTime(trim, DateTimeZone.UTC);
            } else {
                throw deserializationContext.mappingException(getValueClass());
            }
        }
    }

    public class LocalDateDeserializer extends JodaDeserializer<LocalDate> {
        public LocalDateDeserializer() {
            super(LocalDate.class);
        }

        public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (jsonParser.isExpectedStartArrayToken()) {
                jsonParser.nextToken();
                int intValue = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue2 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue3 = jsonParser.getIntValue();
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return new LocalDate(intValue, intValue2, intValue3);
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "after LocalDate ints");
            }
            switch (C35871.$SwitchMap$org$codehaus$jackson$JsonToken[jsonParser.getCurrentToken().ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    return new LocalDate(jsonParser.getLongValue());
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    DateTime parseLocal = parseLocal(jsonParser);
                    return parseLocal == null ? null : parseLocal.toLocalDate();
                default:
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Array, String or Number");
            }
        }
    }

    public class LocalDateTimeDeserializer extends JodaDeserializer<LocalDateTime> {
        public LocalDateTimeDeserializer() {
            super(LocalDateTime.class);
        }

        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (jsonParser.isExpectedStartArrayToken()) {
                jsonParser.nextToken();
                int intValue = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue2 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue3 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue4 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue5 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue6 = jsonParser.getIntValue();
                int i = 0;
                if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    i = jsonParser.getIntValue();
                    jsonParser.nextToken();
                }
                if (jsonParser.getCurrentToken() == JsonToken.END_ARRAY) {
                    return new LocalDateTime(intValue, intValue2, intValue3, intValue4, intValue5, intValue6, i);
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "after LocalDateTime ints");
            }
            switch (C35871.$SwitchMap$org$codehaus$jackson$JsonToken[jsonParser.getCurrentToken().ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    return new LocalDateTime(jsonParser.getLongValue());
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    DateTime parseLocal = parseLocal(jsonParser);
                    return parseLocal == null ? null : parseLocal.toLocalDateTime();
                default:
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Array or Number");
            }
        }
    }

    public Collection<StdDeserializer<?>> provide() {
        return Arrays.asList(new StdDeserializer[]{new DateTimeDeserializer(DateTime.class), new DateTimeDeserializer(ReadableDateTime.class), new DateTimeDeserializer(ReadableInstant.class), new LocalDateDeserializer(), new LocalDateTimeDeserializer(), new DateMidnightDeserializer()});
    }
}
