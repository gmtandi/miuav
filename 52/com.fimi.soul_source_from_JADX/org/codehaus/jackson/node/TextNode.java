package org.codehaus.jackson.node;

import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.Base64Variants;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.io.NumberInput;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.CharTypes;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p124f.p125c.C3022o;

public final class TextNode extends ValueNode {
    static final TextNode EMPTY_STRING_NODE;
    static final int INT_SPACE = 32;
    final String _value;

    static {
        EMPTY_STRING_NODE = new TextNode(C2915a.f14760f);
    }

    public TextNode(String str) {
        this._value = str;
    }

    protected static void appendQuoted(StringBuilder stringBuilder, String str) {
        stringBuilder.append(C3022o.f15057e);
        CharTypes.appendQuoted(stringBuilder, str);
        stringBuilder.append(C3022o.f15057e);
    }

    public static TextNode valueOf(String str) {
        return str == null ? null : str.length() == 0 ? EMPTY_STRING_NODE : new TextNode(str);
    }

    protected void _reportBase64EOF() {
        throw new JsonParseException("Unexpected end-of-String when base64 content", JsonLocation.NA);
    }

    protected void _reportInvalidBase64(Base64Variant base64Variant, char c, int i) {
        _reportInvalidBase64(base64Variant, c, i, null);
    }

    protected void _reportInvalidBase64(Base64Variant base64Variant, char c, int i, String str) {
        r0 = c <= C3022o.f15055c ? "Illegal white space character (code 0x" + Integer.toHexString(c) + ") as character #" + (i + 1) + " of 4-char base64 unit: can only used between units" : base64Variant.usesPaddingChar(c) ? "Unexpected padding character ('" + base64Variant.getPaddingChar() + "') as character #" + (i + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character" : (!Character.isDefined(c) || Character.isISOControl(c)) ? "Illegal character (code 0x" + Integer.toHexString(c) + ") in base64 content" : "Illegal character '" + c + "' (code 0x" + Integer.toHexString(c) + ") in base64 content";
        if (str != null) {
            r0 = r0 + ": " + str;
        }
        throw new JsonParseException(r0, JsonLocation.NA);
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_STRING;
    }

    public boolean equals(Object obj) {
        return obj == this ? true : (obj == null || obj.getClass() != getClass()) ? false : ((TextNode) obj)._value.equals(this._value);
    }

    public byte[] getBinaryValue() {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) {
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder(100);
        String str = this._value;
        int length = str.length();
        int i = 0;
        loop0:
        while (i < length) {
            int i2;
            char charAt;
            while (true) {
                i2 = i + 1;
                charAt = str.charAt(i);
                if (i2 >= length) {
                    break loop0;
                } else if (charAt > C3022o.f15055c) {
                    break;
                } else {
                    i = i2;
                }
            }
            int decodeBase64Char = base64Variant.decodeBase64Char(charAt);
            if (decodeBase64Char < 0) {
                _reportInvalidBase64(base64Variant, charAt, 0);
            }
            if (i2 >= length) {
                _reportBase64EOF();
            }
            i = i2 + 1;
            char charAt2 = str.charAt(i2);
            int decodeBase64Char2 = base64Variant.decodeBase64Char(charAt2);
            if (decodeBase64Char2 < 0) {
                _reportInvalidBase64(base64Variant, charAt2, 1);
            }
            i2 = (decodeBase64Char << 6) | decodeBase64Char2;
            if (i >= length) {
                if (!base64Variant.usesPadding()) {
                    byteArrayBuilder.append(i2 >> 4);
                    break;
                }
                _reportBase64EOF();
            }
            decodeBase64Char = i + 1;
            charAt = str.charAt(i);
            decodeBase64Char2 = base64Variant.decodeBase64Char(charAt);
            char charAt3;
            if (decodeBase64Char2 < 0) {
                if (decodeBase64Char2 != -2) {
                    _reportInvalidBase64(base64Variant, charAt, 2);
                }
                if (decodeBase64Char >= length) {
                    _reportBase64EOF();
                }
                i = decodeBase64Char + 1;
                charAt3 = str.charAt(decodeBase64Char);
                if (!base64Variant.usesPaddingChar(charAt3)) {
                    _reportInvalidBase64(base64Variant, charAt3, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                }
                byteArrayBuilder.append(i2 >> 4);
            } else {
                i2 = (i2 << 6) | decodeBase64Char2;
                if (decodeBase64Char >= length) {
                    if (!base64Variant.usesPadding()) {
                        byteArrayBuilder.appendTwoBytes(i2 >> 2);
                        break;
                    }
                    _reportBase64EOF();
                }
                i = decodeBase64Char + 1;
                charAt3 = str.charAt(decodeBase64Char);
                decodeBase64Char2 = base64Variant.decodeBase64Char(charAt3);
                if (decodeBase64Char2 < 0) {
                    if (decodeBase64Char2 != -2) {
                        _reportInvalidBase64(base64Variant, charAt3, 3);
                    }
                    byteArrayBuilder.appendTwoBytes(i2 >> 2);
                } else {
                    byteArrayBuilder.appendThreeBytes((i2 << 6) | decodeBase64Char2);
                }
            }
        }
        return byteArrayBuilder.toByteArray();
    }

    public String getTextValue() {
        return this._value;
    }

    public boolean getValueAsBoolean(boolean z) {
        return (this._value == null || !"true".equals(this._value.trim())) ? z : true;
    }

    public double getValueAsDouble(double d) {
        return NumberInput.parseAsDouble(this._value, d);
    }

    public int getValueAsInt(int i) {
        return NumberInput.parseAsInt(this._value, i);
    }

    public long getValueAsLong(long j) {
        return NumberInput.parseAsLong(this._value, j);
    }

    public String getValueAsText() {
        return this._value;
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    public boolean isTextual() {
        return true;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._value == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(this._value);
        }
    }

    public String toString() {
        int length = this._value.length();
        StringBuilder stringBuilder = new StringBuilder((length >> 4) + (length + 2));
        appendQuoted(stringBuilder, this._value);
        return stringBuilder.toString();
    }
}
