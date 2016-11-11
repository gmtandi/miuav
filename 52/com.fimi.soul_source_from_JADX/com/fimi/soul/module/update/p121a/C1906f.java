package com.fimi.soul.module.update.p121a;

import android.text.TextUtils;
import com.fimi.kernel.C1189f;
import com.fimi.soul.biz.p096d.C1325k;
import com.fimi.soul.drone.p107c.p108a.p109a.bi;
import com.fimi.soul.drone.p115f.C1538a;
import com.fimi.soul.drone.p115f.C1539b;
import com.fimi.soul.drone.p115f.C1540c;
import com.fimi.soul.drone.p116g.C1542b;
import com.fimi.soul.module.update.C1921n;
import com.fimi.soul.utils.C1969i;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Queue;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.module.update.a.f */
public class C1906f implements C1903c {
    private int f9806a;
    private Queue<bi> f9807b;

    public int m12060a() {
        return this.f9806a;
    }

    public long m12061a(int i) {
        return new File(m12065b(i)).length();
    }

    public void m12062a(int i, int i2) {
        Object obj = (C1902b) C1189f.m8333c().m7926a(C1921n.f9846k, C1902b.class);
        if (obj == null) {
            obj = new C1902b();
        }
        obj.m12028i().put(Integer.valueOf(i), Integer.valueOf(i2));
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                obj.m12013a(i2);
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                obj.m12015b(i2);
                break;
            case Type.BYTE /*3*/:
                obj.m12017c(i2);
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                obj.m12019d(i2);
                break;
            case Type.FLOAT /*6*/:
                obj.m12027h(i2);
                break;
            case Type.ARRAY /*9*/:
                obj.m12023f(i2);
                break;
            case Type.OBJECT /*10*/:
                obj.m12025g(i2);
                break;
            case Opcodes.T_LONG /*11*/:
                obj.m12021e(i2);
                break;
        }
        C1189f.m8333c().m7930a(C1921n.f9846k, obj);
    }

    public void m12063a(int i, byte[] bArr, byte[] bArr2) {
        for (int i2 = 0; i2 < 4; i2++) {
            bArr2[i + i2] = bArr[i2];
        }
    }

    public void m12064a(C1905e c1905e) {
        if (c1905e != null) {
            File file = new File(m12067c(c1905e.m12053d()));
            if (!file.exists()) {
                file.mkdir();
            }
            int d = c1905e.m12053d();
            c1905e.m12042a();
            long e = c1905e.m12055e();
            long f = c1905e.m12056f();
            long g = c1905e.m12057g();
            long h = c1905e.m12058h();
            InputStream inputStream = null;
            try {
                File file2 = new File(m12067c(c1905e.m12053d()));
                if (file2.exists()) {
                    inputStream = new FileInputStream(file2);
                }
                file2 = new File(m12065b(d));
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                byte[] a = C1542b.m10115a(e);
                byte[] a2 = C1542b.m10115a(f);
                byte[] a3 = C1542b.m10115a(g);
                byte[] a4 = C1542b.m10115a(h);
                byte[] a5 = C1540c.m10109a(inputStream);
                OutputStream fileOutputStream = new FileOutputStream(file2);
                m12063a(16, a, a5);
                m12063a(20, a2, a5);
                m12063a(24, a3, a5);
                m12063a(28, a4, a5);
                m12063a(48, C1542b.m10114a((int) C1542b.m10113a(C1538a.m10103a(a5), 12)), a5);
                int[] a6 = C1538a.m10103a(a5);
                m12063a(a5.length - 4, C1542b.m10114a((int) C1542b.m10113a(a6, a6.length - 1)), a5);
                fileOutputStream.write(a5);
                fileOutputStream.flush();
                inputStream.close();
                fileOutputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public String m12065b(int i) {
        return String.format("%s", new Object[]{m12067c(i) + ".crc"});
    }

    public Queue<bi> m12066b() {
        return this.f9807b;
    }

    public String m12067c(int i) {
        Object obj = null;
        if (i == 0) {
            obj = C1921n.f9847l;
        } else if (i == 3) {
            obj = C1921n.f9850o;
        } else if (i == 1) {
            obj = C1921n.f9848m;
        } else if (i == 2) {
            obj = C1921n.f9849n;
        } else if (i == 10) {
            obj = C1921n.f9851p;
        } else if (i == 9) {
            obj = C1921n.f9853r;
        } else if (i == 6) {
            obj = C1921n.f9852q;
        } else if (i == 11) {
            obj = C1921n.f9855t;
        } else if (i == 4 && !C1325k.m8930a().m8942g()) {
            obj = C1921n.f9854s;
        }
        return TextUtils.isEmpty(obj) ? C2915a.f14760f : C1969i.m12493p() + obj;
    }

    public Queue<bi> m12068d(int i) {
        File file = new File(m12065b(i));
        this.f9807b = new LinkedList();
        if (file.exists()) {
            try {
                C1539b.m10108a(i, new FileInputStream(file), file.length());
                this.f9807b = C1539b.m10106a();
                this.f9806a = this.f9807b.size();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.f9807b;
    }

    public void m12069e(int i) {
    }
}
