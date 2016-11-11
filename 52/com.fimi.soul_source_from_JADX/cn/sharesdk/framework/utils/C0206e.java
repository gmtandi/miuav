package cn.sharesdk.framework.utils;

import android.content.Context;
import com.mob.commons.logcollector.LogsCollector;

/* renamed from: cn.sharesdk.framework.utils.e */
class C0206e extends LogsCollector {
    final /* synthetic */ int f377a;
    final /* synthetic */ String f378b;
    final /* synthetic */ C0205d f379c;

    C0206e(C0205d c0205d, Context context, int i, String str) {
        this.f379c = c0205d;
        this.f377a = i;
        this.f378b = str;
        super(context);
    }

    protected String getAppkey() {
        return this.f378b;
    }

    protected String getSDKTag() {
        return "SHARESDK";
    }

    protected int getSDKVersion() {
        return this.f377a;
    }
}
