package com.amap.api.services.core;

import android.content.Context;
import com.baidu.tts.loopj.AsyncHttpClient;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p152c.p161f.C2989l;

/* renamed from: com.amap.api.services.core.r */
public abstract class C0449r<T, V> extends bt {
    protected T f2960a;
    protected int f2961b;
    protected String f2962c;
    protected Context f2963d;
    private int f2964h;

    public C0449r(Context context, T t) {
        this.f2961b = 1;
        this.f2962c = C2915a.f14760f;
        this.f2964h = 1;
        m4450a(context, t);
    }

    private String m4449a(String str) {
        String[] split = str.split("&");
        Arrays.sort(split);
        StringBuffer stringBuffer = new StringBuffer();
        for (String d : split) {
            stringBuffer.append(m4456d(d));
            stringBuffer.append("&");
        }
        String stringBuffer2 = stringBuffer.toString();
        return stringBuffer2.length() > 1 ? (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1) : str;
    }

    private void m4450a(Context context, T t) {
        this.f2963d = context;
        this.f2960a = t;
        this.f2961b = 1;
        m4447d(ServiceSettings.getInstance().getSoTimeOut());
        m4446c(ServiceSettings.getInstance().getConnectionTimeOut());
    }

    private V m4451b(byte[] bArr) {
        return m4453a(bArr);
    }

    private V m4452f() {
        int i = 0;
        V v = null;
        while (i < this.f2961b) {
            try {
                int protocol = ServiceSettings.getInstance().getProtocol();
                bs a = bs.m4739a(false);
                m4444a(ac.m4479a(this.f2963d));
                byte[] a2 = protocol == 1 ? a.m4749a((bt) this) : protocol == 2 ? a.m4750b(this) : null;
                v = m4451b(a2);
                i = this.f2961b;
            } catch (Throwable e) {
                C0471d.m4762a(e, "ProtocalHandler", "getDataMayThrowAMapException");
                i++;
                if (i >= this.f2961b) {
                    throw new AMapException(e.getErrorMessage());
                }
            } catch (Throwable e2) {
                C0471d.m4762a(e2, "ProtocalHandler", "getDataMayThrowAMapCoreException");
                i++;
                if (i < this.f2961b) {
                    try {
                        Thread.sleep((long) (this.f2964h * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER));
                    } catch (InterruptedException e3) {
                        C0471d.m4762a(e2, "ProtocalHandler", "getDataMayThrowInterruptedException");
                        throw new AMapException(e2.getMessage());
                    }
                }
                m4459h();
                throw new AMapException(e2.m4867a());
            } catch (Throwable th) {
                C0471d.m4762a(th, "ProtocalHandler", "getDataMayThrowAMapCoreException");
                AMapException aMapException = new AMapException(AMapException.ERROR_UNKNOWN);
            }
        }
        return v;
    }

    protected V m4453a(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "utf-8");
        } catch (Throwable e) {
            C0471d.m4762a(e, "ProtocalHandler", "loadData");
            str = null;
        }
        if (str == null || str.equals(C2915a.f14760f)) {
            return null;
        }
        C0471d.m4764b(str);
        return m4454b(str);
    }

    protected abstract String a_();

    protected abstract V m4454b(String str);

    protected String m4455c(String str) {
        if (str == null) {
            return str;
        }
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (Throwable e) {
            C0471d.m4762a(e, "ProtocalHandler", "strEncoderUnsupportedEncodingException");
            return new String();
        } catch (Throwable e2) {
            C0471d.m4762a(e2, "ProtocalHandler", "strEncoderException");
            return new String();
        }
    }

    public Map<String, String> c_() {
        return null;
    }

    protected String m4456d(String str) {
        if (str == null) {
            return str;
        }
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (Throwable e) {
            C0471d.m4762a(e, "ProtocalHandler", "strReEncoder");
            return new String();
        } catch (Throwable e2) {
            C0471d.m4762a(e2, "ProtocalHandler", "strReEncoderException");
            return new String();
        }
    }

    public Map<String, String> d_() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(C3004e.f15031q, C2989l.f14939a);
        hashMap.put(C3004e.f15017c, AsyncHttpClient.ENCODING_GZIP);
        hashMap.put(C3004e.f15013Y, "AMAP SDK Android Search 2.5.0");
        hashMap.put("X-INFO", C0498y.m4880a(this.f2963d, C0480l.f3144a, null));
        hashMap.put("ia", Constants.VIA_TO_TYPE_QQ_GROUP);
        hashMap.put("ec", Constants.VIA_TO_TYPE_QQ_GROUP);
        hashMap.put(SharedPref.KEY, C0496w.m4874f(this.f2963d));
        return hashMap;
    }

    public HttpEntity m4457e() {
        try {
            String a_ = a_();
            String a = m4449a(a_);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(a_);
            a_ = C0498y.m4877a();
            stringBuffer.append("&ts=" + a_);
            stringBuffer.append("&scode=" + C0498y.m4881a(this.f2963d, a_, a));
            return new StringEntity(stringBuffer.toString());
        } catch (Throwable e) {
            C0471d.m4762a(e, "ProtocalHandler", "getEntity");
            return null;
        }
    }

    public V m4458g() {
        return this.f2960a != null ? m4452f() : null;
    }

    protected V m4459h() {
        return null;
    }
}
