package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.facebook.common.util.UriUtil;
import com.tencent.open.SocialConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.market.sdk.C2537j;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

@cl(a = "update_item", b = true)
/* renamed from: com.amap.api.mapcore.util.s */
public class C0401s extends C0400v {
    private String f2532m;
    private Context f2533n;

    public C0401s() {
        this.f2532m = C2915a.f14760f;
    }

    public C0401s(OfflineMapCity offlineMapCity, Context context) {
        this.f2532m = C2915a.f14760f;
        this.f2533n = context;
        this.a = offlineMapCity.getCity();
        this.c = offlineMapCity.getAdcode();
        this.b = offlineMapCity.getUrl();
        this.g = offlineMapCity.getSize();
        m4203a();
        this.e = offlineMapCity.getVersion();
        this.k = offlineMapCity.getCode();
        this.i = 0;
        this.l = offlineMapCity.getState();
        this.j = offlineMapCity.getcompleteCode();
    }

    public C0401s(OfflineMapProvince offlineMapProvince, Context context) {
        this.f2532m = C2915a.f14760f;
        this.f2533n = context;
        this.a = offlineMapProvince.getProvinceName();
        this.c = offlineMapProvince.getProvinceCode();
        this.b = offlineMapProvince.getUrl();
        this.g = offlineMapProvince.getSize();
        m4203a();
        this.e = offlineMapProvince.getVersion();
        this.i = 1;
        this.l = offlineMapProvince.getState();
        this.j = offlineMapProvince.getcompleteCode();
    }

    protected void m4203a() {
        this.d = bj.m3634b(this.f2533n) + this.c + ".zip" + ".tmp";
    }

    public void m4204a(String str) {
        this.f2532m = str;
    }

    public void m4205b() {
        this.l = 6;
        m4194a(0);
        m4195a(0);
    }

    public void m4206b(String str) {
        if (str != null) {
            try {
                if (!str.equals(C2915a.f14760f)) {
                    JSONObject jSONObject = new JSONObject(str).getJSONObject(UriUtil.LOCAL_FILE_SCHEME);
                    if (jSONObject != null) {
                        this.a = jSONObject.optString(SocialConstants.PARAM_TITLE);
                        this.c = jSONObject.optString(XiaomiOAuthConstants.EXTRA_CODE_2);
                        this.b = jSONObject.optString(SocialConstants.PARAM_URL);
                        this.d = jSONObject.optString("fileName");
                        this.f = jSONObject.optLong("lLocalLength");
                        this.g = jSONObject.optLong("lRemoteLength");
                        this.l = jSONObject.optInt("mState");
                        this.e = jSONObject.optString(C2537j.aq);
                        this.h = jSONObject.optString("localPath");
                        this.f2532m = jSONObject.optString("vMapFileNames");
                        this.i = jSONObject.optInt("isSheng");
                        this.j = jSONObject.optInt("mCompleteCode");
                        this.k = jSONObject.optString("mCityCode");
                    }
                }
            } catch (Throwable e) {
                ce.m3829a(e, "UpdateItem", "readFileToJSONObject");
                e.printStackTrace();
            }
        }
    }

    public String m4207c() {
        return this.f2532m;
    }

    public void m4208d() {
        OutputStreamWriter outputStreamWriter;
        Throwable e;
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(SocialConstants.PARAM_TITLE, this.a);
            jSONObject2.put(XiaomiOAuthConstants.EXTRA_CODE_2, this.c);
            jSONObject2.put(SocialConstants.PARAM_URL, this.b);
            jSONObject2.put("fileName", this.d);
            jSONObject2.put("lLocalLength", this.f);
            jSONObject2.put("lRemoteLength", this.g);
            jSONObject2.put("mState", this.l);
            jSONObject2.put(C2537j.aq, this.e);
            jSONObject2.put("localPath", this.h);
            if (this.f2532m != null) {
                jSONObject2.put("vMapFileNames", this.f2532m);
            }
            jSONObject2.put("isSheng", this.i);
            jSONObject2.put("mCompleteCode", this.j);
            jSONObject2.put("mCityCode", this.k);
            jSONObject.put(UriUtil.LOCAL_FILE_SCHEME, jSONObject2);
            File file = new File(this.d + ".dt");
            file.delete();
            try {
                outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, true), "utf-8");
                try {
                    outputStreamWriter.write(jSONObject.toString());
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    try {
                        ce.m3829a(e, "UpdateItem", "saveJSONObjectToFile");
                        e.printStackTrace();
                        if (outputStreamWriter != null) {
                            try {
                                outputStreamWriter.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                    } catch (Throwable th) {
                        e = th;
                        if (outputStreamWriter != null) {
                            try {
                                outputStreamWriter.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw e;
                    }
                }
            } catch (IOException e5) {
                e = e5;
                outputStreamWriter = null;
                ce.m3829a(e, "UpdateItem", "saveJSONObjectToFile");
                e.printStackTrace();
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
            } catch (Throwable th2) {
                e = th2;
                outputStreamWriter = null;
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                throw e;
            }
        } catch (Throwable e6) {
            ce.m3829a(e6, "UpdateItem", "saveJSONObjectToFile parseJson");
            e6.printStackTrace();
        }
    }
}
