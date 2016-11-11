package p000a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/* renamed from: a.k */
public final class C0011k {
    static final String f70a = "al_applink_data";
    static final String f71b = "extras";
    static final String f72c = "target_url";

    public static Uri m60a(Context context, Intent intent) {
        Bundle a = C0011k.m61a(intent);
        if (a == null) {
            return null;
        }
        String string = a.getString(f72c);
        if (string == null) {
            return null;
        }
        C0017r.m74a(context, C0017r.f84e, intent, null);
        return Uri.parse(string);
    }

    public static Bundle m61a(Intent intent) {
        return intent.getBundleExtra(f70a);
    }

    public static Bundle m62b(Intent intent) {
        Bundle a = C0011k.m61a(intent);
        return a == null ? null : a.getBundle(f71b);
    }

    public static Uri m63c(Intent intent) {
        Bundle a = C0011k.m61a(intent);
        if (a != null) {
            String string = a.getString(f72c);
            if (string != null) {
                return Uri.parse(string);
            }
        }
        return intent.getData();
    }
}
