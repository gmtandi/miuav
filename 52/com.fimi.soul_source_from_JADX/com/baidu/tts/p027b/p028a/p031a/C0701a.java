package com.baidu.tts.p027b.p028a.p031a;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.p025i.C0699a;
import com.baidu.tts.p027b.p028a.C0675b;
import com.baidu.tts.p027b.p028a.p032b.C0711b;
import com.baidu.tts.p034l.C0825e;
import com.baidu.tts.p034l.C0826f;
import com.baidu.tts.p034l.C0827g;
import com.baidu.tts.p034l.C0828h;
import com.baidu.tts.p034l.C0829i;
import com.baidu.tts.p041e.C0792e;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.baidu.tts.b.a.a.a */
public abstract class C0701a extends C0699a implements C0700d {
    protected C0711b f4091a;
    protected List<C0675b> f4092b;
    protected volatile C0702b f4093c;

    public C0701a() {
        this.f4092b = new ArrayList();
    }

    public int m6167a(C0825e c0825e) {
        return this.f4093c.m6188a(c0825e);
    }

    public int m6168a(C0826f c0826f) {
        return this.f4093c.m6189a(c0826f);
    }

    public int m6169a(C0827g c0827g) {
        return this.f4093c.m6190a(c0827g);
    }

    public C0702b m6170a() {
        return this.f4093c;
    }

    public void m6171a(C0702b c0702b) {
        this.f4093c = c0702b;
    }

    public void m6172a(C0711b c0711b) {
        this.f4093c.m6193a(c0711b);
    }

    public void m6173a(C0675b c0675b) {
        this.f4093c.m6194a(c0675b);
    }

    void m6174a(C0828h c0828h) {
        if (m6141A()) {
            if (c0828h == null) {
                c0828h = new C0828h();
            }
            c0828h.m6857a(C0792e.SYN_START);
            if (this.f4092b != null) {
                for (C0675b c0675b : this.f4092b) {
                    if (c0675b != null) {
                        c0675b.m6015a(c0828h);
                    }
                }
            }
        }
    }

    public void m6175a(C0829i c0829i) {
        this.f4093c.m6195a(c0829i);
    }

    public void m6176a(Object obj) {
        this.f4093c.m6196a(obj);
    }

    public int m6177b(C0825e c0825e) {
        return this.f4093c.m6197b(c0825e);
    }

    void m6178b(C0828h c0828h) {
        if (m6141A()) {
            if (c0828h == null) {
                c0828h = new C0828h();
            }
            c0828h.m6857a(C0792e.SYN_DATA);
            if (this.f4092b != null) {
                for (C0675b c0675b : this.f4092b) {
                    if (c0675b != null) {
                        c0675b.m6017c(c0828h);
                    }
                }
            }
        }
    }

    void m6179c(C0828h c0828h) {
        if (m6141A()) {
            if (c0828h == null) {
                c0828h = new C0828h();
            }
            c0828h.m6857a(C0792e.SYN_FINISH);
            if (this.f4092b != null) {
                for (C0675b c0675b : this.f4092b) {
                    if (c0675b != null) {
                        c0675b.m6016b(c0828h);
                    }
                }
            }
        }
    }

    void m6180d(C0828h c0828h) {
        if (c0828h == null) {
            c0828h = new C0828h();
        }
        c0828h.m6857a(C0792e.SYN_STOP);
        if (this.f4092b != null) {
            for (C0675b c0675b : this.f4092b) {
                if (c0675b != null) {
                    c0675b.m6019e(c0828h);
                }
            }
        }
    }

    void m6181e(C0828h c0828h) {
        if (c0828h == null) {
            c0828h = new C0828h();
        }
        c0828h.m6857a(C0792e.SYN_ERROR);
        if (this.f4092b != null) {
            for (C0675b c0675b : this.f4092b) {
                if (c0675b != null) {
                    c0675b.m6018d(c0828h);
                }
            }
        }
    }

    protected TtsError m6182g() {
        return this.f4093c.m6198b();
    }

    protected void m6183h() {
        this.f4093c.m6191a();
    }

    protected void m6184i() {
        this.f4093c.m6199c();
    }

    protected void m6185j() {
        this.f4093c.m6200d();
    }

    protected void m6186k() {
        this.f4093c.m6201e();
    }

    protected void m6187l() {
        this.f4093c.m6202f();
    }
}
