package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import com.amap.api.mapcore.util.cx.C0372b;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

public class bz {
    private static boolean f2296a;

    static {
        f2296a = true;
    }

    public static void m3795a(Context context) {
        try {
            if (m3804g(context)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date()));
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                stringBuffer.append(UUID.randomUUID().toString());
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                if (stringBuffer.length() == 53) {
                    Object a = bx.m3778a(stringBuffer.toString());
                    Object b = m3799b(context);
                    byte[] bArr = new byte[(a.length + b.length)];
                    System.arraycopy(a, 0, bArr, 0, a.length);
                    System.arraycopy(b, 0, bArr, a.length, b.length);
                    dd.m4003a().m4006b(new cd(bx.m3783c(bArr), Constants.VIA_SSO_LOGIN));
                }
            }
        } catch (Throwable e) {
            cb.m3809a(e, "StatisticsManager", "updateStaticsData");
        } catch (Throwable e2) {
            cb.m3809a(e2, "StatisticsManager", "updateStaticsData");
        }
    }

    private static void m3796a(Context context, long j) {
        FileOutputStream fileOutputStream;
        Throwable th;
        FileNotFoundException e;
        IOException e2;
        File file = new File(cc.m3814a(context, "c.log"));
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(bx.m3778a(String.valueOf(j)));
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (FileNotFoundException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th3) {
                            th = th3;
                            th.printStackTrace();
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th5) {
                            th5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e2 = e4;
                e2.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th6) {
                        th = th6;
                        th.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            fileOutputStream = null;
            e.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (IOException e6) {
            e2 = e6;
            fileOutputStream = null;
            e2.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th7) {
            th = th7;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    private static byte[] m3797a(Context context, byte[] bArr) {
        try {
            return bn.m3671c(context, bArr);
        } catch (CertificateException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeySpecException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return null;
        } catch (IOException e4) {
            e4.printStackTrace();
            return null;
        } catch (InvalidKeyException e5) {
            e5.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e6) {
            e6.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e7) {
            e7.printStackTrace();
            return null;
        } catch (BadPaddingException e8) {
            e8.printStackTrace();
            return null;
        }
    }

    private static byte[] m3798a(cx cxVar, String str) {
        C0372b a;
        byte[] bArr;
        Throwable th;
        Throwable th2;
        InputStream inputStream = null;
        byte[] bArr2 = new byte[0];
        try {
            a = cxVar.m3981a(str);
            try {
                inputStream = a.m3944a(0);
                bArr = new byte[inputStream.available()];
            } catch (Throwable th3) {
                Throwable th4 = th3;
                bArr = bArr2;
                th2 = th4;
                try {
                    th2.printStackTrace();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th22) {
                            th22.printStackTrace();
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable th5) {
                            th22 = th5;
                            th22.printStackTrace();
                            return bArr;
                        }
                    }
                    return bArr;
                } catch (Throwable th6) {
                    th3 = th6;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th222) {
                            th222.printStackTrace();
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable th2222) {
                            th2222.printStackTrace();
                        }
                    }
                    throw th3;
                }
            }
            try {
                inputStream.read(bArr);
                cxVar.m3987c(str);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th22222) {
                        th22222.printStackTrace();
                    }
                }
                if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable th7) {
                        th22222 = th7;
                        th22222.printStackTrace();
                        return bArr;
                    }
                }
            } catch (Throwable th8) {
                th22222 = th8;
                th22222.printStackTrace();
                if (inputStream != null) {
                    inputStream.close();
                }
                if (a != null) {
                    a.close();
                }
                return bArr;
            }
        } catch (Throwable th9) {
            th3 = th9;
            a = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (a != null) {
                a.close();
            }
            throw th3;
        }
        return bArr;
    }

    private static byte[] m3799b(Context context) {
        Object c = m3800c(context);
        Object e = m3802e(context);
        byte[] bArr = new byte[(c.length + e.length)];
        System.arraycopy(c, 0, bArr, 0, c.length);
        System.arraycopy(e, 0, bArr, c.length, e.length);
        return m3797a(context, bArr);
    }

    private static byte[] m3800c(Context context) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        try {
            bx.m3775a(byteArrayOutputStream, "1.2.12.5");
            bx.m3775a(byteArrayOutputStream, bq.m3707q(context));
            bx.m3775a(byteArrayOutputStream, bq.m3699i(context));
            bx.m3775a(byteArrayOutputStream, bq.m3696f(context));
            bx.m3775a(byteArrayOutputStream, Build.MANUFACTURER);
            bx.m3775a(byteArrayOutputStream, Build.MODEL);
            bx.m3775a(byteArrayOutputStream, Build.DEVICE);
            bx.m3775a(byteArrayOutputStream, bq.m3708r(context));
            bx.m3775a(byteArrayOutputStream, bl.m3649c(context));
            bx.m3775a(byteArrayOutputStream, bl.m3650d(context));
            bx.m3775a(byteArrayOutputStream, bl.m3652f(context));
            byteArrayOutputStream.write(new byte[]{(byte) 0});
            bArr = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return bArr;
            }
        } catch (Throwable th3) {
            th = th3;
            th.printStackTrace();
        }
        return bArr;
    }

    private static int m3801d(Context context) {
        int i = 0;
        try {
            File file = new File(cc.m3814a(context, cc.f2319e));
            if (file.exists()) {
                i = file.list().length;
            }
        } catch (Throwable th) {
            cb.m3809a(th, "StatisticsManager", "getFileNum");
        }
        return i;
    }

    private static byte[] m3802e(Context context) {
        Throwable th;
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        String a = cc.m3814a(context, cc.f2319e);
        cx cxVar = null;
        try {
            cxVar = cx.m3961a(new File(a), 1, 1, 10240);
            File file = new File(a);
            if (file != null && file.exists()) {
                String[] list = file.list();
                int length = list.length;
                while (i < length) {
                    String str = list[i];
                    if (str.contains(".0")) {
                        byteArrayOutputStream.write(m3798a(cxVar, str.split("\\.")[0]));
                    }
                    i++;
                }
            }
            bArr = byteArrayOutputStream.toByteArray();
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (cxVar != null) {
                try {
                    cxVar.close();
                } catch (Throwable th2) {
                    th = th2;
                    th.printStackTrace();
                    return bArr;
                }
            }
        } catch (Throwable th3) {
            cb.m3809a(th3, "StatisticsManager", "getContent");
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (cxVar != null) {
                cxVar.close();
            }
        } catch (Throwable th4) {
            th3 = th4;
            th3.printStackTrace();
        }
        return bArr;
    }

    private static long m3803f(android.content.Context r7) {
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.amap.api.mapcore.util.bz.f(android.content.Context):long. bs: [B:17:0x0038, B:40:0x0063]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:57)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
        /*
        r0 = 0;
        r2 = "c.log";
        r2 = com.amap.api.mapcore.util.cc.m3814a(r7, r2);
        r5 = new java.io.File;
        r5.<init>(r2);
        r2 = r5.exists();
        if (r2 != 0) goto L_0x0014;
    L_0x0013:
        return r0;
    L_0x0014:
        r4 = 0;
        r3 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0036, IOException -> 0x0047, Throwable -> 0x0058, all -> 0x0085 }
        r3.<init>(r5);	 Catch:{ FileNotFoundException -> 0x0036, IOException -> 0x0047, Throwable -> 0x0058, all -> 0x0085 }
        r2 = r3.available();	 Catch:{ FileNotFoundException -> 0x008c, IOException -> 0x008a, Throwable -> 0x0088 }
        r2 = new byte[r2];	 Catch:{ FileNotFoundException -> 0x008c, IOException -> 0x008a, Throwable -> 0x0088 }
        r3.read(r2);	 Catch:{ FileNotFoundException -> 0x008c, IOException -> 0x008a, Throwable -> 0x0088 }
        r2 = com.amap.api.mapcore.util.bx.m3772a(r2);	 Catch:{ FileNotFoundException -> 0x008c, IOException -> 0x008a, Throwable -> 0x0088 }
        r0 = java.lang.Long.parseLong(r2);	 Catch:{ FileNotFoundException -> 0x008c, IOException -> 0x008a, Throwable -> 0x0088 }
        if (r3 == 0) goto L_0x0013;
    L_0x002d:
        r3.close();	 Catch:{ Throwable -> 0x0031 }
        goto L_0x0013;
    L_0x0031:
        r2 = move-exception;
    L_0x0032:
        r2.printStackTrace();
        goto L_0x0013;
    L_0x0036:
        r2 = move-exception;
        r3 = r4;
    L_0x0038:
        r4 = "StatisticsManager";	 Catch:{ all -> 0x0079 }
        r5 = "getUpdateTime";	 Catch:{ all -> 0x0079 }
        com.amap.api.mapcore.util.cb.m3809a(r2, r4, r5);	 Catch:{ all -> 0x0079 }
        if (r3 == 0) goto L_0x0013;
    L_0x0041:
        r3.close();	 Catch:{ Throwable -> 0x0045 }
        goto L_0x0013;
    L_0x0045:
        r2 = move-exception;
        goto L_0x0032;
    L_0x0047:
        r2 = move-exception;
        r3 = r4;
    L_0x0049:
        r4 = "StatisticsManager";	 Catch:{ all -> 0x0079 }
        r5 = "getUpdateTime";	 Catch:{ all -> 0x0079 }
        com.amap.api.mapcore.util.cb.m3809a(r2, r4, r5);	 Catch:{ all -> 0x0079 }
        if (r3 == 0) goto L_0x0013;
    L_0x0052:
        r3.close();	 Catch:{ Throwable -> 0x0056 }
        goto L_0x0013;
    L_0x0056:
        r2 = move-exception;
        goto L_0x0032;
    L_0x0058:
        r2 = move-exception;
        r3 = r4;
    L_0x005a:
        r4 = "StatisticsManager";	 Catch:{ all -> 0x0079 }
        r6 = "getUpdateTime";	 Catch:{ all -> 0x0079 }
        com.amap.api.mapcore.util.cb.m3809a(r2, r4, r6);	 Catch:{ all -> 0x0079 }
        if (r5 == 0) goto L_0x006c;
    L_0x0063:
        r2 = r5.exists();	 Catch:{ Throwable -> 0x0074 }
        if (r2 == 0) goto L_0x006c;	 Catch:{ Throwable -> 0x0074 }
    L_0x0069:
        r5.delete();	 Catch:{ Throwable -> 0x0074 }
    L_0x006c:
        if (r3 == 0) goto L_0x0013;
    L_0x006e:
        r3.close();	 Catch:{ Throwable -> 0x0072 }
        goto L_0x0013;
    L_0x0072:
        r2 = move-exception;
        goto L_0x0032;
    L_0x0074:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x0079 }
        goto L_0x006c;
    L_0x0079:
        r0 = move-exception;
    L_0x007a:
        if (r3 == 0) goto L_0x007f;
    L_0x007c:
        r3.close();	 Catch:{ Throwable -> 0x0080 }
    L_0x007f:
        throw r0;
    L_0x0080:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x007f;
    L_0x0085:
        r0 = move-exception;
        r3 = r4;
        goto L_0x007a;
    L_0x0088:
        r2 = move-exception;
        goto L_0x005a;
    L_0x008a:
        r2 = move-exception;
        goto L_0x0049;
    L_0x008c:
        r2 = move-exception;
        goto L_0x0038;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.bz.f(android.content.Context):long");
    }

    private static boolean m3804g(Context context) {
        try {
            if (bq.m3703m(context) != 1 || !f2296a || m3801d(context) < 100) {
                return false;
            }
            long f = m3803f(context);
            long time = new Date().getTime();
            if (time - f < Util.MILLSECONDS_OF_HOUR) {
                return false;
            }
            m3796a(context, time);
            f2296a = false;
            return true;
        } catch (Throwable th) {
            cb.m3809a(th, "StatisticsManager", "isUpdate");
            return false;
        }
    }
}
