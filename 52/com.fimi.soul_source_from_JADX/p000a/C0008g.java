package p000a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.SparseArray;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: a.g */
public class C0008g {
    private static final String f53a = "user_agent";
    private static final String f54b = "version";
    private static final String f55c = "referer_app_link";
    private static final String f56d = "app_name";
    private static final String f57e = "package";
    private static final String f58f = "1.0";
    private static C0002j f59g;
    private final C0006e f60h;
    private final Bundle f61i;
    private final Bundle f62j;

    public C0008g(C0006e c0006e, Bundle bundle, Bundle bundle2) {
        if (c0006e == null) {
            throw new IllegalArgumentException("appLink must not be null.");
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        this.f60h = c0006e;
        this.f61i = bundle;
        this.f62j = bundle2;
    }

    public static C0010i m39a(Context context, C0006e c0006e) {
        return new C0008g(c0006e, null, null).m54a(context);
    }

    public static C0018s<C0010i> m40a(Context context, Uri uri) {
        return C0008g.m41a(context, uri, C0008g.m51c(context));
    }

    public static C0018s<C0010i> m41a(Context context, Uri uri, C0002j c0002j) {
        return c0002j.m12a(uri).m103c(new C0009h(context), C0018s.f89b);
    }

    public static C0018s<C0010i> m42a(Context context, String str) {
        return C0008g.m43a(context, str, C0008g.m51c(context));
    }

    public static C0018s<C0010i> m43a(Context context, String str, C0002j c0002j) {
        return C0008g.m41a(context, Uri.parse(str), c0002j);
    }

    public static C0018s<C0010i> m44a(Context context, URL url) {
        return C0008g.m45a(context, url, C0008g.m51c(context));
    }

    public static C0018s<C0010i> m45a(Context context, URL url, C0002j c0002j) {
        return C0008g.m41a(context, Uri.parse(url.toString()), c0002j);
    }

    private Object m46a(Object obj) {
        int i = 0;
        if (obj instanceof Bundle) {
            return m47a((Bundle) obj);
        }
        if (obj instanceof CharSequence) {
            return obj.toString();
        }
        if (obj instanceof List) {
            JSONArray jSONArray = new JSONArray();
            for (Object a : (List) obj) {
                jSONArray.put(m46a(a));
            }
            return jSONArray;
        } else if (obj instanceof SparseArray) {
            r1 = new JSONArray();
            SparseArray sparseArray = (SparseArray) obj;
            while (i < sparseArray.size()) {
                r1.put(sparseArray.keyAt(i), m46a(sparseArray.valueAt(i)));
                i++;
            }
            return r1;
        } else if (obj instanceof Character) {
            return obj.toString();
        } else {
            if (obj instanceof Boolean) {
                return obj;
            }
            if (obj instanceof Number) {
                return ((obj instanceof Double) || (obj instanceof Float)) ? Double.valueOf(((Number) obj).doubleValue()) : Long.valueOf(((Number) obj).longValue());
            } else {
                int length;
                if (obj instanceof boolean[]) {
                    r1 = new JSONArray();
                    boolean[] zArr = (boolean[]) obj;
                    length = zArr.length;
                    while (i < length) {
                        r1.put(m46a(Boolean.valueOf(zArr[i])));
                        i++;
                    }
                    return r1;
                } else if (obj instanceof char[]) {
                    r1 = new JSONArray();
                    char[] cArr = (char[]) obj;
                    length = cArr.length;
                    while (i < length) {
                        r1.put(m46a(Character.valueOf(cArr[i])));
                        i++;
                    }
                    return r1;
                } else if (obj instanceof CharSequence[]) {
                    r1 = new JSONArray();
                    CharSequence[] charSequenceArr = (CharSequence[]) obj;
                    length = charSequenceArr.length;
                    while (i < length) {
                        r1.put(m46a(charSequenceArr[i]));
                        i++;
                    }
                    return r1;
                } else if (obj instanceof double[]) {
                    r1 = new JSONArray();
                    double[] dArr = (double[]) obj;
                    length = dArr.length;
                    while (i < length) {
                        r1.put(m46a(Double.valueOf(dArr[i])));
                        i++;
                    }
                    return r1;
                } else if (obj instanceof float[]) {
                    r1 = new JSONArray();
                    float[] fArr = (float[]) obj;
                    length = fArr.length;
                    while (i < length) {
                        r1.put(m46a(Float.valueOf(fArr[i])));
                        i++;
                    }
                    return r1;
                } else if (obj instanceof int[]) {
                    r1 = new JSONArray();
                    int[] iArr = (int[]) obj;
                    length = iArr.length;
                    while (i < length) {
                        r1.put(m46a(Integer.valueOf(iArr[i])));
                        i++;
                    }
                    return r1;
                } else if (obj instanceof long[]) {
                    r1 = new JSONArray();
                    long[] jArr = (long[]) obj;
                    length = jArr.length;
                    while (i < length) {
                        r1.put(m46a(Long.valueOf(jArr[i])));
                        i++;
                    }
                    return r1;
                } else if (obj instanceof short[]) {
                    r1 = new JSONArray();
                    short[] sArr = (short[]) obj;
                    length = sArr.length;
                    while (i < length) {
                        r1.put(m46a(Short.valueOf(sArr[i])));
                        i++;
                    }
                    return r1;
                } else if (!(obj instanceof String[])) {
                    return null;
                } else {
                    r1 = new JSONArray();
                    String[] strArr = (String[]) obj;
                    length = strArr.length;
                    while (i < length) {
                        r1.put(m46a(strArr[i]));
                        i++;
                    }
                    return r1;
                }
            }
        }
    }

    private JSONObject m47a(Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            jSONObject.put(str, m46a(bundle.get(str)));
        }
        return jSONObject;
    }

    public static void m48a(C0002j c0002j) {
        f59g = c0002j;
    }

    private void m49a(Context context, Intent intent, C0010i c0010i, JSONException jSONException) {
        Map hashMap = new HashMap();
        if (jSONException != null) {
            hashMap.put(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, jSONException.getLocalizedMessage());
        }
        hashMap.put("success", c0010i.m59b() ? Constants.VIA_TO_TYPE_QQ_GROUP : Constants.VIA_RESULT_SUCCESS);
        hashMap.put(SocialConstants.PARAM_TYPE, c0010i.m58a());
        C0017r.m74a(context, C0017r.f83d, intent, hashMap);
    }

    private Bundle m50b(Context context) {
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        if (context != null) {
            String packageName = context.getPackageName();
            if (packageName != null) {
                bundle2.putString(f57e, packageName);
            }
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo != null) {
                packageName = context.getString(applicationInfo.labelRes);
                if (packageName != null) {
                    bundle2.putString(f56d, packageName);
                }
            }
        }
        bundle.putAll(m55b());
        bundle.putString("target_url", m53a().m32a().toString());
        bundle.putString(f54b, f58f);
        bundle.putString(f53a, "Bolts Android 1.1.4");
        bundle.putBundle(f55c, bundle2);
        bundle.putBundle("extras", m56c());
        return bundle;
    }

    private static C0002j m51c(Context context) {
        return C0008g.m52d() != null ? C0008g.m52d() : new af(context);
    }

    public static C0002j m52d() {
        return f59g;
    }

    public C0006e m53a() {
        return this.f60h;
    }

    public C0010i m54a(Context context) {
        Intent intent;
        Intent intent2;
        C0010i c0010i;
        PackageManager packageManager = context.getPackageManager();
        Bundle b = m50b(context);
        for (C0007f c0007f : m53a().m33b()) {
            intent = new Intent("android.intent.action.VIEW");
            if (c0007f.m35a() != null) {
                intent.setData(c0007f.m35a());
            } else {
                intent.setData(this.f60h.m32a());
            }
            intent.setPackage(c0007f.m38d());
            if (c0007f.m37c() != null) {
                intent.setClassName(c0007f.m38d(), c0007f.m37c());
            }
            intent.putExtra("al_applink_data", b);
            if (packageManager.resolveActivity(intent, AccessibilityNodeInfoCompat.ACTION_CUT) != null) {
                intent2 = intent;
                break;
            }
        }
        intent2 = null;
        C0010i c0010i2 = C0010i.FAILED;
        if (intent2 != null) {
            intent = intent2;
            c0010i = C0010i.APP;
        } else {
            Uri c = m53a().m34c();
            if (c != null) {
                try {
                    intent = new Intent("android.intent.action.VIEW", c.buildUpon().appendQueryParameter("al_applink_data", m47a(b).toString()).build());
                    c0010i = C0010i.WEB;
                } catch (Throwable e) {
                    m49a(context, intent2, C0010i.FAILED, e);
                    throw new RuntimeException(e);
                }
            }
            c0010i = c0010i2;
            intent = null;
        }
        m49a(context, intent, c0010i, null);
        if (intent != null) {
            context.startActivity(intent);
        }
        return c0010i;
    }

    public Bundle m55b() {
        return this.f62j;
    }

    public Bundle m56c() {
        return this.f61i;
    }
}
