package com.baidu.tts.p051n.p052a;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.p034l.C0825e;
import com.baidu.tts.p034l.C0826f;
import com.baidu.tts.p034l.C0827g;
import com.baidu.tts.p034l.C0829i;

/* renamed from: com.baidu.tts.n.a.b */
public class C0852b extends C0851a {
    public C0852b(C0854c c0854c) {
        super(c0854c);
    }

    public TtsError m6909b() {
        return null;
    }

    public void m6910c() {
        this.a.m6938i();
    }

    public void m6911d() {
        this.a.m6939j();
    }

    public void m6912e() {
        this.a.m6940k();
    }

    public void m6913f() {
        this.a.m6941l();
        m6908a(this.a.m6920a());
    }

    public int freeCustomResource(C0825e c0825e) {
        return this.a.m6928b(c0825e);
    }

    public int loadCustomResource(C0825e c0825e) {
        return this.a.m6917a(c0825e);
    }

    public int loadEnglishModel(C0826f c0826f) {
        return this.a.m6918a(c0826f);
    }

    public int loadModel(C0827g c0827g) {
        return this.a.m6919a(c0827g);
    }

    public int setAudioStreamType(int i) {
        return this.a.m6915a(i);
    }

    public int setStereoVolume(float f, float f2) {
        return this.a.m6914a(f, f2);
    }

    public void speak(C0829i c0829i) {
        this.a.m6926a(c0829i);
    }

    public void synthesize(C0829i c0829i) {
        this.a.m6931b(c0829i);
    }
}
