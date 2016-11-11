package org.codehaus.jackson.map.deser;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;

public abstract class FromStringDeserializer<T> extends StdScalarDeserializer<T> {

    public class CurrencyDeserializer extends FromStringDeserializer<Currency> {
        public CurrencyDeserializer() {
            super(Currency.class);
        }

        protected Currency _deserialize(String str, DeserializationContext deserializationContext) {
            return Currency.getInstance(str);
        }
    }

    public class InetAddressDeserializer extends FromStringDeserializer<InetAddress> {
        public InetAddressDeserializer() {
            super(InetAddress.class);
        }

        protected InetAddress _deserialize(String str, DeserializationContext deserializationContext) {
            return InetAddress.getByName(str);
        }
    }

    public class LocaleDeserializer extends FromStringDeserializer<Locale> {
        public LocaleDeserializer() {
            super(Locale.class);
        }

        protected Locale _deserialize(String str, DeserializationContext deserializationContext) {
            int indexOf = str.indexOf(95);
            if (indexOf < 0) {
                return new Locale(str);
            }
            String substring = str.substring(0, indexOf);
            String substring2 = str.substring(indexOf + 1);
            int indexOf2 = substring2.indexOf(95);
            return indexOf2 < 0 ? new Locale(substring, substring2) : new Locale(substring, substring2.substring(0, indexOf2), substring2.substring(indexOf2 + 1));
        }
    }

    public class PatternDeserializer extends FromStringDeserializer<Pattern> {
        public PatternDeserializer() {
            super(Pattern.class);
        }

        protected Pattern _deserialize(String str, DeserializationContext deserializationContext) {
            return Pattern.compile(str);
        }
    }

    public class TimeZoneDeserializer extends FromStringDeserializer<TimeZone> {
        public TimeZoneDeserializer() {
            super(TimeZone.class);
        }

        protected TimeZone _deserialize(String str, DeserializationContext deserializationContext) {
            return TimeZone.getTimeZone(str);
        }
    }

    public class URIDeserializer extends FromStringDeserializer<URI> {
        public URIDeserializer() {
            super(URI.class);
        }

        protected URI _deserialize(String str, DeserializationContext deserializationContext) {
            return URI.create(str);
        }
    }

    public class URLDeserializer extends FromStringDeserializer<URL> {
        public URLDeserializer() {
            super(URL.class);
        }

        protected URL _deserialize(String str, DeserializationContext deserializationContext) {
            return new URL(str);
        }
    }

    public class UUIDDeserializer extends FromStringDeserializer<UUID> {
        public UUIDDeserializer() {
            super(UUID.class);
        }

        protected UUID _deserialize(String str, DeserializationContext deserializationContext) {
            return UUID.fromString(str);
        }

        protected UUID _deserializeEmbedded(Object obj, DeserializationContext deserializationContext) {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                if (bArr.length != 16) {
                    deserializationContext.mappingException("Can only construct UUIDs from 16 byte arrays; got " + bArr.length + " bytes");
                }
                DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
                return new UUID(dataInputStream.readLong(), dataInputStream.readLong());
            }
            super._deserializeEmbedded(obj, deserializationContext);
            return null;
        }
    }

    protected FromStringDeserializer(Class<?> cls) {
        super(cls);
    }

    public static Iterable<FromStringDeserializer<?>> all() {
        Iterable arrayList = new ArrayList();
        arrayList.add(new UUIDDeserializer());
        arrayList.add(new URLDeserializer());
        arrayList.add(new URIDeserializer());
        arrayList.add(new CurrencyDeserializer());
        arrayList.add(new PatternDeserializer());
        arrayList.add(new LocaleDeserializer());
        arrayList.add(new InetAddressDeserializer());
        arrayList.add(new TimeZoneDeserializer());
        return arrayList;
    }

    protected abstract T _deserialize(String str, DeserializationContext deserializationContext);

    protected T _deserializeEmbedded(Object obj, DeserializationContext deserializationContext) {
        throw deserializationContext.mappingException("Don't know how to convert embedded Object of type " + obj.getClass().getName() + " into " + this._valueClass.getName());
    }

    public final T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return null;
            }
            try {
                T _deserialize = _deserialize(trim, deserializationContext);
                if (_deserialize != null) {
                    return _deserialize;
                }
            } catch (IllegalArgumentException e) {
            }
            throw deserializationContext.weirdStringException(this._valueClass, "not a valid textual representation");
        } else if (jsonParser.getCurrentToken() == JsonToken.VALUE_EMBEDDED_OBJECT) {
            T embeddedObject = jsonParser.getEmbeddedObject();
            return embeddedObject != null ? this._valueClass.isAssignableFrom(embeddedObject.getClass()) ? embeddedObject : _deserializeEmbedded(embeddedObject, deserializationContext) : null;
        } else {
            throw deserializationContext.mappingException(this._valueClass);
        }
    }
}
