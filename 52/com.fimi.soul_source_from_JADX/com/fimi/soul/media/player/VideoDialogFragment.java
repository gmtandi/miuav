package com.fimi.soul.media.player;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p082c.C1156a;
import com.fimi.soul.C1205R;
import com.fimi.soul.media.player.FermiPlayerViewBuilder.OnClickCloseListener;
import com.fimi.soul.media.player.FermiPlayerViewBuilder.OnDoubleTapListener;
import org.codehaus.jackson.util.BufferRecycler;
import org.p122a.p123a.C2915a;

@SuppressLint({"ValidFragment"})
public class VideoDialogFragment extends DialogFragment {
    private static final String SP_KEY_FRAGMENT_LOADING_AGAIN = "SP_KEY_FRAGMENT_LOADING_AGAIN_VideoDialogFragment";
    private static final String SP_KEY_FRAGMENT_ORIGIN_ORIENTATION = "SP_KEY_FRAGMENT_ORIGIN_ORIENTATION_VideoDialogFragment";
    private static final String SP_KEY_FRAGMENT_POSITION = "SP_KEY_FRAGMENT_POSITION_VideoDialogFragment";
    private static final String SP_KEY_PLAYER_ADDR = "SP_KEY_PLAYER_ADDR_VideoDialogFragment";
    private int currentPosition;
    private boolean isPortait;
    private boolean loadingAgain;
    C1156a manager;
    private IFermiMediaPlayer player;
    private String url;

    /* renamed from: com.fimi.soul.media.player.VideoDialogFragment.1 */
    class C16411 implements OnDoubleTapListener {
        C16411() {
        }

        public void onDoubleTap(MotionEvent motionEvent) {
            VideoDialogFragment.this.changeScreenOrientation();
        }
    }

    /* renamed from: com.fimi.soul.media.player.VideoDialogFragment.2 */
    class C16422 implements OnClickCloseListener {
        C16422() {
        }

        public void onClose(View view) {
            VideoDialogFragment.this.player.stop();
            VideoDialogFragment.this.dismissDialog();
        }
    }

    /* renamed from: com.fimi.soul.media.player.VideoDialogFragment.3 */
    class C16433 implements OnClickListener {
        C16433() {
        }

        public void onClick(View view) {
            VideoDialogFragment.this.changeScreenOrientation();
        }
    }

    public VideoDialogFragment() {
        this.isPortait = false;
        this.manager = null;
        this.currentPosition = 0;
        this.loadingAgain = false;
        this.url = C2915a.f14760f;
    }

    private void changeScreenOrientation() {
        this.player.pause();
        this.currentPosition = this.player.getPosition();
        Log.d("Good", this.currentPosition + C2915a.f14760f);
        if (this.currentPosition < BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN) {
            this.currentPosition = 0;
        } else {
            this.currentPosition -= 2000;
        }
        this.manager.m7928a(SP_KEY_FRAGMENT_POSITION, this.currentPosition);
        if (this.isPortait) {
            getActivity().setRequestedOrientation(0);
        }
    }

    public static VideoDialogFragment newInstance(String str) {
        VideoDialogFragment videoDialogFragment = new VideoDialogFragment();
        videoDialogFragment.url = str;
        C1189f.m8333c().m7931a(SP_KEY_PLAYER_ADDR, str);
        return videoDialogFragment;
    }

    public void dismissDialog() {
        this.player.stop();
        if (this.manager.m7937d(SP_KEY_FRAGMENT_ORIGIN_ORIENTATION) != this.isPortait) {
            if (this.manager.m7937d(SP_KEY_FRAGMENT_ORIGIN_ORIENTATION)) {
                getActivity().setRequestedOrientation(1);
            } else {
                getActivity().setRequestedOrientation(0);
            }
        }
        this.currentPosition = 0;
        this.manager.m7928a(SP_KEY_FRAGMENT_POSITION, this.currentPosition);
        this.manager.m7933a(SP_KEY_FRAGMENT_LOADING_AGAIN, false);
        dismiss();
    }

    public void onCancel(DialogInterface dialogInterface) {
        dismissDialog();
        super.onCancel(dialogInterface);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.manager = C1189f.m8333c();
        setStyle(C1205R.style.videoDialog, 16973831);
        this.isPortait = getResources().getConfiguration().orientation == 1;
        this.currentPosition = this.manager.m7934b(SP_KEY_FRAGMENT_POSITION);
        this.loadingAgain = this.manager.m7937d(SP_KEY_FRAGMENT_LOADING_AGAIN);
        if (!this.loadingAgain) {
            this.manager.m7933a(SP_KEY_FRAGMENT_ORIGIN_ORIENTATION, this.isPortait);
            this.manager.m7933a(SP_KEY_FRAGMENT_LOADING_AGAIN, true);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i = this.isPortait ? C1205R.layout.layout_videoview_popup : C1205R.layout.layout_videoview_popup_fullscreen;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.player.setAutoPlay(true);
        this.player.setPlayPosition(this.currentPosition);
        this.url = this.manager.m7927a(SP_KEY_PLAYER_ADDR);
        View create = FermiPlayerViewBuilder.Builder(getActivity(), i).setFermiMediaPlayer(this.player).setOnClickFullscreenListener(new C16433()).setOnClickCloseListenner(new C16422()).setOnDoubleTapListener(new C16411()).create();
        this.player.setMediaUri(this.url);
        this.player.prepare();
        return create;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.player.play();
    }
}
