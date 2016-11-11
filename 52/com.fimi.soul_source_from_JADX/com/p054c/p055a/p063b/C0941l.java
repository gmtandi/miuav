package com.p054c.p055a.p063b;

import android.content.Context;
import com.p054c.p055a.p056a.p057a.C0864a;
import com.p054c.p055a.p056a.p057a.p060b.C0872a;
import com.p054c.p055a.p056a.p061b.C0875c;
import com.p054c.p055a.p056a.p061b.p062a.C0879b;
import com.p054c.p055a.p063b.p064a.C0902h;
import com.p054c.p055a.p063b.p066b.C0906d;
import com.p054c.p055a.p063b.p068d.C0920c;
import com.p054c.p055a.p063b.p071g.C0935a;
import com.p054c.p055a.p072c.C0958f;
import com.p054c.p055a.p072c.C0959g;
import java.util.concurrent.Executor;

/* renamed from: com.c.a.b.l */
public class C0941l {
    public static final int f4936a = 3;
    public static final int f4937b = 3;
    public static final C0902h f4938c;
    private static final String f4939d = "diskCache(), diskCacheSize() and diskCacheFileCount calls overlap each other";
    private static final String f4940e = "diskCache() and diskCacheFileNameGenerator() calls overlap each other";
    private static final String f4941f = "memoryCache() and memoryCacheSize() calls overlap each other";
    private static final String f4942g = "threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.";
    private C0872a f4943A;
    private C0920c f4944B;
    private C0906d f4945C;
    private C0924d f4946D;
    private boolean f4947E;
    private Context f4948h;
    private int f4949i;
    private int f4950j;
    private int f4951k;
    private int f4952l;
    private C0935a f4953m;
    private Executor f4954n;
    private Executor f4955o;
    private boolean f4956p;
    private boolean f4957q;
    private int f4958r;
    private int f4959s;
    private boolean f4960t;
    private C0902h f4961u;
    private int f4962v;
    private long f4963w;
    private int f4964x;
    private C0875c f4965y;
    private C0864a f4966z;

    static {
        f4938c = C0902h.FIFO;
    }

    public C0941l(Context context) {
        this.f4949i = 0;
        this.f4950j = 0;
        this.f4951k = 0;
        this.f4952l = 0;
        this.f4953m = null;
        this.f4954n = null;
        this.f4955o = null;
        this.f4956p = false;
        this.f4957q = false;
        this.f4958r = f4937b;
        this.f4959s = f4937b;
        this.f4960t = false;
        this.f4961u = f4938c;
        this.f4962v = 0;
        this.f4963w = 0;
        this.f4964x = 0;
        this.f4965y = null;
        this.f4966z = null;
        this.f4943A = null;
        this.f4944B = null;
        this.f4946D = null;
        this.f4947E = false;
        this.f4948h = context.getApplicationContext();
    }

    private void m7452d() {
        if (this.f4954n == null) {
            this.f4954n = C0905a.m7217a(this.f4958r, this.f4959s, this.f4961u);
        } else {
            this.f4956p = true;
        }
        if (this.f4955o == null) {
            this.f4955o = C0905a.m7217a(this.f4958r, this.f4959s, this.f4961u);
        } else {
            this.f4957q = true;
        }
        if (this.f4966z == null) {
            if (this.f4943A == null) {
                this.f4943A = C0905a.m7219b();
            }
            this.f4966z = C0905a.m7212a(this.f4948h, this.f4943A, this.f4963w, this.f4964x);
        }
        if (this.f4965y == null) {
            this.f4965y = C0905a.m7213a(this.f4948h, this.f4962v);
        }
        if (this.f4960t) {
            this.f4965y = new C0879b(this.f4965y, C0959g.m7564a());
        }
        if (this.f4944B == null) {
            this.f4944B = C0905a.m7215a(this.f4948h);
        }
        if (this.f4945C == null) {
            this.f4945C = C0905a.m7214a(this.f4947E);
        }
        if (this.f4946D == null) {
            this.f4946D = C0924d.m7290t();
        }
    }

    public C0941l m7468a() {
        this.f4960t = true;
        return this;
    }

    public C0941l m7469a(int i) {
        if (!(this.f4954n == null && this.f4955o == null)) {
            C0958f.m7561c(f4942g, new Object[0]);
        }
        this.f4958r = i;
        return this;
    }

    public C0941l m7470a(int i, int i2) {
        this.f4949i = i;
        this.f4950j = i2;
        return this;
    }

    @Deprecated
    public C0941l m7471a(int i, int i2, C0935a c0935a) {
        return m7482b(i, i2, c0935a);
    }

    @Deprecated
    public C0941l m7472a(C0864a c0864a) {
        return m7483b(c0864a);
    }

    @Deprecated
    public C0941l m7473a(C0872a c0872a) {
        return m7484b(c0872a);
    }

    public C0941l m7474a(C0875c c0875c) {
        if (this.f4962v != 0) {
            C0958f.m7561c(f4941f, new Object[0]);
        }
        this.f4965y = c0875c;
        return this;
    }

    public C0941l m7475a(C0902h c0902h) {
        if (!(this.f4954n == null && this.f4955o == null)) {
            C0958f.m7561c(f4942g, new Object[0]);
        }
        this.f4961u = c0902h;
        return this;
    }

    public C0941l m7476a(C0906d c0906d) {
        this.f4945C = c0906d;
        return this;
    }

    public C0941l m7477a(C0920c c0920c) {
        this.f4944B = c0920c;
        return this;
    }

    public C0941l m7478a(C0924d c0924d) {
        this.f4946D = c0924d;
        return this;
    }

    public C0941l m7479a(Executor executor) {
        if (!(this.f4958r == f4937b && this.f4959s == f4937b && this.f4961u == f4938c)) {
            C0958f.m7561c(f4942g, new Object[0]);
        }
        this.f4954n = executor;
        return this;
    }

    public C0941l m7480b() {
        this.f4947E = true;
        return this;
    }

    public C0941l m7481b(int i) {
        if (!(this.f4954n == null && this.f4955o == null)) {
            C0958f.m7561c(f4942g, new Object[0]);
        }
        if (i < 1) {
            this.f4959s = 1;
        } else if (i > 10) {
            this.f4959s = 10;
        } else {
            this.f4959s = i;
        }
        return this;
    }

    public C0941l m7482b(int i, int i2, C0935a c0935a) {
        this.f4951k = i;
        this.f4952l = i2;
        this.f4953m = c0935a;
        return this;
    }

    public C0941l m7483b(C0864a c0864a) {
        if (this.f4963w > 0 || this.f4964x > 0) {
            C0958f.m7561c(f4939d, new Object[0]);
        }
        if (this.f4943A != null) {
            C0958f.m7561c(f4940e, new Object[0]);
        }
        this.f4966z = c0864a;
        return this;
    }

    public C0941l m7484b(C0872a c0872a) {
        if (this.f4966z != null) {
            C0958f.m7561c(f4940e, new Object[0]);
        }
        this.f4943A = c0872a;
        return this;
    }

    public C0941l m7485b(Executor executor) {
        if (!(this.f4958r == f4937b && this.f4959s == f4937b && this.f4961u == f4938c)) {
            C0958f.m7561c(f4942g, new Object[0]);
        }
        this.f4955o = executor;
        return this;
    }

    public C0939j m7486c() {
        m7452d();
        return new C0939j();
    }

    public C0941l m7487c(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("memoryCacheSize must be a positive number");
        }
        if (this.f4965y != null) {
            C0958f.m7561c(f4941f, new Object[0]);
        }
        this.f4962v = i;
        return this;
    }

    public C0941l m7488d(int i) {
        if (i <= 0 || i >= 100) {
            throw new IllegalArgumentException("availableMemoryPercent must be in range (0 < % < 100)");
        }
        if (this.f4965y != null) {
            C0958f.m7561c(f4941f, new Object[0]);
        }
        this.f4962v = (int) (((float) Runtime.getRuntime().maxMemory()) * (((float) i) / 100.0f));
        return this;
    }

    @Deprecated
    public C0941l m7489e(int i) {
        return m7490f(i);
    }

    public C0941l m7490f(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxCacheSize must be a positive number");
        }
        if (this.f4966z != null) {
            C0958f.m7561c(f4939d, new Object[0]);
        }
        this.f4963w = (long) i;
        return this;
    }

    @Deprecated
    public C0941l m7491g(int i) {
        return m7492h(i);
    }

    public C0941l m7492h(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxFileCount must be a positive number");
        }
        if (this.f4966z != null) {
            C0958f.m7561c(f4939d, new Object[0]);
        }
        this.f4964x = i;
        return this;
    }
}
