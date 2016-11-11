package org.p004c.p198b.p202d.p205c;

import java.lang.Thread.State;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.p004c.p187f.p192a.C3377k;
import org.p004c.p187f.p192a.C3404g;
import org.p004c.p187f.p192a.C3532p;

/* renamed from: org.c.b.d.c.c */
public class C3424c extends C3377k {
    private final C3377k f15928a;
    private final TimeUnit f15929b;
    private final long f15930c;
    private final boolean f15931d;
    private volatile ThreadGroup f15932e;

    private C3424c(C3426e c3426e, C3377k c3377k) {
        this.f15932e = null;
        this.f15928a = c3377k;
        this.f15930c = c3426e.f15934b;
        this.f15929b = c3426e.f15935c;
        this.f15931d = c3426e.f15933a;
    }

    @Deprecated
    public C3424c(C3377k c3377k, long j) {
        this(C3424c.m18717b().m18726a(j, TimeUnit.MILLISECONDS), c3377k);
    }

    private Exception m18712a(Thread thread) {
        StackTraceElement[] stackTrace = thread.getStackTrace();
        Thread c = this.f15931d ? m18719c(thread) : null;
        Exception c3532p = new C3532p(this.f15930c, this.f15929b);
        if (stackTrace != null) {
            c3532p.setStackTrace(stackTrace);
            thread.interrupt();
        }
        if (c == null) {
            return c3532p;
        }
        new Exception("Appears to be stuck in thread " + c.getName()).setStackTrace(m18718b(c));
        return new C3404g(Arrays.asList(new Throwable[]{c3532p, new Exception("Appears to be stuck in thread " + c.getName())}));
    }

    private Throwable m18713a(FutureTask<Throwable> futureTask, Thread thread) {
        try {
            return this.f15930c > 0 ? (Throwable) futureTask.get(this.f15930c, this.f15929b) : (Throwable) futureTask.get();
        } catch (Throwable e) {
            return e;
        } catch (ExecutionException e2) {
            return e2.getCause();
        } catch (TimeoutException e3) {
            return m18712a(thread);
        }
    }

    private Thread[] m18715a(ThreadGroup threadGroup) {
        int max = Math.max(threadGroup.activeCount() * 2, 100);
        int i = 0;
        do {
            Thread[] threadArr = new Thread[max];
            int enumerate = threadGroup.enumerate(threadArr);
            if (enumerate < max) {
                return m18716a(threadArr, enumerate);
            }
            max += 100;
            i++;
        } while (i < 5);
        return null;
    }

    private Thread[] m18716a(Thread[] threadArr, int i) {
        int min = Math.min(i, threadArr.length);
        Thread[] threadArr2 = new Thread[min];
        for (int i2 = 0; i2 < min; i2++) {
            threadArr2[i2] = threadArr[i2];
        }
        return threadArr2;
    }

    public static C3426e m18717b() {
        return new C3426e();
    }

    private StackTraceElement[] m18718b(Thread thread) {
        try {
            return thread.getStackTrace();
        } catch (SecurityException e) {
            return new StackTraceElement[0];
        }
    }

    private Thread m18719c(Thread thread) {
        if (this.f15932e == null) {
            return null;
        }
        Thread[] a = m18715a(this.f15932e);
        if (a == null) {
            return null;
        }
        int length = a.length;
        int i = 0;
        Thread thread2 = null;
        long j = 0;
        while (i < length) {
            Thread thread3;
            Thread thread4 = a[i];
            if (thread4.getState() == State.RUNNABLE) {
                long d = m18720d(thread4);
                if (thread2 == null || d > r0) {
                    j = d;
                    thread3 = thread4;
                    i++;
                    thread2 = thread3;
                }
            }
            thread3 = thread2;
            i++;
            thread2 = thread3;
        }
        if (thread2 == thread) {
            thread2 = null;
        }
        return thread2;
    }

    private long m18720d(Thread thread) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        if (threadMXBean.isThreadCpuTimeSupported()) {
            try {
                return threadMXBean.getThreadCpuTime(thread.getId());
            } catch (UnsupportedOperationException e) {
            }
        }
        return 0;
    }

    public void m18721a() {
        Object c3427f = new C3427f();
        FutureTask futureTask = new FutureTask(c3427f);
        this.f15932e = new ThreadGroup("FailOnTimeoutGroup");
        Thread thread = new Thread(this.f15932e, futureTask, "Time-limited test");
        thread.setDaemon(true);
        thread.start();
        c3427f.m18729b();
        Throwable a = m18713a(futureTask, thread);
        if (a != null) {
            throw a;
        }
    }
}
