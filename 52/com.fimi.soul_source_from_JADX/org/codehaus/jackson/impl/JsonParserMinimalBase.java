package org.codehaus.jackson.impl;

import com.amap.api.maps.model.WeightedLatLng;
import com.tencent.mm.sdk.platformtools.Util;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.io.NumberInput;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public abstract class JsonParserMinimalBase extends JsonParser {
    protected static final int INT_APOSTROPHE = 39;
    protected static final int INT_ASTERISK = 42;
    protected static final int INT_BACKSLASH = 92;
    protected static final int INT_COLON = 58;
    protected static final int INT_COMMA = 44;
    protected static final int INT_CR = 13;
    protected static final int INT_LBRACKET = 91;
    protected static final int INT_LCURLY = 123;
    protected static final int INT_LF = 10;
    protected static final int INT_QUOTE = 34;
    protected static final int INT_RBRACKET = 93;
    protected static final int INT_RCURLY = 125;
    protected static final int INT_SLASH = 47;
    protected static final int INT_SPACE = 32;
    protected static final int INT_TAB = 9;
    protected static final int INT_b = 98;
    protected static final int INT_f = 102;
    protected static final int INT_n = 110;
    protected static final int INT_r = 114;
    protected static final int INT_t = 116;
    protected static final int INT_u = 117;

    /* renamed from: org.codehaus.jackson.impl.JsonParserMinimalBase.1 */
    /* synthetic */ class C35761 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonToken;

        static {
            $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_ARRAY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = JsonParserMinimalBase.INT_TAB;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = JsonParserMinimalBase.INT_LF;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    protected JsonParserMinimalBase() {
    }

    protected JsonParserMinimalBase(int i) {
        super(i);
    }

    protected static final String _getCharDesc(int i) {
        char c = (char) i;
        return Character.isISOControl(c) ? "(CTRL-CHAR, code " + i + ")" : i > Util.MASK_8BIT ? "'" + c + "' (code " + i + " / 0x" + Integer.toHexString(i) + ")" : "'" + c + "' (code " + i + ")";
    }

    protected final JsonParseException _constructError(String str, Throwable th) {
        return new JsonParseException(str, getCurrentLocation(), th);
    }

    protected abstract void _handleEOF();

    protected char _handleUnrecognizedCharacterEscape(char c) {
        if (!(isEnabled(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER) || (c == '\'' && isEnabled(Feature.ALLOW_SINGLE_QUOTES)))) {
            _reportError("Unrecognized character escape " + _getCharDesc(c));
        }
        return c;
    }

    protected final void _reportError(String str) {
        throw _constructError(str);
    }

    protected void _reportInvalidEOF() {
        _reportInvalidEOF(" in " + this._currToken);
    }

    protected void _reportInvalidEOF(String str) {
        _reportError("Unexpected end-of-input" + str);
    }

    protected void _reportInvalidEOFInValue() {
        _reportInvalidEOF(" in a value");
    }

    protected void _reportUnexpectedChar(int i, String str) {
        String str2 = "Unexpected character (" + _getCharDesc(i) + ")";
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        _reportError(str2);
    }

    protected final void _throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }

    protected void _throwInvalidSpace(int i) {
        _reportError("Illegal character (" + _getCharDesc((char) i) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
    }

    protected void _throwUnquotedSpace(int i, String str) {
        if (!isEnabled(Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || i >= INT_SPACE) {
            _reportError("Illegal unquoted character (" + _getCharDesc((char) i) + "): has to be escaped using backslash to be included in " + str);
        }
    }

    protected final void _wrapError(String str, Throwable th) {
        throw _constructError(str, th);
    }

    public abstract void close();

    public abstract byte[] getBinaryValue(Base64Variant base64Variant);

    public abstract String getCurrentName();

    public abstract JsonStreamContext getParsingContext();

    public abstract String getText();

    public abstract char[] getTextCharacters();

    public abstract int getTextLength();

    public abstract int getTextOffset();

    public boolean getValueAsBoolean(boolean z) {
        if (this._currToken != null) {
            switch (C35761.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
                case Type.INT /*5*/:
                    return getIntValue() != 0;
                case Type.FLOAT /*6*/:
                    return true;
                case Type.LONG /*7*/:
                case Type.DOUBLE /*8*/:
                    return false;
                case INT_TAB /*9*/:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Boolean) {
                        return ((Boolean) embeddedObject).booleanValue();
                    }
                    break;
                case INT_LF /*10*/:
                    break;
            }
            if ("true".equals(getText().trim())) {
                return true;
            }
        }
        return z;
    }

    public double getValueAsDouble(double d) {
        if (this._currToken == null) {
            return d;
        }
        switch (C35761.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
            case Type.INT /*5*/:
            case Opcodes.T_LONG /*11*/:
                return getDoubleValue();
            case Type.FLOAT /*6*/:
                return WeightedLatLng.DEFAULT_INTENSITY;
            case Type.LONG /*7*/:
            case Type.DOUBLE /*8*/:
                return 0.0d;
            case INT_TAB /*9*/:
                Object embeddedObject = getEmbeddedObject();
                return embeddedObject instanceof Number ? ((Number) embeddedObject).doubleValue() : d;
            case INT_LF /*10*/:
                return NumberInput.parseAsDouble(getText(), d);
            default:
                return d;
        }
    }

    public int getValueAsInt(int i) {
        if (this._currToken == null) {
            return i;
        }
        switch (C35761.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
            case Type.INT /*5*/:
            case Opcodes.T_LONG /*11*/:
                return getIntValue();
            case Type.FLOAT /*6*/:
                return 1;
            case Type.LONG /*7*/:
            case Type.DOUBLE /*8*/:
                return 0;
            case INT_TAB /*9*/:
                Object embeddedObject = getEmbeddedObject();
                return embeddedObject instanceof Number ? ((Number) embeddedObject).intValue() : i;
            case INT_LF /*10*/:
                return NumberInput.parseAsInt(getText(), i);
            default:
                return i;
        }
    }

    public long getValueAsLong(long j) {
        if (this._currToken == null) {
            return j;
        }
        switch (C35761.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
            case Type.INT /*5*/:
            case Opcodes.T_LONG /*11*/:
                return getLongValue();
            case Type.FLOAT /*6*/:
                return 1;
            case Type.LONG /*7*/:
            case Type.DOUBLE /*8*/:
                return 0;
            case INT_TAB /*9*/:
                Object embeddedObject = getEmbeddedObject();
                return embeddedObject instanceof Number ? ((Number) embeddedObject).longValue() : j;
            case INT_LF /*10*/:
                return NumberInput.parseAsLong(getText(), j);
            default:
                return j;
        }
    }

    public abstract boolean hasTextCharacters();

    public abstract boolean isClosed();

    public abstract JsonToken nextToken();

    public JsonParser skipChildren() {
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            int i = 1;
            while (true) {
                JsonToken nextToken = nextToken();
                if (nextToken == null) {
                    _handleEOF();
                } else {
                    switch (C35761.$SwitchMap$org$codehaus$jackson$JsonToken[nextToken.ordinal()]) {
                        case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                            i++;
                            continue;
                        case Type.BYTE /*3*/:
                        case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                            i--;
                            if (i == 0) {
                                break;
                            }
                            continue;
                        default:
                            continue;
                    }
                }
            }
        }
        return this;
    }
}
