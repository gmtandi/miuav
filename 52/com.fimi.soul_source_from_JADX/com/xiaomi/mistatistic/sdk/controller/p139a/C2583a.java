package com.xiaomi.mistatistic.sdk.controller.p139a;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.xiaomi.market.sdk.C2537j;
import com.xiaomi.mistatistic.sdk.controller.C2582e;
import com.xiaomi.mistatistic.sdk.controller.C2588a;
import com.xiaomi.mistatistic.sdk.controller.C2593g;
import com.xiaomi.mistatistic.sdk.controller.C2599m;
import com.xiaomi.mistatistic.sdk.controller.C2605s;
import com.xiaomi.mistatistic.sdk.controller.C2606t;
import com.xiaomi.mistatistic.sdk.data.C2617c;
import com.xiaomi.mistatistic.sdk.data.C2618e;
import java.util.Locale;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.a.a */
public class C2583a implements C2582e {
    public void m14698a() {
        Context a = C2588a.m14708a();
        if (!C2605s.m14782a(a, "basic_info_reported")) {
            m14699a(a);
            C2605s.m14783b(a, "basic_info_reported", 1);
            new C2606t().m14795a();
        }
        String e = C2588a.m14713e();
        Object a2 = C2605s.m14781a(a, "basic_info_version", C2915a.f14760f);
        if (!(TextUtils.isEmpty(a2) || a2.equals(e))) {
            C2599m.m14766a(new C2617c("mistat_basic", "upgrade"));
        }
        C2605s.m14785b(a, "basic_info_version", e);
    }

    public void m14699a(Context context) {
        C2599m.m14766a(new C2617c("mistat_basic", "new"));
        C2599m.m14766a(new C2618e("mistat_basic", "model", Build.MODEL));
        C2599m.m14766a(new C2618e("mistat_basic", "OS", "android" + VERSION.SDK_INT));
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (!TextUtils.isEmpty(telephonyManager.getNetworkOperatorName())) {
            C2599m.m14766a(new C2618e("mistat_basic", "operator", telephonyManager.getSimOperator()));
        }
        Object b = C2593g.m14739b(context);
        if (!TextUtils.isEmpty(b)) {
            C2599m.m14766a(new C2618e("mistat_basic", "IMEI", b));
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (displayMetrics != null) {
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            C2599m.m14766a(new C2618e("mistat_basic", C2537j.ai, i < i2 ? i + "x" + i2 : i2 + "x" + i));
        }
        C2599m.m14766a(new C2618e("mistat_basic", "locale", Locale.getDefault().toString()));
    }
}
