package com.fimi.soul.p087b;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p084e.C1174m;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.biz.camera.entity.X11DeviceInfo;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.biz.camera.p088b.C1213e;
import com.fimi.soul.biz.p096d.C1325k;
import com.fimi.soul.utils.C1969i;
import com.fimi.soul.view.aq;
import com.fimi.soul.view.photodraweeview.C2020f;
import it.p074a.p075a.C2799f;
import java.io.File;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.b.c */
public class C1214c extends BaseAdapter implements OnItemClickListener, C1213e<X11RespCmd> {
    private static final int f5466a = 2;
    private static final int f5467b = 1;
    private static final int f5468c = 3;
    private static final int f5469d = 6;
    private static final int f5470e = 7;
    private static final int f5471f = 8;
    private static final int f5472g = 2;
    private static final int f5473h = 4;
    private static final int f5474i = 5;
    private static final int f5475j = 3;
    private static final int f5476k = 1;
    private static final int f5477l = 8;
    private static final int f5478m = 9;
    private static final float f5479n = 0.064f;
    private static int f5480v;
    private boolean f5481A;
    private Context f5482o;
    private C1313t f5483p;
    private int f5484q;
    private int f5485r;
    private boolean f5486s;
    private String f5487t;
    private String f5488u;
    private X11DeviceInfo f5489w;
    private boolean f5490x;
    private int f5491y;
    private int f5492z;

    static {
        f5480v = 0;
    }

    public C1214c(Context context) {
        this.f5486s = false;
        this.f5490x = true;
        this.f5491y = f5475j;
        this.f5492z = f5475j;
        this.f5481A = false;
        this.f5482o = context;
        this.f5483p = (C1313t) C1276b.m8680a().m8699d();
        this.f5489w = this.f5483p.m8853i().getX11DeviceInfo();
        this.f5486s = C1276b.m8680a().m8698c();
        m8449b();
        this.f5483p.m8845c(new C1215d(this));
    }

    private void m8442a(C1219h c1219h, LayoutParams layoutParams) {
        c1219h.f5497a.setVisibility(f5477l);
        c1219h.f5501e.setVisibility(f5477l);
        c1219h.f5500d.setBackgroundResource(0);
        c1219h.f5498b.setTextColor(this.f5482o.getResources().getColor(C1205R.color.white));
        c1219h.f5498b.setAlpha(C2020f.f10933c);
        c1219h.f5499c.setVisibility(f5477l);
        c1219h.f5501e.setOnSwitchListener(null);
        layoutParams.height = (int) this.f5482o.getResources().getDimension(C1205R.dimen.setting_adapt_height);
        c1219h.f5500d.setLayoutParams(layoutParams);
    }

    private void m8443a(C1219h c1219h, String str) {
        c1219h.f5497a.setVisibility(0);
        c1219h.f5498b.setVisibility(0);
        c1219h.f5497a.setText(str);
        c1219h.f5498b.setVisibility(f5473h);
        c1219h.f5500d.setBackgroundResource(C1205R.color.list_nomal);
    }

    private void m8444a(C1219h c1219h, String str, boolean z) {
        c1219h.f5498b.setVisibility(0);
        c1219h.f5498b.setText(str);
        if (z) {
            c1219h.f5498b.setTextColor(this.f5482o.getResources().getColor(C1205R.color.option_choose));
            c1219h.f5499c.setVisibility(0);
            return;
        }
        c1219h.f5498b.setTextColor(this.f5482o.getResources().getColor(C1205R.color.white_90));
        c1219h.f5499c.setVisibility(f5473h);
    }

    private void m8449b() {
        if (C1314u.f5882i.equals(this.f5489w.getChip())) {
            this.f5490x = false;
            this.f5491y = f5473h;
            this.f5492z = f5472g;
        } else {
            this.f5490x = true;
            this.f5491y = f5475j;
            this.f5492z = f5475j;
        }
        CharSequence videoResolution = this.f5483p.m8853i().getVideoResolution();
        CharSequence photoSize = this.f5483p.m8853i().getPhotoSize();
        if (videoResolution != null) {
            if (videoResolution != null) {
                videoResolution = videoResolution.trim();
            }
            if (photoSize != null) {
                photoSize = photoSize.trim();
            }
            Log.d("Good", "res: " + videoResolution + "size: " + photoSize);
            if (C1314u.bW.contains(videoResolution)) {
                this.f5484q = f5476k;
            }
            if (this.f5490x) {
                if (C1314u.bV.contains(videoResolution)) {
                    this.f5484q = f5472g;
                }
                if (C1314u.bP.contains(videoResolution)) {
                    this.f5484q = f5475j;
                }
            } else {
                if (C1314u.bY.contains(videoResolution)) {
                    this.f5484q = f5476k;
                }
                if (C1314u.bT.contains(videoResolution)) {
                    this.f5484q = f5472g;
                }
                if (C1314u.bW.contains(videoResolution)) {
                    this.f5484q = f5474i;
                }
                if (C1314u.bV.contains(videoResolution)) {
                    this.f5484q = f5473h;
                }
                if (C1314u.bX.contains(videoResolution)) {
                    this.f5484q = f5475j;
                }
            }
            if (photoSize != null) {
                if (this.f5490x) {
                    if (C1314u.bR.contains(photoSize)) {
                        this.f5485r = f5470e;
                    }
                    if (C1314u.bQ.contains(photoSize)) {
                        this.f5485r = f5469d;
                    }
                    if (C1314u.bS.contains(photoSize)) {
                        this.f5485r = f5477l;
                    }
                } else {
                    if (C1314u.bZ.contains(photoSize)) {
                        this.f5485r = f5470e;
                    }
                    if (C1314u.ca.contains(photoSize)) {
                        this.f5485r = f5477l;
                    }
                }
                if (C1314u.ch.equals(this.f5483p.m8853i().getVideoMode())) {
                    this.f5481A = false;
                } else {
                    this.f5481A = true;
                }
            }
        }
    }

    private void m8450b(C1219h c1219h, LayoutParams layoutParams) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.f5482o).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        layoutParams.height = displayMetrics.heightPixels * 0;
        c1219h.f5500d.setLayoutParams(layoutParams);
        c1219h.f5500d.setBackgroundResource(C1205R.color.list_nomal);
    }

    private void m8451b(C1219h c1219h, String str, boolean z) {
        c1219h.f5498b.setVisibility(0);
        c1219h.f5501e.setVisibility(0);
        c1219h.f5498b.setText(str);
        c1219h.f5501e.setSwitchState(z);
    }

    private boolean m8452c() {
        return this.f5483p.m8848d();
    }

    public void m8457a() {
        if (this.f5483p != null) {
            this.f5483p.m8832a((C1213e) this);
            if (!C1325k.m8930a().m8943h()) {
                return;
            }
            if (this.f5483p.m8848d()) {
                this.f5483p.m8873r().m8753f();
                return;
            }
            C1189f.m8331b().m8336a();
            this.f5483p.m8875t().m8790b();
        }
    }

    public void m8458a(boolean z, X11RespCmd x11RespCmd) {
        Log.d("Good", "isSuccess:" + z);
        switch (x11RespCmd.getMsg_id()) {
            case f5472g /*2*/:
                if (!z) {
                    ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.setting_false), ak.f5303c);
                    C1189f.m8331b().m8348c();
                } else if (!C1314u.bv.equals(x11RespCmd.getType())) {
                    ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.set_success), ak.f5303c);
                    this.f5483p.m8873r().m8745b();
                }
            case f5475j /*3*/:
                C1189f.m8331b().m8348c();
                m8449b();
                notifyDataSetChanged();
            case f5473h /*4*/:
                if (z) {
                    C1174m.m8185b(new File(C1969i.m12491n()));
                    ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.format_success), ak.f5303c);
                    this.f5483p.m8873r().m8749c(C1314u.cq);
                    this.f5483p.m8839a(true);
                } else {
                    ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.format_fail), ak.f5303c);
                }
                C1189f.m8331b().m8348c();
            case Opcodes.T_LONG /*11*/:
                this.f5483p.m8873r().m8745b();
            case C2799f.f14258H /*257*/:
                if (z) {
                    this.f5483p.m8873r().m8753f();
                    return;
                }
                C1189f.m8331b().m8348c();
                ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.camera_connect_state_fail), ak.f5303c);
            case C1314u.f5852E /*260*/:
                if (this.f5489w.getChip() == null) {
                    this.f5483p.m8873r().m8750d();
                } else {
                    this.f5483p.m8873r().m8745b();
                }
            case C1314u.af /*10066329*/:
                C1189f.m8331b().m8348c();
            default:
        }
    }

    public int getCount() {
        return (this.f5491y + this.f5492z) + f5469d;
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1219h c1219h;
        if (view == null) {
            c1219h = new C1219h();
            view = c1219h.m8462a(viewGroup);
            view.setTag(c1219h);
        } else {
            c1219h = (C1219h) view.getTag();
        }
        m8442a(c1219h, view.getLayoutParams());
        boolean z = i == this.f5484q || i == this.f5485r;
        if (i == 0) {
            m8443a(c1219h, this.f5482o.getString(C1205R.string.record_title));
        }
        if (this.f5490x) {
            if (i == f5476k) {
                m8444a(c1219h, this.f5482o.getString(C1205R.string.option_video_1080_30f), z);
            }
            if (i == f5472g) {
                m8444a(c1219h, this.f5482o.getString(C1205R.string.option_video_1080p_60), z);
            }
            if (i == f5475j) {
                m8444a(c1219h, "1280x720", z);
            }
        } else {
            if (i == f5472g) {
                m8444a(c1219h, this.f5482o.getString(C1205R.string.option_video_1440p_60_4k), z);
            }
            if (i == f5474i) {
                m8444a(c1219h, this.f5482o.getString(C1205R.string.option_video_1080p_30_4k), z);
            }
            if (i == f5473h) {
                m8444a(c1219h, this.f5482o.getString(C1205R.string.option_video_1080p_60_4k), z);
            }
            if (i == f5475j) {
                m8444a(c1219h, this.f5482o.getString(C1205R.string.option_video_1080p_100_4k), z);
            }
            if (i == f5476k) {
                m8444a(c1219h, this.f5482o.getString(C1205R.string.option_video_2160p_30_4k), z);
            }
        }
        if (i == this.f5491y + f5472g) {
            m8443a(c1219h, this.f5482o.getString(C1205R.string.photo));
        }
        if (this.f5490x) {
            if (i == f5469d) {
                m8444a(c1219h, this.f5482o.getString(C1205R.string.option_photo_16M), z);
            }
            if (i == f5470e) {
                m8444a(c1219h, this.f5482o.getString(C1205R.string.option_photo_12M), z);
            }
            if (i == f5477l) {
                m8444a(c1219h, this.f5482o.getString(C1205R.string.option_photo_8M), z);
            }
        } else {
            if (i == f5470e) {
                m8444a(c1219h, this.f5482o.getString(C1205R.string.option_photo_12M_4k), z);
            }
            if (i == f5477l) {
                m8444a(c1219h, this.f5482o.getString(C1205R.string.option_photo_8M_4k), z);
            }
        }
        if (i == (this.f5491y + this.f5492z) + f5475j) {
            m8443a(c1219h, this.f5482o.getString(C1205R.string.sdcard_operation));
        }
        if (i == (this.f5491y + this.f5492z) + f5473h) {
            c1219h.f5498b.setVisibility(0);
            c1219h.f5498b.setText(this.f5482o.getString(C1205R.string.camera_sd_format));
            c1219h.f5498b.setTextColor(this.f5482o.getResources().getColor(C1205R.color.white_90));
        }
        if (this.f5490x && i == f5473h) {
            if (this.f5481A) {
                c1219h.f5501e.m8371a(true, false);
            } else {
                c1219h.f5501e.m8371a(false, false);
            }
            c1219h.f5501e.setVisibility(0);
            c1219h.f5498b.setVisibility(0);
            c1219h.f5498b.setText(C1205R.string.vedio_recording_audio);
            c1219h.f5501e.setOnSwitchListener(new C1216e(this));
        }
        if (i == (this.f5491y + this.f5492z) + f5474i) {
            LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = (int) this.f5482o.getResources().getDimension(C1205R.dimen.setting_adapt_bottom);
            c1219h.f5500d.setLayoutParams(layoutParams);
            c1219h.f5500d.setBackgroundResource(C1205R.color.list_nomal);
            c1219h.f5497a.setVisibility(f5477l);
            c1219h.f5501e.setVisibility(f5477l);
            c1219h.f5499c.setVisibility(f5477l);
            c1219h.f5498b.setVisibility(f5477l);
        }
        return view;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        boolean z = true;
        if (i != this.f5484q && i != this.f5485r) {
            boolean z2;
            if (this.f5490x) {
                if (i != f5476k) {
                    z2 = false;
                } else if (m8452c()) {
                    this.f5484q = i;
                    this.f5487t = C1314u.bA;
                    this.f5488u = C1314u.bW;
                    z2 = true;
                } else {
                    ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.set_fail_no_connect_camera), ak.f5302b);
                    return;
                }
                if (i == f5472g) {
                    if (m8452c()) {
                        this.f5484q = i;
                        this.f5487t = C1314u.bA;
                        this.f5488u = C1314u.bV;
                        z2 = true;
                    } else {
                        ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.set_fail_no_connect_camera), ak.f5302b);
                        return;
                    }
                }
                if (i == f5475j) {
                    if (m8452c()) {
                        this.f5484q = i;
                        this.f5487t = C1314u.bA;
                        this.f5488u = C1314u.bP;
                        z2 = true;
                    } else {
                        ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.set_fail_no_connect_camera), ak.f5302b);
                        return;
                    }
                }
                if (i == f5469d) {
                    if (m8452c()) {
                        this.f5485r = i;
                        this.f5487t = C1314u.bF;
                        this.f5488u = C1314u.bQ;
                        z2 = true;
                    } else {
                        ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.set_fail_no_connect_camera), ak.f5302b);
                        return;
                    }
                }
                if (i == f5477l) {
                    if (m8452c()) {
                        this.f5485r = i;
                        this.f5487t = C1314u.bF;
                        this.f5488u = C1314u.bS;
                        z2 = true;
                    } else {
                        ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.set_fail_no_connect_camera), ak.f5302b);
                        return;
                    }
                }
                if (i == f5470e) {
                    if (m8452c()) {
                        this.f5485r = i;
                        this.f5487t = C1314u.bF;
                        this.f5488u = C1314u.bR;
                        z2 = true;
                    } else {
                        ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.set_fail_no_connect_camera), ak.f5302b);
                        return;
                    }
                }
            }
            if (i != f5476k) {
                z2 = false;
            } else if (m8452c()) {
                this.f5484q = i;
                this.f5487t = C1314u.bA;
                this.f5488u = C1314u.bY;
                z2 = true;
            } else {
                ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.set_fail_no_connect_camera), ak.f5302b);
                return;
            }
            if (i == f5472g) {
                if (m8452c()) {
                    this.f5484q = i;
                    this.f5487t = C1314u.bA;
                    this.f5488u = C1314u.bT;
                    z2 = true;
                } else {
                    ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.set_fail_no_connect_camera), ak.f5302b);
                    return;
                }
            }
            if (i == f5474i) {
                if (m8452c()) {
                    this.f5484q = i;
                    this.f5487t = C1314u.bA;
                    this.f5488u = C1314u.bW;
                    z2 = true;
                } else {
                    ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.set_fail_no_connect_camera), ak.f5302b);
                    return;
                }
            }
            if (i == f5473h) {
                if (m8452c()) {
                    this.f5484q = i;
                    this.f5487t = C1314u.bA;
                    this.f5488u = C1314u.bV;
                    z2 = true;
                } else {
                    ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.set_fail_no_connect_camera), ak.f5302b);
                    return;
                }
            }
            if (i == f5475j) {
                if (m8452c()) {
                    this.f5484q = i;
                    this.f5487t = C1314u.bA;
                    this.f5488u = C1314u.bX;
                    z2 = true;
                } else {
                    ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.set_fail_no_connect_camera), ak.f5302b);
                    return;
                }
            }
            if (i == f5477l) {
                if (m8452c()) {
                    this.f5485r = i;
                    this.f5487t = C1314u.bF;
                    this.f5488u = C1314u.ca;
                    z2 = true;
                } else {
                    ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.set_fail_no_connect_camera), ak.f5302b);
                    return;
                }
            }
            if (i == f5470e) {
                if (m8452c()) {
                    this.f5485r = i;
                    this.f5487t = C1314u.bF;
                    this.f5488u = C1314u.bZ;
                    z2 = true;
                } else {
                    ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.set_fail_no_connect_camera), ak.f5302b);
                    return;
                }
            }
            if (i == (this.f5491y + this.f5492z) + f5473h) {
                if (!m8452c()) {
                    ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.no_connect_camera), ak.f5302b);
                    return;
                } else if (this.f5483p.m8852h()) {
                    new aq(this.f5482o).m12748a(this.f5482o.getString(C1205R.string.you_ensure_format_tf)).m12747a(this.f5482o.getResources().getColor(C1205R.color.dialog_ensure_hot)).m12753b(this.f5482o.getString(C1205R.string.format), new C1218g(this)).m12749a(this.f5482o.getString(C1205R.string.cancel), new C1217f(this)).m12746a().show();
                } else {
                    ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.format_fail_camera_no_tf_card), ak.f5302b);
                }
            }
            if (!this.f5490x || i != f5473h) {
                z = z2;
            } else if (m8452c()) {
                this.f5487t = C1314u.bL;
                if (this.f5481A) {
                    this.f5481A = false;
                    this.f5488u = C1314u.ch;
                } else {
                    this.f5481A = true;
                    this.f5488u = C1314u.ci;
                }
            } else {
                ak.m8085a(this.f5482o, this.f5482o.getString(C1205R.string.set_fail_no_connect_camera), ak.f5302b);
                return;
            }
            if (z) {
                this.f5483p.m8873r().m8742a(this.f5487t, this.f5488u);
                C1189f.m8331b().m8336a();
            }
        }
    }
}
