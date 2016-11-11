package com.mining.app.zxing.p126a;

import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.os.Handler;
import android.util.Log;

/* renamed from: com.mining.app.zxing.a.a */
final class C2122a implements AutoFocusCallback {
    private static final String f11146a;
    private static final long f11147b = 1500;
    private Handler f11148c;
    private int f11149d;

    static {
        f11146a = C2122a.class.getSimpleName();
    }

    C2122a() {
    }

    void m13057a(Handler handler, int i) {
        this.f11148c = handler;
        this.f11149d = i;
    }

    public void onAutoFocus(boolean z, Camera camera) {
        if (this.f11148c != null) {
            this.f11148c.sendMessageDelayed(this.f11148c.obtainMessage(this.f11149d, Boolean.valueOf(z)), f11147b);
            this.f11148c = null;
            return;
        }
        Log.d(f11146a, "Got auto-focus callback, but no handler for it");
    }
}
