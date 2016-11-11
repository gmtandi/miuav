package org.codehaus.jackson.org.objectweb.asm.signature;

import com.fimi.soul.module.setting.newhand.C1873o;
import com.hoho.android.usbserial.driver.UsbId;
import com.tencent.open.GameAppOperation;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class SignatureReader {
    private final String f16271a;

    public SignatureReader(String str) {
        this.f16271a = str;
    }

    private static int m19387a(String str, int i, SignatureVisitor signatureVisitor) {
        int i2 = i + 1;
        char charAt = str.charAt(i);
        switch (charAt) {
            case UsbId.ARDUINO_MEGA_2560_R3 /*66*/:
            case UsbId.ARDUINO_UNO_R3 /*67*/:
            case UsbId.ARDUINO_SERIAL_ADAPTER_R3 /*68*/:
            case 'F':
            case 'I':
            case 'J':
            case Opcodes.AASTORE /*83*/:
            case Opcodes.SASTORE /*86*/:
            case Opcodes.DUP_X1 /*90*/:
                signatureVisitor.visitBaseType(charAt);
                return i2;
            case Opcodes.BASTORE /*84*/:
                int indexOf = str.indexOf(59, i2);
                signatureVisitor.visitTypeVariable(str.substring(i2, indexOf));
                return indexOf + 1;
            case Opcodes.DUP_X2 /*91*/:
                return m19387a(str, i2, signatureVisitor.visitArrayType());
            default:
                Object obj = null;
                int i3 = i2;
                int i4 = i2;
                Object obj2 = null;
                while (true) {
                    int i5 = i4 + 1;
                    char charAt2 = str.charAt(i4);
                    String substring;
                    switch (charAt2) {
                        case Opcodes.V1_2 /*46*/:
                        case UsbId.ARDUINO_SERIAL_ADAPTER /*59*/:
                            if (obj == null) {
                                substring = str.substring(i3, i5 - 1);
                                if (obj2 != null) {
                                    signatureVisitor.visitInnerClassType(substring);
                                } else {
                                    signatureVisitor.visitClassType(substring);
                                }
                            }
                            if (charAt2 != ';') {
                                obj2 = 1;
                                obj = null;
                                i3 = i5;
                                i4 = i5;
                                break;
                            }
                            signatureVisitor.visitEnd();
                            return i5;
                        case GameAppOperation.SHARE_PRIZE_SUMMARY_MAX_LENGTH /*60*/:
                            substring = str.substring(i3, i5 - 1);
                            if (obj2 != null) {
                                signatureVisitor.visitInnerClassType(substring);
                            } else {
                                signatureVisitor.visitClassType(substring);
                            }
                            int i6 = i5;
                            while (true) {
                                charAt2 = str.charAt(i6);
                                switch (charAt2) {
                                    case C1873o.f9552n /*42*/:
                                        i6++;
                                        signatureVisitor.visitTypeArgument();
                                        break;
                                    case C1873o.f9553o /*43*/:
                                    case GameAppOperation.SHARE_PRIZE_TITLE_MAX_LENGTH /*45*/:
                                        i6 = m19387a(str, i6 + 1, signatureVisitor.visitTypeArgument(charAt2));
                                        break;
                                    case '>':
                                        i4 = i6;
                                        obj = 1;
                                        continue;
                                    default:
                                        i6 = m19387a(str, i6, signatureVisitor.visitTypeArgument(SignatureVisitor.INSTANCEOF));
                                        break;
                                }
                            }
                        default:
                            i4 = i5;
                            break;
                    }
                }
        }
    }

    public void accept(SignatureVisitor signatureVisitor) {
        int i = 0;
        String str = this.f16271a;
        int length = str.length();
        if (str.charAt(0) == '<') {
            i = 2;
            char charAt;
            do {
                int indexOf = str.indexOf(58, i);
                signatureVisitor.visitFormalTypeParameter(str.substring(i - 1, indexOf));
                i = indexOf + 1;
                charAt = str.charAt(i);
                indexOf = (charAt == 'L' || charAt == '[' || charAt == 'T') ? m19387a(str, i, signatureVisitor.visitClassBound()) : i;
                while (true) {
                    i = indexOf + 1;
                    charAt = str.charAt(indexOf);
                    if (charAt != ':') {
                        break;
                    }
                    indexOf = m19387a(str, i, signatureVisitor.visitInterfaceBound());
                }
            } while (charAt != '>');
        }
        if (str.charAt(i) == '(') {
            i++;
            while (str.charAt(i) != ')') {
                i = m19387a(str, i, signatureVisitor.visitParameterType());
            }
            i = m19387a(str, i + 1, signatureVisitor.visitReturnType());
            while (i < length) {
                i = m19387a(str, i + 1, signatureVisitor.visitExceptionType());
            }
            return;
        }
        i = m19387a(str, i, signatureVisitor.visitSuperclass());
        while (i < length) {
            i = m19387a(str, i, signatureVisitor.visitInterface());
        }
    }

    public void acceptType(SignatureVisitor signatureVisitor) {
        m19387a(this.f16271a, 0, signatureVisitor);
    }
}
