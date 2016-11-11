package org.codehaus.jackson.impl;

import com.fimi.kernel.p084e.C1186y;
import com.tencent.mm.sdk.platformtools.MAlarmHandler;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.JsonParser.NumberType;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.io.NumberInput;

public abstract class JsonNumericParserBase extends JsonParserBase {
    static final BigDecimal BD_MAX_INT;
    static final BigDecimal BD_MAX_LONG;
    static final BigDecimal BD_MIN_INT;
    static final BigDecimal BD_MIN_LONG;
    protected static final char CHAR_NULL = '\u0000';
    protected static final int INT_0 = 48;
    protected static final int INT_1 = 49;
    protected static final int INT_2 = 50;
    protected static final int INT_3 = 51;
    protected static final int INT_4 = 52;
    protected static final int INT_5 = 53;
    protected static final int INT_6 = 54;
    protected static final int INT_7 = 55;
    protected static final int INT_8 = 56;
    protected static final int INT_9 = 57;
    protected static final int INT_DECIMAL_POINT = 46;
    protected static final int INT_E = 69;
    protected static final int INT_MINUS = 45;
    protected static final int INT_PLUS = 43;
    protected static final int INT_e = 101;
    static final double MAX_INT_D = 2.147483647E9d;
    static final long MAX_INT_L = 2147483647L;
    static final double MAX_LONG_D = 9.223372036854776E18d;
    static final double MIN_INT_D = -2.147483648E9d;
    static final long MIN_INT_L = -2147483648L;
    static final double MIN_LONG_D = -9.223372036854776E18d;
    protected static final int NR_BIGDECIMAL = 16;
    protected static final int NR_BIGINT = 4;
    protected static final int NR_DOUBLE = 8;
    protected static final int NR_INT = 1;
    protected static final int NR_LONG = 2;
    protected static final int NR_UNKNOWN = 0;
    protected int _expLength;
    protected int _fractLength;
    protected int _intLength;
    protected int _numTypesValid;
    protected BigDecimal _numberBigDecimal;
    protected BigInteger _numberBigInt;
    protected double _numberDouble;
    protected int _numberInt;
    protected long _numberLong;
    protected boolean _numberNegative;

    static {
        BD_MIN_LONG = new BigDecimal(Long.MIN_VALUE);
        BD_MAX_LONG = new BigDecimal(MAlarmHandler.NEXT_FIRE_INTERVAL);
        BD_MIN_INT = new BigDecimal(Long.MIN_VALUE);
        BD_MAX_INT = new BigDecimal(MAlarmHandler.NEXT_FIRE_INTERVAL);
    }

    protected JsonNumericParserBase(IOContext iOContext, int i) {
        super(iOContext, i);
        this._numTypesValid = 0;
    }

    private final void _parseSlowFloatValue(int i) {
        if (i == NR_BIGDECIMAL) {
            try {
                this._numberBigDecimal = this._textBuffer.contentsAsDecimal();
                this._numTypesValid = NR_BIGDECIMAL;
                return;
            } catch (Throwable e) {
                _wrapError("Malformed numeric value '" + this._textBuffer.contentsAsString() + "'", e);
                return;
            }
        }
        this._numberDouble = this._textBuffer.contentsAsDouble();
        this._numTypesValid = NR_DOUBLE;
    }

    private final void _parseSlowIntValue(int i, char[] cArr, int i2, int i3) {
        String contentsAsString = this._textBuffer.contentsAsString();
        try {
            if (NumberInput.inLongRange(cArr, i2, i3, this._numberNegative)) {
                this._numberLong = Long.parseLong(contentsAsString);
                this._numTypesValid = NR_LONG;
                return;
            }
            this._numberBigInt = new BigInteger(contentsAsString);
            this._numTypesValid = NR_BIGINT;
        } catch (Throwable e) {
            _wrapError("Malformed numeric value '" + contentsAsString + "'", e);
        }
    }

    protected void _parseNumericValue(int i) {
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            char[] textBuffer = this._textBuffer.getTextBuffer();
            int textOffset = this._textBuffer.getTextOffset();
            int i2 = this._intLength;
            if (this._numberNegative) {
                textOffset += NR_INT;
            }
            if (i2 <= 9) {
                textOffset = NumberInput.parseInt(textBuffer, textOffset, i2);
                if (this._numberNegative) {
                    textOffset = -textOffset;
                }
                this._numberInt = textOffset;
                this._numTypesValid = NR_INT;
            } else if (i2 <= 18) {
                long parseLong = NumberInput.parseLong(textBuffer, textOffset, i2);
                if (this._numberNegative) {
                    parseLong = -parseLong;
                }
                if (i2 == 10) {
                    if (this._numberNegative) {
                        if (parseLong >= MIN_INT_L) {
                            this._numberInt = (int) parseLong;
                            this._numTypesValid = NR_INT;
                            return;
                        }
                    } else if (parseLong <= MAX_INT_L) {
                        this._numberInt = (int) parseLong;
                        this._numTypesValid = NR_INT;
                        return;
                    }
                }
                this._numberLong = parseLong;
                this._numTypesValid = NR_LONG;
            } else {
                _parseSlowIntValue(i, textBuffer, textOffset, i2);
            }
        } else if (this._currToken == JsonToken.VALUE_NUMBER_FLOAT) {
            _parseSlowFloatValue(i);
        } else {
            _reportError("Current token (" + this._currToken + ") not numeric, can not use numeric value accessors");
        }
    }

    protected void convertNumberToBigDecimal() {
        if ((this._numTypesValid & NR_DOUBLE) != 0) {
            this._numberBigDecimal = new BigDecimal(getText());
        } else if ((this._numTypesValid & NR_BIGINT) != 0) {
            this._numberBigDecimal = new BigDecimal(this._numberBigInt);
        } else if ((this._numTypesValid & NR_LONG) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf(this._numberLong);
        } else if ((this._numTypesValid & NR_INT) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf((long) this._numberInt);
        } else {
            _throwInternal();
        }
        this._numTypesValid |= NR_BIGDECIMAL;
    }

    protected void convertNumberToBigInteger() {
        if ((this._numTypesValid & NR_BIGDECIMAL) != 0) {
            this._numberBigInt = this._numberBigDecimal.toBigInteger();
        } else if ((this._numTypesValid & NR_LONG) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberLong);
        } else if ((this._numTypesValid & NR_INT) != 0) {
            this._numberBigInt = BigInteger.valueOf((long) this._numberInt);
        } else if ((this._numTypesValid & NR_DOUBLE) != 0) {
            this._numberBigInt = BigDecimal.valueOf(this._numberDouble).toBigInteger();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= NR_BIGINT;
    }

    protected void convertNumberToDouble() {
        if ((this._numTypesValid & NR_BIGDECIMAL) != 0) {
            this._numberDouble = this._numberBigDecimal.doubleValue();
        } else if ((this._numTypesValid & NR_BIGINT) != 0) {
            this._numberDouble = this._numberBigInt.doubleValue();
        } else if ((this._numTypesValid & NR_LONG) != 0) {
            this._numberDouble = (double) this._numberLong;
        } else if ((this._numTypesValid & NR_INT) != 0) {
            this._numberDouble = (double) this._numberInt;
        } else {
            _throwInternal();
        }
        this._numTypesValid |= NR_DOUBLE;
    }

    protected void convertNumberToInt() {
        if ((this._numTypesValid & NR_LONG) != 0) {
            int i = (int) this._numberLong;
            if (((long) i) != this._numberLong) {
                _reportError("Numeric value (" + getText() + ") out of range of int");
            }
            this._numberInt = i;
        } else if ((this._numTypesValid & NR_BIGINT) != 0) {
            this._numberInt = this._numberBigInt.intValue();
        } else if ((this._numTypesValid & NR_DOUBLE) != 0) {
            if (this._numberDouble < MIN_INT_D || this._numberDouble > MAX_INT_D) {
                reportOverflowInt();
            }
            this._numberInt = (int) this._numberDouble;
        } else if ((this._numTypesValid & NR_BIGDECIMAL) != 0) {
            if (BD_MIN_INT.compareTo(this._numberBigDecimal) > 0 || BD_MAX_INT.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigDecimal.intValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= NR_INT;
    }

    protected void convertNumberToLong() {
        if ((this._numTypesValid & NR_INT) != 0) {
            this._numberLong = (long) this._numberInt;
        } else if ((this._numTypesValid & NR_BIGINT) != 0) {
            this._numberLong = this._numberBigInt.longValue();
        } else if ((this._numTypesValid & NR_DOUBLE) != 0) {
            if (this._numberDouble < MIN_LONG_D || this._numberDouble > MAX_LONG_D) {
                reportOverflowLong();
            }
            this._numberLong = (long) this._numberDouble;
        } else if ((this._numTypesValid & NR_BIGDECIMAL) != 0) {
            if (BD_MIN_LONG.compareTo(this._numberBigDecimal) > 0 || BD_MAX_LONG.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigDecimal.longValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= NR_LONG;
    }

    public BigInteger getBigIntegerValue() {
        if ((this._numTypesValid & NR_BIGINT) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(NR_BIGINT);
            }
            if ((this._numTypesValid & NR_BIGINT) == 0) {
                convertNumberToBigInteger();
            }
        }
        return this._numberBigInt;
    }

    public BigDecimal getDecimalValue() {
        if ((this._numTypesValid & NR_BIGDECIMAL) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(NR_BIGDECIMAL);
            }
            if ((this._numTypesValid & NR_BIGDECIMAL) == 0) {
                convertNumberToBigDecimal();
            }
        }
        return this._numberBigDecimal;
    }

    public double getDoubleValue() {
        if ((this._numTypesValid & NR_DOUBLE) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(NR_DOUBLE);
            }
            if ((this._numTypesValid & NR_DOUBLE) == 0) {
                convertNumberToDouble();
            }
        }
        return this._numberDouble;
    }

    public float getFloatValue() {
        return (float) getDoubleValue();
    }

    public int getIntValue() {
        if ((this._numTypesValid & NR_INT) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(NR_INT);
            }
            if ((this._numTypesValid & NR_INT) == 0) {
                convertNumberToInt();
            }
        }
        return this._numberInt;
    }

    public long getLongValue() {
        if ((this._numTypesValid & NR_LONG) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(NR_LONG);
            }
            if ((this._numTypesValid & NR_LONG) == 0) {
                convertNumberToLong();
            }
        }
        return this._numberLong;
    }

    public NumberType getNumberType() {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        return this._currToken == JsonToken.VALUE_NUMBER_INT ? (this._numTypesValid & NR_INT) != 0 ? NumberType.INT : (this._numTypesValid & NR_LONG) != 0 ? NumberType.LONG : NumberType.BIG_INTEGER : (this._numTypesValid & NR_BIGDECIMAL) != 0 ? NumberType.BIG_DECIMAL : NumberType.DOUBLE;
    }

    public Number getNumberValue() {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            return (this._numTypesValid & NR_INT) != 0 ? Integer.valueOf(this._numberInt) : (this._numTypesValid & NR_LONG) != 0 ? Long.valueOf(this._numberLong) : (this._numTypesValid & NR_BIGINT) != 0 ? this._numberBigInt : this._numberBigDecimal;
        } else {
            if ((this._numTypesValid & NR_BIGDECIMAL) != 0) {
                return this._numberBigDecimal;
            }
            if ((this._numTypesValid & NR_DOUBLE) == 0) {
                _throwInternal();
            }
            return Double.valueOf(this._numberDouble);
        }
    }

    protected void reportInvalidNumber(String str) {
        _reportError("Invalid numeric value: " + str);
    }

    protected void reportOverflowInt() {
        _reportError("Numeric value (" + getText() + ") out of range of int (" + C1186y.f5353a + " - " + Integer.MAX_VALUE + ")");
    }

    protected void reportOverflowLong() {
        _reportError("Numeric value (" + getText() + ") out of range of long (" + Long.MIN_VALUE + " - " + MAlarmHandler.NEXT_FIRE_INTERVAL + ")");
    }

    protected void reportUnexpectedNumberChar(int i, String str) {
        String str2 = "Unexpected character (" + JsonParserMinimalBase._getCharDesc(i) + ") in numeric value";
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        _reportError(str2);
    }

    protected final JsonToken reset(boolean z, int i, int i2, int i3) {
        return (i2 >= NR_INT || i3 >= NR_INT) ? resetFloat(z, i, i2, i3) : resetInt(z, i);
    }

    protected final JsonToken resetAsNaN(String str, double d) {
        this._textBuffer.resetWithString(str);
        this._numberDouble = d;
        this._numTypesValid = NR_DOUBLE;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    protected final JsonToken resetFloat(boolean z, int i, int i2, int i3) {
        this._numberNegative = z;
        this._intLength = i;
        this._fractLength = i2;
        this._expLength = i3;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    protected final JsonToken resetInt(boolean z, int i) {
        this._numberNegative = z;
        this._intLength = i;
        this._fractLength = 0;
        this._expLength = 0;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_INT;
    }
}
