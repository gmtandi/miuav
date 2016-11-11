package org.codehaus.jackson.node;

import java.util.Arrays;
import org.codehaus.jackson.Base64Variants;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;

public final class BinaryNode extends ValueNode {
    static final BinaryNode EMPTY_BINARY_NODE;
    final byte[] _data;

    static {
        EMPTY_BINARY_NODE = new BinaryNode(new byte[0]);
    }

    public BinaryNode(byte[] bArr) {
        this._data = bArr;
    }

    public BinaryNode(byte[] bArr, int i, int i2) {
        if (i == 0 && i2 == bArr.length) {
            this._data = bArr;
            return;
        }
        this._data = new byte[i2];
        System.arraycopy(bArr, i, this._data, 0, i2);
    }

    public static BinaryNode valueOf(byte[] bArr) {
        return bArr == null ? null : bArr.length == 0 ? EMPTY_BINARY_NODE : new BinaryNode(bArr);
    }

    public static BinaryNode valueOf(byte[] bArr, int i, int i2) {
        return bArr == null ? null : i2 == 0 ? EMPTY_BINARY_NODE : new BinaryNode(bArr, i, i2);
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_EMBEDDED_OBJECT;
    }

    public boolean equals(Object obj) {
        return obj == this ? true : (obj == null || obj.getClass() != getClass()) ? false : Arrays.equals(((BinaryNode) obj)._data, this._data);
    }

    public byte[] getBinaryValue() {
        return this._data;
    }

    public String getValueAsText() {
        return Base64Variants.getDefaultVariant().encode(this._data, false);
    }

    public int hashCode() {
        return this._data == null ? -1 : this._data.length;
    }

    public boolean isBinary() {
        return true;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeBinary(this._data);
    }

    public String toString() {
        return Base64Variants.getDefaultVariant().encode(this._data, true);
    }
}
