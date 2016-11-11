package it.sephiroth.android.library.p143a;

import android.os.Build.VERSION;
import android.view.View;
import it.sephiroth.android.library.p143a.p145b.C2817a;
import it.sephiroth.android.library.p143a.p146c.C2819a;

/* renamed from: it.sephiroth.android.library.a.b */
public class C2818b {
    private static final String f14322a = "ViewHelper";

    public static final C2815c m16051a(View view) {
        int i = VERSION.SDK_INT;
        return i >= 16 ? new C2819a(view) : i >= 14 ? new C2817a(view) : new C2816d(view);
    }
}
