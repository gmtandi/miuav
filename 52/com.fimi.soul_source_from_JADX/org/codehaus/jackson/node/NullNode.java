package org.codehaus.jackson.node;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;

public final class NullNode extends ValueNode {
    public static final NullNode instance;

    static {
        instance = new NullNode();
    }

    private NullNode() {
    }

    public static NullNode getInstance() {
        return instance;
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_NULL;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public double getValueAsDouble(double d) {
        return 0.0d;
    }

    public int getValueAsInt(int i) {
        return 0;
    }

    public long getValueAsLong(long j) {
        return 0;
    }

    public String getValueAsText() {
        return "null";
    }

    public boolean isNull() {
        return true;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeNull();
    }
}
