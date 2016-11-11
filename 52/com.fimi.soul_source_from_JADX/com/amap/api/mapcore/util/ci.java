package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Looper;
import com.amap.api.mapcore.util.bv.C0363a;
import com.amap.api.mapcore.util.cx.C0372b;
import com.tencent.mm.sdk.contact.RContact;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.openauth.BuildConfig;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

abstract class ci {
    private bv f2329a;
    private int f2330b;
    private da f2331c;
    private cx f2332d;

    /* renamed from: com.amap.api.mapcore.util.ci.a */
    class C0369a implements da {
        final /* synthetic */ ci f2340a;
        private cs f2341b;

        C0369a(ci ciVar, cs csVar) {
            this.f2340a = ciVar;
            this.f2341b = csVar;
        }

        public void m3877a(String str) {
            try {
                this.f2341b.m3925b(str, cc.m3813a(this.f2340a.m3862b()));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    protected ci(int i) {
        this.f2330b = i;
    }

    private String m3836a(Context context, String str) {
        String str2 = null;
        try {
            str2 = bn.m3668a(context, bx.m3778a(str));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return str2;
    }

    private String m3837a(String str, String str2, String str3, int i, String str4, String str5) {
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

    private String m3838a(List<? extends ct> list, Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"pinfo\":\"").append(m3851f(context)).append("\",\"els\":[");
        Object obj = 1;
        for (ct ctVar : list) {
            Object obj2;
            String d = m3848d(ctVar.m3912b());
            if (d != null) {
                if (C2915a.f14760f.equals(d)) {
                    obj2 = obj;
                    obj = obj2;
                } else {
                    String str = d + "||" + ctVar.m3915c();
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

    private void m3839a(cs csVar, int i) {
        try {
            m3841a(csVar.m3921a(2, cc.m3813a(i)), csVar, i);
        } catch (Throwable th) {
            cb.m3809a(th, "LogProcessor", "processDeleteFail");
        }
    }

    private void m3840a(cs csVar, String str, String str2, int i, boolean z) {
        ct b = cc.m3817b(i);
        b.m3910a(0);
        b.m3914b(str);
        b.m3911a(str2);
        csVar.m3922a(b);
    }

    private void m3841a(List<? extends ct> list, cs csVar, int i) {
        if (list != null && list.size() > 0) {
            for (ct ctVar : list) {
                if (m3844b(ctVar.m3912b())) {
                    csVar.m3923a(ctVar.m3912b(), ctVar.getClass());
                } else {
                    ctVar.m3910a(2);
                    csVar.m3924b(ctVar);
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m3842a(android.content.Context r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, com.amap.api.mapcore.util.cs r13) {
        /*
        r8 = this;
        r3 = 0;
        r2 = 0;
        r1 = 0;
        r0 = com.amap.api.mapcore.util.cc.m3814a(r9, r11);	 Catch:{ IOException -> 0x008f, Throwable -> 0x00aa }
        r4 = new java.io.File;	 Catch:{ IOException -> 0x008f, Throwable -> 0x00aa }
        r4.<init>(r0);	 Catch:{ IOException -> 0x008f, Throwable -> 0x00aa }
        r0 = r4.exists();	 Catch:{ IOException -> 0x008f, Throwable -> 0x00aa }
        if (r0 != 0) goto L_0x002f;
    L_0x0012:
        r0 = r4.mkdirs();	 Catch:{ IOException -> 0x008f, Throwable -> 0x00aa }
        if (r0 != 0) goto L_0x002f;
    L_0x0018:
        r0 = 0;
        if (r3 == 0) goto L_0x001e;
    L_0x001b:
        r3.close();	 Catch:{ Throwable -> 0x0105 }
    L_0x001e:
        if (r1 == 0) goto L_0x0023;
    L_0x0020:
        r1.close();	 Catch:{ Throwable -> 0x010b }
    L_0x0023:
        if (r2 == 0) goto L_0x002e;
    L_0x0025:
        r1 = r2.m3983a();
        if (r1 != 0) goto L_0x002e;
    L_0x002b:
        r2.close();	 Catch:{ Throwable -> 0x0111 }
    L_0x002e:
        return r0;
    L_0x002f:
        r0 = 1;
        r5 = 1;
        r6 = 20480; // 0x5000 float:2.8699E-41 double:1.01185E-319;
        r2 = com.amap.api.mapcore.util.cx.m3961a(r4, r0, r5, r6);	 Catch:{ IOException -> 0x008f, Throwable -> 0x00aa }
        r0 = r8.m3852a(r13);	 Catch:{ IOException -> 0x008f, Throwable -> 0x00aa }
        r2.m3982a(r0);	 Catch:{ IOException -> 0x008f, Throwable -> 0x00aa }
        r1 = r2.m3981a(r10);	 Catch:{ IOException -> 0x008f, Throwable -> 0x00aa }
        if (r1 == 0) goto L_0x0060;
    L_0x0044:
        r0 = 0;
        if (r3 == 0) goto L_0x004a;
    L_0x0047:
        r3.close();	 Catch:{ Throwable -> 0x0114 }
    L_0x004a:
        if (r1 == 0) goto L_0x004f;
    L_0x004c:
        r1.close();	 Catch:{ Throwable -> 0x011a }
    L_0x004f:
        if (r2 == 0) goto L_0x002e;
    L_0x0051:
        r1 = r2.m3983a();
        if (r1 != 0) goto L_0x002e;
    L_0x0057:
        r2.close();	 Catch:{ Throwable -> 0x005b }
        goto L_0x002e;
    L_0x005b:
        r1 = move-exception;
    L_0x005c:
        r1.printStackTrace();
        goto L_0x002e;
    L_0x0060:
        r0 = com.amap.api.mapcore.util.bx.m3778a(r12);	 Catch:{ IOException -> 0x008f, Throwable -> 0x00aa }
        r4 = r2.m3984b(r10);	 Catch:{ IOException -> 0x008f, Throwable -> 0x00aa }
        r5 = 0;
        r3 = r4.m3941a(r5);	 Catch:{ IOException -> 0x008f, Throwable -> 0x00aa }
        r3.write(r0);	 Catch:{ IOException -> 0x008f, Throwable -> 0x00aa }
        r4.m3942a();	 Catch:{ IOException -> 0x008f, Throwable -> 0x00aa }
        r2.m3985b();	 Catch:{ IOException -> 0x008f, Throwable -> 0x00aa }
        r0 = 1;
        if (r3 == 0) goto L_0x007c;
    L_0x0079:
        r3.close();	 Catch:{ Throwable -> 0x0120 }
    L_0x007c:
        if (r1 == 0) goto L_0x0081;
    L_0x007e:
        r1.close();	 Catch:{ Throwable -> 0x0126 }
    L_0x0081:
        if (r2 == 0) goto L_0x002e;
    L_0x0083:
        r1 = r2.m3983a();
        if (r1 != 0) goto L_0x002e;
    L_0x0089:
        r2.close();	 Catch:{ Throwable -> 0x008d }
        goto L_0x002e;
    L_0x008d:
        r1 = move-exception;
        goto L_0x005c;
    L_0x008f:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00c9 }
        if (r3 == 0) goto L_0x0098;
    L_0x0095:
        r3.close();	 Catch:{ Throwable -> 0x00ef }
    L_0x0098:
        if (r1 == 0) goto L_0x009d;
    L_0x009a:
        r1.close();	 Catch:{ Throwable -> 0x00f4 }
    L_0x009d:
        if (r2 == 0) goto L_0x00a8;
    L_0x009f:
        r0 = r2.m3983a();
        if (r0 != 0) goto L_0x00a8;
    L_0x00a5:
        r2.close();	 Catch:{ Throwable -> 0x00f9 }
    L_0x00a8:
        r0 = 0;
        goto L_0x002e;
    L_0x00aa:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00c9 }
        if (r3 == 0) goto L_0x00b3;
    L_0x00b0:
        r3.close();	 Catch:{ Throwable -> 0x00fb }
    L_0x00b3:
        if (r1 == 0) goto L_0x00b8;
    L_0x00b5:
        r1.close();	 Catch:{ Throwable -> 0x0100 }
    L_0x00b8:
        if (r2 == 0) goto L_0x00a8;
    L_0x00ba:
        r0 = r2.m3983a();
        if (r0 != 0) goto L_0x00a8;
    L_0x00c0:
        r2.close();	 Catch:{ Throwable -> 0x00c4 }
        goto L_0x00a8;
    L_0x00c4:
        r0 = move-exception;
    L_0x00c5:
        r0.printStackTrace();
        goto L_0x00a8;
    L_0x00c9:
        r0 = move-exception;
        if (r3 == 0) goto L_0x00cf;
    L_0x00cc:
        r3.close();	 Catch:{ Throwable -> 0x00e0 }
    L_0x00cf:
        if (r1 == 0) goto L_0x00d4;
    L_0x00d1:
        r1.close();	 Catch:{ Throwable -> 0x00e5 }
    L_0x00d4:
        if (r2 == 0) goto L_0x00df;
    L_0x00d6:
        r1 = r2.m3983a();
        if (r1 != 0) goto L_0x00df;
    L_0x00dc:
        r2.close();	 Catch:{ Throwable -> 0x00ea }
    L_0x00df:
        throw r0;
    L_0x00e0:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x00cf;
    L_0x00e5:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00d4;
    L_0x00ea:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00df;
    L_0x00ef:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0098;
    L_0x00f4:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x009d;
    L_0x00f9:
        r0 = move-exception;
        goto L_0x00c5;
    L_0x00fb:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00b3;
    L_0x0100:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00b8;
    L_0x0105:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x001e;
    L_0x010b:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0023;
    L_0x0111:
        r1 = move-exception;
        goto L_0x005c;
    L_0x0114:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x004a;
    L_0x011a:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004f;
    L_0x0120:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x007c;
    L_0x0126:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0081;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ci.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, com.amap.api.mapcore.util.cs):boolean");
    }

    private cx m3843b(Context context, String str) {
        cx cxVar = null;
        try {
            File file = new File(cc.m3814a(context, str));
            if (file.exists() || file.mkdirs()) {
                cxVar = cx.m3961a(file, 1, 1, 20480);
            }
        } catch (Throwable e) {
            cb.m3809a(e, "LogProcessor", "initDiskLru");
        } catch (Throwable e2) {
            cb.m3809a(e2, "LogProcessor", "initDiskLru");
        }
        return cxVar;
    }

    private boolean m3844b(String str) {
        boolean z = false;
        if (this.f2332d != null) {
            try {
                z = this.f2332d.m3987c(str);
            } catch (Throwable e) {
                cb.m3809a(e, "LogUpdateProcessor", "deleteLogData");
            } catch (Throwable e2) {
                cb.m3809a(e2, "LogUpdateProcessor", "deleteLogData");
            }
        }
        return z;
    }

    private int m3845c(String str) {
        int i = 0;
        try {
            byte[] b = dd.m4003a().m4006b(new cd(bx.m3783c(bx.m3778a(str))));
            if (b == null) {
                return 0;
            }
            try {
                JSONObject jSONObject = new JSONObject(bx.m3772a(b));
                String str2 = XiaomiOAuthConstants.EXTRA_CODE_2;
                return jSONObject.has(str2) ? jSONObject.getInt(str2) : 0;
            } catch (Throwable e) {
                cb.m3809a(e, "LogProcessor", "processUpdate");
                return 1;
            }
        } catch (Throwable e2) {
            if (e2.m3645b() != 27) {
                i = 1;
            }
            cb.m3809a(e2, "LogProcessor", "processUpdate");
            return i;
        }
    }

    private String m3846c(Throwable th) {
        return th.toString();
    }

    private String m3847d() {
        return bx.m3769a(new Date().getTime());
    }

    private String m3848d(String str) {
        Throwable e;
        String str2;
        String str3;
        Throwable th;
        Object obj;
        String str4 = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        InputStream a;
        try {
            if (this.f2332d == null) {
                if (str4 != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e2) {
                        cb.m3809a(e2, "LogProcessor", "readLog1");
                    }
                }
                if (str4 != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        e = e3;
                        str2 = "LogProcessor";
                        str3 = "readLog2";
                        cb.m3809a(e, str2, str3);
                        return str4;
                    }
                }
                return str4;
            }
            C0372b a2 = this.f2332d.m3981a(str);
            if (a2 == null) {
                if (str4 != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e22) {
                        cb.m3809a(e22, "LogProcessor", "readLog1");
                    }
                }
                if (str4 != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e = e4;
                        str2 = "LogProcessor";
                        str3 = "readLog2";
                        cb.m3809a(e, str2, str3);
                        return str4;
                    }
                }
                return str4;
            }
            a = a2.m3944a(0);
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
                    str4 = bx.m3772a(byteArrayOutputStream.toByteArray());
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable e5) {
                            cb.m3809a(e5, "LogProcessor", "readLog1");
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (IOException e6) {
                            e5 = e6;
                            str2 = "LogProcessor";
                            str3 = "readLog2";
                            cb.m3809a(e5, str2, str3);
                            return str4;
                        }
                    }
                } catch (IOException e7) {
                    e5 = e7;
                    try {
                        cb.m3809a(e5, "LogProcessor", "readLog");
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Throwable e52) {
                                cb.m3809a(e52, "LogProcessor", "readLog1");
                            }
                        }
                        if (a != null) {
                            try {
                                a.close();
                            } catch (IOException e8) {
                                e52 = e8;
                                str2 = "LogProcessor";
                                str3 = "readLog2";
                                cb.m3809a(e52, str2, str3);
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
                                cb.m3809a(e522, "LogProcessor", "readLog1");
                            }
                        }
                        if (a != null) {
                            try {
                                a.close();
                            } catch (Throwable e5222) {
                                cb.m3809a(e5222, "LogProcessor", "readLog2");
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    e5222 = th3;
                    cb.m3809a(e5222, "LogProcessor", "readLog");
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable e52222) {
                            cb.m3809a(e52222, "LogProcessor", "readLog1");
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (IOException e9) {
                            e52222 = e9;
                            str2 = "LogProcessor";
                            str3 = "readLog2";
                            cb.m3809a(e52222, str2, str3);
                            return str4;
                        }
                    }
                    return str4;
                }
            } catch (IOException e10) {
                e52222 = e10;
                obj = str4;
                cb.m3809a(e52222, "LogProcessor", "readLog");
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
            cb.m3809a(e522222, "LogProcessor", "readLog");
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

    private void m3849d(Context context) {
        try {
            this.f2332d = m3843b(context, m3853a());
        } catch (Throwable th) {
            cb.m3809a(th, "LogProcessor", "LogUpDateProcessor");
        }
    }

    private List<bv> m3850e(Context context) {
        List<bv> a;
        Throwable th;
        Throwable th2;
        Throwable th3;
        List<bv> list = null;
        try {
            synchronized (Looper.getMainLooper()) {
                try {
                    a = new cu(context, false).m3927a();
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

    private String m3851f(Context context) {
        String str = null;
        try {
            String a = bn.m3662a(context);
            if (!C2915a.f14760f.equals(a)) {
                str = bn.m3672d(context, bx.m3778a(a));
            }
        } catch (Throwable th) {
            cb.m3809a(th, "LogProcessor", "getPublicInfo");
        }
        return str;
    }

    protected da m3852a(cs csVar) {
        try {
            if (this.f2331c == null) {
                this.f2331c = new C0369a(this, csVar);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.f2331c;
    }

    protected String m3853a() {
        return cc.m3819c(this.f2330b);
    }

    protected String m3854a(String str) {
        return bs.m3730c(str);
    }

    protected String m3855a(Throwable th) {
        String str = null;
        try {
            str = m3863b(th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        return str;
    }

    protected abstract String m3856a(List<bv> list);

    void m3857a(Context context, Throwable th, String str, String str2) {
        List<bv> e = m3850e(context);
        if (e != null && e.size() != 0) {
            String a = m3855a(th);
            if (a != null && !C2915a.f14760f.equals(a)) {
                for (bv bvVar : e) {
                    if (m3861a(bvVar.m3768e(), a)) {
                        m3859a(bvVar, context, th, a, str, str2);
                        return;
                    }
                }
                if (a.contains("com.amap.api.col")) {
                    try {
                        m3859a(new C0363a("collection", BuildConfig.VERSION_NAME, "AMap_collection_1.0").m3757a(new String[]{"com.amap.api.collection"}).m3758a(), context, th, a, str, str2);
                    } catch (bk e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    protected void m3858a(bv bvVar) {
        this.f2329a = bvVar;
    }

    void m3859a(bv bvVar, Context context, Throwable th, String str, String str2, String str3) {
        m3858a(bvVar);
        String d = m3847d();
        String a = bn.m3664a(context, bvVar);
        String a2 = bl.m3646a(context);
        String c = m3846c(th);
        if (c != null && !C2915a.f14760f.equals(c)) {
            int b = m3862b();
            StringBuilder stringBuilder = new StringBuilder();
            if (str2 != null) {
                stringBuilder.append("class:").append(str2);
            }
            if (str3 != null) {
                stringBuilder.append(" method:").append(str3).append(RContact.FAVOUR_CONTACT_SHOW_HEAD_CHAR).append("<br/>");
            }
            stringBuilder.append(str);
            String a3 = m3854a(str);
            String a4 = m3837a(a2, a, d, b, c, stringBuilder.toString());
            if (a4 != null && !C2915a.f14760f.equals(a4)) {
                String a5 = m3836a(context, a4);
                String a6 = m3853a();
                synchronized (Looper.getMainLooper()) {
                    cs csVar = new cs(context);
                    m3840a(csVar, bvVar.m3763a(), a3, b, m3842a(context, a3, a6, a5, csVar));
                }
            }
        }
    }

    protected abstract boolean m3860a(Context context);

    protected boolean m3861a(String[] strArr, String str) {
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

    protected int m3862b() {
        return this.f2330b;
    }

    String m3863b(Throwable th) {
        String a = bx.m3770a(th);
        return a != null ? a.replaceAll("\n", "<br/>") : null;
    }

    void m3864b(Context context) {
        List e = m3850e(context);
        if (e != null && e.size() != 0) {
            String a = m3856a(e);
            if (a != null && !C2915a.f14760f.equals(a)) {
                String d = m3847d();
                String a2 = bn.m3664a(context, this.f2329a);
                int b = m3862b();
                String a3 = m3837a(bl.m3646a(context), a2, d, b, "ANR", a);
                if (a3 != null && !C2915a.f14760f.equals(a3)) {
                    String a4 = m3854a(a);
                    String a5 = m3836a(context, a3);
                    String a6 = m3853a();
                    synchronized (Looper.getMainLooper()) {
                        cs csVar = new cs(context);
                        m3840a(csVar, this.f2329a.m3763a(), a4, b, m3842a(context, a4, a6, a5, csVar));
                    }
                }
            }
        }
    }

    void m3865c() {
        try {
            if (this.f2332d != null && !this.f2332d.m3983a()) {
                this.f2332d.close();
            }
        } catch (Throwable e) {
            cb.m3809a(e, "LogProcessor", "closeDiskLru");
        } catch (Throwable e2) {
            cb.m3809a(e2, "LogProcessor", "closeDiskLru");
        }
    }

    void m3866c(Context context) {
        try {
            m3849d(context);
            if (m3860a(context)) {
                synchronized (Looper.getMainLooper()) {
                    cs csVar = new cs(context);
                    m3839a(csVar, m3862b());
                    List a = csVar.m3921a(0, cc.m3813a(m3862b()));
                    if (a == null || a.size() == 0) {
                        return;
                    }
                    String a2 = m3838a(a, context);
                    if (a2 == null) {
                        return;
                    }
                    if (m3845c(a2) == 1) {
                        m3841a(a, csVar, m3862b());
                    }
                }
            }
        } catch (Throwable th) {
            cb.m3809a(th, "LogProcessor", "processUpdateLog");
        }
    }
}
