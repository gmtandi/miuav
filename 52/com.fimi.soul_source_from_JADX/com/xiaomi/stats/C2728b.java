package com.xiaomi.stats;

import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.channel.commonutils.string.C2473a;
import com.xiaomi.xmpush.thrift.C2730b;
import com.xiaomi.xmpush.thrift.C2773x;
import java.util.Hashtable;
import org.p122a.p137b.C2478b;

/* renamed from: com.xiaomi.stats.b */
public class C2728b {
    private static final int f13461a;
    private static final int f13462b;
    private static final int f13463c;

    /* renamed from: com.xiaomi.stats.b.a */
    class C2727a {
        static Hashtable<Integer, Long> f13460a;

        static {
            f13460a = new Hashtable();
        }
    }

    static {
        f13461a = C2730b.TCP_CONN_FAIL.m15401a();
        f13462b = C2730b.TCP_CONN_TIME.m15401a();
        f13463c = C2730b.PING_RTT.m15401a();
    }

    public static void m15390a() {
        C2728b.m15391a(0, f13463c);
    }

    public static synchronized void m15391a(int i, int i2) {
        synchronized (C2728b.class) {
            C2727a.f13460a.put(Integer.valueOf((i << 24) | i2), Long.valueOf(System.currentTimeMillis()));
        }
    }

    public static void m15392a(int i, int i2, int i3, String str) {
        C2726a.m15380a().m15384a(i, i2, i3, str);
    }

    public static void m15393a(long j, String str) {
        C2726a.m15380a().m15384a(0, f13462b, (int) j, str);
    }

    public static void m15394a(String str) {
        C2726a.m15380a().m15384a(0, f13461a, 1, str);
    }

    public static boolean m15395a(int i, int i2, int i3, String str, String str2) {
        return C2726a.m15380a().m15387a(i, i2, i3, str, str2);
    }

    public static void m15396b() {
        C2728b.m15397b(0, f13463c);
    }

    public static synchronized void m15397b(int i, int i2) {
        synchronized (C2728b.class) {
            long currentTimeMillis = System.currentTimeMillis();
            int i3 = (i << 24) | i2;
            if (C2727a.f13460a.contains(Integer.valueOf(i3))) {
                C2726a.m15380a().m15384a(i, i2, (int) (currentTimeMillis - ((Long) C2727a.f13460a.get(Integer.valueOf(i3))).longValue()), null);
                C2727a.f13460a.remove(Integer.valueOf(i2));
            }
        }
    }

    public static String m15398c() {
        C2478b c = C2726a.m15380a().m15389c();
        if (c == null) {
            return null;
        }
        byte[] a = C2773x.m15815a(c);
        if (a == null) {
            return null;
        }
        String str = new String(C2473a.m14158a(a));
        C2463b.m14126b(str);
        return str;
    }
}
