package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import com.mi.live.openlivesdk.C2116b;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.channel.commonutils.misc.C2464a;
import com.xiaomi.channel.commonutils.network.C2472a;
import com.xiaomi.channel.commonutils.string.C2476d;
import com.xiaomi.market.sdk.C2537j;
import com.xiaomi.market.sdk.C2539l;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.json.JSONObject;

/* renamed from: com.xiaomi.push.service.g */
public class C2651g {
    private static C2650f f13139a;
    private static String f13140b;
    private static String f13141c;
    private static String f13142d;

    static {
        f13140b = null;
        f13141c = null;
        f13142d = null;
    }

    public static synchronized C2650f m15021a(Context context) {
        C2650f c2650f = null;
        synchronized (C2651g.class) {
            if (f13139a != null) {
                c2650f = f13139a;
            } else {
                SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_account", 0);
                Object string = sharedPreferences.getString("uuid", null);
                Object string2 = sharedPreferences.getString("token", null);
                Object string3 = sharedPreferences.getString("security", null);
                String string4 = sharedPreferences.getString("app_id", null);
                String string5 = sharedPreferences.getString("app_token", null);
                String string6 = sharedPreferences.getString(C2539l.PACKAGE_NAME, null);
                Object string7 = sharedPreferences.getString("device_id", null);
                int i = sharedPreferences.getInt("env_type", 1);
                if (!TextUtils.isEmpty(string7) && string7.startsWith("a-")) {
                    string7 = C2651g.m15028e(context);
                    sharedPreferences.edit().putString("device_id", string7).commit();
                }
                if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3))) {
                    CharSequence e = C2651g.m15028e(context);
                    if ("com.xiaomi.xmsf".equals(context.getPackageName()) || TextUtils.isEmpty(e) || TextUtils.isEmpty(r8) || r8.equals(e)) {
                        f13139a = new C2650f(string, string2, string3, string4, string5, string6, i);
                        c2650f = f13139a;
                    } else {
                        C2463b.m14127c("erase the old account.");
                        C2651g.m15029f(context);
                    }
                }
            }
        }
        return c2650f;
    }

    public static synchronized C2650f m15022a(Context context, String str, String str2, String str3) {
        C2650f c2650f = null;
        synchronized (C2651g.class) {
            PackageInfo packageInfo;
            List arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("devid", C2651g.m15025b(context)));
            String str4 = C2651g.m15030g(context) ? "1000271" : str2;
            String str5 = C2651g.m15030g(context) ? "420100086271" : str3;
            String str6 = C2651g.m15030g(context) ? "com.xiaomi.xmsf" : str;
            arrayList.add(new BasicNameValuePair(SocialConstants.PARAM_APP_ID, str4));
            arrayList.add(new BasicNameValuePair("apptoken", str5));
            try {
                packageInfo = context.getPackageManager().getPackageInfo(str6, Opcodes.ACC_ENUM);
            } catch (Throwable e) {
                C2463b.m14125a(e);
                packageInfo = null;
            }
            arrayList.add(new BasicNameValuePair("appversion", packageInfo != null ? String.valueOf(packageInfo.versionCode) : Constants.VIA_RESULT_SUCCESS));
            arrayList.add(new BasicNameValuePair("sdkversion", Constants.VIA_SSO_LOGIN));
            arrayList.add(new BasicNameValuePair(C2116b.f11124g, str6));
            arrayList.add(new BasicNameValuePair("model", Build.MODEL));
            arrayList.add(new BasicNameValuePair("imei", C2651g.m15026c(context)));
            arrayList.add(new BasicNameValuePair(C2537j.ac, VERSION.RELEASE + "-" + VERSION.INCREMENTAL));
            String a = C2472a.m14144a(context, C2651g.m15023a(), arrayList);
            if (!TextUtils.isEmpty(a)) {
                JSONObject jSONObject = new JSONObject(a);
                if (jSONObject.getInt(XiaomiOAuthConstants.EXTRA_CODE_2) == 0) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(UriUtil.DATA_SCHEME);
                    c2650f = new C2650f(jSONObject2.getString("userId") + "@xiaomi.com/an" + C2476d.m14165a(6), jSONObject2.getString("token"), jSONObject2.getString("ssecurity"), str4, str5, str6, C2464a.m14132c());
                    C2651g.m15024a(context, c2650f);
                    f13139a = c2650f;
                } else {
                    C2654j.m15037a(context, jSONObject.getInt(XiaomiOAuthConstants.EXTRA_CODE_2), jSONObject.optString(SocialConstants.PARAM_COMMENT));
                    C2463b.m14123a(a);
                }
            }
        }
        return c2650f;
    }

    public static String m15023a() {
        if (C2464a.m14131b()) {
            return "http://10.237.12.17:9085/pass/register";
        }
        return "https://" + (C2464a.m14130a() ? "sandbox.xmpush.xiaomi.com" : "register.xmpush.xiaomi.com") + "/pass/register";
    }

    private static void m15024a(Context context, C2650f c2650f) {
        Editor edit = context.getSharedPreferences("mipush_account", 0).edit();
        edit.putString("uuid", c2650f.f13132a);
        edit.putString("security", c2650f.f13134c);
        edit.putString("token", c2650f.f13133b);
        edit.putString("app_id", c2650f.f13135d);
        edit.putString(C2539l.PACKAGE_NAME, c2650f.f13137f);
        edit.putString("app_token", c2650f.f13136e);
        edit.putString("device_id", C2651g.m15028e(context));
        edit.putInt("env_type", c2650f.f13138g);
        edit.commit();
    }

    protected static String m15025b(Context context) {
        String str = null;
        if (f13141c == null) {
            String string;
            String c = C2651g.m15026c(context);
            try {
                string = Secure.getString(context.getContentResolver(), "android_id");
            } catch (Throwable th) {
                C2463b.m14125a(th);
                string = null;
            }
            if (VERSION.SDK_INT > 8) {
                str = Build.SERIAL;
            }
            f13141c = "a-" + C2476d.m14166a(c + string + str);
        }
        return f13141c;
    }

    public static String m15026c(Context context) {
        if (f13140b != null) {
            return f13140b;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String deviceId = telephonyManager.getDeviceId();
            int i = 10;
            while (deviceId == null) {
                int i2 = i - 1;
                if (i <= 0) {
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                deviceId = telephonyManager.getDeviceId();
                i = i2;
            }
            if (deviceId != null) {
                f13140b = deviceId;
            }
            return deviceId;
        } catch (Throwable th) {
            C2463b.m14125a(th);
            return null;
        }
    }

    public static String m15027d(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimOperatorName();
    }

    public static synchronized String m15028e(Context context) {
        String str;
        synchronized (C2651g.class) {
            if (f13142d != null) {
                str = f13142d;
            } else {
                String string;
                try {
                    string = Secure.getString(context.getContentResolver(), "android_id");
                } catch (Throwable th) {
                    C2463b.m14125a(th);
                    string = null;
                }
                f13142d = C2476d.m14166a(string + (VERSION.SDK_INT > 8 ? Build.SERIAL : null));
                str = f13142d;
            }
        }
        return str;
    }

    public static void m15029f(Context context) {
        context.getSharedPreferences("mipush_account", 0).edit().clear().commit();
        f13139a = null;
    }

    private static boolean m15030g(Context context) {
        return context.getPackageName().equals("com.xiaomi.xmsf");
    }
}
