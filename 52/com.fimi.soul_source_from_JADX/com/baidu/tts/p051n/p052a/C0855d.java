package com.baidu.tts.p051n.p052a;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.p027b.p029b.p036b.C0744b.C0743a;
import com.baidu.tts.p034l.C0825e;
import com.baidu.tts.p034l.C0826f;
import com.baidu.tts.p034l.C0827g;
import com.baidu.tts.p034l.C0829i;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p041e.C0802n.C0801a;

/* renamed from: com.baidu.tts.n.a.d */
public class C0855d extends C0851a {
    public C0855d(C0854c c0854c) {
        super(c0854c);
    }

    public TtsError m6947b() {
        TtsError h = this.a.m6937h();
        if (h != null) {
            C0802n errorEnum = h.getErrorEnum();
            if (errorEnum != null) {
                if (C0801a.f4486a.equals(errorEnum.m6751a())) {
                    m6908a(this.a.m6936g());
                }
            }
        } else {
            m6908a(this.a.m6936g());
        }
        return h;
    }

    public void m6948c() {
        this.a.m6945p();
    }

    public void m6949d() {
        this.a.m6945p();
    }

    public void m6950e() {
        this.a.m6945p();
    }

    public void m6951f() {
    }

    public int freeCustomResource(C0825e c0825e) {
        return this.a.m6945p();
    }

    public int loadCustomResource(C0825e c0825e) {
        return this.a.m6945p();
    }

    public int loadEnglishModel(C0826f c0826f) {
        return this.a.m6945p();
    }

    public int loadModel(C0827g c0827g) {
        return this.a.m6945p();
    }

    public int setAudioStreamType(int i) {
        this.a.getTtsParams().m6887e().m6481a(i);
        return 0;
    }

    public int setStereoVolume(float f, float f2) {
        C0743a e = this.a.getTtsParams().m6887e();
        e.m6486a(f);
        e.m6488b(f2);
        return 0;
    }

    public void speak(C0829i c0829i) {
        this.a.m6945p();
    }

    public void synthesize(C0829i c0829i) {
        this.a.m6945p();
    }
}
