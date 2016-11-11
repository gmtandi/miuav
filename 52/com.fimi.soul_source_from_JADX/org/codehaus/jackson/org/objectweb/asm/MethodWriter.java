package org.codehaus.jackson.org.objectweb.asm;

import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.facebook.imageutils.JfifUtil;
import com.fimi.soul.drone.p107c.p108a.p109a.bj;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.open.yyb.AppbarJsBridge;
import com.xiaomi.infra.galaxy.fds.android.util.Consts;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.io.CharacterEscapes;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;
import org.p122a.p123a.C2915a;

class MethodWriter implements MethodVisitor {
    private int f16222A;
    private Handler f16223B;
    private Handler f16224C;
    private int f16225D;
    private ByteVector f16226E;
    private int f16227F;
    private ByteVector f16228G;
    private int f16229H;
    private ByteVector f16230I;
    private Attribute f16231J;
    private boolean f16232K;
    private int f16233L;
    private final int f16234M;
    private Label f16235N;
    private Label f16236O;
    private Label f16237P;
    private int f16238Q;
    private int f16239R;
    private int f16240S;
    MethodWriter f16241a;
    final ClassWriter f16242b;
    private int f16243c;
    private final int f16244d;
    private final int f16245e;
    private final String f16246f;
    String f16247g;
    int f16248h;
    int f16249i;
    int f16250j;
    int[] f16251k;
    private ByteVector f16252l;
    private AnnotationWriter f16253m;
    private AnnotationWriter f16254n;
    private AnnotationWriter[] f16255o;
    private AnnotationWriter[] f16256p;
    private Attribute f16257q;
    private ByteVector f16258r;
    private int f16259s;
    private int f16260t;
    private int f16261u;
    private ByteVector f16262v;
    private int f16263w;
    private int[] f16264x;
    private int f16265y;
    private int[] f16266z;

    MethodWriter(ClassWriter classWriter, int i, String str, String str2, String str3, String[] strArr, boolean z, boolean z2) {
        int i2;
        int i3 = 0;
        this.f16258r = new ByteVector();
        if (classWriter.f16140A == null) {
            classWriter.f16140A = this;
        } else {
            classWriter.f16141B.f16241a = this;
        }
        classWriter.f16141B = this;
        this.f16242b = classWriter;
        this.f16243c = i;
        this.f16244d = classWriter.newUTF8(str);
        this.f16245e = classWriter.newUTF8(str2);
        this.f16246f = str2;
        this.f16247g = str3;
        if (strArr != null && strArr.length > 0) {
            this.f16250j = strArr.length;
            this.f16251k = new int[this.f16250j];
            for (i2 = 0; i2 < this.f16250j; i2++) {
                this.f16251k[i2] = classWriter.newClass(strArr[i2]);
            }
        }
        if (!z2) {
            i3 = z ? 1 : 2;
        }
        this.f16234M = i3;
        if (z || z2) {
            if (z2 && "<init>".equals(str)) {
                this.f16243c |= AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START;
            }
            i2 = Type.getArgumentsAndReturnSizes(this.f16246f) >> 2;
            if ((i & 8) != 0) {
                i2--;
            }
            this.f16260t = i2;
            this.f16235N = new Label();
            Label label = this.f16235N;
            label.f16211a |= 8;
            visitLabel(this.f16235N);
        }
    }

    static int m19366a(byte[] bArr, int i) {
        return ((((bArr[i] & Util.MASK_8BIT) << 24) | ((bArr[i + 1] & Util.MASK_8BIT) << 16)) | ((bArr[i + 2] & Util.MASK_8BIT) << 8)) | (bArr[i + 3] & Util.MASK_8BIT);
    }

    static int m19367a(int[] iArr, int[] iArr2, int i, int i2) {
        int i3 = i2 - i;
        int i4 = 0;
        while (i4 < iArr.length) {
            if (i < iArr[i4] && iArr[i4] <= i2) {
                i3 += iArr2[i4];
            } else if (i2 < iArr[i4] && iArr[i4] <= i) {
                i3 -= iArr2[i4];
            }
            i4++;
        }
        return i3;
    }

    private void m19368a(int i, int i2) {
        while (i < i2) {
            int i3 = this.f16266z[i];
            int i4 = -268435456 & i3;
            if (i4 == 0) {
                i4 = i3 & 1048575;
                switch (i3 & 267386880) {
                    case 24117248:
                        this.f16262v.putByte(7).putShort(this.f16242b.newClass(this.f16242b.f16143E[i4].f16206g));
                        break;
                    case 25165824:
                        this.f16262v.putByte(8).putShort(this.f16242b.f16143E[i4].f16204c);
                        break;
                    default:
                        this.f16262v.putByte(i4);
                        break;
                }
            }
            StringBuffer stringBuffer = new StringBuffer();
            i4 >>= 28;
            while (true) {
                int i5 = i4 - 1;
                if (i4 > 0) {
                    stringBuffer.append('[');
                    i4 = i5;
                } else {
                    if ((i3 & 267386880) != 24117248) {
                        switch (i3 & 15) {
                            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                                stringBuffer.append('I');
                                break;
                            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                                stringBuffer.append('F');
                                break;
                            case Type.BYTE /*3*/:
                                stringBuffer.append('D');
                                break;
                            case Type.ARRAY /*9*/:
                                stringBuffer.append('Z');
                                break;
                            case Type.OBJECT /*10*/:
                                stringBuffer.append('B');
                                break;
                            case Opcodes.T_LONG /*11*/:
                                stringBuffer.append('C');
                                break;
                            case Opcodes.FCONST_1 /*12*/:
                                stringBuffer.append('S');
                                break;
                            default:
                                stringBuffer.append('J');
                                break;
                        }
                    }
                    stringBuffer.append('L');
                    stringBuffer.append(this.f16242b.f16143E[i3 & 1048575].f16206g);
                    stringBuffer.append(';');
                    this.f16262v.putByte(7).putShort(this.f16242b.newClass(stringBuffer.toString()));
                }
            }
            i++;
        }
    }

    private void m19369a(int i, int i2, int i3) {
        int i4 = (i2 + 3) + i3;
        if (this.f16266z == null || this.f16266z.length < i4) {
            this.f16266z = new int[i4];
        }
        this.f16266z[0] = i;
        this.f16266z[1] = i2;
        this.f16266z[2] = i3;
        this.f16265y = 3;
    }

    private void m19370a(int i, Label label) {
        Edge edge = new Edge();
        edge.f16174a = i;
        edge.f16175b = label;
        edge.f16176c = this.f16237P.f16220j;
        this.f16237P.f16220j = edge;
    }

    private void m19371a(Object obj) {
        if (obj instanceof String) {
            this.f16262v.putByte(7).putShort(this.f16242b.newClass((String) obj));
        } else if (obj instanceof Integer) {
            this.f16262v.putByte(((Integer) obj).intValue());
        } else {
            this.f16262v.putByte(8).putShort(((Label) obj).f16213c);
        }
    }

    private void m19372a(Label label, Label[] labelArr) {
        int i = 0;
        if (this.f16237P != null) {
            if (this.f16234M == 0) {
                this.f16237P.f16218h.m19349a((int) Opcodes.LOOKUPSWITCH, 0, null, null);
                m19370a(0, label);
                Label a = label.m19359a();
                a.f16211a |= 16;
                for (int i2 = 0; i2 < labelArr.length; i2++) {
                    m19370a(0, labelArr[i2]);
                    Label a2 = labelArr[i2].m19359a();
                    a2.f16211a |= 16;
                }
            } else {
                this.f16238Q--;
                m19370a(this.f16238Q, label);
                while (i < labelArr.length) {
                    m19370a(this.f16238Q, labelArr[i]);
                    i++;
                }
            }
            m19381e();
        }
    }

    static void m19373a(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >>> 8);
        bArr[i + 1] = (byte) i2;
    }

    static void m19374a(int[] iArr, int[] iArr2, Label label) {
        if ((label.f16211a & 4) == 0) {
            label.f16213c = m19367a(iArr, iArr2, 0, label.f16213c);
            label.f16211a |= 4;
        }
    }

    static short m19375b(byte[] bArr, int i) {
        return (short) (((bArr[i] & Util.MASK_8BIT) << 8) | (bArr[i + 1] & Util.MASK_8BIT));
    }

    private void m19376b() {
        if (this.f16264x != null) {
            if (this.f16262v == null) {
                this.f16262v = new ByteVector();
            }
            m19379c();
            this.f16261u++;
        }
        this.f16264x = this.f16266z;
        this.f16266z = null;
    }

    private void m19377b(Frame frame) {
        int i = 0;
        int[] iArr = frame.f16189c;
        int[] iArr2 = frame.f16190d;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < iArr.length) {
            int i5 = iArr[i2];
            if (i5 == 16777216) {
                i4++;
            } else {
                i3 += i4 + 1;
                i4 = 0;
            }
            if (i5 == 16777220 || i5 == 16777219) {
                i2++;
            }
            i2++;
        }
        i2 = 0;
        i4 = 0;
        while (i2 < iArr2.length) {
            i5 = iArr2[i2];
            i4++;
            if (i5 == 16777220 || i5 == 16777219) {
                i2++;
            }
            i2++;
        }
        m19369a(frame.f16188b.f16213c, i3, i4);
        i2 = 0;
        while (i3 > 0) {
            i4 = iArr[i2];
            int[] iArr3 = this.f16266z;
            int i6 = this.f16265y;
            this.f16265y = i6 + 1;
            iArr3[i6] = i4;
            if (i4 == 16777220 || i4 == 16777219) {
                i2++;
            }
            i2++;
            i3--;
        }
        while (i < iArr2.length) {
            i2 = iArr2[i];
            int[] iArr4 = this.f16266z;
            i4 = this.f16265y;
            this.f16265y = i4 + 1;
            iArr4[i4] = i2;
            if (i2 == 16777220 || i2 == 16777219) {
                i++;
            }
            i++;
        }
        m19376b();
    }

    static int m19378c(byte[] bArr, int i) {
        return ((bArr[i] & Util.MASK_8BIT) << 8) | (bArr[i + 1] & Util.MASK_8BIT);
    }

    private void m19379c() {
        int i = 64;
        int i2 = 0;
        int i3 = this.f16266z[1];
        int i4 = this.f16266z[2];
        if ((this.f16242b.f16149b & Util.MASK_16BIT) < 50) {
            this.f16262v.putShort(this.f16266z[0]).putShort(i3);
            m19368a(3, i3 + 3);
            this.f16262v.putShort(i4);
            m19368a(i3 + 3, (i3 + 3) + i4);
            return;
        }
        int i5;
        int i6;
        int i7 = this.f16264x[1];
        int i8 = this.f16261u == 0 ? this.f16266z[0] : (this.f16266z[0] - this.f16264x[0]) - 1;
        if (i4 == 0) {
            i5 = i3 - i7;
            switch (i5) {
                case AppbarJsBridge.Code_Java_Exception /*-3*/:
                case CharacterEscapes.ESCAPE_CUSTOM /*-2*/:
                case Opcodes.F_NEW /*-1*/:
                    i = 248;
                    i7 = i3;
                    break;
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    if (i8 >= 64) {
                        i = 251;
                        break;
                    } else {
                        i = 0;
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                case Type.BYTE /*3*/:
                    i = SmileConstants.INT_MARKER_END_OF_STRING;
                    break;
                default:
                    i = Util.MASK_8BIT;
                    break;
            }
            i6 = i7;
        } else if (i3 == i7 && i4 == 1) {
            if (i8 >= 63) {
                i = 247;
            }
            i5 = 0;
            i6 = i7;
        } else {
            i5 = 0;
            i = Util.MASK_8BIT;
            i6 = i7;
        }
        if (i != 255) {
            i7 = 3;
            while (i2 < i6) {
                if (this.f16266z[i7] != this.f16264x[i7]) {
                    i = Util.MASK_8BIT;
                } else {
                    i7++;
                    i2++;
                }
            }
        }
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.f16262v.putByte(i8);
            case SmileConstants.TOKEN_PREFIX_TINY_ASCII /*64*/:
                this.f16262v.putByte(i8 + 64);
                m19368a(i3 + 3, i3 + 4);
            case 247:
                this.f16262v.putByte(247).putShort(i8);
                m19368a(i3 + 3, i3 + 4);
            case 248:
                this.f16262v.putByte(i5 + 251).putShort(i8);
            case 251:
                this.f16262v.putByte(251).putShort(i8);
            case SmileConstants.INT_MARKER_END_OF_STRING /*252*/:
                this.f16262v.putByte(i5 + 251).putShort(i8);
                m19368a(i6 + 3, i3 + 3);
            default:
                this.f16262v.putByte(Util.MASK_8BIT).putShort(i8).putShort(i3);
                m19368a(3, i3 + 3);
                this.f16262v.putShort(i4);
                m19368a(i3 + 3, (i3 + 3) + i4);
        }
    }

    private void m19380d() {
        byte[] bArr = this.f16258r.f16133a;
        int[] iArr = new int[0];
        int[] iArr2 = new int[0];
        boolean[] zArr = new boolean[this.f16258r.f16134b];
        int i = 3;
        while (true) {
            int c;
            if (i == 3) {
                i = 2;
            }
            int i2 = i;
            i = 0;
            while (i < bArr.length) {
                int i3 = bArr[i] & Util.MASK_8BIT;
                int i4 = 0;
                switch (ClassWriter.f16139a[i3]) {
                    case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                        i++;
                        break;
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    case Type.BYTE /*3*/:
                    case Type.OBJECT /*10*/:
                        i += 2;
                        break;
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    case Type.INT /*5*/:
                    case Type.FLOAT /*6*/:
                    case Opcodes.T_LONG /*11*/:
                    case Opcodes.FCONST_1 /*12*/:
                        i += 3;
                        break;
                    case Type.LONG /*7*/:
                        i += 5;
                        break;
                    case Type.DOUBLE /*8*/:
                        if (i3 > bj.f6779b) {
                            i3 = i3 < JfifUtil.MARKER_SOS ? i3 - 49 : i3 - 20;
                            c = m19378c(bArr, i + 1) + i;
                        } else {
                            c = m19375b(bArr, i + 1) + i;
                        }
                        c = m19367a(iArr, iArr2, i, c);
                        if ((c < -32768 || c > 32767) && !zArr[i]) {
                            c = (i3 == Opcodes.GOTO || i3 == Opcodes.JSR) ? 2 : 5;
                            zArr[i] = true;
                        } else {
                            c = 0;
                        }
                        i += 3;
                        i4 = c;
                        break;
                    case Type.ARRAY /*9*/:
                        i += 5;
                        break;
                    case Opcodes.FCONST_2 /*13*/:
                        if (i2 == 1) {
                            i4 = -(m19367a(iArr, iArr2, 0, i) & 3);
                        } else if (!zArr[i]) {
                            i4 = i & 3;
                            zArr[i] = true;
                        }
                        i = (i + 4) - (i & 3);
                        i += (((m19366a(bArr, i + 8) - m19366a(bArr, i + 4)) + 1) * 4) + 12;
                        break;
                    case Opcodes.DCONST_0 /*14*/:
                        if (i2 == 1) {
                            i4 = -(m19367a(iArr, iArr2, 0, i) & 3);
                        } else if (!zArr[i]) {
                            i4 = i & 3;
                            zArr[i] = true;
                        }
                        i = (i + 4) - (i & 3);
                        i += (m19366a(bArr, i + 4) * 8) + 8;
                        break;
                    case Segment.TOKENS_PER_SEGMENT /*16*/:
                        if ((bArr[i + 1] & Util.MASK_8BIT) != Opcodes.IINC) {
                            i += 4;
                            break;
                        } else {
                            i += 6;
                            break;
                        }
                    default:
                        i += 4;
                        break;
                }
                if (i4 != 0) {
                    Object obj = new int[(iArr.length + 1)];
                    Object obj2 = new int[(iArr2.length + 1)];
                    System.arraycopy(iArr, 0, obj, 0, iArr.length);
                    System.arraycopy(iArr2, 0, obj2, 0, iArr2.length);
                    obj[iArr.length] = i;
                    obj2[iArr2.length] = i4;
                    if (i4 > 0) {
                        i2 = 3;
                        iArr2 = obj2;
                        iArr = obj;
                    } else {
                        iArr2 = obj2;
                        iArr = obj;
                    }
                }
            }
            if (i2 < 3) {
                i2--;
            }
            if (i2 == 0) {
                ByteVector byteVector = new ByteVector(this.f16258r.f16134b);
                i = 0;
                while (i < this.f16258r.f16134b) {
                    c = bArr[i] & Util.MASK_8BIT;
                    int i5;
                    switch (ClassWriter.f16139a[c]) {
                        case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                        case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                            byteVector.putByte(c);
                            i++;
                            continue;
                        case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        case Type.BYTE /*3*/:
                        case Type.OBJECT /*10*/:
                            byteVector.putByteArray(bArr, i, 2);
                            i += 2;
                            continue;
                        case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        case Type.INT /*5*/:
                        case Type.FLOAT /*6*/:
                        case Opcodes.T_LONG /*11*/:
                        case Opcodes.FCONST_1 /*12*/:
                            byteVector.putByteArray(bArr, i, 3);
                            i += 3;
                            continue;
                        case Type.LONG /*7*/:
                            byteVector.putByteArray(bArr, i, 5);
                            i += 5;
                            continue;
                        case Type.DOUBLE /*8*/:
                            if (c > bj.f6779b) {
                                c = c < JfifUtil.MARKER_SOS ? c - 49 : c - 20;
                                i2 = m19378c(bArr, i + 1) + i;
                            } else {
                                i2 = m19375b(bArr, i + 1) + i;
                            }
                            i3 = m19367a(iArr, iArr2, i, i2);
                            if (zArr[i]) {
                                if (c == Opcodes.GOTO) {
                                    byteVector.putByte(C2799f.f14282t);
                                    i2 = i3;
                                } else if (c == Opcodes.JSR) {
                                    byteVector.putByte(bj.f6779b);
                                    i2 = i3;
                                } else {
                                    byteVector.putByte(c <= Opcodes.IF_ACMPNE ? ((c + 1) ^ 1) - 1 : c ^ 1);
                                    byteVector.putShort(8);
                                    byteVector.putByte(C2799f.f14282t);
                                    i2 = i3 - 3;
                                }
                                byteVector.putInt(i2);
                            } else {
                                byteVector.putByte(c);
                                byteVector.putShort(i3);
                            }
                            i += 3;
                            continue;
                        case Type.ARRAY /*9*/:
                            i2 = m19367a(iArr, iArr2, i, m19366a(bArr, i + 1) + i);
                            byteVector.putByte(c);
                            byteVector.putInt(i2);
                            i += 5;
                            continue;
                        case Opcodes.FCONST_2 /*13*/:
                            i2 = (i + 4) - (i & 3);
                            byteVector.putByte(Opcodes.TABLESWITCH);
                            byteVector.putByteArray(null, 0, (4 - (byteVector.f16134b % 4)) % 4);
                            i2 += 4;
                            byteVector.putInt(m19367a(iArr, iArr2, i, m19366a(bArr, i2) + i));
                            c = m19366a(bArr, i2);
                            i3 = i2 + 4;
                            byteVector.putInt(c);
                            i2 = (m19366a(bArr, i3) - c) + 1;
                            c = i3 + 4;
                            byteVector.putInt(m19366a(bArr, c - 4));
                            i5 = i2;
                            i2 = c;
                            c = i5;
                            while (c > 0) {
                                i3 = i2 + 4;
                                byteVector.putInt(m19367a(iArr, iArr2, i, i + m19366a(bArr, i2)));
                                c--;
                                i2 = i3;
                            }
                            break;
                        case Opcodes.DCONST_0 /*14*/:
                            i2 = (i + 4) - (i & 3);
                            byteVector.putByte(Opcodes.LOOKUPSWITCH);
                            byteVector.putByteArray(null, 0, (4 - (byteVector.f16134b % 4)) % 4);
                            i3 = i2 + 4;
                            byteVector.putInt(m19367a(iArr, iArr2, i, m19366a(bArr, i2) + i));
                            i2 = m19366a(bArr, i3);
                            c = i3 + 4;
                            byteVector.putInt(i2);
                            i5 = i2;
                            i2 = c;
                            c = i5;
                            while (c > 0) {
                                byteVector.putInt(m19366a(bArr, i2));
                                i2 += 4;
                                i3 = i2 + 4;
                                byteVector.putInt(m19367a(iArr, iArr2, i, i + m19366a(bArr, i2)));
                                c--;
                                i2 = i3;
                            }
                            break;
                        case Segment.TOKENS_PER_SEGMENT /*16*/:
                            if ((bArr[i + 1] & Util.MASK_8BIT) != Opcodes.IINC) {
                                byteVector.putByteArray(bArr, i, 4);
                                i += 4;
                                break;
                            }
                            byteVector.putByteArray(bArr, i, 6);
                            i += 6;
                            continue;
                        default:
                            byteVector.putByteArray(bArr, i, 4);
                            i += 4;
                            continue;
                    }
                    i = i2;
                }
                if (this.f16261u > 0) {
                    if (this.f16234M == 0) {
                        this.f16261u = 0;
                        this.f16262v = null;
                        this.f16264x = null;
                        this.f16266z = null;
                        Frame frame = new Frame();
                        frame.f16188b = this.f16235N;
                        frame.m19350a(this.f16242b, this.f16243c, Type.getArgumentTypes(this.f16246f), this.f16260t);
                        m19377b(frame);
                        for (Label label = this.f16235N; label != null; label = label.f16219i) {
                            i2 = label.f16213c - 3;
                            if ((label.f16211a & 32) != 0 || (i2 >= 0 && zArr[i2])) {
                                m19374a(iArr, iArr2, label);
                                m19377b(label.f16218h);
                            }
                        }
                    } else {
                        this.f16242b.f16147I = true;
                    }
                }
                for (Handler handler = this.f16223B; handler != null; handler = handler.f16201f) {
                    m19374a(iArr, iArr2, handler.f16196a);
                    m19374a(iArr, iArr2, handler.f16197b);
                    m19374a(iArr, iArr2, handler.f16198c);
                }
                c = 0;
                while (c < 2) {
                    ByteVector byteVector2 = c == 0 ? this.f16226E : this.f16228G;
                    if (byteVector2 != null) {
                        byte[] bArr2 = byteVector2.f16133a;
                        for (i = 0; i < byteVector2.f16134b; i += 10) {
                            int c2 = m19378c(bArr2, i);
                            int a = m19367a(iArr, iArr2, 0, c2);
                            m19373a(bArr2, i, a);
                            m19373a(bArr2, i + 2, m19367a(iArr, iArr2, 0, c2 + m19378c(bArr2, i + 2)) - a);
                        }
                    }
                    c++;
                }
                if (this.f16230I != null) {
                    byte[] bArr3 = this.f16230I.f16133a;
                    for (i = 0; i < this.f16230I.f16134b; i += 4) {
                        m19373a(bArr3, i, m19367a(iArr, iArr2, 0, m19378c(bArr3, i)));
                    }
                }
                for (Attribute attribute = this.f16231J; attribute != null; attribute = attribute.f16131a) {
                    Label[] labels = attribute.getLabels();
                    if (labels != null) {
                        for (i = labels.length - 1; i >= 0; i--) {
                            m19374a(iArr, iArr2, labels[i]);
                        }
                    }
                }
                this.f16258r = byteVector;
                return;
            }
            i = i2;
        }
    }

    private void m19381e() {
        if (this.f16234M == 0) {
            Label label = new Label();
            label.f16218h = new Frame();
            label.f16218h.f16188b = label;
            label.m19364a(this, this.f16258r.f16134b, this.f16258r.f16133a);
            this.f16236O.f16219i = label;
            this.f16236O = label;
        } else {
            this.f16237P.f16217g = this.f16239R;
        }
        this.f16237P = null;
    }

    final int m19382a() {
        if (this.f16248h != 0) {
            return this.f16249i + 6;
        }
        int i;
        int length;
        if (this.f16232K) {
            m19380d();
        }
        int i2 = 8;
        if (this.f16258r.f16134b > 0) {
            this.f16242b.newUTF8("Code");
            i = ((this.f16258r.f16134b + 18) + (this.f16222A * 8)) + 8;
            if (this.f16226E != null) {
                this.f16242b.newUTF8("LocalVariableTable");
                i += this.f16226E.f16134b + 8;
            }
            if (this.f16228G != null) {
                this.f16242b.newUTF8("LocalVariableTypeTable");
                i += this.f16228G.f16134b + 8;
            }
            if (this.f16230I != null) {
                this.f16242b.newUTF8("LineNumberTable");
                i += this.f16230I.f16134b + 8;
            }
            if (this.f16262v != null) {
                this.f16242b.newUTF8(((this.f16242b.f16149b & Util.MASK_16BIT) >= 50 ? 1 : null) != null ? "StackMapTable" : "StackMap");
                i2 = i + (this.f16262v.f16134b + 8);
            } else {
                i2 = i;
            }
            if (this.f16231J != null) {
                i2 += this.f16231J.m19306a(this.f16242b, this.f16258r.f16133a, this.f16258r.f16134b, this.f16259s, this.f16260t);
            }
        }
        if (this.f16250j > 0) {
            this.f16242b.newUTF8("Exceptions");
            i2 += (this.f16250j * 2) + 8;
        }
        if ((this.f16243c & Opcodes.ACC_SYNTHETIC) != 0 && ((this.f16242b.f16149b & Util.MASK_16BIT) < 49 || (this.f16243c & AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START) != 0)) {
            this.f16242b.newUTF8("Synthetic");
            i2 += 6;
        }
        if ((this.f16243c & Opcodes.ACC_DEPRECATED) != 0) {
            this.f16242b.newUTF8("Deprecated");
            i2 += 6;
        }
        if (this.f16247g != null) {
            this.f16242b.newUTF8(Consts.SIGNATURE);
            this.f16242b.newUTF8(this.f16247g);
            i2 += 8;
        }
        if (this.f16252l != null) {
            this.f16242b.newUTF8("AnnotationDefault");
            i2 += this.f16252l.f16134b + 6;
        }
        if (this.f16253m != null) {
            this.f16242b.newUTF8("RuntimeVisibleAnnotations");
            i2 += this.f16253m.m19303a() + 8;
        }
        if (this.f16254n != null) {
            this.f16242b.newUTF8("RuntimeInvisibleAnnotations");
            i2 += this.f16254n.m19303a() + 8;
        }
        if (this.f16255o != null) {
            this.f16242b.newUTF8("RuntimeVisibleParameterAnnotations");
            length = i2 + (((this.f16255o.length - this.f16240S) * 2) + 7);
            for (i = this.f16255o.length - 1; i >= this.f16240S; i--) {
                length += this.f16255o[i] == null ? 0 : this.f16255o[i].m19303a();
            }
        } else {
            length = i2;
        }
        if (this.f16256p != null) {
            this.f16242b.newUTF8("RuntimeInvisibleParameterAnnotations");
            length += ((this.f16256p.length - this.f16240S) * 2) + 7;
            for (i = this.f16256p.length - 1; i >= this.f16240S; i--) {
                length += this.f16256p[i] == null ? 0 : this.f16256p[i].m19303a();
            }
        }
        i2 = length;
        return this.f16257q != null ? i2 + this.f16257q.m19306a(this.f16242b, null, 0, -1, -1) : i2;
    }

    final void m19383a(ByteVector byteVector) {
        int i = 1;
        byteVector.putShort(((393216 | ((this.f16243c & AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START) / 64)) ^ -1) & this.f16243c).putShort(this.f16244d).putShort(this.f16245e);
        if (this.f16248h != 0) {
            byteVector.putByteArray(this.f16242b.f16148J.f16136b, this.f16248h, this.f16249i);
            return;
        }
        int i2 = this.f16258r.f16134b > 0 ? 1 : 0;
        if (this.f16250j > 0) {
            i2++;
        }
        if ((this.f16243c & Opcodes.ACC_SYNTHETIC) != 0 && ((this.f16242b.f16149b & Util.MASK_16BIT) < 49 || (this.f16243c & AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START) != 0)) {
            i2++;
        }
        if ((this.f16243c & Opcodes.ACC_DEPRECATED) != 0) {
            i2++;
        }
        if (this.f16247g != null) {
            i2++;
        }
        if (this.f16252l != null) {
            i2++;
        }
        if (this.f16253m != null) {
            i2++;
        }
        if (this.f16254n != null) {
            i2++;
        }
        if (this.f16255o != null) {
            i2++;
        }
        if (this.f16256p != null) {
            i2++;
        }
        if (this.f16257q != null) {
            i2 += this.f16257q.m19305a();
        }
        byteVector.putShort(i2);
        if (this.f16258r.f16134b > 0) {
            i2 = (this.f16258r.f16134b + 12) + (this.f16222A * 8);
            if (this.f16226E != null) {
                i2 += this.f16226E.f16134b + 8;
            }
            if (this.f16228G != null) {
                i2 += this.f16228G.f16134b + 8;
            }
            if (this.f16230I != null) {
                i2 += this.f16230I.f16134b + 8;
            }
            int i3 = this.f16262v != null ? i2 + (this.f16262v.f16134b + 8) : i2;
            if (this.f16231J != null) {
                i3 += this.f16231J.m19306a(this.f16242b, this.f16258r.f16133a, this.f16258r.f16134b, this.f16259s, this.f16260t);
            }
            byteVector.putShort(this.f16242b.newUTF8("Code")).putInt(i3);
            byteVector.putShort(this.f16259s).putShort(this.f16260t);
            byteVector.putInt(this.f16258r.f16134b).putByteArray(this.f16258r.f16133a, 0, this.f16258r.f16134b);
            byteVector.putShort(this.f16222A);
            if (this.f16222A > 0) {
                for (Handler handler = this.f16223B; handler != null; handler = handler.f16201f) {
                    byteVector.putShort(handler.f16196a.f16213c).putShort(handler.f16197b.f16213c).putShort(handler.f16198c.f16213c).putShort(handler.f16200e);
                }
            }
            i2 = this.f16226E != null ? 1 : 0;
            if (this.f16228G != null) {
                i2++;
            }
            if (this.f16230I != null) {
                i2++;
            }
            if (this.f16262v != null) {
                i2++;
            }
            if (this.f16231J != null) {
                i2 += this.f16231J.m19305a();
            }
            byteVector.putShort(i2);
            if (this.f16226E != null) {
                byteVector.putShort(this.f16242b.newUTF8("LocalVariableTable"));
                byteVector.putInt(this.f16226E.f16134b + 2).putShort(this.f16225D);
                byteVector.putByteArray(this.f16226E.f16133a, 0, this.f16226E.f16134b);
            }
            if (this.f16228G != null) {
                byteVector.putShort(this.f16242b.newUTF8("LocalVariableTypeTable"));
                byteVector.putInt(this.f16228G.f16134b + 2).putShort(this.f16227F);
                byteVector.putByteArray(this.f16228G.f16133a, 0, this.f16228G.f16134b);
            }
            if (this.f16230I != null) {
                byteVector.putShort(this.f16242b.newUTF8("LineNumberTable"));
                byteVector.putInt(this.f16230I.f16134b + 2).putShort(this.f16229H);
                byteVector.putByteArray(this.f16230I.f16133a, 0, this.f16230I.f16134b);
            }
            if (this.f16262v != null) {
                if ((this.f16242b.f16149b & Util.MASK_16BIT) < 50) {
                    i = 0;
                }
                byteVector.putShort(this.f16242b.newUTF8(i != 0 ? "StackMapTable" : "StackMap"));
                byteVector.putInt(this.f16262v.f16134b + 2).putShort(this.f16261u);
                byteVector.putByteArray(this.f16262v.f16133a, 0, this.f16262v.f16134b);
            }
            if (this.f16231J != null) {
                this.f16231J.m19307a(this.f16242b, this.f16258r.f16133a, this.f16258r.f16134b, this.f16260t, this.f16259s, byteVector);
            }
        }
        if (this.f16250j > 0) {
            byteVector.putShort(this.f16242b.newUTF8("Exceptions")).putInt((this.f16250j * 2) + 2);
            byteVector.putShort(this.f16250j);
            for (i2 = 0; i2 < this.f16250j; i2++) {
                byteVector.putShort(this.f16251k[i2]);
            }
        }
        if ((this.f16243c & Opcodes.ACC_SYNTHETIC) != 0 && ((this.f16242b.f16149b & Util.MASK_16BIT) < 49 || (this.f16243c & AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START) != 0)) {
            byteVector.putShort(this.f16242b.newUTF8("Synthetic")).putInt(0);
        }
        if ((this.f16243c & Opcodes.ACC_DEPRECATED) != 0) {
            byteVector.putShort(this.f16242b.newUTF8("Deprecated")).putInt(0);
        }
        if (this.f16247g != null) {
            byteVector.putShort(this.f16242b.newUTF8(Consts.SIGNATURE)).putInt(2).putShort(this.f16242b.newUTF8(this.f16247g));
        }
        if (this.f16252l != null) {
            byteVector.putShort(this.f16242b.newUTF8("AnnotationDefault"));
            byteVector.putInt(this.f16252l.f16134b);
            byteVector.putByteArray(this.f16252l.f16133a, 0, this.f16252l.f16134b);
        }
        if (this.f16253m != null) {
            byteVector.putShort(this.f16242b.newUTF8("RuntimeVisibleAnnotations"));
            this.f16253m.m19304a(byteVector);
        }
        if (this.f16254n != null) {
            byteVector.putShort(this.f16242b.newUTF8("RuntimeInvisibleAnnotations"));
            this.f16254n.m19304a(byteVector);
        }
        if (this.f16255o != null) {
            byteVector.putShort(this.f16242b.newUTF8("RuntimeVisibleParameterAnnotations"));
            AnnotationWriter.m19302a(this.f16255o, this.f16240S, byteVector);
        }
        if (this.f16256p != null) {
            byteVector.putShort(this.f16242b.newUTF8("RuntimeInvisibleParameterAnnotations"));
            AnnotationWriter.m19302a(this.f16256p, this.f16240S, byteVector);
        }
        if (this.f16257q != null) {
            this.f16257q.m19307a(this.f16242b, null, 0, -1, -1, byteVector);
        }
    }

    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(this.f16242b.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.f16242b, true, byteVector, byteVector, 2);
        if (z) {
            annotationWriter.f16129g = this.f16253m;
            this.f16253m = annotationWriter;
        } else {
            annotationWriter.f16129g = this.f16254n;
            this.f16254n = annotationWriter;
        }
        return annotationWriter;
    }

    public AnnotationVisitor visitAnnotationDefault() {
        this.f16252l = new ByteVector();
        return new AnnotationWriter(this.f16242b, false, this.f16252l, null, 0);
    }

    public void visitAttribute(Attribute attribute) {
        if (attribute.isCodeAttribute()) {
            attribute.f16131a = this.f16231J;
            this.f16231J = attribute;
            return;
        }
        attribute.f16131a = this.f16257q;
        this.f16257q = attribute;
    }

    public void visitCode() {
    }

    public void visitEnd() {
    }

    public void visitFieldInsn(int i, String str, String str2, String str3) {
        int i2 = 1;
        int i3 = -2;
        Item a = this.f16242b.m19333a(str, str2, str3);
        if (this.f16237P != null) {
            if (this.f16234M == 0) {
                this.f16237P.f16218h.m19349a(i, 0, this.f16242b, a);
            } else {
                char charAt = str3.charAt(0);
                switch (i) {
                    case Opcodes.GETSTATIC /*178*/:
                        i3 = this.f16238Q;
                        if (charAt == 'D' || charAt == 'J') {
                            i2 = 2;
                        }
                        i2 += i3;
                        break;
                    case Opcodes.PUTSTATIC /*179*/:
                        int i4 = this.f16238Q;
                        i2 = (charAt == 'D' || charAt == 'J') ? -2 : -1;
                        i2 += i4;
                        break;
                    case Opcodes.GETFIELD /*180*/:
                        i3 = this.f16238Q;
                        if (!(charAt == 'D' || charAt == 'J')) {
                            i2 = 0;
                        }
                        i2 += i3;
                        break;
                    default:
                        i2 = this.f16238Q;
                        if (charAt == 'D' || charAt == 'J') {
                            i3 = -3;
                        }
                        i2 += i3;
                        break;
                }
                if (i2 > this.f16239R) {
                    this.f16239R = i2;
                }
                this.f16238Q = i2;
            }
        }
        this.f16258r.m19310b(i, a.f16202a);
    }

    public void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        int i4 = 0;
        if (this.f16234M != 0) {
            if (i == -1) {
                m19369a(this.f16258r.f16134b, i2, i3);
                for (int i5 = 0; i5 < i2; i5++) {
                    int[] iArr;
                    int i6;
                    if (objArr[i5] instanceof String) {
                        iArr = this.f16266z;
                        i6 = this.f16265y;
                        this.f16265y = i6 + 1;
                        iArr[i6] = this.f16242b.m19335c((String) objArr[i5]) | 24117248;
                    } else if (objArr[i5] instanceof Integer) {
                        iArr = this.f16266z;
                        i6 = this.f16265y;
                        this.f16265y = i6 + 1;
                        iArr[i6] = ((Integer) objArr[i5]).intValue();
                    } else {
                        iArr = this.f16266z;
                        i6 = this.f16265y;
                        this.f16265y = i6 + 1;
                        iArr[i6] = this.f16242b.m19325a(C2915a.f14760f, ((Label) objArr[i5]).f16213c) | 25165824;
                    }
                }
                while (i4 < i3) {
                    int[] iArr2;
                    int i7;
                    if (objArr2[i4] instanceof String) {
                        iArr2 = this.f16266z;
                        i7 = this.f16265y;
                        this.f16265y = i7 + 1;
                        iArr2[i7] = this.f16242b.m19335c((String) objArr2[i4]) | 24117248;
                    } else if (objArr2[i4] instanceof Integer) {
                        iArr2 = this.f16266z;
                        i7 = this.f16265y;
                        this.f16265y = i7 + 1;
                        iArr2[i7] = ((Integer) objArr2[i4]).intValue();
                    } else {
                        iArr2 = this.f16266z;
                        i7 = this.f16265y;
                        this.f16265y = i7 + 1;
                        iArr2[i7] = this.f16242b.m19325a(C2915a.f14760f, ((Label) objArr2[i4]).f16213c) | 25165824;
                    }
                    i4++;
                }
                m19376b();
                return;
            }
            int i8;
            if (this.f16262v == null) {
                this.f16262v = new ByteVector();
                i8 = this.f16258r.f16134b;
            } else {
                i8 = (this.f16258r.f16134b - this.f16263w) - 1;
                if (i8 < 0) {
                    if (i != 3) {
                        throw new IllegalStateException();
                    }
                    return;
                }
            }
            switch (i) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    this.f16262v.putByte(Util.MASK_8BIT).putShort(i8).putShort(i2);
                    for (i8 = 0; i8 < i2; i8++) {
                        m19371a(objArr[i8]);
                    }
                    this.f16262v.putShort(i3);
                    while (i4 < i3) {
                        m19371a(objArr2[i4]);
                        i4++;
                    }
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    this.f16262v.putByte(i2 + 251).putShort(i8);
                    for (i8 = 0; i8 < i2; i8++) {
                        m19371a(objArr[i8]);
                    }
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    this.f16262v.putByte(251 - i2).putShort(i8);
                    break;
                case Type.BYTE /*3*/:
                    if (i8 >= 64) {
                        this.f16262v.putByte(251).putShort(i8);
                        break;
                    } else {
                        this.f16262v.putByte(i8);
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i8 < 64) {
                        this.f16262v.putByte(i8 + 64);
                    } else {
                        this.f16262v.putByte(247).putShort(i8);
                    }
                    m19371a(objArr2[0]);
                    break;
            }
            this.f16263w = this.f16258r.f16134b;
            this.f16261u++;
        }
    }

    public void visitIincInsn(int i, int i2) {
        if (this.f16237P != null && this.f16234M == 0) {
            this.f16237P.f16218h.m19349a((int) Opcodes.IINC, i, null, null);
        }
        if (this.f16234M != 2) {
            int i3 = i + 1;
            if (i3 > this.f16260t) {
                this.f16260t = i3;
            }
        }
        if (i > Util.MASK_8BIT || i2 > Opcodes.LAND || i2 < -128) {
            this.f16258r.putByte(SmileConstants.MIN_BUFFER_FOR_POSSIBLE_SHORT_STRING).m19310b(Opcodes.IINC, i).putShort(i2);
        } else {
            this.f16258r.putByte(Opcodes.IINC).m19309a(i, i2);
        }
    }

    public void visitInsn(int i) {
        this.f16258r.putByte(i);
        if (this.f16237P != null) {
            if (this.f16234M == 0) {
                this.f16237P.f16218h.m19349a(i, 0, null, null);
            } else {
                int i2 = this.f16238Q + Frame.f16187a[i];
                if (i2 > this.f16239R) {
                    this.f16239R = i2;
                }
                this.f16238Q = i2;
            }
            if ((i >= Opcodes.IRETURN && i <= Opcodes.RETURN) || i == Opcodes.ATHROW) {
                m19381e();
            }
        }
    }

    public void visitIntInsn(int i, int i2) {
        if (this.f16237P != null) {
            if (this.f16234M == 0) {
                this.f16237P.f16218h.m19349a(i, i2, null, null);
            } else if (i != Opcodes.NEWARRAY) {
                int i3 = this.f16238Q + 1;
                if (i3 > this.f16239R) {
                    this.f16239R = i3;
                }
                this.f16238Q = i3;
            }
        }
        if (i == 17) {
            this.f16258r.m19310b(i, i2);
        } else {
            this.f16258r.m19309a(i, i2);
        }
    }

    public void visitJumpInsn(int i, Label label) {
        Label label2 = null;
        if (this.f16237P != null) {
            if (this.f16234M == 0) {
                this.f16237P.f16218h.m19349a(i, 0, null, null);
                Label a = label.m19359a();
                a.f16211a |= 16;
                m19370a(0, label);
                if (i != Opcodes.GOTO) {
                    label2 = new Label();
                }
            } else if (i == Opcodes.JSR) {
                if ((label.f16211a & Opcodes.ACC_INTERFACE) == 0) {
                    label.f16211a |= Opcodes.ACC_INTERFACE;
                    this.f16233L++;
                }
                label2 = this.f16237P;
                label2.f16211a |= SmileConstants.TOKEN_PREFIX_TINY_UNICODE;
                m19370a(this.f16238Q + 1, label);
                label2 = new Label();
            } else {
                this.f16238Q += Frame.f16187a[i];
                m19370a(this.f16238Q, label);
            }
        }
        if ((label.f16211a & 2) == 0 || label.f16213c - this.f16258r.f16134b >= -32768) {
            this.f16258r.putByte(i);
            label.m19361a(this, this.f16258r, this.f16258r.f16134b - 1, false);
        } else {
            if (i == Opcodes.GOTO) {
                this.f16258r.putByte(C2799f.f14282t);
            } else if (i == Opcodes.JSR) {
                this.f16258r.putByte(bj.f6779b);
            } else {
                if (label2 != null) {
                    label2.f16211a |= 16;
                }
                this.f16258r.putByte(i <= Opcodes.IF_ACMPNE ? ((i + 1) ^ 1) - 1 : i ^ 1);
                this.f16258r.putShort(8);
                this.f16258r.putByte(C2799f.f14282t);
            }
            label.m19361a(this, this.f16258r, this.f16258r.f16134b - 1, true);
        }
        if (this.f16237P != null) {
            if (label2 != null) {
                visitLabel(label2);
            }
            if (i == Opcodes.GOTO) {
                m19381e();
            }
        }
    }

    public void visitLabel(Label label) {
        this.f16232K |= label.m19364a(this, this.f16258r.f16134b, this.f16258r.f16133a);
        if ((label.f16211a & 1) == 0) {
            if (this.f16234M == 0) {
                Label label2;
                if (this.f16237P != null) {
                    if (label.f16213c == this.f16237P.f16213c) {
                        label2 = this.f16237P;
                        label2.f16211a |= label.f16211a & 16;
                        label.f16218h = this.f16237P.f16218h;
                        return;
                    }
                    m19370a(0, label);
                }
                this.f16237P = label;
                if (label.f16218h == null) {
                    label.f16218h = new Frame();
                    label.f16218h.f16188b = label;
                }
                if (this.f16236O != null) {
                    if (label.f16213c == this.f16236O.f16213c) {
                        label2 = this.f16236O;
                        label2.f16211a |= label.f16211a & 16;
                        label.f16218h = this.f16236O.f16218h;
                        this.f16237P = this.f16236O;
                        return;
                    }
                    this.f16236O.f16219i = label;
                }
                this.f16236O = label;
            } else if (this.f16234M == 1) {
                if (this.f16237P != null) {
                    this.f16237P.f16217g = this.f16239R;
                    m19370a(this.f16238Q, label);
                }
                this.f16237P = label;
                this.f16238Q = 0;
                this.f16239R = 0;
                if (this.f16236O != null) {
                    this.f16236O.f16219i = label;
                }
                this.f16236O = label;
            }
        }
    }

    public void visitLdcInsn(Object obj) {
        int i;
        Item a = this.f16242b.m19330a(obj);
        if (this.f16237P != null) {
            if (this.f16234M == 0) {
                this.f16237P.f16218h.m19349a(18, 0, this.f16242b, a);
            } else {
                i = (a.f16203b == 5 || a.f16203b == 6) ? this.f16238Q + 2 : this.f16238Q + 1;
                if (i > this.f16239R) {
                    this.f16239R = i;
                }
                this.f16238Q = i;
            }
        }
        i = a.f16202a;
        if (a.f16203b == 5 || a.f16203b == 6) {
            this.f16258r.m19310b(20, i);
        } else if (i >= Opcodes.ACC_NATIVE) {
            this.f16258r.m19310b(19, i);
        } else {
            this.f16258r.m19309a(18, i);
        }
    }

    public void visitLineNumber(int i, Label label) {
        if (this.f16230I == null) {
            this.f16230I = new ByteVector();
        }
        this.f16229H++;
        this.f16230I.putShort(label.f16213c);
        this.f16230I.putShort(i);
    }

    public void visitLocalVariable(String str, String str2, String str3, Label label, Label label2, int i) {
        int i2 = 2;
        if (str3 != null) {
            if (this.f16228G == null) {
                this.f16228G = new ByteVector();
            }
            this.f16227F++;
            this.f16228G.putShort(label.f16213c).putShort(label2.f16213c - label.f16213c).putShort(this.f16242b.newUTF8(str)).putShort(this.f16242b.newUTF8(str3)).putShort(i);
        }
        if (this.f16226E == null) {
            this.f16226E = new ByteVector();
        }
        this.f16225D++;
        this.f16226E.putShort(label.f16213c).putShort(label2.f16213c - label.f16213c).putShort(this.f16242b.newUTF8(str)).putShort(this.f16242b.newUTF8(str2)).putShort(i);
        if (this.f16234M != 2) {
            char charAt = str2.charAt(0);
            if (!(charAt == 'J' || charAt == 'D')) {
                i2 = 1;
            }
            i2 += i;
            if (i2 > this.f16260t) {
                this.f16260t = i2;
            }
        }
    }

    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        int i = 0;
        int i2 = this.f16258r.f16134b;
        this.f16258r.putByte(Opcodes.LOOKUPSWITCH);
        this.f16258r.putByteArray(null, 0, (4 - (this.f16258r.f16134b % 4)) % 4);
        label.m19361a(this, this.f16258r, i2, true);
        this.f16258r.putInt(labelArr.length);
        while (i < labelArr.length) {
            this.f16258r.putInt(iArr[i]);
            labelArr[i].m19361a(this, this.f16258r, i2, true);
            i++;
        }
        m19372a(label, labelArr);
    }

    public void visitMaxs(int i, int i2) {
        Label a;
        Label a2;
        Label label;
        Edge edge;
        int i3;
        Label label2;
        int length;
        int i4;
        if (this.f16234M == 0) {
            Handler handler = this.f16223B;
            while (handler != null) {
                a = handler.f16196a.m19359a();
                a2 = handler.f16198c.m19359a();
                Label a3 = handler.f16197b.m19359a();
                int c = 24117248 | this.f16242b.m19335c(handler.f16199d == null ? "java/lang/Throwable" : handler.f16199d);
                a2.f16211a |= 16;
                for (label = a; label != a3; label = label.f16219i) {
                    edge = new Edge();
                    edge.f16174a = c;
                    edge.f16175b = a2;
                    edge.f16176c = label.f16220j;
                    label.f16220j = edge;
                }
                handler = handler.f16201f;
            }
            Frame frame = this.f16235N.f16218h;
            frame.m19350a(this.f16242b, this.f16243c, Type.getArgumentTypes(this.f16246f), this.f16260t);
            m19377b(frame);
            a2 = this.f16235N;
            i3 = 0;
            while (a2 != null) {
                label2 = a2.f16221k;
                a2.f16221k = null;
                Frame frame2 = a2.f16218h;
                if ((a2.f16211a & 16) != 0) {
                    a2.f16211a |= 32;
                }
                a2.f16211a |= 64;
                length = frame2.f16190d.length + a2.f16217g;
                if (length <= i3) {
                    length = i3;
                }
                Edge edge2 = a2.f16220j;
                while (edge2 != null) {
                    a = edge2.f16175b.m19359a();
                    if (frame2.m19351a(this.f16242b, a.f16218h, edge2.f16174a) && a.f16221k == null) {
                        a.f16221k = label2;
                    } else {
                        a = label2;
                    }
                    edge2 = edge2.f16176c;
                    label2 = a;
                }
                a2 = label2;
                i3 = length;
            }
            length = i3;
            for (a2 = this.f16235N; a2 != null; a2 = a2.f16219i) {
                Frame frame3 = a2.f16218h;
                if ((a2.f16211a & 32) != 0) {
                    m19377b(frame3);
                }
                if ((a2.f16211a & 64) == 0) {
                    a = a2.f16219i;
                    int i5 = a2.f16213c;
                    i4 = (a == null ? this.f16258r.f16134b : a.f16213c) - 1;
                    if (i4 >= i5) {
                        length = Math.max(length, 1);
                        for (i3 = i5; i3 < i4; i3++) {
                            this.f16258r.f16133a[i3] = (byte) 0;
                        }
                        this.f16258r.f16133a[i4] = (byte) -65;
                        m19369a(i5, 0, 1);
                        int[] iArr = this.f16266z;
                        i5 = this.f16265y;
                        this.f16265y = i5 + 1;
                        iArr[i5] = this.f16242b.m19335c("java/lang/Throwable") | 24117248;
                        m19376b();
                    }
                }
            }
            this.f16259s = length;
        } else if (this.f16234M == 1) {
            for (Handler handler2 = this.f16223B; handler2 != null; handler2 = handler2.f16201f) {
                label2 = handler2.f16198c;
                a2 = handler2.f16197b;
                for (label = handler2.f16196a; label != a2; label = label.f16219i) {
                    Edge edge3 = new Edge();
                    edge3.f16174a = Integer.MAX_VALUE;
                    edge3.f16175b = label2;
                    if ((label.f16211a & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) == 0) {
                        edge3.f16176c = label.f16220j;
                        label.f16220j = edge3;
                    } else {
                        edge3.f16176c = label.f16220j.f16176c.f16176c;
                        label.f16220j.f16176c.f16176c = edge3;
                    }
                }
            }
            if (this.f16233L > 0) {
                this.f16235N.m19365b(null, 1, this.f16233L);
                length = 0;
                for (a = this.f16235N; a != null; a = a.f16219i) {
                    if ((a.f16211a & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) != 0) {
                        label2 = a.f16220j.f16176c.f16175b;
                        if ((label2.f16211a & SmileConstants.MAX_SHARED_STRING_VALUES) == 0) {
                            length++;
                            label2.m19365b(null, ((((long) length) / 32) << 32) | (1 << (length % 32)), this.f16233L);
                        }
                    }
                }
                for (a = this.f16235N; a != null; a = a.f16219i) {
                    if ((a.f16211a & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) != 0) {
                        label = this.f16235N;
                        while (label != null) {
                            label.f16211a &= -2049;
                            label = label.f16219i;
                        }
                        a.f16220j.f16176c.f16175b.m19365b(a, 0, this.f16233L);
                    }
                }
            }
            label2 = this.f16235N;
            i3 = 0;
            while (label2 != null) {
                a2 = label2.f16221k;
                i4 = label2.f16216f;
                length = label2.f16217g + i4;
                if (length <= i3) {
                    length = i3;
                }
                edge = label2.f16220j;
                Edge edge4 = (label2.f16211a & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) != 0 ? edge.f16176c : edge;
                while (edge4 != null) {
                    label2 = edge4.f16175b;
                    if ((label2.f16211a & 8) == 0) {
                        label2.f16216f = edge4.f16174a == Integer.MAX_VALUE ? 1 : edge4.f16174a + i4;
                        label2.f16211a |= 8;
                        label2.f16221k = a2;
                        a = label2;
                    } else {
                        a = a2;
                    }
                    edge4 = edge4.f16176c;
                    a2 = a;
                }
                label2 = a2;
                i3 = length;
            }
            this.f16259s = i3;
        } else {
            this.f16259s = i;
            this.f16260t = i2;
        }
    }

    public void visitMethodInsn(int i, String str, String str2, String str3) {
        boolean z = i == Opcodes.INVOKEINTERFACE;
        Item a = i == Opcodes.INVOKEDYNAMIC ? this.f16242b.m19332a(str2, str3) : this.f16242b.m19334a(str, str2, str3, z);
        int i2 = a.f16204c;
        if (this.f16237P != null) {
            if (this.f16234M == 0) {
                this.f16237P.f16218h.m19349a(i, 0, this.f16242b, a);
            } else {
                int argumentsAndReturnSizes;
                if (i2 == 0) {
                    argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(str3);
                    a.f16204c = argumentsAndReturnSizes;
                } else {
                    argumentsAndReturnSizes = i2;
                }
                i2 = (i == Opcodes.INVOKESTATIC || i == Opcodes.INVOKEDYNAMIC) ? ((this.f16238Q - (argumentsAndReturnSizes >> 2)) + (argumentsAndReturnSizes & 3)) + 1 : (this.f16238Q - (argumentsAndReturnSizes >> 2)) + (argumentsAndReturnSizes & 3);
                if (i2 > this.f16239R) {
                    this.f16239R = i2;
                }
                this.f16238Q = i2;
                i2 = argumentsAndReturnSizes;
            }
        }
        if (z) {
            if (i2 == 0) {
                i2 = Type.getArgumentsAndReturnSizes(str3);
                a.f16204c = i2;
            }
            this.f16258r.m19310b(Opcodes.INVOKEINTERFACE, a.f16202a).m19309a(i2 >> 2, 0);
            return;
        }
        this.f16258r.m19310b(i, a.f16202a);
        if (i == Opcodes.INVOKEDYNAMIC) {
            this.f16258r.putShort(0);
        }
    }

    public void visitMultiANewArrayInsn(String str, int i) {
        Item a = this.f16242b.m19331a(str);
        if (this.f16237P != null) {
            if (this.f16234M == 0) {
                this.f16237P.f16218h.m19349a((int) Opcodes.MULTIANEWARRAY, i, this.f16242b, a);
            } else {
                this.f16238Q += 1 - i;
            }
        }
        this.f16258r.m19310b(Opcodes.MULTIANEWARRAY, a.f16202a).putByte(i);
    }

    public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        if ("Ljava/lang/Synthetic;".equals(str)) {
            this.f16240S = Math.max(this.f16240S, i + 1);
            return new AnnotationWriter(this.f16242b, false, byteVector, null, 0);
        }
        byteVector.putShort(this.f16242b.newUTF8(str)).putShort(0);
        AnnotationVisitor annotationWriter = new AnnotationWriter(this.f16242b, true, byteVector, byteVector, 2);
        if (z) {
            if (this.f16255o == null) {
                this.f16255o = new AnnotationWriter[Type.getArgumentTypes(this.f16246f).length];
            }
            annotationWriter.f16129g = this.f16255o[i];
            this.f16255o[i] = annotationWriter;
            return annotationWriter;
        }
        if (this.f16256p == null) {
            this.f16256p = new AnnotationWriter[Type.getArgumentTypes(this.f16246f).length];
        }
        annotationWriter.f16129g = this.f16256p[i];
        this.f16256p[i] = annotationWriter;
        return annotationWriter;
    }

    public void visitTableSwitchInsn(int i, int i2, Label label, Label[] labelArr) {
        int i3 = 0;
        int i4 = this.f16258r.f16134b;
        this.f16258r.putByte(Opcodes.TABLESWITCH);
        this.f16258r.putByteArray(null, 0, (4 - (this.f16258r.f16134b % 4)) % 4);
        label.m19361a(this, this.f16258r, i4, true);
        this.f16258r.putInt(i).putInt(i2);
        while (i3 < labelArr.length) {
            labelArr[i3].m19361a(this, this.f16258r, i4, true);
            i3++;
        }
        m19372a(label, labelArr);
    }

    public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
        this.f16222A++;
        Handler handler = new Handler();
        handler.f16196a = label;
        handler.f16197b = label2;
        handler.f16198c = label3;
        handler.f16199d = str;
        handler.f16200e = str != null ? this.f16242b.newClass(str) : 0;
        if (this.f16224C == null) {
            this.f16223B = handler;
        } else {
            this.f16224C.f16201f = handler;
        }
        this.f16224C = handler;
    }

    public void visitTypeInsn(int i, String str) {
        Item a = this.f16242b.m19331a(str);
        if (this.f16237P != null) {
            if (this.f16234M == 0) {
                this.f16237P.f16218h.m19349a(i, this.f16258r.f16134b, this.f16242b, a);
            } else if (i == Opcodes.NEW) {
                int i2 = this.f16238Q + 1;
                if (i2 > this.f16239R) {
                    this.f16239R = i2;
                }
                this.f16238Q = i2;
            }
        }
        this.f16258r.m19310b(i, a.f16202a);
    }

    public void visitVarInsn(int i, int i2) {
        int i3;
        if (this.f16237P != null) {
            if (this.f16234M == 0) {
                this.f16237P.f16218h.m19349a(i, i2, null, null);
            } else if (i == Opcodes.RET) {
                Label label = this.f16237P;
                label.f16211a |= Opcodes.ACC_NATIVE;
                this.f16237P.f16216f = this.f16238Q;
                m19381e();
            } else {
                i3 = this.f16238Q + Frame.f16187a[i];
                if (i3 > this.f16239R) {
                    this.f16239R = i3;
                }
                this.f16238Q = i3;
            }
        }
        if (this.f16234M != 2) {
            i3 = (i == 22 || i == 24 || i == 55 || i == 57) ? i2 + 2 : i2 + 1;
            if (i3 > this.f16260t) {
                this.f16260t = i3;
            }
        }
        if (i2 < 4 && i != Opcodes.RET) {
            this.f16258r.putByte(i < 54 ? (((i - 21) << 2) + 26) + i2 : (((i - 54) << 2) + 59) + i2);
        } else if (i2 >= Opcodes.ACC_NATIVE) {
            this.f16258r.putByte(SmileConstants.MIN_BUFFER_FOR_POSSIBLE_SHORT_STRING).m19310b(i, i2);
        } else {
            this.f16258r.m19309a(i, i2);
        }
        if (i >= 54 && this.f16234M == 0 && this.f16222A > 0) {
            visitLabel(new Label());
        }
    }
}
