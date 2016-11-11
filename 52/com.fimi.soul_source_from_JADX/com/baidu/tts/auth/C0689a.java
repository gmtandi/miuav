package com.baidu.tts.auth;

import com.baidu.tts.auth.C0693b.C0691a;
import com.baidu.tts.auth.C0697c.C0694a;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.p027b.p028a.p032b.C0723e.C0721a;
import com.baidu.tts.p027b.p028a.p032b.C0727f.C0725b;
import com.baidu.tts.p030j.C0692b;
import com.baidu.tts.p030j.C0813c;
import com.baidu.tts.p034l.C0824b;
import com.baidu.tts.p034l.C0831j;
import com.baidu.tts.p041e.C0799l;
import com.baidu.tts.p041e.C0800m;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p044g.p045a.C0807c;
import com.baidu.tts.p044g.p046b.C0808a;
import com.baidu.tts.p044g.p046b.C0809b;
import com.baidu.tts.tools.StringTool;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.baidu.tts.auth.a */
public class C0689a {
    private static volatile C0689a f4067a;
    private C0813c<C0697c, C0694a> f4068b;
    private C0813c<C0693b, C0691a> f4069c;

    /* renamed from: com.baidu.tts.auth.a.1 */
    class C06841 implements Callable<C0694a> {
        final /* synthetic */ C0824b f4056a;
        final /* synthetic */ CountDownLatch f4057b;
        final /* synthetic */ C0689a f4058c;

        C06841(C0689a c0689a, C0824b c0824b, CountDownLatch countDownLatch) {
            this.f4058c = c0689a;
            this.f4056a = c0824b;
            this.f4057b = countDownLatch;
        }

        public C0694a m6091a() {
            try {
                C0694a a = this.f4058c.m6103a(this.f4056a.m6833a());
                return a;
            } finally {
                this.f4057b.countDown();
            }
        }

        public /* synthetic */ Object call() {
            return m6091a();
        }
    }

    /* renamed from: com.baidu.tts.auth.a.2 */
    class C06852 implements Callable<C0691a> {
        final /* synthetic */ C0824b f4059a;
        final /* synthetic */ CountDownLatch f4060b;
        final /* synthetic */ C0689a f4061c;

        C06852(C0689a c0689a, C0824b c0824b, CountDownLatch countDownLatch) {
            this.f4061c = c0689a;
            this.f4059a = c0824b;
            this.f4060b = countDownLatch;
        }

        public C0691a m6092a() {
            try {
                C0691a a = this.f4061c.m6102a(this.f4059a.m6836b());
                return a;
            } finally {
                this.f4060b.countDown();
            }
        }

        public /* synthetic */ Object call() {
            return m6092a();
        }
    }

    /* renamed from: com.baidu.tts.auth.a.3 */
    /* synthetic */ class C06863 {
        static final /* synthetic */ int[] f4062a;

        static {
            f4062a = new int[C0800m.values().length];
            try {
                f4062a[C0800m.ONLINE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4062a[C0800m.OFFLINE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4062a[C0800m.MIX.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* renamed from: com.baidu.tts.auth.a.a */
    class C0687a implements Callable<C0691a> {
        final /* synthetic */ C0689a f4063a;
        private C0721a f4064b;

        public C0687a(C0689a c0689a, C0721a c0721a) {
            this.f4063a = c0689a;
            this.f4064b = c0721a;
        }

        public C0691a m6093a() {
            C0691a c0691a = new C0691a();
            C0808a g = C0809b.m6769a().m6778g();
            if (g == null) {
                c0691a.m6108a(C0807c.m6758a().m6765b(C0802n.APP_RESOURCE_IS_NULL));
                return c0691a;
            }
            String h = this.f4064b.m6329h();
            String g2 = this.f4064b.m6327g();
            if (StringTool.isEmpty(g2)) {
                g2 = g.m6768b();
            }
            LoggerProxy.m6515d("AuthClient", "appCode=" + h);
            LoggerProxy.m6515d("AuthClient", "licenseFilePath=" + g2);
            C0692b c0693b = new C0693b();
            c0693b.m6119a(h);
            c0693b.m6121b(g2);
            return (C0691a) this.f4063a.f4069c.m6799a(c0693b);
        }

        public /* synthetic */ Object call() {
            return m6093a();
        }
    }

    /* renamed from: com.baidu.tts.auth.a.b */
    class C0688b implements Callable<C0694a> {
        final /* synthetic */ C0689a f4065a;
        private C0725b f4066b;

        public C0688b(C0689a c0689a, C0725b c0725b) {
            this.f4065a = c0689a;
            this.f4066b = c0725b;
        }

        public C0694a m6094a() {
            String d = this.f4066b.m6355d();
            String a = this.f4066b.m6345a();
            String b = this.f4066b.m6349b();
            LoggerProxy.m6515d("AuthClient", "pid=" + d);
            LoggerProxy.m6515d("AuthClient", "ak=" + a);
            LoggerProxy.m6515d("AuthClient", "sk=" + b);
            C0692b c0697c = new C0697c();
            c0697c.m6135a(d);
            c0697c.m6137b(a);
            c0697c.m6139c(b);
            return (C0694a) this.f4065a.f4068b.m6799a(c0697c);
        }

        public /* synthetic */ Object call() {
            return m6094a();
        }
    }

    static {
        f4067a = null;
    }

    private C0689a() {
        this.f4068b = new C0813c();
        this.f4069c = new C0813c();
    }

    public static C0689a m6095a() {
        if (f4067a == null) {
            synchronized (C0689a.class) {
                if (f4067a == null) {
                    f4067a = new C0689a();
                }
            }
        }
        return f4067a;
    }

    private <T> T m6097a(Callable<T> callable, long j) {
        return m6098a((Callable) callable).get(j, TimeUnit.MILLISECONDS);
    }

    private <T> FutureTask<T> m6098a(Callable<T> callable) {
        Object futureTask = new FutureTask(callable);
        new Thread(futureTask).start();
        return futureTask;
    }

    public AuthInfo m6100a(C0800m c0800m, C0831j c0831j) {
        C0824b a = c0831j.m6883a();
        AuthInfo authInfo = new AuthInfo();
        authInfo.setTtsEnum(c0800m);
        switch (C06863.f4062a[c0800m.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                authInfo.setOnlineResult(m6103a(a.m6833a()));
                return authInfo;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                authInfo.setOfflineResult(m6102a(a.m6836b()));
                return authInfo;
            case Type.BYTE /*3*/:
                return m6101a(a);
            default:
                return authInfo;
        }
    }

    public AuthInfo m6101a(C0824b c0824b) {
        C0691a c0691a;
        LoggerProxy.m6515d("AuthClient", "enter authMix");
        CountDownLatch countDownLatch = new CountDownLatch(2);
        FutureTask futureTask = new FutureTask(new C06841(this, c0824b, countDownLatch));
        FutureTask futureTask2 = new FutureTask(new C06852(this, c0824b, countDownLatch));
        new Thread(futureTask).start();
        new Thread(futureTask2).start();
        try {
            LoggerProxy.m6515d("AuthClient", "+ await");
            countDownLatch.await();
            LoggerProxy.m6515d("AuthClient", "- await");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            futureTask.cancel(true);
            futureTask2.cancel(true);
        }
        C0694a c0694a = new C0694a();
        LoggerProxy.m6515d("AuthClient", "+ mix online get onlineResult=" + c0694a);
        try {
            c0694a = (C0694a) futureTask.get();
        } catch (Throwable e2) {
            Thread.currentThread().interrupt();
            futureTask.cancel(true);
            c0694a.m6125a(C0807c.m6758a().m6763a(C0802n.ONLINE_AUTH_INTERRUPTED_EXCEPTION, e2));
        } catch (ExecutionException e3) {
            c0694a.m6125a(C0807c.m6758a().m6763a(C0802n.ONLINE_AUTH_EXECUTION_EXCEPTION, e3.getCause()));
        } catch (Throwable e22) {
            c0694a.m6125a(C0807c.m6758a().m6763a(C0802n.ONLINE_AUTH_CANCELLATION_EXCEPTION, e22));
        }
        LoggerProxy.m6515d("AuthClient", "- online get");
        C0691a c0691a2 = new C0691a();
        LoggerProxy.m6515d("AuthClient", "+ mix offline get offlineResult=" + c0691a2);
        try {
            c0691a = (C0691a) futureTask2.get();
        } catch (Throwable e222) {
            Thread.currentThread().interrupt();
            futureTask2.cancel(true);
            c0691a2.m6108a(C0807c.m6758a().m6763a(C0802n.OFFLINE_AUTH_INTERRUPTED_EXCEPTION, e222));
            c0691a = c0691a2;
        } catch (ExecutionException e32) {
            c0691a2.m6108a(C0807c.m6758a().m6763a(C0802n.OFFLINE_AUTH_EXECUTION_EXCEPTION, e32.getCause()));
            c0691a = c0691a2;
        } catch (Throwable e2222) {
            c0691a2.m6108a(C0807c.m6758a().m6763a(C0802n.OFFLINE_AUTH_CANCELLATION_EXCEPTION, e2222));
            c0691a = c0691a2;
        }
        LoggerProxy.m6515d("AuthClient", "- offline get");
        AuthInfo authInfo = new AuthInfo();
        authInfo.setTtsEnum(C0800m.MIX);
        authInfo.setOnlineResult(c0694a);
        authInfo.setOfflineResult(c0691a);
        LoggerProxy.m6515d("AuthClient", "end authMix");
        return authInfo;
    }

    public C0691a m6102a(C0721a c0721a) {
        C0691a c0691a = new C0691a();
        try {
            return (C0691a) m6097a(new C0687a(this, c0721a), C0799l.DEFAULT.m6747a());
        } catch (Throwable e) {
            Thread.currentThread().interrupt();
            c0691a.m6108a(C0807c.m6758a().m6763a(C0802n.OFFLINE_AUTH_INTERRUPTED_EXCEPTION, e));
            return c0691a;
        } catch (ExecutionException e2) {
            c0691a.m6108a(C0807c.m6758a().m6763a(C0802n.OFFLINE_AUTH_EXECUTION_EXCEPTION, e2.getCause()));
            return c0691a;
        } catch (Throwable e3) {
            c0691a.m6108a(C0807c.m6758a().m6763a(C0802n.OFFLINE_AUTH_TIMEOUT_EXCEPTION, e3));
            return c0691a;
        } catch (Throwable e32) {
            c0691a.m6108a(C0807c.m6758a().m6763a(C0802n.OFFLINE_AUTH_CANCELLATION_EXCEPTION, e32));
            return c0691a;
        }
    }

    public C0694a m6103a(C0725b c0725b) {
        C0694a c0694a = new C0694a();
        try {
            return (C0694a) m6097a(new C0688b(this, c0725b), C0799l.DEFAULT.m6747a());
        } catch (Throwable e) {
            Thread.currentThread().interrupt();
            c0694a.m6125a(C0807c.m6758a().m6763a(C0802n.ONLINE_AUTH_INTERRUPTED_EXCEPTION, e));
            return c0694a;
        } catch (ExecutionException e2) {
            c0694a.m6125a(C0807c.m6758a().m6763a(C0802n.ONLINE_AUTH_EXECUTION_EXCEPTION, e2.getCause()));
            return c0694a;
        } catch (Throwable e3) {
            c0694a.m6125a(C0807c.m6758a().m6763a(C0802n.ONLINE_AUTH_TIMEOUT_EXCEPTION, e3));
            return c0694a;
        } catch (Throwable e32) {
            c0694a.m6125a(C0807c.m6758a().m6763a(C0802n.ONLINE_AUTH_CANCELLATION_EXCEPTION, e32));
            return c0694a;
        }
    }

    public void m6104b() {
        if (this.f4068b != null) {
            this.f4068b.m6800a();
        }
        if (this.f4069c != null) {
            this.f4069c.m6800a();
        }
    }
}
