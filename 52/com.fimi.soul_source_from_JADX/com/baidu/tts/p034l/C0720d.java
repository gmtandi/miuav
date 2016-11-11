package com.baidu.tts.p034l;

import com.baidu.tts.p033m.C0719a;
import com.baidu.tts.p041e.C0791d;
import com.baidu.tts.p041e.C0795h;
import com.tencent.connect.common.Constants;

/* renamed from: com.baidu.tts.l.d */
public class C0720d<T> extends C0719a<T> {
    private String f4117a;
    private String f4118b;
    private String f4119c;
    private C0795h f4120d;
    private C0791d f4121e;

    public C0720d() {
        this.f4117a = Constants.VIA_SHARE_TYPE_TEXT;
        this.f4118b = Constants.VIA_SHARE_TYPE_TEXT;
        this.f4119c = Constants.VIA_SHARE_TYPE_TEXT;
        this.f4120d = C0795h.ZH;
        this.f4121e = C0791d.UTF8;
    }

    public void m6301a(C0791d c0791d) {
        this.f4121e = c0791d;
    }

    public void m6302i(String str) {
        this.f4120d = C0795h.m6743a(str);
    }

    public void m6303j(String str) {
        this.f4117a = str;
    }

    public void m6304k(String str) {
        this.f4118b = str;
    }

    public void m6305l(String str) {
        this.f4119c = str;
    }

    public String m6306p() {
        return this.f4121e.m6737a();
    }

    public String m6307q() {
        return this.f4121e.m6738b();
    }

    public String m6308r() {
        return this.f4120d.m6744a();
    }

    public String m6309s() {
        return this.f4117a;
    }

    public String m6310t() {
        return this.f4118b;
    }

    public String m6311u() {
        return this.f4119c;
    }

    public long m6312v() {
        return Long.parseLong(this.f4119c);
    }

    public long m6313w() {
        return Long.parseLong(this.f4117a);
    }

    public long m6314x() {
        return Long.parseLong(this.f4118b);
    }
}
