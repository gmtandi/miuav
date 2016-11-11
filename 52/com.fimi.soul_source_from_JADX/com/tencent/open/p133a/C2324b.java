package com.tencent.open.p133a;

import com.fimi.kernel.p084e.C1173l;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.MAlarmHandler;
import com.tencent.open.p133a.C2331d.C2327b;
import com.tencent.open.p133a.C2331d.C2330e;
import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.tencent.open.a.b */
public class C2324b {
    private static SimpleDateFormat f11975a;
    private static FileFilter f11976b;
    private String f11977c;
    private int f11978d;
    private int f11979e;
    private int f11980f;
    private long f11981g;
    private File f11982h;
    private int f11983i;
    private String f11984j;
    private long f11985k;
    private FileFilter f11986l;
    private Comparator<? super File> f11987m;

    /* renamed from: com.tencent.open.a.b.1 */
    final class C23211 implements FileFilter {
        C23211() {
        }

        public boolean accept(File file) {
            return file.isDirectory() && C2324b.m13710a(file) > 0;
        }
    }

    /* renamed from: com.tencent.open.a.b.2 */
    class C23222 implements FileFilter {
        final /* synthetic */ C2324b f11973a;

        C23222(C2324b c2324b) {
            this.f11973a = c2324b;
        }

        public boolean accept(File file) {
            return file.getName().endsWith(this.f11973a.m13735i()) && C2324b.m13714f(file) != -1;
        }
    }

    /* renamed from: com.tencent.open.a.b.3 */
    class C23233 implements Comparator<File> {
        final /* synthetic */ C2324b f11974a;

        C23233(C2324b c2324b) {
            this.f11974a = c2324b;
        }

        public int m13709a(File file, File file2) {
            return C2324b.m13714f(file) - C2324b.m13714f(file2);
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m13709a((File) obj, (File) obj2);
        }
    }

    static {
        f11975a = C2330e.m13748a(C1173l.f5330b);
        f11976b = new C23211();
    }

    public C2324b(File file, int i, int i2, int i3, String str, long j, int i4, String str2, long j2) {
        this.f11977c = "Tracer.File";
        this.f11978d = Integer.MAX_VALUE;
        this.f11979e = Integer.MAX_VALUE;
        this.f11980f = Opcodes.ACC_SYNTHETIC;
        this.f11981g = 10000;
        this.f11983i = 10;
        this.f11984j = ".log";
        this.f11985k = MAlarmHandler.NEXT_FIRE_INTERVAL;
        this.f11986l = new C23222(this);
        this.f11987m = new C23233(this);
        m13728c(file);
        m13721b(i);
        m13717a(i2);
        m13726c(i3);
        m13718a(str);
        m13722b(j);
        m13730d(i4);
        m13723b(str2);
        m13727c(j2);
    }

    public static long m13710a(File file) {
        try {
            return f11975a.parse(file.getName()).getTime();
        } catch (Exception e) {
            return -1;
        }
    }

    private File m13712d(long j) {
        return m13713e(m13716a(j));
    }

    private File m13713e(File file) {
        File[] b = m13724b(file);
        if (b == null || b.length == 0) {
            return new File(file, Constants.VIA_TO_TYPE_QQ_GROUP + m13735i());
        }
        m13719a(b);
        File file2 = b[b.length - 1];
        int length = b.length - m13731e();
        if (((int) file2.length()) > m13729d()) {
            file2 = new File(file, (C2324b.m13714f(file2) + 1) + m13735i());
            length++;
        }
        for (int i = 0; i < length; i++) {
            b[i].delete();
        }
        return file2;
    }

    private static int m13714f(File file) {
        try {
            String name = file.getName();
            return Integer.parseInt(name.substring(0, name.indexOf(46)));
        } catch (Exception e) {
            return -1;
        }
    }

    public File m13715a() {
        return m13712d(System.currentTimeMillis());
    }

    public File m13716a(long j) {
        File file = new File(m13733g(), f11975a.format(Long.valueOf(j)));
        file.mkdirs();
        return file;
    }

    public void m13717a(int i) {
        this.f11978d = i;
    }

    public void m13718a(String str) {
        this.f11977c = str;
    }

    public File[] m13719a(File[] fileArr) {
        Arrays.sort(fileArr, this.f11987m);
        return fileArr;
    }

    public void m13720b() {
        if (m13733g() != null) {
            File[] listFiles = m13733g().listFiles(f11976b);
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (System.currentTimeMillis() - C2324b.m13710a(file) > m13736j()) {
                        C2327b.m13738a(file);
                    }
                }
            }
        }
    }

    public void m13721b(int i) {
        this.f11979e = i;
    }

    public void m13722b(long j) {
        this.f11981g = j;
    }

    public void m13723b(String str) {
        this.f11984j = str;
    }

    public File[] m13724b(File file) {
        return file.listFiles(this.f11986l);
    }

    public String m13725c() {
        return this.f11977c;
    }

    public void m13726c(int i) {
        this.f11980f = i;
    }

    public void m13727c(long j) {
        this.f11985k = j;
    }

    public void m13728c(File file) {
        this.f11982h = file;
    }

    public int m13729d() {
        return this.f11978d;
    }

    public void m13730d(int i) {
        this.f11983i = i;
    }

    public int m13731e() {
        return this.f11979e;
    }

    public int m13732f() {
        return this.f11980f;
    }

    public File m13733g() {
        return this.f11982h;
    }

    public int m13734h() {
        return this.f11983i;
    }

    public String m13735i() {
        return this.f11984j;
    }

    public long m13736j() {
        return this.f11985k;
    }
}
