package org.codehaus.jackson.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.FormatSchema;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonGenerator.Feature;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.SerializableString;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.io.SerializedString;

public class JsonGeneratorDelegate extends JsonGenerator {
    protected JsonGenerator delegate;

    public JsonGeneratorDelegate(JsonGenerator jsonGenerator) {
        this.delegate = jsonGenerator;
    }

    public boolean canUseSchema(FormatSchema formatSchema) {
        return this.delegate.canUseSchema(formatSchema);
    }

    public void close() {
        this.delegate.close();
    }

    public void copyCurrentEvent(JsonParser jsonParser) {
        this.delegate.copyCurrentEvent(jsonParser);
    }

    public void copyCurrentStructure(JsonParser jsonParser) {
        this.delegate.copyCurrentStructure(jsonParser);
    }

    public JsonGenerator disable(Feature feature) {
        return this.delegate.disable(feature);
    }

    public JsonGenerator enable(Feature feature) {
        return this.delegate.enable(feature);
    }

    public void flush() {
        this.delegate.flush();
    }

    public ObjectCodec getCodec() {
        return this.delegate.getCodec();
    }

    public JsonStreamContext getOutputContext() {
        return this.delegate.getOutputContext();
    }

    public Object getOutputTarget() {
        return this.delegate.getOutputTarget();
    }

    public boolean isClosed() {
        return this.delegate.isClosed();
    }

    public boolean isEnabled(Feature feature) {
        return this.delegate.isEnabled(feature);
    }

    public JsonGenerator setCodec(ObjectCodec objectCodec) {
        this.delegate.setCodec(objectCodec);
        return this;
    }

    public void setSchema(FormatSchema formatSchema) {
        this.delegate.setSchema(formatSchema);
    }

    public JsonGenerator useDefaultPrettyPrinter() {
        this.delegate.useDefaultPrettyPrinter();
        return this;
    }

    public Version version() {
        return this.delegate.version();
    }

    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) {
        this.delegate.writeBinary(base64Variant, bArr, i, i2);
    }

    public void writeBoolean(boolean z) {
        this.delegate.writeBoolean(z);
    }

    public void writeEndArray() {
        this.delegate.writeEndArray();
    }

    public void writeEndObject() {
        this.delegate.writeEndObject();
    }

    public void writeFieldName(String str) {
        this.delegate.writeFieldName(str);
    }

    public void writeFieldName(SerializableString serializableString) {
        this.delegate.writeFieldName(serializableString);
    }

    public void writeFieldName(SerializedString serializedString) {
        this.delegate.writeFieldName(serializedString);
    }

    public void writeNull() {
        this.delegate.writeNull();
    }

    public void writeNumber(double d) {
        this.delegate.writeNumber(d);
    }

    public void writeNumber(float f) {
        this.delegate.writeNumber(f);
    }

    public void writeNumber(int i) {
        this.delegate.writeNumber(i);
    }

    public void writeNumber(long j) {
        this.delegate.writeNumber(j);
    }

    public void writeNumber(String str) {
        this.delegate.writeNumber(str);
    }

    public void writeNumber(BigDecimal bigDecimal) {
        this.delegate.writeNumber(bigDecimal);
    }

    public void writeNumber(BigInteger bigInteger) {
        this.delegate.writeNumber(bigInteger);
    }

    public void writeObject(Object obj) {
        this.delegate.writeObject(obj);
    }

    public void writeRaw(char c) {
        this.delegate.writeRaw(c);
    }

    public void writeRaw(String str) {
        this.delegate.writeRaw(str);
    }

    public void writeRaw(String str, int i, int i2) {
        this.delegate.writeRaw(str, i, i2);
    }

    public void writeRaw(char[] cArr, int i, int i2) {
        this.delegate.writeRaw(cArr, i, i2);
    }

    public void writeRawUTF8String(byte[] bArr, int i, int i2) {
        this.delegate.writeRawUTF8String(bArr, i, i2);
    }

    public void writeRawValue(String str) {
        this.delegate.writeRawValue(str);
    }

    public void writeRawValue(String str, int i, int i2) {
        this.delegate.writeRawValue(str, i, i2);
    }

    public void writeRawValue(char[] cArr, int i, int i2) {
        this.delegate.writeRawValue(cArr, i, i2);
    }

    public void writeStartArray() {
        this.delegate.writeStartArray();
    }

    public void writeStartObject() {
        this.delegate.writeStartObject();
    }

    public void writeString(String str) {
        this.delegate.writeString(str);
    }

    public void writeString(SerializableString serializableString) {
        this.delegate.writeString(serializableString);
    }

    public void writeString(char[] cArr, int i, int i2) {
        this.delegate.writeString(cArr, i, i2);
    }

    public void writeTree(JsonNode jsonNode) {
        this.delegate.writeTree(jsonNode);
    }

    public void writeUTF8String(byte[] bArr, int i, int i2) {
        this.delegate.writeUTF8String(bArr, i, i2);
    }
}
