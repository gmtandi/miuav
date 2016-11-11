package com.baidu.tts.p027b.p029b.p035a;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.p025i.C0699a;
import com.baidu.tts.p027b.p029b.C0677a;
import com.baidu.tts.p034l.C0828h;

/* renamed from: com.baidu.tts.b.b.a.a */
public abstract class C0731a extends C0699a implements C0730c {
    protected C0677a f4167a;
    protected volatile C0732b f4168b;

    public int m6400a(float f, float f2) {
        return this.f4168b.m6417a(f, f2);
    }

    public int m6401a(int i) {
        return this.f4168b.m6418a(i);
    }

    public C0732b m6402a() {
        return this.f4168b;
    }

    public void m6403a(C0732b c0732b) {
        this.f4168b = c0732b;
    }

    public void m6404a(C0677a c0677a) {
        this.f4168b.m6421a(c0677a);
    }

    public void m6405a(C0828h c0828h) {
        this.f4168b.m6422a(c0828h);
    }

    public <T> void m6406a(T t) {
        this.f4168b.m6423a((Object) t);
    }

    protected void m6407b(C0828h c0828h) {
        if (m6141A() && this.f4167a != null) {
            this.f4167a.m6025a(c0828h);
        }
    }

    protected void m6408c(C0828h c0828h) {
        if (m6141A() && this.f4167a != null) {
            this.f4167a.m6026b(c0828h);
        }
    }

    protected void m6409d(C0828h c0828h) {
        if (m6141A() && this.f4167a != null) {
            this.f4167a.m6027c(c0828h);
        }
    }

    protected TtsError m6410g() {
        return this.f4168b.m6424b();
    }

    protected void m6411h() {
        this.f4168b.m6419a();
    }

    protected void m6412i() {
        this.f4168b.m6425c();
    }

    protected void m6413j() {
        this.f4168b.m6426d();
    }

    protected void m6414k() {
        this.f4168b.m6427e();
    }

    protected void m6415l() {
        this.f4168b.m6428f();
    }

    public void m6416o() {
        this.f4168b.m6429o();
    }
}
