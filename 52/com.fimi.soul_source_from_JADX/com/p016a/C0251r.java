package com.p016a;

import android.content.Context;
import android.os.Looper;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.mm.sdk.contact.RContact;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.openauth.BuildConfig;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.r */
abstract class C0251r {
    private gd f1286a;
    private int f1287b;
    private bg f1288c;
    private az f1289d;

    protected C0251r(int i) {
        this.f1287b = i;
    }

    private String m1986a(Context context, gd gdVar) {
        return fp.m1852a(context, gdVar);
    }

    private String m1987a(Context context, String str) {
        String str2 = null;
        try {
            str2 = fp.m1855a(context, str.getBytes(C1142e.f5201a));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return str2;
    }

    private String m1988a(String str, String str2, String str3, int i, String str4, String str5) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append("\"timestamp\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str5);
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }

    private String m1989a(List<ab> list, Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"pinfo\":\"").append(m2003g(context)).append("\",\"els\":[");
        Object obj = 1;
        for (ab abVar : list) {
            Object obj2;
            String d = m1999d(abVar.m978b());
            if (d != null) {
                if (C2915a.f14760f.equals(d)) {
                    obj2 = obj;
                    obj = obj2;
                } else {
                    String str = d + "||" + abVar.m982d();
                    if (obj != null) {
                        obj = null;
                    } else {
                        stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                    }
                    stringBuilder.append("{\"log\":\"").append(str).append("\"}");
                }
            }
            obj2 = obj;
            obj = obj2;
        }
        if (obj != null) {
            return null;
        }
        stringBuilder.append("]}");
        return stringBuilder.toString();
    }

    private void m1990a(C0264z c0264z, int i) {
        try {
            m1992a(c0264z.m2061a(2, i), c0264z, i);
        } catch (Throwable th) {
            C0247g.m1917a(th, "LogProcessor", "processDeleteFail");
        }
    }

    private void m1991a(C0264z c0264z, String str, String str2, int i, boolean z) {
        ab abVar = new ab();
        abVar.m976a(0);
        abVar.m980b(str);
        abVar.m977a(str2);
        c0264z.m2064b(abVar, i);
    }

    private void m1992a(List<ab> list, C0264z c0264z, int i) {
        if (list != null && list.size() > 0) {
            for (ab abVar : list) {
                if (m1995b(abVar.m978b())) {
                    c0264z.m2063a(abVar.m978b(), i);
                } else {
                    abVar.m976a(2);
                    c0264z.m2062a(abVar, abVar.m975a());
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m1993a(android.content.Context r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, com.p016a.C0264z r13) {
        /*
        r8 = this;
        r3 = 0;
        r2 = 0;
        r1 = 0;
        r0 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r0.<init>();	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r4 = r9.getFilesDir();	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r4 = r4.getAbsolutePath();	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r0.append(r4);	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r4 = com.p016a.C0255n.f1297a;	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r0.append(r4);	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r0.append(r11);	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r0 = r0.toString();	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r4 = new java.io.File;	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r4.<init>(r0);	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r0 = r4.exists();	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        if (r0 != 0) goto L_0x0047;
    L_0x002a:
        r0 = r4.mkdirs();	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        if (r0 != 0) goto L_0x0047;
    L_0x0030:
        r0 = 0;
        if (r3 == 0) goto L_0x0036;
    L_0x0033:
        r3.close();	 Catch:{ Throwable -> 0x011f }
    L_0x0036:
        if (r1 == 0) goto L_0x003b;
    L_0x0038:
        r1.close();	 Catch:{ Throwable -> 0x0125 }
    L_0x003b:
        if (r2 == 0) goto L_0x0046;
    L_0x003d:
        r1 = r2.m1136a();
        if (r1 != 0) goto L_0x0046;
    L_0x0043:
        r2.close();	 Catch:{ Throwable -> 0x012b }
    L_0x0046:
        return r0;
    L_0x0047:
        r0 = 1;
        r5 = 1;
        r6 = 20480; // 0x5000 float:2.8699E-41 double:1.01185E-319;
        r2 = com.p016a.az.m1113a(r4, r0, r5, r6);	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r0 = r8.m2004a(r13);	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r2.m1135a(r0);	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r1 = r2.m1134a(r10);	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        if (r1 == 0) goto L_0x0078;
    L_0x005c:
        r0 = 0;
        if (r3 == 0) goto L_0x0062;
    L_0x005f:
        r3.close();	 Catch:{ Throwable -> 0x012e }
    L_0x0062:
        if (r1 == 0) goto L_0x0067;
    L_0x0064:
        r1.close();	 Catch:{ Throwable -> 0x0134 }
    L_0x0067:
        if (r2 == 0) goto L_0x0046;
    L_0x0069:
        r1 = r2.m1136a();
        if (r1 != 0) goto L_0x0046;
    L_0x006f:
        r2.close();	 Catch:{ Throwable -> 0x0073 }
        goto L_0x0046;
    L_0x0073:
        r1 = move-exception;
    L_0x0074:
        r1.printStackTrace();
        goto L_0x0046;
    L_0x0078:
        r0 = "UTF-8";
        r0 = r12.getBytes(r0);	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r4 = r2.m1137b(r10);	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r5 = 0;
        r3 = r4.m1144a(r5);	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r3.write(r0);	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r4.m1145a();	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r2.m1138b();	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00c4 }
        r0 = 1;
        if (r3 == 0) goto L_0x0096;
    L_0x0093:
        r3.close();	 Catch:{ Throwable -> 0x013a }
    L_0x0096:
        if (r1 == 0) goto L_0x009b;
    L_0x0098:
        r1.close();	 Catch:{ Throwable -> 0x0140 }
    L_0x009b:
        if (r2 == 0) goto L_0x0046;
    L_0x009d:
        r1 = r2.m1136a();
        if (r1 != 0) goto L_0x0046;
    L_0x00a3:
        r2.close();	 Catch:{ Throwable -> 0x00a7 }
        goto L_0x0046;
    L_0x00a7:
        r1 = move-exception;
        goto L_0x0074;
    L_0x00a9:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00e3 }
        if (r3 == 0) goto L_0x00b2;
    L_0x00af:
        r3.close();	 Catch:{ Throwable -> 0x0109 }
    L_0x00b2:
        if (r1 == 0) goto L_0x00b7;
    L_0x00b4:
        r1.close();	 Catch:{ Throwable -> 0x010e }
    L_0x00b7:
        if (r2 == 0) goto L_0x00c2;
    L_0x00b9:
        r0 = r2.m1136a();
        if (r0 != 0) goto L_0x00c2;
    L_0x00bf:
        r2.close();	 Catch:{ Throwable -> 0x0113 }
    L_0x00c2:
        r0 = 0;
        goto L_0x0046;
    L_0x00c4:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00e3 }
        if (r3 == 0) goto L_0x00cd;
    L_0x00ca:
        r3.close();	 Catch:{ Throwable -> 0x0115 }
    L_0x00cd:
        if (r1 == 0) goto L_0x00d2;
    L_0x00cf:
        r1.close();	 Catch:{ Throwable -> 0x011a }
    L_0x00d2:
        if (r2 == 0) goto L_0x00c2;
    L_0x00d4:
        r0 = r2.m1136a();
        if (r0 != 0) goto L_0x00c2;
    L_0x00da:
        r2.close();	 Catch:{ Throwable -> 0x00de }
        goto L_0x00c2;
    L_0x00de:
        r0 = move-exception;
    L_0x00df:
        r0.printStackTrace();
        goto L_0x00c2;
    L_0x00e3:
        r0 = move-exception;
        if (r3 == 0) goto L_0x00e9;
    L_0x00e6:
        r3.close();	 Catch:{ Throwable -> 0x00fa }
    L_0x00e9:
        if (r1 == 0) goto L_0x00ee;
    L_0x00eb:
        r1.close();	 Catch:{ Throwable -> 0x00ff }
    L_0x00ee:
        if (r2 == 0) goto L_0x00f9;
    L_0x00f0:
        r1 = r2.m1136a();
        if (r1 != 0) goto L_0x00f9;
    L_0x00f6:
        r2.close();	 Catch:{ Throwable -> 0x0104 }
    L_0x00f9:
        throw r0;
    L_0x00fa:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x00e9;
    L_0x00ff:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00ee;
    L_0x0104:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00f9;
    L_0x0109:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00b2;
    L_0x010e:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00b7;
    L_0x0113:
        r0 = move-exception;
        goto L_0x00df;
    L_0x0115:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00cd;
    L_0x011a:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00d2;
    L_0x011f:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0036;
    L_0x0125:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x003b;
    L_0x012b:
        r1 = move-exception;
        goto L_0x0074;
    L_0x012e:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0062;
    L_0x0134:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0067;
    L_0x013a:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0096;
    L_0x0140:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x009b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.r.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, com.a.z):boolean");
    }

    private az m1994b(Context context, String str) {
        az azVar = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(context.getFilesDir().getAbsolutePath());
            stringBuilder.append(C0255n.f1297a);
            stringBuilder.append(str);
            File file = new File(stringBuilder.toString());
            if (file.exists() || file.mkdirs()) {
                azVar = az.m1113a(file, 1, 1, 20480);
            }
        } catch (Throwable e) {
            C0247g.m1917a(e, "LogProcessor", "initDiskLru");
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "LogProcessor", "initDiskLru");
        }
        return azVar;
    }

    private boolean m1995b(String str) {
        boolean z = false;
        if (this.f1289d != null) {
            try {
                z = this.f1289d.m1140c(str);
            } catch (Throwable e) {
                C0247g.m1917a(e, "LogUpdateProcessor", "deleteLogData");
            } catch (Throwable e2) {
                C0247g.m1917a(e2, "LogUpdateProcessor", "deleteLogData");
            }
        }
        return z;
    }

    private int m1996c(String str) {
        byte[] b;
        int i = 0;
        try {
            b = gf.m1964b(str.getBytes(C1142e.f5201a));
        } catch (UnsupportedEncodingException e) {
            b = gf.m1964b(str.getBytes());
        }
        try {
            byte[] b2 = bk.m1169a().m1173b(new C0260t(b));
            if (b2 == null) {
                return 0;
            }
            String str2;
            int i2;
            try {
                str2 = new String(b2, C1142e.f5201a);
            } catch (UnsupportedEncodingException e2) {
                str2 = new String(b2);
            }
            try {
                JSONObject jSONObject = new JSONObject(str2);
                str2 = XiaomiOAuthConstants.EXTRA_CODE_2;
                if (jSONObject.has(str2)) {
                    i2 = jSONObject.getInt(str2);
                    return i2;
                }
                i2 = i;
            } catch (Throwable e3) {
                C0247g.m1917a(e3, "LogProcessor", "processUpdate");
                i2 = 1;
            }
            return i2;
        } catch (Throwable e32) {
            if (e32.m1833a() != 27) {
                i = 1;
            }
            C0247g.m1917a(e32, "LogProcessor", "processUpdate");
        }
    }

    private String m1997c(Throwable th) {
        return th.toString();
    }

    private String m1998d() {
        return gf.m1955a(new Date().getTime());
    }

    private String m1999d(String str) {
        Throwable e;
        String str2;
        String str3;
        InputStream a;
        Throwable th;
        Object obj;
        String str4 = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            if (this.f1289d == null) {
                if (str4 != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e2) {
                        C0247g.m1917a(e2, "LogProcessor", "readLog1");
                    }
                }
                if (str4 != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        e = e3;
                        str2 = "LogProcessor";
                        str3 = "readLog2";
                        C0247g.m1917a(e, str2, str3);
                        return str4;
                    }
                }
                return str4;
            }
            bc a2 = this.f1289d.m1134a(str);
            if (a2 == null) {
                if (str4 != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e22) {
                        C0247g.m1917a(e22, "LogProcessor", "readLog1");
                    }
                }
                if (str4 != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e = e4;
                        str2 = "LogProcessor";
                        str3 = "readLog2";
                        C0247g.m1917a(e, str2, str3);
                        return str4;
                    }
                }
                return str4;
            }
            a = a2.m1147a(0);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
                    while (true) {
                        int read = a.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    str4 = byteArrayOutputStream.toString("utf-8");
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable e5) {
                            C0247g.m1917a(e5, "LogProcessor", "readLog1");
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (IOException e6) {
                            e5 = e6;
                            str2 = "LogProcessor";
                            str3 = "readLog2";
                            C0247g.m1917a(e5, str2, str3);
                            return str4;
                        }
                    }
                } catch (IOException e7) {
                    e5 = e7;
                    try {
                        C0247g.m1917a(e5, "LogProcessor", "readLog");
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Throwable e52) {
                                C0247g.m1917a(e52, "LogProcessor", "readLog1");
                            }
                        }
                        if (a != null) {
                            try {
                                a.close();
                            } catch (IOException e8) {
                                e52 = e8;
                                str2 = "LogProcessor";
                                str3 = "readLog2";
                                C0247g.m1917a(e52, str2, str3);
                                return str4;
                            }
                        }
                        return str4;
                    } catch (Throwable th2) {
                        th = th2;
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Throwable e522) {
                                C0247g.m1917a(e522, "LogProcessor", "readLog1");
                            }
                        }
                        if (a != null) {
                            try {
                                a.close();
                            } catch (Throwable e5222) {
                                C0247g.m1917a(e5222, "LogProcessor", "readLog2");
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    e5222 = th3;
                    C0247g.m1917a(e5222, "LogProcessor", "readLog");
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable e52222) {
                            C0247g.m1917a(e52222, "LogProcessor", "readLog1");
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (IOException e9) {
                            e52222 = e9;
                            str2 = "LogProcessor";
                            str3 = "readLog2";
                            C0247g.m1917a(e52222, str2, str3);
                            return str4;
                        }
                    }
                    return str4;
                }
            } catch (IOException e10) {
                e52222 = e10;
                obj = str4;
                C0247g.m1917a(e52222, "LogProcessor", "readLog");
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (a != null) {
                    a.close();
                }
                return str4;
            } catch (Throwable e522222) {
                obj = str4;
                th = e522222;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (a != null) {
                    a.close();
                }
                throw th;
            }
            return str4;
        } catch (IOException e11) {
            e522222 = e11;
            obj = str4;
            Object obj2 = str4;
            C0247g.m1917a(e522222, "LogProcessor", "readLog");
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (a != null) {
                a.close();
            }
            return str4;
        } catch (Throwable e5222222) {
            byteArrayOutputStream = str4;
            a = str4;
            th = e5222222;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    private void m2000d(Context context) {
        try {
            this.f1289d = m1994b(context, m2005a());
        } catch (Throwable th) {
            C0247g.m1917a(th, "LogProcessor", "LogUpDateProcessor");
        }
    }

    private List<gd> m2001e(Context context) {
        List<gd> a;
        Throwable th;
        Throwable th2;
        Throwable th3;
        List<gd> list = null;
        try {
            synchronized (Looper.getMainLooper()) {
                try {
                    a = new ac(context, false).m984a();
                    try {
                        return a;
                    } catch (Throwable th22) {
                        th = th22;
                        list = a;
                        th3 = th;
                        try {
                            throw th3;
                        } catch (Throwable th32) {
                            th = th32;
                            a = list;
                            th22 = th;
                        }
                    }
                } catch (Throwable th4) {
                    th32 = th4;
                    throw th32;
                }
            }
        } catch (Throwable th322) {
            th = th322;
            a = null;
            th22 = th;
            th22.printStackTrace();
            return a;
        }
    }

    private String m2002f(Context context) {
        return fn.m1834a(context);
    }

    private String m2003g(Context context) {
        String str = null;
        try {
            String a = fp.m1851a(context);
            if (!C2915a.f14760f.equals(a)) {
                str = fp.m1859c(context, a.getBytes(C1142e.f5201a));
            }
        } catch (Throwable e) {
            C0247g.m1917a(e, "LogProcessor", "getPublicInfo");
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "LogProcessor", "getPublicInfo");
        }
        return str;
    }

    protected bg m2004a(C0264z c0264z) {
        try {
            if (this.f1288c == null) {
                this.f1288c = new C0259s(this, c0264z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.f1288c;
    }

    protected String m2005a() {
        return C0255n.m2033b(this.f1287b);
    }

    protected String m2006a(String str) {
        return fz.m1913c(str);
    }

    protected String m2007a(Throwable th) {
        String str = null;
        try {
            str = m2015b(th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        return str;
    }

    protected abstract String m2008a(List<gd> list);

    void m2009a(Context context, Throwable th, String str, String str2) {
        List<gd> e = m2001e(context);
        if (e != null && e.size() != 0) {
            String a = m2007a(th);
            if (a != null && !C2915a.f14760f.equals(a)) {
                for (gd gdVar : e) {
                    if (m2013a(gdVar.m1944f(), a)) {
                        m2011a(gdVar, context, th, a, str, str2);
                        return;
                    }
                }
                if (a.contains("com.amap.api.col")) {
                    try {
                        m2011a(new ge("collection", BuildConfig.VERSION_NAME, "AMap_collection_1.0").m1954a(new String[]{"com.amap.api.collection"}).m1951a(), context, th, a, str, str2);
                    } catch (fm e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    protected void m2010a(gd gdVar) {
        this.f1286a = gdVar;
    }

    void m2011a(gd gdVar, Context context, Throwable th, String str, String str2, String str3) {
        m2010a(gdVar);
        String d = m1998d();
        String a = m1986a(context, gdVar);
        String f = m2002f(context);
        String c = m1997c(th);
        if (c != null && !C2915a.f14760f.equals(c)) {
            int b = m2014b();
            StringBuilder stringBuilder = new StringBuilder();
            if (str2 != null) {
                stringBuilder.append("class:").append(str2);
            }
            if (str3 != null) {
                stringBuilder.append(" method:").append(str3).append(RContact.FAVOUR_CONTACT_SHOW_HEAD_CHAR).append("<br/>");
            }
            stringBuilder.append(str);
            String a2 = m2006a(str);
            String a3 = m1988a(f, a, d, b, c, stringBuilder.toString());
            if (a3 != null && !C2915a.f14760f.equals(a3)) {
                String a4 = m1987a(context, a3);
                String a5 = m2005a();
                synchronized (Looper.getMainLooper()) {
                    C0264z c0264z = new C0264z(context);
                    m1991a(c0264z, gdVar.m1938a(), a2, b, m1993a(context, a2, a5, a4, c0264z));
                }
            }
        }
    }

    protected abstract boolean m2012a(Context context);

    protected boolean m2013a(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        try {
            for (String indexOf : strArr) {
                if (str.indexOf(indexOf) != -1) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    protected int m2014b() {
        return this.f1287b;
    }

    String m2015b(Throwable th) {
        String a = gf.m1957a(th);
        return a != null ? a.replaceAll("\n", "<br/>") : null;
    }

    void m2016b(Context context) {
        List e = m2001e(context);
        if (e != null && e.size() != 0) {
            String a = m2008a(e);
            if (a != null && !C2915a.f14760f.equals(a)) {
                String d = m1998d();
                String a2 = m1986a(context, this.f1286a);
                int b = m2014b();
                String a3 = m1988a(m2002f(context), a2, d, b, "ANR", a);
                if (a3 != null && !C2915a.f14760f.equals(a3)) {
                    String a4 = m2006a(a);
                    String a5 = m1987a(context, a3);
                    String a6 = m2005a();
                    synchronized (Looper.getMainLooper()) {
                        C0264z c0264z = new C0264z(context);
                        m1991a(c0264z, this.f1286a.m1938a(), a4, b, m1993a(context, a4, a6, a5, c0264z));
                    }
                }
            }
        }
    }

    void m2017c() {
        try {
            if (this.f1289d != null && !this.f1289d.m1136a()) {
                this.f1289d.close();
            }
        } catch (Throwable e) {
            C0247g.m1917a(e, "LogProcessor", "closeDiskLru");
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "LogProcessor", "closeDiskLru");
        }
    }

    void m2018c(Context context) {
        try {
            m2000d(context);
            if (m2012a(context)) {
                synchronized (Looper.getMainLooper()) {
                    C0264z c0264z = new C0264z(context);
                    m1990a(c0264z, m2014b());
                    List a = c0264z.m2061a(0, m2014b());
                    if (a == null || a.size() == 0) {
                        return;
                    }
                    String a2 = m1989a(a, context);
                    if (a2 == null) {
                        return;
                    }
                    if (m1996c(a2) == 1) {
                        m1992a(a, c0264z, m2014b());
                    }
                }
            }
        } catch (Throwable th) {
            C0247g.m1917a(th, "LogProcessor", "processUpdateLog");
        }
    }
}
