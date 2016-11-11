package com.tencent.mm.algorithm;

import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public final class MD5 {
    private MD5() {
    }

    public static String getMD5(File file) {
        return getMD5(file, 102400);
    }

    public static String getMD5(File file, int i) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        Throwable th;
        if (file == null || i <= 0 || !file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                String md5 = getMD5(fileInputStream, (int) (((long) i) <= file.length() ? (long) i : file.length()));
                fileInputStream.close();
                try {
                    fileInputStream.close();
                    return md5;
                } catch (IOException e) {
                    return md5;
                }
            } catch (Exception e2) {
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e3) {
                    }
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            fileInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    public static String getMD5(File file, int i, int i2) {
        FileInputStream fileInputStream;
        Throwable th;
        if (file == null || !file.exists() || i < 0 || i2 <= 0) {
            return null;
        }
        FileInputStream fileInputStream2;
        try {
            fileInputStream2 = new FileInputStream(file);
            try {
                String md5 = getMD5(fileInputStream2, 102400, i, i2);
                fileInputStream2.close();
                try {
                    fileInputStream2.close();
                    return md5;
                } catch (IOException e) {
                    return md5;
                }
            } catch (Exception e2) {
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                    }
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
    }

    public static final String getMD5(FileInputStream fileInputStream, int i) {
        String str = null;
        if (fileInputStream != null && i > 0) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                StringBuilder stringBuilder = new StringBuilder(32);
                byte[] bArr = new byte[i];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    instance.update(bArr, 0, read);
                }
                byte[] digest = instance.digest();
                for (byte b : digest) {
                    stringBuilder.append(Integer.toString((b & Util.MASK_8BIT) + Opcodes.ACC_NATIVE, 16).substring(1));
                }
                str = stringBuilder.toString();
            } catch (Exception e) {
            }
        }
        return str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String getMD5(java.io.FileInputStream r8, int r9, int r10, int r11) {
        /*
        r0 = 0;
        r1 = 0;
        if (r8 == 0) goto L_0x000a;
    L_0x0004:
        if (r9 <= 0) goto L_0x000a;
    L_0x0006:
        if (r10 < 0) goto L_0x000a;
    L_0x0008:
        if (r11 > 0) goto L_0x000b;
    L_0x000a:
        return r0;
    L_0x000b:
        r2 = (long) r10;
        r2 = r8.skip(r2);	 Catch:{ Exception -> 0x0063 }
        r4 = (long) r10;	 Catch:{ Exception -> 0x0063 }
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 < 0) goto L_0x000a;
    L_0x0015:
        r2 = "MD5";
        r3 = java.security.MessageDigest.getInstance(r2);	 Catch:{ Exception -> 0x0063 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0063 }
        r2 = 32;
        r4.<init>(r2);	 Catch:{ Exception -> 0x0063 }
        r5 = new byte[r9];	 Catch:{ Exception -> 0x0063 }
        r2 = r1;
    L_0x0025:
        r6 = r8.read(r5);	 Catch:{ Exception -> 0x0063 }
        r7 = -1;
        if (r6 == r7) goto L_0x0040;
    L_0x002c:
        if (r2 >= r11) goto L_0x0040;
    L_0x002e:
        r7 = r2 + r6;
        if (r7 > r11) goto L_0x0038;
    L_0x0032:
        r7 = 0;
        r3.update(r5, r7, r6);	 Catch:{ Exception -> 0x0063 }
        r2 = r2 + r6;
        goto L_0x0025;
    L_0x0038:
        r6 = 0;
        r2 = r11 - r2;
        r3.update(r5, r6, r2);	 Catch:{ Exception -> 0x0063 }
        r2 = r11;
        goto L_0x0025;
    L_0x0040:
        r2 = r3.digest();	 Catch:{ Exception -> 0x0063 }
    L_0x0044:
        r3 = r2.length;	 Catch:{ Exception -> 0x0063 }
        if (r1 >= r3) goto L_0x005e;
    L_0x0047:
        r3 = r2[r1];	 Catch:{ Exception -> 0x0063 }
        r3 = r3 & 255;
        r3 = r3 + 256;
        r5 = 16;
        r3 = java.lang.Integer.toString(r3, r5);	 Catch:{ Exception -> 0x0063 }
        r5 = 1;
        r3 = r3.substring(r5);	 Catch:{ Exception -> 0x0063 }
        r4.append(r3);	 Catch:{ Exception -> 0x0063 }
        r1 = r1 + 1;
        goto L_0x0044;
    L_0x005e:
        r0 = r4.toString();	 Catch:{ Exception -> 0x0063 }
        goto L_0x000a;
    L_0x0063:
        r1 = move-exception;
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mm.algorithm.MD5.getMD5(java.io.FileInputStream, int, int, int):java.lang.String");
    }

    public static String getMD5(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str);
        return file.exists() ? getMD5(file, 102400) : null;
    }

    public static String getMD5(String str, int i, int i2) {
        if (str == null) {
            return null;
        }
        File file = new File(str);
        return file.exists() ? getMD5(file, i, i2) : null;
    }

    public static final String getMessageDigest(byte[] bArr) {
        int i = 0;
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            byte[] digest = instance.digest();
            int length = digest.length;
            char[] cArr2 = new char[(length * 2)];
            int i2 = 0;
            while (i < length) {
                byte b = digest[i];
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b & 15];
                i++;
            }
            return new String(cArr2);
        } catch (Exception e) {
            return null;
        }
    }

    public static final byte[] getRawDigest(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            return instance.digest();
        } catch (Exception e) {
            return null;
        }
    }
}
