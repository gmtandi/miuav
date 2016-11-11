package org.codehaus.jackson.org.objectweb.asm;

import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.hoho.android.usbserial.driver.UsbId;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;

final class Frame {
    static final int[] f16187a;
    Label f16188b;
    int[] f16189c;
    int[] f16190d;
    private int[] f16191e;
    private int[] f16192f;
    private int f16193g;
    private int f16194h;
    private int[] f16195i;

    static {
        int[] iArr = new int[C2799f.f14283u];
        String str = "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDDCDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCDCDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEFEDDDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE";
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = str.charAt(i) - 69;
        }
        f16187a = iArr;
    }

    Frame() {
    }

    private int m19338a() {
        if (this.f16193g > 0) {
            int[] iArr = this.f16192f;
            int i = this.f16193g - 1;
            this.f16193g = i;
            return iArr[i];
        }
        Label label = this.f16188b;
        int i2 = label.f16216f - 1;
        label.f16216f = i2;
        return 50331648 | (-i2);
    }

    private int m19339a(int i) {
        if (this.f16191e == null || i >= this.f16191e.length) {
            return 33554432 | i;
        }
        int i2 = this.f16191e[i];
        if (i2 != 0) {
            return i2;
        }
        i2 = 33554432 | i;
        this.f16191e[i] = i2;
        return i2;
    }

    private int m19340a(ClassWriter classWriter, int i) {
        int c;
        if (i == 16777222) {
            c = classWriter.m19335c(classWriter.f16144F) | 24117248;
        } else if ((-1048576 & i) != 25165824) {
            return i;
        } else {
            c = classWriter.m19335c(classWriter.f16143E[1048575 & i].f16206g) | 24117248;
        }
        for (int i2 = 0; i2 < this.f16194h; i2++) {
            int i3 = this.f16195i[i2];
            int i4 = -268435456 & i3;
            int i5 = 251658240 & i3;
            if (i5 == 33554432) {
                i3 = this.f16189c[i3 & 8388607] + i4;
            } else if (i5 == 50331648) {
                i3 = this.f16190d[this.f16190d.length - (i3 & 8388607)] + i4;
            }
            if (i == i3) {
                return c;
            }
        }
        return i;
    }

    private void m19341a(int i, int i2) {
        if (this.f16191e == null) {
            this.f16191e = new int[10];
        }
        int length = this.f16191e.length;
        if (i >= length) {
            Object obj = new int[Math.max(i + 1, length * 2)];
            System.arraycopy(this.f16191e, 0, obj, 0, length);
            this.f16191e = obj;
        }
        this.f16191e[i] = i2;
    }

    private void m19342a(String str) {
        char charAt = str.charAt(0);
        if (charAt == '(') {
            m19347c((Type.getArgumentsAndReturnSizes(str) >> 2) - 1);
        } else if (charAt == 'J' || charAt == 'D') {
            m19347c(2);
        } else {
            m19347c(1);
        }
    }

    private void m19343a(ClassWriter classWriter, String str) {
        int b = m19345b(classWriter, str);
        if (b != 0) {
            m19346b(b);
            if (b == 16777220 || b == 16777219) {
                m19346b(ViewCompat.MEASURED_STATE_TOO_SMALL);
            }
        }
    }

    private static boolean m19344a(ClassWriter classWriter, int i, int[] iArr, int i2) {
        int i3 = iArr[i2];
        if (i3 == i) {
            return false;
        }
        int i4;
        if ((268435455 & i) != 16777221) {
            i4 = i;
        } else if (i3 == 16777221) {
            return false;
        } else {
            i4 = 16777221;
        }
        if (i3 == 0) {
            iArr[i2] = i4;
            return true;
        }
        if ((i3 & 267386880) == 24117248 || (i3 & -268435456) != 0) {
            if (i4 == 16777221) {
                return false;
            }
            i4 = (-1048576 & i4) == (-1048576 & i3) ? (i3 & 267386880) == 24117248 ? classWriter.m19324a(i4 & 1048575, 1048575 & i3) | ((i4 & -268435456) | 24117248) : classWriter.m19335c("java/lang/Object") | 24117248 : ((i4 & 267386880) == 24117248 || (i4 & -268435456) != 0) ? classWriter.m19335c("java/lang/Object") | 24117248 : ViewCompat.MEASURED_STATE_TOO_SMALL;
        } else if (i3 != 16777221) {
            i4 = ViewCompat.MEASURED_STATE_TOO_SMALL;
        } else if ((i4 & 267386880) != 24117248 && (i4 & -268435456) == 0) {
            i4 = ViewCompat.MEASURED_STATE_TOO_SMALL;
        }
        if (i3 == i4) {
            return false;
        }
        iArr[i2] = i4;
        return true;
    }

    private static int m19345b(ClassWriter classWriter, String str) {
        int i = 16777217;
        int indexOf = str.charAt(0) == '(' ? str.indexOf(41) + 1 : 0;
        switch (str.charAt(indexOf)) {
            case UsbId.ARDUINO_MEGA_2560_R3 /*66*/:
            case UsbId.ARDUINO_UNO_R3 /*67*/:
            case 'I':
            case Opcodes.AASTORE /*83*/:
            case Opcodes.DUP_X1 /*90*/:
                return 16777217;
            case UsbId.ARDUINO_SERIAL_ADAPTER_R3 /*68*/:
                return 16777219;
            case 'F':
                return 16777218;
            case 'J':
                return 16777220;
            case 'L':
                return 24117248 | classWriter.m19335c(str.substring(indexOf + 1, str.length() - 1));
            case Opcodes.SASTORE /*86*/:
                return 0;
            default:
                int i2 = indexOf + 1;
                while (str.charAt(i2) == '[') {
                    i2++;
                }
                switch (str.charAt(i2)) {
                    case UsbId.ARDUINO_MEGA_2560_R3 /*66*/:
                        i = 16777226;
                        break;
                    case UsbId.ARDUINO_UNO_R3 /*67*/:
                        i = 16777227;
                        break;
                    case UsbId.ARDUINO_SERIAL_ADAPTER_R3 /*68*/:
                        i = 16777219;
                        break;
                    case 'F':
                        i = 16777218;
                        break;
                    case 'I':
                        break;
                    case 'J':
                        i = 16777220;
                        break;
                    case Opcodes.AASTORE /*83*/:
                        i = 16777228;
                        break;
                    case Opcodes.DUP_X1 /*90*/:
                        i = 16777225;
                        break;
                    default:
                        i = classWriter.m19335c(str.substring(i2 + 1, str.length() - 1)) | 24117248;
                        break;
                }
                return ((i2 - indexOf) << 28) | i;
        }
    }

    private void m19346b(int i) {
        if (this.f16192f == null) {
            this.f16192f = new int[10];
        }
        int length = this.f16192f.length;
        if (this.f16193g >= length) {
            Object obj = new int[Math.max(this.f16193g + 1, length * 2)];
            System.arraycopy(this.f16192f, 0, obj, 0, length);
            this.f16192f = obj;
        }
        int[] iArr = this.f16192f;
        int i2 = this.f16193g;
        this.f16193g = i2 + 1;
        iArr[i2] = i;
        length = this.f16188b.f16216f + this.f16193g;
        if (length > this.f16188b.f16217g) {
            this.f16188b.f16217g = length;
        }
    }

    private void m19347c(int i) {
        if (this.f16193g >= i) {
            this.f16193g -= i;
            return;
        }
        Label label = this.f16188b;
        label.f16216f -= i - this.f16193g;
        this.f16193g = 0;
    }

    private void m19348d(int i) {
        if (this.f16195i == null) {
            this.f16195i = new int[2];
        }
        int length = this.f16195i.length;
        if (this.f16194h >= length) {
            Object obj = new int[Math.max(this.f16194h + 1, length * 2)];
            System.arraycopy(this.f16195i, 0, obj, 0, length);
            this.f16195i = obj;
        }
        int[] iArr = this.f16195i;
        int i2 = this.f16194h;
        this.f16194h = i2 + 1;
        iArr[i2] = i;
    }

    void m19349a(int i, int i2, ClassWriter classWriter, Item item) {
        int a;
        int a2;
        int a3;
        String str;
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
            case Opcodes.INEG /*116*/:
            case Opcodes.LNEG /*117*/:
            case Opcodes.FNEG /*118*/:
            case Opcodes.DNEG /*119*/:
            case Opcodes.I2B /*145*/:
            case Opcodes.I2C /*146*/:
            case Opcodes.I2S /*147*/:
            case Opcodes.GOTO /*167*/:
            case Opcodes.RETURN /*177*/:
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                m19346b(16777221);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
            case Type.BYTE /*3*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
            case Type.INT /*5*/:
            case Type.FLOAT /*6*/:
            case Type.LONG /*7*/:
            case Type.DOUBLE /*8*/:
            case Segment.TOKENS_PER_SEGMENT /*16*/:
            case Opcodes.SIPUSH /*17*/:
            case Opcodes.ILOAD /*21*/:
                m19346b(16777217);
            case Type.ARRAY /*9*/:
            case Type.OBJECT /*10*/:
            case Opcodes.LLOAD /*22*/:
                m19346b(16777220);
                m19346b(ViewCompat.MEASURED_STATE_TOO_SMALL);
            case Opcodes.T_LONG /*11*/:
            case Opcodes.FCONST_1 /*12*/:
            case Opcodes.FCONST_2 /*13*/:
            case Opcodes.FLOAD /*23*/:
                m19346b(16777218);
            case Opcodes.DCONST_0 /*14*/:
            case Opcodes.DCONST_1 /*15*/:
            case Opcodes.DLOAD /*24*/:
                m19346b(16777219);
                m19346b(ViewCompat.MEASURED_STATE_TOO_SMALL);
            case Opcodes.LDC /*18*/:
                switch (item.f16203b) {
                    case Type.BYTE /*3*/:
                        m19346b(16777217);
                    case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                        m19346b(16777218);
                    case Type.INT /*5*/:
                        m19346b(16777220);
                        m19346b(ViewCompat.MEASURED_STATE_TOO_SMALL);
                    case Type.FLOAT /*6*/:
                        m19346b(16777219);
                        m19346b(ViewCompat.MEASURED_STATE_TOO_SMALL);
                    case Type.LONG /*7*/:
                        m19346b(24117248 | classWriter.m19335c("java/lang/Class"));
                    default:
                        m19346b(24117248 | classWriter.m19335c("java/lang/String"));
                }
            case Opcodes.ALOAD /*25*/:
                m19346b(m19339a(i2));
            case Opcodes.V1_2 /*46*/:
            case Opcodes.V1_7 /*51*/:
            case Opcodes.CALOAD /*52*/:
            case Opcodes.SALOAD /*53*/:
                m19347c(2);
                m19346b(16777217);
            case Opcodes.V1_3 /*47*/:
            case Opcodes.D2L /*143*/:
                m19347c(2);
                m19346b(16777220);
                m19346b(ViewCompat.MEASURED_STATE_TOO_SMALL);
            case SmileConstants.TOKEN_PREFIX_KEY_SHARED_LONG /*48*/:
                m19347c(2);
                m19346b(16777218);
            case Opcodes.V1_5 /*49*/:
            case Opcodes.L2D /*138*/:
                m19347c(2);
                m19346b(16777219);
                m19346b(ViewCompat.MEASURED_STATE_TOO_SMALL);
            case Opcodes.V1_6 /*50*/:
                m19347c(1);
                m19346b(m19338a() - 268435456);
            case Opcodes.ISTORE /*54*/:
            case SmileConstants.MAX_SHORT_NAME_UNICODE_BYTES /*56*/:
            case Opcodes.ASTORE /*58*/:
                m19341a(i2, m19338a());
                if (i2 > 0) {
                    a = m19339a(i2 - 1);
                    if (a == 16777220 || a == 16777219) {
                        m19341a(i2 - 1, (int) ViewCompat.MEASURED_STATE_TOO_SMALL);
                    } else if ((251658240 & a) != ViewCompat.MEASURED_STATE_TOO_SMALL) {
                        m19341a(i2 - 1, a | GravityCompat.RELATIVE_LAYOUT_DIRECTION);
                    }
                }
            case Opcodes.LSTORE /*55*/:
            case Opcodes.DSTORE /*57*/:
                m19347c(1);
                m19341a(i2, m19338a());
                m19341a(i2 + 1, (int) ViewCompat.MEASURED_STATE_TOO_SMALL);
                if (i2 > 0) {
                    a = m19339a(i2 - 1);
                    if (a == 16777220 || a == 16777219) {
                        m19341a(i2 - 1, (int) ViewCompat.MEASURED_STATE_TOO_SMALL);
                    } else if ((251658240 & a) != ViewCompat.MEASURED_STATE_TOO_SMALL) {
                        m19341a(i2 - 1, a | GravityCompat.RELATIVE_LAYOUT_DIRECTION);
                    }
                }
            case Opcodes.IASTORE /*79*/:
            case Opcodes.FASTORE /*81*/:
            case Opcodes.AASTORE /*83*/:
            case Opcodes.BASTORE /*84*/:
            case Opcodes.CASTORE /*85*/:
            case Opcodes.SASTORE /*86*/:
                m19347c(3);
            case Opcodes.LASTORE /*80*/:
            case Opcodes.DASTORE /*82*/:
                m19347c(4);
            case Opcodes.POP /*87*/:
            case Opcodes.IFEQ /*153*/:
            case Opcodes.IFNE /*154*/:
            case Opcodes.IFLT /*155*/:
            case Opcodes.IFGE /*156*/:
            case Opcodes.IFGT /*157*/:
            case Opcodes.IFLE /*158*/:
            case Opcodes.TABLESWITCH /*170*/:
            case Opcodes.LOOKUPSWITCH /*171*/:
            case Opcodes.IRETURN /*172*/:
            case Opcodes.FRETURN /*174*/:
            case Opcodes.ARETURN /*176*/:
            case Opcodes.ATHROW /*191*/:
            case Opcodes.MONITORENTER /*194*/:
            case Opcodes.MONITOREXIT /*195*/:
            case Opcodes.IFNULL /*198*/:
            case Opcodes.IFNONNULL /*199*/:
                m19347c(1);
            case Opcodes.POP2 /*88*/:
            case Opcodes.IF_ICMPEQ /*159*/:
            case SmileConstants.TOKEN_PREFIX_SHORT_UNICODE /*160*/:
            case Opcodes.IF_ICMPLT /*161*/:
            case Opcodes.IF_ICMPGE /*162*/:
            case Opcodes.IF_ICMPGT /*163*/:
            case Opcodes.IF_ICMPLE /*164*/:
            case Opcodes.IF_ACMPEQ /*165*/:
            case Opcodes.IF_ACMPNE /*166*/:
            case Opcodes.LRETURN /*173*/:
            case Opcodes.DRETURN /*175*/:
                m19347c(2);
            case Opcodes.DUP /*89*/:
                a = m19338a();
                m19346b(a);
                m19346b(a);
            case Opcodes.DUP_X1 /*90*/:
                a = m19338a();
                a2 = m19338a();
                m19346b(a);
                m19346b(a2);
                m19346b(a);
            case Opcodes.DUP_X2 /*91*/:
                a = m19338a();
                a2 = m19338a();
                a3 = m19338a();
                m19346b(a);
                m19346b(a3);
                m19346b(a2);
                m19346b(a);
            case Opcodes.DUP2 /*92*/:
                a = m19338a();
                a2 = m19338a();
                m19346b(a2);
                m19346b(a);
                m19346b(a2);
                m19346b(a);
            case Opcodes.DUP2_X1 /*93*/:
                a = m19338a();
                a2 = m19338a();
                a3 = m19338a();
                m19346b(a2);
                m19346b(a);
                m19346b(a3);
                m19346b(a2);
                m19346b(a);
            case Opcodes.DUP2_X2 /*94*/:
                a = m19338a();
                a2 = m19338a();
                a3 = m19338a();
                int a4 = m19338a();
                m19346b(a2);
                m19346b(a);
                m19346b(a4);
                m19346b(a3);
                m19346b(a2);
                m19346b(a);
            case Opcodes.SWAP /*95*/:
                a = m19338a();
                a2 = m19338a();
                m19346b(a);
                m19346b(a2);
            case SmileConstants.TOKEN_PREFIX_SMALL_ASCII /*96*/:
            case Opcodes.ISUB /*100*/:
            case Opcodes.IMUL /*104*/:
            case Opcodes.IDIV /*108*/:
            case Opcodes.IREM /*112*/:
            case Opcodes.ISHL /*120*/:
            case Opcodes.ISHR /*122*/:
            case Opcodes.IUSHR /*124*/:
            case Opcodes.IAND /*126*/:
            case SmileConstants.TOKEN_PREFIX_TINY_UNICODE /*128*/:
            case Opcodes.IXOR /*130*/:
            case Opcodes.L2I /*136*/:
            case Opcodes.D2I /*142*/:
            case Opcodes.FCMPL /*149*/:
            case Opcodes.FCMPG /*150*/:
                m19347c(2);
                m19346b(16777217);
            case Opcodes.LADD /*97*/:
            case Opcodes.LSUB /*101*/:
            case Opcodes.LMUL /*105*/:
            case Opcodes.LDIV /*109*/:
            case Opcodes.LREM /*113*/:
            case Opcodes.LAND /*127*/:
            case Opcodes.LOR /*129*/:
            case Opcodes.LXOR /*131*/:
                m19347c(4);
                m19346b(16777220);
                m19346b(ViewCompat.MEASURED_STATE_TOO_SMALL);
            case Opcodes.FADD /*98*/:
            case Opcodes.FSUB /*102*/:
            case Opcodes.FMUL /*106*/:
            case Opcodes.FDIV /*110*/:
            case Opcodes.FREM /*114*/:
            case Opcodes.L2F /*137*/:
            case Opcodes.D2F /*144*/:
                m19347c(2);
                m19346b(16777218);
            case Opcodes.DADD /*99*/:
            case Opcodes.DSUB /*103*/:
            case Opcodes.DMUL /*107*/:
            case Opcodes.DDIV /*111*/:
            case Opcodes.DREM /*115*/:
                m19347c(4);
                m19346b(16777219);
                m19346b(ViewCompat.MEASURED_STATE_TOO_SMALL);
            case Opcodes.LSHL /*121*/:
            case Opcodes.LSHR /*123*/:
            case Opcodes.LUSHR /*125*/:
                m19347c(3);
                m19346b(16777220);
                m19346b(ViewCompat.MEASURED_STATE_TOO_SMALL);
            case Opcodes.IINC /*132*/:
                m19341a(i2, 16777217);
            case Opcodes.I2L /*133*/:
            case Opcodes.F2L /*140*/:
                m19347c(1);
                m19346b(16777220);
                m19346b(ViewCompat.MEASURED_STATE_TOO_SMALL);
            case Opcodes.I2F /*134*/:
                m19347c(1);
                m19346b(16777218);
            case Opcodes.I2D /*135*/:
            case Opcodes.F2D /*141*/:
                m19347c(1);
                m19346b(16777219);
                m19346b(ViewCompat.MEASURED_STATE_TOO_SMALL);
            case Opcodes.F2I /*139*/:
            case Opcodes.ARRAYLENGTH /*190*/:
            case Opcodes.INSTANCEOF /*193*/:
                m19347c(1);
                m19346b(16777217);
            case Opcodes.LCMP /*148*/:
            case Opcodes.DCMPL /*151*/:
            case Opcodes.DCMPG /*152*/:
                m19347c(4);
                m19346b(16777217);
            case Opcodes.JSR /*168*/:
            case Opcodes.RET /*169*/:
                throw new RuntimeException("JSR/RET are not supported with computeFrames option");
            case Opcodes.GETSTATIC /*178*/:
                m19343a(classWriter, item.f16208i);
            case Opcodes.PUTSTATIC /*179*/:
                m19342a(item.f16208i);
            case Opcodes.GETFIELD /*180*/:
                m19347c(1);
                m19343a(classWriter, item.f16208i);
            case Opcodes.PUTFIELD /*181*/:
                m19342a(item.f16208i);
                m19338a();
            case Opcodes.INVOKEVIRTUAL /*182*/:
            case Opcodes.INVOKESPECIAL /*183*/:
            case Opcodes.INVOKESTATIC /*184*/:
            case Opcodes.INVOKEINTERFACE /*185*/:
                m19342a(item.f16208i);
                if (i != Opcodes.INVOKESTATIC) {
                    a = m19338a();
                    if (i == Opcodes.INVOKESPECIAL && item.f16207h.charAt(0) == '<') {
                        m19348d(a);
                    }
                }
                m19343a(classWriter, item.f16208i);
            case Opcodes.INVOKEDYNAMIC /*186*/:
                m19342a(item.f16207h);
                m19343a(classWriter, item.f16207h);
            case Opcodes.NEW /*187*/:
                m19346b(25165824 | classWriter.m19325a(item.f16206g, i2));
            case Opcodes.NEWARRAY /*188*/:
                m19338a();
                switch (i2) {
                    case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                        m19346b(285212681);
                    case Type.INT /*5*/:
                        m19346b(285212683);
                    case Type.FLOAT /*6*/:
                        m19346b(285212674);
                    case Type.LONG /*7*/:
                        m19346b(285212675);
                    case Type.DOUBLE /*8*/:
                        m19346b(285212682);
                    case Type.ARRAY /*9*/:
                        m19346b(285212684);
                    case Type.OBJECT /*10*/:
                        m19346b(285212673);
                    default:
                        m19346b(285212676);
                }
            case Opcodes.ANEWARRAY /*189*/:
                str = item.f16206g;
                m19338a();
                if (str.charAt(0) == '[') {
                    m19343a(classWriter, new StringBuffer().append('[').append(str).toString());
                } else {
                    m19346b(classWriter.m19335c(str) | 292552704);
                }
            case SmileConstants.TOKEN_PREFIX_SMALL_INT /*192*/:
                str = item.f16206g;
                m19338a();
                if (str.charAt(0) == '[') {
                    m19343a(classWriter, str);
                } else {
                    m19346b(classWriter.m19335c(str) | 24117248);
                }
            default:
                m19347c(i2);
                m19343a(classWriter, item.f16206g);
        }
    }

    void m19350a(ClassWriter classWriter, int i, Type[] typeArr, int i2) {
        int i3 = 1;
        int i4 = 0;
        this.f16189c = new int[i2];
        this.f16190d = new int[0];
        if ((i & 8) != 0) {
            i3 = 0;
        } else if ((AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START & i) == 0) {
            this.f16189c[0] = 24117248 | classWriter.m19335c(classWriter.f16144F);
        } else {
            this.f16189c[0] = 16777222;
        }
        while (i4 < typeArr.length) {
            int b = m19345b(classWriter, typeArr[i4].getDescriptor());
            int i5 = i3 + 1;
            this.f16189c[i3] = b;
            if (b == 16777220 || b == 16777219) {
                i3 = i5 + 1;
                this.f16189c[i5] = ViewCompat.MEASURED_STATE_TOO_SMALL;
            } else {
                i3 = i5;
            }
            i4++;
        }
        while (i3 < i2) {
            i4 = i3 + 1;
            this.f16189c[i3] = ViewCompat.MEASURED_STATE_TOO_SMALL;
            i3 = i4;
        }
    }

    boolean m19351a(ClassWriter classWriter, Frame frame, int i) {
        boolean z = false;
        int length = this.f16189c.length;
        int length2 = this.f16190d.length;
        if (frame.f16189c == null) {
            frame.f16189c = new int[length];
            z = true;
        }
        int i2 = 0;
        boolean z2 = z;
        while (i2 < length) {
            int i3;
            int i4;
            int i5;
            if (this.f16191e == null || i2 >= this.f16191e.length) {
                i3 = this.f16189c[i2];
            } else {
                i3 = this.f16191e[i2];
                if (i3 == 0) {
                    i3 = this.f16189c[i2];
                } else {
                    i4 = -268435456 & i3;
                    i5 = 251658240 & i3;
                    if (i5 != ViewCompat.MEASURED_STATE_TOO_SMALL) {
                        i4 = i5 == 33554432 ? i4 + this.f16189c[8388607 & i3] : i4 + this.f16190d[length2 - (8388607 & i3)];
                        i3 = ((i3 & GravityCompat.RELATIVE_LAYOUT_DIRECTION) == 0 || !(i4 == 16777220 || i4 == 16777219)) ? i4 : ViewCompat.MEASURED_STATE_TOO_SMALL;
                    }
                }
            }
            if (this.f16195i != null) {
                i3 = m19340a(classWriter, i3);
            }
            z2 |= m19344a(classWriter, i3, frame.f16189c, i2);
            i2++;
        }
        if (i > 0) {
            i4 = 0;
            i3 = z2;
            while (i4 < length) {
                int a = m19344a(classWriter, this.f16189c[i4], frame.f16189c, i4) | i3;
                i4++;
                i3 = a;
            }
            if (frame.f16190d == null) {
                frame.f16190d = new int[1];
                i3 = 1;
            }
            return m19344a(classWriter, i, frame.f16190d, 0) | i3;
        }
        length = this.f16190d.length + this.f16188b.f16216f;
        if (frame.f16190d == null) {
            frame.f16190d = new int[(this.f16193g + length)];
            z = true;
        } else {
            z = z2;
        }
        boolean z3 = z;
        for (a = 0; a < length; a++) {
            i3 = this.f16190d[a];
            if (this.f16195i != null) {
                i3 = m19340a(classWriter, i3);
            }
            z3 |= m19344a(classWriter, i3, frame.f16190d, a);
        }
        for (i3 = 0; i3 < this.f16193g; i3++) {
            a = this.f16192f[i3];
            i2 = -268435456 & a;
            i5 = 251658240 & a;
            if (i5 != ViewCompat.MEASURED_STATE_TOO_SMALL) {
                i2 = i5 == 33554432 ? i2 + this.f16189c[8388607 & a] : i2 + this.f16190d[length2 - (8388607 & a)];
                a = ((a & GravityCompat.RELATIVE_LAYOUT_DIRECTION) == 0 || !(i2 == 16777220 || i2 == 16777219)) ? i2 : ViewCompat.MEASURED_STATE_TOO_SMALL;
            }
            if (this.f16195i != null) {
                a = m19340a(classWriter, a);
            }
            z3 |= m19344a(classWriter, a, frame.f16190d, length + i3);
        }
        return z3;
    }
}
