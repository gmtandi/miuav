package com.baidu.tts.auth;

import android.content.Context;
import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.jni.EmbeddedSynthesizerEngine;
import com.baidu.tts.p030j.C0690a;
import com.baidu.tts.p030j.C0692b;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p044g.p045a.C0807c;
import com.baidu.tts.p044g.p046b.C0809b;
import com.baidu.tts.tools.StringTool;
import com.tencent.connect.common.Constants;
import com.tencent.connect.dataprovider.ErrorCode;
import com.tencent.mm.sdk.openapi.BaseResp.ErrCode;
import com.tencent.open.yyb.AppbarJsBridge;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.auth.AuthConstants;
import java.io.File;
import java.util.Arrays;
import org.codehaus.jackson.io.CharacterEscapes;
import org.p122a.p123a.C2915a;

/* renamed from: com.baidu.tts.auth.b */
public class C0693b implements C0692b<C0693b, C0691a> {
    private String f4075a;
    private String f4076b;

    /* renamed from: com.baidu.tts.auth.b.a */
    public class C0691a implements C0690a {
        private int f4070a;
        private int f4071b;
        private String f4072c;
        private String f4073d;
        private TtsError f4074e;

        public C0691a() {
            this.f4071b = -1;
        }

        public int m6106a() {
            return this.f4070a >= XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER ? this.f4070a + AuthConstants.ERROR_CONNECT_FAILED : 0;
        }

        public void m6107a(int i) {
            this.f4071b = i;
        }

        public void m6108a(TtsError ttsError) {
            if (ttsError != null) {
                LoggerProxy.m6515d("OfflineAuth", "this=" + this + "--error=" + ttsError.getDetailMessage());
            }
            this.f4074e = ttsError;
        }

        public void m6109a(String str) {
            this.f4072c = str;
        }

        public TtsError m6110b() {
            return this.f4074e;
        }

        public void m6111b(String str) {
            this.f4073d = str;
        }

        public String m6112c() {
            if (m6114e()) {
                return "valid official";
            }
            if (m6113d()) {
                return "valid temp";
            }
            switch (this.f4070a) {
                case ErrorCode.SdCardNotExist /*-10*/:
                    return "temp license expired";
                case ErrorCode.FileNotExist /*-8*/:
                    return "license not exist or invalid license";
                case ErrorCode.PathIsNull /*-7*/:
                    return "platform unmatched";
                case ErrorCode.FileSizeTooLarge /*-6*/:
                    return "will expire after a month";
                case AppbarJsBridge.AUTHORIZE_FAIL /*-5*/:
                    return "official license expired";
                case ErrCode.ERR_AUTH_DENIED /*-4*/:
                    return "cuid unmatched";
                case AppbarJsBridge.Code_Java_Exception /*-3*/:
                    return "sign or appcode unmatched";
                case CharacterEscapes.ESCAPE_CUSTOM /*-2*/:
                    return "package name unmatched";
                default:
                    return "not a valid result";
            }
        }

        public boolean m6113d() {
            return this.f4070a >= XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
        }

        public boolean m6114e() {
            return this.f4070a >= 0 && this.f4070a < XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
        }

        public boolean m6115f() {
            return this.f4070a == -5 || this.f4070a == -6;
        }

        public boolean m6116g() {
            if (StringTool.isEmpty(this.f4072c)) {
                return false;
            }
            File file = new File(this.f4072c);
            if (!file.exists()) {
                return false;
            }
            C0809b a = C0809b.m6769a();
            byte[] bArr = new byte[32];
            this.f4070a = EmbeddedSynthesizerEngine.bdTTSVerifyLicense(a.m6779h(), this.f4073d, a.m6780i(), this.f4072c, bArr);
            LoggerProxy.m6515d("OfflineAuth", "verify result=" + this.f4070a);
            if (bArr != null) {
                LoggerProxy.m6515d("OfflineAuth", "get appIdStr=" + Arrays.toString(bArr));
            }
            if (this.f4070a >= 0) {
                return true;
            }
            LoggerProxy.m6515d("OfflineAuth", "isDelete=" + file.delete());
            return false;
        }
    }

    public int m6117a(C0693b c0693b) {
        return (StringTool.isEqual(this.f4075a, c0693b.m6118a()) && StringTool.isEqual(this.f4076b, c0693b.m6120b())) ? 0 : 1;
    }

    public String m6118a() {
        return this.f4075a;
    }

    public void m6119a(String str) {
        this.f4075a = str;
    }

    public String m6120b() {
        return this.f4076b;
    }

    public void m6121b(String str) {
        this.f4076b = str;
    }

    public C0691a m6122c() {
        C0691a c0691a = new C0691a();
        c0691a.m6109a(this.f4076b);
        c0691a.m6111b(this.f4075a);
        if (!c0691a.m6116g()) {
            C0809b a = C0809b.m6769a();
            Context h = a.m6779h();
            String i = a.m6780i();
            LoggerProxy.m6515d("OfflineAuth", "+ downloadLicense");
            int bdTTSGetLicense = EmbeddedSynthesizerEngine.bdTTSGetLicense(h, this.f4075a, i, Constants.VIA_RESULT_SUCCESS, C2915a.f14760f, this.f4076b);
            LoggerProxy.m6515d("OfflineAuth", "- downloadLicense ret = " + bdTTSGetLicense);
            c0691a.m6107a(bdTTSGetLicense);
            if (bdTTSGetLicense < 0) {
                c0691a.m6108a(C0807c.m6758a().m6760a(C0802n.OFFLINE_ENGINE_DOWNLOAD_LICENSE_FAILED, bdTTSGetLicense, "appCode=" + this.f4075a + "--licensePath=" + this.f4076b));
            } else {
                c0691a.m6116g();
            }
        }
        return c0691a;
    }

    public /* synthetic */ Object call() {
        return m6122c();
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m6117a((C0693b) obj);
    }
}
