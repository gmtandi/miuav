package com.fimi.soul.module.droneFragment;

import com.fimi.soul.media.player.IMediaPlayer;
import com.fimi.soul.media.player.IMediaPlayer.OnInfoListener;

/* renamed from: com.fimi.soul.module.droneFragment.d */
class C1690d implements OnInfoListener {
    final /* synthetic */ C1685a f8270a;

    C1690d(C1685a c1685a) {
        this.f8270a = c1685a;
    }

    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
        this.f8270a.f8154b.toggleAspectRatio();
        return false;
    }
}
