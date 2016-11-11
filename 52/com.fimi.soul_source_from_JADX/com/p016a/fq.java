package com.p016a;

import android.content.Context;
import android.text.TextUtils;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.fq */
public class fq {
    public static fr m1860a(Context context, gd gdVar, String str) {
        try {
            return fq.m1861a(new bk().m1171a(new fv(context, gdVar, str)));
        } catch (Throwable e) {
            C0247g.m1917a(e, "ConfigManager", "loadConfig");
            return new fr();
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "ConfigManager", "loadConfig");
            return new fr();
        }
    }

    public static fr m1861a(byte[] bArr) {
        boolean z = false;
        fr frVar = new fr();
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    JSONObject jSONObject = new JSONObject(new String(bArr, C1142e.f5201a));
                    if (Constants.VIA_TO_TYPE_QQ_GROUP.equals(fq.m1862a(jSONObject, RMsgInfo.COL_STATUS)) && jSONObject.has("result")) {
                        jSONObject = jSONObject.getJSONObject("result");
                        if (jSONObject != null) {
                            JSONObject jSONObject2;
                            boolean b = gf.m1960a(jSONObject, "exception") ? fq.m1867b(jSONObject.getJSONObject("exception")) : false;
                            if (gf.m1960a(jSONObject, "common")) {
                                z = fq.m1866a(jSONObject.getJSONObject("common"));
                            }
                            fs fsVar = new fs();
                            fsVar.f1231a = b;
                            fsVar.f1232b = z;
                            frVar.f1228g = fsVar;
                            if (jSONObject.has("sdkupdate")) {
                                jSONObject2 = jSONObject.getJSONObject("sdkupdate");
                                fu fuVar = new fu();
                                fq.m1864a(jSONObject2, fuVar);
                                frVar.f1229h = fuVar;
                            }
                            if (gf.m1960a(jSONObject, "sdkcoordinate")) {
                                jSONObject2 = jSONObject.getJSONObject("sdkcoordinate");
                                ft ftVar = new ft();
                                fq.m1863a(jSONObject2, ftVar);
                                frVar.f1230i = ftVar;
                            }
                            if (gf.m1960a(jSONObject, "callamap")) {
                                frVar.f1226e = jSONObject.getJSONObject("callamap");
                            }
                            if (gf.m1960a(jSONObject, "ca")) {
                                frVar.f1227f = jSONObject.getJSONObject("ca");
                            }
                            if (gf.m1960a(jSONObject, "locate")) {
                                frVar.f1225d = jSONObject.getJSONObject("locate");
                            }
                            if (gf.m1960a(jSONObject, "callamappro")) {
                                frVar.f1224c = jSONObject.getJSONObject("callamappro");
                            }
                            if (gf.m1960a(jSONObject, "opflag")) {
                                frVar.f1223b = jSONObject.getJSONObject("opflag");
                            }
                            if (gf.m1960a(jSONObject, "amappushflag")) {
                                frVar.f1222a = jSONObject.getJSONObject("amappushflag");
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                C0247g.m1917a(e, "ConfigManager", "loadConfig");
            } catch (Throwable e2) {
                C0247g.m1917a(e2, "ConfigManager", "loadConfig");
            } catch (Throwable e22) {
                C0247g.m1917a(e22, "ConfigManager", "loadConfig");
            }
        }
        return frVar;
    }

    public static String m1862a(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return C2915a.f14760f;
        }
        String str2 = C2915a.f14760f;
        return (!jSONObject.has(str) || jSONObject.getString(str).equals("[]")) ? str2 : jSONObject.optString(str);
    }

    private static void m1863a(JSONObject jSONObject, ft ftVar) {
        if (jSONObject != null) {
            try {
                String a = fq.m1862a(jSONObject, "md5");
                String a2 = fq.m1862a(jSONObject, SocialConstants.PARAM_URL);
                ftVar.f1234b = a;
                ftVar.f1233a = a2;
            } catch (Throwable e) {
                C0247g.m1917a(e, "ConfigManager", "parseSDKCoordinate");
            } catch (Throwable e2) {
                C0247g.m1917a(e2, "ConfigManager", "parseSDKCoordinate");
            }
        }
    }

    private static void m1864a(JSONObject jSONObject, fu fuVar) {
        if (jSONObject != null) {
            try {
                Object a = fq.m1862a(jSONObject, "md5");
                Object a2 = fq.m1862a(jSONObject, SocialConstants.PARAM_URL);
                Object a3 = fq.m1862a(jSONObject, "sdkversion");
                if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3)) {
                    fuVar.f1235a = a2;
                    fuVar.f1236b = a;
                    fuVar.f1237c = a3;
                }
            } catch (Throwable e) {
                C0247g.m1917a(e, "ConfigManager", "parseSDKUpdate");
            } catch (Throwable e2) {
                C0247g.m1917a(e2, "ConfigManager", "parseSDKUpdate");
            }
        }
    }

    private static boolean m1865a(String str) {
        return str != null && str.equals(Constants.VIA_TO_TYPE_QQ_GROUP);
    }

    private static boolean m1866a(JSONObject jSONObject) {
        boolean z = false;
        if (jSONObject != null) {
            try {
                z = fq.m1865a(fq.m1862a(jSONObject.getJSONObject("commoninfo"), "com_isupload"));
            } catch (Throwable e) {
                C0247g.m1917a(e, "ConfigManager", "parseCommon");
            } catch (Throwable e2) {
                C0247g.m1917a(e2, "ConfigManager", "parseCommon");
            }
        }
        return z;
    }

    private static boolean m1867b(JSONObject jSONObject) {
        boolean z = false;
        if (jSONObject != null) {
            try {
                z = fq.m1865a(fq.m1862a(jSONObject.getJSONObject("exceptinfo"), "ex_isupload"));
            } catch (Throwable e) {
                C0247g.m1917a(e, "ConfigManager", "parseException");
            } catch (Throwable e2) {
                C0247g.m1917a(e2, "ConfigManager", "parseException");
            }
        }
        return z;
    }
}
