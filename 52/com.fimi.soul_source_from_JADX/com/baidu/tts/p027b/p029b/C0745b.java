package com.baidu.tts.p027b.p029b;

import com.baidu.tts.p027b.p029b.p036b.C0740c;
import com.baidu.tts.p027b.p029b.p036b.C0744b;

/* renamed from: com.baidu.tts.b.b.b */
public class C0745b {
    private static volatile C0745b f4192a;

    static {
        f4192a = null;
    }

    private C0745b() {
    }

    public static C0745b m6510a() {
        if (f4192a == null) {
            synchronized (C0745b.class) {
                if (f4192a == null) {
                    f4192a = new C0745b();
                }
            }
        }
        return f4192a;
    }

    private C0744b m6511c() {
        return new C0744b();
    }

    public C0740c m6512b() {
        return m6511c();
    }
}
