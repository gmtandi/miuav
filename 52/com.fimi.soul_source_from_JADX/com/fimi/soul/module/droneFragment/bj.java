package com.fimi.soul.module.droneFragment;

import android.view.View;
import android.widget.ImageView;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.media.player.widget.FimiVideoView;

public class bj {
    private FimiVideoView f8217a;
    private String f8218b;
    private ImageView f8219c;
    private ImageView f8220d;

    public bj(View view) {
        this.f8218b = C1314u.f5879f;
        this.f8217a = (FimiVideoView) view.findViewById(C1205R.id.my_video_view);
        this.f8219c = (ImageView) view.findViewById(C1205R.id.targeImage);
        this.f8220d = (ImageView) view.findViewById(C1205R.id.control_cantairn);
    }

    public void m11042a() {
        if (this.f8217a.isPlaying()) {
            this.f8217a.stopPlayback();
            this.f8217a.setKeepScreenOn(false);
            this.f8217a.setZOrderMediaOverlay(false);
            this.f8217a.destroyDrawingCache();
            this.f8217a.release(true);
        }
        this.f8219c.setVisibility(8);
        this.f8217a.setHideSurfaceView(true);
        this.f8217a.setVisibility(8);
        this.f8220d.setVisibility(0);
    }

    public void m11043b() {
        this.f8217a.setKeepScreenOn(true);
        this.f8217a.setHideSurfaceView(false);
        this.f8217a.setVisibility(0);
        this.f8217a.setZOrderMediaOverlay(true);
        this.f8217a.setDecodeType(0);
        this.f8217a.setVideoPath(this.f8218b);
        this.f8217a.start();
        this.f8220d.setVisibility(8);
        this.f8217a.setOnPreparedListener(new bk(this));
        this.f8217a.setOnInfoListener(new bl(this));
    }
}
