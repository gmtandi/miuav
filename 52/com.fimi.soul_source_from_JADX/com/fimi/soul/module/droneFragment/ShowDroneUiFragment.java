package com.fimi.soul.module.droneFragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.tts.loopj.AsyncHttpClient;
import com.fimi.kernel.BaseFragment;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p076b.p078b.C1122k;
import com.fimi.kernel.p084e.ak;
import com.fimi.kernel.view.percent.PercentRelativeLayout;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1309p;
import com.fimi.soul.biz.camera.C1312s;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.biz.camera.entity.X11RespCmd.NotificationType;
import com.fimi.soul.biz.camera.p088b.C1213e;
import com.fimi.soul.biz.p090b.C1253k;
import com.fimi.soul.biz.p092c.C1263a;
import com.fimi.soul.biz.p092c.C1266d;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.C1564g;
import com.fimi.soul.drone.p117h.C1565h;
import com.fimi.soul.drone.p117h.C1575r;
import com.fimi.soul.drone.p117h.C1576s;
import com.fimi.soul.drone.p117h.aa;
import com.fimi.soul.live.NativeAudiolive;
import com.fimi.soul.module.droneui.FlightActivity;
import com.fimi.soul.module.flyplannermedia.DroneMediaTabActivity;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.UpDownSliding;
import com.fimi.soul.view.aq;
import com.fimi.soul.view.ax;
import com.fimi.soul.view.ci;
import com.mi.live.openlivesdk.C2119e;
import com.tencent.mm.sdk.openapi.BaseResp.ErrCode;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import it.p074a.p075a.C2799f;
import java.util.Timer;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;

public class ShowDroneUiFragment extends BaseFragment implements OnClickListener, C1213e<X11RespCmd>, C1312s, C1234i {
    private static final int f8100A = 1;
    private static final int f8101B = 2;
    private static final int f8102C = 3;
    private static final int f8103D = 4;
    private static final int f8104E = 5;
    private static final int f8105F = 6;
    private static final int f8106G = 7;
    private static final int f8107H = 8;
    private static final int f8108J = 9;
    private static final int f8109K = 10;
    private static final int f8110L = 11;
    private static final int f8111M = 12;
    private static final int f8112N = 13;
    private static final int f8113O = 15;
    private boolean f8114I;
    private C1576s f8115P;
    private long f8116Q;
    private boolean f8117R;
    private boolean f8118S;
    private boolean f8119T;
    private Timer f8120U;
    private Timer f8121V;
    private Button f8122W;
    private Button f8123X;
    private PercentRelativeLayout f8124Y;
    private boolean f8125Z;
    String f8126a;
    private boolean aa;
    private aq ab;
    private ax ac;
    private be ad;
    private C1263a ae;
    private bi af;
    private bh ag;
    private C1253k ah;
    private bf ai;
    private C1122k aj;
    Handler f8127b;
    private SectorProgressBar f8128c;
    private SectorProgressBar f8129d;
    private SectorProgressBar f8130e;
    private SectorProgressBar f8131f;
    private ImageButton f8132g;
    private ImageButton f8133h;
    private ImageButton f8134i;
    private ImageButton f8135j;
    private Context f8136k;
    private C1433a f8137l;
    private DroidPlannerApp f8138m;
    private PercentRelativeLayout f8139n;
    private ImageView f8140o;
    private ImageView f8141p;
    private bg f8142q;
    private C1313t f8143r;
    private UpDownSliding f8144s;
    private C2119e f8145t;
    private NativeAudiolive f8146u;
    private boolean f8147v;
    private ci f8148w;
    private C1685a f8149x;
    private ShowDroneStatusLineFragment f8150y;
    private ShowDroneStatusFragment f8151z;

    public ShowDroneUiFragment() {
        this.f8142q = bg.MAP;
        this.f8147v = false;
        this.f8114I = false;
        this.f8117R = false;
        this.f8118S = false;
        this.f8119T = false;
        this.f8125Z = false;
        this.aa = false;
        this.ad = be.FIRSTSTATUS;
        this.af = bi.Record;
        this.ai = bf.FIRSTSTATE;
        this.f8126a = null;
        this.f8127b = new au(this);
        this.f8143r = (C1313t) C1276b.m8680a().m8699d();
        this.f8143r.m8845c(new at(this));
    }

    private void m10959a(TextView textView, String str) {
        if (!textView.getText().toString().equals(str)) {
            textView.setText(str);
        }
    }

    private void m10964a(bi biVar) {
        if (!this.f8147v) {
            this.af = biVar;
        }
    }

    private void m10966b(int i) {
        m10977e(false);
        new ax(getActivity()).m12783a(C1189f.m8334d().getString(C1205R.string.live_stopped) + getString(i)).m12784a(getString(C1205R.string.ensure), new ax(this)).m12781a().show();
    }

    @TargetApi(16)
    private void m10969b(boolean z) {
        if (this.f8142q.equals(bg.CAMER)) {
            m10972c(z);
            m10975d(z);
        }
    }

    private void m10971c(int i) {
        int i2 = -1;
        int i3 = 0;
        try {
            this.f8146u.fmLiveStop();
            while (i2 < 0 && i3 < f8103D) {
                Thread.sleep(2000);
                i2 = this.f8146u.fmLiveStart(C1314u.f5879f, this.f8126a);
                i3 += f8100A;
            }
            if (i2 < 0) {
                m10966b(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(16)
    private void m10972c(boolean z) {
        if (this.f8137l.m9575V() && this.f8150y != null && this.f8150y.m10950a() == C1205R.string.fault_camera) {
            m10997a((int) C1205R.string.tf_normal);
        }
        if (this.f8142q.equals(bg.CAMER)) {
            this.f8134i.setEnabled(z);
            if (z) {
                this.f8134i.setImageAlpha(Util.MASK_8BIT);
                return;
            } else {
                this.f8134i.setImageAlpha(85);
                return;
            }
        }
        this.f8134i.setEnabled(true);
        this.f8134i.setImageAlpha(Util.MASK_8BIT);
    }

    @TargetApi(16)
    private void m10975d(boolean z) {
        if (this.f8142q.equals(bg.MAP)) {
            this.f8135j.setEnabled(true);
            this.f8135j.setImageAlpha(Util.MASK_8BIT);
        } else {
            this.f8135j.setEnabled(z);
            if (z) {
                this.f8135j.setImageAlpha(Util.MASK_8BIT);
            } else {
                this.f8135j.setImageAlpha(85);
            }
        }
        this.f8133h.setEnabled(z);
        if (z) {
            this.f8133h.setImageAlpha(Util.MASK_8BIT);
        } else {
            this.f8133h.setImageAlpha(85);
        }
    }

    @TargetApi(16)
    private void m10977e(boolean z) {
        boolean z2 = true;
        int i = 0;
        if (this.f8137l.m9575V()) {
            this.f8143r.m8873r().m8753f();
        }
        this.f8147v = z;
        if (this.f8142q.equals(bg.CAMER)) {
            if (C1189f.m8334d() != null) {
                this.f8134i.setImageDrawable(C1189f.m8334d().getResources().getDrawable(!z ? C1205R.drawable.btn_live_play : C1205R.drawable.btn_live_stop));
            }
            this.f8133h.setEnabled(!z);
            this.f8135j.setEnabled(!z);
            if (z) {
                this.f8135j.setImageAlpha(85);
                this.f8133h.setImageAlpha(85);
            } else {
                this.f8133h.setImageAlpha(Util.MASK_8BIT);
                this.f8135j.setImageAlpha(Util.MASK_8BIT);
            }
        }
        UpDownSliding upDownSliding = this.f8144s;
        if (z) {
            z2 = false;
        }
        upDownSliding.setEnabled(z2);
        if (this.f8151z != null) {
            ShowDroneStatusFragment showDroneStatusFragment = this.f8151z;
            if (!z) {
                i = f8107H;
            }
            showDroneStatusFragment.m10922a(i);
        }
        if (this.f8150y != null) {
            this.f8150y.m10956b(z);
        }
        if (!z) {
            this.f8146u.fmLiveStop();
        }
    }

    private void m10981f() {
        this.f8142q = bg.CAMER;
        this.f8144s.setVisibility(0);
        this.f8134i.setImageDrawable(getResources().getDrawable(C1205R.drawable.camer_btn_selector));
        if (this.ae != null) {
            this.ae.m8646a((int) C1205R.drawable.photo);
        }
        this.f8132g.setImageDrawable(getResources().getDrawable(C1205R.drawable.map));
        m10988i();
        this.f8149x.m11011a(this.f8143r.m8848d());
        ((FlightActivity) getActivity()).m11342d();
        this.f8149x.m11016e();
    }

    private void m10984g() {
        if (C1189f.m8335e().m8037h()) {
            if (this.ab == null) {
                this.ab = new aq(getActivity());
            }
            if (!this.ab.m12750b().isShowing()) {
                this.ab.m12748a(getString(C1205R.string.check_tf_card_must_format)).m12747a(getResources().getColor(C1205R.color.dialog_ensure_hot)).m12753b(getString(C1205R.string.ensure), new bb(this)).m12749a(getString(C1205R.string.cancel), new ba(this)).m12746a().show();
            }
        }
    }

    private void m10986h() {
        if (C1189f.m8335e().m8038i()) {
            if (this.ac == null) {
                this.ac = new ax(getActivity());
            }
            if (!this.ac.m12785b().isShowing()) {
                this.ac.m12783a(getString(C1205R.string.tf_card_no_use_please_change)).m12784a(getString(C1205R.string.ensure), new bc(this)).m12781a().show();
            }
        }
    }

    private void m10988i() {
        boolean z = true;
        boolean z2 = this.f8143r.m8850f() == C1309p.ContinueCapturing || this.f8143r.m8850f() == C1309p.Recoding || this.f8147v;
        if (z2) {
            z = false;
        }
        m10975d(z);
        if (!this.f8142q.equals(bg.MAP)) {
            if (this.af != bi.Record) {
                if (this.f8143r.m8853i().isContinueCaptureMode()) {
                    m10964a(bi.ContinueCapture);
                }
                if (C1309p.Recoding == this.f8143r.m8850f()) {
                    m10964a(bi.Record);
                }
            }
            m10994l();
            m10989j();
        }
    }

    private void m10989j() {
        if (!isAdded()) {
            return;
        }
        if (this.f8147v) {
            this.f8134i.setImageDrawable(getResources().getDrawable(C1205R.drawable.btn_live_stop));
            return;
        }
        switch (av.f8193d[this.f8143r.m8850f().ordinal()]) {
            case f8100A /*1*/:
                this.f8134i.setImageDrawable(getResources().getDrawable(C1205R.drawable.recording_on));
                this.f8144s.setEnabled(false);
            case f8101B /*2*/:
                this.f8134i.setImageDrawable(getResources().getDrawable(C1205R.drawable.recording_on));
                this.f8144s.setEnabled(false);
            case f8102C /*3*/:
                this.f8144s.setEnabled(true);
            case f8103D /*4*/:
                this.f8144s.setEnabled(true);
            default:
        }
    }

    private void m10992k() {
        try {
            if (this.f8120U != null) {
                this.f8120U.cancel();
                this.f8120U = null;
            }
            if (this.f8150y != null && getActivity() != null) {
                this.f8150y.m10954a(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m10994l() {
        if (isAdded() && !this.f8142q.equals(bg.MAP)) {
            switch (av.f8192c[this.af.ordinal()]) {
                case f8100A /*1*/:
                    this.f8144s.setState(ci.Record);
                    this.f8134i.setImageDrawable(getResources().getDrawable(C1205R.drawable.video_btn_selector));
                    this.f8151z.m10923a(true);
                case f8101B /*2*/:
                    this.f8134i.setImageDrawable(getResources().getDrawable(C1205R.drawable.video_btn_selector));
                case f8102C /*3*/:
                    this.f8144s.setState(ci.TakePhoto);
                    this.f8134i.setImageDrawable(getResources().getDrawable(C1205R.drawable.camer_btn_selector));
                    this.f8151z.m10923a(false);
                case f8103D /*4*/:
                    this.f8144s.setState(ci.Live);
                    this.f8134i.setImageDrawable(getResources().getDrawable(C1205R.drawable.btn_live_play));
                    this.f8151z.m10923a(true);
                default:
            }
        }
    }

    public void m10997a(int i) {
        if (this.f8150y != null && getActivity() != null) {
            this.f8150y.m10957c(i);
        }
    }

    public void m10998a(long j) {
        this.f8116Q = j;
        if (this.f8120U == null) {
            this.f8120U = new Timer();
            this.f8120U.schedule(new bd(this), 0, 1000);
        }
    }

    public void m10999a(C1309p c1309p, C1309p c1309p2) {
        ak.m8085a(getActivity(), "\u72b6\u6001\u53d8\u5316\u5566" + c1309p + "-->" + c1309p2, (int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
    }

    public void m11000a(ShowDroneStatusLineFragment showDroneStatusLineFragment, ShowDroneStatusFragment showDroneStatusFragment) {
        this.f8150y = showDroneStatusLineFragment;
        this.f8151z = showDroneStatusFragment;
    }

    public void m11001a(boolean z) {
        if (!this.f8142q.equals(bg.CAMER)) {
            return;
        }
        if (z) {
            this.f8149x.m11016e();
        } else {
            this.f8149x.m11017f();
        }
    }

    public void m11002a(boolean z, X11RespCmd x11RespCmd) {
        if (this.f8150y != null) {
            NotificationType notificationType = x11RespCmd.getNotificationType();
            if (notificationType == NotificationType.StorageIOError) {
                m10997a((int) C1205R.string.tf_io_error);
                this.f8143r.m8836a(C1309p.Normal);
            } else if (notificationType != NotificationType.StorageRunOut) {
                if (notificationType == NotificationType.CardRemoved) {
                    this.f8143r.m8839a(true);
                    m10997a((int) C1205R.string.tf_remove_error);
                    this.f8143r.m8844b(false);
                    this.f8143r.m8836a(C1309p.Normal);
                } else if (notificationType == NotificationType.CardInsert) {
                    m10997a((int) C1205R.string.tf_normal);
                    this.f8143r.m8844b(true);
                } else if (notificationType == NotificationType.LowSpeedCard) {
                    m10997a((int) C1205R.string.tf_low_speed_error);
                } else if (notificationType == NotificationType.PhotoFinish) {
                    m11001a(true);
                } else if ((notificationType == NotificationType.CARD_FILE_SYS_ERROR || notificationType == NotificationType.CARD_PARAM_ERR || notificationType == NotificationType.CARD_NO_PROPOSE || notificationType == NotificationType.CARD_NO_PROPOSE_AND_PARAM_ERR || notificationType == NotificationType.CARD_WRITE_LOW || notificationType == NotificationType.CARD_WRITE_LOW_AND_PARAM_ERR) && this.f8143r.m8848d()) {
                    this.f8143r.m8873r().m8745b();
                }
            }
            switch (x11RespCmd.getMsg_id()) {
                case C1314u.f5852E /*260*/:
                    if (this.f8147v) {
                        this.f8143r.m8873r().m8754g();
                    } else {
                        this.f8143r.m8873r().m8755h();
                    }
                    this.f8143r.m8873r().m8752e();
                    m10988i();
                    break;
            }
        }
        if (x11RespCmd.getMsg_id() == C1314u.af) {
            if (m7661a() != null) {
            }
            if (this.f8137l.m9575V() && this.f8143r.m8850f() != C1309p.Recoding) {
                m10997a((int) C1205R.string.tf_normal);
                m11001a(true);
            }
            this.f8127b.sendEmptyMessageDelayed(f8101B, 2000);
            if (!this.f8143r.m8848d()) {
                this.f8143r.m8875t().m8790b();
            }
        } else if (x11RespCmd.getMsg_id() == C1314u.ag) {
            if (!this.f8137l.m9570Q()) {
                m10997a((int) C1205R.string.fault_camera);
            }
            this.f8143r.m8836a(C1309p.Normal);
            m10992k();
            m11001a(false);
            m10988i();
        } else {
            if (!z) {
                Log.d("good", x11RespCmd.getErrorMsg());
                switch (x11RespCmd.getRval()) {
                    case ErrCode.ERR_AUTH_DENIED /*-4*/:
                        if (m7661a() != null) {
                            Log.d("Good", "\u91cd\u65b0\u5efa\u7acbSession\u8fde\u63a5");
                        }
                        this.f8143r.m8875t().m8790b();
                        break;
                }
            }
            switch (x11RespCmd.getMsg_id()) {
                case f8102C /*3*/:
                    if (C1314u.aL.equals(this.f8143r.m8853i().getSDState()) || C1314u.aM.equals(this.f8143r.m8853i().getSDState())) {
                        m10984g();
                    } else if (C1314u.aN.equals(this.f8143r.m8853i().getSDState()) || C1314u.aO.equals(this.f8143r.m8853i().getSDState()) || C1314u.aP.equals(this.f8143r.m8853i().getSDState()) || C1314u.aQ.equals(this.f8143r.m8853i().getSDState())) {
                        m10986h();
                    } else if (C1314u.aR.equals(this.f8143r.m8853i().getSDState())) {
                        ak.m8084a(getActivity(), getString(C1205R.string.uncheck_tf_card_please_re_insert));
                    }
                    if (bg.CAMER == this.f8142q && isAdded()) {
                        m10988i();
                    }
                case f8103D /*4*/:
                    if (z) {
                        ak.m8085a(this.f8136k, getString(C1205R.string.format_success), ak.f5303c);
                        this.f8143r.m8873r().m8749c(C1314u.cq);
                        this.f8143r.m8839a(true);
                    } else {
                        ak.m8085a(this.f8136k, this.f8136k.getString(C1205R.string.format_fail), ak.f5303c);
                    }
                    C1189f.m8331b().m8348c();
                case C2799f.f14258H /*257*/:
                    m11001a(true);
                    this.f8143r.m8855k().getFileList().clear();
                    if (getActivity() != null) {
                        m10997a((int) C1205R.string.tf_normal);
                    }
                case C1314u.f5854G /*513*/:
                    if (z) {
                        this.f8134i.setImageDrawable(getResources().getDrawable(C1205R.drawable.recording_on));
                        return;
                    }
                    Log.d("Good", "\u5f00\u59cb\u5f55\u50cf\u5931\u8d25");
                    this.f8134i.setImageDrawable(getResources().getDrawable(C1205R.drawable.recording));
                case C1314u.f5855H /*514*/:
                    this.f8134i.setImageDrawable(getResources().getDrawable(C1205R.drawable.recording));
                case C1314u.f5858K /*769*/:
                    if (this.af == bi.ContinueCapture) {
                        this.f8134i.setImageDrawable(getResources().getDrawable(C1205R.drawable.recording_on));
                    }
                    if (this.af != bi.TakePhoto) {
                        return;
                    }
                    if (z) {
                        this.f8134i.setImageDrawable(getResources().getDrawable(C1205R.drawable.camer_btn_selector));
                    } else {
                        m7661a().m8341a(this.f8136k.getString(C1205R.string.take_photo_false));
                    }
                case C1314u.f5859L /*770*/:
                    Log.d("Good", "\u505c\u6b62\u8fde\u62cd");
                    this.f8134i.setImageDrawable(getResources().getDrawable(C1205R.drawable.recording));
                default:
            }
        }
    }

    public void m11004d() {
        this.f8146u = new NativeAudiolive();
        this.f8146u.setLiveCallBackListener(new ay(this));
        if (this.f8146u.isLivePushing()) {
            m10977e(true);
        }
        this.f8145t = new C2119e(C1236a.f5577A, C1236a.f5578B, f8100A, new az(this), this.f8138m);
    }

    public void m11005e() {
        if (this.f8143r != null) {
            this.f8143r.m8857m();
        }
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.aj = C1122k.m7798a(getActivity());
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f8136k = activity.getApplicationContext();
        this.f8138m = (DroidPlannerApp) activity.getApplication();
        this.ag = (bh) activity;
        this.f8137l = this.f8138m.f5570a;
    }

    @TargetApi(16)
    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.view_camera:
                if (this.f8143r.m8850f() == C1309p.Recoding) {
                    String str = C2915a.f14760f;
                    if (this.f8143r.m8853i() != null) {
                        CharSequence videoResolution = this.f8143r.m8853i().getVideoResolution();
                        if (videoResolution != null && !C2915a.f14760f.equals(videoResolution) && !C1314u.bP.contains(videoResolution) && !C1314u.bY.contains(videoResolution) && !C1314u.bT.contains(videoResolution)) {
                            if (m7661a().m8343b() == null || !m7661a().m8343b().isShowing()) {
                                m7661a().m8336a();
                                if (m7661a().m8343b() != null) {
                                    m7661a().m8343b().setCancelable(false);
                                }
                                this.f8143r.m8876u().m8720j();
                                this.f8127b.sendEmptyMessageDelayed(f8106G, 4000);
                            }
                        }
                    }
                }
            case C1205R.id.camerButton:
                if (this.f8142q.equals(bg.MAP)) {
                    if ((C1253k.m8598a(getActivity().getApplicationContext()).m8599a().get() == f8101B || this.f8137l.ah().m10170e() == f8103D) && this.f8124Y.isShown()) {
                        this.f8124Y.setVisibility(f8107H);
                    }
                    m10981f();
                } else {
                    this.f8142q = bg.MAP;
                    this.f8144s.setVisibility(f8107H);
                    this.f8134i.setImageDrawable(getResources().getDrawable(C1205R.drawable.navigabutton));
                    if (this.ae != null) {
                        this.ae.m8650b();
                    }
                    this.f8132g.setImageDrawable(getResources().getDrawable(C1205R.drawable.video));
                    ((FlightActivity) getActivity()).m11343e();
                    this.f8149x.m11014c();
                    this.f8135j.setEnabled(true);
                    this.f8135j.setImageAlpha(Util.MASK_8BIT);
                    this.f8149x.m11017f();
                }
                m10972c(this.f8137l.m9570Q());
            case C1205R.id.setttingbutton:
                this.ag.m11039a();
            case C1205R.id.rightcenterbutton:
                if (!this.f8142q.equals(bg.MAP)) {
                    int i = 4000;
                    if (m7661a().m8343b() == null || !m7661a().m8343b().isShowing()) {
                        m7661a().m8336a();
                        if (m7661a().m8343b() != null) {
                            m7661a().m8343b().setCancelable(false);
                        }
                        switch (av.f8192c[this.af.ordinal()]) {
                            case f8100A /*1*/:
                                if (this.f8143r.m8850f() != C1309p.Recoding) {
                                    this.f8143r.m8876u().m8716e();
                                    break;
                                }
                                this.f8143r.m8876u().m8717f();
                                this.f8127b.sendEmptyMessageDelayed(f8101B, 2000);
                                break;
                            case f8101B /*2*/:
                                if (this.f8143r.m8850f() != C1309p.ContinueCapturing) {
                                    Log.d("Good", "\u5f00\u59cb\u8fde\u62cd");
                                    this.f8143r.m8876u().m8715d();
                                    break;
                                }
                                Log.d("Good", "\u8fde\u62cd\u4e2d--\u300b\u505c\u6b62\u8fde\u62cd");
                                i = 13000;
                                this.f8118S = true;
                                m10969b(false);
                                this.f8143r.m8876u().m8714c();
                                break;
                            case f8102C /*3*/:
                                this.f8143r.m8876u().m8713b();
                                break;
                            case f8103D /*4*/:
                                if (!this.f8147v) {
                                    this.f8145t.m13054a(getActivity());
                                    break;
                                } else {
                                    this.f8145t.m13055b(getActivity());
                                    break;
                                }
                        }
                        this.f8127b.removeMessages(f8106G);
                        this.f8127b.sendEmptyMessageDelayed(f8106G, (long) i);
                    }
                } else if (this.f8137l.ah().m10172g().judgeOpenDrawLayout()) {
                    this.f8137l.m9589a(C1584h.OPENDRAWCONTROL);
                }
            case C1205R.id.locationbutton:
                if (!this.f8142q.equals(bg.MAP)) {
                    m11001a(false);
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), DroneMediaTabActivity.class);
                    startActivity(intent);
                } else if (this.ae != null) {
                    this.ae.m8652d();
                }
            case C1205R.id.left_pause_btn:
                C1664h.m10813a(this.f8137l).m10828d();
            case C1205R.id.right_pause_btn:
                if (getString(C1205R.string.actionpause).equals(this.f8123X.getText().toString())) {
                    C1664h.m10813a(this.f8137l).m10830f();
                } else {
                    C1664h.m10813a(this.f8137l).m10829e();
                }
            default:
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.ah = C1253k.m8598a(getActivity());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.showdroncontral, null);
        this.f8139n = (PercentRelativeLayout) inflate.findViewById(C1205R.id.showui);
        View findViewById = inflate.findViewById(C1205R.id.view_camera);
        this.f8149x = new C1685a(getActivity(), findViewById);
        findViewById.setOnClickListener(this);
        this.f8140o = (ImageView) this.f8139n.findViewById(C1205R.id.leftbg);
        this.f8141p = (ImageView) this.f8139n.findViewById(C1205R.id.rghtbg);
        this.f8128c = (SectorProgressBar) this.f8139n.findViewById(C1205R.id.spb_leftTop);
        this.f8128c.setSectorProgressBarType(ac.LeftTop);
        this.f8129d = (SectorProgressBar) this.f8139n.findViewById(C1205R.id.spb_leftBottom);
        this.f8129d.setSectorProgressBarType(ac.LeftBottom);
        this.f8130e = (SectorProgressBar) this.f8139n.findViewById(C1205R.id.spb_rightTop);
        if (ShowDroneStatusFragment.f8017c > 30 || ShowDroneStatusFragment.f8017c == 0) {
            this.f8130e.setSectorProgressBarType(ac.RightTop);
        } else {
            this.f8130e.setSectorProgressBarType(ac.RightToplow);
        }
        this.f8131f = (SectorProgressBar) this.f8139n.findViewById(C1205R.id.spb_rightBottom);
        this.f8131f.setSectorProgressBarType(ac.RightBottom);
        this.f8124Y = (PercentRelativeLayout) this.f8139n.findViewById(C1205R.id.status_action_vg);
        this.f8122W = (Button) this.f8139n.findViewById(C1205R.id.left_pause_btn);
        this.f8123X = (Button) this.f8139n.findViewById(C1205R.id.right_pause_btn);
        AssetManager assets = getActivity().getAssets();
        View[] viewArr = new View[f8101B];
        viewArr[0] = this.f8122W;
        viewArr[f8100A] = this.f8123X;
        be.m12359a(assets, viewArr);
        this.f8132g = (ImageButton) this.f8139n.findViewById(C1205R.id.camerButton);
        this.f8133h = (ImageButton) this.f8139n.findViewById(C1205R.id.setttingbutton);
        this.f8134i = (ImageButton) this.f8139n.findViewById(C1205R.id.rightcenterbutton);
        this.f8135j = (ImageButton) this.f8139n.findViewById(C1205R.id.locationbutton);
        viewArr = new View[f8105F];
        viewArr[0] = this.f8132g;
        viewArr[f8100A] = this.f8133h;
        viewArr[f8101B] = this.f8134i;
        viewArr[f8102C] = this.f8135j;
        viewArr[f8103D] = this.f8122W;
        viewArr[f8104E] = this.f8123X;
        int length = viewArr.length;
        for (int i = 0; i < length; i += f8100A) {
            viewArr[i].setOnClickListener(this);
        }
        this.f8144s = (UpDownSliding) this.f8139n.findViewById(C1205R.id.up_down_slid);
        if (this.f8144s.getCurrentState() == ci.Record) {
            m10964a(bi.Record);
        } else if (this.f8144s.getCurrentState() == ci.TakePhoto) {
            m10964a(bi.TakePhoto);
        } else if (this.f8144s.getCurrentState() == ci.Live) {
            m10964a(bi.Live);
        }
        this.f8144s.setOnUpDownSliding(new aw(this));
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        this.f8137l.m9594b((C1234i) this);
        this.f8119T = true;
        this.f8128c.m10908b();
        this.f8129d.m10908b();
        this.f8130e.m10908b();
        this.f8131f.m10908b();
        this.f8145t.m13053a();
        if (this.f8120U != null) {
            this.f8120U.cancel();
        }
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        if (c1433a.m9570Q() && c1433a.m9569P().m9995a()) {
            byte a;
            double f;
            switch (av.f8191b[c1584h.ordinal()]) {
                case f8100A /*1*/:
                    m10981f();
                    return;
                case f8101B /*2*/:
                    if (c1433a.ah().m10171f() == f8103D) {
                        m10959a(this.f8123X, getString(C1205R.string.actionresume));
                    } else if (c1433a.ah().m10171f() == f8101B) {
                        m10959a(this.f8123X, getString(C1205R.string.actionpause));
                    }
                    if (c1433a.ah().m10172g().isExceAction()) {
                        if (this.f8142q.equals(bg.MAP)) {
                            if (!this.f8124Y.isShown()) {
                                this.f8124Y.setVisibility(0);
                                return;
                            }
                            return;
                        } else if (((this.f8142q.equals(bg.CAMER) && C1253k.m8598a(getActivity().getApplicationContext()).m8599a().get() == f8101B) || c1433a.ah().m10170e() == f8103D) && this.f8124Y.isShown()) {
                            this.f8124Y.setVisibility(f8107H);
                            return;
                        } else {
                            return;
                        }
                    } else if (this.f8124Y.isShown()) {
                        this.f8124Y.setVisibility(f8107H);
                        return;
                    } else {
                        return;
                    }
                case f8102C /*3*/:
                    C1664h.m10813a(c1433a).m10827c(Opcodes.I2S);
                    a = c1433a.m9559F().m10424a();
                    if (a == 80) {
                        if (getString(C1205R.string.actionpause).equals(this.f8123X.getText().toString())) {
                            m10959a(this.f8123X, getString(C1205R.string.actionresume));
                            return;
                        }
                        return;
                    } else if (a == 82) {
                        m10959a(this.f8123X, getString(C1205R.string.actionpause));
                        return;
                    } else {
                        if (this.f8124Y.isShown()) {
                            this.f8124Y.setVisibility(f8107H);
                        }
                        switch (this.ah.m8599a().get()) {
                            case f8100A /*1*/:
                                c1433a.m9589a(C1584h.SHOWINFORWINDOW);
                                break;
                            case f8101B /*2*/:
                                c1433a.m9589a(C1584h.SHOWQUITDOUBLEINFRWINDOW);
                                break;
                            case f8102C /*3*/:
                                c1433a.m9589a(C1584h.SHOWDOUBLEPOI);
                                break;
                            case f8103D /*4*/:
                                c1433a.m9589a(C1584h.QUITTAKEPHOTOFLY);
                                break;
                        }
                        this.ah.m8600a(0);
                        m10959a(this.f8123X, getString(C1205R.string.actionpause));
                        return;
                    }
                case f8105F /*6*/:
                    if (this.ae != null && this.ae.m8654f()) {
                        if (this.f8142q.equals(bg.MAP)) {
                            this.ae.m8650b();
                        } else {
                            this.ae.m8646a((int) C1205R.drawable.photo);
                        }
                        if (this.ae.m8651c() == C1266d.DRONEPRESS) {
                            this.ae.m8653e();
                        }
                    }
                    f = (c1433a.m9617t().m10362f() - 677216.0d) / 10.0d;
                    if (!c1433a.aa()) {
                        f = 0.0d;
                    }
                    if (f < 0.0d) {
                        this.f8128c.setProgress(0.0f);
                    } else if (f >= 100.0d) {
                        this.f8128c.setProgress(100.0f);
                        this.f8128c.m10906a(70.0f);
                    } else {
                        this.f8128c.setProgress((float) (0.7d * f));
                        this.f8128c.m10906a((float) (f * 0.7d));
                    }
                    f = c1433a.m9617t().m10363g();
                    if (!c1433a.aa()) {
                        f = 0.0d;
                    }
                    if (f > 0.0d) {
                        this.f8129d.setProgress(((float) f) > 100.0f ? 100.0f : (float) (0.7d * f));
                        this.f8129d.m10906a((float) (f * 0.7d));
                        return;
                    }
                    this.f8129d.setProgress(0.0f);
                    return;
                case f8106G /*7*/:
                    f = (double) ShowDroneStatusFragment.f8018d;
                    if (0.0d >= f || f >= 30.0d) {
                        this.f8130e.setSectorProgressBarType(ac.RightTop);
                    } else {
                        this.f8130e.setSectorProgressBarType(ac.RightToplow);
                    }
                    this.f8130e.setProgress((float) f);
                    this.f8130e.m10906a((float) (f * 0.7d));
                    return;
                case f8107H /*8*/:
                    m10972c(c1433a.m9570Q());
                    if (!c1433a.ah().m10172g().isLightStream()) {
                        a = c1433a.m9617t().m10359c();
                        if (a > f8103D) {
                            if (a <= (byte) 8) {
                                this.f8131f.setSectorProgressBarType(ac.RightBottomSatt);
                            } else {
                                this.f8131f.setSectorProgressBarType(ac.RightBottom);
                            }
                            if (a <= (byte) 8) {
                                this.f8131f.setProgress(((float) a) * 4.2f);
                                this.f8131f.m10906a(((float) a) * 2.87f);
                                return;
                            } else if (a <= (byte) 8 || a > f8112N) {
                                this.f8131f.setProgress((((float) a) * 4.7f) + 5.0f);
                                this.f8131f.m10906a((float) (((double) (((float) a) * 3.29f)) + 3.2d));
                                return;
                            } else {
                                this.f8131f.setProgress((((float) a) * 5.5f) - 11.0f);
                                this.f8131f.m10906a((float) (((double) (((float) a) * 4.6f)) - 13.8d));
                                return;
                            }
                        }
                        return;
                    } else if (this.f8131f.getProgress() != 0.0f) {
                        this.f8131f.setProgress(0.0f);
                        this.f8131f.m10906a(0.0f);
                        return;
                    } else {
                        return;
                    }
                case f8108J /*9*/:
                    m11001a(c1433a.m9575V());
                    m10988i();
                    return;
                case f8109K /*10*/:
                    aa ab = c1433a.ab();
                    if (ab.m10193R() && !ab.m10225u()) {
                        this.f8149x.m11013b(getString(C1205R.string.fault_super_heat));
                        return;
                    } else if (ab.m10195T() && !ab.m10225u()) {
                        this.f8149x.m11013b(getString(C1205R.string.fault_sevo_stal));
                        return;
                    } else if (ab.m10201Z() && !ab.m10225u()) {
                        this.f8149x.m11013b(getString(C1205R.string.land_GC_PREHEAT));
                        return;
                    } else if (ab.m10224t() && !ab.m10225u()) {
                        this.f8149x.m11013b(getString(C1205R.string.land_GC_UNREADY));
                        return;
                    } else if (ab.m10192Q() && !ab.m10225u()) {
                        this.f8149x.m11013b(getString(C1205R.string.gc_fault));
                        return;
                    } else if (ab.m10194S() && !ab.m10225u()) {
                        this.f8149x.m11013b(getString(C1205R.string.fault_self_fail));
                        return;
                    } else if (ab.m10196U() && !ab.m10225u()) {
                        this.f8149x.m11013b(getString(C1205R.string.fault_gc_heat_error));
                        return;
                    } else if (ab.m10197V() && !ab.m10225u()) {
                        this.f8149x.m11013b(getString(C1205R.string.fault_ahrs));
                        return;
                    } else if (!ab.m10225u() || c1433a.m9575V()) {
                        this.f8149x.m11013b(null);
                        return;
                    } else {
                        this.f8149x.m11013b(getString(C1205R.string.fault_camera));
                        m10997a((int) C1205R.string.fault_camera);
                        if (this.f8146u != null) {
                            this.f8146u.fmLiveStop();
                            return;
                        }
                        return;
                    }
                case f8110L /*11*/:
                    int a2 = c1433a.f6506b.m9849a();
                    if (a2 == 49) {
                        C1575r c1575r = (C1575r) c1433a.f6506b;
                        if (!(this.f8118S || c1575r.m10595d() == C1576s.StopQuicklyShot)) {
                            m7661a().m8348c();
                            this.f8127b.removeMessages(f8106G);
                        }
                        if (c1575r.m10595d() == C1576s.StartRecord) {
                            this.f8143r.m8836a(C1309p.Recoding);
                            if (!this.f8117R) {
                                this.f8117R = true;
                                be.m12395m(getActivity());
                                this.f8127b.sendEmptyMessageDelayed(f8111M, 2000);
                            }
                            ak.m8085a(getActivity(), getString(C1205R.string.record_start), (int) AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS);
                            m10998a(System.currentTimeMillis());
                            c1433a.m9589a(C1584h.CAMERARECORD);
                        } else if (c1575r.m10595d() == C1576s.StopRecrod) {
                            ak.m8085a(getActivity(), getString(C1205R.string.record_end), (int) AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS);
                            if (!this.aa) {
                                this.aa = true;
                                be.m12394l(getActivity());
                                this.f8127b.sendEmptyMessageDelayed(f8110L, 2000);
                            }
                            this.f8143r.m8836a(C1309p.Normal);
                            m10992k();
                        } else if (c1575r.m10595d() == C1576s.NormalShot) {
                            if (this.f8143r.m8850f() == C1309p.Recoding) {
                                ak.m8085a(getActivity(), getString(C1205R.string.capture_success), (int) AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS);
                            } else {
                                ak.m8083a(getActivity(), (int) C1205R.string.take_photo_success, (int) AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS);
                                m10997a((int) C1205R.string.tf_normal);
                                this.f8143r.m8836a(C1309p.Normal);
                            }
                            if (!this.f8125Z) {
                                this.f8125Z = true;
                                be.m12396n(getActivity());
                                this.f8127b.sendEmptyMessageDelayed(f8109K, 1500);
                            }
                            m10964a(bi.TakePhoto);
                            this.f8143r.m8839a(true);
                        } else if (c1575r.m10595d() == C1576s.QuicklyShot) {
                            ak.m8085a(getActivity(), "\u5f00\u59cb\u8fde\u62cd", (int) AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS);
                            this.f8127b.sendEmptyMessageDelayed(f8106G, 2000);
                            this.f8143r.m8836a(C1309p.ContinueCapturing);
                        } else if (c1575r.m10595d() == C1576s.StopQuicklyShot) {
                            ak.m8085a(getActivity(), "\u505c\u6b62\u8fde\u62cd", 3000);
                            this.f8143r.m8836a(C1309p.Normal);
                            this.f8127b.sendEmptyMessageDelayed(f8101B, 100);
                        }
                        m10988i();
                        return;
                    } else if (a2 == 50) {
                        C1564g c1564g = (C1564g) c1433a.f6506b;
                        if (c1564g.m10548f() != C1565h.NoSDCard) {
                            this.f8143r.m8844b(true);
                        } else {
                            this.f8143r.m8844b(false);
                        }
                        switch (av.f8190a[c1564g.m10548f().ordinal()]) {
                            case f8100A /*1*/:
                                if (this.f8143r.m8848d()) {
                                    m10964a(bi.Record);
                                    this.f8143r.m8836a(C1309p.Recoding);
                                    if (c1564g.m10546d() != 0) {
                                        m10998a(System.currentTimeMillis() - c1564g.m10546d());
                                    }
                                    this.f8143r.m8839a(true);
                                    break;
                                }
                                break;
                            case f8101B /*2*/:
                                m10964a(bi.ContinueCapture);
                                this.f8143r.m8836a(C1309p.ContinueCapturing);
                                if (this.f8150y != null) {
                                    this.f8150y.m10953a(c1564g.m10546d() + "\u5f20");
                                }
                                this.f8143r.m8839a(true);
                                break;
                            case f8102C /*3*/:
                                if (this.f8118S) {
                                    m10997a((int) C1205R.string.tf_normal);
                                    m7661a().m8348c();
                                    this.f8127b.sendEmptyMessage(f8102C);
                                    this.f8118S = false;
                                    this.f8127b.removeMessages(f8106G);
                                    m10969b(true);
                                }
                                if (!this.f8127b.hasMessages(f8106G)) {
                                    if (!this.f8143r.m8848d() && c1433a.m9570Q()) {
                                        m10997a((int) C1205R.string.tf_normal);
                                    }
                                    if (this.f8150y != null && (this.f8150y.m10950a() == C1205R.string.tf_out_error || this.f8150y.m10950a() == C1205R.string.tf_full_error || this.f8150y.m10950a() == C1205R.string.tf_remove_error || this.f8150y.m10950a() == C1205R.string.tf_io_error)) {
                                        m10997a((int) C1205R.string.tf_normal);
                                    }
                                    m10992k();
                                    this.f8143r.m8836a(C1309p.Normal);
                                    break;
                                }
                                return;
                            case f8103D /*4*/:
                                m10997a((int) C1205R.string.tf_remove_error);
                                this.f8143r.m8836a(C1309p.Normal);
                                break;
                            case f8104E /*5*/:
                                m10997a((int) C1205R.string.tf_full_error);
                                this.f8143r.m8836a(C1309p.Normal);
                                break;
                            case f8105F /*6*/:
                                m10997a((int) C1205R.string.tf_out_error);
                                this.f8143r.m8836a(C1309p.Normal);
                                break;
                            case f8106G /*7*/:
                                if (!this.f8143r.m8848d() && c1433a.m9570Q()) {
                                    m10997a((int) C1205R.string.tf_camera_init_wifi);
                                    break;
                                }
                            case f8107H /*8*/:
                                if (!this.f8143r.m8848d() && c1433a.m9570Q()) {
                                    m10997a((int) C1205R.string.tf_camera_start);
                                    break;
                                }
                            case f8108J /*9*/:
                                m10997a((int) C1205R.string.tf_io_error);
                                this.f8143r.m8836a(C1309p.Normal);
                                m10992k();
                                break;
                            case f8109K /*10*/:
                                m10997a((int) C1205R.string.tf_low_speed_error);
                                break;
                            case f8110L /*11*/:
                                this.f8118S = true;
                                m10997a((int) C1205R.string.tf_save_photo);
                                break;
                        }
                        m10988i();
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
        switch (av.f8191b[c1584h.ordinal()]) {
            case f8111M /*12*/:
                m11001a(false);
                m10992k();
                break;
        }
        if (!c1433a.m9569P().m9995a()) {
            if (this.f8129d.getProgress() != 0.0f) {
                this.f8129d.setProgress(0.0f);
                this.f8129d.m10906a(0.0f);
            }
            if (this.f8131f.getProgress() != 0.0f) {
                this.f8131f.setProgress(0.0f);
                this.f8131f.m10906a(0.0f);
            }
            if (this.f8130e.getProgress() != 0.0f) {
                this.f8130e.setProgress(0.0f);
                this.f8130e.m10906a(0.0f);
            }
            if (this.f8128c.getProgress() != 0.0f) {
                this.f8128c.setProgress(0.0f);
                this.f8128c.m10906a(0.0f);
            }
        }
        if (!c1433a.m9575V()) {
            m11001a(false);
        }
        if (!(c1433a.m9570Q() || this.ae == null)) {
            if (this.f8142q.equals(bg.MAP)) {
                this.ae.m8650b();
            } else {
                this.ae.m8646a((int) C1205R.drawable.photo);
            }
        }
        this.f8143r.m8836a(C1309p.Normal);
        if (!(this.f8143r.m8848d() && c1433a.m9575V())) {
            m10997a((int) C1205R.string.fault_camera);
            this.f8143r.m8855k().getFileList().clear();
        }
        m10977e(false);
        m10988i();
        m10972c(false);
        this.f8149x.m11013b(null);
        this.f8143r.m8839a(true);
    }

    public void onResume() {
        super.onResume();
        this.f8144s.m12727b(getActivity());
        this.f8143r.m8846c(true);
        if (!this.f8114I) {
            this.f8127b.sendEmptyMessage(f8107H);
        }
    }

    public void onStart() {
        super.onStart();
        if (this.ae == null) {
            this.ae = C1263a.m8645a();
            this.ae.m8647a(this.f8135j, this.f8137l);
        }
        this.aa = false;
        this.f8125Z = false;
        this.f8137l.m9590a((C1234i) this);
        this.f8143r.m8838a((C1312s) this);
        this.f8143r.m8832a((C1213e) this);
        if (!this.f8143r.m8848d() && this.f8137l.m9575V()) {
            this.f8143r.m8875t().m8790b();
        }
        if (this.f8143r.m8848d()) {
            this.f8143r.m8873r().m8745b();
        }
        m11001a(true);
        this.f8127b.sendEmptyMessageDelayed(f8101B, 2000);
    }

    public void onStop() {
        super.onStop();
        m11001a(false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f8149x.m11015d();
        m11004d();
    }
}
