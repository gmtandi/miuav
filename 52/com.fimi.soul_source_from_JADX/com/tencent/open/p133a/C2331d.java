package com.tencent.open.p133a;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.text.SimpleDateFormat;

/* renamed from: com.tencent.open.a.d */
public class C2331d {

    /* renamed from: com.tencent.open.a.d.a */
    public final class C2326a {
        public static final boolean m13737a(int i, int i2) {
            return i2 == (i & i2);
        }
    }

    /* renamed from: com.tencent.open.a.d.b */
    public final class C2327b {
        public static boolean m13738a(File file) {
            int i = 0;
            if (file == null) {
                return false;
            }
            if (file.isFile()) {
                if (file.delete()) {
                    return true;
                }
                file.deleteOnExit();
                return false;
            } else if (!file.isDirectory()) {
                return false;
            } else {
                File[] listFiles = file.listFiles();
                int length = listFiles.length;
                while (i < length) {
                    C2327b.m13738a(listFiles[i]);
                    i++;
                }
                return file.delete();
            }
        }
    }

    /* renamed from: com.tencent.open.a.d.c */
    public final class C2328c {
        public static boolean m13739a() {
            String externalStorageState = Environment.getExternalStorageState();
            return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
        }

        public static C2329d m13740b() {
            return !C2328c.m13739a() ? null : C2329d.m13741b(Environment.getExternalStorageDirectory());
        }
    }

    /* renamed from: com.tencent.open.a.d.d */
    public class C2329d {
        private File f12008a;
        private long f12009b;
        private long f12010c;

        public static C2329d m13741b(File file) {
            C2329d c2329d = new C2329d();
            c2329d.m13744a(file);
            StatFs statFs = new StatFs(file.getAbsolutePath());
            long blockSize = (long) statFs.getBlockSize();
            long availableBlocks = (long) statFs.getAvailableBlocks();
            c2329d.m13743a(((long) statFs.getBlockCount()) * blockSize);
            c2329d.m13746b(blockSize * availableBlocks);
            return c2329d;
        }

        public File m13742a() {
            return this.f12008a;
        }

        public void m13743a(long j) {
            this.f12009b = j;
        }

        public void m13744a(File file) {
            this.f12008a = file;
        }

        public long m13745b() {
            return this.f12009b;
        }

        public void m13746b(long j) {
            this.f12010c = j;
        }

        public long m13747c() {
            return this.f12010c;
        }

        public String toString() {
            return String.format("[%s : %d / %d]", new Object[]{m13742a().getAbsolutePath(), Long.valueOf(m13747c()), Long.valueOf(m13745b())});
        }
    }

    /* renamed from: com.tencent.open.a.d.e */
    public final class C2330e {
        @SuppressLint({"SimpleDateFormat"})
        public static SimpleDateFormat m13748a(String str) {
            return new SimpleDateFormat(str);
        }
    }
}
