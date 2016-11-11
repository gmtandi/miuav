package com.xiaomi.mistatistic.sdk;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mistatistic.sdk.controller.C2588a;
import com.xiaomi.mistatistic.sdk.controller.C2589b;
import com.xiaomi.mistatistic.sdk.controller.C2593g;
import com.xiaomi.mistatistic.sdk.controller.C2595i;
import com.xiaomi.mistatistic.sdk.controller.C2599m;
import com.xiaomi.mistatistic.sdk.controller.C2601o;
import com.xiaomi.mistatistic.sdk.controller.C2605s;
import com.xiaomi.mistatistic.sdk.controller.C2606t;
import com.xiaomi.mistatistic.sdk.controller.C2610x;
import com.xiaomi.mistatistic.sdk.controller.ab;
import com.xiaomi.mistatistic.sdk.controller.p139a.C2583a;
import com.xiaomi.mistatistic.sdk.data.C2615d;
import com.xiaomi.mistatistic.sdk.data.C2616b;
import com.xiaomi.mistatistic.sdk.data.C2617c;
import com.xiaomi.mistatistic.sdk.data.C2618e;
import java.util.Map;
import org.p122a.p123a.C2915a;

public abstract class MiStatInterface {
    public static final long MAX_UPLOAD_INTERVAL = 86400000;
    public static final long MIN_UPLOAD_INTERVAL = 300000;
    public static final int UPLOAD_POLICY_BATCH = 2;
    public static final int UPLOAD_POLICY_DEVELOPMENT = 5;
    public static final int UPLOAD_POLICY_INTERVAL = 4;
    public static final int UPLOAD_POLICY_REALTIME = 0;
    public static final int UPLOAD_POLICY_WHILE_INITIALIZE = 3;
    public static final int UPLOAD_POLICY_WIFI_ONLY = 1;
    private static boolean sInitialized;

    static {
        sInitialized = false;
    }

    private static void checkCategoryAndKey(String str, String str2) {
        if (!TextUtils.isEmpty(str) && str.startsWith("mistat_")) {
            throw new IllegalArgumentException("category cannot start with mistat_");
        } else if (!TextUtils.isEmpty(str2) && str2.startsWith("mistat_")) {
            throw new IllegalArgumentException("key cannot start with mistat_");
        }
    }

    private static void checkInitialized() {
        if (!sInitialized) {
            throw new IllegalStateException("not initialized, do you forget to call initialize when application started?");
        }
    }

    public static void enableExceptionCatcher(boolean z) {
        C2579a.m14691a(false);
        C2579a.m14689a(z ? UPLOAD_POLICY_BATCH : UPLOAD_POLICY_WHILE_INITIALIZE);
    }

    public static final void enableLog() {
        C2601o.m14768a();
    }

    public static final String getDeviceID(Context context) {
        return C2593g.m14736a(context);
    }

    public static final void initialize(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("appID or appKey is empty.");
        }
        Context applicationContext = context.getApplicationContext();
        if (str3 == null) {
            str3 = C2915a.f14760f;
        }
        C2588a.m14709a(applicationContext, str, str2, str3);
        C2595i.m14745a();
        new C2593g().m14742a();
        ab.m14718a().m14721b();
        sInitialized = true;
    }

    public static boolean isExceptionCatcherEnabled() {
        return C2579a.m14695d() != UPLOAD_POLICY_WIFI_ONLY;
    }

    public static final void recordCalculateEvent(String str, String str2, long j) {
        recordCalculateEvent(str, str2, j, null);
    }

    public static final void recordCalculateEvent(String str, String str2, long j, Map map) {
        checkInitialized();
        checkCategoryAndKey(str, str2);
        if (C2605s.m14782a(C2588a.m14708a(), "basic_info_reported")) {
            C2599m.m14766a(new C2616b(TextUtils.isEmpty(str) ? "mistat_default" : str, str2, j, map));
        }
    }

    public static final void recordCountEvent(String str, String str2) {
        recordCountEvent(str, str2, null);
    }

    public static final void recordCountEvent(String str, String str2, Map map) {
        checkInitialized();
        checkCategoryAndKey(str, str2);
        if (C2605s.m14782a(C2588a.m14708a(), "basic_info_reported")) {
            if (TextUtils.isEmpty(str)) {
                str = "mistat_default";
            }
            C2599m.m14766a(new C2617c(str, str2, map));
        }
    }

    public static final void recordNumericPropertyEvent(String str, String str2, long j) {
        checkInitialized();
        checkCategoryAndKey(str, str2);
        if (C2605s.m14782a(C2588a.m14708a(), "basic_info_reported")) {
            if (TextUtils.isEmpty(str)) {
                str = "mistat_default";
            }
            C2599m.m14766a(new C2615d(str, str2, j));
        }
    }

    public static final void recordPageEnd() {
        checkInitialized();
        C2610x.m14799a().m14808b();
    }

    public static final void recordPageStart(Activity activity, String str) {
        checkInitialized();
        C2610x.m14799a().m14807a(activity, str);
        C2589b.m14731a().m14734a(new C2583a());
    }

    public static final void recordStringPropertyEvent(String str, String str2, String str3) {
        checkInitialized();
        checkCategoryAndKey(str, str2);
        if (C2605s.m14782a(C2588a.m14708a(), "basic_info_reported")) {
            if (TextUtils.isEmpty(str)) {
                str = "mistat_default";
            }
            C2599m.m14766a(new C2618e(str, str2, str3));
        }
    }

    public static final void setUploadPolicy(int i, long j) {
        checkInitialized();
        if (i != UPLOAD_POLICY_INTERVAL || (j >= MIN_UPLOAD_INTERVAL && j <= MAX_UPLOAD_INTERVAL)) {
            ab.m14718a().m14720a(i, j);
            return;
        }
        throw new IllegalArgumentException("interval should be set between 5 minutes and 1 day");
    }

    public static boolean shouldExceptionUploadImmediately() {
        return C2579a.m14695d() == UPLOAD_POLICY_BATCH;
    }

    public static final void triggerUploadManually() {
        checkInitialized();
        new C2606t().m14795a();
    }
}
