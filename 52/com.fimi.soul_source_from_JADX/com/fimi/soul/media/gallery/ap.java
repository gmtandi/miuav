package com.fimi.soul.media.gallery;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

class ap extends Handler {
    final /* synthetic */ ar f7803a;
    final /* synthetic */ String f7804b;
    final /* synthetic */ an f7805c;

    ap(an anVar, ar arVar, String str) {
        this.f7805c = anVar;
        this.f7803a = arVar;
        this.f7804b = str;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        this.f7803a.m10771a((Bitmap) message.obj, this.f7804b);
    }
}
