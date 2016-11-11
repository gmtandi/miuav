package com.baidu.tts.p051n.p052a;

import android.content.Context;
import com.baidu.tts.aop.tts.ITts;
import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.aop.ttslistener.TtsListener;
import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.auth.C0689a;
import com.baidu.tts.p022a.p026c.C0680b;
import com.baidu.tts.p022a.p026c.C0681c;
import com.baidu.tts.p027b.p028a.C0710a;
import com.baidu.tts.p027b.p028a.p031a.C0700d;
import com.baidu.tts.p027b.p029b.p035a.C0730c;
import com.baidu.tts.p027b.p029b.p035a.C0737f;
import com.baidu.tts.p034l.C0825e;
import com.baidu.tts.p034l.C0826f;
import com.baidu.tts.p034l.C0827g;
import com.baidu.tts.p034l.C0828h;
import com.baidu.tts.p034l.C0829i;
import com.baidu.tts.p034l.C0831j;
import com.baidu.tts.p041e.C0793f;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.p041e.C0800m;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p044g.p045a.C0807c;
import com.baidu.tts.p044g.p046b.C0809b;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.baidu.tts.n.a.c */
public class C0854c implements ITts {
    static final /* synthetic */ boolean f4622a;
    private C0831j f4623b;
    private TtsListener f4624c;
    private C0800m f4625d;
    private C0681c f4626e;
    private volatile C0851a f4627f;
    private C0855d f4628g;
    private C0852b f4629h;

    /* renamed from: com.baidu.tts.n.a.c.1 */
    /* synthetic */ class C08531 {
        static final /* synthetic */ int[] f4621a;

        static {
            f4621a = new int[C0800m.values().length];
            try {
                f4621a[C0800m.ONLINE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4621a[C0800m.OFFLINE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4621a[C0800m.MIX.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    static {
        f4622a = !C0854c.class.desiredAssertionStatus();
    }

    public C0854c() {
        this.f4623b = new C0831j();
        this.f4628g = new C0855d(this);
        this.f4629h = new C0852b(this);
        this.f4627f = this.f4628g;
    }

    int m6914a(float f, float f2) {
        return this.f4626e.m6057a(f, f2);
    }

    int m6915a(int i) {
        try {
            return this.f4626e.m6071f().m6010a().m6395a(i);
        } catch (Exception e) {
            return -1;
        }
    }

    int m6916a(C0794g c0794g, String str) {
        return this.f4623b != null ? this.f4623b.m6882a(c0794g, str) : 0;
    }

    int m6917a(C0825e c0825e) {
        return this.f4626e.m6058a(c0825e);
    }

    int m6918a(C0826f c0826f) {
        return this.f4626e.m6059a(c0826f);
    }

    int m6919a(C0827g c0827g) {
        return this.f4626e.m6060a(c0827g);
    }

    public C0855d m6920a() {
        return this.f4628g;
    }

    void m6921a(Context context) {
        C0809b.m6769a().m6772a(context);
    }

    public void m6922a(TtsError ttsError) {
        m6925a(C0828h.m6851b(ttsError));
    }

    void m6923a(TtsListener ttsListener) {
        if (ttsListener != null && ttsListener != this.f4624c) {
            this.f4624c = ttsListener;
            if (this.f4626e != null) {
                this.f4626e.m6063a(this.f4624c);
            }
        }
    }

    void m6924a(C0800m c0800m) {
        this.f4625d = c0800m;
    }

    public void m6925a(C0828h c0828h) {
        if (this.f4624c != null) {
            this.f4624c.onError(c0828h);
        }
    }

    void m6926a(C0829i c0829i) {
        this.f4626e.m6064a(c0829i);
    }

    void m6927a(C0851a c0851a) {
        this.f4627f = c0851a;
    }

    public AuthInfo auth(C0800m c0800m) {
        return this.f4627f.auth(c0800m);
    }

    int m6928b(C0825e c0825e) {
        return this.f4626e.m6065b(c0825e);
    }

    public TtsError m6929b() {
        return this.f4627f.m6001b();
    }

    AuthInfo m6930b(C0800m c0800m) {
        return C0689a.m6095a().m6100a(c0800m, this.f4623b);
    }

    void m6931b(C0829i c0829i) {
        this.f4626e.m6067b(c0829i);
    }

    public void m6932c() {
        this.f4627f.m6002c();
    }

    public void m6933d() {
        this.f4627f.m6003d();
    }

    public void m6934e() {
        this.f4627f.m6004e();
    }

    public void m6935f() {
        this.f4627f.m6005f();
    }

    public int freeCustomResource(C0825e c0825e) {
        return this.f4627f.freeCustomResource(c0825e);
    }

    public C0852b m6936g() {
        return this.f4629h;
    }

    public C0800m getMode() {
        return this.f4627f.getMode();
    }

    public TtsListener getTtsListener() {
        return this.f4627f.getTtsListener();
    }

    public C0831j getTtsParams() {
        return this.f4627f.getTtsParams();
    }

    TtsError m6937h() {
        Object obj = null;
        if (this.f4625d == null) {
            this.f4625d = C0800m.ONLINE;
        }
        if (this.f4623b == null) {
            this.f4623b = new C0831j();
        }
        TtsError b = C0809b.m6769a().m6773b();
        if (f4622a || b == null) {
            C0700d a;
            switch (C08531.f4621a[this.f4625d.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    a = C0710a.m6249a().m6253a(C0793f.ONLINE);
                    obj = this.f4623b.m6885c();
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    a = C0710a.m6249a().m6253a(C0793f.OFFLINE);
                    obj = this.f4623b.m6886d();
                    break;
                case Type.BYTE /*3*/:
                    a = C0710a.m6249a().m6253a(C0793f.MIX);
                    obj = this.f4623b.m6883a();
                    break;
                default:
                    a = null;
                    break;
            }
            if (a == null || obj == null) {
                return C0807c.m6758a().m6765b(C0802n.TTS_UNINITIAL);
            }
            a.m6165a(obj);
            C0730c c0737f = new C0737f();
            c0737f.m6398a(this.f4623b.m6884b());
            this.f4626e = new C0681c();
            this.f4626e.m6062a(new C0680b(a, c0737f, this.f4623b));
            if (this.f4624c != null) {
                this.f4626e.m6063a(this.f4624c);
            }
            return this.f4626e.m6061a();
        }
        throw new AssertionError();
    }

    void m6938i() {
        this.f4626e.m6066b();
    }

    void m6939j() {
        this.f4626e.m6068c();
    }

    void m6940k() {
        this.f4626e.m6069d();
    }

    void m6941l() {
        this.f4626e.m6070e();
        C0689a.m6095a().m6104b();
        C0809b.m6769a().m6777f();
        this.f4623b = new C0831j();
    }

    public int loadCustomResource(C0825e c0825e) {
        return this.f4627f.loadCustomResource(c0825e);
    }

    public int loadEnglishModel(C0826f c0826f) {
        return this.f4627f.loadEnglishModel(c0826f);
    }

    public int loadModel(C0827g c0827g) {
        return this.f4627f.loadModel(c0827g);
    }

    TtsListener m6942m() {
        return this.f4624c;
    }

    C0800m m6943n() {
        return this.f4625d;
    }

    C0831j m6944o() {
        return this.f4623b;
    }

    public int m6945p() {
        if (this.f4624c == null) {
            throw new IllegalStateException(C0802n.TTS_UNINITIAL.m6753c());
        }
        m6922a(C0807c.m6758a().m6765b(C0802n.TTS_UNINITIAL));
        return -1;
    }

    public boolean m6946q() {
        return this.f4629h == this.f4627f;
    }

    public int setAudioStreamType(int i) {
        return this.f4627f.setAudioStreamType(i);
    }

    public void setContext(Context context) {
        this.f4627f.setContext(context);
    }

    public void setMode(C0800m c0800m) {
        this.f4627f.setMode(c0800m);
    }

    public int setParam(C0794g c0794g, String str) {
        return this.f4627f.setParam(c0794g, str);
    }

    public int setStereoVolume(float f, float f2) {
        return this.f4627f.setStereoVolume(f, f2);
    }

    public void setTtsListener(TtsListener ttsListener) {
        this.f4627f.setTtsListener(ttsListener);
    }

    public void speak(C0829i c0829i) {
        this.f4627f.speak(c0829i);
    }

    public void synthesize(C0829i c0829i) {
        this.f4627f.synthesize(c0829i);
    }
}
