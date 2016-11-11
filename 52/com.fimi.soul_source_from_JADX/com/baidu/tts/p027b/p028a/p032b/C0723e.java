package com.baidu.tts.p027b.p028a.p032b;

import android.text.TextUtils;
import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.auth.C0689a;
import com.baidu.tts.auth.C0693b.C0691a;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.jni.EmbeddedSynthesizerEngine;
import com.baidu.tts.jni.EmbeddedSynthesizerEngine.OnNewDataListener;
import com.baidu.tts.p034l.C0720d;
import com.baidu.tts.p034l.C0825e;
import com.baidu.tts.p034l.C0826f;
import com.baidu.tts.p034l.C0827g;
import com.baidu.tts.p034l.C0828h;
import com.baidu.tts.p034l.C0829i;
import com.baidu.tts.p041e.C0785a;
import com.baidu.tts.p041e.C0791d;
import com.baidu.tts.p041e.C0793f;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p044g.p045a.C0807c;
import com.baidu.tts.tools.DataTool;
import com.baidu.tts.tools.ResourceTools;
import com.tencent.connect.common.Constants;
import java.util.concurrent.Callable;

/* renamed from: com.baidu.tts.b.a.b.e */
public class C0723e extends C0712a {
    private C0721a f4133b;
    private long[] f4134c;

    /* renamed from: com.baidu.tts.b.a.b.e.a */
    public class C0721a extends C0720d<C0721a> {
        private String f4122a;
        private String f4123b;
        private String f4124c;
        private String f4125d;
        private String f4126e;
        private String f4127f;
        private String f4128g;
        private String f4129h;

        public C0721a() {
            this.f4122a = Constants.VIA_RESULT_SUCCESS;
            this.f4123b = Constants.VIA_RESULT_SUCCESS;
            this.f4124c = Constants.VIA_RESULT_SUCCESS;
            this.f4125d = Constants.VIA_RESULT_SUCCESS;
        }

        public int m6315a(String str) {
            if (!DataTool.isLong(str)) {
                return C0802n.TTS_PARAMETER_INVALID.m6752b();
            }
            this.f4122a = str;
            return 0;
        }

        public long m6316a() {
            long j = 0;
            try {
                j = Long.parseLong(this.f4122a);
            } catch (Exception e) {
            }
            return j;
        }

        public long m6317b() {
            long j = 0;
            try {
                j = Long.parseLong(this.f4123b);
            } catch (Exception e) {
            }
            return j;
        }

        public void m6318b(String str) {
            this.f4123b = str;
        }

        public int m6319c(String str) {
            if (!DataTool.isLong(str)) {
                return C0802n.TTS_PARAMETER_INVALID.m6752b();
            }
            this.f4124c = str;
            return 0;
        }

        public long m6320c() {
            long j = 0;
            try {
                j = Long.parseLong(this.f4124c);
            } catch (Exception e) {
            }
            return j;
        }

        public int m6321d(String str) {
            if (!DataTool.isLong(str)) {
                return C0802n.TTS_PARAMETER_INVALID.m6752b();
            }
            this.f4125d = str;
            return 0;
        }

        public long m6322d() {
            long j = 0;
            try {
                j = Long.parseLong(this.f4125d);
            } catch (Exception e) {
            }
            return j;
        }

        public String m6323e() {
            return this.f4126e;
        }

        public void m6324e(String str) {
            this.f4126e = str;
        }

        public String m6325f() {
            return this.f4127f;
        }

        public void m6326f(String str) {
            this.f4127f = str;
        }

        public String m6327g() {
            return this.f4128g;
        }

        public void m6328g(String str) {
            this.f4128g = str;
        }

        public String m6329h() {
            return this.f4129h;
        }

        public void m6330h(String str) {
            this.f4129h = str;
        }
    }

    /* renamed from: com.baidu.tts.b.a.b.e.b */
    class C0722b implements OnNewDataListener, Callable<TtsError> {
        final /* synthetic */ C0723e f4130a;
        private C0829i f4131b;
        private int f4132c;

        public C0722b(C0723e c0723e, C0829i c0829i) {
            this.f4130a = c0723e;
            this.f4132c = 0;
            this.f4131b = c0829i;
        }

        public TtsError m6331a() {
            C0691a a = C0689a.m6095a().m6102a(this.f4130a.f4133b);
            if (a == null) {
                return C0807c.m6758a().m6765b(C0802n.OFFLINE_ENGINE_AUTH_NULL);
            }
            if (!a.m6116g()) {
                return a.m6110b();
            }
            LoggerProxy.m6515d("OfflineSynthesizer", "engineResult = " + EmbeddedSynthesizerEngine.bdTTSSetParam(this.f4130a.f4134c[0], 0, 0));
            EmbeddedSynthesizerEngine.bdTTSSetParam(this.f4130a.f4134c[0], 5, this.f4130a.f4133b.m6312v());
            EmbeddedSynthesizerEngine.bdTTSSetParam(this.f4130a.f4134c[0], 6, this.f4130a.f4133b.m6313w());
            EmbeddedSynthesizerEngine.bdTTSSetParam(this.f4130a.f4134c[0], 7, this.f4130a.f4133b.m6314x());
            EmbeddedSynthesizerEngine.bdTTSSetParam(this.f4130a.f4134c[0], 17, this.f4130a.f4133b.m6316a());
            EmbeddedSynthesizerEngine.bdTTSSetParam(this.f4130a.f4134c[0], 18, this.f4130a.f4133b.m6317b());
            EmbeddedSynthesizerEngine.bdTTSSetParam(this.f4130a.f4134c[0], 19, this.f4130a.f4133b.m6320c());
            EmbeddedSynthesizerEngine.bdTTSSetParam(this.f4130a.f4134c[0], 9, this.f4130a.f4133b.m6322d());
            EmbeddedSynthesizerEngine.setOnNewDataListener(this);
            this.f4131b.m6875c(C0791d.GBK.m6737a());
            byte[] e = this.f4131b.m6878e();
            LoggerProxy.m6515d("OfflineSynthesizer", "before bdttssynthesis");
            int bdTTSSynthesis = EmbeddedSynthesizerEngine.bdTTSSynthesis(this.f4130a.f4134c[0], e, e.length);
            LoggerProxy.m6515d("OfflineSynthesizer", "after bdttssynthesis result = " + bdTTSSynthesis);
            return bdTTSSynthesis == 0 ? null : C0807c.m6758a().m6759a(C0802n.OFFLINE_ENGINE_SYNTHESIZE_ERROR, bdTTSSynthesis);
        }

        public /* synthetic */ Object call() {
            return m6331a();
        }

        public int onNewData(byte[] bArr, int i) {
            C0828h b = C0828h.m6852b(this.f4131b);
            b.m6865d(C0793f.OFFLINE.m6739a());
            b.m6856a(C0785a.PCM);
            b.m6860a(bArr);
            b.m6864c(i);
            this.f4132c++;
            if (bArr.length == 0) {
                this.f4132c = -this.f4132c;
            }
            b.m6862b(this.f4132c);
            this.f4130a.m6268a(b);
            if (!Thread.currentThread().isInterrupted()) {
                return 0;
            }
            LoggerProxy.m6515d("OfflineSynthesizer", "interrupted to interrupt syn");
            return -1;
        }
    }

    public C0723e() {
        this.f4134c = new long[1];
    }

    public int m6334a(C0825e c0825e) {
        return EmbeddedSynthesizerEngine.bdTTSDomainDataInit(ResourceTools.stringToByteArrayAddNull(c0825e.m6840a()), this.f4134c[0]);
    }

    public int m6335a(C0826f c0826f) {
        Object b = c0826f.m6844b();
        Object a = c0826f.m6842a();
        boolean isEmpty = TextUtils.isEmpty(b);
        if (TextUtils.isEmpty(a) || isEmpty) {
            return C0802n.TTS_PARAMETER_INVALID.m6752b();
        }
        int loadEnglishEngine = EmbeddedSynthesizerEngine.loadEnglishEngine(ResourceTools.stringToByteArrayAddNull(a), ResourceTools.stringToByteArrayAddNull(b), this.f4134c[0]);
        LoggerProxy.m6515d("OfflineSynthesizer", "loadEnglishModel ret=" + loadEnglishEngine);
        return loadEnglishEngine;
    }

    public int m6336a(C0827g c0827g) {
        int i = 0;
        Object b = c0827g.m6848b();
        Object a = c0827g.m6846a();
        boolean isEmpty = TextUtils.isEmpty(b);
        boolean isEmpty2 = TextUtils.isEmpty(a);
        if (isEmpty && isEmpty2) {
            return C0802n.TTS_PARAMETER_INVALID.m6752b();
        }
        int bdTTSReInitData = !isEmpty ? EmbeddedSynthesizerEngine.bdTTSReInitData(ResourceTools.stringToByteArrayAddNull(b), this.f4134c[0]) : 0;
        if (!isEmpty2) {
            i = EmbeddedSynthesizerEngine.bdTTSReInitData(ResourceTools.stringToByteArrayAddNull(a), this.f4134c[0]);
        }
        return i + bdTTSReInitData;
    }

    public TtsError m6337a() {
        if (this.f4133b == null) {
            this.f4133b = new C0721a();
        }
        C0691a a = C0689a.m6095a().m6102a(this.f4133b);
        if (!a.m6116g()) {
            return a.m6110b();
        }
        String e = this.f4133b.m6323e();
        String f = this.f4133b.m6325f();
        byte[] stringToByteArrayAddNull = ResourceTools.stringToByteArrayAddNull(e);
        byte[] stringToByteArrayAddNull2 = ResourceTools.stringToByteArrayAddNull(f);
        LoggerProxy.m6515d("OfflineSynthesizer", "before bdTTSEngineInit");
        int bdTTSEngineInit = EmbeddedSynthesizerEngine.bdTTSEngineInit(stringToByteArrayAddNull, stringToByteArrayAddNull2, this.f4134c);
        LoggerProxy.m6515d("OfflineSynthesizer", "engine init ret = " + bdTTSEngineInit);
        return bdTTSEngineInit == 0 ? null : C0807c.m6758a().m6760a(C0802n.OFFLINE_ENGINE_INIT_FAILED, bdTTSEngineInit, "bdTTSEngineInit result not 0");
    }

    public TtsError m6338a(C0829i c0829i) {
        try {
            return new C0722b(this, c0829i).m6331a();
        } catch (InterruptedException e) {
            throw e;
        } catch (Throwable e2) {
            return C0807c.m6758a().m6763a(C0802n.OFFLINE_ENGINE_CALL_EXCEPTION, e2);
        }
    }

    public <OfflineSynthesizerParams> void m6339a(OfflineSynthesizerParams offlineSynthesizerParams) {
        this.f4133b = (C0721a) offlineSynthesizerParams;
    }

    public int m6340b(C0825e c0825e) {
        return EmbeddedSynthesizerEngine.bdTTSDomainDataUninit(this.f4134c[0]);
    }

    public TtsError m6341b() {
        EmbeddedSynthesizerEngine.bdTTSEngineUninit(this.f4134c[0]);
        return null;
    }
}
