package org.codehaus.jackson.map.ext;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.SerializerBase;
import org.codehaus.jackson.map.util.Provider;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class JodaSerializers implements Provider<Entry<Class<?>, JsonSerializer<?>>> {
    static final HashMap<Class<?>, JsonSerializer<?>> _serializers;

    public abstract class JodaSerializer<T> extends SerializerBase<T> {
        static final DateTimeFormatter _localDateFormat;
        static final DateTimeFormatter _localDateTimeFormat;

        static {
            _localDateTimeFormat = ISODateTimeFormat.dateTime();
            _localDateFormat = ISODateTimeFormat.date();
        }

        protected JodaSerializer(Class<T> cls) {
            super((Class) cls);
        }

        protected String printLocalDate(ReadableInstant readableInstant) {
            return _localDateFormat.print(readableInstant);
        }

        protected String printLocalDate(ReadablePartial readablePartial) {
            return _localDateFormat.print(readablePartial);
        }

        protected String printLocalDateTime(ReadablePartial readablePartial) {
            return _localDateTimeFormat.print(readablePartial);
        }
    }

    public final class DateMidnightSerializer extends JodaSerializer<DateMidnight> {
        public DateMidnightSerializer() {
            super(DateMidnight.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode(serializerProvider.isEnabled(Feature.WRITE_DATES_AS_TIMESTAMPS) ? "array" : "string", true);
        }

        public void serialize(DateMidnight dateMidnight, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (serializerProvider.isEnabled(Feature.WRITE_DATES_AS_TIMESTAMPS)) {
                jsonGenerator.writeStartArray();
                jsonGenerator.writeNumber(dateMidnight.year().get());
                jsonGenerator.writeNumber(dateMidnight.monthOfYear().get());
                jsonGenerator.writeNumber(dateMidnight.dayOfMonth().get());
                jsonGenerator.writeEndArray();
                return;
            }
            jsonGenerator.writeString(printLocalDate((ReadableInstant) dateMidnight));
        }
    }

    public final class DateTimeSerializer extends JodaSerializer<DateTime> {
        public DateTimeSerializer() {
            super(DateTime.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode(serializerProvider.isEnabled(Feature.WRITE_DATES_AS_TIMESTAMPS) ? "number" : "string", true);
        }

        public void serialize(DateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (serializerProvider.isEnabled(Feature.WRITE_DATES_AS_TIMESTAMPS)) {
                jsonGenerator.writeNumber(dateTime.getMillis());
            } else {
                jsonGenerator.writeString(dateTime.toString());
            }
        }
    }

    public final class LocalDateSerializer extends JodaSerializer<LocalDate> {
        public LocalDateSerializer() {
            super(LocalDate.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode(serializerProvider.isEnabled(Feature.WRITE_DATES_AS_TIMESTAMPS) ? "array" : "string", true);
        }

        public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (serializerProvider.isEnabled(Feature.WRITE_DATES_AS_TIMESTAMPS)) {
                jsonGenerator.writeStartArray();
                jsonGenerator.writeNumber(localDate.year().get());
                jsonGenerator.writeNumber(localDate.monthOfYear().get());
                jsonGenerator.writeNumber(localDate.dayOfMonth().get());
                jsonGenerator.writeEndArray();
                return;
            }
            jsonGenerator.writeString(printLocalDate((ReadablePartial) localDate));
        }
    }

    public final class LocalDateTimeSerializer extends JodaSerializer<LocalDateTime> {
        public LocalDateTimeSerializer() {
            super(LocalDateTime.class);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode(serializerProvider.isEnabled(Feature.WRITE_DATES_AS_TIMESTAMPS) ? "array" : "string", true);
        }

        public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (serializerProvider.isEnabled(Feature.WRITE_DATES_AS_TIMESTAMPS)) {
                jsonGenerator.writeStartArray();
                jsonGenerator.writeNumber(localDateTime.year().get());
                jsonGenerator.writeNumber(localDateTime.monthOfYear().get());
                jsonGenerator.writeNumber(localDateTime.dayOfMonth().get());
                jsonGenerator.writeNumber(localDateTime.hourOfDay().get());
                jsonGenerator.writeNumber(localDateTime.minuteOfHour().get());
                jsonGenerator.writeNumber(localDateTime.secondOfMinute().get());
                jsonGenerator.writeNumber(localDateTime.millisOfSecond().get());
                jsonGenerator.writeEndArray();
                return;
            }
            jsonGenerator.writeString(printLocalDateTime(localDateTime));
        }
    }

    static {
        _serializers = new HashMap();
        _serializers.put(DateTime.class, new DateTimeSerializer());
        _serializers.put(LocalDateTime.class, new LocalDateTimeSerializer());
        _serializers.put(LocalDate.class, new LocalDateSerializer());
        _serializers.put(DateMidnight.class, new DateMidnightSerializer());
    }

    public Collection<Entry<Class<?>, JsonSerializer<?>>> provide() {
        return _serializers.entrySet();
    }
}
