package com.fimi.soul.media.gallery;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Handler;
import android.os.Message;
import com.fimi.kernel.p084e.aa;
import com.fimi.soul.utils.C1985y;
import it.p074a.p075a.C2799f;
import java.io.IOException;

class aq implements Runnable {
    final /* synthetic */ String f7806a;
    final /* synthetic */ Handler f7807b;
    final /* synthetic */ an f7808c;

    aq(an anVar, String str, Handler handler) {
        this.f7808c = anVar;
        this.f7806a = str;
        this.f7807b = handler;
    }

    public void run() {
        Bitmap bitmap = null;
        if (C1985y.m12536c(this.f7806a, this.f7808c.f7800d)) {
            bitmap = ThumbnailUtils.createVideoThumbnail(this.f7806a, 1);
        } else {
            try {
                bitmap = aa.m7982a(this.f7808c.f7800d, this.f7806a, (int) C2799f.f14257G);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Message obtainMessage = this.f7807b.obtainMessage();
        obtainMessage.obj = bitmap;
        this.f7807b.sendMessage(obtainMessage);
        this.f7808c.m10764a(this.f7806a, bitmap);
    }
}
