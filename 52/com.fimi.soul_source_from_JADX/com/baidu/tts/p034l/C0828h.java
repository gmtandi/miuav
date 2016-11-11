package com.baidu.tts.p034l;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.p033m.C0719a;
import com.baidu.tts.p041e.C0785a;
import com.baidu.tts.p041e.C0792e;

/* renamed from: com.baidu.tts.l.h */
public class C0828h extends C0719a<C0828h> {
    private C0792e f4602a;
    private int f4603b;
    private int f4604c;
    private String f4605d;
    private int f4606e;
    private int f4607f;
    private byte[] f4608g;
    private C0785a f4609h;
    private C0829i f4610i;
    private TtsError f4611j;

    public static C0828h m6850a(C0829i c0829i, TtsError ttsError) {
        C0828h b = C0828h.m6852b(c0829i);
        b.m6855a(ttsError);
        return b;
    }

    public static C0828h m6851b(TtsError ttsError) {
        C0828h c0828h = new C0828h();
        c0828h.m6855a(ttsError);
        return c0828h;
    }

    public static C0828h m6852b(C0829i c0829i) {
        C0828h c0828h = new C0828h();
        c0828h.m6858a(c0829i);
        return c0828h;
    }

    public int m6853a() {
        return this.f4604c;
    }

    public void m6854a(int i) {
        this.f4604c = i;
    }

    public void m6855a(TtsError ttsError) {
        this.f4611j = ttsError;
    }

    public void m6856a(C0785a c0785a) {
        this.f4609h = c0785a;
    }

    public void m6857a(C0792e c0792e) {
        this.f4602a = c0792e;
    }

    public void m6858a(C0829i c0829i) {
        this.f4610i = c0829i;
    }

    public void m6859a(String str) {
        this.f4605d = str;
    }

    public void m6860a(byte[] bArr) {
        this.f4608g = bArr;
    }

    public int m6861b() {
        return this.f4606e;
    }

    public void m6862b(int i) {
        this.f4606e = i;
    }

    public int m6863c() {
        return this.f4607f;
    }

    public void m6864c(int i) {
        this.f4607f = i;
    }

    public void m6865d(int i) {
        this.f4603b = i;
    }

    public byte[] m6866d() {
        return this.f4608g;
    }

    public C0829i m6867e() {
        return this.f4610i;
    }

    public TtsError m6868f() {
        return this.f4611j;
    }
}
