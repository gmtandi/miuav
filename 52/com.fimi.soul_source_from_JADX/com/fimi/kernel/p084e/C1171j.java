package com.fimi.kernel.p084e;

import com.tencent.mm.sdk.platformtools.Util;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.kernel.e.j */
public final class C1171j {
    private static final char[] f5322a;
    private static final byte[] f5323b;

    static {
        f5322a = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', SignatureVisitor.EXTENDS, '/'};
        f5323b = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 62, (byte) -1, (byte) -1, (byte) -1, (byte) 63, SmileConstants.TOKEN_KEY_LONG_STRING, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, SmileConstants.HEADER_BYTE_1, (byte) 59, (byte) 60, (byte) 61, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, SmileConstants.TOKEN_LITERAL_EMPTY_STRING, SmileConstants.TOKEN_LITERAL_NULL, SmileConstants.TOKEN_LITERAL_FALSE, SmileConstants.TOKEN_LITERAL_TRUE, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, SmileConstants.HEADER_BYTE_2, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1};
    }

    public static final String m8127a(String str, String str2) {
        return C1171j.m8128a(str, str2, 0);
    }

    public static final String m8128a(String str, String str2, int i) {
        int i2 = 0;
        try {
            byte[] bytes = str.getBytes(str2);
            int length = bytes.length;
            int ceil = (int) Math.ceil(((double) length) * 1.36d);
            int i3 = i > 0 ? ceil / i : 0;
            StringBuffer stringBuffer = new StringBuffer(ceil + i3);
            ceil = length % 3;
            length -= ceil;
            while (i2 < length) {
                int i4 = i2 + 1;
                int i5 = i4 + 1;
                i4 = ((bytes[i4] & Util.MASK_8BIT) << 8) | ((bytes[i2] & Util.MASK_8BIT) << 16);
                i2 = i5 + 1;
                i4 |= bytes[i5] & Util.MASK_8BIT;
                stringBuffer.append(f5322a[i4 >> 18]);
                stringBuffer.append(f5322a[(i4 >> 12) & 63]);
                stringBuffer.append(f5322a[(i4 >> 6) & 63]);
                stringBuffer.append(f5322a[i4 & 63]);
            }
            if (ceil == 1) {
                length = i2 + 1;
                i2 = bytes[i2] & Util.MASK_8BIT;
                stringBuffer.append(f5322a[i2 >> 2]);
                stringBuffer.append(f5322a[(i2 & 3) << 4]);
                stringBuffer.append("==");
            } else if (ceil == 2) {
                length = i2 + 1;
                ceil = length + 1;
                i2 = ((bytes[i2] & Util.MASK_8BIT) << 8) | (bytes[length] & Util.MASK_8BIT);
                stringBuffer.append(f5322a[i2 >> 10]);
                stringBuffer.append(f5322a[(i2 >> 4) & 63]);
                stringBuffer.append(f5322a[(i2 & 15) << 2]);
                stringBuffer.append("=");
            }
            if (i3 > 0) {
                i3 = i;
                while (i3 < stringBuffer.length()) {
                    stringBuffer.insert(i3, '\n');
                    i3 = (i3 + i) + 1;
                }
            }
            return stringBuffer.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final String m8129b(String str, String str2) {
        String str3 = null;
        try {
            byte[] bytes = str.getBytes(str2);
            int length = bytes.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) (((double) length) * 0.67d));
            int i = 0;
            while (i < length) {
                int i2;
                byte b;
                byte b2;
                while (i < length) {
                    i2 = i + 1;
                    b = f5323b[bytes[i]];
                    if (i2 >= length || b != (byte) -1) {
                        b2 = b;
                        i = i2;
                        break;
                    }
                    i = i2;
                }
                int i3 = -1;
                if (i3 != -1) {
                    while (i < length) {
                        i2 = i + 1;
                        b = f5323b[bytes[i]];
                        if (i2 >= length || b != (byte) -1) {
                            byte b3 = b;
                            i = i2;
                            break;
                        }
                        i = i2;
                    }
                    int i4 = -1;
                    if (i4 != -1) {
                        byteArrayOutputStream.write((i3 << 2) | ((i4 & 48) >>> 4));
                        while (i < length) {
                            i2 = i + 1;
                            b = bytes[i];
                            if (b == (byte) 61) {
                                i3 = -1;
                                i = i2;
                                break;
                            }
                            b = f5323b[b];
                            if (i2 >= length || b != (byte) -1) {
                                b2 = b;
                                i = i2;
                                break;
                            }
                            i = i2;
                        }
                        i3 = -1;
                        if (i3 != -1) {
                            byteArrayOutputStream.write(((i4 & 15) << 4) | ((i3 & 60) >>> 2));
                            while (i < length) {
                                i2 = i + 1;
                                b = bytes[i];
                                if (b != (byte) 61) {
                                    i = f5323b[b];
                                    if (i != -1) {
                                        break;
                                    }
                                    i = i2;
                                } else {
                                    i = -1;
                                    break;
                                }
                            }
                            i2 = i;
                            i = -1;
                            if (i != -1) {
                                byteArrayOutputStream.write(i | ((i3 & 3) << 6));
                                i = i2;
                            }
                        }
                    }
                }
            }
            try {
                break;
                str3 = byteArrayOutputStream.toString(str2);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return str3;
    }
}
