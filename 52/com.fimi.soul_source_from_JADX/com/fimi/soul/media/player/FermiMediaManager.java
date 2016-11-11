package com.fimi.soul.media.player;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.fimi.soul.C1205R;
import com.fimi.soul.media.player.FermiPlayerViewBuilder.OnClickCloseListener;
import com.fimi.soul.media.player.FermiPlayerViewBuilder.OnDoubleTapListener;
import org.codehaus.jackson.smile.SmileConstants;

public final class FermiMediaManager {
    public static final String FERMI_PLAYER_TEMP_VARIABLE = "FERMI_PLAYER_TEMP_VARIABLE";
    private static FermiMediaManager fermiMediaManager;
    private Context context;
    private Dialog dialog;
    private DroneVideoDialogFragment droneFragment;
    private VideoDialogFragment fragment;
    private IFermiMediaPlayer player;

    /* renamed from: com.fimi.soul.media.player.FermiMediaManager.1 */
    class C16141 implements OnDoubleTapListener {
        C16141() {
        }

        public void onDoubleTap(MotionEvent motionEvent) {
        }
    }

    /* renamed from: com.fimi.soul.media.player.FermiMediaManager.2 */
    class C16152 implements OnClickCloseListener {
        final /* synthetic */ IFermiMediaPlayer val$player;

        C16152(IFermiMediaPlayer iFermiMediaPlayer) {
            this.val$player = iFermiMediaPlayer;
        }

        public void onClose(View view) {
            this.val$player.stop();
            FermiMediaManager.this.dialog.dismiss();
        }
    }

    /* renamed from: com.fimi.soul.media.player.FermiMediaManager.3 */
    class C16163 implements OnDoubleTapListener {
        C16163() {
        }

        public void onDoubleTap(MotionEvent motionEvent) {
        }
    }

    /* renamed from: com.fimi.soul.media.player.FermiMediaManager.4 */
    class C16174 implements OnClickCloseListener {
        final /* synthetic */ Dialog val$dialog;
        final /* synthetic */ IFermiMediaPlayer val$player;

        C16174(IFermiMediaPlayer iFermiMediaPlayer, Dialog dialog) {
            this.val$player = iFermiMediaPlayer;
            this.val$dialog = dialog;
        }

        public void onClose(View view) {
            this.val$player.stop();
            this.val$dialog.dismiss();
        }
    }

    /* renamed from: com.fimi.soul.media.player.FermiMediaManager.5 */
    /* synthetic */ class C16185 {
        static final /* synthetic */ int[] $SwitchMap$com$fimi$soul$media$player$FermiMediaPlayerType;

        static {
            $SwitchMap$com$fimi$soul$media$player$FermiMediaPlayerType = new int[FermiMediaPlayerType.values().length];
            try {
                $SwitchMap$com$fimi$soul$media$player$FermiMediaPlayerType[FermiMediaPlayerType.SystemMediaPlayer.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$fimi$soul$media$player$FermiMediaPlayerType[FermiMediaPlayerType.FimiMediaPlayer.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public FermiMediaManager() {
        this.player = null;
    }

    private Context getContext() {
        return this.context;
    }

    public static synchronized FermiMediaManager getDefaultManager(Context context) {
        FermiMediaManager fermiMediaManager;
        synchronized (FermiMediaManager.class) {
            if (fermiMediaManager == null) {
                fermiMediaManager = new FermiMediaManager();
            }
            fermiMediaManager.context = context;
            fermiMediaManager = fermiMediaManager;
        }
        return fermiMediaManager;
    }

    public IFermiMediaPlayer createFermiMediaPlayer(FermiMediaPlayerType fermiMediaPlayerType) {
        switch (C16185.$SwitchMap$com$fimi$soul$media$player$FermiMediaPlayerType[fermiMediaPlayerType.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return new FermiSystemMediaPlayer(getContext());
            default:
                return null;
        }
    }

    public void dismissPopVideoView() {
        if (this.fragment != null) {
            this.fragment.dismissDialog();
            this.fragment = null;
        }
        if (this.dialog != null) {
            this.dialog.dismiss();
            this.dialog = null;
        }
    }

    public void showFullScreenPopVideoView(FragmentManager fragmentManager, String str) {
        this.droneFragment = DroneVideoDialogFragment.newInstance(str);
        this.droneFragment.show(fragmentManager, str);
    }

    public void showPopVideoView(FragmentManager fragmentManager, String str) {
        this.fragment = VideoDialogFragment.newInstance(str);
        this.fragment.show(fragmentManager, "VIDEO_DIALOG_FRAGMENT");
    }

    public void showPopVideoView(String str) {
        Dialog fermiPlayerDialog = new FermiPlayerDialog(getContext(), C1205R.style.videoDialog);
        IFermiMediaPlayer createFermiMediaPlayer = createFermiMediaPlayer(FermiMediaPlayerType.SystemMediaPlayer);
        createFermiMediaPlayer.setAutoPlay(true);
        fermiPlayerDialog.setContentView(FermiPlayerViewBuilder.Builder(getContext(), C1205R.layout.layout_videoview_popup).setFermiMediaPlayer(createFermiMediaPlayer).setOnClickCloseListenner(new C16174(createFermiMediaPlayer, fermiPlayerDialog)).setOnDoubleTapListener(new C16163()).create());
        if (str != null) {
            createFermiMediaPlayer.setMediaUri(str);
        }
        fermiPlayerDialog.show();
    }

    public void showPopVideoViewWithNoSeekBar(String str) {
        this.dialog = new FermiPlayerDialog(getContext(), C1205R.style.videoDialog);
        IFermiMediaPlayer createFermiMediaPlayer = createFermiMediaPlayer(FermiMediaPlayerType.SystemMediaPlayer);
        createFermiMediaPlayer.setAutoPlay(true);
        this.dialog.setContentView(FermiPlayerViewBuilder.Builder(getContext(), C1205R.layout.layout_videoview_popup).setFermiMediaPlayer(createFermiMediaPlayer).setOnClickCloseListenner(new C16152(createFermiMediaPlayer)).setOnDoubleTapListener(new C16141()).create());
        if (str != null) {
            createFermiMediaPlayer.setMediaUri(str);
        }
        this.dialog.show();
        createFermiMediaPlayer.play();
    }
}
