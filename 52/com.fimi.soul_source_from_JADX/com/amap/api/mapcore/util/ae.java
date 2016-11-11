package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.io.File;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ae {
    private C0340b f2106a;

    /* renamed from: com.amap.api.mapcore.util.ae.c */
    public interface C0337c {
        void m3392a();

        void m3393a(long j);
    }

    /* renamed from: com.amap.api.mapcore.util.ae.1 */
    final class C03381 implements C0337c {
        final /* synthetic */ aa f2098a;

        C03381(aa aaVar) {
            this.f2098a = aaVar;
        }

        public void m3394a() {
            if (this.f2098a != null) {
                this.f2098a.m3361q();
            }
        }

        public void m3395a(long j) {
            try {
                if (this.f2098a != null) {
                    this.f2098a.m3358a(j);
                }
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.util.ae.a */
    public class C0339a {
        public boolean f2099a;

        public C0339a() {
            this.f2099a = false;
        }
    }

    /* renamed from: com.amap.api.mapcore.util.ae.b */
    class C0340b {
        final /* synthetic */ ae f2100a;
        private String f2101b;
        private String f2102c;
        private aa f2103d;
        private C0339a f2104e;
        private String f2105f;

        public C0340b(ae aeVar, ab abVar, aa aaVar) {
            this.f2100a = aeVar;
            this.f2103d = null;
            this.f2104e = new C0339a();
            this.f2101b = abVar.m3363A();
            this.f2102c = abVar.m3364B();
            this.f2103d = aaVar;
        }

        public String m3396a() {
            return this.f2101b;
        }

        public void m3397a(String str) {
            if (str.length() > 1) {
                this.f2105f = str;
            }
        }

        public String m3398b() {
            return this.f2102c;
        }

        public String m3399c() {
            return this.f2105f;
        }

        public aa m3400d() {
            return this.f2103d;
        }

        public C0339a m3401e() {
            return this.f2104e;
        }

        public void m3402f() {
            this.f2104e.f2099a = true;
        }
    }

    public ae(ab abVar, aa aaVar) {
        this.f2106a = new C0340b(this, abVar, aaVar);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int m3403a(java.io.File r8, java.util.zip.ZipInputStream r9, long r10, long r12, com.amap.api.mapcore.util.ae.C0337c r14, com.amap.api.mapcore.util.ae.C0339a r15) {
        /*
        r0 = 0;
        r1 = new java.io.BufferedOutputStream;
        r2 = new java.io.FileOutputStream;
        r2.<init>(r8);
        r1.<init>(r2);
        r2 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r2 = new byte[r2];
    L_0x000f:
        r3 = 0;
        r4 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r3 = r9.read(r2, r3, r4);
        r4 = -1;
        if (r3 == r4) goto L_0x0040;
    L_0x0019:
        if (r15 == 0) goto L_0x0023;
    L_0x001b:
        r4 = r15.f2099a;
        if (r4 == 0) goto L_0x0023;
    L_0x001f:
        r1.close();
    L_0x0022:
        return r0;
    L_0x0023:
        r4 = 0;
        r1.write(r2, r4, r3);
        r0 = r0 + r3;
        r4 = 0;
        r3 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1));
        if (r3 <= 0) goto L_0x000f;
    L_0x002e:
        if (r14 == 0) goto L_0x000f;
    L_0x0030:
        r4 = (long) r0;
        r4 = r4 + r10;
        r6 = 100;
        r4 = r4 * r6;
        r4 = r4 / r12;
        if (r15 == 0) goto L_0x003c;
    L_0x0038:
        r3 = r15.f2099a;
        if (r3 != 0) goto L_0x000f;
    L_0x003c:
        r14.m3393a(r4);
        goto L_0x000f;
    L_0x0040:
        r1.close();
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ae.a(java.io.File, java.util.zip.ZipInputStream, long, long, com.amap.api.mapcore.util.ae$c, com.amap.api.mapcore.util.ae$a):int");
    }

    private static void m3404a(C0340b c0340b) {
        if (c0340b != null) {
            aa d = c0340b.m3400d();
            if (d != null) {
                d.m3360p();
            }
            Object a = c0340b.m3396a();
            Object b = c0340b.m3398b();
            if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(b)) {
                File file = new File(a);
                if (file.exists()) {
                    C0337c c03381;
                    File file2 = new File(b);
                    if (file2.exists() || !file2.mkdirs()) {
                        c03381 = new C03381(d);
                    } else {
                        c03381 = new C03381(d);
                    }
                    try {
                        if (c0340b.m3401e().f2099a && d != null) {
                            d.m3362r();
                        }
                        m3406a(file, file2, c03381, c0340b);
                        if (c0340b.m3401e().f2099a) {
                            if (d != null) {
                                d.m3362r();
                            }
                        } else if (d != null) {
                            d.m3359b(c0340b.m3399c());
                        }
                    } catch (Exception e) {
                        if (c0340b.m3401e().f2099a) {
                            if (d != null) {
                                d.m3362r();
                            }
                        } else if (d != null) {
                            d.m3361q();
                        }
                    }
                } else if (c0340b.m3401e().f2099a) {
                    if (d != null) {
                        d.m3362r();
                    }
                } else if (d != null) {
                    d.m3361q();
                }
            } else if (c0340b.m3401e().f2099a) {
                if (d != null) {
                    d.m3362r();
                }
            } else if (d != null) {
                d.m3361q();
            }
        }
    }

    private static void m3405a(File file) {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            m3405a(parentFile);
            if (!parentFile.mkdir()) {
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void m3406a(java.io.File r10, java.io.File r11, com.amap.api.mapcore.util.ae.C0337c r12, com.amap.api.mapcore.util.ae.C0340b r13) {
        /*
        r0 = new java.lang.StringBuffer;
        r0.<init>();
        r5 = r13.m3401e();
        r2 = 0;
        if (r12 == 0) goto L_0x0049;
    L_0x000d:
        r1 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0080 }
        r1.<init>(r10);	 Catch:{ Exception -> 0x0080 }
        r4 = new java.util.zip.CheckedInputStream;	 Catch:{ Exception -> 0x0080 }
        r6 = new java.util.zip.CRC32;	 Catch:{ Exception -> 0x0080 }
        r6.<init>();	 Catch:{ Exception -> 0x0080 }
        r4.<init>(r1, r6);	 Catch:{ Exception -> 0x0080 }
        r6 = new java.util.zip.ZipInputStream;	 Catch:{ Exception -> 0x0080 }
        r6.<init>(r4);	 Catch:{ Exception -> 0x0080 }
    L_0x0021:
        r7 = r6.getNextEntry();	 Catch:{ Exception -> 0x0080 }
        if (r7 == 0) goto L_0x0039;
    L_0x0027:
        if (r5 == 0) goto L_0x006c;
    L_0x0029:
        r8 = r5.f2099a;	 Catch:{ Exception -> 0x0080 }
        if (r8 == 0) goto L_0x006c;
    L_0x002d:
        r6.closeEntry();	 Catch:{ Exception -> 0x0080 }
        r6.close();	 Catch:{ Exception -> 0x0080 }
        r4.close();	 Catch:{ Exception -> 0x0080 }
        r1.close();	 Catch:{ Exception -> 0x0080 }
    L_0x0039:
        r0 = r0.toString();	 Catch:{ Exception -> 0x0080 }
        r13.m3397a(r0);	 Catch:{ Exception -> 0x0080 }
        r6.close();	 Catch:{ Exception -> 0x0080 }
        r4.close();	 Catch:{ Exception -> 0x0080 }
        r1.close();	 Catch:{ Exception -> 0x0080 }
    L_0x0049:
        r6 = new java.io.FileInputStream;
        r6.<init>(r10);
        r7 = new java.util.zip.CheckedInputStream;
        r0 = new java.util.zip.CRC32;
        r0.<init>();
        r7.<init>(r6, r0);
        r1 = new java.util.zip.ZipInputStream;
        r1.<init>(r7);
        r0 = r11;
        r4 = r12;
        m3407a(r0, r1, r2, r4, r5);
        r1.close();
        r7.close();
        r6.close();
        return;
    L_0x006c:
        r8 = r7.isDirectory();	 Catch:{ Exception -> 0x0080 }
        if (r8 != 0) goto L_0x0092;
    L_0x0072:
        r8 = r7.getName();	 Catch:{ Exception -> 0x0080 }
        r8 = m3408a(r8);	 Catch:{ Exception -> 0x0080 }
        if (r8 != 0) goto L_0x0085;
    L_0x007c:
        r12.m3392a();	 Catch:{ Exception -> 0x0080 }
        goto L_0x0039;
    L_0x0080:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0049;
    L_0x0085:
        r8 = r7.getName();	 Catch:{ Exception -> 0x0080 }
        r8 = r0.append(r8);	 Catch:{ Exception -> 0x0080 }
        r9 = ";";
        r8.append(r9);	 Catch:{ Exception -> 0x0080 }
    L_0x0092:
        r8 = r7.getSize();	 Catch:{ Exception -> 0x0080 }
        r2 = r2 + r8;
        r6.closeEntry();	 Catch:{ Exception -> 0x0080 }
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ae.a(java.io.File, java.io.File, com.amap.api.mapcore.util.ae$c, com.amap.api.mapcore.util.ae$b):void");
    }

    private static void m3407a(File file, ZipInputStream zipInputStream, long j, C0337c c0337c, C0339a c0339a) {
        int i = 0;
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                return;
            }
            if (c0339a == null || !c0339a.f2099a) {
                String str = file.getPath() + File.separator + nextEntry.getName();
                if (!m3408a(str)) {
                    break;
                }
                File file2 = new File(str);
                m3405a(file2);
                int a = nextEntry.isDirectory() ? !file2.mkdirs() ? i : i : m3403a(file2, zipInputStream, (long) i, j, c0337c, c0339a) + i;
                zipInputStream.closeEntry();
                i = a;
            } else {
                zipInputStream.closeEntry();
                return;
            }
        }
        if (c0337c != null) {
            c0337c.m3392a();
        }
    }

    private static boolean m3408a(String str) {
        return !str.contains("../");
    }

    public void m3409a() {
        if (this.f2106a != null) {
            this.f2106a.m3402f();
        }
    }

    public void m3410b() {
        if (this.f2106a != null) {
            m3404a(this.f2106a);
        }
    }
}
