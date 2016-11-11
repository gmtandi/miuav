package com.baidu.tts.auth;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.auth.C0693b.C0691a;
import com.baidu.tts.auth.C0697c.C0694a;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.p041e.C0800m;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p044g.p045a.C0807c;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class AuthInfo {
    private C0800m f4052a;
    private C0694a f4053b;
    private C0691a f4054c;
    private TtsError f4055d;

    /* renamed from: com.baidu.tts.auth.AuthInfo.1 */
    /* synthetic */ class C06831 {
        static final /* synthetic */ int[] f4051a;

        static {
            f4051a = new int[C0800m.values().length];
            try {
                f4051a[C0800m.ONLINE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4051a[C0800m.OFFLINE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4051a[C0800m.MIX.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public int getLeftValidDays() {
        return this.f4054c.m6106a();
    }

    public TtsError getMixTtsError() {
        TtsError onlineTtsError = getOnlineTtsError();
        TtsError offlineTtsError = getOfflineTtsError();
        TtsError ttsError = null;
        if (onlineTtsError != null && offlineTtsError != null) {
            ttsError = C0807c.m6758a().m6765b(C0802n.MIX_ENGINE_AUTH_FAILURE);
        } else if (onlineTtsError == null && offlineTtsError != null) {
            ttsError = C0807c.m6758a().m6765b(C0802n.OFFLINE_ENGINE_AUTH_FAILURE);
        } else if (onlineTtsError != null && offlineTtsError == null) {
            ttsError = C0807c.m6758a().m6765b(C0802n.ONLINE_ENGINE_AUTH_FAILURE);
        }
        return ttsError != null ? ttsError : this.f4055d;
    }

    public String getNotifyMessage() {
        return this.f4054c.m6112c();
    }

    public C0691a getOfflineResult() {
        return this.f4054c;
    }

    public TtsError getOfflineTtsError() {
        return this.f4054c != null ? this.f4054c.m6110b() : this.f4055d;
    }

    public C0694a getOnlineResult() {
        return this.f4053b;
    }

    public TtsError getOnlineTtsError() {
        return this.f4053b != null ? this.f4053b.m6127b() : this.f4055d;
    }

    public C0800m getTtsEnum() {
        return this.f4052a;
    }

    public TtsError getTtsError() {
        if (this.f4055d != null) {
            return this.f4055d;
        }
        switch (C06831.f4051a[this.f4052a.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return this.f4053b.m6127b();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return this.f4054c.m6110b();
            case Type.BYTE /*3*/:
                return getMixTtsError();
            default:
                return null;
        }
    }

    public boolean isMixSuccess() {
        return isOnlineSuccess() || isOfflineSuccess();
    }

    public boolean isOfflineSuccess() {
        return this.f4054c != null ? this.f4054c.m6116g() : false;
    }

    public boolean isOnlineSuccess() {
        return this.f4053b != null ? this.f4053b.m6129g() : false;
    }

    public boolean isSuccess() {
        if (this.f4055d != null) {
            LoggerProxy.m6515d("AuthInfo", "cause=" + this.f4055d.getThrowable().getMessage());
            return false;
        } else if (this.f4052a == null) {
            return false;
        } else {
            switch (C06831.f4051a[this.f4052a.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    return isOnlineSuccess();
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    return isOfflineSuccess();
                case Type.BYTE /*3*/:
                    return isMixSuccess();
                default:
                    return false;
            }
        }
    }

    public void setOfflineResult(C0691a c0691a) {
        this.f4054c = c0691a;
    }

    public void setOnlineResult(C0694a c0694a) {
        this.f4053b = c0694a;
    }

    public void setTtsEnum(C0800m c0800m) {
        this.f4052a = c0800m;
    }

    public void setTtsError(TtsError ttsError) {
        this.f4055d = ttsError;
    }
}
