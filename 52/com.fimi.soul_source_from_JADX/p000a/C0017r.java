package p000a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.tencent.open.GameAppOperation;
import com.tencent.open.SocialConstants;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: a.r */
public class C0017r {
    public static final String f80a = "com.parse.bolts.measurement_event";
    public static final String f81b = "event_name";
    public static final String f82c = "event_args";
    public static final String f83d = "al_nav_out";
    public static final String f84e = "al_nav_in";
    private Context f85f;
    private String f86g;
    private Bundle f87h;

    private C0017r(Context context, String str, Bundle bundle) {
        this.f85f = context.getApplicationContext();
        this.f86g = str;
        this.f87h = bundle;
    }

    private static Bundle m71a(Context context, String str, Bundle bundle, Intent intent) {
        Bundle bundle2 = new Bundle();
        ComponentName resolveActivity = intent.resolveActivity(context.getPackageManager());
        if (resolveActivity != null) {
            bundle2.putString("class", resolveActivity.getShortClassName());
        }
        if (f83d.equals(str)) {
            if (resolveActivity != null) {
                bundle2.putString("package", resolveActivity.getPackageName());
            }
            if (intent.getData() != null) {
                bundle2.putString("outputURL", intent.getData().toString());
            }
            if (intent.getScheme() != null) {
                bundle2.putString("outputURLScheme", intent.getScheme());
            }
        } else if (f84e.equals(str)) {
            if (intent.getData() != null) {
                bundle2.putString("inputURL", intent.getData().toString());
            }
            if (intent.getScheme() != null) {
                bundle2.putString("inputURLScheme", intent.getScheme());
            }
        }
        for (String str2 : bundle.keySet()) {
            Object obj = bundle.get(str2);
            String a;
            if (obj instanceof Bundle) {
                for (String a2 : ((Bundle) obj).keySet()) {
                    String a3 = C0017r.m72a(((Bundle) obj).get(a2));
                    if (str2.equals("referer_app_link")) {
                        if (a2.equalsIgnoreCase(SocialConstants.PARAM_URL)) {
                            bundle2.putString("refererURL", a3);
                        } else if (a2.equalsIgnoreCase(GameAppOperation.QQFAV_DATALINE_APPNAME)) {
                            bundle2.putString("refererAppName", a3);
                        } else if (a2.equalsIgnoreCase("package")) {
                            bundle2.putString("sourceApplication", a3);
                        }
                    }
                    bundle2.putString(str2 + "/" + a2, a3);
                }
            } else {
                a2 = C0017r.m72a(obj);
                if (str2.equals("target_url")) {
                    Uri parse = Uri.parse(a2);
                    bundle2.putString("targetURL", parse.toString());
                    bundle2.putString("targetURLHost", parse.getHost());
                } else {
                    bundle2.putString(str2, a2);
                }
            }
        }
        return bundle2;
    }

    private static String m72a(Object obj) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof JSONArray) || (obj instanceof JSONObject)) {
            return obj.toString();
        }
        try {
            return obj instanceof Collection ? new JSONArray((Collection) obj).toString() : obj instanceof Map ? new JSONObject((Map) obj).toString() : obj.toString();
        } catch (Exception e) {
            return null;
        }
    }

    private void m73a() {
        if (this.f86g == null) {
            Log.d(getClass().getName(), "Event name is required");
        }
        try {
            Class cls = Class.forName("android.support.v4.content.LocalBroadcastManager");
            Method method = cls.getMethod("getInstance", new Class[]{Context.class});
            Method method2 = cls.getMethod("sendBroadcast", new Class[]{Intent.class});
            Object invoke = method.invoke(null, new Object[]{this.f85f});
            Intent intent = new Intent(f80a);
            intent.putExtra(f81b, this.f86g);
            intent.putExtra(f82c, this.f87h);
            method2.invoke(invoke, new Object[]{intent});
        } catch (Exception e) {
            Log.d(getClass().getName(), "LocalBroadcastManager in android support library is required to raise bolts event.");
        }
    }

    static void m74a(Context context, String str, Intent intent, Map<String, String> map) {
        Bundle a;
        Bundle bundle = new Bundle();
        if (intent != null) {
            Bundle a2 = C0011k.m61a(intent);
            if (a2 != null) {
                a = C0017r.m71a(context, str, a2, intent);
                if (map != null) {
                    for (String str2 : map.keySet()) {
                        a.putString(str2, (String) map.get(str2));
                    }
                }
                new C0017r(context, str, a).m73a();
            }
            Uri data = intent.getData();
            if (data != null) {
                bundle.putString("intentData", data.toString());
            }
            a = intent.getExtras();
            if (a != null) {
                for (String str22 : a.keySet()) {
                    bundle.putString(str22, C0017r.m72a(a.get(str22)));
                }
            }
        }
        a = bundle;
        if (map != null) {
            for (String str222 : map.keySet()) {
                a.putString(str222, (String) map.get(str222));
            }
        }
        new C0017r(context, str, a).m73a();
    }
}
