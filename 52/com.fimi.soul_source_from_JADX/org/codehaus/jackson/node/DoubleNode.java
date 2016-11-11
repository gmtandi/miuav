package org.codehaus.jackson.node;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser.NumberType;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.io.NumberOutput;
import org.codehaus.jackson.map.SerializerProvider;

public final class DoubleNode extends NumericNode {
    protected final double _value;

    public DoubleNode(double d) {
        this._value = d;
    }

    public static DoubleNode valueOf(double d) {
        return new DoubleNode(d);
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public boolean equals(Object obj) {
        return obj == this ? true : obj == null ? false : obj.getClass() != getClass() ? false : ((DoubleNode) obj)._value == this._value;
    }

    public BigInteger getBigIntegerValue() {
        return getDecimalValue().toBigInteger();
    }

    public BigDecimal getDecimalValue() {
        return BigDecimal.valueOf(this._value);
    }

    public double getDoubleValue() {
        return this._value;
    }

    public int getIntValue() {
        return (int) this._value;
    }

    public long getLongValue() {
        return (long) this._value;
    }

    public NumberType getNumberType() {
        return NumberType.DOUBLE;
    }

    public Number getNumberValue() {
        return Double.valueOf(this._value);
    }

    public String getValueAsText() {
        return NumberOutput.toString(this._value);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this._value);
        return ((int) (doubleToLongBits >> 32)) ^ ((int) doubleToLongBits);
    }

    public boolean isDouble() {
        return true;
    }

    public boolean isFloatingPointNumber() {
        return true;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeNumber(this._value);
    }
}
