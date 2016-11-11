package com.fimi.soul.module.droneFragment;

import com.fimi.soul.media.player.IMediaPlayer;
import com.fimi.soul.media.player.IMediaPlayer.OnInfoListener;

class bl implements OnInfoListener {
    final /* synthetic */ bj f8222a;

    bl(bj bjVar) {
        this.f8222a = bjVar;
    }

    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
        if (this.f8222a.f8217a.isPlaying()) {
            this.f8222a.f8219c.setVisibility(0);
        }
        this.f8222a.f8217a.toggleAspectRatioPOi();
        return false;
    }
}
