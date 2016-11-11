package p000a;

import java.util.concurrent.Executor;

/* renamed from: a.o */
class C0015o implements Executor {
    private static final int f77a = 15;
    private ThreadLocal<Integer> f78b;

    private C0015o() {
        this.f78b = new ThreadLocal();
    }

    private int m67a() {
        Integer num = (Integer) this.f78b.get();
        if (num == null) {
            num = Integer.valueOf(0);
        }
        int intValue = num.intValue() + 1;
        this.f78b.set(Integer.valueOf(intValue));
        return intValue;
    }

    private int m68b() {
        Integer num = (Integer) this.f78b.get();
        if (num == null) {
            num = Integer.valueOf(0);
        }
        int intValue = num.intValue() - 1;
        if (intValue == 0) {
            this.f78b.remove();
        } else {
            this.f78b.set(Integer.valueOf(intValue));
        }
        return intValue;
    }

    public void execute(Runnable runnable) {
        if (m67a() <= f77a) {
            try {
                runnable.run();
            } catch (Throwable th) {
                m68b();
            }
        } else {
            C0013m.m64a().execute(runnable);
        }
        m68b();
    }
}
