package org.codehaus.jackson.node;

import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonNodeFactory {
    public static final JsonNodeFactory instance;

    static {
        instance = new JsonNodeFactory();
    }

    protected JsonNodeFactory() {
    }

    public POJONode POJONode(Object obj) {
        return new POJONode(obj);
    }

    public ArrayNode arrayNode() {
        return new ArrayNode(this);
    }

    public BinaryNode binaryNode(byte[] bArr) {
        return BinaryNode.valueOf(bArr);
    }

    public BinaryNode binaryNode(byte[] bArr, int i, int i2) {
        return BinaryNode.valueOf(bArr, i, i2);
    }

    public BooleanNode booleanNode(boolean z) {
        return z ? BooleanNode.getTrue() : BooleanNode.getFalse();
    }

    public NullNode nullNode() {
        return NullNode.getInstance();
    }

    public NumericNode numberNode(byte b) {
        return IntNode.valueOf(b);
    }

    public NumericNode numberNode(double d) {
        return DoubleNode.valueOf(d);
    }

    public NumericNode numberNode(float f) {
        return DoubleNode.valueOf((double) f);
    }

    public NumericNode numberNode(int i) {
        return IntNode.valueOf(i);
    }

    public NumericNode numberNode(long j) {
        return LongNode.valueOf(j);
    }

    public NumericNode numberNode(BigDecimal bigDecimal) {
        return DecimalNode.valueOf(bigDecimal);
    }

    public NumericNode numberNode(BigInteger bigInteger) {
        return BigIntegerNode.valueOf(bigInteger);
    }

    public NumericNode numberNode(short s) {
        return IntNode.valueOf(s);
    }

    public ObjectNode objectNode() {
        return new ObjectNode(this);
    }

    public TextNode textNode(String str) {
        return TextNode.valueOf(str);
    }
}
