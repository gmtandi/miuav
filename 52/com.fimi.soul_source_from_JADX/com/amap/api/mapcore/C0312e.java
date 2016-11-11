package com.amap.api.mapcore;

import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import com.amap.api.mapcore.util.ce;

/* renamed from: com.amap.api.mapcore.e */
class C0312e extends Handler {
    final /* synthetic */ AMapDelegateImp f1925a;

    C0312e(AMapDelegateImp aMapDelegateImp) {
        this.f1925a = aMapDelegateImp;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        try {
            if (this.f1925a.ac != null) {
                this.f1925a.ac.onTouch((MotionEvent) message.obj);
            }
        } catch (Throwable th) {
            ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "onTouchHandler");
            th.printStackTrace();
        }
    }
}
