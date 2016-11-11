package com.amap.api.services.core;

import android.content.Context;
import android.os.Looper;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.mm.sdk.contact.RContact;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

abstract class bi {
    private ad f3054a;

    bi() {
    }

    static bi m4619a(int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return new bc();
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return new be();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return new ba();
            default:
                return null;
        }
    }

    private String m4620a(Context context, ad adVar) {
        return C0498y.m4879a(context, adVar);
    }

    private String m4621a(Context context, String str) {
        String str2 = null;
        try {
            str2 = C0498y.m4882a(context, str.getBytes(C1142e.f5201a));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return str2;
    }

    private String m4622a(String str, String str2, String str3, int i, String str4, String str5) {
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

    private void m4623a(ak akVar, String str, String str2, int i, boolean z) {
        am amVar = new am();
        amVar.m4538a(0);
        amVar.m4542b(str);
        amVar.m4539a(str2);
        akVar.m4535b(amVar, i);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m4624a(android.content.Context r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, com.amap.api.services.core.ak r13) {
        /*
        r8 = this;
        r2 = 0;
        r1 = 0;
        r0 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r0.<init>();	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r3 = r9.getFilesDir();	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r3 = r3.getAbsolutePath();	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r0.append(r3);	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r3 = com.amap.api.services.core.bf.f3074a;	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r0.append(r3);	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r0.append(r11);	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r0 = r0.toString();	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r3 = new java.io.File;	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r3.<init>(r0);	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r0 = r3.exists();	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        if (r0 != 0) goto L_0x0041;
    L_0x0029:
        r0 = r3.mkdirs();	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        if (r0 != 0) goto L_0x0041;
    L_0x002f:
        r0 = 0;
        if (r2 == 0) goto L_0x0035;
    L_0x0032:
        r2.close();	 Catch:{ Throwable -> 0x00f1 }
    L_0x0035:
        if (r1 == 0) goto L_0x0040;
    L_0x0037:
        r2 = r1.m4717a();
        if (r2 != 0) goto L_0x0040;
    L_0x003d:
        r1.close();	 Catch:{ Throwable -> 0x00f7 }
    L_0x0040:
        return r0;
    L_0x0041:
        r0 = 1;
        r4 = 1;
        r6 = 20480; // 0x5000 float:2.8699E-41 double:1.01185E-319;
        r1 = com.amap.api.services.core.bk.m4695a(r3, r0, r4, r6);	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r0 = r8.m4630a(r13);	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r1.m4716a(r0);	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r0 = r1.m4715a(r10);	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        if (r0 == 0) goto L_0x006d;
    L_0x0056:
        r0 = 0;
        if (r2 == 0) goto L_0x005c;
    L_0x0059:
        r2.close();	 Catch:{ Throwable -> 0x00fa }
    L_0x005c:
        if (r1 == 0) goto L_0x0040;
    L_0x005e:
        r2 = r1.m4717a();
        if (r2 != 0) goto L_0x0040;
    L_0x0064:
        r1.close();	 Catch:{ Throwable -> 0x0068 }
        goto L_0x0040;
    L_0x0068:
        r1 = move-exception;
    L_0x0069:
        r1.printStackTrace();
        goto L_0x0040;
    L_0x006d:
        r0 = "UTF-8";
        r0 = r12.getBytes(r0);	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r3 = r1.m4718b(r10);	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r4 = 0;
        r2 = r3.m4675a(r4);	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r2.write(r0);	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r3.m4676a();	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r1.m4719b();	 Catch:{ IOException -> 0x0099, Throwable -> 0x00af }
        r0 = 1;
        if (r2 == 0) goto L_0x008b;
    L_0x0088:
        r2.close();	 Catch:{ Throwable -> 0x0100 }
    L_0x008b:
        if (r1 == 0) goto L_0x0040;
    L_0x008d:
        r2 = r1.m4717a();
        if (r2 != 0) goto L_0x0040;
    L_0x0093:
        r1.close();	 Catch:{ Throwable -> 0x0097 }
        goto L_0x0040;
    L_0x0097:
        r1 = move-exception;
        goto L_0x0069;
    L_0x0099:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00c9 }
        if (r2 == 0) goto L_0x00a2;
    L_0x009f:
        r2.close();	 Catch:{ Throwable -> 0x00e5 }
    L_0x00a2:
        if (r1 == 0) goto L_0x00ad;
    L_0x00a4:
        r0 = r1.m4717a();
        if (r0 != 0) goto L_0x00ad;
    L_0x00aa:
        r1.close();	 Catch:{ Throwable -> 0x00ea }
    L_0x00ad:
        r0 = 0;
        goto L_0x0040;
    L_0x00af:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00c9 }
        if (r2 == 0) goto L_0x00b8;
    L_0x00b5:
        r2.close();	 Catch:{ Throwable -> 0x00ec }
    L_0x00b8:
        if (r1 == 0) goto L_0x00ad;
    L_0x00ba:
        r0 = r1.m4717a();
        if (r0 != 0) goto L_0x00ad;
    L_0x00c0:
        r1.close();	 Catch:{ Throwable -> 0x00c4 }
        goto L_0x00ad;
    L_0x00c4:
        r0 = move-exception;
    L_0x00c5:
        r0.printStackTrace();
        goto L_0x00ad;
    L_0x00c9:
        r0 = move-exception;
        if (r2 == 0) goto L_0x00cf;
    L_0x00cc:
        r2.close();	 Catch:{ Throwable -> 0x00db }
    L_0x00cf:
        if (r1 == 0) goto L_0x00da;
    L_0x00d1:
        r2 = r1.m4717a();
        if (r2 != 0) goto L_0x00da;
    L_0x00d7:
        r1.close();	 Catch:{ Throwable -> 0x00e0 }
    L_0x00da:
        throw r0;
    L_0x00db:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x00cf;
    L_0x00e0:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00da;
    L_0x00e5:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00a2;
    L_0x00ea:
        r0 = move-exception;
        goto L_0x00c5;
    L_0x00ec:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00b8;
    L_0x00f1:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0035;
    L_0x00f7:
        r1 = move-exception;
        goto L_0x0069;
    L_0x00fa:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x005c;
    L_0x0100:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x008b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.core.bi.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, com.amap.api.services.core.ak):boolean");
    }

    private String m4625b(Throwable th) {
        return th.toString();
    }

    private List<ad> m4626b(Context context) {
        List<ad> a;
        Throwable th;
        Throwable th2;
        Throwable th3;
        List<ad> list = null;
        try {
            synchronized (Looper.getMainLooper()) {
                try {
                    a = new an(context).m4546a();
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

    private String m4627c() {
        return bj.m4670a(new Date().getTime());
    }

    private String m4628c(Context context) {
        return C0496w.m4873e(context);
    }

    protected abstract int m4629a();

    protected abstract bn m4630a(ak akVar);

    protected abstract String m4631a(String str);

    protected String m4632a(Throwable th) {
        String str = null;
        try {
            str = bj.m4671a(th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        return str;
    }

    protected abstract String m4633a(List<ad> list);

    void m4634a(Context context) {
        List b = m4626b(context);
        if (b != null && b.size() != 0) {
            String a = m4633a(b);
            if (a != null && !C2915a.f14760f.equals(a)) {
                String c = m4627c();
                String a2 = m4620a(context, this.f3054a);
                int a3 = m4629a();
                String a4 = m4622a(m4628c(context), a2, c, a3, "ANR", a);
                if (a4 != null && !C2915a.f14760f.equals(a4)) {
                    String a5 = m4631a(a);
                    String a6 = m4621a(context, a4);
                    String b2 = m4638b();
                    synchronized (Looper.getMainLooper()) {
                        ak akVar = new ak(context);
                        m4623a(akVar, this.f3054a.m4494a(), a5, a3, m4624a(context, a5, b2, a6, akVar));
                    }
                }
            }
        }
    }

    void m4635a(Context context, Throwable th, String str, String str2) {
        List<ad> b = m4626b(context);
        if (b != null && b.size() != 0) {
            String a = m4632a(th);
            if (a != null && !C2915a.f14760f.equals(a)) {
                for (ad adVar : b) {
                    if (m4637a(adVar.m4499f(), a)) {
                        m4636a(adVar);
                        String c = m4627c();
                        String a2 = m4620a(context, adVar);
                        String c2 = m4628c(context);
                        String b2 = m4625b(th);
                        if (b2 != null && !C2915a.f14760f.equals(b2)) {
                            int a3 = m4629a();
                            StringBuilder stringBuilder = new StringBuilder();
                            if (str != null) {
                                stringBuilder.append("class:").append(str);
                            }
                            if (str2 != null) {
                                stringBuilder.append(" method:").append(str2).append(RContact.FAVOUR_CONTACT_SHOW_HEAD_CHAR).append("<br/>");
                            }
                            stringBuilder.append(a);
                            String a4 = m4631a(a);
                            String a5 = m4622a(c2, a2, c, a3, b2, stringBuilder.toString());
                            if (a5 != null && !C2915a.f14760f.equals(a5)) {
                                String a6 = m4621a(context, a5);
                                String b3 = m4638b();
                                synchronized (Looper.getMainLooper()) {
                                    ak akVar = new ak(context);
                                    m4623a(akVar, adVar.m4494a(), a4, a3, m4624a(context, a4, b3, a6, akVar));
                                }
                            } else {
                                return;
                            }
                        }
                        return;
                    }
                }
            }
        }
    }

    protected void m4636a(ad adVar) {
        this.f3054a = adVar;
    }

    protected boolean m4637a(String[] strArr, String str) {
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

    protected abstract String m4638b();
}
