package org.codehaus.jackson.node;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser.NumberType;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;

public final class DecimalNode extends NumericNode {
    protected final BigDecimal _value;

    public DecimalNode(BigDecimal bigDecimal) {
        this._value = bigDecimal;
    }

    public static DecimalNode valueOf(BigDecimal bigDecimal) {
        return new DecimalNode(bigDecimal);
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public boolean equals(Object obj) {
        return obj == this ? true : (obj == null || obj.getClass() != getClass()) ? false : ((DecimalNode) obj)._value.equals(this._value);
    }

    public BigInteger getBigIntegerValue() {
        return this._value.toBigInteger();
    }

    public BigDecimal getDecimalValue() {
        return this._value;
    }

    public double getDoubleValue() {
        return this._value.doubleValue();
    }

    public int getIntValue() {
        return this._value.intValue();
    }

    public long getLongValue() {
        return this._value.longValue();
    }

    public NumberType getNumberType() {
        return NumberType.BIG_DECIMAL;
    }

    public Number getNumberValue() {
        return this._value;
    }

    public String getValueAsText() {
        return this._value.toString();
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    public boolean isBigDecimal() {
        return true;
    }

    public boolean isFloatingPointNumber() {
        return true;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeNumber(this._value);
    }
}
