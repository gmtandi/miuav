package com.mining.app.zxing.p127b;

import android.os.Handler;
import android.os.Looper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultPointCallback;
import com.mining.app.zxing.activity.MipcaActivityCapture;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

/* renamed from: com.mining.app.zxing.b.e */
final class C2134e extends Thread {
    public static final String f11218a = "barcode_bitmap";
    private final MipcaActivityCapture f11219b;
    private final Hashtable<DecodeHintType, Object> f11220c;
    private Handler f11221d;
    private final CountDownLatch f11222e;

    C2134e(MipcaActivityCapture mipcaActivityCapture, Vector<BarcodeFormat> vector, String str, ResultPointCallback resultPointCallback) {
        Object vector2;
        this.f11219b = mipcaActivityCapture;
        this.f11222e = new CountDownLatch(1);
        this.f11220c = new Hashtable(3);
        if (vector == null || vector.isEmpty()) {
            vector2 = new Vector();
            vector2.addAll(C2132c.f11211b);
        }
        this.f11220c.put(DecodeHintType.POSSIBLE_FORMATS, vector2);
        if (str != null) {
            this.f11220c.put(DecodeHintType.CHARACTER_SET, str);
        }
        this.f11220c.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, resultPointCallback);
    }

    Handler m13108a() {
        try {
            this.f11222e.await();
        } catch (InterruptedException e) {
        }
        return this.f11221d;
    }

    public void run() {
        Looper.prepare();
        this.f11221d = new C2133d(this.f11219b, this.f11220c);
        this.f11222e.countDown();
        Looper.loop();
    }
}
