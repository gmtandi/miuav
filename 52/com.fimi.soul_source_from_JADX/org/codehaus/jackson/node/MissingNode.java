package org.codehaus.jackson.node;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.p122a.p123a.C2915a;

public final class MissingNode extends BaseJsonNode {
    private static final MissingNode instance;

    static {
        instance = new MissingNode();
    }

    private MissingNode() {
    }

    public static MissingNode getInstance() {
        return instance;
    }

    public JsonToken asToken() {
        return JsonToken.NOT_AVAILABLE;
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
        return null;
    }

    public boolean isMissingNode() {
        return true;
    }

    public JsonNode path(int i) {
        return this;
    }

    public JsonNode path(String str) {
        return this;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeNull();
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        jsonGenerator.writeNull();
    }

    public String toString() {
        return C2915a.f14760f;
    }
}
