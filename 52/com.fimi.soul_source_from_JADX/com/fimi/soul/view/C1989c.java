package com.fimi.soul.view;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import com.fimi.kernel.view.dialog.C1198a;
import com.fimi.kernel.view.dialog.C1200c;
import com.fimi.soul.C1205R;

/* renamed from: com.fimi.soul.view.c */
public class C1989c implements C1200c {
    private C2000i f10741c;
    private C2003l f10742d;
    private C1997f f10743e;

    public Dialog m12809a(Context context, int i, String str, String str2, C1198a c1198a) {
        return m12812a(context, str, str2, c1198a);
    }

    public Dialog m12810a(Context context, String str, int i, String str2, String str3, C1198a c1198a) {
        return m12811a(context, str, null, i, str2, str3, c1198a);
    }

    public Dialog m12811a(Context context, String str, String str2, int i, String str3, String str4, C1198a c1198a) {
        if (this.f10741c == null) {
            this.f10741c = new C2000i(this, context, str, str2, i, str3, str4, c1198a);
        }
        if (!this.f10741c.isShowing()) {
            this.f10741c.show();
        }
        return this.f10741c;
    }

    public Dialog m12812a(Context context, String str, String str2, C1198a c1198a) {
        Builder negativeButton = new Builder(context).setTitle(str).setMessage(str2).setNegativeButton(context.getString(C1205R.string.confirm), new C1995d(this, c1198a));
        if (c1198a != null) {
            negativeButton.setPositiveButton(context.getString(C1205R.string.cancel), new C1996e(this, c1198a));
        }
        return negativeButton.show();
    }

    public Dialog m12813a(Context context, String str, String str2, String str3, Boolean bool, String str4, C1198a c1198a) {
        if (this.f10743e == null) {
            this.f10743e = new C1997f(this, context, str, str2, str3, bool, str4, c1198a);
        }
        if (!this.f10743e.isShowing()) {
            this.f10743e.show();
        }
        return this.f10743e;
    }

    public Dialog m12814b(Context context, String str, String str2, C1198a c1198a) {
        if (this.f10742d == null) {
            this.f10742d = new C2003l(this, context, str, str2, c1198a);
        }
        if (!this.f10742d.isShowing()) {
            this.f10742d.show();
        }
        return this.f10742d;
    }
}
