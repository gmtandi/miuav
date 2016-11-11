package com.tencent.open.p133a;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.tencent.open.a.a */
public class C2320a extends C2319i implements Callback {
    private C2324b f11962a;
    private FileWriter f11963b;
    private File f11964c;
    private char[] f11965d;
    private volatile C2334g f11966e;
    private volatile C2334g f11967f;
    private volatile C2334g f11968g;
    private volatile C2334g f11969h;
    private volatile boolean f11970i;
    private HandlerThread f11971j;
    private Handler f11972k;

    public C2320a(int i, boolean z, C2335h c2335h, C2324b c2324b) {
        super(i, z, c2335h);
        this.f11970i = false;
        m13705a(c2324b);
        this.f11966e = new C2334g();
        this.f11967f = new C2334g();
        this.f11968g = this.f11966e;
        this.f11969h = this.f11967f;
        this.f11965d = new char[c2324b.m13732f()];
        c2324b.m13720b();
        m13700g();
        this.f11971j = new HandlerThread(c2324b.m13725c(), c2324b.m13734h());
        if (this.f11971j != null) {
            this.f11971j.start();
        }
        if (this.f11971j.isAlive() && this.f11971j.getLooper() != null) {
            this.f11972k = new Handler(this.f11971j.getLooper(), this);
        }
    }

    public C2320a(C2324b c2324b) {
        this(C2325c.f11990c, true, C2335h.f12018a, c2324b);
    }

    private void m13699f() {
        if (Thread.currentThread() == this.f11971j && !this.f11970i) {
            this.f11970i = true;
            m13702i();
            try {
                this.f11969h.m13764a(m13700g(), this.f11965d);
            } catch (IOException e) {
            } finally {
                this.f11969h.m13765b();
            }
            this.f11970i = false;
        }
    }

    private Writer m13700g() {
        File a = m13708c().m13715a();
        if (!(a == null || a.equals(this.f11964c))) {
            this.f11964c = a;
            m13701h();
            try {
                this.f11963b = new FileWriter(this.f11964c, true);
            } catch (IOException e) {
                return null;
            }
        }
        return this.f11963b;
    }

    private void m13701h() {
        try {
            if (this.f11963b != null) {
                this.f11963b.flush();
                this.f11963b.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void m13702i() {
        synchronized (this) {
            if (this.f11968g == this.f11966e) {
                this.f11968g = this.f11967f;
                this.f11969h = this.f11966e;
            } else {
                this.f11968g = this.f11966e;
                this.f11969h = this.f11967f;
            }
        }
    }

    public void m13703a() {
        if (this.f11972k.hasMessages(SmileConstants.MAX_SHARED_STRING_VALUES)) {
            this.f11972k.removeMessages(SmileConstants.MAX_SHARED_STRING_VALUES);
        }
        this.f11972k.sendEmptyMessage(SmileConstants.MAX_SHARED_STRING_VALUES);
    }

    protected void m13704a(int i, Thread thread, long j, String str, String str2, Throwable th) {
        m13706a(m13698e().m13767a(i, thread, j, str, str2, th));
    }

    public void m13705a(C2324b c2324b) {
        this.f11962a = c2324b;
    }

    protected void m13706a(String str) {
        this.f11968g.m13763a(str);
        if (this.f11968g.m13762a() >= m13708c().m13732f()) {
            m13703a();
        }
    }

    public void m13707b() {
        m13701h();
        this.f11971j.quit();
    }

    public C2324b m13708c() {
        return this.f11962a;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case SmileConstants.MAX_SHARED_STRING_VALUES /*1024*/:
                m13699f();
                break;
        }
        return true;
    }
}
