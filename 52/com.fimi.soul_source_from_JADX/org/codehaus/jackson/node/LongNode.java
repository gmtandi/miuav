package org.codehaus.jackson.node;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser.NumberType;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.io.NumberOutput;
import org.codehaus.jackson.map.SerializerProvider;

public final class LongNode extends NumericNode {
    final long _value;

    public LongNode(long j) {
        this._value = j;
    }

    public static LongNode valueOf(long j) {
        return new LongNode(j);
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_INT;
    }

    public boolean equals(Object obj) {
        return obj == this ? true : obj == null ? false : obj.getClass() != getClass() ? false : ((LongNode) obj)._value == this._value;
    }

    public BigInteger getBigIntegerValue() {
        return BigInteger.valueOf(this._value);
    }

    public BigDecimal getDecimalValue() {
        return BigDecimal.valueOf(this._value);
    }

    public double getDoubleValue() {
        return (double) this._value;
    }

    public int getIntValue() {
        return (int) this._value;
    }

    public long getLongValue() {
        return this._value;
    }

    public NumberType getNumberType() {
        return NumberType.LONG;
    }

    public Number getNumberValue() {
        return Long.valueOf(this._value);
    }

    public boolean getValueAsBoolean(boolean z) {
        return this._value != 0;
    }

    public String getValueAsText() {
        return NumberOutput.toString(this._value);
    }

    public int hashCode() {
        return ((int) this._value) ^ ((int) (this._value >> 32));
    }

    public boolean isIntegralNumber() {
        return true;
    }

    public boolean isLong() {
        return true;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeNumber(this._value);
    }
}
