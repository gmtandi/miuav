package com.fimi.soul.module.droneui;

import android.util.Log;
import com.fimi.soul.media.player.IFermiMediaPlayer;
import com.fimi.soul.media.player.IFermiMediaPlayer.FermiPlyaerState;
import com.fimi.soul.media.player.OnPlayerStateChangedListener;

/* renamed from: com.fimi.soul.module.droneui.c */
class C1742c implements OnPlayerStateChangedListener {
    final /* synthetic */ DroneCameraFragment f8599a;

    C1742c(DroneCameraFragment droneCameraFragment) {
        this.f8599a = droneCameraFragment;
    }

    public void OnPlayerStateChanged(FermiPlyaerState fermiPlyaerState, IFermiMediaPlayer iFermiMediaPlayer) {
        Log.d("Good", "\u64ad\u653e\u5668\u72b6\u6001-->" + fermiPlyaerState.toString());
        if (fermiPlyaerState == FermiPlyaerState.Inited) {
            this.f8599a.f8567g = true;
            this.f8599a.m11323f();
        }
        if (!this.f8599a.f8567g) {
            return;
        }
        if (fermiPlyaerState == FermiPlyaerState.Playing) {
            this.f8599a.getActivity().runOnUiThread(new C1743d(this));
        } else {
            this.f8599a.getActivity().runOnUiThread(new C1744e(this));
        }
    }
}
