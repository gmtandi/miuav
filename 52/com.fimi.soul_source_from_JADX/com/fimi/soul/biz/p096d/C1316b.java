package com.fimi.soul.biz.p096d;

import android.os.Message;
import android.util.Log;
import com.fimi.kernel.C1099d;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p076b.C1111a;
import com.fimi.kernel.p076b.C1152e;
import com.fimi.kernel.p076b.C1153f;
import com.fimi.kernel.p076b.p081e.C1145b;
import com.fimi.soul.entity.APConfig;
import com.fimi.soul.entity.APStatus;
import com.tencent.connect.common.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.d.b */
public class C1316b extends C1099d {
    public static final String f5906a = "PSK_KEY";
    public static final String f5907b = "AP_SSID";
    public static final String f5908c = "AP_PRIMARY_CH";
    public static final String f5909d = "DEF_ATH_countrycode";
    public static final String f5910e = "VERSION";
    public static final String f5911f = "AP_IPADDR";
    public static final String f5912g = "ATH_txpower";
    public static final String f5913h = "ERROR_INFO";
    public static final String f5914i = "SIGNAL_INFO";
    public static String f5915j = null;
    public static final String f5916k = "SP_KEY_CACHE_APCONFIG";
    private static String f5917l;
    private static String f5918m;
    private C1145b f5919n;
    private Map<String, String> f5920o;
    private Pattern f5921p;
    private C1111a f5922q;

    static {
        f5917l = "http://192.168.42.100/cgi-bin";
        f5915j = "UNCONNECT_CAMERA";
        f5918m = "\".*\"";
    }

    public C1316b() {
        this.f5919n = null;
        this.f5919n = (C1145b) C1189f.m8328a(C1152e.Volley);
        this.f5922q = (C1111a) C1189f.m8328a(C1152e.Http);
        this.f5921p = Pattern.compile(f5918m);
        this.f5920o = new HashMap();
    }

    private boolean m8880a(String[] strArr, String str) {
        for (String equals : strArr) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private void m8882b(String str, String str2, C1153f<Boolean> c1153f) {
        Map hashMap = new HashMap();
        hashMap.put(str, str2);
        this.f5919n.m7907a(m8884c("VAPcfg"), hashMap, new C1323i(this, c1153f));
    }

    private String m8884c(String str) {
        return String.format("%s/%s", new Object[]{f5917l, str});
    }

    private APConfig m8885d() {
        APConfig aPConfig = new APConfig();
        if (this.f5920o != null && this.f5920o.size() > 0) {
            aPConfig.setApPwd((String) this.f5920o.get(f5906a));
            aPConfig.setSsid((String) this.f5920o.get(f5907b));
            aPConfig.setPrimaryChannel((String) this.f5920o.get(f5908c));
            aPConfig.setCountryCode((String) this.f5920o.get(f5909d));
            aPConfig.setVersion((String) this.f5920o.get(f5910e));
            aPConfig.setIpAddr((String) this.f5920o.get(f5911f));
            aPConfig.setPower((String) this.f5920o.get(f5912g));
            aPConfig.setConCameraStatus((String) this.f5920o.get(f5913h));
            if (this.f5920o.get(f5914i) != null) {
                aPConfig.setSignalInfo(Integer.parseInt((String) this.f5920o.get(f5914i)));
            }
        }
        return aPConfig;
    }

    protected void m8886a(Message message) {
    }

    public void m8887a(C1153f<APStatus> c1153f) {
        this.f5919n.m7910b(m8884c("status.cgi"), new C1317c(this, c1153f));
    }

    public void m8888a(APConfig aPConfig, C1153f<Boolean> c1153f) {
        if (aPConfig != null) {
            C1324j c1324j = new C1324j(this, String.format("%s/setting.cgi", new Object[]{f5917l}));
            c1324j.m8928a(f5906a, aPConfig.getApPwd());
            c1324j.m8928a(f5909d, aPConfig.getCountryCode());
            c1324j.m8928a(f5912g, aPConfig.getPower());
            c1324j.m8928a(f5907b, aPConfig.getSsid());
            c1324j.m8928a(f5910e, aPConfig.getVersion());
            String a = c1324j.m8927a();
            Log.d("Good", a);
            this.f5922q.m7729a(a, new C1319e(this, c1153f));
        }
    }

    public void m8889a(String str, String str2, C1153f<Boolean> c1153f) {
        C1324j c1324j = new C1324j(this, String.format("%s/setting.cgi", new Object[]{f5917l}));
        c1324j.m8928a(str, str2);
        this.f5922q.m7729a(c1324j.m8927a(), new C1321g(this, c1153f));
    }

    public boolean m8890a(String str) {
        return m8880a(m8896c(), str);
    }

    public boolean m8891a(String str, String str2) {
        return m8880a(m8894b(str), str2);
    }

    public APConfig m8892b() {
        APConfig aPConfig = (APConfig) C1189f.m8333c().m7926a(f5916k, APConfig.class);
        return aPConfig == null ? new APConfig() : aPConfig;
    }

    public void m8893b(C1153f<APConfig> c1153f) {
        this.f5920o.clear();
        this.f5919n.m7910b(m8884c("config.cgi"), new C1318d(this, c1153f));
    }

    public String[] m8894b(String str) {
        return m8880a(C1315a.f5900a, str) ? C1315a.f5903d : m8880a(C1315a.f5902c, str) ? C1315a.f5905f : m8880a(C1315a.f5901b, str) ? C1315a.f5901b : null;
    }

    public void m8895c(C1153f<Boolean> c1153f) {
        Map hashMap = new HashMap();
        hashMap.put("INDEX", Constants.VIA_TO_TYPE_QQ_GROUP);
        hashMap.put("COMMIT", "Save");
        this.f5919n.m7907a(m8884c("VAPcfg"), hashMap, new C1322h(this, c1153f));
    }

    public String[] m8896c() {
        return m8894b(m8892b().getCountryCode());
    }

    public void m8897d(C1153f<Boolean> c1153f) {
        m8882b("RebootButton", "Reboot", c1153f);
    }

    public void m8898e(C1153f<Boolean> c1153f) {
        m8882b(C2915a.f14760f, "FactoryReset", c1153f);
    }
}
