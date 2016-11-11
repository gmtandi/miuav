package com.mining.app.zxing.p126a;

import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.os.Handler;
import android.util.Log;

/* renamed from: com.mining.app.zxing.a.f */
final class C2127f implements PreviewCallback {
    private static final String f11186a;
    private final C2123b f11187b;
    private final boolean f11188c;
    private Handler f11189d;
    private int f11190e;

    static {
        f11186a = C2127f.class.getSimpleName();
    }

    C2127f(C2123b c2123b, boolean z) {
        this.f11187b = c2123b;
        this.f11188c = z;
    }

    void m13096a(Handler handler, int i) {
        this.f11189d = handler;
        this.f11190e = i;
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        Point a = this.f11187b.m13064a();
        if (!this.f11188c) {
            camera.setPreviewCallback(null);
        }
        if (this.f11189d != null) {
            this.f11189d.obtainMessage(this.f11190e, a.x, a.y, bArr).sendToTarget();
            this.f11189d = null;
            return;
        }
        Log.d(f11186a, "Got preview callback, but no handler for it");
    }
}
