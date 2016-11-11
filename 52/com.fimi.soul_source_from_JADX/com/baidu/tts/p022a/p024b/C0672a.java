package com.baidu.tts.p022a.p024b;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.baidu.tts.aop.tts.ITts;
import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.aop.tts.TtsFactory;
import com.baidu.tts.aop.ttslistener.TtsListener;
import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizeBag;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.baidu.tts.p034l.C0825e;
import com.baidu.tts.p034l.C0826f;
import com.baidu.tts.p034l.C0827g;
import com.baidu.tts.p034l.C0828h;
import com.baidu.tts.p034l.C0829i;
import com.baidu.tts.p037c.C0747a;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.p041e.C0796i;
import com.baidu.tts.p041e.C0799l;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p044g.p046b.C0809b;
import com.baidu.tts.tools.ResourceTools;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.baidu.tts.a.b.a */
public class C0672a {
    private ITts f4026a;
    private SpeechSynthesizerListener f4027b;
    private ThreadPoolExecutor f4028c;
    private TtsListener f4029d;

    /* renamed from: com.baidu.tts.a.b.a.1 */
    class C06671 implements TtsListener {
        final /* synthetic */ C0672a f4016a;

        C06671(C0672a c0672a) {
            this.f4016a = c0672a;
        }

        private boolean m5967a(C0828h c0828h) {
            try {
                switch (C06682.f4017a[c0828h.m6868f().getTtsErrorFlyweight().m6755a().ordinal()]) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    case Type.BYTE /*3*/:
                        return true;
                    default:
                        return false;
                }
            } catch (Exception e) {
                LoggerProxy.m6515d("SpeechSynthesizerMachine", "isStopped exception=" + e.toString());
                return false;
            }
        }

        public void onError(C0828h c0828h) {
            if (this.f4016a.f4027b != null && !m5967a(c0828h)) {
                this.f4016a.f4027b.onError(this.f4016a.m5975a(c0828h), this.f4016a.m5977b(c0828h));
            }
        }

        public void onPlayFinished(C0828h c0828h) {
            if (this.f4016a.f4027b != null) {
                this.f4016a.f4027b.onSpeechFinish(this.f4016a.m5975a(c0828h));
            }
        }

        public void onPlayProgressUpdate(C0828h c0828h) {
            if (this.f4016a.f4027b != null) {
                this.f4016a.f4027b.onSpeechProgressChanged(this.f4016a.m5975a(c0828h), c0828h.m6863c());
            }
        }

        public void onPlayStart(C0828h c0828h) {
            if (this.f4016a.f4027b != null) {
                this.f4016a.f4027b.onSpeechStart(this.f4016a.m5975a(c0828h));
            }
        }

        public void onSynthesizeDataArrived(C0828h c0828h) {
            if (this.f4016a.f4027b != null) {
                this.f4016a.f4027b.onSynthesizeDataArrived(this.f4016a.m5975a(c0828h), c0828h.m6866d(), c0828h.m6863c());
            }
        }

        public void onSynthesizeFinished(C0828h c0828h) {
            if (this.f4016a.f4027b != null) {
                this.f4016a.f4027b.onSynthesizeFinish(this.f4016a.m5975a(c0828h));
            }
        }

        public void onSynthesizeStart(C0828h c0828h) {
            if (this.f4016a.f4027b != null) {
                this.f4016a.f4027b.onSynthesizeStart(this.f4016a.m5975a(c0828h));
            }
        }
    }

    /* renamed from: com.baidu.tts.a.b.a.2 */
    /* synthetic */ class C06682 {
        static final /* synthetic */ int[] f4017a;

        static {
            f4017a = new int[C0802n.values().length];
            try {
                f4017a[C0802n.MIX_AUTH_INTERRUPTED_EXCEPTION.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4017a[C0802n.OFFLINE_AUTH_INTERRUPTED_EXCEPTION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4017a[C0802n.ONLINE_AUTH_INTERRUPTED_EXCEPTION.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* renamed from: com.baidu.tts.a.b.a.a */
    class C0669a implements Callable<Void> {
        List<SpeechSynthesizeBag> f4018a;
        final /* synthetic */ C0672a f4019b;

        public C0669a(C0672a c0672a, List<SpeechSynthesizeBag> list) {
            this.f4019b = c0672a;
            this.f4018a = list;
        }

        public Void m5968a() {
            if (this.f4018a != null) {
                int size = this.f4018a.size();
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        SpeechSynthesizeBag speechSynthesizeBag = (SpeechSynthesizeBag) this.f4018a.get(i);
                        if (speechSynthesizeBag != null) {
                            String text = speechSynthesizeBag.getText();
                            CharSequence utteranceId = speechSynthesizeBag.getUtteranceId();
                            if (TextUtils.isEmpty(utteranceId)) {
                                utteranceId = String.valueOf(i);
                                speechSynthesizeBag.setUtteranceId(utteranceId);
                            }
                            CharSequence charSequence = utteranceId;
                            if (Thread.currentThread().isInterrupted()) {
                                break;
                            }
                            C0829i c0829i = new C0829i(text, charSequence);
                            c0829i.m6870a(C0796i.SPEAK);
                            this.f4019b.f4026a.speak(c0829i);
                        }
                    }
                }
            }
            return null;
        }

        public /* synthetic */ Object call() {
            return m5968a();
        }
    }

    /* renamed from: com.baidu.tts.a.b.a.b */
    class C0670b implements Callable<Void> {
        final /* synthetic */ C0672a f4020a;
        private String f4021b;
        private String f4022c;

        public C0670b(C0672a c0672a, String str, String str2) {
            this.f4020a = c0672a;
            this.f4021b = str;
            this.f4022c = str2;
        }

        public Void m5969a() {
            C0829i c0829i = new C0829i(this.f4021b, this.f4022c);
            c0829i.m6870a(C0796i.SPEAK);
            this.f4020a.f4026a.speak(c0829i);
            return null;
        }

        public /* synthetic */ Object call() {
            return m5969a();
        }
    }

    /* renamed from: com.baidu.tts.a.b.a.c */
    class C0671c implements Callable<Void> {
        final /* synthetic */ C0672a f4023a;
        private String f4024b;
        private String f4025c;

        public C0671c(C0672a c0672a, String str, String str2) {
            this.f4023a = c0672a;
            this.f4024b = str;
            this.f4025c = str2;
        }

        public Void m5970a() {
            C0829i c0829i = new C0829i(this.f4024b, this.f4025c);
            c0829i.m6870a(C0796i.SYNTHESIZE);
            this.f4023a.f4026a.synthesize(c0829i);
            return null;
        }

        public /* synthetic */ Object call() {
            return m5970a();
        }
    }

    public C0672a() {
        this.f4029d = new C06671(this);
        this.f4026a = m5979g();
        this.f4026a.setTtsListener(this.f4029d);
    }

    private int m5971a(String str, Callable<Void> callable) {
        C0802n isTextValid = ResourceTools.isTextValid(str);
        return isTextValid == null ? m5972a((Callable) callable) : isTextValid.m6752b();
    }

    private int m5972a(Callable<Void> callable) {
        try {
            m5980h().submit(callable);
            return 0;
        } catch (RejectedExecutionException e) {
            return SpeechSynthesizer.ERROR_QUEUE_IS_FULL;
        }
    }

    private String m5975a(C0828h c0828h) {
        if (c0828h != null) {
            C0829i e = c0828h.m6867e();
            if (e != null) {
                return e.m6879f();
            }
        }
        LoggerProxy.m6515d("SpeechSynthesizerMachine", "getUtteranceId null");
        return null;
    }

    private SpeechError m5977b(C0828h c0828h) {
        SpeechError speechError;
        if (c0828h != null) {
            TtsError f = c0828h.m6868f();
            if (f != null) {
                int detailCode = f.getDetailCode();
                String detailMessage = f.getDetailMessage();
                speechError = new SpeechError();
                speechError.code = detailCode;
                speechError.description = detailMessage;
                return speechError;
            }
            LoggerProxy.m6515d("SpeechSynthesizerMachine", "ttsError is null");
        }
        speechError = new SpeechError();
        speechError.code = C0802n.TTS_ERROR_UNKNOW.m6752b();
        speechError.description = C0802n.TTS_ERROR_UNKNOW.m6753c();
        return speechError;
    }

    private ITts m5979g() {
        return (ITts) new TtsFactory().makeProxy();
    }

    private synchronized ExecutorService m5980h() {
        if (this.f4028c == null) {
            this.f4028c = new C0747a((int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER, "SpeechSynthesizerPoolThread", new AbortPolicy());
        }
        return this.f4028c;
    }

    private void m5981i() {
        if (this.f4028c != null) {
            if (!this.f4028c.isShutdown()) {
                this.f4028c.shutdownNow();
            }
            try {
                LoggerProxy.m6515d("SpeechSynthesizerMachine", "isTerminated=" + this.f4028c.awaitTermination(C0799l.DEFAULT.m6747a(), TimeUnit.MILLISECONDS));
            } catch (InterruptedException e) {
                LoggerProxy.m6515d("SpeechSynthesizerMachine", "InterruptedException");
            }
            this.f4028c = null;
        }
    }

    public int m5982a(float f, float f2) {
        return this.f4026a.setStereoVolume(f, f2);
    }

    public int m5983a(int i) {
        return this.f4026a.setAudioStreamType(i);
    }

    public int m5984a(String str) {
        C0825e c0825e = new C0825e();
        c0825e.m6841a(str);
        return this.f4026a.loadCustomResource(c0825e);
    }

    public int m5985a(String str, String str2) {
        try {
            return this.f4026a.setParam(C0794g.valueOf(str), str2);
        } catch (Exception e) {
            return C0802n.TTS_PARAMETER_INVALID.m6752b();
        }
    }

    public int m5986a(String str, String str2, Bundle bundle) {
        return m5971a(str, new C0670b(this, str, str2));
    }

    public int m5987a(List<SpeechSynthesizeBag> list) {
        return list.size() <= 100 ? m5972a(new C0669a(this, list)) : SpeechSynthesizer.ERROR_LIST_IS_TOO_LONG;
    }

    public TtsError m5988a(TtsMode ttsMode) {
        this.f4026a.setMode(ttsMode.getTtsEnum());
        return this.f4026a.m6001b();
    }

    public String m5989a() {
        return C0809b.m6769a().m6781j();
    }

    public void m5990a(Context context) {
        this.f4026a.setContext(context);
    }

    public void m5991a(SpeechSynthesizerListener speechSynthesizerListener) {
        if (this.f4027b != speechSynthesizerListener) {
            this.f4027b = speechSynthesizerListener;
        }
    }

    public int m5992b() {
        if (this.f4026a != null) {
            this.f4026a.m6003d();
        }
        return 0;
    }

    public int m5993b(String str, String str2) {
        C0827g c0827g = new C0827g();
        c0827g.m6849b(str);
        c0827g.m6847a(str2);
        return this.f4026a.loadModel(c0827g);
    }

    public int m5994b(String str, String str2, Bundle bundle) {
        return m5971a(str, new C0671c(this, str, str2));
    }

    public AuthInfo m5995b(TtsMode ttsMode) {
        return this.f4026a.auth(ttsMode.getTtsEnum());
    }

    public int m5996c() {
        if (this.f4026a != null) {
            this.f4026a.m6002c();
        }
        return 0;
    }

    public int m5997c(String str, String str2) {
        C0826f c0826f = new C0826f();
        c0826f.m6843a(str);
        c0826f.m6845b(str2);
        return this.f4026a.loadEnglishModel(c0826f);
    }

    public int m5998d() {
        m5981i();
        if (this.f4026a != null) {
            this.f4026a.m6004e();
        }
        return 0;
    }

    public int m5999e() {
        m5981i();
        if (this.f4026a != null) {
            this.f4026a.m6005f();
            this.f4026a = null;
        }
        return 0;
    }

    public int m6000f() {
        return this.f4026a.freeCustomResource(null);
    }
}
