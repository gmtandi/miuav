package com.tencent.open.utils;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.p133a.C2333f;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.p152c.p156c.C2951i;

public class OpenConfig {
    private static HashMap<String, OpenConfig> f12097a;
    private static String f12098b;
    private Context f12099c;
    private String f12100d;
    private JSONObject f12101e;
    private long f12102f;
    private int f12103g;
    private boolean f12104h;

    /* renamed from: com.tencent.open.utils.OpenConfig.1 */
    class C23611 extends Thread {
        final /* synthetic */ Bundle f12095a;
        final /* synthetic */ OpenConfig f12096b;

        C23611(OpenConfig openConfig, Bundle bundle) {
            this.f12096b = openConfig;
            this.f12095a = bundle;
        }

        public void run() {
            try {
                this.f12096b.m13841a(Util.parseJson(HttpUtils.openUrl2(this.f12096b.f12099c, "http://cgi.connect.qq.com/qqconnectopen/openapi/policy_conf", C2951i.f14860a, this.f12095a).response));
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.f12096b.f12103g = 0;
        }
    }

    static {
        f12097a = null;
        f12098b = null;
    }

    private OpenConfig(Context context, String str) {
        this.f12099c = null;
        this.f12100d = null;
        this.f12101e = null;
        this.f12102f = 0;
        this.f12103g = 0;
        this.f12104h = true;
        this.f12099c = context.getApplicationContext();
        this.f12100d = str;
        m13838a();
        m13842b();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m13837a(java.lang.String r6) {
        /*
        r5 = this;
        r1 = "";
        r0 = r5.f12100d;	 Catch:{ FileNotFoundException -> 0x0052 }
        if (r0 == 0) goto L_0x0050;
    L_0x0006:
        r0 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x0052 }
        r0.<init>();	 Catch:{ FileNotFoundException -> 0x0052 }
        r0 = r0.append(r6);	 Catch:{ FileNotFoundException -> 0x0052 }
        r2 = ".";
        r0 = r0.append(r2);	 Catch:{ FileNotFoundException -> 0x0052 }
        r2 = r5.f12100d;	 Catch:{ FileNotFoundException -> 0x0052 }
        r0 = r0.append(r2);	 Catch:{ FileNotFoundException -> 0x0052 }
        r0 = r0.toString();	 Catch:{ FileNotFoundException -> 0x0052 }
    L_0x001f:
        r2 = r5.f12099c;	 Catch:{ FileNotFoundException -> 0x0052 }
        r0 = r2.openFileInput(r0);	 Catch:{ FileNotFoundException -> 0x0052 }
    L_0x0025:
        r3 = new java.io.BufferedReader;
        r2 = new java.io.InputStreamReader;
        r4 = "UTF-8";
        r4 = java.nio.charset.Charset.forName(r4);
        r2.<init>(r0, r4);
        r3.<init>(r2);
        r2 = new java.lang.StringBuffer;
        r2.<init>();
    L_0x003a:
        r4 = r3.readLine();	 Catch:{ IOException -> 0x0044 }
        if (r4 == 0) goto L_0x0064;
    L_0x0040:
        r2.append(r4);	 Catch:{ IOException -> 0x0044 }
        goto L_0x003a;
    L_0x0044:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x007c }
        r0.close();	 Catch:{ IOException -> 0x0076 }
        r3.close();	 Catch:{ IOException -> 0x0076 }
        r0 = r1;
    L_0x004f:
        return r0;
    L_0x0050:
        r0 = r6;
        goto L_0x001f;
    L_0x0052:
        r0 = move-exception;
        r0 = r5.f12099c;	 Catch:{ IOException -> 0x005e }
        r0 = r0.getAssets();	 Catch:{ IOException -> 0x005e }
        r0 = r0.open(r6);	 Catch:{ IOException -> 0x005e }
        goto L_0x0025;
    L_0x005e:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x004f;
    L_0x0064:
        r1 = r2.toString();	 Catch:{ IOException -> 0x0044 }
        r0.close();	 Catch:{ IOException -> 0x0070 }
        r3.close();	 Catch:{ IOException -> 0x0070 }
        r0 = r1;
        goto L_0x004f;
    L_0x0070:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x004f;
    L_0x0076:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x004f;
    L_0x007c:
        r1 = move-exception;
        r0.close();	 Catch:{ IOException -> 0x0084 }
        r3.close();	 Catch:{ IOException -> 0x0084 }
    L_0x0083:
        throw r1;
    L_0x0084:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0083;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.utils.OpenConfig.a(java.lang.String):java.lang.String");
    }

    private void m13838a() {
        try {
            this.f12101e = new JSONObject(m13837a("com.tencent.open.config.json"));
        } catch (JSONException e) {
            this.f12101e = new JSONObject();
        }
    }

    private void m13840a(String str, String str2) {
        try {
            if (this.f12100d != null) {
                str = str + "." + this.f12100d;
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.f12099c.openFileOutput(str, 0), Charset.forName(C1142e.f5201a));
            outputStreamWriter.write(str2);
            outputStreamWriter.flush();
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void m13841a(JSONObject jSONObject) {
        m13843b("cgi back, do update");
        this.f12101e = jSONObject;
        m13840a("com.tencent.open.config.json", jSONObject.toString());
        this.f12102f = SystemClock.elapsedRealtime();
    }

    private void m13842b() {
        if (this.f12103g != 0) {
            m13843b("update thread is running, return");
            return;
        }
        this.f12103g = 1;
        Bundle bundle = new Bundle();
        bundle.putString(SocialConstants.PARAM_APP_ID, this.f12100d);
        bundle.putString("appid_for_getting_config", this.f12100d);
        bundle.putString("status_os", VERSION.RELEASE);
        bundle.putString("status_machine", Build.MODEL);
        bundle.putString("status_version", VERSION.SDK);
        bundle.putString("sdkv", Constants.SDK_VERSION);
        bundle.putString("sdkp", "a");
        new C23611(this, bundle).start();
    }

    private void m13843b(String str) {
        if (this.f12104h) {
            C2333f.m13754b("OpenConfig", str + "; appid: " + this.f12100d);
        }
    }

    private void m13844c() {
        int optInt = this.f12101e.optInt("Common_frequency");
        if (optInt == 0) {
            optInt = 1;
        }
        if (SystemClock.elapsedRealtime() - this.f12102f >= ((long) (optInt * 3600000))) {
            m13842b();
        }
    }

    public static OpenConfig getInstance(Context context, String str) {
        if (f12097a == null) {
            f12097a = new HashMap();
        }
        if (str != null) {
            f12098b = str;
        }
        if (str == null) {
            str = f12098b != null ? f12098b : Constants.VIA_RESULT_SUCCESS;
        }
        OpenConfig openConfig = (OpenConfig) f12097a.get(str);
        if (openConfig != null) {
            return openConfig;
        }
        openConfig = new OpenConfig(context, str);
        f12097a.put(str, openConfig);
        return openConfig;
    }

    public boolean getBoolean(String str) {
        m13843b("get " + str);
        m13844c();
        Object opt = this.f12101e.opt(str);
        if (opt == null) {
            return false;
        }
        if (!(opt instanceof Integer)) {
            return opt instanceof Boolean ? ((Boolean) opt).booleanValue() : false;
        } else {
            return !opt.equals(Integer.valueOf(0));
        }
    }

    public int getInt(String str) {
        m13843b("get " + str);
        m13844c();
        return this.f12101e.optInt(str);
    }

    public long getLong(String str) {
        m13843b("get " + str);
        m13844c();
        return this.f12101e.optLong(str);
    }
}
