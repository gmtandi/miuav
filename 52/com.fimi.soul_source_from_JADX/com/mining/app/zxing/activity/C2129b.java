package com.mining.app.zxing.activity;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

/* renamed from: com.mining.app.zxing.activity.b */
class C2129b implements OnCompletionListener {
    final /* synthetic */ MipcaActivityCapture f11201a;

    C2129b(MipcaActivityCapture mipcaActivityCapture) {
        this.f11201a = mipcaActivityCapture;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.seekTo(0);
    }
}
