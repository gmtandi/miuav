package org.codehaus.jackson.node;

import com.amap.api.maps.model.WeightedLatLng;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;

public final class BooleanNode extends ValueNode {
    public static final BooleanNode FALSE;
    public static final BooleanNode TRUE;

    static {
        TRUE = new BooleanNode();
        FALSE = new BooleanNode();
    }

    private BooleanNode() {
    }

    public static BooleanNode getFalse() {
        return FALSE;
    }

    public static BooleanNode getTrue() {
        return TRUE;
    }

    public static BooleanNode valueOf(boolean z) {
        return z ? TRUE : FALSE;
    }

    public JsonToken asToken() {
        return this == TRUE ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public boolean getBooleanValue() {
        return this == TRUE;
    }

    public boolean getValueAsBoolean() {
        return this == TRUE;
    }

    public boolean getValueAsBoolean(boolean z) {
        return this == TRUE;
    }

    public double getValueAsDouble(double d) {
        return this == TRUE ? WeightedLatLng.DEFAULT_INTENSITY : 0.0d;
    }

    public int getValueAsInt(int i) {
        return this == TRUE ? 1 : 0;
    }

    public long getValueAsLong(long j) {
        return this == TRUE ? 1 : 0;
    }

    public String getValueAsText() {
        return this == TRUE ? "true" : "false";
    }

    public boolean isBoolean() {
        return true;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeBoolean(this == TRUE);
    }
}
