package com.fimi.soul.media.gallery;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.fimi.soul.media.player.FermiPlayerActivity;
import com.fimi.soul.media.player.FermiPlayerFullActivity;

/* renamed from: com.fimi.soul.media.gallery.c */
class C1589c implements OnClickListener {
    final /* synthetic */ DroneImagePagerActivity f7810a;

    C1589c(DroneImagePagerActivity droneImagePagerActivity) {
        this.f7810a = droneImagePagerActivity;
    }

    public void onClick(View view) {
        this.f7810a.f7707h = (String) this.f7810a.f7717s.get(this.f7810a.f7710l.getCurrentItem());
        if (this.f7810a.f7718t != null) {
            this.f7810a.f7706g = (String) this.f7810a.f7718t.get(this.f7810a.f7710l.getCurrentItem());
        }
        Intent intent = new Intent(this.f7810a, FermiPlayerActivity.class);
        if (this.f7810a.f7707h != null) {
            intent.putExtra(FermiPlayerFullActivity.SP_KEY_PLAYER_ADDR, this.f7810a.f7707h.split("&&")[0]);
        } else {
            intent.putExtra(FermiPlayerFullActivity.SP_KEY_PLAYER_ADDR, this.f7810a.f7718t);
        }
        intent.putExtra(FermiPlayerActivity.SP_KEY_PLAYER_IS_REMOTE_ADDR, this.f7810a.f7706g);
        intent.putExtra(FermiPlayerActivity.PLAY_NOW, true);
        intent.putExtra(FermiPlayerActivity.ONLINEFILE, this.f7810a.f7709k);
        this.f7810a.startActivity(intent);
    }
}
