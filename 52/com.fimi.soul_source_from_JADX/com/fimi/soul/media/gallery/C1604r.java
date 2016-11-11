package com.fimi.soul.media.gallery;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.fimi.soul.entity.WifiDistanceFile;

/* renamed from: com.fimi.soul.media.gallery.r */
class C1604r implements OnClickListener {
    final /* synthetic */ C1602p f7825a;

    C1604r(C1602p c1602p) {
        this.f7825a = c1602p;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f7825a.f7823a.f7708j.m8874s().m8777c(((WifiDistanceFile) this.f7825a.f7823a.f7705J.get(this.f7825a.f7823a.f7710l.getCurrentItem())).getAbsolutePath());
    }
}
