package com.baidu.tts.chainofresponsibility.logger;

import android.util.Log;
import com.baidu.tts.p042f.p043a.C0804a;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.baidu.tts.chainofresponsibility.logger.d */
public class C0755d {
    private static volatile C0755d f4204a;
    private List<C0749b> f4205b;
    private ExecutorService f4206c;
    private C0757f f4207d;
    private C0756e f4208e;
    private C0753a f4209f;
    private boolean f4210g;

    /* renamed from: com.baidu.tts.chainofresponsibility.logger.d.1 */
    /* synthetic */ class C07521 {
        static final /* synthetic */ int[] f4198a;

        static {
            f4198a = new int[C0753a.values().length];
            try {
                f4198a[C0753a.DEVELOP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4198a[C0753a.RELEASE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* renamed from: com.baidu.tts.chainofresponsibility.logger.d.a */
    enum C0753a {
        DEVELOP,
        RELEASE
    }

    /* renamed from: com.baidu.tts.chainofresponsibility.logger.d.b */
    class C0754b implements Runnable {
        final /* synthetic */ C0755d f4202a;
        private C0751c f4203b;

        public C0754b(C0755d c0755d, C0751c c0751c) {
            this.f4202a = c0755d;
            this.f4203b = c0751c;
        }

        public void run() {
            for (C0749b a : this.f4202a.f4205b) {
                a.m6513a(this.f4203b, null, C0755d.f4204a);
            }
        }
    }

    static {
        f4204a = null;
    }

    private C0755d() {
        this.f4208e = new C0756e();
        this.f4209f = C0753a.RELEASE;
        this.f4210g = false;
        this.f4205b = m6532g();
        this.f4207d = new C0757f();
        this.f4205b.add(this.f4207d);
        this.f4206c = Executors.newSingleThreadExecutor(new C0804a("LoggerChainPoolThread"));
    }

    public static C0755d m6527a() {
        if (f4204a == null) {
            synchronized (C0755d.class) {
                if (f4204a == null) {
                    f4204a = new C0755d();
                }
            }
        }
        return f4204a;
    }

    private void m6529a(C0751c c0751c, int i, String str, String str2) {
        if (c0751c == null) {
            c0751c = new C0751c();
        }
        c0751c.m6521a(i);
        c0751c.m6522a(str);
        c0751c.m6524b(str2);
        m6534a(c0751c);
    }

    private void m6530b(C0751c c0751c) {
        try {
            if (this.f4206c != null && !this.f4206c.isShutdown()) {
                this.f4206c.execute(new C0754b(this, c0751c));
            }
        } catch (Exception e) {
            Log.e("LoggerChain", "executeWork exception=" + e.toString());
        }
    }

    private List<C0749b> m6532g() {
        return this.f4205b == null ? new CopyOnWriteArrayList() : this.f4205b;
    }

    public void m6533a(int i, String str, String str2) {
        m6529a(new C0751c(), i, str, str2);
    }

    public void m6534a(C0751c c0751c) {
        if (c0751c != null) {
            switch (C07521.f4198a[this.f4209f.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    c0751c.m6521a(6);
                    this.f4208e.m6544a(c0751c, null, f4204a);
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (this.f4210g) {
                        this.f4208e.m6544a(c0751c, null, f4204a);
                        break;
                    }
                    break;
            }
            m6530b(c0751c);
        }
    }

    public void m6535a(String str) {
        if (this.f4208e != null) {
            this.f4208e.m6545a(str);
        }
    }

    public void m6536a(List<String> list) {
        if (this.f4208e != null) {
            this.f4208e.m6546a((List) list);
        }
    }

    public void m6537a(boolean z) {
        this.f4210g = z;
    }

    public void m6538b() {
        if (this.f4205b != null) {
            this.f4205b.clear();
        }
    }

    public void m6539b(String str) {
        if (this.f4208e != null) {
            this.f4208e.m6547b(str);
        }
    }

    public void m6540c() {
        if (this.f4208e != null) {
            this.f4208e.m6543a();
        }
    }

    public void m6541d() {
        this.f4209f = C0753a.RELEASE;
    }

    public boolean m6542e() {
        return this.f4209f == null || this.f4209f == C0753a.RELEASE;
    }
}
