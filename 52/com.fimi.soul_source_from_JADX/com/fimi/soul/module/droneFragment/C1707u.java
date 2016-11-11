package com.fimi.soul.module.droneFragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.media.player.widget.FimiVideoView;

/* renamed from: com.fimi.soul.module.droneFragment.u */
public class C1707u {
    private View f8339a;
    private FimiVideoView f8340b;
    private String f8341c;
    private ImageView f8342d;
    private Context f8343e;

    public C1707u(View view, Context context) {
        this.f8341c = C1314u.f5879f;
        this.f8339a = view;
        this.f8340b = (FimiVideoView) view.findViewById(C1205R.id.my_video_view);
        this.f8342d = (ImageView) view.findViewById(C1205R.id.targeImage);
        this.f8343e = context;
    }

    private void m11155c() {
        this.f8340b.setKeepScreenOn(true);
        this.f8340b.setHideSurfaceView(false);
        this.f8340b.setVisibility(0);
        this.f8340b.setZOrderMediaOverlay(true);
        this.f8340b.setDecodeType(0);
        this.f8340b.setVideoPath(this.f8341c);
        this.f8340b.start();
        this.f8340b.setOnPreparedListener(new C1708v(this));
        this.f8340b.setOnInfoListener(new C1709w(this));
    }

    public void m11156a() {
        this.f8340b.setVisibility(0);
        m11155c();
    }

    public void m11157b() {
        if (this.f8340b.isPlaying()) {
            this.f8340b.stopPlayback();
            this.f8340b.setKeepScreenOn(false);
            this.f8340b.setZOrderMediaOverlay(false);
            this.f8340b.destroyDrawingCache();
            this.f8340b.release(true);
        }
        this.f8342d.setVisibility(8);
        this.f8340b.setHideSurfaceView(true);
        this.f8340b.setVisibility(8);
    }
}
