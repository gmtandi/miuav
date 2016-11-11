package com.baidu.tts.p022a.p026c;

import android.util.Log;
import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.aop.ttslistener.TtsListener;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.p027b.p028a.C0675b;
import com.baidu.tts.p027b.p028a.p031a.C0700d;
import com.baidu.tts.p027b.p029b.C0677a;
import com.baidu.tts.p027b.p029b.p035a.C0730c;
import com.baidu.tts.p034l.C0825e;
import com.baidu.tts.p034l.C0826f;
import com.baidu.tts.p034l.C0827g;
import com.baidu.tts.p034l.C0828h;
import com.baidu.tts.p034l.C0829i;
import com.baidu.tts.p034l.C0831j;
import com.baidu.tts.p041e.C0796i;

/* renamed from: com.baidu.tts.a.c.b */
public class C0680b implements C0674a {
    private C0700d f4033a;
    private C0730c f4034b;
    private TtsListener f4035c;
    private C0675b f4036d;
    private C0677a f4037e;
    private C0675b f4038f;

    /* renamed from: com.baidu.tts.a.c.b.1 */
    class C06761 implements C0675b {
        final /* synthetic */ C0680b f4030a;

        C06761(C0680b c0680b) {
            this.f4030a = c0680b;
        }

        public void m6020a(C0828h c0828h) {
            if (this.f4030a.f4035c != null) {
                this.f4030a.f4035c.onSynthesizeStart(c0828h);
            }
        }

        public void m6021b(C0828h c0828h) {
            if (this.f4030a.f4035c != null) {
                this.f4030a.f4035c.onSynthesizeFinished(c0828h);
            }
        }

        public void m6022c(C0828h c0828h) {
            if (this.f4030a.f4035c != null) {
                this.f4030a.f4035c.onSynthesizeDataArrived(c0828h);
            }
        }

        public void m6023d(C0828h c0828h) {
            if (this.f4030a.f4035c != null) {
                this.f4030a.f4035c.onError(c0828h);
            }
        }

        public void m6024e(C0828h c0828h) {
            LoggerProxy.m6515d("TtsAdapter", "onSynthesizeStop");
        }
    }

    /* renamed from: com.baidu.tts.a.c.b.2 */
    class C06782 implements C0677a {
        final /* synthetic */ C0680b f4031a;

        C06782(C0680b c0680b) {
            this.f4031a = c0680b;
        }

        public void m6028a(C0828h c0828h) {
            if (this.f4031a.f4035c != null) {
                this.f4031a.f4035c.onPlayStart(c0828h);
            }
        }

        public void m6029b(C0828h c0828h) {
            if (this.f4031a.f4035c != null) {
                this.f4031a.f4035c.onPlayProgressUpdate(c0828h);
            }
        }

        public void m6030c(C0828h c0828h) {
            if (this.f4031a.f4035c != null) {
                try {
                    this.f4031a.f4035c.onPlayFinished(c0828h);
                } catch (Exception e) {
                    Log.e("TtsAdapter", "onPlayFinished exception e=" + e.toString());
                }
            }
        }
    }

    /* renamed from: com.baidu.tts.a.c.b.3 */
    class C06793 implements C0675b {
        final /* synthetic */ C0680b f4032a;

        C06793(C0680b c0680b) {
            this.f4032a = c0680b;
        }

        public void m6031a(C0828h c0828h) {
        }

        public void m6032b(C0828h c0828h) {
        }

        public void m6033c(C0828h c0828h) {
            if (this.f4032a.m6038a(c0828h)) {
                this.f4032a.f4034b.m6397a(c0828h);
            }
        }

        public void m6034d(C0828h c0828h) {
        }

        public void m6035e(C0828h c0828h) {
        }
    }

    public C0680b(C0700d c0700d, C0730c c0730c, C0831j c0831j) {
        this.f4033a = c0700d;
        this.f4034b = c0730c;
    }

    private boolean m6038a(C0828h c0828h) {
        C0829i e = c0828h.m6867e();
        return e == null ? false : C0796i.m6745a(e.m6880g());
    }

    public int m6040a(float f, float f2) {
        return this.f4034b.m6394a(f, f2);
    }

    public int m6041a(C0825e c0825e) {
        return this.f4033a.m6159a(c0825e);
    }

    public int m6042a(C0826f c0826f) {
        return this.f4033a.m6160a(c0826f);
    }

    public int m6043a(C0827g c0827g) {
        return this.f4033a.m6161a(c0827g);
    }

    public C0730c m6044a() {
        return this.f4034b;
    }

    public void m6045a(TtsListener ttsListener) {
        this.f4035c = ttsListener;
        m6046a(this.f4033a);
        m6047a(this.f4034b);
    }

    protected void m6046a(C0700d c0700d) {
        if (this.f4036d == null) {
            this.f4036d = new C06761(this);
        }
        c0700d.m6163a(this.f4036d);
    }

    protected void m6047a(C0730c c0730c) {
        if (this.f4037e == null) {
            this.f4037e = new C06782(this);
        }
        c0730c.m6396a(this.f4037e);
    }

    public void m6048a(C0829i c0829i) {
        this.f4033a.m6164a(c0829i);
    }

    public int m6049b(C0825e c0825e) {
        return this.f4033a.m6166b(c0825e);
    }

    public TtsError m6050b() {
        TtsError b = this.f4033a.m6001b();
        this.f4034b.m6001b();
        m6056g();
        return b;
    }

    public void m6051b(C0829i c0829i) {
        this.f4034b.m6399o();
        this.f4033a.m6164a(c0829i);
    }

    public void m6052c() {
        this.f4033a.m6002c();
        this.f4034b.m6002c();
    }

    public void m6053d() {
        this.f4033a.m6003d();
        this.f4034b.m6003d();
    }

    public void m6054e() {
        LoggerProxy.m6515d("TtsAdapter", "before engine stop");
        this.f4033a.m6004e();
        LoggerProxy.m6515d("TtsAdapter", "after engine stop");
        this.f4034b.m6004e();
        LoggerProxy.m6515d("TtsAdapter", "after play stop");
    }

    public void m6055f() {
        LoggerProxy.m6515d("TtsAdapter", "before engine destroy");
        this.f4033a.m6005f();
        LoggerProxy.m6515d("TtsAdapter", "after engine destroy");
        this.f4034b.m6005f();
        LoggerProxy.m6515d("TtsAdapter", "after player destroy");
    }

    protected void m6056g() {
        this.f4038f = new C06793(this);
        this.f4033a.m6163a(this.f4038f);
    }
}
