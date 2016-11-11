package com.xiaomi.channel.commonutils.logger;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.channel.commonutils.logger.b */
public abstract class C2463b {
    private static int f12414a;
    private static LoggerInterface f12415b;
    private static final HashMap<Integer, Long> f12416c;
    private static final HashMap<Integer, String> f12417d;
    private static final Integer f12418e;
    private static AtomicInteger f12419f;

    static {
        f12414a = 2;
        f12415b = new C2462a();
        f12416c = new HashMap();
        f12417d = new HashMap();
        f12418e = Integer.valueOf(-1);
        f12419f = new AtomicInteger(1);
    }

    public static void m14117a(int i) {
        if (i < 0 || i > 5) {
            C2463b.m14118a(2, "set log level as " + i);
        }
        f12414a = i;
    }

    public static void m14118a(int i, String str) {
        if (i >= f12414a) {
            f12415b.log(str);
        }
    }

    public static void m14119a(int i, String str, Throwable th) {
        if (i >= f12414a) {
            f12415b.log(str, th);
        }
    }

    public static void m14120a(int i, Throwable th) {
        if (i >= f12414a) {
            f12415b.log(C2915a.f14760f, th);
        }
    }

    public static void m14121a(LoggerInterface loggerInterface) {
        f12415b = loggerInterface;
    }

    public static void m14122a(Integer num) {
        if (f12414a <= 1 && f12416c.containsKey(num)) {
            long currentTimeMillis = System.currentTimeMillis() - ((Long) f12416c.remove(num)).longValue();
            f12415b.log(((String) f12417d.remove(num)) + " ends in " + currentTimeMillis + " ms");
        }
    }

    public static void m14123a(String str) {
        C2463b.m14118a(2, "[Thread:" + Thread.currentThread().getId() + "] " + str);
    }

    public static void m14124a(String str, Throwable th) {
        C2463b.m14119a(4, str, th);
    }

    public static void m14125a(Throwable th) {
        C2463b.m14120a(4, th);
    }

    public static void m14126b(String str) {
        C2463b.m14118a(1, "[Thread:" + Thread.currentThread().getId() + "] " + str);
    }

    public static void m14127c(String str) {
        C2463b.m14118a(4, str);
    }

    public static Integer m14128d(String str) {
        if (f12414a > 1) {
            return f12418e;
        }
        Integer valueOf = Integer.valueOf(f12419f.incrementAndGet());
        f12416c.put(valueOf, Long.valueOf(System.currentTimeMillis()));
        f12417d.put(valueOf, str);
        f12415b.log(str + " starts");
        return valueOf;
    }
}
