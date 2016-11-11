package com.baidu.tts.p027b.p028a.p032b;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.p027b.p028a.C0675b;
import com.baidu.tts.p027b.p028a.p032b.C0723e.C0721a;
import com.baidu.tts.p034l.C0824b;
import com.baidu.tts.p034l.C0825e;
import com.baidu.tts.p034l.C0826f;
import com.baidu.tts.p034l.C0827g;
import com.baidu.tts.p034l.C0828h;
import com.baidu.tts.p034l.C0829i;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p044g.p045a.C0807c;
import it.p074a.p075a.C2799f;

/* renamed from: com.baidu.tts.b.a.b.d */
public class C0717d extends C0712a {
    private C0824b f4110b;
    private C0727f f4111c;
    private C0723e f4112d;
    private C0714c f4113e;
    private TtsError f4114f;
    private int f4115g;
    private int f4116h;

    /* renamed from: com.baidu.tts.b.a.b.d.1 */
    class C07151 implements C0675b {
        final /* synthetic */ C0717d f4108a;

        C07151(C0717d c0717d) {
            this.f4108a = c0717d;
        }

        public void m6278a(C0828h c0828h) {
        }

        public void m6279b(C0828h c0828h) {
        }

        public void m6280c(C0828h c0828h) {
            this.f4108a.f4116h = c0828h.m6861b();
            this.f4108a.f4115g = c0828h.m6863c();
            this.f4108a.m6268a(c0828h);
        }

        public void m6281d(C0828h c0828h) {
        }

        public void m6282e(C0828h c0828h) {
        }
    }

    /* renamed from: com.baidu.tts.b.a.b.d.2 */
    class C07162 implements C0675b {
        final /* synthetic */ C0717d f4109a;

        C07162(C0717d c0717d) {
            this.f4109a = c0717d;
        }

        public void m6283a(C0828h c0828h) {
        }

        public void m6284b(C0828h c0828h) {
        }

        public void m6285c(C0828h c0828h) {
            this.f4109a.m6268a(this.f4109a.m6291b(c0828h));
        }

        public void m6286d(C0828h c0828h) {
        }

        public void m6287e(C0828h c0828h) {
        }
    }

    public C0717d() {
        this.f4115g = 0;
        this.f4116h = 0;
        this.f4111c = new C0727f();
        this.f4112d = new C0723e();
        this.f4113e = new C0714c();
    }

    private C0828h m6291b(C0828h c0828h) {
        int b = c0828h.m6861b();
        c0828h.m6862b(b >= 0 ? b + this.f4116h : b - this.f4116h);
        c0828h.m6864c(c0828h.m6863c() + this.f4115g);
        return c0828h;
    }

    public int m6292a(C0825e c0825e) {
        return this.f4112d.m6334a(c0825e);
    }

    public int m6293a(C0826f c0826f) {
        return this.f4112d.m6335a(c0826f);
    }

    public int m6294a(C0827g c0827g) {
        if (this.f4114f == null) {
            return this.f4112d.m6336a(c0827g);
        }
        String a = c0827g.m6846a();
        String b = c0827g.m6848b();
        C0721a b2 = this.f4110b.m6836b();
        b2.m6324e(a);
        b2.m6326f(b);
        this.f4114f = this.f4112d.m6337a();
        return this.f4114f == null ? 0 : this.f4114f.getDetailCode();
    }

    public TtsError m6295a() {
        this.f4111c.m6267a(new C07151(this));
        this.f4112d.m6267a(new C07162(this));
        this.f4111c.m6265a();
        this.f4114f = this.f4112d.m6337a();
        return this.f4114f != null ? C0807c.m6758a().m6765b(C0802n.MIX_ENGINE_OFFLINE_INIT_FAILURE) : null;
    }

    public TtsError m6296a(C0829i c0829i) {
        this.f4116h = 0;
        this.f4115g = 0;
        if (!this.f4113e.m6277a()) {
            return this.f4112d.m6338a(c0829i);
        }
        TtsError a = this.f4111c.m6382a(c0829i);
        if (a == null) {
            return a;
        }
        LoggerProxy.m6515d("MixSynthesizer", "online synthesize ttserror=" + a.getDetailMessage());
        c0829i.m6873b(c0829i.m6874c().substring(this.f4115g));
        return this.f4112d.m6338a(c0829i);
    }

    public <AllSynthesizerParams> void m6297a(AllSynthesizerParams allSynthesizerParams) {
        this.f4110b = (C0824b) allSynthesizerParams;
        Object a = this.f4110b.m6833a();
        a.m6346a(3);
        a.m6350b((int) C2799f.f14263a);
        this.f4111c.m6383a(a);
        this.f4112d.m6339a(this.f4110b.m6836b());
        this.f4113e.m6276a(this.f4110b);
    }

    public int m6298b(C0825e c0825e) {
        return this.f4112d.m6340b(c0825e);
    }

    public TtsError m6299b() {
        this.f4111c.m6271b();
        this.f4112d.m6341b();
        this.f4113e.m6276a(null);
        return null;
    }
}
