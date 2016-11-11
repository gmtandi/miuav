package com.p016a;

import android.text.TextUtils;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.gf */
public class gf {
    public static String m1955a(long j) {
        String str = null;
        try {
            str = new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS", Locale.CHINA).format(new Date(j));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return str;
    }

    public static String m1956a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return C2915a.f14760f;
            }
            String[] split = str.split("&");
            Arrays.sort(split);
            StringBuffer stringBuffer = new StringBuffer();
            for (String append : split) {
                stringBuffer.append(append);
                stringBuffer.append("&");
            }
            String stringBuffer2 = stringBuffer.toString();
            if (stringBuffer2.length() > 1) {
                return (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1);
            }
            return str;
        } catch (Throwable th) {
            C0247g.m1917a(th, "Utils", "sortParams");
        }
    }

    public static String m1957a(Throwable th) {
        Throwable cause;
        Throwable th2;
        String str = null;
        Writer stringWriter;
        PrintWriter printWriter;
        try {
            stringWriter = new StringWriter();
            try {
                printWriter = new PrintWriter(stringWriter);
                try {
                    th.printStackTrace(printWriter);
                    for (cause = th.getCause(); cause != null; cause = cause.getCause()) {
                        cause.printStackTrace(printWriter);
                    }
                    str = stringWriter.toString();
                    if (stringWriter != null) {
                        try {
                            stringWriter.close();
                        } catch (Throwable cause2) {
                            cause2.printStackTrace();
                        }
                    }
                    if (printWriter != null) {
                        try {
                            printWriter.close();
                        } catch (Throwable th3) {
                            cause2 = th3;
                            cause2.printStackTrace();
                            return str;
                        }
                    }
                } catch (Throwable th4) {
                    cause2 = th4;
                    try {
                        cause2.printStackTrace();
                        if (stringWriter != null) {
                            try {
                                stringWriter.close();
                            } catch (Throwable cause22) {
                                cause22.printStackTrace();
                            }
                        }
                        if (printWriter != null) {
                            try {
                                printWriter.close();
                            } catch (Throwable th5) {
                                cause22 = th5;
                                cause22.printStackTrace();
                                return str;
                            }
                        }
                        return str;
                    } catch (Throwable th6) {
                        th2 = th6;
                        if (stringWriter != null) {
                            try {
                                stringWriter.close();
                            } catch (Throwable cause222) {
                                cause222.printStackTrace();
                            }
                        }
                        if (printWriter != null) {
                            try {
                                printWriter.close();
                            } catch (Throwable cause2222) {
                                cause2222.printStackTrace();
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable cause22222) {
                printWriter = null;
                th2 = cause22222;
                if (stringWriter != null) {
                    stringWriter.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
                throw th2;
            }
        } catch (Throwable cause222222) {
            printWriter = null;
            stringWriter = null;
            th2 = cause222222;
            if (stringWriter != null) {
                stringWriter.close();
            }
            if (printWriter != null) {
                printWriter.close();
            }
            throw th2;
        }
        return str;
    }

    static String m1958a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append((String) entry.getValue());
        }
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.security.PublicKey m1959a(android.content.Context r5) {
        /*
        r0 = 0;
        r1 = 674; // 0x2a2 float:9.44E-43 double:3.33E-321;
        r1 = new byte[r1];
        r1 = {48, -126, 2, -98, 48, -126, 2, 7, -96, 3, 2, 1, 2, 2, 9, 0, -99, 15, 119, 58, 44, -19, -105, -40, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 48, 104, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 67, 78, 49, 19, 48, 17, 6, 3, 85, 4, 8, 12, 10, 83, 111, 109, 101, 45, 83, 116, 97, 116, 101, 49, 16, 48, 14, 6, 3, 85, 4, 7, 12, 7, 66, 101, 105, 106, 105, 110, 103, 49, 17, 48, 15, 6, 3, 85, 4, 10, 12, 8, 65, 117, 116, 111, 110, 97, 118, 105, 49, 31, 48, 29, 6, 3, 85, 4, 3, 12, 22, 99, 111, 109, 46, 97, 117, 116, 111, 110, 97, 118, 105, 46, 97, 112, 105, 115, 101, 114, 118, 101, 114, 48, 30, 23, 13, 49, 51, 48, 56, 49, 53, 48, 55, 53, 54, 53, 53, 90, 23, 13, 50, 51, 48, 56, 49, 51, 48, 55, 53, 54, 53, 53, 90, 48, 104, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 67, 78, 49, 19, 48, 17, 6, 3, 85, 4, 8, 12, 10, 83, 111, 109, 101, 45, 83, 116, 97, 116, 101, 49, 16, 48, 14, 6, 3, 85, 4, 7, 12, 7, 66, 101, 105, 106, 105, 110, 103, 49, 17, 48, 15, 6, 3, 85, 4, 10, 12, 8, 65, 117, 116, 111, 110, 97, 118, 105, 49, 31, 48, 29, 6, 3, 85, 4, 3, 12, 22, 99, 111, 109, 46, 97, 117, 116, 111, 110, 97, 118, 105, 46, 97, 112, 105, 115, 101, 114, 118, 101, 114, 48, -127, -97, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -127, -115, 0, 48, -127, -119, 2, -127, -127, 0, -15, -27, -128, -56, 118, -59, 62, -127, 79, 125, -36, 121, 0, 63, -125, -30, 118, 5, -85, -121, 91, 39, 90, 123, 72, -126, -83, -41, -45, -77, -42, -120, -81, 23, -2, -121, -29, 123, -7, 22, -114, -20, -25, 74, 67, -43, 65, 124, -7, 11, -72, 38, -123, 16, -58, 80, 32, 58, -33, 14, 11, 36, 60, 13, -121, 100, 105, -32, 123, -31, 114, -101, -41, 12, 100, 33, -120, 63, 126, -123, 48, 55, 80, -116, 28, -10, 125, 59, -41, -95, -126, 118, -70, 43, -127, 9, 93, -100, 81, -19, -114, -41, 85, -103, -37, -116, 118, 72, 86, 125, -43, -92, -11, 63, 69, -38, -10, -65, 126, -53, -115, 60, 62, -86, -80, 1, 39, 19, 2, 3, 1, 0, 1, -93, 80, 48, 78, 48, 29, 6, 3, 85, 29, 14, 4, 22, 4, 20, -29, 63, 48, -79, -113, -13, 26, 85, 22, -27, 93, -5, 122, -103, -109, 14, -18, 6, -13, -109, 48, 31, 6, 3, 85, 29, 35, 4, 24, 48, 22, -128, 20, -29, 63, 48, -79, -113, -13, 26, 85, 22, -27, 93, -5, 122, -103, -109, 14, -18, 6, -13, -109, 48, 12, 6, 3, 85, 29, 19, 4, 5, 48, 3, 1, 1, -1, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 3, -127, -127, 0, -32, -74, 55, -125, -58, -128, 15, -62, 100, -60, 3, -86, 81, 112, -61, -56, -69, -126, 8, 99, -100, -38, -108, -56, -122, 125, 19, -64, -61, 90, 85, -47, -8, -123, -103, 105, 77, -32, -65, -62, -28, 67, -28, -78, 116, -49, 120, -2, 33, 13, 47, 46, -5, -112, 3, -101, -125, -115, 92, -124, 58, 80, 107, -67, 82, 6, -63, 39, -90, -1, 85, -58, 82, -115, 119, 13, -4, -32, 0, -98, 100, -41, 94, -75, 75, -103, 126, -80, 85, 40, -27, 60, 105, 28, -27, -21, -15, -98, 103, -88, -109, 35, -119, -27, -26, -122, 113, 63, 35, -33, 70, 23, 33, -23, 66, 108, 56, 112, 46, -85, -123, -123, 33, 118, 27, 96, -7, -103};
        r2 = new java.io.ByteArrayInputStream;	 Catch:{ Throwable -> 0x003b, all -> 0x0043 }
        r2.<init>(r1);	 Catch:{ Throwable -> 0x003b, all -> 0x0043 }
        r1 = "X.509";
        r1 = java.security.cert.CertificateFactory.getInstance(r1);	 Catch:{ Throwable -> 0x004e }
        r3 = "RSA";
        r3 = java.security.KeyFactory.getInstance(r3);	 Catch:{ Throwable -> 0x004e }
        r1 = r1.generateCertificate(r2);	 Catch:{ Throwable -> 0x004e }
        if (r1 == 0) goto L_0x0021;
    L_0x001f:
        if (r3 != 0) goto L_0x0027;
    L_0x0021:
        if (r2 == 0) goto L_0x0026;
    L_0x0023:
        r2.close();
    L_0x0026:
        return r0;
    L_0x0027:
        r4 = new java.security.spec.X509EncodedKeySpec;	 Catch:{ Throwable -> 0x004e }
        r1 = r1.getPublicKey();	 Catch:{ Throwable -> 0x004e }
        r1 = r1.getEncoded();	 Catch:{ Throwable -> 0x004e }
        r4.<init>(r1);	 Catch:{ Throwable -> 0x004e }
        r0 = r3.generatePublic(r4);	 Catch:{ Throwable -> 0x004e }
        if (r2 == 0) goto L_0x0026;
    L_0x003a:
        goto L_0x0023;
    L_0x003b:
        r1 = move-exception;
        r2 = r0;
    L_0x003d:
        r1.printStackTrace();	 Catch:{ all -> 0x004c }
        if (r2 == 0) goto L_0x0026;
    L_0x0042:
        goto L_0x0023;
    L_0x0043:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0046:
        if (r2 == 0) goto L_0x004b;
    L_0x0048:
        r2.close();
    L_0x004b:
        throw r0;
    L_0x004c:
        r0 = move-exception;
        goto L_0x0046;
    L_0x004e:
        r1 = move-exception;
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.gf.a(android.content.Context):java.security.PublicKey");
    }

    public static boolean m1960a(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }

    public static byte[] m1961a() {
        int i = 0;
        try {
            String[] split = new StringBuffer("16,16,18,77,15,911,121,77,121,911,38,77,911,99,86,67,611,96,48,77,84,911,38,67,021,301,86,67,611,98,48,77,511,77,48,97,511,58,48,97,511,84,501,87,511,96,48,77,221,911,38,77,121,37,86,67,25,301,86,67,021,96,86,67,021,701,86,67,35,56,86,67,611,37,221,87").reverse().toString().split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            byte[] bArr = new byte[split.length];
            for (int i2 = 0; i2 < split.length; i2++) {
                bArr[i2] = Byte.parseByte(split[i2]);
            }
            split = new StringBuffer(new String(fy.m1905b(new String(bArr)))).reverse().toString().split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            byte[] bArr2 = new byte[split.length];
            while (i < split.length) {
                bArr2[i] = Byte.parseByte(split[i]);
                i++;
            }
            return bArr2;
        } catch (Throwable th) {
            C0247g.m1917a(th, "Utils", "getIV");
            return new byte[16];
        }
    }

    public static byte[] m1962a(byte[] bArr) {
        try {
            return gf.m1968f(bArr);
        } catch (Throwable e) {
            C0247g.m1917a(e, "Utils", "gZip");
            return new byte[0];
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "Utils", "gZip");
            return new byte[0];
        }
    }

    public static String m1963b(Map<String, String> map) {
        return gf.m1956a(gf.m1958a((Map) map));
    }

    public static byte[] m1964b(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        ZipOutputStream zipOutputStream;
        Throwable th;
        Throwable th2;
        byte[] bArr2 = null;
        if (!(bArr == null || bArr.length == 0)) {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
                } catch (Throwable th3) {
                    zipOutputStream = bArr2;
                    th2 = th3;
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th2;
                }
                try {
                    zipOutputStream.putNextEntry(new ZipEntry("log"));
                    zipOutputStream.write(bArr);
                    zipOutputStream.closeEntry();
                    zipOutputStream.finish();
                    bArr2 = byteArrayOutputStream.toByteArray();
                    if (zipOutputStream != null) {
                        try {
                            zipOutputStream.close();
                        } catch (Throwable th32) {
                            C0247g.m1917a(th32, "Utils", "zip1");
                            th32.printStackTrace();
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th4) {
                            th32 = th4;
                            C0247g.m1917a(th32, "Utils", "zip2");
                            th32.printStackTrace();
                            return bArr2;
                        }
                    }
                } catch (Throwable th5) {
                    th32 = th5;
                    C0247g.m1917a(th32, "Utils", "zip");
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    return bArr2;
                }
            } catch (Throwable th322) {
                byteArrayOutputStream = bArr2;
                zipOutputStream = bArr2;
                th2 = th322;
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th2;
            }
        }
        return bArr2;
    }

    static String m1965c(byte[] bArr) {
        try {
            return gf.m1967e(bArr);
        } catch (Throwable th) {
            C0247g.m1917a(th, "Utils", "HexString");
            return null;
        }
    }

    static String m1966d(byte[] bArr) {
        try {
            return gf.m1967e(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String m1967e(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bArr == null) {
            return null;
        }
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & Util.MASK_8BIT);
            if (toHexString.length() == 1) {
                toHexString = '0' + toHexString;
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    private static byte[] m1968f(byte[] bArr) {
        IOException e;
        Throwable th;
        Throwable th2;
        byte[] bArr2 = null;
        if (bArr != null) {
            ByteArrayOutputStream byteArrayOutputStream;
            GZIPOutputStream gZIPOutputStream;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    try {
                        gZIPOutputStream.write(bArr);
                        gZIPOutputStream.finish();
                        bArr2 = byteArrayOutputStream.toByteArray();
                        if (gZIPOutputStream != null) {
                            gZIPOutputStream.close();
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                    } catch (IOException e2) {
                        e = e2;
                        try {
                            throw e;
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        throw th;
                    }
                } catch (IOException e3) {
                    IOException iOException = e3;
                    gZIPOutputStream = null;
                    e = iOException;
                    throw e;
                } catch (Throwable th5) {
                    th2 = th5;
                    gZIPOutputStream = null;
                    th = th2;
                    if (gZIPOutputStream != null) {
                        gZIPOutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e32) {
                byteArrayOutputStream = null;
                e = e32;
                gZIPOutputStream = null;
                throw e;
            } catch (Throwable th52) {
                byteArrayOutputStream = null;
                th = th52;
                gZIPOutputStream = null;
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        }
        return bArr2;
    }
}
