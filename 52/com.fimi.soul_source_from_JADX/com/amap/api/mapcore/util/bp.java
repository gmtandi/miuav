package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.open.SocialConstants;
import com.tencent.stat.DeviceInfo;
import com.xiaomi.market.sdk.C2537j;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.C3004e;

public class bp {

    /* renamed from: com.amap.api.mapcore.util.bp.a */
    public class C0359a {
        public JSONObject f2246a;
        public JSONObject f2247b;
        public JSONObject f2248c;
        public JSONObject f2249d;
        @Deprecated
        public JSONObject f2250e;
        public JSONObject f2251f;
        public C0356a f2252g;
        public C0358c f2253h;
        public C0357b f2254i;

        /* renamed from: com.amap.api.mapcore.util.bp.a.a */
        public class C0356a {
            public boolean f2239a;
            public boolean f2240b;
        }

        /* renamed from: com.amap.api.mapcore.util.bp.a.b */
        public class C0357b {
            public String f2241a;
            public String f2242b;
        }

        /* renamed from: com.amap.api.mapcore.util.bp.a.c */
        public class C0358c {
            public String f2243a;
            public String f2244b;
            public String f2245c;
        }
    }

    /* renamed from: com.amap.api.mapcore.util.bp.b */
    class C0360b extends dj {
        private Context f2255a;
        private bv f2256b;
        private String f2257c;

        C0360b(Context context, bv bvVar, String str) {
            this.f2257c = C2915a.f14760f;
            this.f2255a = context;
            this.f2256b = bvVar;
            this.f2257c = str;
        }

        public String m3674a() {
            return "https://restapi.amap.com/v3/config/resource?";
        }

        public Map<String, String> m3675b() {
            Object q = bq.m3707q(this.f2255a);
            if (!TextUtils.isEmpty(q)) {
                q = bs.m3728b(new StringBuilder(q).reverse().toString());
            }
            Map hashMap = new HashMap();
            hashMap.put(SharedPref.KEY, bl.m3652f(this.f2255a));
            hashMap.put("opertype", this.f2257c);
            hashMap.put("plattype", "android");
            hashMap.put("product", this.f2256b.m3763a());
            hashMap.put(C2537j.aq, this.f2256b.m3765b());
            hashMap.put("output", "json");
            hashMap.put("androidversion", VERSION.SDK_INT + C2915a.f14760f);
            hashMap.put(C2537j.as, q);
            hashMap.put("abitype", Build.CPU_ABI);
            hashMap.put("ext", this.f2256b.m3767d());
            String a = bn.m3661a();
            String a2 = bn.m3667a(this.f2255a, a, bx.m3780b(hashMap));
            hashMap.put(DeviceInfo.TAG_TIMESTAMPS, a);
            hashMap.put("scode", a2);
            return hashMap;
        }

        public Map<String, String> m3676c() {
            Map<String, String> hashMap = new HashMap();
            hashMap.put(C3004e.f15013Y, this.f2256b.m3766c());
            hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", new Object[]{this.f2256b.m3765b(), this.f2256b.m3763a()}));
            hashMap.put("logversion", "2.0");
            return hashMap;
        }
    }

    public static C0359a m3677a(Context context, bv bvVar, String str) {
        try {
            return m3678a(new dd().m4005a(new C0360b(context, bvVar, str)));
        } catch (Throwable e) {
            cb.m3809a(e, "ConfigManager", "loadConfig");
            return new C0359a();
        } catch (Throwable e2) {
            cb.m3809a(e2, "ConfigManager", "loadConfig");
            return new C0359a();
        }
    }

    public static C0359a m3678a(byte[] bArr) {
        boolean z = false;
        C0359a c0359a = new C0359a();
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    JSONObject jSONObject = new JSONObject(bx.m3772a(bArr));
                    if (Constants.VIA_TO_TYPE_QQ_GROUP.equals(m3679a(jSONObject, RMsgInfo.COL_STATUS)) && jSONObject.has("result")) {
                        jSONObject = jSONObject.getJSONObject("result");
                        if (jSONObject != null) {
                            JSONObject jSONObject2;
                            boolean b = bx.m3776a(jSONObject, "exception") ? m3684b(jSONObject.getJSONObject("exception")) : false;
                            if (bx.m3776a(jSONObject, "common")) {
                                z = m3683a(jSONObject.getJSONObject("common"));
                            }
                            C0356a c0356a = new C0356a();
                            c0356a.f2239a = b;
                            c0356a.f2240b = z;
                            c0359a.f2252g = c0356a;
                            if (jSONObject.has("sdkupdate")) {
                                jSONObject2 = jSONObject.getJSONObject("sdkupdate");
                                C0358c c0358c = new C0358c();
                                m3681a(jSONObject2, c0358c);
                                c0359a.f2253h = c0358c;
                            }
                            if (bx.m3776a(jSONObject, "sdkcoordinate")) {
                                jSONObject2 = jSONObject.getJSONObject("sdkcoordinate");
                                C0357b c0357b = new C0357b();
                                m3680a(jSONObject2, c0357b);
                                c0359a.f2254i = c0357b;
                            }
                            if (bx.m3776a(jSONObject, "callamap")) {
                                c0359a.f2250e = jSONObject.getJSONObject("callamap");
                            }
                            if (bx.m3776a(jSONObject, "ca")) {
                                c0359a.f2251f = jSONObject.getJSONObject("ca");
                            }
                            if (bx.m3776a(jSONObject, "locate")) {
                                c0359a.f2249d = jSONObject.getJSONObject("locate");
                            }
                            if (bx.m3776a(jSONObject, "callamappro")) {
                                c0359a.f2248c = jSONObject.getJSONObject("callamappro");
                            }
                            if (bx.m3776a(jSONObject, "opflag")) {
                                c0359a.f2247b = jSONObject.getJSONObject("opflag");
                            }
                            if (bx.m3776a(jSONObject, "amappushflag")) {
                                c0359a.f2246a = jSONObject.getJSONObject("amappushflag");
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                cb.m3809a(e, "ConfigManager", "loadConfig");
            } catch (Throwable e2) {
                cb.m3809a(e2, "ConfigManager", "loadConfig");
            }
        }
        return c0359a;
    }

    public static String m3679a(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return C2915a.f14760f;
        }
        String str2 = C2915a.f14760f;
        return (!jSONObject.has(str) || jSONObject.getString(str).equals("[]")) ? str2 : jSONObject.optString(str);
    }

    private static void m3680a(JSONObject jSONObject, C0357b c0357b) {
        if (jSONObject != null) {
            try {
                String a = m3679a(jSONObject, "md5");
                String a2 = m3679a(jSONObject, SocialConstants.PARAM_URL);
                c0357b.f2242b = a;
                c0357b.f2241a = a2;
            } catch (Throwable e) {
                cb.m3809a(e, "ConfigManager", "parseSDKCoordinate");
            } catch (Throwable e2) {
                cb.m3809a(e2, "ConfigManager", "parseSDKCoordinate");
            }
        }
    }

    private static void m3681a(JSONObject jSONObject, C0358c c0358c) {
        if (jSONObject != null) {
            try {
                Object a = m3679a(jSONObject, "md5");
                Object a2 = m3679a(jSONObject, SocialConstants.PARAM_URL);
                Object a3 = m3679a(jSONObject, "sdkversion");
                if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3)) {
                    c0358c.f2243a = a2;
                    c0358c.f2244b = a;
                    c0358c.f2245c = a3;
                }
            } catch (Throwable e) {
                cb.m3809a(e, "ConfigManager", "parseSDKUpdate");
            } catch (Throwable e2) {
                cb.m3809a(e2, "ConfigManager", "parseSDKUpdate");
            }
        }
    }

    private static boolean m3682a(String str) {
        return str != null && str.equals(Constants.VIA_TO_TYPE_QQ_GROUP);
    }

    private static boolean m3683a(JSONObject jSONObject) {
        boolean z = false;
        if (jSONObject != null) {
            try {
                z = m3682a(m3679a(jSONObject.getJSONObject("commoninfo"), "com_isupload"));
            } catch (Throwable e) {
                cb.m3809a(e, "ConfigManager", "parseCommon");
            } catch (Throwable e2) {
                cb.m3809a(e2, "ConfigManager", "parseCommon");
            }
        }
        return z;
    }

    private static boolean m3684b(JSONObject jSONObject) {
        boolean z = false;
        if (jSONObject != null) {
            try {
                z = m3682a(m3679a(jSONObject.getJSONObject("exceptinfo"), "ex_isupload"));
            } catch (Throwable e) {
                cb.m3809a(e, "ConfigManager", "parseException");
            } catch (Throwable e2) {
                cb.m3809a(e2, "ConfigManager", "parseException");
            }
        }
        return z;
    }
}
