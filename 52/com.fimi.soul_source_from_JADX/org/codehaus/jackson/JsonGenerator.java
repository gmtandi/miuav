package org.codehaus.jackson;

import java.io.Closeable;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.io.CharacterEscapes;
import org.codehaus.jackson.io.SerializedString;

public abstract class JsonGenerator implements Closeable, Versioned {
    protected PrettyPrinter _cfgPrettyPrinter;

    public enum Feature {
        AUTO_CLOSE_TARGET(true),
        AUTO_CLOSE_JSON_CONTENT(true),
        QUOTE_FIELD_NAMES(true),
        QUOTE_NON_NUMERIC_NUMBERS(true),
        WRITE_NUMBERS_AS_STRINGS(false),
        FLUSH_PASSED_TO_STREAM(true),
        ESCAPE_NON_ASCII(false);
        
        final boolean _defaultState;
        final int _mask;

        private Feature(boolean z) {
            this._defaultState = z;
            this._mask = 1 << ordinal();
        }

        public static int collectDefaults() {
            int i = 0;
            for (Feature feature : values()) {
                if (feature.enabledByDefault()) {
                    i |= feature.getMask();
                }
            }
            return i;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public int getMask() {
            return this._mask;
        }
    }

    protected JsonGenerator() {
    }

    public boolean canUseSchema(FormatSchema formatSchema) {
        return false;
    }

    public abstract void close();

    public JsonGenerator configure(Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public abstract void copyCurrentEvent(JsonParser jsonParser);

    public abstract void copyCurrentStructure(JsonParser jsonParser);

    public abstract JsonGenerator disable(Feature feature);

    @Deprecated
    public void disableFeature(Feature feature) {
        disable(feature);
    }

    public abstract JsonGenerator enable(Feature feature);

    @Deprecated
    public void enableFeature(Feature feature) {
        enable(feature);
    }

    public abstract void flush();

    public CharacterEscapes getCharacterEscapes() {
        return null;
    }

    public abstract ObjectCodec getCodec();

    public int getHighestEscapedChar() {
        return 0;
    }

    public abstract JsonStreamContext getOutputContext();

    public Object getOutputTarget() {
        return null;
    }

    public abstract boolean isClosed();

    public abstract boolean isEnabled(Feature feature);

    @Deprecated
    public boolean isFeatureEnabled(Feature feature) {
        return isEnabled(feature);
    }

    public JsonGenerator setCharacterEscapes(CharacterEscapes characterEscapes) {
        return this;
    }

    public abstract JsonGenerator setCodec(ObjectCodec objectCodec);

    @Deprecated
    public void setFeature(Feature feature, boolean z) {
        configure(feature, z);
    }

    public JsonGenerator setHighestNonEscapedChar(int i) {
        return this;
    }

    public JsonGenerator setPrettyPrinter(PrettyPrinter prettyPrinter) {
        this._cfgPrettyPrinter = prettyPrinter;
        return this;
    }

    public void setSchema(FormatSchema formatSchema) {
        throw new UnsupportedOperationException("Generator of type " + getClass().getName() + " does not support schema of type '" + formatSchema.getSchemaType() + "'");
    }

    public abstract JsonGenerator useDefaultPrettyPrinter();

    public Version version() {
        return Version.unknownVersion();
    }

    public final void writeArrayFieldStart(String str) {
        writeFieldName(str);
        writeStartArray();
    }

    public abstract void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2);

    public void writeBinary(byte[] bArr) {
        writeBinary(Base64Variants.getDefaultVariant(), bArr, 0, bArr.length);
    }

    public void writeBinary(byte[] bArr, int i, int i2) {
        writeBinary(Base64Variants.getDefaultVariant(), bArr, i, i2);
    }

    public final void writeBinaryField(String str, byte[] bArr) {
        writeFieldName(str);
        writeBinary(bArr);
    }

    public abstract void writeBoolean(boolean z);

    public final void writeBooleanField(String str, boolean z) {
        writeFieldName(str);
        writeBoolean(z);
    }

    public abstract void writeEndArray();

    public abstract void writeEndObject();

    public abstract void writeFieldName(String str);

    public void writeFieldName(SerializableString serializableString) {
        writeFieldName(serializableString.getValue());
    }

    public void writeFieldName(SerializedString serializedString) {
        writeFieldName(serializedString.getValue());
    }

    public abstract void writeNull();

    public final void writeNullField(String str) {
        writeFieldName(str);
        writeNull();
    }

    public abstract void writeNumber(double d);

    public abstract void writeNumber(float f);

    public abstract void writeNumber(int i);

    public abstract void writeNumber(long j);

    public abstract void writeNumber(String str);

    public abstract void writeNumber(BigDecimal bigDecimal);

    public abstract void writeNumber(BigInteger bigInteger);

    public final void writeNumberField(String str, double d) {
        writeFieldName(str);
        writeNumber(d);
    }

    public final void writeNumberField(String str, float f) {
        writeFieldName(str);
        writeNumber(f);
    }

    public final void writeNumberField(String str, int i) {
        writeFieldName(str);
        writeNumber(i);
    }

    public final void writeNumberField(String str, long j) {
        writeFieldName(str);
        writeNumber(j);
    }

    public final void writeNumberField(String str, BigDecimal bigDecimal) {
        writeFieldName(str);
        writeNumber(bigDecimal);
    }

    public abstract void writeObject(Object obj);

    public final void writeObjectField(String str, Object obj) {
        writeFieldName(str);
        writeObject(obj);
    }

    public final void writeObjectFieldStart(String str) {
        writeFieldName(str);
        writeStartObject();
    }

    public abstract void writeRaw(char c);

    public abstract void writeRaw(String str);

    public abstract void writeRaw(String str, int i, int i2);

    public abstract void writeRaw(char[] cArr, int i, int i2);

    public abstract void writeRawUTF8String(byte[] bArr, int i, int i2);

    public abstract void writeRawValue(String str);

    public abstract void writeRawValue(String str, int i, int i2);

    public abstract void writeRawValue(char[] cArr, int i, int i2);

    public abstract void writeStartArray();

    public abstract void writeStartObject();

    public abstract void writeString(String str);

    public void writeString(SerializableString serializableString) {
        writeString(serializableString.getValue());
    }

    public abstract void writeString(char[] cArr, int i, int i2);

    public void writeStringField(String str, String str2) {
        writeFieldName(str);
        writeString(str2);
    }

    public abstract void writeTree(JsonNode jsonNode);

    public abstract void writeUTF8String(byte[] bArr, int i, int i2);
}
