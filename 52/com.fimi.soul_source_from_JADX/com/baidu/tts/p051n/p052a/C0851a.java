package com.baidu.tts.p051n.p052a;

import android.content.Context;
import com.baidu.tts.aop.tts.ITts;
import com.baidu.tts.aop.ttslistener.TtsListener;
import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.p034l.C0831j;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.p041e.C0800m;

/* renamed from: com.baidu.tts.n.a.a */
public abstract class C0851a implements ITts {
    protected C0854c f4620a;

    public C0851a(C0854c c0854c) {
        this.f4620a = c0854c;
    }

    public void m6908a(C0851a c0851a) {
        this.f4620a.m6927a(c0851a);
    }

    public AuthInfo auth(C0800m c0800m) {
        return this.f4620a.m6930b(c0800m);
    }

    public C0800m getMode() {
        return this.f4620a.m6943n();
    }

    public TtsListener getTtsListener() {
        return this.f4620a.m6942m();
    }

    public C0831j getTtsParams() {
        return this.f4620a.m6944o();
    }

    public void setContext(Context context) {
        this.f4620a.m6921a(context);
    }

    public void setMode(C0800m c0800m) {
        this.f4620a.m6924a(c0800m);
    }

    public int setParam(C0794g c0794g, String str) {
        return this.f4620a.m6916a(c0794g, str);
    }

    public void setTtsListener(TtsListener ttsListener) {
        this.f4620a.m6923a(ttsListener);
    }
}
