package com.fimi.soul.media.gallery;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.fimi.soul.entity.WifiDistanceFile;

/* renamed from: com.fimi.soul.media.gallery.v */
class C1608v implements OnClickListener {
    final /* synthetic */ C1606t f7829a;

    C1608v(C1606t c1606t) {
        this.f7829a = c1606t;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f7829a.f7827a.f7708j.m8874s().m8777c(((WifiDistanceFile) this.f7829a.f7827a.f7705J.get(this.f7829a.f7827a.f7710l.getCurrentItem())).getAbsolutePath());
    }
}
