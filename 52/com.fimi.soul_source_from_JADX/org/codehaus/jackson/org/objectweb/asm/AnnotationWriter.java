package org.codehaus.jackson.org.objectweb.asm;

final class AnnotationWriter implements AnnotationVisitor {
    private final ClassWriter f16123a;
    private int f16124b;
    private final boolean f16125c;
    private final ByteVector f16126d;
    private final ByteVector f16127e;
    private final int f16128f;
    AnnotationWriter f16129g;
    AnnotationWriter f16130h;

    AnnotationWriter(ClassWriter classWriter, boolean z, ByteVector byteVector, ByteVector byteVector2, int i) {
        this.f16123a = classWriter;
        this.f16125c = z;
        this.f16126d = byteVector;
        this.f16127e = byteVector2;
        this.f16128f = i;
    }

    static void m19302a(AnnotationWriter[] annotationWriterArr, int i, ByteVector byteVector) {
        int length = ((annotationWriterArr.length - i) * 2) + 1;
        for (int i2 = i; i2 < annotationWriterArr.length; i2++) {
            length += annotationWriterArr[i2] == null ? 0 : annotationWriterArr[i2].m19303a();
        }
        byteVector.putInt(length).putByte(annotationWriterArr.length - i);
        while (i < annotationWriterArr.length) {
            AnnotationWriter annotationWriter = annotationWriterArr[i];
            AnnotationWriter annotationWriter2 = null;
            length = 0;
            while (annotationWriter != null) {
                length++;
                annotationWriter.visitEnd();
                annotationWriter.f16130h = annotationWriter2;
                AnnotationWriter annotationWriter3 = annotationWriter;
                annotationWriter = annotationWriter.f16129g;
                annotationWriter2 = annotationWriter3;
            }
            byteVector.putShort(length);
            while (annotationWriter2 != null) {
                byteVector.putByteArray(annotationWriter2.f16126d.f16133a, 0, annotationWriter2.f16126d.f16134b);
                annotationWriter2 = annotationWriter2.f16130h;
            }
            i++;
        }
    }

    int m19303a() {
        int i = 0;
        while (this != null) {
            i += this.f16126d.f16134b;
            this = this.f16129g;
        }
        return i;
    }

    void m19304a(ByteVector byteVector) {
        AnnotationWriter annotationWriter = null;
        int i = 2;
        int i2 = 0;
        for (AnnotationWriter annotationWriter2 = this; annotationWriter2 != null; annotationWriter2 = annotationWriter2.f16129g) {
            i2++;
            i += annotationWriter2.f16126d.f16134b;
            annotationWriter2.visitEnd();
            annotationWriter2.f16130h = annotationWriter;
            annotationWriter = annotationWriter2;
        }
        byteVector.putInt(i);
        byteVector.putShort(i2);
        while (annotationWriter != null) {
            byteVector.putByteArray(annotationWriter.f16126d.f16133a, 0, annotationWriter.f16126d.f16134b);
            annotationWriter = annotationWriter.f16130h;
        }
    }

    public void visit(String str, Object obj) {
        int i = 1;
        int i2 = 0;
        this.f16124b++;
        if (this.f16125c) {
            this.f16126d.putShort(this.f16123a.newUTF8(str));
        }
        if (obj instanceof String) {
            this.f16126d.m19310b(Opcodes.DREM, this.f16123a.newUTF8((String) obj));
        } else if (obj instanceof Byte) {
            this.f16126d.m19310b(66, this.f16123a.m19328a(((Byte) obj).byteValue()).f16202a);
        } else if (obj instanceof Boolean) {
            if (!((Boolean) obj).booleanValue()) {
                i = 0;
            }
            this.f16126d.m19310b(90, this.f16123a.m19328a(i).f16202a);
        } else if (obj instanceof Character) {
            this.f16126d.m19310b(67, this.f16123a.m19328a(((Character) obj).charValue()).f16202a);
        } else if (obj instanceof Short) {
            this.f16126d.m19310b(83, this.f16123a.m19328a(((Short) obj).shortValue()).f16202a);
        } else if (obj instanceof Type) {
            this.f16126d.m19310b(99, this.f16123a.newUTF8(((Type) obj).getDescriptor()));
        } else if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            this.f16126d.m19310b(91, bArr.length);
            while (i2 < bArr.length) {
                this.f16126d.m19310b(66, this.f16123a.m19328a(bArr[i2]).f16202a);
                i2++;
            }
        } else if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            this.f16126d.m19310b(91, zArr.length);
            for (boolean z : zArr) {
                this.f16126d.m19310b(90, this.f16123a.m19328a(z ? 1 : 0).f16202a);
            }
        } else if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            this.f16126d.m19310b(91, sArr.length);
            while (i2 < sArr.length) {
                this.f16126d.m19310b(83, this.f16123a.m19328a(sArr[i2]).f16202a);
                i2++;
            }
        } else if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            this.f16126d.m19310b(91, cArr.length);
            while (i2 < cArr.length) {
                this.f16126d.m19310b(67, this.f16123a.m19328a(cArr[i2]).f16202a);
                i2++;
            }
        } else if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            this.f16126d.m19310b(91, iArr.length);
            while (i2 < iArr.length) {
                this.f16126d.m19310b(73, this.f16123a.m19328a(iArr[i2]).f16202a);
                i2++;
            }
        } else if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            this.f16126d.m19310b(91, jArr.length);
            while (i2 < jArr.length) {
                this.f16126d.m19310b(74, this.f16123a.m19329a(jArr[i2]).f16202a);
                i2++;
            }
        } else if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            this.f16126d.m19310b(91, fArr.length);
            while (i2 < fArr.length) {
                this.f16126d.m19310b(70, this.f16123a.m19327a(fArr[i2]).f16202a);
                i2++;
            }
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            this.f16126d.m19310b(91, dArr.length);
            while (i2 < dArr.length) {
                this.f16126d.m19310b(68, this.f16123a.m19326a(dArr[i2]).f16202a);
                i2++;
            }
        } else {
            Item a = this.f16123a.m19330a(obj);
            this.f16126d.m19310b(".s.IFJDCS".charAt(a.f16203b), a.f16202a);
        }
    }

    public AnnotationVisitor visitAnnotation(String str, String str2) {
        this.f16124b++;
        if (this.f16125c) {
            this.f16126d.putShort(this.f16123a.newUTF8(str));
        }
        this.f16126d.m19310b(64, this.f16123a.newUTF8(str2)).putShort(0);
        return new AnnotationWriter(this.f16123a, true, this.f16126d, this.f16126d, this.f16126d.f16134b - 2);
    }

    public AnnotationVisitor visitArray(String str) {
        this.f16124b++;
        if (this.f16125c) {
            this.f16126d.putShort(this.f16123a.newUTF8(str));
        }
        this.f16126d.m19310b(91, 0);
        return new AnnotationWriter(this.f16123a, false, this.f16126d, this.f16126d, this.f16126d.f16134b - 2);
    }

    public void visitEnd() {
        if (this.f16127e != null) {
            byte[] bArr = this.f16127e.f16133a;
            bArr[this.f16128f] = (byte) (this.f16124b >>> 8);
            bArr[this.f16128f + 1] = (byte) this.f16124b;
        }
    }

    public void visitEnum(String str, String str2, String str3) {
        this.f16124b++;
        if (this.f16125c) {
            this.f16126d.putShort(this.f16123a.newUTF8(str));
        }
        this.f16126d.m19310b(Opcodes.LSUB, this.f16123a.newUTF8(str2)).putShort(this.f16123a.newUTF8(str3));
    }
}
