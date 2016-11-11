package com.mining.app.zxing.p127b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.fimi.soul.C1205R;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.mining.app.zxing.activity.MipcaActivityCapture;
import com.mining.app.zxing.p126a.C2124c;
import com.mining.app.zxing.p126a.C2126e;
import java.util.Hashtable;

/* renamed from: com.mining.app.zxing.b.d */
final class C2133d extends Handler {
    private static final String f11215a;
    private final MipcaActivityCapture f11216b;
    private final MultiFormatReader f11217c;

    static {
        f11215a = C2133d.class.getSimpleName();
    }

    C2133d(MipcaActivityCapture mipcaActivityCapture, Hashtable<DecodeHintType, Object> hashtable) {
        this.f11217c = new MultiFormatReader();
        this.f11217c.setHints(hashtable);
        this.f11216b = mipcaActivityCapture;
    }

    private void m13107a(byte[] bArr, int i, int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        Result result = null;
        byte[] bArr2 = new byte[bArr.length];
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                bArr2[(((i4 * i2) + i2) - i3) - 1] = bArr[(i3 * i) + i4];
            }
        }
        C2126e a = C2124c.m13071a().m13074a(bArr2, i2, i);
        try {
            result = this.f11217c.decodeWithState(new BinaryBitmap(new HybridBinarizer(a)));
        } catch (ReaderException e) {
        } finally {
            a = this.f11217c;
            a.reset();
        }
        if (result != null) {
            Log.d(f11215a, "Found barcode (" + (System.currentTimeMillis() - currentTimeMillis) + " ms):\n" + result.toString());
            Message obtain = Message.obtain(this.f11216b.m13100b(), C1205R.id.decode_succeeded, result);
            Bundle bundle = new Bundle();
            bundle.putParcelable(C2134e.f11218a, a.m13095c());
            obtain.setData(bundle);
            obtain.sendToTarget();
            return;
        }
        Message.obtain(this.f11216b.m13100b(), C1205R.id.decode_failed).sendToTarget();
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case C1205R.id.decode:
                m13107a((byte[]) message.obj, message.arg1, message.arg2);
            case C1205R.id.quit:
                Looper.myLooper().quit();
            default:
        }
    }
}
