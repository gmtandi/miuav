package com.mining.app.zxing.p127b;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.util.Log;
import com.fimi.soul.C1205R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.mining.app.zxing.activity.MipcaActivityCapture;
import com.mining.app.zxing.p126a.C2124c;
import com.mining.app.zxing.view.C2145a;
import java.util.Vector;

/* renamed from: com.mining.app.zxing.b.a */
public final class C2130a extends Handler {
    private static final String f11202a;
    private final MipcaActivityCapture f11203b;
    private final C2134e f11204c;
    private C2131b f11205d;

    static {
        f11202a = C2130a.class.getSimpleName();
    }

    public C2130a(MipcaActivityCapture mipcaActivityCapture, Vector<BarcodeFormat> vector, String str) {
        this.f11203b = mipcaActivityCapture;
        this.f11204c = new C2134e(mipcaActivityCapture, vector, str, new C2145a(mipcaActivityCapture.m13098a()));
        this.f11204c.start();
        this.f11205d = C2131b.SUCCESS;
        C2124c.m13071a().m13080c();
        m13102b();
    }

    private void m13102b() {
        if (this.f11205d == C2131b.SUCCESS) {
            this.f11205d = C2131b.PREVIEW;
            C2124c.m13071a().m13075a(this.f11204c.m13108a(), C1205R.id.decode);
            C2124c.m13071a().m13079b(this, C1205R.id.auto_focus);
            this.f11203b.m13101c();
        }
    }

    public void m13103a() {
        this.f11205d = C2131b.DONE;
        C2124c.m13071a().m13081d();
        Message.obtain(this.f11204c.m13108a(), C1205R.id.quit).sendToTarget();
        try {
            this.f11204c.join();
        } catch (InterruptedException e) {
        }
        removeMessages(C1205R.id.decode_succeeded);
        removeMessages(C1205R.id.decode_failed);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case C1205R.id.auto_focus:
                if (this.f11205d == C2131b.PREVIEW) {
                    C2124c.m13071a().m13079b(this, C1205R.id.auto_focus);
                }
            case C1205R.id.decode_failed:
                this.f11205d = C2131b.PREVIEW;
                C2124c.m13071a().m13075a(this.f11204c.m13108a(), C1205R.id.decode);
            case C1205R.id.decode_succeeded:
                Log.d(f11202a, "Got decode succeeded message");
                this.f11205d = C2131b.SUCCESS;
                Bundle data = message.getData();
                this.f11203b.m13099a((Result) message.obj, data == null ? null : (Bitmap) data.getParcelable(C2134e.f11218a));
            case C1205R.id.launch_product_query:
                Log.d(f11202a, "Got product query message");
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((String) message.obj));
                intent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
                this.f11203b.startActivity(intent);
            case C1205R.id.restart_preview:
                Log.d(f11202a, "Got restart preview message");
                m13102b();
            case C1205R.id.return_scan_result:
                Log.d(f11202a, "Got return scan result message");
                this.f11203b.setResult(-1, (Intent) message.obj);
                this.f11203b.finish();
            default:
        }
    }
}
