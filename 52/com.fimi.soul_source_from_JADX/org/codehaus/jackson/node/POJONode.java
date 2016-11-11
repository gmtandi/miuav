package org.codehaus.jackson.node;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;

public final class POJONode extends ValueNode {
    protected final Object _value;

    public POJONode(Object obj) {
        this._value = obj;
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_EMBEDDED_OBJECT;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        POJONode pOJONode = (POJONode) obj;
        return this._value == null ? pOJONode._value == null : this._value.equals(pOJONode._value);
    }

    public Object getPojo() {
        return this._value;
    }

    public boolean getValueAsBoolean(boolean z) {
        return (this._value == null || !(this._value instanceof Boolean)) ? z : ((Boolean) this._value).booleanValue();
    }

    public double getValueAsDouble(double d) {
        return this._value instanceof Number ? ((Number) this._value).doubleValue() : d;
    }

    public int getValueAsInt(int i) {
        return this._value instanceof Number ? ((Number) this._value).intValue() : i;
    }

    public long getValueAsLong(long j) {
        return this._value instanceof Number ? ((Number) this._value).longValue() : j;
    }

    public String getValueAsText() {
        return this._value == null ? "null" : this._value.toString();
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    public boolean isPojo() {
        return true;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._value == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeObject(this._value);
        }
    }

    public String toString() {
        return String.valueOf(this._value);
    }
}
