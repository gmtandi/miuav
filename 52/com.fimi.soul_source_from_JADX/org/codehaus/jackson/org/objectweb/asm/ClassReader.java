package org.codehaus.jackson.org.objectweb.asm;

import com.hoho.android.usbserial.driver.UsbId;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import it.sephiroth.android.library.widget.ExpandableHListView;
import java.io.IOException;
import java.io.InputStream;
import org.codehaus.jackson.smile.SmileConstants;

public class ClassReader {
    public static final int EXPAND_FRAMES = 8;
    public static final int SKIP_CODE = 1;
    public static final int SKIP_DEBUG = 2;
    public static final int SKIP_FRAMES = 4;
    private final int[] f16135a;
    public final byte[] f16136b;
    private final String[] f16137c;
    private final int f16138d;
    public final int header;

    public ClassReader(InputStream inputStream) {
        this(m19317a(inputStream));
    }

    public ClassReader(String str) {
        this(ClassLoader.getSystemResourceAsStream(new StringBuffer().append(str.replace('.', '/')).append(".class").toString()));
    }

    public ClassReader(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public ClassReader(byte[] bArr, int i, int i2) {
        this.f16136b = bArr;
        this.f16135a = new int[readUnsignedShort(i + EXPAND_FRAMES)];
        int length = this.f16135a.length;
        this.f16137c = new String[length];
        int i3 = 0;
        int i4 = i + 10;
        int i5 = SKIP_CODE;
        int i6 = i4;
        while (i5 < length) {
            this.f16135a[i5] = i6 + SKIP_CODE;
            switch (bArr[i6]) {
                case SKIP_CODE /*1*/:
                    i4 = readUnsignedShort(i6 + SKIP_CODE) + 3;
                    if (i4 <= i3) {
                        break;
                    }
                    i3 = i4;
                    break;
                case Type.BYTE /*3*/:
                case SKIP_FRAMES /*4*/:
                case Type.ARRAY /*9*/:
                case Type.OBJECT /*10*/:
                case Opcodes.T_LONG /*11*/:
                case Opcodes.FCONST_1 /*12*/:
                    i4 = 5;
                    break;
                case Type.INT /*5*/:
                case Type.FLOAT /*6*/:
                    i4 = 9;
                    i5 += SKIP_CODE;
                    break;
                default:
                    i4 = 3;
                    break;
            }
            i5 += SKIP_CODE;
            i6 = i4 + i6;
        }
        this.f16138d = i3;
        this.header = i6;
    }

    private int m19311a(int i, char[] cArr, String str, AnnotationVisitor annotationVisitor) {
        int i2 = false;
        if (annotationVisitor == null) {
            switch (this.f16136b[i] & Util.MASK_8BIT) {
                case SmileConstants.TOKEN_PREFIX_TINY_ASCII /*64*/:
                    return m19312a(i + 3, cArr, true, null);
                case Opcodes.DUP_X2 /*91*/:
                    return m19312a(i + SKIP_CODE, cArr, false, null);
                case Opcodes.LSUB /*101*/:
                    return i + 5;
                default:
                    return i + 3;
            }
        }
        int i3 = i + SKIP_CODE;
        switch (this.f16136b[i] & Util.MASK_8BIT) {
            case SmileConstants.TOKEN_PREFIX_TINY_ASCII /*64*/:
                return m19312a(i3 + SKIP_DEBUG, cArr, true, annotationVisitor.visitAnnotation(str, readUTF8(i3, cArr)));
            case UsbId.ARDUINO_MEGA_2560_R3 /*66*/:
                annotationVisitor.visit(str, new Byte((byte) readInt(this.f16135a[readUnsignedShort(i3)])));
                return i3 + SKIP_DEBUG;
            case UsbId.ARDUINO_UNO_R3 /*67*/:
                annotationVisitor.visit(str, new Character((char) readInt(this.f16135a[readUnsignedShort(i3)])));
                return i3 + SKIP_DEBUG;
            case UsbId.ARDUINO_SERIAL_ADAPTER_R3 /*68*/:
            case 70:
            case 73:
            case 74:
                annotationVisitor.visit(str, readConst(readUnsignedShort(i3), cArr));
                return i3 + SKIP_DEBUG;
            case Opcodes.AASTORE /*83*/:
                annotationVisitor.visit(str, new Short((short) readInt(this.f16135a[readUnsignedShort(i3)])));
                return i3 + SKIP_DEBUG;
            case Opcodes.DUP_X1 /*90*/:
                annotationVisitor.visit(str, readInt(this.f16135a[readUnsignedShort(i3)]) == 0 ? Boolean.FALSE : Boolean.TRUE);
                return i3 + SKIP_DEBUG;
            case Opcodes.DUP_X2 /*91*/:
                int readUnsignedShort = readUnsignedShort(i3);
                i3 += SKIP_DEBUG;
                if (readUnsignedShort == 0) {
                    return m19312a(i3 - 2, cArr, false, annotationVisitor.visitArray(str));
                }
                int i4 = i3 + SKIP_CODE;
                Object obj;
                switch (this.f16136b[i3] & Util.MASK_8BIT) {
                    case UsbId.ARDUINO_MEGA_2560_R3 /*66*/:
                        obj = new byte[readUnsignedShort];
                        while (i2 < readUnsignedShort) {
                            obj[i2] = (byte) readInt(this.f16135a[readUnsignedShort(i4)]);
                            i4 += 3;
                            i2 += SKIP_CODE;
                        }
                        annotationVisitor.visit(str, obj);
                        return i4 - 1;
                    case UsbId.ARDUINO_UNO_R3 /*67*/:
                        obj = new char[readUnsignedShort];
                        while (i2 < readUnsignedShort) {
                            obj[i2] = (char) readInt(this.f16135a[readUnsignedShort(i4)]);
                            i4 += 3;
                            i2 += SKIP_CODE;
                        }
                        annotationVisitor.visit(str, obj);
                        return i4 - 1;
                    case UsbId.ARDUINO_SERIAL_ADAPTER_R3 /*68*/:
                        obj = new double[readUnsignedShort];
                        while (i2 < readUnsignedShort) {
                            obj[i2] = Double.longBitsToDouble(readLong(this.f16135a[readUnsignedShort(i4)]));
                            i4 += 3;
                            i2 += SKIP_CODE;
                        }
                        annotationVisitor.visit(str, obj);
                        return i4 - 1;
                    case 70:
                        obj = new float[readUnsignedShort];
                        while (i2 < readUnsignedShort) {
                            obj[i2] = Float.intBitsToFloat(readInt(this.f16135a[readUnsignedShort(i4)]));
                            i4 += 3;
                            i2 += SKIP_CODE;
                        }
                        annotationVisitor.visit(str, obj);
                        return i4 - 1;
                    case 73:
                        obj = new int[readUnsignedShort];
                        while (i2 < readUnsignedShort) {
                            obj[i2] = readInt(this.f16135a[readUnsignedShort(i4)]);
                            i4 += 3;
                            i2 += SKIP_CODE;
                        }
                        annotationVisitor.visit(str, obj);
                        return i4 - 1;
                    case 74:
                        obj = new long[readUnsignedShort];
                        while (i2 < readUnsignedShort) {
                            obj[i2] = readLong(this.f16135a[readUnsignedShort(i4)]);
                            i4 += 3;
                            i2 += SKIP_CODE;
                        }
                        annotationVisitor.visit(str, obj);
                        return i4 - 1;
                    case Opcodes.AASTORE /*83*/:
                        obj = new short[readUnsignedShort];
                        while (i2 < readUnsignedShort) {
                            obj[i2] = (short) readInt(this.f16135a[readUnsignedShort(i4)]);
                            i4 += 3;
                            i2 += SKIP_CODE;
                        }
                        annotationVisitor.visit(str, obj);
                        return i4 - 1;
                    case Opcodes.DUP_X1 /*90*/:
                        Object obj2 = new boolean[readUnsignedShort];
                        int i5 = i4;
                        for (i3 = 0; i3 < readUnsignedShort; i3 += SKIP_CODE) {
                            obj2[i3] = readInt(this.f16135a[readUnsignedShort(i5)]) != 0;
                            i5 += 3;
                        }
                        annotationVisitor.visit(str, obj2);
                        return i5 - 1;
                    default:
                        return m19312a(i4 - 3, cArr, false, annotationVisitor.visitArray(str));
                }
            case Opcodes.DADD /*99*/:
                annotationVisitor.visit(str, Type.getType(readUTF8(i3, cArr)));
                return i3 + SKIP_DEBUG;
            case Opcodes.LSUB /*101*/:
                annotationVisitor.visitEnum(str, readUTF8(i3, cArr), readUTF8(i3 + SKIP_DEBUG, cArr));
                return i3 + SKIP_FRAMES;
            case Opcodes.DREM /*115*/:
                annotationVisitor.visit(str, readUTF8(i3, cArr));
                return i3 + SKIP_DEBUG;
            default:
                return i3;
        }
    }

    private int m19312a(int i, char[] cArr, boolean z, AnnotationVisitor annotationVisitor) {
        int readUnsignedShort = readUnsignedShort(i);
        int i2 = i + SKIP_DEBUG;
        int i3;
        if (z) {
            i3 = readUnsignedShort;
            readUnsignedShort = i2;
            i2 = i3;
            while (i2 > 0) {
                i2--;
                readUnsignedShort = m19311a(readUnsignedShort + SKIP_DEBUG, cArr, readUTF8(readUnsignedShort, cArr), annotationVisitor);
            }
        } else {
            i3 = readUnsignedShort;
            readUnsignedShort = i2;
            i2 = i3;
            while (i2 > 0) {
                i2--;
                readUnsignedShort = m19311a(readUnsignedShort, cArr, null, annotationVisitor);
            }
        }
        if (annotationVisitor != null) {
            annotationVisitor.visitEnd();
        }
        return readUnsignedShort;
    }

    private int m19313a(Object[] objArr, int i, int i2, char[] cArr, Label[] labelArr) {
        int i3 = i2 + SKIP_CODE;
        switch (this.f16136b[i2] & Util.MASK_8BIT) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                objArr[i] = Opcodes.TOP;
                return i3;
            case SKIP_CODE /*1*/:
                objArr[i] = Opcodes.INTEGER;
                return i3;
            case SKIP_DEBUG /*2*/:
                objArr[i] = Opcodes.FLOAT;
                return i3;
            case Type.BYTE /*3*/:
                objArr[i] = Opcodes.DOUBLE;
                return i3;
            case SKIP_FRAMES /*4*/:
                objArr[i] = Opcodes.LONG;
                return i3;
            case Type.INT /*5*/:
                objArr[i] = Opcodes.NULL;
                return i3;
            case Type.FLOAT /*6*/:
                objArr[i] = Opcodes.UNINITIALIZED_THIS;
                return i3;
            case Type.LONG /*7*/:
                objArr[i] = readClass(i3, cArr);
                return i3 + SKIP_DEBUG;
            default:
                objArr[i] = readLabel(readUnsignedShort(i3), labelArr);
                return i3 + SKIP_DEBUG;
        }
    }

    private String m19314a(int i, int i2, char[] cArr) {
        int i3 = i + i2;
        byte[] bArr = this.f16136b;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i < i3) {
            int i7;
            int i8 = i + SKIP_CODE;
            byte b = bArr[i];
            switch (i5) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    int i9 = b & Util.MASK_8BIT;
                    if (i9 >= SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
                        if (i9 < SmileConstants.TOKEN_PREFIX_MISC_OTHER && i9 > Opcodes.ATHROW) {
                            i4 = (char) (i9 & 31);
                            i5 = SKIP_CODE;
                            i7 = i6;
                            break;
                        }
                        i4 = (char) (i9 & 15);
                        i5 = SKIP_DEBUG;
                        i7 = i6;
                        break;
                    }
                    i7 = i6 + SKIP_CODE;
                    cArr[i6] = (char) i9;
                    break;
                case SKIP_CODE /*1*/:
                    i5 = i6 + SKIP_CODE;
                    cArr[i6] = (char) ((b & 63) | (i4 << 6));
                    i7 = i5;
                    i5 = 0;
                    break;
                case SKIP_DEBUG /*2*/:
                    i4 = (char) ((i4 << 6) | (b & 63));
                    i5 = SKIP_CODE;
                    i7 = i6;
                    break;
                default:
                    i7 = i6;
                    break;
            }
            i6 = i7;
            i = i8;
        }
        return new String(cArr, 0, i6);
    }

    private Attribute m19315a(Attribute[] attributeArr, String str, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        for (int i4 = 0; i4 < attributeArr.length; i4 += SKIP_CODE) {
            if (attributeArr[i4].type.equals(str)) {
                return attributeArr[i4].read(this, i, i2, cArr, i3, labelArr);
            }
        }
        return new Attribute(str).read(this, i, i2, null, -1, null);
    }

    private void m19316a(int i, String str, char[] cArr, boolean z, MethodVisitor methodVisitor) {
        int i2 = i + SKIP_CODE;
        int i3 = this.f16136b[i] & Util.MASK_8BIT;
        int length = Type.getArgumentTypes(str).length - i3;
        int i4 = 0;
        while (i4 < length) {
            AnnotationVisitor visitParameterAnnotation = methodVisitor.visitParameterAnnotation(i4, "Ljava/lang/Synthetic;", false);
            if (visitParameterAnnotation != null) {
                visitParameterAnnotation.visitEnd();
            }
            i4 += SKIP_CODE;
        }
        for (int i5 = i4; i5 < i3 + length; i5 += SKIP_CODE) {
            i2 += SKIP_DEBUG;
            for (i4 = readUnsignedShort(i2); i4 > 0; i4--) {
                i2 = m19312a(i2 + SKIP_DEBUG, cArr, true, methodVisitor.visitParameterAnnotation(i5, readUTF8(i2, cArr), z));
            }
        }
    }

    private static byte[] m19317a(InputStream inputStream) {
        if (inputStream == null) {
            throw new IOException("Class not found");
        }
        Object obj = new byte[inputStream.available()];
        int i = 0;
        while (true) {
            int read = inputStream.read(obj, i, obj.length - i);
            if (read == -1) {
                break;
            }
            read += i;
            if (read == obj.length) {
                int read2 = inputStream.read();
                if (read2 < 0) {
                    return obj;
                }
                Object obj2 = new byte[(obj.length + XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER)];
                System.arraycopy(obj, 0, obj2, 0, read);
                i = read + SKIP_CODE;
                obj2[read] = (byte) read2;
                obj = obj2;
            } else {
                i = read;
            }
        }
        if (i >= obj.length) {
            return obj;
        }
        Object obj3 = new byte[i];
        System.arraycopy(obj, 0, obj3, 0, i);
        return obj3;
    }

    void m19318a(ClassWriter classWriter) {
        int i;
        char[] cArr = new char[this.f16138d];
        int length = this.f16135a.length;
        Item[] itemArr = new Item[length];
        int i2 = SKIP_CODE;
        while (i2 < length) {
            i = this.f16135a[i2];
            byte b = this.f16136b[i - 1];
            Item item = new Item(i2);
            switch (b) {
                case SKIP_CODE /*1*/:
                    String str = this.f16137c[i2];
                    if (str == null) {
                        i = this.f16135a[i2];
                        String[] strArr = this.f16137c;
                        str = m19314a(i + SKIP_DEBUG, readUnsignedShort(i), cArr);
                        strArr[i2] = str;
                    }
                    item.m19355a(b, str, null, null);
                    i = i2;
                    break;
                case Type.BYTE /*3*/:
                    item.m19354a(readInt(i));
                    i = i2;
                    break;
                case SKIP_FRAMES /*4*/:
                    item.m19353a(Float.intBitsToFloat(readInt(i)));
                    i = i2;
                    break;
                case Type.INT /*5*/:
                    item.m19356a(readLong(i));
                    i = i2 + SKIP_CODE;
                    break;
                case Type.FLOAT /*6*/:
                    item.m19352a(Double.longBitsToDouble(readLong(i)));
                    i = i2 + SKIP_CODE;
                    break;
                case Type.ARRAY /*9*/:
                case Type.OBJECT /*10*/:
                case Opcodes.T_LONG /*11*/:
                    int i3 = this.f16135a[readUnsignedShort(i + SKIP_DEBUG)];
                    item.m19355a(b, readClass(i, cArr), readUTF8(i3, cArr), readUTF8(i3 + SKIP_DEBUG, cArr));
                    i = i2;
                    break;
                case Opcodes.FCONST_1 /*12*/:
                    item.m19355a(b, readUTF8(i, cArr), readUTF8(i + SKIP_DEBUG, cArr), null);
                    i = i2;
                    break;
                default:
                    item.m19355a(b, readUTF8(i, cArr), null, null);
                    i = i2;
                    break;
            }
            i2 = item.f16209j % itemArr.length;
            item.f16210k = itemArr[i2];
            itemArr[i2] = item;
            i2 = i + SKIP_CODE;
        }
        i = this.f16135a[SKIP_CODE] - 1;
        classWriter.f16151d.putByteArray(this.f16136b, i, this.header - i);
        classWriter.f16152e = itemArr;
        classWriter.f16153f = (int) (0.75d * ((double) length));
        classWriter.f16150c = length;
    }

    public void accept(ClassVisitor classVisitor, int i) {
        accept(classVisitor, new Attribute[0], i);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void accept(org.codehaus.jackson.org.objectweb.asm.ClassVisitor r49, org.codehaus.jackson.org.objectweb.asm.Attribute[] r50, int r51) {
        /*
        r48 = this;
        r0 = r48;
        r0 = r0.f16136b;
        r41 = r0;
        r0 = r48;
        r4 = r0.f16138d;
        r9 = new char[r4];
        r26 = 0;
        r25 = 0;
        r24 = 0;
        r0 = r48;
        r4 = r0.header;
        r0 = r48;
        r12 = r0.readUnsignedShort(r4);
        r5 = r4 + 2;
        r0 = r48;
        r13 = r0.readClass(r5, r9);
        r0 = r48;
        r5 = r0.f16135a;
        r6 = r4 + 4;
        r0 = r48;
        r6 = r0.readUnsignedShort(r6);
        r5 = r5[r6];
        if (r5 != 0) goto L_0x005e;
    L_0x0034:
        r15 = 0;
    L_0x0035:
        r5 = r4 + 6;
        r0 = r48;
        r5 = r0.readUnsignedShort(r5);
        r0 = new java.lang.String[r5];
        r16 = r0;
        r23 = 0;
        r5 = r4 + 8;
        r4 = 0;
        r17 = r5;
    L_0x0048:
        r0 = r16;
        r5 = r0.length;
        if (r4 >= r5) goto L_0x0065;
    L_0x004d:
        r0 = r48;
        r1 = r17;
        r5 = r0.readClass(r1, r9);
        r16[r4] = r5;
        r5 = r17 + 2;
        r4 = r4 + 1;
        r17 = r5;
        goto L_0x0048;
    L_0x005e:
        r0 = r48;
        r15 = r0.readUTF8(r5, r9);
        goto L_0x0035;
    L_0x0065:
        r4 = r51 & 1;
        if (r4 == 0) goto L_0x00a1;
    L_0x0069:
        r4 = 1;
        r40 = r4;
    L_0x006c:
        r4 = r51 & 2;
        if (r4 == 0) goto L_0x00a5;
    L_0x0070:
        r4 = 1;
        r39 = r4;
    L_0x0073:
        r4 = r51 & 8;
        if (r4 == 0) goto L_0x00a9;
    L_0x0077:
        r4 = 1;
        r27 = r4;
    L_0x007a:
        r0 = r48;
        r1 = r17;
        r4 = r0.readUnsignedShort(r1);
        r5 = r17 + 2;
        r6 = r4;
    L_0x0085:
        if (r6 <= 0) goto L_0x00b1;
    L_0x0087:
        r4 = r5 + 6;
        r0 = r48;
        r4 = r0.readUnsignedShort(r4);
        r5 = r5 + 8;
    L_0x0091:
        if (r4 <= 0) goto L_0x00ad;
    L_0x0093:
        r7 = r5 + 2;
        r0 = r48;
        r7 = r0.readInt(r7);
        r7 = r7 + 6;
        r5 = r5 + r7;
        r4 = r4 + -1;
        goto L_0x0091;
    L_0x00a1:
        r4 = 0;
        r40 = r4;
        goto L_0x006c;
    L_0x00a5:
        r4 = 0;
        r39 = r4;
        goto L_0x0073;
    L_0x00a9:
        r4 = 0;
        r27 = r4;
        goto L_0x007a;
    L_0x00ad:
        r4 = r6 + -1;
        r6 = r4;
        goto L_0x0085;
    L_0x00b1:
        r0 = r48;
        r4 = r0.readUnsignedShort(r5);
        r5 = r5 + 2;
        r6 = r4;
    L_0x00ba:
        if (r6 <= 0) goto L_0x00da;
    L_0x00bc:
        r4 = r5 + 6;
        r0 = r48;
        r4 = r0.readUnsignedShort(r4);
        r5 = r5 + 8;
    L_0x00c6:
        if (r4 <= 0) goto L_0x00d6;
    L_0x00c8:
        r7 = r5 + 2;
        r0 = r48;
        r7 = r0.readInt(r7);
        r7 = r7 + 6;
        r5 = r5 + r7;
        r4 = r4 + -1;
        goto L_0x00c6;
    L_0x00d6:
        r4 = r6 + -1;
        r6 = r4;
        goto L_0x00ba;
    L_0x00da:
        r14 = 0;
        r22 = 0;
        r21 = 0;
        r20 = 0;
        r19 = 0;
        r18 = 0;
        r0 = r48;
        r4 = r0.readUnsignedShort(r5);
        r5 = r5 + 2;
        r28 = r4;
        r29 = r5;
    L_0x00f1:
        if (r28 <= 0) goto L_0x029c;
    L_0x00f3:
        r0 = r48;
        r1 = r29;
        r6 = r0.readUTF8(r1, r9);
        r4 = "SourceFile";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x0143;
    L_0x0103:
        r4 = r29 + 6;
        r0 = r48;
        r4 = r0.readUTF8(r4, r9);
        r5 = r19;
        r6 = r20;
        r7 = r21;
        r8 = r4;
        r10 = r23;
        r11 = r24;
        r4 = r18;
        r19 = r26;
        r18 = r25;
    L_0x011c:
        r20 = r29 + 2;
        r0 = r48;
        r1 = r20;
        r20 = r0.readInt(r1);
        r20 = r20 + 6;
        r21 = r29 + r20;
        r20 = r28 + -1;
        r22 = r8;
        r28 = r20;
        r23 = r10;
        r29 = r21;
        r24 = r11;
        r25 = r18;
        r26 = r19;
        r21 = r7;
        r18 = r4;
        r19 = r5;
        r20 = r6;
        goto L_0x00f1;
    L_0x0143:
        r4 = "InnerClasses";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x015f;
    L_0x014b:
        r4 = r29 + 6;
        r5 = r19;
        r6 = r20;
        r7 = r21;
        r8 = r22;
        r10 = r4;
        r11 = r24;
        r4 = r18;
        r19 = r26;
        r18 = r25;
        goto L_0x011c;
    L_0x015f:
        r4 = "EnclosingMethod";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x01a1;
    L_0x0167:
        r4 = r29 + 6;
        r0 = r48;
        r6 = r0.readClass(r4, r9);
        r4 = r29 + 8;
        r0 = r48;
        r4 = r0.readUnsignedShort(r4);
        if (r4 == 0) goto L_0x0fb1;
    L_0x0179:
        r0 = r48;
        r5 = r0.f16135a;
        r5 = r5[r4];
        r0 = r48;
        r5 = r0.readUTF8(r5, r9);
        r0 = r48;
        r7 = r0.f16135a;
        r4 = r7[r4];
        r4 = r4 + 2;
        r0 = r48;
        r4 = r0.readUTF8(r4, r9);
    L_0x0193:
        r7 = r21;
        r8 = r22;
        r10 = r23;
        r11 = r24;
        r18 = r25;
        r19 = r26;
        goto L_0x011c;
    L_0x01a1:
        r4 = "Signature";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x01c5;
    L_0x01a9:
        r4 = r29 + 6;
        r0 = r48;
        r14 = r0.readUTF8(r4, r9);
        r4 = r18;
        r5 = r19;
        r6 = r20;
        r7 = r21;
        r8 = r22;
        r10 = r23;
        r11 = r24;
        r18 = r25;
        r19 = r26;
        goto L_0x011c;
    L_0x01c5:
        r4 = "RuntimeVisibleAnnotations";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x01e3;
    L_0x01cd:
        r4 = r29 + 6;
        r5 = r19;
        r6 = r20;
        r7 = r21;
        r8 = r22;
        r10 = r23;
        r11 = r24;
        r19 = r4;
        r4 = r18;
        r18 = r25;
        goto L_0x011c;
    L_0x01e3:
        r4 = "Deprecated";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x0202;
    L_0x01eb:
        r4 = 131072; // 0x20000 float:1.83671E-40 double:6.47582E-319;
        r12 = r12 | r4;
        r4 = r18;
        r5 = r19;
        r6 = r20;
        r7 = r21;
        r8 = r22;
        r10 = r23;
        r11 = r24;
        r18 = r25;
        r19 = r26;
        goto L_0x011c;
    L_0x0202:
        r4 = "Synthetic";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x0222;
    L_0x020a:
        r4 = 266240; // 0x41000 float:3.73082E-40 double:1.3154E-318;
        r12 = r12 | r4;
        r4 = r18;
        r5 = r19;
        r6 = r20;
        r7 = r21;
        r8 = r22;
        r10 = r23;
        r11 = r24;
        r18 = r25;
        r19 = r26;
        goto L_0x011c;
    L_0x0222:
        r4 = "SourceDebugExtension";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x024f;
    L_0x022a:
        r4 = r29 + 2;
        r0 = r48;
        r4 = r0.readInt(r4);
        r5 = r29 + 6;
        r6 = new char[r4];
        r0 = r48;
        r4 = r0.m19314a(r5, r4, r6);
        r5 = r19;
        r6 = r20;
        r7 = r4;
        r8 = r22;
        r10 = r23;
        r11 = r24;
        r4 = r18;
        r19 = r26;
        r18 = r25;
        goto L_0x011c;
    L_0x024f:
        r4 = "RuntimeInvisibleAnnotations";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x026f;
    L_0x0257:
        r4 = r29 + 6;
        r5 = r19;
        r6 = r20;
        r7 = r21;
        r8 = r22;
        r10 = r23;
        r11 = r24;
        r19 = r26;
        r47 = r4;
        r4 = r18;
        r18 = r47;
        goto L_0x011c;
    L_0x026f:
        r7 = r29 + 6;
        r4 = r29 + 2;
        r0 = r48;
        r8 = r0.readInt(r4);
        r10 = -1;
        r11 = 0;
        r4 = r48;
        r5 = r50;
        r4 = r4.m19315a(r5, r6, r7, r8, r9, r10, r11);
        if (r4 == 0) goto L_0x0f9d;
    L_0x0285:
        r0 = r24;
        r4.f16131a = r0;
        r5 = r19;
        r6 = r20;
        r7 = r21;
        r8 = r22;
        r10 = r23;
        r11 = r4;
        r4 = r18;
        r19 = r26;
        r18 = r25;
        goto L_0x011c;
    L_0x029c:
        r4 = 4;
        r0 = r48;
        r11 = r0.readInt(r4);
        r10 = r49;
        r10.visit(r11, r12, r13, r14, r15, r16);
        if (r39 != 0) goto L_0x02b7;
    L_0x02aa:
        if (r22 != 0) goto L_0x02ae;
    L_0x02ac:
        if (r21 == 0) goto L_0x02b7;
    L_0x02ae:
        r0 = r49;
        r1 = r22;
        r2 = r21;
        r0.visitSource(r1, r2);
    L_0x02b7:
        if (r20 == 0) goto L_0x02c4;
    L_0x02b9:
        r0 = r49;
        r1 = r20;
        r2 = r19;
        r3 = r18;
        r0.visitOuterClass(r1, r2, r3);
    L_0x02c4:
        r4 = 1;
        r7 = r4;
    L_0x02c6:
        if (r7 < 0) goto L_0x0303;
    L_0x02c8:
        if (r7 != 0) goto L_0x02fa;
    L_0x02ca:
        r5 = r25;
    L_0x02cc:
        if (r5 == 0) goto L_0x02ff;
    L_0x02ce:
        r0 = r48;
        r4 = r0.readUnsignedShort(r5);
        r5 = r5 + 2;
        r47 = r4;
        r4 = r5;
        r5 = r47;
    L_0x02db:
        if (r5 <= 0) goto L_0x02ff;
    L_0x02dd:
        r6 = r4 + 2;
        r8 = 1;
        r0 = r48;
        r10 = r0.readUTF8(r4, r9);
        if (r7 == 0) goto L_0x02fd;
    L_0x02e8:
        r4 = 1;
    L_0x02e9:
        r0 = r49;
        r4 = r0.visitAnnotation(r10, r4);
        r0 = r48;
        r6 = r0.m19312a(r6, r9, r8, r4);
        r4 = r5 + -1;
        r5 = r4;
        r4 = r6;
        goto L_0x02db;
    L_0x02fa:
        r5 = r26;
        goto L_0x02cc;
    L_0x02fd:
        r4 = 0;
        goto L_0x02e9;
    L_0x02ff:
        r4 = r7 + -1;
        r7 = r4;
        goto L_0x02c6;
    L_0x0303:
        if (r24 == 0) goto L_0x0318;
    L_0x0305:
        r0 = r24;
        r4 = r0.f16131a;
        r5 = 0;
        r0 = r24;
        r0.f16131a = r5;
        r0 = r49;
        r1 = r24;
        r0.visitAttribute(r1);
        r24 = r4;
        goto L_0x0303;
    L_0x0318:
        if (r23 == 0) goto L_0x0374;
    L_0x031a:
        r0 = r48;
        r1 = r23;
        r4 = r0.readUnsignedShort(r1);
        r5 = r23 + 2;
        r7 = r4;
        r8 = r5;
    L_0x0326:
        if (r7 <= 0) goto L_0x0374;
    L_0x0328:
        r0 = r48;
        r4 = r0.readUnsignedShort(r8);
        if (r4 != 0) goto L_0x035b;
    L_0x0330:
        r4 = 0;
    L_0x0331:
        r5 = r8 + 2;
        r0 = r48;
        r5 = r0.readUnsignedShort(r5);
        if (r5 != 0) goto L_0x0362;
    L_0x033b:
        r5 = 0;
    L_0x033c:
        r6 = r8 + 4;
        r0 = r48;
        r6 = r0.readUnsignedShort(r6);
        if (r6 != 0) goto L_0x036b;
    L_0x0346:
        r6 = 0;
    L_0x0347:
        r10 = r8 + 6;
        r0 = r48;
        r10 = r0.readUnsignedShort(r10);
        r0 = r49;
        r0.visitInnerClass(r4, r5, r6, r10);
        r5 = r8 + 8;
        r4 = r7 + -1;
        r7 = r4;
        r8 = r5;
        goto L_0x0326;
    L_0x035b:
        r0 = r48;
        r4 = r0.readClass(r8, r9);
        goto L_0x0331;
    L_0x0362:
        r5 = r8 + 2;
        r0 = r48;
        r5 = r0.readClass(r5, r9);
        goto L_0x033c;
    L_0x036b:
        r6 = r8 + 4;
        r0 = r48;
        r6 = r0.readUTF8(r6, r9);
        goto L_0x0347;
    L_0x0374:
        r0 = r48;
        r1 = r17;
        r4 = r0.readUnsignedShort(r1);
        r21 = r17 + 2;
        r22 = r4;
    L_0x0380:
        if (r22 <= 0) goto L_0x04eb;
    L_0x0382:
        r0 = r48;
        r1 = r21;
        r16 = r0.readUnsignedShort(r1);
        r4 = r21 + 2;
        r0 = r48;
        r12 = r0.readUTF8(r4, r9);
        r4 = r21 + 4;
        r0 = r48;
        r13 = r0.readUTF8(r4, r9);
        r15 = 0;
        r14 = 0;
        r19 = 0;
        r18 = 0;
        r17 = 0;
        r4 = r21 + 6;
        r0 = r48;
        r4 = r0.readUnsignedShort(r4);
        r5 = r21 + 8;
        r20 = r4;
        r21 = r5;
    L_0x03b0:
        if (r20 <= 0) goto L_0x047e;
    L_0x03b2:
        r0 = r48;
        r1 = r21;
        r6 = r0.readUTF8(r1, r9);
        r4 = "ConstantValue";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x03ee;
    L_0x03c2:
        r4 = r21 + 6;
        r0 = r48;
        r4 = r0.readUnsignedShort(r4);
        r5 = r16;
        r6 = r17;
        r7 = r18;
        r8 = r19;
    L_0x03d2:
        r10 = r21 + 2;
        r0 = r48;
        r10 = r0.readInt(r10);
        r10 = r10 + 6;
        r11 = r21 + r10;
        r10 = r20 + -1;
        r15 = r4;
        r20 = r10;
        r16 = r5;
        r21 = r11;
        r17 = r6;
        r18 = r7;
        r19 = r8;
        goto L_0x03b0;
    L_0x03ee:
        r4 = "Signature";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x0408;
    L_0x03f6:
        r4 = r21 + 6;
        r0 = r48;
        r14 = r0.readUTF8(r4, r9);
        r4 = r15;
        r5 = r16;
        r6 = r17;
        r7 = r18;
        r8 = r19;
        goto L_0x03d2;
    L_0x0408:
        r4 = "Deprecated";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x041d;
    L_0x0410:
        r4 = 131072; // 0x20000 float:1.83671E-40 double:6.47582E-319;
        r4 = r4 | r16;
        r5 = r4;
        r6 = r17;
        r7 = r18;
        r8 = r19;
        r4 = r15;
        goto L_0x03d2;
    L_0x041d:
        r4 = "Synthetic";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x0433;
    L_0x0425:
        r4 = 266240; // 0x41000 float:3.73082E-40 double:1.3154E-318;
        r4 = r4 | r16;
        r5 = r4;
        r6 = r17;
        r7 = r18;
        r8 = r19;
        r4 = r15;
        goto L_0x03d2;
    L_0x0433:
        r4 = "RuntimeVisibleAnnotations";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x0446;
    L_0x043b:
        r4 = r21 + 6;
        r5 = r16;
        r6 = r17;
        r7 = r18;
        r8 = r4;
        r4 = r15;
        goto L_0x03d2;
    L_0x0446:
        r4 = "RuntimeInvisibleAnnotations";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x045a;
    L_0x044e:
        r4 = r21 + 6;
        r5 = r16;
        r6 = r17;
        r7 = r4;
        r8 = r19;
        r4 = r15;
        goto L_0x03d2;
    L_0x045a:
        r7 = r21 + 6;
        r4 = r21 + 2;
        r0 = r48;
        r8 = r0.readInt(r4);
        r10 = -1;
        r11 = 0;
        r4 = r48;
        r5 = r50;
        r4 = r4.m19315a(r5, r6, r7, r8, r9, r10, r11);
        if (r4 == 0) goto L_0x0f92;
    L_0x0470:
        r0 = r17;
        r4.f16131a = r0;
        r5 = r16;
        r6 = r4;
        r7 = r18;
        r8 = r19;
        r4 = r15;
        goto L_0x03d2;
    L_0x047e:
        if (r15 != 0) goto L_0x04bf;
    L_0x0480:
        r15 = 0;
    L_0x0481:
        r10 = r49;
        r11 = r16;
        r8 = r10.visitField(r11, r12, r13, r14, r15);
        if (r8 == 0) goto L_0x04e5;
    L_0x048b:
        r4 = 1;
        r7 = r4;
    L_0x048d:
        if (r7 < 0) goto L_0x04cf;
    L_0x048f:
        if (r7 != 0) goto L_0x04c6;
    L_0x0491:
        r5 = r18;
    L_0x0493:
        if (r5 == 0) goto L_0x04cb;
    L_0x0495:
        r0 = r48;
        r4 = r0.readUnsignedShort(r5);
        r5 = r5 + 2;
        r47 = r4;
        r4 = r5;
        r5 = r47;
    L_0x04a2:
        if (r5 <= 0) goto L_0x04cb;
    L_0x04a4:
        r6 = r4 + 2;
        r10 = 1;
        r0 = r48;
        r11 = r0.readUTF8(r4, r9);
        if (r7 == 0) goto L_0x04c9;
    L_0x04af:
        r4 = 1;
    L_0x04b0:
        r4 = r8.visitAnnotation(r11, r4);
        r0 = r48;
        r6 = r0.m19312a(r6, r9, r10, r4);
        r4 = r5 + -1;
        r5 = r4;
        r4 = r6;
        goto L_0x04a2;
    L_0x04bf:
        r0 = r48;
        r15 = r0.readConst(r15, r9);
        goto L_0x0481;
    L_0x04c6:
        r5 = r19;
        goto L_0x0493;
    L_0x04c9:
        r4 = 0;
        goto L_0x04b0;
    L_0x04cb:
        r4 = r7 + -1;
        r7 = r4;
        goto L_0x048d;
    L_0x04cf:
        if (r17 == 0) goto L_0x04e2;
    L_0x04d1:
        r0 = r17;
        r4 = r0.f16131a;
        r5 = 0;
        r0 = r17;
        r0.f16131a = r5;
        r0 = r17;
        r8.visitAttribute(r0);
        r17 = r4;
        goto L_0x04cf;
    L_0x04e2:
        r8.visitEnd();
    L_0x04e5:
        r4 = r22 + -1;
        r22 = r4;
        goto L_0x0380;
    L_0x04eb:
        r0 = r48;
        r1 = r21;
        r4 = r0.readUnsignedShort(r1);
        r37 = r21 + 2;
        r38 = r4;
    L_0x04f7:
        if (r38 <= 0) goto L_0x0f39;
    L_0x04f9:
        r25 = r37 + 6;
        r0 = r48;
        r1 = r37;
        r21 = r0.readUnsignedShort(r1);
        r4 = r37 + 2;
        r0 = r48;
        r12 = r0.readUTF8(r4, r9);
        r4 = r37 + 4;
        r0 = r48;
        r13 = r0.readUTF8(r4, r9);
        r14 = 0;
        r23 = 0;
        r22 = 0;
        r18 = 0;
        r17 = 0;
        r16 = 0;
        r20 = 0;
        r19 = 0;
        r15 = 0;
        r4 = r37 + 6;
        r0 = r48;
        r4 = r0.readUnsignedShort(r4);
        r5 = r37 + 8;
        r24 = r4;
        r37 = r5;
    L_0x0531:
        if (r24 <= 0) goto L_0x0697;
    L_0x0533:
        r0 = r48;
        r1 = r37;
        r6 = r0.readUTF8(r1, r9);
        r4 = r37 + 2;
        r0 = r48;
        r8 = r0.readInt(r4);
        r7 = r37 + 6;
        r4 = "Code";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x0578;
    L_0x054d:
        if (r40 != 0) goto L_0x0f7f;
    L_0x054f:
        r4 = r16;
        r5 = r17;
        r6 = r18;
        r10 = r15;
        r11 = r7;
        r15 = r21;
        r16 = r20;
        r17 = r22;
        r18 = r23;
    L_0x055f:
        r8 = r8 + r7;
        r7 = r24 + -1;
        r24 = r7;
        r19 = r11;
        r21 = r15;
        r37 = r8;
        r20 = r16;
        r22 = r17;
        r23 = r18;
        r16 = r4;
        r15 = r10;
        r17 = r5;
        r18 = r6;
        goto L_0x0531;
    L_0x0578:
        r4 = "Exceptions";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x0592;
    L_0x0580:
        r4 = r16;
        r5 = r17;
        r6 = r18;
        r10 = r7;
        r11 = r19;
        r15 = r21;
        r16 = r20;
        r17 = r22;
        r18 = r23;
        goto L_0x055f;
    L_0x0592:
        r4 = "Signature";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x05b2;
    L_0x059a:
        r0 = r48;
        r14 = r0.readUTF8(r7, r9);
        r4 = r16;
        r5 = r17;
        r6 = r18;
        r10 = r15;
        r11 = r19;
        r15 = r21;
        r16 = r20;
        r17 = r22;
        r18 = r23;
        goto L_0x055f;
    L_0x05b2:
        r4 = "Deprecated";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x05cf;
    L_0x05ba:
        r4 = 131072; // 0x20000 float:1.83671E-40 double:6.47582E-319;
        r4 = r4 | r21;
        r5 = r17;
        r6 = r18;
        r10 = r15;
        r11 = r19;
        r15 = r4;
        r17 = r22;
        r18 = r23;
        r4 = r16;
        r16 = r20;
        goto L_0x055f;
    L_0x05cf:
        r4 = "RuntimeVisibleAnnotations";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x05ea;
    L_0x05d7:
        r4 = r16;
        r5 = r17;
        r6 = r18;
        r10 = r15;
        r11 = r19;
        r15 = r21;
        r16 = r20;
        r17 = r22;
        r18 = r7;
        goto L_0x055f;
    L_0x05ea:
        r4 = "AnnotationDefault";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x0604;
    L_0x05f2:
        r4 = r16;
        r5 = r17;
        r6 = r7;
        r10 = r15;
        r11 = r19;
        r18 = r23;
        r16 = r20;
        r17 = r22;
        r15 = r21;
        goto L_0x055f;
    L_0x0604:
        r4 = "Synthetic";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x0623;
    L_0x060c:
        r4 = 266240; // 0x41000 float:3.73082E-40 double:1.3154E-318;
        r4 = r4 | r21;
        r5 = r17;
        r6 = r18;
        r10 = r15;
        r11 = r19;
        r15 = r4;
        r17 = r22;
        r18 = r23;
        r4 = r16;
        r16 = r20;
        goto L_0x055f;
    L_0x0623:
        r4 = "RuntimeInvisibleAnnotations";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x063e;
    L_0x062b:
        r4 = r16;
        r5 = r17;
        r6 = r18;
        r10 = r15;
        r11 = r19;
        r15 = r21;
        r16 = r20;
        r17 = r7;
        r18 = r23;
        goto L_0x055f;
    L_0x063e:
        r4 = "RuntimeVisibleParameterAnnotations";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x0658;
    L_0x0646:
        r4 = r16;
        r5 = r7;
        r6 = r18;
        r10 = r15;
        r11 = r19;
        r17 = r22;
        r16 = r20;
        r15 = r21;
        r18 = r23;
        goto L_0x055f;
    L_0x0658:
        r4 = "RuntimeInvisibleParameterAnnotations";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x0672;
    L_0x0660:
        r4 = r7;
        r5 = r17;
        r6 = r18;
        r10 = r15;
        r11 = r19;
        r16 = r20;
        r15 = r21;
        r17 = r22;
        r18 = r23;
        goto L_0x055f;
    L_0x0672:
        r10 = -1;
        r11 = 0;
        r4 = r48;
        r5 = r50;
        r4 = r4.m19315a(r5, r6, r7, r8, r9, r10, r11);
        if (r4 == 0) goto L_0x0f7f;
    L_0x067e:
        r0 = r20;
        r4.f16131a = r0;
        r5 = r17;
        r6 = r18;
        r10 = r15;
        r11 = r19;
        r15 = r21;
        r17 = r22;
        r18 = r23;
        r47 = r16;
        r16 = r4;
        r4 = r47;
        goto L_0x055f;
    L_0x0697:
        if (r15 != 0) goto L_0x06d1;
    L_0x0699:
        r4 = 0;
        r5 = r15;
        r15 = r4;
    L_0x069c:
        r10 = r49;
        r11 = r21;
        r11 = r10.visitMethod(r11, r12, r13, r14, r15);
        if (r11 == 0) goto L_0x078a;
    L_0x06a6:
        r4 = r11 instanceof org.codehaus.jackson.org.objectweb.asm.MethodWriter;
        if (r4 == 0) goto L_0x0711;
    L_0x06aa:
        r4 = r11;
        r4 = (org.codehaus.jackson.org.objectweb.asm.MethodWriter) r4;
        r6 = r4.f16242b;
        r6 = r6.f16148J;
        r0 = r48;
        if (r6 != r0) goto L_0x0711;
    L_0x06b5:
        r6 = r4.f16247g;
        if (r14 != r6) goto L_0x0711;
    L_0x06b9:
        r6 = 0;
        if (r15 != 0) goto L_0x06ee;
    L_0x06bc:
        r5 = r4.f16250j;
        if (r5 != 0) goto L_0x06ec;
    L_0x06c0:
        r5 = 1;
    L_0x06c1:
        if (r5 == 0) goto L_0x0711;
    L_0x06c3:
        r0 = r25;
        r4.f16248h = r0;
        r5 = r37 - r25;
        r4.f16249i = r5;
    L_0x06cb:
        r4 = r38 + -1;
        r38 = r4;
        goto L_0x04f7;
    L_0x06d1:
        r0 = r48;
        r4 = r0.readUnsignedShort(r15);
        r6 = new java.lang.String[r4];
        r5 = r15 + 2;
        r4 = 0;
    L_0x06dc:
        r7 = r6.length;
        if (r4 >= r7) goto L_0x0f7c;
    L_0x06df:
        r0 = r48;
        r7 = r0.readClass(r5, r9);
        r6[r4] = r7;
        r5 = r5 + 2;
        r4 = r4 + 1;
        goto L_0x06dc;
    L_0x06ec:
        r5 = 0;
        goto L_0x06c1;
    L_0x06ee:
        r7 = r15.length;
        r8 = r4.f16250j;
        if (r7 != r8) goto L_0x0f79;
    L_0x06f3:
        r6 = 1;
        r7 = r15.length;
        r7 = r7 + -1;
        r47 = r7;
        r7 = r5;
        r5 = r47;
    L_0x06fc:
        if (r5 < 0) goto L_0x0f79;
    L_0x06fe:
        r7 = r7 + -2;
        r8 = r4.f16251k;
        r8 = r8[r5];
        r0 = r48;
        r10 = r0.readUnsignedShort(r7);
        if (r8 == r10) goto L_0x070e;
    L_0x070c:
        r5 = 0;
        goto L_0x06c1;
    L_0x070e:
        r5 = r5 + -1;
        goto L_0x06fc;
    L_0x0711:
        if (r18 == 0) goto L_0x0724;
    L_0x0713:
        r4 = r11.visitAnnotationDefault();
        r5 = 0;
        r0 = r48;
        r1 = r18;
        r0.m19311a(r1, r9, r5, r4);
        if (r4 == 0) goto L_0x0724;
    L_0x0721:
        r4.visitEnd();
    L_0x0724:
        r4 = 1;
        r7 = r4;
    L_0x0726:
        if (r7 < 0) goto L_0x0761;
    L_0x0728:
        if (r7 != 0) goto L_0x0758;
    L_0x072a:
        r5 = r22;
    L_0x072c:
        if (r5 == 0) goto L_0x075d;
    L_0x072e:
        r0 = r48;
        r4 = r0.readUnsignedShort(r5);
        r5 = r5 + 2;
        r47 = r4;
        r4 = r5;
        r5 = r47;
    L_0x073b:
        if (r5 <= 0) goto L_0x075d;
    L_0x073d:
        r6 = r4 + 2;
        r8 = 1;
        r0 = r48;
        r10 = r0.readUTF8(r4, r9);
        if (r7 == 0) goto L_0x075b;
    L_0x0748:
        r4 = 1;
    L_0x0749:
        r4 = r11.visitAnnotation(r10, r4);
        r0 = r48;
        r6 = r0.m19312a(r6, r9, r8, r4);
        r4 = r5 + -1;
        r5 = r4;
        r4 = r6;
        goto L_0x073b;
    L_0x0758:
        r5 = r23;
        goto L_0x072c;
    L_0x075b:
        r4 = 0;
        goto L_0x0749;
    L_0x075d:
        r4 = r7 + -1;
        r7 = r4;
        goto L_0x0726;
    L_0x0761:
        if (r17 == 0) goto L_0x076c;
    L_0x0763:
        r10 = 1;
        r6 = r48;
        r7 = r17;
        r8 = r13;
        r6.m19316a(r7, r8, r9, r10, r11);
    L_0x076c:
        if (r16 == 0) goto L_0x0777;
    L_0x076e:
        r10 = 0;
        r6 = r48;
        r7 = r16;
        r8 = r13;
        r6.m19316a(r7, r8, r9, r10, r11);
    L_0x0777:
        if (r20 == 0) goto L_0x078a;
    L_0x0779:
        r0 = r20;
        r4 = r0.f16131a;
        r5 = 0;
        r0 = r20;
        r0.f16131a = r5;
        r0 = r20;
        r11.visitAttribute(r0);
        r20 = r4;
        goto L_0x0777;
    L_0x078a:
        if (r11 == 0) goto L_0x0f32;
    L_0x078c:
        if (r19 == 0) goto L_0x0f32;
    L_0x078e:
        r0 = r48;
        r1 = r19;
        r42 = r0.readUnsignedShort(r1);
        r4 = r19 + 2;
        r0 = r48;
        r43 = r0.readUnsignedShort(r4);
        r4 = r19 + 4;
        r0 = r48;
        r44 = r0.readInt(r4);
        r34 = r19 + 8;
        r45 = r34 + r44;
        r11.visitCode();
        r4 = r44 + 2;
        r0 = new org.codehaus.jackson.org.objectweb.asm.Label[r4];
        r20 = r0;
        r4 = r44 + 1;
        r0 = r48;
        r1 = r20;
        r0.readLabel(r4, r1);
        r5 = r34;
    L_0x07be:
        r0 = r45;
        if (r5 >= r0) goto L_0x0899;
    L_0x07c2:
        r7 = r5 - r34;
        r4 = r41[r5];
        r4 = r4 & 255;
        r6 = org.codehaus.jackson.org.objectweb.asm.ClassWriter.f16139a;
        r4 = r6[r4];
        switch(r4) {
            case 0: goto L_0x07d3;
            case 1: goto L_0x088d;
            case 2: goto L_0x0891;
            case 3: goto L_0x088d;
            case 4: goto L_0x07d3;
            case 5: goto L_0x0891;
            case 6: goto L_0x0891;
            case 7: goto L_0x0895;
            case 8: goto L_0x07d6;
            case 9: goto L_0x07e9;
            case 10: goto L_0x088d;
            case 11: goto L_0x0891;
            case 12: goto L_0x0891;
            case 13: goto L_0x080c;
            case 14: goto L_0x0851;
            case 15: goto L_0x07cf;
            case 16: goto L_0x07fc;
            default: goto L_0x07cf;
        };
    L_0x07cf:
        r4 = r5 + 4;
    L_0x07d1:
        r5 = r4;
        goto L_0x07be;
    L_0x07d3:
        r4 = r5 + 1;
        goto L_0x07d1;
    L_0x07d6:
        r4 = r5 + 1;
        r0 = r48;
        r4 = r0.readShort(r4);
        r4 = r4 + r7;
        r0 = r48;
        r1 = r20;
        r0.readLabel(r4, r1);
        r4 = r5 + 3;
        goto L_0x07d1;
    L_0x07e9:
        r4 = r5 + 1;
        r0 = r48;
        r4 = r0.readInt(r4);
        r4 = r4 + r7;
        r0 = r48;
        r1 = r20;
        r0.readLabel(r4, r1);
        r4 = r5 + 5;
        goto L_0x07d1;
    L_0x07fc:
        r4 = r5 + 1;
        r4 = r41[r4];
        r4 = r4 & 255;
        r6 = 132; // 0x84 float:1.85E-43 double:6.5E-322;
        if (r4 != r6) goto L_0x0809;
    L_0x0806:
        r4 = r5 + 6;
        goto L_0x07d1;
    L_0x0809:
        r4 = r5 + 4;
        goto L_0x07d1;
    L_0x080c:
        r4 = r5 + 4;
        r5 = r7 & 3;
        r5 = r4 - r5;
        r0 = r48;
        r4 = r0.readInt(r5);
        r4 = r4 + r7;
        r0 = r48;
        r1 = r20;
        r0.readLabel(r4, r1);
        r4 = r5 + 8;
        r0 = r48;
        r4 = r0.readInt(r4);
        r6 = r5 + 4;
        r0 = r48;
        r6 = r0.readInt(r6);
        r4 = r4 - r6;
        r4 = r4 + 1;
        r5 = r5 + 12;
        r47 = r4;
        r4 = r5;
        r5 = r47;
    L_0x083a:
        if (r5 <= 0) goto L_0x07d1;
    L_0x083c:
        r0 = r48;
        r6 = r0.readInt(r4);
        r6 = r6 + r7;
        r0 = r48;
        r1 = r20;
        r0.readLabel(r6, r1);
        r6 = r4 + 4;
        r4 = r5 + -1;
        r5 = r4;
        r4 = r6;
        goto L_0x083a;
    L_0x0851:
        r4 = r5 + 4;
        r5 = r7 & 3;
        r5 = r4 - r5;
        r0 = r48;
        r4 = r0.readInt(r5);
        r4 = r4 + r7;
        r0 = r48;
        r1 = r20;
        r0.readLabel(r4, r1);
        r4 = r5 + 4;
        r0 = r48;
        r4 = r0.readInt(r4);
        r5 = r5 + 8;
        r47 = r4;
        r4 = r5;
        r5 = r47;
    L_0x0874:
        if (r5 <= 0) goto L_0x07d1;
    L_0x0876:
        r6 = r4 + 4;
        r0 = r48;
        r6 = r0.readInt(r6);
        r6 = r6 + r7;
        r0 = r48;
        r1 = r20;
        r0.readLabel(r6, r1);
        r6 = r4 + 8;
        r4 = r5 + -1;
        r5 = r4;
        r4 = r6;
        goto L_0x0874;
    L_0x088d:
        r4 = r5 + 2;
        goto L_0x07d1;
    L_0x0891:
        r4 = r5 + 3;
        goto L_0x07d1;
    L_0x0895:
        r4 = r5 + 5;
        goto L_0x07d1;
    L_0x0899:
        r0 = r48;
        r4 = r0.readUnsignedShort(r5);
        r5 = r5 + 2;
    L_0x08a1:
        if (r4 <= 0) goto L_0x08f4;
    L_0x08a3:
        r0 = r48;
        r6 = r0.readUnsignedShort(r5);
        r0 = r48;
        r1 = r20;
        r6 = r0.readLabel(r6, r1);
        r7 = r5 + 2;
        r0 = r48;
        r7 = r0.readUnsignedShort(r7);
        r0 = r48;
        r1 = r20;
        r7 = r0.readLabel(r7, r1);
        r8 = r5 + 4;
        r0 = r48;
        r8 = r0.readUnsignedShort(r8);
        r0 = r48;
        r1 = r20;
        r8 = r0.readLabel(r8, r1);
        r10 = r5 + 6;
        r0 = r48;
        r10 = r0.readUnsignedShort(r10);
        if (r10 != 0) goto L_0x08e4;
    L_0x08db:
        r10 = 0;
        r11.visitTryCatchBlock(r6, r7, r8, r10);
    L_0x08df:
        r5 = r5 + 8;
        r4 = r4 + -1;
        goto L_0x08a1;
    L_0x08e4:
        r0 = r48;
        r14 = r0.f16135a;
        r10 = r14[r10];
        r0 = r48;
        r10 = r0.readUTF8(r10, r9);
        r11.visitTryCatchBlock(r6, r7, r8, r10);
        goto L_0x08df;
    L_0x08f4:
        r31 = 0;
        r30 = 0;
        r10 = 0;
        r8 = 0;
        r7 = 0;
        r33 = 0;
        r25 = 0;
        r22 = 0;
        r32 = 0;
        r26 = 0;
        r24 = 0;
        r23 = 0;
        r29 = 1;
        r28 = 0;
        r0 = r48;
        r4 = r0.readUnsignedShort(r5);
        r5 = r5 + 2;
        r35 = r4;
        r36 = r5;
    L_0x0919:
        if (r35 <= 0) goto L_0x0a71;
    L_0x091b:
        r0 = r48;
        r1 = r36;
        r46 = r0.readUTF8(r1, r9);
        r4 = "LocalVariableTable";
        r0 = r46;
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0979;
    L_0x092d:
        if (r39 != 0) goto L_0x0f54;
    L_0x092f:
        r4 = r36 + 6;
        r5 = r36 + 6;
        r0 = r48;
        r5 = r0.readUnsignedShort(r5);
        r6 = r36 + 8;
    L_0x093b:
        if (r5 <= 0) goto L_0x0f5f;
    L_0x093d:
        r0 = r48;
        r14 = r0.readUnsignedShort(r6);
        r15 = r20[r14];
        if (r15 != 0) goto L_0x0959;
    L_0x0947:
        r0 = r48;
        r1 = r20;
        r15 = r0.readLabel(r14, r1);
        r0 = r15.f16211a;
        r16 = r0;
        r16 = r16 | 1;
        r0 = r16;
        r15.f16211a = r0;
    L_0x0959:
        r15 = r6 + 2;
        r0 = r48;
        r15 = r0.readUnsignedShort(r15);
        r14 = r14 + r15;
        r15 = r20[r14];
        if (r15 != 0) goto L_0x0974;
    L_0x0966:
        r0 = r48;
        r1 = r20;
        r14 = r0.readLabel(r14, r1);
        r15 = r14.f16211a;
        r15 = r15 | 1;
        r14.f16211a = r15;
    L_0x0974:
        r6 = r6 + 10;
        r5 = r5 + -1;
        goto L_0x093b;
    L_0x0979:
        r4 = "LocalVariableTypeTable";
        r0 = r46;
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x09aa;
    L_0x0983:
        r4 = r36 + 6;
        r5 = r7;
        r6 = r8;
        r7 = r10;
        r8 = r4;
        r4 = r29;
        r10 = r31;
    L_0x098d:
        r14 = r36 + 2;
        r0 = r48;
        r14 = r0.readInt(r14);
        r14 = r14 + 6;
        r15 = r36 + r14;
        r14 = r35 + -1;
        r29 = r4;
        r30 = r8;
        r31 = r10;
        r35 = r14;
        r36 = r15;
        r8 = r6;
        r10 = r7;
        r7 = r5;
        goto L_0x0919;
    L_0x09aa:
        r4 = "LineNumberTable";
        r0 = r46;
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x09eb;
    L_0x09b4:
        if (r39 != 0) goto L_0x0f54;
    L_0x09b6:
        r4 = r36 + 6;
        r0 = r48;
        r4 = r0.readUnsignedShort(r4);
        r5 = r36 + 8;
    L_0x09c0:
        if (r4 <= 0) goto L_0x0f54;
    L_0x09c2:
        r0 = r48;
        r6 = r0.readUnsignedShort(r5);
        r14 = r20[r6];
        if (r14 != 0) goto L_0x09da;
    L_0x09cc:
        r0 = r48;
        r1 = r20;
        r14 = r0.readLabel(r6, r1);
        r15 = r14.f16211a;
        r15 = r15 | 1;
        r14.f16211a = r15;
    L_0x09da:
        r6 = r20[r6];
        r14 = r5 + 2;
        r0 = r48;
        r14 = r0.readUnsignedShort(r14);
        r6.f16212b = r14;
        r5 = r5 + 4;
        r4 = r4 + -1;
        goto L_0x09c0;
    L_0x09eb:
        r4 = "StackMapTable";
        r0 = r46;
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0a16;
    L_0x09f5:
        r4 = r51 & 4;
        if (r4 != 0) goto L_0x0f54;
    L_0x09f9:
        r6 = r36 + 8;
        r4 = r36 + 2;
        r0 = r48;
        r5 = r0.readInt(r4);
        r4 = r36 + 6;
        r0 = r48;
        r4 = r0.readUnsignedShort(r4);
        r7 = r6;
        r8 = r30;
        r10 = r31;
        r6 = r5;
        r5 = r4;
        r4 = r29;
        goto L_0x098d;
    L_0x0a16:
        r4 = "StackMap";
        r0 = r46;
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0a3d;
    L_0x0a20:
        r4 = r51 & 4;
        if (r4 != 0) goto L_0x0f54;
    L_0x0a24:
        r7 = r36 + 8;
        r4 = r36 + 2;
        r0 = r48;
        r6 = r0.readInt(r4);
        r4 = r36 + 6;
        r0 = r48;
        r5 = r0.readUnsignedShort(r4);
        r4 = 0;
        r8 = r30;
        r10 = r31;
        goto L_0x098d;
    L_0x0a3d:
        r4 = 0;
        r6 = r4;
        r5 = r28;
    L_0x0a41:
        r0 = r50;
        r4 = r0.length;
        if (r6 >= r4) goto L_0x0f6c;
    L_0x0a46:
        r4 = r50[r6];
        r4 = r4.type;
        r0 = r46;
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0f69;
    L_0x0a52:
        r14 = r50[r6];
        r16 = r36 + 6;
        r4 = r36 + 2;
        r0 = r48;
        r17 = r0.readInt(r4);
        r19 = r34 + -8;
        r15 = r48;
        r18 = r9;
        r4 = r14.read(r15, r16, r17, r18, r19, r20);
        if (r4 == 0) goto L_0x0f69;
    L_0x0a6a:
        r4.f16131a = r5;
    L_0x0a6c:
        r5 = r6 + 1;
        r6 = r5;
        r5 = r4;
        goto L_0x0a41;
    L_0x0a71:
        if (r10 == 0) goto L_0x0f4c;
    L_0x0a73:
        r0 = r43;
        r14 = new java.lang.Object[r0];
        r0 = r42;
        r0 = new java.lang.Object[r0];
        r16 = r0;
        if (r27 == 0) goto L_0x0f48;
    L_0x0a7f:
        r5 = 0;
        r4 = r21 & 8;
        if (r4 != 0) goto L_0x0f45;
    L_0x0a84:
        r4 = "<init>";
        r4 = r4.equals(r12);
        if (r4 == 0) goto L_0x0acc;
    L_0x0a8c:
        r4 = 1;
        r6 = org.codehaus.jackson.org.objectweb.asm.Opcodes.UNINITIALIZED_THIS;
        r14[r5] = r6;
    L_0x0a91:
        r5 = 1;
        r6 = r5;
    L_0x0a93:
        r12 = r6 + 1;
        r5 = r13.charAt(r6);
        switch(r5) {
            case 66: goto L_0x0adc;
            case 67: goto L_0x0adc;
            case 68: goto L_0x0af7;
            case 70: goto L_0x0ae5;
            case 73: goto L_0x0adc;
            case 74: goto L_0x0aee;
            case 76: goto L_0x0b2f;
            case 83: goto L_0x0adc;
            case 90: goto L_0x0adc;
            case 91: goto L_0x0b00;
            default: goto L_0x0a9c;
        };
    L_0x0a9c:
        r5 = -1;
        r6 = r10;
    L_0x0a9e:
        r12 = r10 + r8;
        r12 = r12 + -2;
        if (r6 >= r12) goto L_0x0b49;
    L_0x0aa4:
        r12 = r41[r6];
        r13 = 8;
        if (r12 != r13) goto L_0x0ac9;
    L_0x0aaa:
        r12 = r6 + 1;
        r0 = r48;
        r12 = r0.readUnsignedShort(r12);
        if (r12 < 0) goto L_0x0ac9;
    L_0x0ab4:
        r0 = r44;
        if (r12 >= r0) goto L_0x0ac9;
    L_0x0ab8:
        r13 = r34 + r12;
        r13 = r41[r13];
        r13 = r13 & 255;
        r15 = 187; // 0xbb float:2.62E-43 double:9.24E-322;
        if (r13 != r15) goto L_0x0ac9;
    L_0x0ac2:
        r0 = r48;
        r1 = r20;
        r0.readLabel(r12, r1);
    L_0x0ac9:
        r6 = r6 + 1;
        goto L_0x0a9e;
    L_0x0acc:
        r4 = 1;
        r0 = r48;
        r6 = r0.header;
        r6 = r6 + 2;
        r0 = r48;
        r6 = r0.readClass(r6, r9);
        r14[r5] = r6;
        goto L_0x0a91;
    L_0x0adc:
        r5 = r4 + 1;
        r6 = org.codehaus.jackson.org.objectweb.asm.Opcodes.INTEGER;
        r14[r4] = r6;
        r4 = r5;
        r6 = r12;
        goto L_0x0a93;
    L_0x0ae5:
        r5 = r4 + 1;
        r6 = org.codehaus.jackson.org.objectweb.asm.Opcodes.FLOAT;
        r14[r4] = r6;
        r4 = r5;
        r6 = r12;
        goto L_0x0a93;
    L_0x0aee:
        r5 = r4 + 1;
        r6 = org.codehaus.jackson.org.objectweb.asm.Opcodes.LONG;
        r14[r4] = r6;
        r4 = r5;
        r6 = r12;
        goto L_0x0a93;
    L_0x0af7:
        r5 = r4 + 1;
        r6 = org.codehaus.jackson.org.objectweb.asm.Opcodes.DOUBLE;
        r14[r4] = r6;
        r4 = r5;
        r6 = r12;
        goto L_0x0a93;
    L_0x0b00:
        r5 = r13.charAt(r12);
        r15 = 91;
        if (r5 != r15) goto L_0x0b0b;
    L_0x0b08:
        r12 = r12 + 1;
        goto L_0x0b00;
    L_0x0b0b:
        r5 = r13.charAt(r12);
        r15 = 76;
        if (r5 != r15) goto L_0x0b21;
    L_0x0b13:
        r5 = r12 + 1;
    L_0x0b15:
        r12 = r13.charAt(r5);
        r15 = 59;
        if (r12 == r15) goto L_0x0b20;
    L_0x0b1d:
        r5 = r5 + 1;
        goto L_0x0b15;
    L_0x0b20:
        r12 = r5;
    L_0x0b21:
        r5 = r4 + 1;
        r12 = r12 + 1;
        r6 = r13.substring(r6, r12);
        r14[r4] = r6;
        r4 = r5;
        r6 = r12;
        goto L_0x0a93;
    L_0x0b2f:
        r5 = r13.charAt(r12);
        r15 = 59;
        if (r5 == r15) goto L_0x0b3a;
    L_0x0b37:
        r12 = r12 + 1;
        goto L_0x0b2f;
    L_0x0b3a:
        r5 = r4 + 1;
        r15 = r6 + 1;
        r6 = r12 + 1;
        r12 = r13.substring(r15, r12);
        r14[r4] = r12;
        r4 = r5;
        goto L_0x0a93;
    L_0x0b49:
        r22 = r4;
        r4 = r14;
    L_0x0b4c:
        r14 = r4;
        r15 = r26;
        r23 = r32;
        r13 = r22;
        r6 = r5;
        r22 = r33;
        r4 = r7;
        r7 = r10;
        r33 = r34;
    L_0x0b5a:
        r0 = r33;
        r1 = r45;
        if (r0 >= r1) goto L_0x0e76;
    L_0x0b60:
        r35 = r33 - r34;
        r5 = r20[r35];
        if (r5 == 0) goto L_0x0b74;
    L_0x0b66:
        r11.visitLabel(r5);
        if (r39 != 0) goto L_0x0b74;
    L_0x0b6b:
        r8 = r5.f16212b;
        if (r8 <= 0) goto L_0x0b74;
    L_0x0b6f:
        r8 = r5.f16212b;
        r11.visitLineNumber(r8, r5);
    L_0x0b74:
        r32 = r4;
    L_0x0b76:
        if (r14 == 0) goto L_0x0c87;
    L_0x0b78:
        r0 = r35;
        if (r6 == r0) goto L_0x0b7f;
    L_0x0b7c:
        r4 = -1;
        if (r6 != r4) goto L_0x0c87;
    L_0x0b7f:
        if (r29 == 0) goto L_0x0b83;
    L_0x0b81:
        if (r27 == 0) goto L_0x0bb5;
    L_0x0b83:
        r12 = -1;
        r11.visitFrame(r12, r13, r14, r15, r16);
    L_0x0b87:
        if (r32 <= 0) goto L_0x0c84;
    L_0x0b89:
        if (r29 == 0) goto L_0x0bc4;
    L_0x0b8b:
        r18 = r7 + 1;
        r4 = r41[r7];
        r12 = r4 & 255;
        r22 = r6;
    L_0x0b93:
        r6 = 0;
        r4 = 64;
        if (r12 >= r4) goto L_0x0bcc;
    L_0x0b98:
        r5 = 3;
        r4 = 0;
        r7 = r5;
        r5 = r4;
        r4 = r12;
    L_0x0b9d:
        r4 = r4 + 1;
        r4 = r4 + r22;
        r0 = r48;
        r1 = r20;
        r0.readLabel(r4, r1);
        r8 = r32 + -1;
        r15 = r5;
        r23 = r6;
        r22 = r7;
        r32 = r8;
        r6 = r4;
        r7 = r18;
        goto L_0x0b76;
    L_0x0bb5:
        r4 = -1;
        if (r6 == r4) goto L_0x0b87;
    L_0x0bb8:
        r21 = r11;
        r24 = r14;
        r25 = r15;
        r26 = r16;
        r21.visitFrame(r22, r23, r24, r25, r26);
        goto L_0x0b87;
    L_0x0bc4:
        r12 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        r4 = -1;
        r22 = r4;
        r18 = r7;
        goto L_0x0b93;
    L_0x0bcc:
        r4 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r12 >= r4) goto L_0x0bdf;
    L_0x0bd0:
        r4 = r12 + -64;
        r17 = 0;
        r15 = r48;
        r19 = r9;
        r18 = r15.m19313a(r16, r17, r18, r19, r20);
        r7 = 4;
        r5 = 1;
        goto L_0x0b9d;
    L_0x0bdf:
        r0 = r48;
        r1 = r18;
        r21 = r0.readUnsignedShort(r1);
        r18 = r18 + 2;
        r4 = 247; // 0xf7 float:3.46E-43 double:1.22E-321;
        if (r12 != r4) goto L_0x0bfe;
    L_0x0bed:
        r17 = 0;
        r15 = r48;
        r19 = r9;
        r18 = r15.m19313a(r16, r17, r18, r19, r20);
        r5 = 4;
        r4 = 1;
        r7 = r5;
        r5 = r4;
        r4 = r21;
        goto L_0x0b9d;
    L_0x0bfe:
        r4 = 248; // 0xf8 float:3.48E-43 double:1.225E-321;
        if (r12 < r4) goto L_0x0c11;
    L_0x0c02:
        r4 = 251; // 0xfb float:3.52E-43 double:1.24E-321;
        if (r12 >= r4) goto L_0x0c11;
    L_0x0c06:
        r6 = 2;
        r5 = 251 - r12;
        r13 = r13 - r5;
        r4 = 0;
        r7 = r6;
        r6 = r5;
        r5 = r4;
        r4 = r21;
        goto L_0x0b9d;
    L_0x0c11:
        r4 = 251; // 0xfb float:3.52E-43 double:1.24E-321;
        if (r12 != r4) goto L_0x0c1c;
    L_0x0c15:
        r5 = 3;
        r4 = 0;
        r7 = r5;
        r5 = r4;
        r4 = r21;
        goto L_0x0b9d;
    L_0x0c1c:
        r4 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        if (r12 >= r4) goto L_0x0c4a;
    L_0x0c20:
        if (r27 == 0) goto L_0x0c3a;
    L_0x0c22:
        r4 = r13;
    L_0x0c23:
        r5 = r12 + -251;
        r8 = r18;
        r7 = r4;
        r4 = r5;
    L_0x0c29:
        if (r4 <= 0) goto L_0x0c3c;
    L_0x0c2b:
        r15 = r7 + 1;
        r5 = r48;
        r6 = r14;
        r10 = r20;
        r8 = r5.m19313a(r6, r7, r8, r9, r10);
        r4 = r4 + -1;
        r7 = r15;
        goto L_0x0c29;
    L_0x0c3a:
        r4 = 0;
        goto L_0x0c23;
    L_0x0c3c:
        r6 = 1;
        r5 = r12 + -251;
        r13 = r13 + r5;
        r4 = 0;
        r7 = r6;
        r18 = r8;
        r6 = r5;
        r5 = r4;
        r4 = r21;
        goto L_0x0b9d;
    L_0x0c4a:
        r13 = 0;
        r0 = r48;
        r1 = r18;
        r12 = r0.readUnsignedShort(r1);
        r8 = r18 + 2;
        r7 = 0;
        r4 = r12;
    L_0x0c57:
        if (r4 <= 0) goto L_0x0c68;
    L_0x0c59:
        r15 = r7 + 1;
        r5 = r48;
        r6 = r14;
        r10 = r20;
        r8 = r5.m19313a(r6, r7, r8, r9, r10);
        r4 = r4 + -1;
        r7 = r15;
        goto L_0x0c57;
    L_0x0c68:
        r0 = r48;
        r4 = r0.readUnsignedShort(r8);
        r18 = r8 + 2;
        r17 = 0;
        r5 = r4;
    L_0x0c73:
        if (r5 <= 0) goto L_0x0f3d;
    L_0x0c75:
        r6 = r17 + 1;
        r15 = r48;
        r19 = r9;
        r18 = r15.m19313a(r16, r17, r18, r19, r20);
        r5 = r5 + -1;
        r17 = r6;
        goto L_0x0c73;
    L_0x0c84:
        r14 = 0;
        goto L_0x0b76;
    L_0x0c87:
        r4 = r41[r33];
        r8 = r4 & 255;
        r4 = org.codehaus.jackson.org.objectweb.asm.ClassWriter.f16139a;
        r4 = r4[r8];
        switch(r4) {
            case 0: goto L_0x0cab;
            case 1: goto L_0x0dc2;
            case 2: goto L_0x0dcd;
            case 3: goto L_0x0db5;
            case 4: goto L_0x0cb1;
            case 5: goto L_0x0e56;
            case 6: goto L_0x0e04;
            case 7: goto L_0x0e04;
            case 8: goto L_0x0ccf;
            case 9: goto L_0x0ce1;
            case 10: goto L_0x0ddc;
            case 11: goto L_0x0def;
            case 12: goto L_0x0e65;
            case 13: goto L_0x0d23;
            case 14: goto L_0x0d6e;
            case 15: goto L_0x0c92;
            case 16: goto L_0x0cf5;
            default: goto L_0x0c92;
        };
    L_0x0c92:
        r4 = r33 + 1;
        r0 = r48;
        r4 = r0.readClass(r4, r9);
        r5 = r33 + 3;
        r5 = r41[r5];
        r5 = r5 & 255;
        r11.visitMultiANewArrayInsn(r4, r5);
        r5 = r33 + 4;
    L_0x0ca5:
        r4 = r32;
        r33 = r5;
        goto L_0x0b5a;
    L_0x0cab:
        r11.visitInsn(r8);
        r5 = r33 + 1;
        goto L_0x0ca5;
    L_0x0cb1:
        r4 = 54;
        if (r8 <= r4) goto L_0x0cc3;
    L_0x0cb5:
        r4 = r8 + -59;
        r5 = r4 >> 2;
        r5 = r5 + 54;
        r4 = r4 & 3;
        r11.visitVarInsn(r5, r4);
    L_0x0cc0:
        r5 = r33 + 1;
        goto L_0x0ca5;
    L_0x0cc3:
        r4 = r8 + -26;
        r5 = r4 >> 2;
        r5 = r5 + 21;
        r4 = r4 & 3;
        r11.visitVarInsn(r5, r4);
        goto L_0x0cc0;
    L_0x0ccf:
        r4 = r33 + 1;
        r0 = r48;
        r4 = r0.readShort(r4);
        r4 = r4 + r35;
        r4 = r20[r4];
        r11.visitJumpInsn(r8, r4);
        r5 = r33 + 3;
        goto L_0x0ca5;
    L_0x0ce1:
        r4 = r8 + -33;
        r5 = r33 + 1;
        r0 = r48;
        r5 = r0.readInt(r5);
        r5 = r5 + r35;
        r5 = r20[r5];
        r11.visitJumpInsn(r4, r5);
        r5 = r33 + 5;
        goto L_0x0ca5;
    L_0x0cf5:
        r4 = r33 + 1;
        r4 = r41[r4];
        r4 = r4 & 255;
        r5 = 132; // 0x84 float:1.85E-43 double:6.5E-322;
        if (r4 != r5) goto L_0x0d15;
    L_0x0cff:
        r4 = r33 + 2;
        r0 = r48;
        r4 = r0.readUnsignedShort(r4);
        r5 = r33 + 4;
        r0 = r48;
        r5 = r0.readShort(r5);
        r11.visitIincInsn(r4, r5);
        r5 = r33 + 6;
        goto L_0x0ca5;
    L_0x0d15:
        r5 = r33 + 2;
        r0 = r48;
        r5 = r0.readUnsignedShort(r5);
        r11.visitVarInsn(r4, r5);
        r5 = r33 + 4;
        goto L_0x0ca5;
    L_0x0d23:
        r4 = r33 + 4;
        r5 = r35 & 3;
        r4 = r4 - r5;
        r0 = r48;
        r5 = r0.readInt(r4);
        r8 = r35 + r5;
        r5 = r4 + 4;
        r0 = r48;
        r10 = r0.readInt(r5);
        r5 = r4 + 8;
        r0 = r48;
        r12 = r0.readInt(r5);
        r5 = r4 + 12;
        r4 = r12 - r10;
        r4 = r4 + 1;
        r0 = new org.codehaus.jackson.org.objectweb.asm.Label[r4];
        r17 = r0;
        r4 = 0;
    L_0x0d4b:
        r0 = r17;
        r0 = r0.length;
        r18 = r0;
        r0 = r18;
        if (r4 >= r0) goto L_0x0d65;
    L_0x0d54:
        r0 = r48;
        r18 = r0.readInt(r5);
        r18 = r18 + r35;
        r18 = r20[r18];
        r17[r4] = r18;
        r5 = r5 + 4;
        r4 = r4 + 1;
        goto L_0x0d4b;
    L_0x0d65:
        r4 = r20[r8];
        r0 = r17;
        r11.visitTableSwitchInsn(r10, r12, r4, r0);
        goto L_0x0ca5;
    L_0x0d6e:
        r4 = r33 + 4;
        r5 = r35 & 3;
        r4 = r4 - r5;
        r0 = r48;
        r5 = r0.readInt(r4);
        r8 = r35 + r5;
        r5 = r4 + 4;
        r0 = r48;
        r10 = r0.readInt(r5);
        r5 = r4 + 8;
        r12 = new int[r10];
        r10 = new org.codehaus.jackson.org.objectweb.asm.Label[r10];
        r4 = 0;
    L_0x0d8a:
        r0 = r12.length;
        r17 = r0;
        r0 = r17;
        if (r4 >= r0) goto L_0x0dae;
    L_0x0d91:
        r0 = r48;
        r17 = r0.readInt(r5);
        r12[r4] = r17;
        r17 = r5 + 4;
        r0 = r48;
        r1 = r17;
        r17 = r0.readInt(r1);
        r17 = r17 + r35;
        r17 = r20[r17];
        r10[r4] = r17;
        r5 = r5 + 8;
        r4 = r4 + 1;
        goto L_0x0d8a;
    L_0x0dae:
        r4 = r20[r8];
        r11.visitLookupSwitchInsn(r4, r12, r10);
        goto L_0x0ca5;
    L_0x0db5:
        r4 = r33 + 1;
        r4 = r41[r4];
        r4 = r4 & 255;
        r11.visitVarInsn(r8, r4);
        r5 = r33 + 2;
        goto L_0x0ca5;
    L_0x0dc2:
        r4 = r33 + 1;
        r4 = r41[r4];
        r11.visitIntInsn(r8, r4);
        r5 = r33 + 2;
        goto L_0x0ca5;
    L_0x0dcd:
        r4 = r33 + 1;
        r0 = r48;
        r4 = r0.readShort(r4);
        r11.visitIntInsn(r8, r4);
        r5 = r33 + 3;
        goto L_0x0ca5;
    L_0x0ddc:
        r4 = r33 + 1;
        r4 = r41[r4];
        r4 = r4 & 255;
        r0 = r48;
        r4 = r0.readConst(r4, r9);
        r11.visitLdcInsn(r4);
        r5 = r33 + 2;
        goto L_0x0ca5;
    L_0x0def:
        r4 = r33 + 1;
        r0 = r48;
        r4 = r0.readUnsignedShort(r4);
        r0 = r48;
        r4 = r0.readConst(r4, r9);
        r11.visitLdcInsn(r4);
        r5 = r33 + 3;
        goto L_0x0ca5;
    L_0x0e04:
        r0 = r48;
        r4 = r0.f16135a;
        r5 = r33 + 1;
        r0 = r48;
        r5 = r0.readUnsignedShort(r5);
        r5 = r4[r5];
        r4 = 186; // 0xba float:2.6E-43 double:9.2E-322;
        if (r8 != r4) goto L_0x0e39;
    L_0x0e16:
        r4 = "java/lang/dyn/Dynamic";
    L_0x0e18:
        r0 = r48;
        r10 = r0.readUTF8(r5, r9);
        r5 = r5 + 2;
        r0 = r48;
        r5 = r0.readUTF8(r5, r9);
        r12 = 182; // 0xb6 float:2.55E-43 double:9.0E-322;
        if (r8 >= r12) goto L_0x0e4e;
    L_0x0e2a:
        r11.visitFieldInsn(r8, r4, r10, r5);
    L_0x0e2d:
        r4 = 185; // 0xb9 float:2.59E-43 double:9.14E-322;
        if (r8 == r4) goto L_0x0e35;
    L_0x0e31:
        r4 = 186; // 0xba float:2.6E-43 double:9.2E-322;
        if (r8 != r4) goto L_0x0e52;
    L_0x0e35:
        r5 = r33 + 5;
        goto L_0x0ca5;
    L_0x0e39:
        r0 = r48;
        r4 = r0.readClass(r5, r9);
        r0 = r48;
        r10 = r0.f16135a;
        r5 = r5 + 2;
        r0 = r48;
        r5 = r0.readUnsignedShort(r5);
        r5 = r10[r5];
        goto L_0x0e18;
    L_0x0e4e:
        r11.visitMethodInsn(r8, r4, r10, r5);
        goto L_0x0e2d;
    L_0x0e52:
        r5 = r33 + 3;
        goto L_0x0ca5;
    L_0x0e56:
        r4 = r33 + 1;
        r0 = r48;
        r4 = r0.readClass(r4, r9);
        r11.visitTypeInsn(r8, r4);
        r5 = r33 + 3;
        goto L_0x0ca5;
    L_0x0e65:
        r4 = r33 + 1;
        r4 = r41[r4];
        r4 = r4 & 255;
        r5 = r33 + 2;
        r5 = r41[r5];
        r11.visitIincInsn(r4, r5);
        r5 = r33 + 3;
        goto L_0x0ca5;
    L_0x0e76:
        r4 = r45 - r34;
        r4 = r20[r4];
        if (r4 == 0) goto L_0x0e7f;
    L_0x0e7c:
        r11.visitLabel(r4);
    L_0x0e7f:
        if (r39 != 0) goto L_0x0f18;
    L_0x0e81:
        if (r31 == 0) goto L_0x0f18;
    L_0x0e83:
        r4 = 0;
        if (r30 == 0) goto L_0x0eb5;
    L_0x0e86:
        r0 = r48;
        r1 = r30;
        r4 = r0.readUnsignedShort(r1);
        r5 = r4 * 3;
        r6 = r30 + 2;
        r4 = new int[r5];
    L_0x0e94:
        if (r5 <= 0) goto L_0x0eb5;
    L_0x0e96:
        r5 = r5 + -1;
        r7 = r6 + 6;
        r4[r5] = r7;
        r5 = r5 + -1;
        r7 = r6 + 8;
        r0 = r48;
        r7 = r0.readUnsignedShort(r7);
        r4[r5] = r7;
        r5 = r5 + -1;
        r0 = r48;
        r7 = r0.readUnsignedShort(r6);
        r4[r5] = r7;
        r6 = r6 + 10;
        goto L_0x0e94;
    L_0x0eb5:
        r0 = r48;
        r1 = r31;
        r5 = r0.readUnsignedShort(r1);
        r6 = r31 + 2;
        r7 = r6;
        r6 = r5;
    L_0x0ec1:
        if (r6 <= 0) goto L_0x0f18;
    L_0x0ec3:
        r0 = r48;
        r8 = r0.readUnsignedShort(r7);
        r5 = r7 + 2;
        r0 = r48;
        r10 = r0.readUnsignedShort(r5);
        r5 = r7 + 8;
        r0 = r48;
        r17 = r0.readUnsignedShort(r5);
        r14 = 0;
        if (r4 == 0) goto L_0x0ef6;
    L_0x0edc:
        r5 = 0;
    L_0x0edd:
        r12 = r4.length;
        if (r5 >= r12) goto L_0x0ef6;
    L_0x0ee0:
        r12 = r4[r5];
        if (r12 != r8) goto L_0x0f15;
    L_0x0ee4:
        r12 = r5 + 1;
        r12 = r4[r12];
        r0 = r17;
        if (r12 != r0) goto L_0x0f15;
    L_0x0eec:
        r5 = r5 + 2;
        r5 = r4[r5];
        r0 = r48;
        r14 = r0.readUTF8(r5, r9);
    L_0x0ef6:
        r5 = r7 + 4;
        r0 = r48;
        r12 = r0.readUTF8(r5, r9);
        r5 = r7 + 6;
        r0 = r48;
        r13 = r0.readUTF8(r5, r9);
        r15 = r20[r8];
        r5 = r8 + r10;
        r16 = r20[r5];
        r11.visitLocalVariable(r12, r13, r14, r15, r16, r17);
        r7 = r7 + 10;
        r5 = r6 + -1;
        r6 = r5;
        goto L_0x0ec1;
    L_0x0f15:
        r5 = r5 + 3;
        goto L_0x0edd;
    L_0x0f18:
        if (r28 == 0) goto L_0x0f2b;
    L_0x0f1a:
        r0 = r28;
        r4 = r0.f16131a;
        r5 = 0;
        r0 = r28;
        r0.f16131a = r5;
        r0 = r28;
        r11.visitAttribute(r0);
        r28 = r4;
        goto L_0x0f18;
    L_0x0f2b:
        r0 = r42;
        r1 = r43;
        r11.visitMaxs(r0, r1);
    L_0x0f32:
        if (r11 == 0) goto L_0x06cb;
    L_0x0f34:
        r11.visitEnd();
        goto L_0x06cb;
    L_0x0f39:
        r49.visitEnd();
        return;
    L_0x0f3d:
        r5 = r4;
        r6 = r12;
        r7 = r13;
        r13 = r12;
        r4 = r21;
        goto L_0x0b9d;
    L_0x0f45:
        r4 = r5;
        goto L_0x0a91;
    L_0x0f48:
        r4 = r22;
        goto L_0x0a9c;
    L_0x0f4c:
        r16 = r23;
        r4 = r24;
        r5 = r25;
        goto L_0x0b4c;
    L_0x0f54:
        r4 = r29;
        r5 = r7;
        r6 = r8;
        r7 = r10;
        r8 = r30;
        r10 = r31;
        goto L_0x098d;
    L_0x0f5f:
        r5 = r7;
        r6 = r8;
        r7 = r10;
        r8 = r30;
        r10 = r4;
        r4 = r29;
        goto L_0x098d;
    L_0x0f69:
        r4 = r5;
        goto L_0x0a6c;
    L_0x0f6c:
        r4 = r29;
        r6 = r8;
        r28 = r5;
        r8 = r30;
        r5 = r7;
        r7 = r10;
        r10 = r31;
        goto L_0x098d;
    L_0x0f79:
        r5 = r6;
        goto L_0x06c1;
    L_0x0f7c:
        r15 = r6;
        goto L_0x069c;
    L_0x0f7f:
        r4 = r16;
        r5 = r17;
        r6 = r18;
        r10 = r15;
        r11 = r19;
        r15 = r21;
        r16 = r20;
        r17 = r22;
        r18 = r23;
        goto L_0x055f;
    L_0x0f92:
        r4 = r15;
        r5 = r16;
        r6 = r17;
        r7 = r18;
        r8 = r19;
        goto L_0x03d2;
    L_0x0f9d:
        r4 = r18;
        r5 = r19;
        r6 = r20;
        r7 = r21;
        r8 = r22;
        r10 = r23;
        r11 = r24;
        r18 = r25;
        r19 = r26;
        goto L_0x011c;
    L_0x0fb1:
        r4 = r18;
        r5 = r19;
        goto L_0x0193;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.org.objectweb.asm.ClassReader.accept(org.codehaus.jackson.org.objectweb.asm.ClassVisitor, org.codehaus.jackson.org.objectweb.asm.Attribute[], int):void");
    }

    public int getAccess() {
        return readUnsignedShort(this.header);
    }

    public String getClassName() {
        return readClass(this.header + SKIP_DEBUG, new char[this.f16138d]);
    }

    public String[] getInterfaces() {
        int i = this.header + 6;
        int readUnsignedShort = readUnsignedShort(i);
        String[] strArr = new String[readUnsignedShort];
        if (readUnsignedShort > 0) {
            char[] cArr = new char[this.f16138d];
            for (int i2 = 0; i2 < readUnsignedShort; i2 += SKIP_CODE) {
                i += SKIP_DEBUG;
                strArr[i2] = readClass(i, cArr);
            }
        }
        return strArr;
    }

    public int getItem(int i) {
        return this.f16135a[i];
    }

    public String getSuperName() {
        int i = this.f16135a[readUnsignedShort(this.header + SKIP_FRAMES)];
        return i == 0 ? null : readUTF8(i, new char[this.f16138d]);
    }

    public int readByte(int i) {
        return this.f16136b[i] & Util.MASK_8BIT;
    }

    public String readClass(int i, char[] cArr) {
        return readUTF8(this.f16135a[readUnsignedShort(i)], cArr);
    }

    public Object readConst(int i, char[] cArr) {
        int i2 = this.f16135a[i];
        switch (this.f16136b[i2 - 1]) {
            case Type.BYTE /*3*/:
                return new Integer(readInt(i2));
            case SKIP_FRAMES /*4*/:
                return new Float(Float.intBitsToFloat(readInt(i2)));
            case Type.INT /*5*/:
                return new Long(readLong(i2));
            case Type.FLOAT /*6*/:
                return new Double(Double.longBitsToDouble(readLong(i2)));
            case Type.LONG /*7*/:
                return Type.getObjectType(readUTF8(i2, cArr));
            default:
                return readUTF8(i2, cArr);
        }
    }

    public int readInt(int i) {
        byte[] bArr = this.f16136b;
        return (bArr[i + 3] & Util.MASK_8BIT) | ((((bArr[i] & Util.MASK_8BIT) << 24) | ((bArr[i + SKIP_CODE] & Util.MASK_8BIT) << 16)) | ((bArr[i + SKIP_DEBUG] & Util.MASK_8BIT) << EXPAND_FRAMES));
    }

    protected Label readLabel(int i, Label[] labelArr) {
        if (labelArr[i] == null) {
            labelArr[i] = new Label();
        }
        return labelArr[i];
    }

    public long readLong(int i) {
        return (((long) readInt(i)) << 32) | (((long) readInt(i + SKIP_FRAMES)) & ExpandableHListView.aZ);
    }

    public short readShort(int i) {
        byte[] bArr = this.f16136b;
        return (short) ((bArr[i + SKIP_CODE] & Util.MASK_8BIT) | ((bArr[i] & Util.MASK_8BIT) << EXPAND_FRAMES));
    }

    public String readUTF8(int i, char[] cArr) {
        int readUnsignedShort = readUnsignedShort(i);
        String str = this.f16137c[readUnsignedShort];
        if (str != null) {
            return str;
        }
        int i2 = this.f16135a[readUnsignedShort];
        String[] strArr = this.f16137c;
        str = m19314a(i2 + SKIP_DEBUG, readUnsignedShort(i2), cArr);
        strArr[readUnsignedShort] = str;
        return str;
    }

    public int readUnsignedShort(int i) {
        byte[] bArr = this.f16136b;
        return (bArr[i + SKIP_CODE] & Util.MASK_8BIT) | ((bArr[i] & Util.MASK_8BIT) << EXPAND_FRAMES);
    }
}
