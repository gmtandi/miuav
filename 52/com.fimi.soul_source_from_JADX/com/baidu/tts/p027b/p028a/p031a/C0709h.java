package com.baidu.tts.p027b.p028a.p031a;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.p034l.C0825e;
import com.baidu.tts.p034l.C0826f;
import com.baidu.tts.p034l.C0827g;
import com.baidu.tts.p034l.C0828h;
import com.baidu.tts.p034l.C0829i;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p044g.p045a.C0807c;

/* renamed from: com.baidu.tts.b.a.a.h */
public class C0709h extends C0702b {
    public C0709h(C0705c c0705c) {
        super(c0705c);
    }

    private void m6241a(C0828h c0828h) {
        c0828h.m6855a(C0807c.m6758a().m6765b(C0802n.OFFLINE_ENGINE_UNINITIALIZED));
        this.a.m6181e(c0828h);
    }

    public int m6242a(C0825e c0825e) {
        m6241a(new C0828h());
        return -1;
    }

    public int m6243a(C0826f c0826f) {
        m6241a(new C0828h());
        return -1;
    }

    public int m6244a(C0827g c0827g) {
        m6241a(new C0828h());
        return -1;
    }

    public void m6245a(C0829i c0829i) {
        m6241a(C0828h.m6852b(c0829i));
    }

    public int m6246b(C0825e c0825e) {
        m6241a(new C0828h());
        return -1;
    }

    public TtsError m6247b() {
        TtsError s = this.a.m6224s();
        m6192a(this.a.m6221p());
        return s;
    }
}
