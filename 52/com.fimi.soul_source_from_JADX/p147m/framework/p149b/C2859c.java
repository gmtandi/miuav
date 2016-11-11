package p147m.framework.p149b;

import com.tencent.mm.sdk.platformtools.Util;

/* renamed from: m.framework.b.c */
public class C2859c {
    private static final char[] f14601a;

    static {
        f14601a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    protected static int m16485a(char c, int i) {
        int digit = Character.digit(c, 16);
        if (digit != -1) {
            return digit;
        }
        throw new RuntimeException("Illegal hexadecimal charcter " + c + " at index " + i);
    }

    public static String m16486a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[i])}));
        }
        return stringBuffer.toString();
    }

    public static byte[] m16487a(String str) {
        byte[] bArr = null;
        if (str != null) {
            int length = str.length();
            if (length % 2 != 1) {
                int i = length / 2;
                bArr = new byte[i];
                for (length = 0; length < i; length++) {
                    bArr[length] = (byte) Integer.parseInt(str.substring(length * 2, (length * 2) + 2), 16);
                }
            }
        }
        return bArr;
    }

    public static byte[] m16488a(char[] cArr) {
        int i = 0;
        int length = cArr.length;
        if ((length & 1) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }
        byte[] bArr = new byte[(length >> 1)];
        int i2 = 0;
        while (i < length) {
            i++;
            i++;
            bArr[i2] = (byte) (((C2859c.m16485a(cArr[i], i) << 4) | C2859c.m16485a(cArr[i], i)) & Util.MASK_8BIT);
            i2++;
        }
        return bArr;
    }

    public static String m16489b(byte[] bArr) {
        return new String(C2859c.m16491c(bArr));
    }

    public static byte[] m16490b(String str) {
        return C2859c.m16488a(str.toCharArray());
    }

    public static char[] m16491c(byte[] bArr) {
        int i = 0;
        int length = bArr.length;
        char[] cArr = new char[(length << 1)];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr[i] = f14601a[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr[i3] = f14601a[bArr[i2] & 15];
        }
        return cArr;
    }

    public byte[] m16492a(Object obj) {
        try {
            return C2859c.m16488a(obj instanceof String ? ((String) obj).toCharArray() : (char[]) obj);
        } catch (ClassCastException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public char[] m16493b(Object obj) {
        try {
            return C2859c.m16491c(obj instanceof String ? ((String) obj).getBytes() : (byte[]) obj);
        } catch (ClassCastException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public byte[] m16494d(byte[] bArr) {
        return C2859c.m16488a(new String(bArr).toCharArray());
    }

    public byte[] m16495e(byte[] bArr) {
        return new String(C2859c.m16491c(bArr)).getBytes();
    }
}
