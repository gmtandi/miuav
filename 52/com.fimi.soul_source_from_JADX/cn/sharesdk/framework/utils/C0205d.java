package cn.sharesdk.framework.utils;

import android.content.Context;
import com.mob.tools.log.NLog;

/* renamed from: cn.sharesdk.framework.utils.d */
public class C0205d extends NLog {
    private C0205d(Context context, int i, String str) {
        NLog.setCollector("SHARESDK", new C0206e(this, context, i, str));
    }

    public static NLog m752a() {
        return NLog.getInstanceForSDK("SHARESDK", true);
    }

    public static NLog m753a(Context context, int i, String str) {
        return new C0205d(context, i, str);
    }

    protected String getSDKTag() {
        return "SHARESDK";
    }
}
