package com.baidu.tts.p044g.p045a;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.p041e.C0802n;
import java.util.Hashtable;

/* renamed from: com.baidu.tts.g.a.c */
public class C0807c {
    private static volatile C0807c f4555a;
    private Hashtable<C0802n, C0806b> f4556b;

    static {
        f4555a = null;
    }

    private C0807c() {
        this.f4556b = new Hashtable();
    }

    public static C0807c m6758a() {
        if (f4555a == null) {
            synchronized (C0807c.class) {
                if (f4555a == null) {
                    f4555a = new C0807c();
                }
            }
        }
        return f4555a;
    }

    public TtsError m6759a(C0802n c0802n, int i) {
        return m6760a(c0802n, i, null);
    }

    public TtsError m6760a(C0802n c0802n, int i, String str) {
        return m6761a(c0802n, i, str, null);
    }

    public TtsError m6761a(C0802n c0802n, int i, String str, Throwable th) {
        TtsError b = m6765b(c0802n);
        b.setCode(i);
        b.setMessage(str);
        b.setThrowable(th);
        return b;
    }

    public TtsError m6762a(C0802n c0802n, String str) {
        return m6760a(c0802n, 0, str);
    }

    public TtsError m6763a(C0802n c0802n, Throwable th) {
        TtsError b = m6765b(c0802n);
        b.setThrowable(th);
        return b;
    }

    public C0806b m6764a(C0802n c0802n) {
        C0806b c0806b = (C0806b) this.f4556b.get(c0802n);
        if (c0806b != null) {
            return c0806b;
        }
        c0806b = new C0806b(c0802n);
        this.f4556b.put(c0802n, c0806b);
        return c0806b;
    }

    public TtsError m6765b(C0802n c0802n) {
        C0806b a = m6764a(c0802n);
        TtsError ttsError = new TtsError();
        ttsError.setTtsErrorFlyweight(a);
        return ttsError;
    }
}
