package org.codehaus.jackson.map.deser;

import java.sql.Timestamp;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;

public class TimestampDeserializer extends StdScalarDeserializer<Timestamp> {
    public TimestampDeserializer() {
        super(Timestamp.class);
    }

    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return new Timestamp(_parseDate(jsonParser, deserializationContext).getTime());
    }
}
