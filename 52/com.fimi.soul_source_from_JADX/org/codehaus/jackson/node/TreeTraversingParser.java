package org.codehaus.jackson.node;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonParser.NumberType;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.impl.JsonParserMinimalBase;
import org.codehaus.jackson.node.NodeCursor.Array;
import org.codehaus.jackson.node.NodeCursor.Object;
import org.codehaus.jackson.node.NodeCursor.RootValue;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class TreeTraversingParser extends JsonParserMinimalBase {
    protected boolean _closed;
    protected JsonToken _nextToken;
    protected NodeCursor _nodeCursor;
    protected ObjectCodec _objectCodec;
    protected boolean _startContainer;

    /* renamed from: org.codehaus.jackson.node.TreeTraversingParser.1 */
    /* synthetic */ class C35921 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonToken;

        static {
            $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public TreeTraversingParser(JsonNode jsonNode) {
        this(jsonNode, null);
    }

    public TreeTraversingParser(JsonNode jsonNode, ObjectCodec objectCodec) {
        super(0);
        this._objectCodec = objectCodec;
        if (jsonNode.isArray()) {
            this._nextToken = JsonToken.START_ARRAY;
            this._nodeCursor = new Array(jsonNode, null);
        } else if (jsonNode.isObject()) {
            this._nextToken = JsonToken.START_OBJECT;
            this._nodeCursor = new Object(jsonNode, null);
        } else {
            this._nodeCursor = new RootValue(jsonNode, null);
        }
    }

    protected void _handleEOF() {
        _throwInternal();
    }

    public void close() {
        if (!this._closed) {
            this._closed = true;
            this._nodeCursor = null;
            this._currToken = null;
        }
    }

    protected JsonNode currentNode() {
        return (this._closed || this._nodeCursor == null) ? null : this._nodeCursor.currentNode();
    }

    protected JsonNode currentNumericNode() {
        JsonNode currentNode = currentNode();
        if (currentNode != null && currentNode.isNumber()) {
            return currentNode;
        }
        throw _constructError("Current token (" + (currentNode == null ? null : currentNode.asToken()) + ") not numeric, can not use numeric value accessors");
    }

    public BigInteger getBigIntegerValue() {
        return currentNumericNode().getBigIntegerValue();
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) {
        JsonNode currentNode = currentNode();
        if (currentNode != null) {
            byte[] binaryValue = currentNode.getBinaryValue();
            if (binaryValue != null) {
                return binaryValue;
            }
            if (currentNode.isPojo()) {
                Object pojo = ((POJONode) currentNode).getPojo();
                if (pojo instanceof byte[]) {
                    return (byte[]) pojo;
                }
            }
        }
        return null;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public JsonLocation getCurrentLocation() {
        return JsonLocation.NA;
    }

    public String getCurrentName() {
        return this._nodeCursor == null ? null : this._nodeCursor.getCurrentName();
    }

    public BigDecimal getDecimalValue() {
        return currentNumericNode().getDecimalValue();
    }

    public double getDoubleValue() {
        return currentNumericNode().getDoubleValue();
    }

    public Object getEmbeddedObject() {
        if (!this._closed) {
            JsonNode currentNode = currentNode();
            if (currentNode != null && currentNode.isPojo()) {
                return ((POJONode) currentNode).getPojo();
            }
        }
        return null;
    }

    public float getFloatValue() {
        return (float) currentNumericNode().getDoubleValue();
    }

    public int getIntValue() {
        return currentNumericNode().getIntValue();
    }

    public long getLongValue() {
        return currentNumericNode().getLongValue();
    }

    public NumberType getNumberType() {
        JsonNode currentNumericNode = currentNumericNode();
        return currentNumericNode == null ? null : currentNumericNode.getNumberType();
    }

    public Number getNumberValue() {
        return currentNumericNode().getNumberValue();
    }

    public JsonStreamContext getParsingContext() {
        return this._nodeCursor;
    }

    public String getText() {
        if (this._closed) {
            return null;
        }
        switch (C35921.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return this._nodeCursor.getCurrentName();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return currentNode().getTextValue();
            case Type.BYTE /*3*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return String.valueOf(currentNode().getNumberValue());
            case Type.INT /*5*/:
                JsonNode currentNode = currentNode();
                if (currentNode != null && currentNode.isBinary()) {
                    return currentNode.getValueAsText();
                }
        }
        return this._currToken != null ? this._currToken.asString() : null;
    }

    public char[] getTextCharacters() {
        return getText().toCharArray();
    }

    public int getTextLength() {
        return getText().length();
    }

    public int getTextOffset() {
        return 0;
    }

    public JsonLocation getTokenLocation() {
        return JsonLocation.NA;
    }

    public boolean hasTextCharacters() {
        return false;
    }

    public boolean isClosed() {
        return this._closed;
    }

    public JsonToken nextToken() {
        if (this._nextToken != null) {
            this._currToken = this._nextToken;
            this._nextToken = null;
            return this._currToken;
        } else if (this._startContainer) {
            this._startContainer = false;
            if (this._nodeCursor.currentHasChildren()) {
                this._nodeCursor = this._nodeCursor.iterateChildren();
                this._currToken = this._nodeCursor.nextToken();
                if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                    this._startContainer = true;
                }
                return this._currToken;
            }
            this._currToken = this._currToken == JsonToken.START_OBJECT ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            return this._currToken;
        } else if (this._nodeCursor == null) {
            this._closed = true;
            return null;
        } else {
            this._currToken = this._nodeCursor.nextToken();
            if (this._currToken != null) {
                if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                    this._startContainer = true;
                }
                return this._currToken;
            }
            this._currToken = this._nodeCursor.endToken();
            this._nodeCursor = this._nodeCursor.getParent();
            return this._currToken;
        }
    }

    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    public JsonParser skipChildren() {
        if (this._currToken == JsonToken.START_OBJECT) {
            this._startContainer = false;
            this._currToken = JsonToken.END_OBJECT;
        } else if (this._currToken == JsonToken.START_ARRAY) {
            this._startContainer = false;
            this._currToken = JsonToken.END_ARRAY;
        }
        return this;
    }
}
