package com.fimi.soul.module.droneui;

import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseDroneFragment;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.media.player.FermiMediaManager;
import com.fimi.soul.media.player.FermiMediaPlayerType;
import com.fimi.soul.media.player.IFermiMediaPlayer;
import com.fimi.soul.media.player.IMediaPlayer;

public class DroneCameraFragment extends BaseDroneFragment {
    private static final String f8561a;
    private static final int f8562b = 2;
    private static View f8563e;
    private SurfaceView f8564c;
    private String f8565d;
    private IFermiMediaPlayer f8566f;
    private boolean f8567g;

    static {
        f8561a = DroneCameraFragment.class.getSimpleName();
    }

    public DroneCameraFragment() {
        this.f8565d = C1314u.f5879f;
        this.f8567g = false;
    }

    private void m11316a(View view) {
        this.f8564c = (SurfaceView) view.findViewById(C1205R.id.ids_video_view);
        f8563e = view.findViewById(C1205R.id.control_cantairn);
        f8563e.setVisibility(0);
    }

    private void m11321i() {
        this.f8564c.setKeepScreenOn(true);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        this.f8564c.setZOrderMediaOverlay(true);
        this.f8566f = FermiMediaManager.getDefaultManager(getActivity()).createFermiMediaPlayer(FermiMediaPlayerType.FimiMediaPlayer);
        this.f8566f.setSurfaceView(this.f8564c);
        this.f8566f.setAutoPlay(false);
        this.f8566f.setMediaUri(this.f8565d);
        this.f8566f.addOnPlayerStateChangedListener(new C1742c(this));
    }

    public void m11322a(Message message) {
        m7661a().m8348c();
        if (message.what == f8562b) {
            f8563e.setVisibility(8);
        }
    }

    public void m11323f() {
        if (((FlightActivity) getActivity()).m11340b() == C1755q.Camera && this.f8566f != null && this.f8565d != null && this.f8567g) {
            try {
                this.f8564c.setVisibility(0);
                this.f8566f.prepare();
                this.f8566f.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void m11324g() {
        f8563e.setVisibility(0);
        this.f8564c.setZOrderMediaOverlay(false);
        this.f8564c.setVisibility(8);
        if (this.f8566f.isPlaying()) {
            this.f8566f.stop();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        View inflate = layoutInflater.inflate(C1205R.layout.fragment_camera_preview, null);
        getActivity().setVolumeControlStream(3);
        m11316a(inflate);
        m11321i();
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.f8566f.isPlaying()) {
            this.f8566f.stop();
        }
        if (this.f8566f instanceof IMediaPlayer) {
            ((IMediaPlayer) this.f8566f).release();
        }
    }

    public void onStart() {
        Log.d("Good", "onStart");
        m11323f();
        super.onStart();
    }

    public void onStop() {
        super.onStop();
        if (this.f8566f.isPlaying()) {
            this.f8566f.stop();
        }
    }
}
