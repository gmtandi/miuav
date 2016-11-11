package com.baidu.tts.p027b.p028a.p032b;

import android.text.TextUtils;
import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.auth.C0689a;
import com.baidu.tts.auth.C0697c.C0694a;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.loopj.ResponseHandlerInterface;
import com.baidu.tts.loopj.SyncHttpClient;
import com.baidu.tts.p034l.C0720d;
import com.baidu.tts.p034l.C0825e;
import com.baidu.tts.p034l.C0826f;
import com.baidu.tts.p034l.C0827g;
import com.baidu.tts.p034l.C0828h;
import com.baidu.tts.p034l.C0829i;
import com.baidu.tts.p041e.C0786b;
import com.baidu.tts.p041e.C0790c;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.p041e.C0799l;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p041e.C0803o;
import com.baidu.tts.p044g.p045a.C0807c;
import com.baidu.tts.p044g.p046b.C0809b;
import com.baidu.tts.p053o.C0856a;
import com.baidu.tts.tools.CommonUtility;
import com.tencent.connect.common.Constants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

/* renamed from: com.baidu.tts.b.a.b.f */
public class C0727f extends C0712a {
    private C0725b f4161b;

    /* renamed from: com.baidu.tts.b.a.b.f.a */
    class C0724a implements Callable<C0828h> {
        final /* synthetic */ C0727f f4135a;
        private int f4136b;
        private String f4137c;
        private C0829i f4138d;
        private C0725b f4139e;
        private C0828h f4140f;
        private SyncHttpClient f4141g;

        public C0724a(C0727f c0727f, int i, String str, C0829i c0829i, C0725b c0725b, C0828h c0828h) {
            this.f4135a = c0727f;
            this.f4136b = i;
            this.f4137c = str;
            this.f4138d = c0829i;
            this.f4139e = c0725b;
            this.f4140f = c0828h;
        }

        public C0828h m6342a() {
            try {
                HttpEntity a = this.f4135a.m6377a(this.f4136b, this.f4137c, this.f4138d, this.f4139e);
                this.f4141g = new SyncHttpClient();
                this.f4141g.setMaxRetriesAndTimeout(this.f4139e.m6369m(), this.f4139e.m6370n());
                int o = this.f4139e.m6371o();
                LoggerProxy.m6515d("OnlineSynthesizer", "timeout=" + o);
                this.f4141g.setTimeout(o);
                ResponseHandlerInterface c0729h = new C0729h(this.f4140f);
                c0729h.m6393a(this.f4139e);
                try {
                    if (!Thread.currentThread().isInterrupted()) {
                        LoggerProxy.m6515d("OnlineSynthesizer", "before post");
                        this.f4141g.post(null, C0803o.TTS_SERVER.m6754a(), a, null, c0729h);
                        LoggerProxy.m6515d("OnlineSynthesizer", "after post");
                    }
                } catch (Exception e) {
                    LoggerProxy.m6516e("OnlineSynthesizer", "loopj exception = " + e.getMessage());
                }
                return this.f4140f;
            } catch (C0856a e2) {
                this.f4140f.m6855a(C0807c.m6758a().m6765b(C0802n.ONLINE_TOKEN_IS_NULL));
                return this.f4140f;
            }
        }

        public void m6343b() {
            if (this.f4141g != null) {
                this.f4141g.stop();
            }
        }

        public /* synthetic */ Object call() {
            return m6342a();
        }
    }

    /* renamed from: com.baidu.tts.b.a.b.f.b */
    public class C0725b extends C0720d<C0725b> {
        private static Set<String> f4142p;
        private String f4143a;
        private C0786b f4144b;
        private C0790c f4145c;
        private String f4146d;
        private String f4147e;
        private String f4148f;
        private String f4149g;
        private String f4150h;
        private String f4151i;
        private String f4152j;
        private String f4153k;
        private String f4154l;
        private int f4155m;
        private int f4156n;
        private int f4157o;

        static {
            f4142p = new HashSet();
            f4142p.add(C0794g.SPEED.m6742b());
        }

        public C0725b() {
            this.f4144b = C0786b.AMR;
            this.f4145c = C0790c.AMR_15K85;
            this.f4146d = Constants.VIA_RESULT_SUCCESS;
            this.f4155m = 5;
            this.f4156n = XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
            this.f4157o = C0799l.DEFAULT.m6748b();
        }

        public int m6344a(C0786b c0786b) {
            if (c0786b == null) {
                return C0802n.TTS_PARAMETER_INVALID.m6752b();
            }
            this.f4144b = c0786b;
            return 0;
        }

        public String m6345a() {
            return this.f4153k;
        }

        public void m6346a(int i) {
            this.f4155m = i;
        }

        public void m6347a(C0790c c0790c) {
            this.f4145c = c0790c;
        }

        public void m6348a(String str) {
            this.f4153k = str;
        }

        public String m6349b() {
            return this.f4154l;
        }

        public void m6350b(int i) {
            this.f4156n = i;
        }

        public void m6351b(String str) {
            this.f4154l = str;
        }

        public String m6352c() {
            return this.f4144b.m6733a();
        }

        public void m6353c(int i) {
            this.f4157o = i;
        }

        public void m6354c(String str) {
            this.f4143a = str;
        }

        public String m6355d() {
            return this.f4143a;
        }

        public void m6356d(String str) {
            this.f4146d = str;
        }

        public String m6357e() {
            return this.f4145c.m6735a();
        }

        public void m6358e(String str) {
            this.f4149g = str;
        }

        public String m6359f() {
            return this.f4146d;
        }

        public void m6360f(String str) {
            this.f4150h = str;
        }

        public String m6361g() {
            return this.f4147e;
        }

        public void m6362g(String str) {
            this.f4151i = str;
        }

        public String m6363h() {
            return this.f4148f;
        }

        public void m6364h(String str) {
            this.f4152j = str;
        }

        public String m6365i() {
            return this.f4149g;
        }

        public String m6366j() {
            return this.f4150h;
        }

        public String m6367k() {
            return this.f4151i;
        }

        public String m6368l() {
            return this.f4152j;
        }

        public int m6369m() {
            return this.f4155m;
        }

        public int m6370n() {
            return this.f4156n;
        }

        public int m6371o() {
            return this.f4157o;
        }
    }

    /* renamed from: com.baidu.tts.b.a.b.f.c */
    class C0726c implements Callable<TtsError> {
        final /* synthetic */ C0727f f4158a;
        private C0829i f4159b;
        private String f4160c;

        public C0726c(C0727f c0727f, C0829i c0829i) {
            this.f4158a = c0727f;
            this.f4159b = c0829i;
            this.f4160c = CommonUtility.generateSerialNumber();
        }

        private boolean m6372a(C0828h c0828h) {
            return c0828h != null && c0828h.m6868f() == null && c0828h.m6853a() == 0;
        }

        private boolean m6373b(C0828h c0828h) {
            return !m6372a(c0828h) || c0828h.m6861b() < 0;
        }

        public TtsError m6374a() {
            C0828h a;
            int i = 0;
            do {
                i++;
                LoggerProxy.m6515d("OnlineSynthesizer", "count=" + i);
                a = this.f4158a.m6375a(i, this.f4160c, this.f4159b);
                if (m6372a(a)) {
                    this.f4158a.m6268a(a);
                }
            } while (!m6373b(a));
            return a == null ? C0807c.m6758a().m6765b(C0802n.ONLINE_ENGINE_CALL_EXCEPTION) : a.m6868f();
        }

        public /* synthetic */ Object call() {
            return m6374a();
        }
    }

    private C0828h m6375a(int i, String str, C0829i c0829i) {
        C0828h b = C0828h.m6852b(c0829i);
        C0725b c0725b = (C0725b) this.f4161b.m6300y();
        C0724a c0724a = new C0724a(this, i, str, c0829i, c0725b, b);
        FutureTask futureTask = new FutureTask(c0724a);
        new Thread(futureTask).start();
        try {
            return (C0828h) futureTask.get((long) c0725b.m6371o(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            futureTask.cancel(true);
            c0724a.m6343b();
            throw e;
        } catch (ExecutionException e2) {
            b.m6855a(C0807c.m6758a().m6763a(C0802n.ONLINE_ENGINE_GET_EXECUTION_EXCEPTION, e2.getCause()));
            return b;
        } catch (Throwable e3) {
            LoggerProxy.m6515d("OnlineSynthesizer", "startOnceHttpRequest timeout");
            futureTask.cancel(true);
            c0724a.m6343b();
            b.m6855a(C0807c.m6758a().m6763a(C0802n.ONLINE_ENGINE_GET_TIMEOUT, e3));
            return b;
        }
    }

    private HttpEntity m6377a(int i, String str, C0829i c0829i, C0725b c0725b) {
        if (c0725b == null) {
            return null;
        }
        UrlEncodedFormEntity urlEncodedFormEntity;
        List<NameValuePair> arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair(C0794g.INDEX.m6741a(), String.valueOf(i)));
        arrayList.add(new BasicNameValuePair(C0794g.SERIAL_NUMBER.m6741a(), str));
        c0829i.m6875c(c0725b.m6306p());
        String d = c0829i.m6876d();
        if (i == 1) {
            try {
                arrayList.add(new BasicNameValuePair(C0794g.TEXT.m6741a(), URLEncoder.encode(c0829i.m6874c(), d)));
                C0809b a = C0809b.m6769a();
                String a2 = a.m6771a(C0794g.CTP.m6741a());
                String a3 = a.m6771a(C0794g.VERSION.m6741a());
                arrayList.add(new BasicNameValuePair(C0794g.CTP.m6741a(), a2));
                String i2 = a.m6780i();
                if (i2 != null) {
                    arrayList.add(new BasicNameValuePair(C0794g.CUID.m6741a(), i2));
                }
                arrayList.add(new BasicNameValuePair(C0794g.VERSION.m6741a(), a3));
                Object d2 = c0725b.m6355d();
                if (TextUtils.isEmpty(d2)) {
                    LoggerProxy.m6515d("OnlineSynthesizer", "before online auth");
                    C0694a a4 = C0689a.m6095a().m6103a(c0725b);
                    LoggerProxy.m6515d("OnlineSynthesizer", "after online auth");
                    if (a4.m6129g()) {
                        arrayList.add(new BasicNameValuePair(C0794g.TOKEN.m6741a(), a4.m6123a()));
                    } else {
                        throw new C0856a();
                    }
                }
                arrayList.add(new BasicNameValuePair(C0794g.PRODUCT_ID.m6741a(), d2));
                arrayList.add(new BasicNameValuePair(C0794g.TEXT_ENCODE.m6741a(), c0725b.m6307q()));
                arrayList.add(new BasicNameValuePair(C0794g.AUDIO_ENCODE.m6741a(), c0725b.m6352c()));
                arrayList.add(new BasicNameValuePair(C0794g.BITRATE.m6741a(), c0725b.m6357e()));
                arrayList.add(new BasicNameValuePair(C0794g.SPEAKER.m6741a(), c0725b.m6359f()));
                arrayList.add(new BasicNameValuePair(C0794g.NUMBER.m6741a(), c0725b.m6361g()));
                arrayList.add(new BasicNameValuePair(C0794g.ENGINE.m6741a(), c0725b.m6363h()));
                arrayList.add(new BasicNameValuePair(C0794g.STYLE.m6741a(), c0725b.m6365i()));
                arrayList.add(new BasicNameValuePair(C0794g.BACKGROUND.m6741a(), c0725b.m6366j()));
                arrayList.add(new BasicNameValuePair(C0794g.TERRITORY.m6741a(), c0725b.m6367k()));
                arrayList.add(new BasicNameValuePair(C0794g.PUNCTUATION.m6741a(), c0725b.m6368l()));
                arrayList.add(new BasicNameValuePair(C0794g.LANGUAGE.m6741a(), c0725b.m6308r()));
                arrayList.add(new BasicNameValuePair(C0794g.SPEED.m6741a(), c0725b.m6309s()));
                arrayList.add(new BasicNameValuePair(C0794g.PITCH.m6741a(), c0725b.m6310t()));
                arrayList.add(new BasicNameValuePair(C0794g.VOLUME.m6741a(), c0725b.m6311u()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            for (NameValuePair nameValuePair : arrayList) {
                stringBuffer.append(nameValuePair.getName());
                stringBuffer.append("=");
                stringBuffer.append(nameValuePair.getValue());
                stringBuffer.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
            }
            LoggerProxy.m6515d("OnlineSynthesizer", "request params: " + stringBuffer);
            urlEncodedFormEntity = new UrlEncodedFormEntity(arrayList, d);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            urlEncodedFormEntity = null;
        }
        return urlEncodedFormEntity;
    }

    public int m6379a(C0825e c0825e) {
        return C0802n.ONLINE_UNSUPPORTED_OPERATION.m6752b();
    }

    public int m6380a(C0826f c0826f) {
        return C0802n.ONLINE_UNSUPPORTED_OPERATION.m6752b();
    }

    public int m6381a(C0827g c0827g) {
        return C0802n.ONLINE_UNSUPPORTED_OPERATION.m6752b();
    }

    public TtsError m6382a(C0829i c0829i) {
        try {
            return new C0726c(this, c0829i).m6374a();
        } catch (InterruptedException e) {
            throw e;
        } catch (Throwable e2) {
            return C0807c.m6758a().m6763a(C0802n.ONLINE_ENGINE_CALL_EXCEPTION, e2);
        }
    }

    public <OnlineSynthesizerParams> void m6383a(OnlineSynthesizerParams onlineSynthesizerParams) {
        this.f4161b = (C0725b) onlineSynthesizerParams;
    }

    public int m6384b(C0825e c0825e) {
        return C0802n.ONLINE_UNSUPPORTED_OPERATION.m6752b();
    }
}
