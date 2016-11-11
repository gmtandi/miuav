package org.codehaus.jackson.org.objectweb.asm;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import com.tencent.mm.sdk.platformtools.Util;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.codehaus.jackson.smile.SmileConstants;

public class Type {
    public static final int ARRAY = 9;
    public static final int BOOLEAN = 1;
    public static final Type BOOLEAN_TYPE;
    public static final int BYTE = 3;
    public static final Type BYTE_TYPE;
    public static final int CHAR = 2;
    public static final Type CHAR_TYPE;
    public static final int DOUBLE = 8;
    public static final Type DOUBLE_TYPE;
    public static final int FLOAT = 6;
    public static final Type FLOAT_TYPE;
    public static final int INT = 5;
    public static final Type INT_TYPE;
    public static final int LONG = 7;
    public static final Type LONG_TYPE;
    public static final int OBJECT = 10;
    public static final int SHORT = 4;
    public static final Type SHORT_TYPE;
    public static final int VOID = 0;
    public static final Type VOID_TYPE;
    private final int f16267a;
    private final char[] f16268b;
    private final int f16269c;
    private final int f16270d;

    static {
        VOID_TYPE = new Type(0, null, 1443168256, BOOLEAN);
        BOOLEAN_TYPE = new Type(BOOLEAN, null, 1509950721, BOOLEAN);
        CHAR_TYPE = new Type(CHAR, null, 1124075009, BOOLEAN);
        BYTE_TYPE = new Type(BYTE, null, 1107297537, BOOLEAN);
        SHORT_TYPE = new Type(SHORT, null, 1392510721, BOOLEAN);
        INT_TYPE = new Type(INT, null, 1224736769, BOOLEAN);
        FLOAT_TYPE = new Type(FLOAT, null, 1174536705, BOOLEAN);
        LONG_TYPE = new Type(LONG, null, 1241579778, BOOLEAN);
        DOUBLE_TYPE = new Type(DOUBLE, null, 1141048066, BOOLEAN);
    }

    private Type(int i, char[] cArr, int i2, int i3) {
        this.f16267a = i;
        this.f16268b = cArr;
        this.f16269c = i2;
        this.f16270d = i3;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.codehaus.jackson.org.objectweb.asm.Type m19384a(char[] r4, int r5) {
        /*
        r3 = 59;
        r0 = 1;
        r1 = r4[r5];
        switch(r1) {
            case 66: goto L_0x001a;
            case 67: goto L_0x0017;
            case 68: goto L_0x0029;
            case 70: goto L_0x0023;
            case 73: goto L_0x0020;
            case 74: goto L_0x0026;
            case 83: goto L_0x001d;
            case 86: goto L_0x0011;
            case 90: goto L_0x0014;
            case 91: goto L_0x002c;
            default: goto L_0x0008;
        };
    L_0x0008:
        r1 = r5 + r0;
        r1 = r4[r1];
        if (r1 == r3) goto L_0x0055;
    L_0x000e:
        r0 = r0 + 1;
        goto L_0x0008;
    L_0x0011:
        r0 = VOID_TYPE;
    L_0x0013:
        return r0;
    L_0x0014:
        r0 = BOOLEAN_TYPE;
        goto L_0x0013;
    L_0x0017:
        r0 = CHAR_TYPE;
        goto L_0x0013;
    L_0x001a:
        r0 = BYTE_TYPE;
        goto L_0x0013;
    L_0x001d:
        r0 = SHORT_TYPE;
        goto L_0x0013;
    L_0x0020:
        r0 = INT_TYPE;
        goto L_0x0013;
    L_0x0023:
        r0 = FLOAT_TYPE;
        goto L_0x0013;
    L_0x0026:
        r0 = LONG_TYPE;
        goto L_0x0013;
    L_0x0029:
        r0 = DOUBLE_TYPE;
        goto L_0x0013;
    L_0x002c:
        r1 = r5 + r0;
        r1 = r4[r1];
        r2 = 91;
        if (r1 != r2) goto L_0x0037;
    L_0x0034:
        r0 = r0 + 1;
        goto L_0x002c;
    L_0x0037:
        r1 = r5 + r0;
        r1 = r4[r1];
        r2 = 76;
        if (r1 != r2) goto L_0x004a;
    L_0x003f:
        r0 = r0 + 1;
    L_0x0041:
        r1 = r5 + r0;
        r1 = r4[r1];
        if (r1 == r3) goto L_0x004a;
    L_0x0047:
        r0 = r0 + 1;
        goto L_0x0041;
    L_0x004a:
        r1 = new org.codehaus.jackson.org.objectweb.asm.Type;
        r2 = 9;
        r0 = r0 + 1;
        r1.<init>(r2, r4, r5, r0);
        r0 = r1;
        goto L_0x0013;
    L_0x0055:
        r1 = new org.codehaus.jackson.org.objectweb.asm.Type;
        r2 = 10;
        r3 = r5 + 1;
        r0 = r0 + -1;
        r1.<init>(r2, r4, r3, r0);
        r0 = r1;
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.org.objectweb.asm.Type.a(char[], int):org.codehaus.jackson.org.objectweb.asm.Type");
    }

    private void m19385a(StringBuffer stringBuffer) {
        if (this.f16268b == null) {
            stringBuffer.append((char) ((this.f16269c & ViewCompat.MEASURED_STATE_MASK) >>> 24));
        } else if (this.f16267a == ARRAY) {
            stringBuffer.append(this.f16268b, this.f16269c, this.f16270d);
        } else {
            stringBuffer.append('L');
            stringBuffer.append(this.f16268b, this.f16269c, this.f16270d);
            stringBuffer.append(';');
        }
    }

    private static void m19386a(StringBuffer stringBuffer, Class cls) {
        char charAt;
        while (!cls.isPrimitive()) {
            if (cls.isArray()) {
                stringBuffer.append('[');
                cls = cls.getComponentType();
            } else {
                stringBuffer.append('L');
                String name = cls.getName();
                int length = name.length();
                for (int i = 0; i < length; i += BOOLEAN) {
                    charAt = name.charAt(i);
                    if (charAt == '.') {
                        charAt = '/';
                    }
                    stringBuffer.append(charAt);
                }
                stringBuffer.append(';');
                return;
            }
        }
        charAt = cls == Integer.TYPE ? 'I' : cls == Void.TYPE ? 'V' : cls == Boolean.TYPE ? 'Z' : cls == Byte.TYPE ? 'B' : cls == Character.TYPE ? 'C' : cls == Short.TYPE ? 'S' : cls == Double.TYPE ? 'D' : cls == Float.TYPE ? 'F' : 'J';
        stringBuffer.append(charAt);
    }

    public static Type[] getArgumentTypes(String str) {
        int i = BOOLEAN;
        char[] toCharArray = str.toCharArray();
        int i2 = 0;
        int i3 = BOOLEAN;
        while (true) {
            int i4 = i3 + BOOLEAN;
            char c = toCharArray[i3];
            if (c == ')') {
                break;
            } else if (c == 'L') {
                i3 = i4;
                while (true) {
                    i4 = i3 + BOOLEAN;
                    if (toCharArray[i3] == ';') {
                        break;
                    }
                    i3 = i4;
                }
                i2 += BOOLEAN;
                i3 = i4;
            } else if (c != '[') {
                i2 += BOOLEAN;
                i3 = i4;
            } else {
                i3 = i4;
            }
        }
        Type[] typeArr = new Type[i2];
        i2 = 0;
        while (toCharArray[i] != ')') {
            typeArr[i2] = m19384a(toCharArray, i);
            i += (typeArr[i2].f16267a == OBJECT ? CHAR : 0) + typeArr[i2].f16270d;
            i2 += BOOLEAN;
        }
        return typeArr;
    }

    public static Type[] getArgumentTypes(Method method) {
        Class[] parameterTypes = method.getParameterTypes();
        Type[] typeArr = new Type[parameterTypes.length];
        for (int length = parameterTypes.length - 1; length >= 0; length--) {
            typeArr[length] = getType(parameterTypes[length]);
        }
        return typeArr;
    }

    public static int getArgumentsAndReturnSizes(String str) {
        int i;
        char charAt;
        int i2 = BOOLEAN;
        int i3 = BOOLEAN;
        int i4 = BOOLEAN;
        while (true) {
            i = i3 + BOOLEAN;
            charAt = str.charAt(i3);
            if (charAt == ')') {
                break;
            } else if (charAt == 'L') {
                i3 = i;
                while (true) {
                    i = i3 + BOOLEAN;
                    if (str.charAt(i3) == ';') {
                        break;
                    }
                    i3 = i;
                }
                i4 += BOOLEAN;
                i3 = i;
            } else if (charAt == '[') {
                while (true) {
                    charAt = str.charAt(i);
                    if (charAt != '[') {
                        break;
                    }
                    i += BOOLEAN;
                }
                if (charAt == 'D' || charAt == 'J') {
                    i4--;
                    i3 = i;
                } else {
                    i3 = i;
                }
            } else if (charAt == 'D' || charAt == 'J') {
                i4 += CHAR;
                i3 = i;
            } else {
                i4 += BOOLEAN;
                i3 = i;
            }
        }
        charAt = str.charAt(i);
        i4 <<= CHAR;
        if (charAt == 'V') {
            i2 = 0;
        } else if (charAt == 'D' || charAt == 'J') {
            i2 = CHAR;
        }
        return i4 | i2;
    }

    public static String getConstructorDescriptor(Constructor constructor) {
        Class[] parameterTypes = constructor.getParameterTypes();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        for (int i = 0; i < parameterTypes.length; i += BOOLEAN) {
            m19386a(stringBuffer, parameterTypes[i]);
        }
        return stringBuffer.append(")V").toString();
    }

    public static String getDescriptor(Class cls) {
        StringBuffer stringBuffer = new StringBuffer();
        m19386a(stringBuffer, cls);
        return stringBuffer.toString();
    }

    public static String getInternalName(Class cls) {
        return cls.getName().replace('.', '/');
    }

    public static String getMethodDescriptor(Method method) {
        Class[] parameterTypes = method.getParameterTypes();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        for (int i = 0; i < parameterTypes.length; i += BOOLEAN) {
            m19386a(stringBuffer, parameterTypes[i]);
        }
        stringBuffer.append(')');
        m19386a(stringBuffer, method.getReturnType());
        return stringBuffer.toString();
    }

    public static String getMethodDescriptor(Type type, Type[] typeArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        for (int i = 0; i < typeArr.length; i += BOOLEAN) {
            typeArr[i].m19385a(stringBuffer);
        }
        stringBuffer.append(')');
        type.m19385a(stringBuffer);
        return stringBuffer.toString();
    }

    public static Type getObjectType(String str) {
        char[] toCharArray = str.toCharArray();
        return new Type(toCharArray[0] == '[' ? ARRAY : OBJECT, toCharArray, 0, toCharArray.length);
    }

    public static Type getReturnType(String str) {
        return m19384a(str.toCharArray(), str.indexOf(41) + BOOLEAN);
    }

    public static Type getReturnType(Method method) {
        return getType(method.getReturnType());
    }

    public static Type getType(Class cls) {
        return cls.isPrimitive() ? cls == Integer.TYPE ? INT_TYPE : cls == Void.TYPE ? VOID_TYPE : cls == Boolean.TYPE ? BOOLEAN_TYPE : cls == Byte.TYPE ? BYTE_TYPE : cls == Character.TYPE ? CHAR_TYPE : cls == Short.TYPE ? SHORT_TYPE : cls == Double.TYPE ? DOUBLE_TYPE : cls == Float.TYPE ? FLOAT_TYPE : LONG_TYPE : getType(getDescriptor(cls));
    }

    public static Type getType(String str) {
        return m19384a(str.toCharArray(), 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Type)) {
            return false;
        }
        Type type = (Type) obj;
        if (this.f16267a != type.f16267a) {
            return false;
        }
        if (this.f16267a != OBJECT && this.f16267a != ARRAY) {
            return true;
        }
        if (this.f16270d != type.f16270d) {
            return false;
        }
        int i = this.f16269c;
        int i2 = type.f16269c;
        int i3 = this.f16270d + i;
        while (i < i3) {
            if (this.f16268b[i] != type.f16268b[i2]) {
                return false;
            }
            i += BOOLEAN;
            i2 += BOOLEAN;
        }
        return true;
    }

    public String getClassName() {
        switch (this.f16267a) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return "void";
            case BOOLEAN /*1*/:
                return "boolean";
            case CHAR /*2*/:
                return "char";
            case BYTE /*3*/:
                return "byte";
            case SHORT /*4*/:
                return "short";
            case INT /*5*/:
                return "int";
            case FLOAT /*6*/:
                return "float";
            case LONG /*7*/:
                return "long";
            case DOUBLE /*8*/:
                return "double";
            case ARRAY /*9*/:
                StringBuffer stringBuffer = new StringBuffer(getElementType().getClassName());
                for (int dimensions = getDimensions(); dimensions > 0; dimensions--) {
                    stringBuffer.append("[]");
                }
                return stringBuffer.toString();
            default:
                return new String(this.f16268b, this.f16269c, this.f16270d).replace('/', '.');
        }
    }

    public String getDescriptor() {
        StringBuffer stringBuffer = new StringBuffer();
        m19385a(stringBuffer);
        return stringBuffer.toString();
    }

    public int getDimensions() {
        int i = BOOLEAN;
        while (this.f16268b[this.f16269c + i] == '[') {
            i += BOOLEAN;
        }
        return i;
    }

    public Type getElementType() {
        return m19384a(this.f16268b, this.f16269c + getDimensions());
    }

    public String getInternalName() {
        return new String(this.f16268b, this.f16269c, this.f16270d);
    }

    public int getOpcode(int i) {
        int i2 = SHORT;
        if (i == 46 || i == 79) {
            if (this.f16268b == null) {
                i2 = (this.f16269c & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> DOUBLE;
            }
            return i2 + i;
        }
        if (this.f16268b == null) {
            i2 = (this.f16269c & 16711680) >> 16;
        }
        return i2 + i;
    }

    public int getSize() {
        return this.f16268b == null ? this.f16269c & Util.MASK_8BIT : BOOLEAN;
    }

    public int getSort() {
        return this.f16267a;
    }

    public int hashCode() {
        int i = this.f16267a * 13;
        if (this.f16267a == OBJECT || this.f16267a == ARRAY) {
            int i2 = this.f16269c;
            int i3 = i2 + this.f16270d;
            while (i2 < i3) {
                int i4 = (i + this.f16268b[i2]) * 17;
                i2 += BOOLEAN;
                i = i4;
            }
        }
        return i;
    }

    public String toString() {
        return getDescriptor();
    }
}
