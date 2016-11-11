package com.fimi.soul.module.droneFragment;

import com.fimi.soul.media.player.IMediaPlayer;
import com.fimi.soul.media.player.IMediaPlayer.OnInfoListener;

/* renamed from: com.fimi.soul.module.droneFragment.w */
class C1709w implements OnInfoListener {
    final /* synthetic */ C1707u f8345a;

    C1709w(C1707u c1707u) {
        this.f8345a = c1707u;
    }

    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
        if (this.f8345a.f8340b.isPlaying()) {
            this.f8345a.f8342d.setVisibility(0);
        }
        this.f8345a.f8340b.toggleAspectRatioPOi();
        return false;
    }
}
