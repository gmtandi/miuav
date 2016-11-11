package com.android.volley;

import android.content.Intent;

/* renamed from: com.android.volley.a */
public class C0553a extends ag {
    private Intent f3496b;

    public C0553a(Intent intent) {
        this.f3496b = intent;
    }

    public C0553a(C0566n c0566n) {
        super(c0566n);
    }

    public C0553a(String str) {
        super(str);
    }

    public C0553a(String str, Exception exception) {
        super(str, exception);
    }

    public Intent m5046a() {
        return this.f3496b;
    }

    public String getMessage() {
        return this.f3496b != null ? "User needs to (re)enter credentials." : super.getMessage();
    }
}
