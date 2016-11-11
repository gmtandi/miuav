package com.fimi.soul.media.player;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p082c.C1156a;
import com.fimi.soul.C1205R;
import com.fimi.soul.media.player.FermiPlayerViewBuilder.OnClickCloseListener;
import com.fimi.soul.media.player.FermiPlayerViewBuilder.OnDisappearButtonListener;
import org.p122a.p123a.C2915a;

@SuppressLint({"ValidFragment"})
public class DroneVideoDialogFragment extends DialogFragment implements OnDisappearButtonListener {
    private static final String SP_KEY_FRAGMENT_POSITION = "SP_KEY_FRAGMENT_POSITION_VideoDialogFragment";
    private static final String SP_KEY_PLAYER_ADDR = "SP_KEY_PLAYER_ADDR_VideoDialogFragment";
    private View bottomView;
    private int currentPosition;
    C1156a manager;
    private IFermiMediaPlayer player;
    private View topView;
    private String url;

    /* renamed from: com.fimi.soul.media.player.DroneVideoDialogFragment.1 */
    class C16131 implements OnClickCloseListener {
        C16131() {
        }

        public void onClose(View view) {
            DroneVideoDialogFragment.this.player.stop();
            DroneVideoDialogFragment.this.dismissDialog();
        }
    }

    public DroneVideoDialogFragment() {
        this.manager = null;
        this.currentPosition = 0;
        this.url = C2915a.f14760f;
    }

    public static DroneVideoDialogFragment newInstance(String str) {
        DroneVideoDialogFragment droneVideoDialogFragment = new DroneVideoDialogFragment();
        droneVideoDialogFragment.url = str;
        C1189f.m8333c().m7931a(SP_KEY_PLAYER_ADDR, str);
        return droneVideoDialogFragment;
    }

    public void dismissDialog() {
        this.player.stop();
        this.currentPosition = 0;
        this.manager.m7928a(SP_KEY_FRAGMENT_POSITION, this.currentPosition);
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
        this.currentPosition = this.manager.m7934b(SP_KEY_FRAGMENT_POSITION);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.player = FermiMediaManager.getDefaultManager(getActivity()).createFermiMediaPlayer(FermiMediaPlayerType.SystemMediaPlayer);
        this.player.setAutoPlay(true);
        this.player.setPlayPosition(this.currentPosition);
        this.url = this.manager.m7927a(SP_KEY_PLAYER_ADDR);
        View create = FermiPlayerViewBuilder.Builder(getActivity(), C1205R.layout.layout_dronevideovideo_fullscreen).setFermiMediaPlayer(this.player).setOnClickCloseListenner(new C16131()).setOnDisappearButtonListener(this).create();
        this.topView = create.findViewById(C1205R.id.rl_topView);
        this.bottomView = create.findViewById(C1205R.id.rl_bottomView);
        this.player.setMediaUri(this.url);
        this.player.prepare();
        return create;
    }

    public void onDisappearButton(boolean z) {
        if (z) {
            this.topView.setVisibility(8);
            this.bottomView.setVisibility(8);
            return;
        }
        this.topView.setVisibility(0);
        this.bottomView.setVisibility(0);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.player.play();
    }
}
