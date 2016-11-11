package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.os.StatFs;
import com.baidu.android.common.logging.Log;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.util.HashMap;

public class ba {
    private static final CompressFormat f2178a;
    private cx f2179b;
    private bf<String, Bitmap> f2180c;
    private C0352a f2181d;
    private final Object f2182e;
    private boolean f2183f;
    private HashMap<String, WeakReference<Bitmap>> f2184g;

    /* renamed from: com.amap.api.mapcore.util.ba.1 */
    class C03511 extends bf<String, Bitmap> {
        final /* synthetic */ ba f2169a;

        C03511(ba baVar, int i) {
            this.f2169a = baVar;
            super(i);
        }

        protected int m3520a(String str, Bitmap bitmap) {
            int a = ba.m3528a(bitmap);
            return a == 0 ? 1 : a;
        }

        protected void m3522a(boolean z, String str, Bitmap bitmap, Bitmap bitmap2) {
            if (bj.m3639c() && this.f2169a.f2184g != null && bitmap != null && !bitmap.isRecycled()) {
                this.f2169a.f2184g.put(str, new WeakReference(bitmap));
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.util.ba.a */
    public class C0352a {
        public int f2170a;
        public int f2171b;
        public File f2172c;
        public CompressFormat f2173d;
        public int f2174e;
        public boolean f2175f;
        public boolean f2176g;
        public boolean f2177h;

        public C0352a(Context context, String str) {
            this.f2170a = 5242880;
            this.f2171b = Log.FILE_LIMETE;
            this.f2173d = ba.f2178a;
            this.f2174e = 100;
            this.f2175f = true;
            this.f2176g = true;
            this.f2177h = false;
            this.f2172c = ba.m3532a(context, str);
        }

        public void m3523a(int i) {
            this.f2170a = i;
        }

        public void m3524a(String str) {
            this.f2172c = new File(str);
        }

        public void m3525a(boolean z) {
            this.f2175f = z;
        }

        public void m3526b(int i) {
            if (i <= 0) {
                this.f2176g = false;
            }
            this.f2171b = i;
        }

        public void m3527b(boolean z) {
            this.f2176g = z;
        }
    }

    static {
        f2178a = CompressFormat.PNG;
    }

    private ba(C0352a c0352a) {
        this.f2182e = new Object();
        this.f2183f = true;
        m3535b(c0352a);
    }

    public static int m3528a(Bitmap bitmap) {
        return bj.m3641d() ? bitmap.getByteCount() : bitmap.getRowBytes() * bitmap.getHeight();
    }

    public static long m3529a(File file) {
        if (bj.m3636b()) {
            return file.getUsableSpace();
        }
        StatFs statFs = new StatFs(file.getPath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public static ba m3530a(C0352a c0352a) {
        return new ba(c0352a);
    }

    public static File m3531a(Context context) {
        if (bj.m3626a()) {
            return context.getExternalCacheDir();
        }
        return new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/cache/"));
    }

    public static File m3532a(Context context, String str) {
        File a = m3531a(context);
        String path = (("mounted".equals(Environment.getExternalStorageState()) || !m3538e()) && a != null) ? a.getPath() : context.getCacheDir().getPath();
        return new File(path + File.separator + str);
    }

    private static String m3533a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & Util.MASK_8BIT);
            if (toHexString.length() == 1) {
                stringBuilder.append('0');
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    private void m3535b(C0352a c0352a) {
        this.f2181d = c0352a;
        if (this.f2181d.f2175f) {
            if (bj.m3639c()) {
                this.f2184g = new HashMap();
            }
            this.f2180c = new C03511(this, this.f2181d.f2170a);
        }
        if (c0352a.f2177h) {
            m3541a();
        }
    }

    private void m3536b(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        int length = listFiles.length;
        int i = 0;
        while (i < length) {
            File file2 = listFiles[i];
            if (file2.isDirectory()) {
                m3536b(file2);
            }
            if (file2.delete()) {
                i++;
            } else {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }

    public static String m3537c(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes("utf-8"));
            return m3533a(instance.digest());
        } catch (Throwable th) {
            return String.valueOf(str.hashCode());
        }
    }

    public static boolean m3538e() {
        return bj.m3636b() ? Environment.isExternalStorageRemovable() : true;
    }

    public Bitmap m3540a(String str) {
        Bitmap bitmap;
        if (bj.m3639c() && this.f2184g != null) {
            WeakReference weakReference = (WeakReference) this.f2184g.get(str);
            if (weakReference != null) {
                bitmap = (Bitmap) weakReference.get();
                if (bitmap == null || bitmap.isRecycled()) {
                    bitmap = null;
                }
                this.f2184g.remove(str);
                if (bitmap == null && this.f2180c != null) {
                    bitmap = (Bitmap) this.f2180c.m3514a((Object) str);
                }
                return (bitmap == null || bitmap.isRecycled()) ? null : bitmap;
            }
        }
        bitmap = null;
        bitmap = (Bitmap) this.f2180c.m3514a((Object) str);
        if (bitmap == null) {
            return null;
        }
    }

    public void m3541a() {
        synchronized (this.f2182e) {
            if (this.f2179b == null || this.f2179b.m3983a()) {
                File file = this.f2181d.f2172c;
                if (this.f2181d.f2176g && file != null) {
                    try {
                        if (file.exists()) {
                            m3536b(file);
                        }
                        file.mkdir();
                    } catch (Throwable th) {
                    }
                    if (m3529a(file) > ((long) this.f2181d.f2171b)) {
                        try {
                            this.f2179b = cx.m3961a(file, 1, 1, (long) this.f2181d.f2171b);
                        } catch (Throwable th2) {
                            this.f2181d.f2172c = null;
                        }
                    }
                }
            }
            this.f2183f = false;
            this.f2182e.notifyAll();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m3542a(java.lang.String r7, android.graphics.Bitmap r8) {
        /*
        r6 = this;
        if (r7 == 0) goto L_0x000a;
    L_0x0002:
        if (r8 == 0) goto L_0x000a;
    L_0x0004:
        r0 = r8.isRecycled();
        if (r0 == 0) goto L_0x000b;
    L_0x000a:
        return;
    L_0x000b:
        r0 = r6.f2180c;
        if (r0 == 0) goto L_0x0014;
    L_0x000f:
        r0 = r6.f2180c;
        r0.m3518b(r7, r8);
    L_0x0014:
        r2 = r6.f2182e;
        monitor-enter(r2);
        r0 = r6.f2179b;	 Catch:{ all -> 0x004d }
        if (r0 == 0) goto L_0x004b;
    L_0x001b:
        r1 = m3537c(r7);	 Catch:{ all -> 0x004d }
        r0 = 0;
        r3 = r6.f2179b;	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        r3 = r3.m3981a(r1);	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        if (r3 != 0) goto L_0x0050;
    L_0x0028:
        r3 = r6.f2179b;	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        r1 = r3.m3984b(r1);	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        if (r1 == 0) goto L_0x0046;
    L_0x0030:
        r3 = 0;
        r0 = r1.m3941a(r3);	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        r3 = r6.f2181d;	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r3 = r3.f2173d;	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r4 = r6.f2181d;	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r4 = r4.f2174e;	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r8.compress(r3, r4, r0);	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r1.m3942a();	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r0.close();	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
    L_0x0046:
        if (r0 == 0) goto L_0x004b;
    L_0x0048:
        r0.close();	 Catch:{ Throwable -> 0x006c }
    L_0x004b:
        monitor-exit(r2);	 Catch:{ all -> 0x004d }
        goto L_0x000a;
    L_0x004d:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x004d }
        throw r0;
    L_0x0050:
        r1 = 0;
        r1 = r3.m3944a(r1);	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        r1.close();	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        goto L_0x0046;
    L_0x0059:
        r1 = move-exception;
        if (r0 == 0) goto L_0x004b;
    L_0x005c:
        r0.close();	 Catch:{ Throwable -> 0x0060 }
        goto L_0x004b;
    L_0x0060:
        r0 = move-exception;
        goto L_0x004b;
    L_0x0062:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
    L_0x0066:
        if (r1 == 0) goto L_0x006b;
    L_0x0068:
        r1.close();	 Catch:{ Throwable -> 0x006e }
    L_0x006b:
        throw r0;	 Catch:{ all -> 0x004d }
    L_0x006c:
        r0 = move-exception;
        goto L_0x004b;
    L_0x006e:
        r1 = move-exception;
        goto L_0x006b;
    L_0x0070:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x0066;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ba.a(java.lang.String, android.graphics.Bitmap):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap m3543b(java.lang.String r8) {
        /*
        r7 = this;
        r3 = 0;
        r1 = m3537c(r8);
        r4 = r7.f2182e;
        monitor-enter(r4);
    L_0x0008:
        r2 = r7.f2183f;	 Catch:{ all -> 0x0052 }
        if (r2 == 0) goto L_0x0014;
    L_0x000c:
        r2 = r7.f2182e;	 Catch:{ Throwable -> 0x0012 }
        r2.wait();	 Catch:{ Throwable -> 0x0012 }
        goto L_0x0008;
    L_0x0012:
        r2 = move-exception;
        goto L_0x0008;
    L_0x0014:
        r2 = r7.f2179b;	 Catch:{ all -> 0x0052 }
        if (r2 == 0) goto L_0x003e;
    L_0x0018:
        r2 = r7.f2179b;	 Catch:{ Throwable -> 0x0040, all -> 0x004a }
        r1 = r2.m3981a(r1);	 Catch:{ Throwable -> 0x0040, all -> 0x004a }
        if (r1 == 0) goto L_0x005d;
    L_0x0020:
        r2 = 0;
        r2 = r1.m3944a(r2);	 Catch:{ Throwable -> 0x0040, all -> 0x004a }
        if (r2 == 0) goto L_0x0039;
    L_0x0027:
        r0 = r2;
        r0 = (java.io.FileInputStream) r0;	 Catch:{ Throwable -> 0x005b, all -> 0x0059 }
        r1 = r0;
        r1 = r1.getFD();	 Catch:{ Throwable -> 0x005b, all -> 0x0059 }
        r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r6 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r3 = com.amap.api.mapcore.util.bc.m3568a(r1, r5, r6, r7);	 Catch:{ Throwable -> 0x005b, all -> 0x0059 }
    L_0x0039:
        if (r2 == 0) goto L_0x003e;
    L_0x003b:
        r2.close();	 Catch:{ Throwable -> 0x0055 }
    L_0x003e:
        monitor-exit(r4);	 Catch:{ all -> 0x0052 }
        return r3;
    L_0x0040:
        r1 = move-exception;
        r2 = r3;
    L_0x0042:
        if (r2 == 0) goto L_0x003e;
    L_0x0044:
        r2.close();	 Catch:{ Throwable -> 0x0048 }
        goto L_0x003e;
    L_0x0048:
        r1 = move-exception;
        goto L_0x003e;
    L_0x004a:
        r1 = move-exception;
        r2 = r3;
    L_0x004c:
        if (r2 == 0) goto L_0x0051;
    L_0x004e:
        r2.close();	 Catch:{ Throwable -> 0x0057 }
    L_0x0051:
        throw r1;	 Catch:{ all -> 0x0052 }
    L_0x0052:
        r1 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0052 }
        throw r1;
    L_0x0055:
        r1 = move-exception;
        goto L_0x003e;
    L_0x0057:
        r2 = move-exception;
        goto L_0x0051;
    L_0x0059:
        r1 = move-exception;
        goto L_0x004c;
    L_0x005b:
        r1 = move-exception;
        goto L_0x0042;
    L_0x005d:
        r2 = r3;
        goto L_0x0039;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ba.b(java.lang.String):android.graphics.Bitmap");
    }

    public void m3544b() {
        if (bj.m3639c() && this.f2184g != null) {
            this.f2184g.clear();
        }
        if (this.f2180c != null) {
            this.f2180c.m3515a();
        }
        synchronized (this.f2182e) {
            this.f2183f = true;
            if (!(this.f2179b == null || this.f2179b.m3983a())) {
                try {
                    this.f2179b.m3986c();
                } catch (Throwable th) {
                }
                this.f2179b = null;
                m3541a();
            }
        }
    }

    public void m3545c() {
        synchronized (this.f2182e) {
            if (this.f2179b != null) {
                try {
                    this.f2179b.m3985b();
                } catch (Throwable th) {
                }
            }
        }
    }

    public void m3546d() {
        if (bj.m3639c() && this.f2184g != null) {
            this.f2184g.clear();
        }
        if (this.f2180c != null) {
            this.f2180c.m3515a();
        }
        synchronized (this.f2182e) {
            if (this.f2179b != null) {
                try {
                    if (!this.f2179b.m3983a()) {
                        this.f2179b.m3986c();
                        this.f2179b = null;
                    }
                } catch (Throwable th) {
                }
            }
        }
    }
}
