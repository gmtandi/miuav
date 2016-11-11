package org.codehaus.jackson.org.objectweb.asm;

public class Attribute {
    Attribute f16131a;
    byte[] f16132b;
    public final String type;

    protected Attribute(String str) {
        this.type = str;
    }

    final int m19305a() {
        int i = 0;
        while (this != null) {
            i++;
            this = this.f16131a;
        }
        return i;
    }

    final int m19306a(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        Attribute attribute = this;
        while (attribute != null) {
            classWriter.newUTF8(attribute.type);
            int i5 = (attribute.write(classWriter, bArr, i, i2, i3).f16134b + 6) + i4;
            attribute = attribute.f16131a;
            i4 = i5;
        }
        return i4;
    }

    final void m19307a(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3, ByteVector byteVector) {
        for (Attribute attribute = this; attribute != null; attribute = attribute.f16131a) {
            ByteVector write = attribute.write(classWriter, bArr, i, i2, i3);
            byteVector.putShort(classWriter.newUTF8(attribute.type)).putInt(write.f16134b);
            byteVector.putByteArray(write.f16133a, 0, write.f16134b);
        }
    }

    protected Label[] getLabels() {
        return null;
    }

    public boolean isCodeAttribute() {
        return false;
    }

    public boolean isUnknown() {
        return true;
    }

    protected Attribute read(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        Attribute attribute = new Attribute(this.type);
        attribute.f16132b = new byte[i2];
        System.arraycopy(classReader.f16136b, i, attribute.f16132b, 0, i2);
        return attribute;
    }

    protected ByteVector write(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3) {
        ByteVector byteVector = new ByteVector();
        byteVector.f16133a = this.f16132b;
        byteVector.f16134b = this.f16132b.length;
        return byteVector;
    }
}
