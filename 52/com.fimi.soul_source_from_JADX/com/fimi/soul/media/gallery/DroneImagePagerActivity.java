package com.fimi.soul.media.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import cn.sharesdk.framework.ShareSDK;
import com.fimi.kernel.p076b.p078b.C1113b;
import com.fimi.kernel.p076b.p078b.C1115d;
import com.fimi.kernel.p076b.p078b.C1119h;
import com.fimi.kernel.p076b.p078b.C1122k;
import com.fimi.kernel.p076b.p078b.C1127o;
import com.fimi.kernel.p084e.ab;
import com.fimi.kernel.p084e.ak;
import com.fimi.kernel.view.progress.ProgressView;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.biz.camera.entity.X11FileInfo;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.biz.camera.p088b.C1213e;
import com.fimi.soul.biz.p103k.ac;
import com.fimi.soul.entity.ShareInfo;
import com.fimi.soul.entity.WifiDistanceFile;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.fimi.soul.p087b.C1224m;
import com.fimi.soul.utils.C1969i;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.C1989c;
import com.fimi.soul.view.HackyViewPager;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class DroneImagePagerActivity extends BaseActivity implements OnClickListener, C1119h, C1127o, C1213e<X11RespCmd> {
    public static final String f7689a = "image_index";
    public static final String f7690b = "extra_type";
    public static final String f7691c = "image_urls";
    public static final String f7692d = "image_drone_media";
    public static final String f7693e = "image_drone_local";
    public static final String f7694f = "video_duration";
    private static final String f7695i = "STATE_POSITION";
    private C1113b f7696A;
    private ProgressView f7697B;
    private ac f7698C;
    private Button f7699D;
    private Button f7700E;
    private TextView f7701F;
    private TextView f7702G;
    private TextView f7703H;
    private int f7704I;
    private List<WifiDistanceFile> f7705J;
    public String f7706g;
    public String f7707h;
    private C1313t f7708j;
    private boolean f7709k;
    private HackyViewPager f7710l;
    private int f7711m;
    private TextView f7712n;
    private View f7713o;
    private View f7714p;
    private View f7715q;
    private boolean f7716r;
    private ArrayList<String> f7717s;
    private ArrayList<String> f7718t;
    private ArrayList<String> f7719u;
    private ImageButton f7720v;
    private ImageButton f7721w;
    private ImageButton f7722x;
    private DroneImagePagerAdapter f7723y;
    private C1122k f7724z;

    public DroneImagePagerActivity() {
        this.f7709k = false;
        this.f7716r = true;
        this.f7706g = C2915a.f14760f;
        this.f7707h = C2915a.f14760f;
    }

    private String m10656a(int i) {
        String str = C2915a.f14760f;
        if (this.f7717s == null || this.f7717s.size() <= 0) {
            return str;
        }
        str = ((String) this.f7717s.get(i)).toString();
        return str.contains("THUMB_") ? ((String) this.f7717s.get(i)).substring(((String) this.f7717s.get(i)).lastIndexOf("/") + 1).replace("THUMB_", C2915a.f14760f) : str.contains("_THM") ? ((String) this.f7717s.get(i)).substring(((String) this.f7717s.get(i)).lastIndexOf("/") + 1).replace("_THM", C2915a.f14760f) : ((String) this.f7717s.get(i)).substring(((String) this.f7717s.get(i)).lastIndexOf("/") + 1);
    }

    private void m10658a() {
        this.f7712n = (TextView) this.f7714p.findViewById(C1205R.id.tv_pageName);
        this.f7712n.setVisibility(0);
        be.m12359a(getAssets(), this.f7712n);
        this.f7711m = getIntent().getIntExtra(f7689a, 0);
        this.f7710l = (HackyViewPager) findViewById(C1205R.id.pager);
        if (this.f7709k) {
            this.f7723y = new DroneImagePagerAdapter(getSupportFragmentManager(), this.f7717s, this.f7718t);
        } else {
            this.f7723y = new DroneImagePagerAdapter(getSupportFragmentManager(), this.f7717s);
        }
        this.f7723y.m10696a(this.f7710l);
        this.f7723y.m10697a(new C1597k(this));
        this.f7710l.setAdapter(this.f7723y);
        this.f7710l.setOnPageChangeListener(new C1598l(this));
        this.f7710l.setCurrentItem(this.f7711m);
        this.f7712n.setText(m10656a(this.f7711m));
    }

    private void m10661b() {
        View findViewById = this.f7713o.findViewById(C1205R.id.ll_bottomDownload);
        View findViewById2 = this.f7713o.findViewById(C1205R.id.ll_bottomShare);
        this.f7720v = (ImageButton) this.f7714p.findViewById(C1205R.id.btn_back);
        this.f7721w = (ImageButton) this.f7713o.findViewById(C1205R.id.btn_bottomDownload);
        ImageButton imageButton = (ImageButton) this.f7713o.findViewById(C1205R.id.btn_bottomShare);
        ImageButton imageButton2 = (ImageButton) this.f7713o.findViewById(C1205R.id.btn_bottomDelete);
        this.f7699D.setOnClickListener(new C1599m(this));
        this.f7700E.setOnClickListener(new C1600n(this));
        if (this.f7709k && !C1276b.m8691c(m10671g())) {
            findViewById2.setVisibility(8);
            findViewById.setVisibility(0);
            m10694c(true);
            m10693b(true);
            this.f7721w.setOnClickListener(new C1601o(this));
            imageButton2.setOnClickListener(new C1602p(this));
        } else if (this.f7709k && C1276b.m8691c(m10671g())) {
            this.f7715q.setVisibility(8);
            r4 = m10671g();
            Log.i("Good", "urlString:" + r4);
            if (r4.endsWith(X11FileInfo.FILE_TYPE_MP4)) {
                findViewById2.setVisibility(8);
            } else {
                findViewById2.setVisibility(0);
            }
            findViewById.setVisibility(8);
            imageButton.setOnClickListener(new C1605s(this));
            imageButton2.setOnClickListener(new C1606t(this));
        } else {
            r4 = m10672h();
            Log.i("Good", "urlString:" + r4);
            if (r4.endsWith(X11FileInfo.FILE_TYPE_MP4)) {
                findViewById2.setVisibility(8);
            } else {
                findViewById2.setVisibility(0);
            }
            findViewById.setVisibility(8);
            m10693b(true);
            m10694c(true);
            imageButton.setOnClickListener(new C1590d(this));
            imageButton2.setOnClickListener(new C1591e(this));
        }
        this.f7720v.setOnClickListener(new C1594h(this));
    }

    private void m10663c() {
        String b = C1969i.m12478b(m10671g());
        this.f7713o.setVisibility(8);
        this.f7715q.setVisibility(0);
        this.f7701F.setText(String.format(getString(C1205R.string.download_file_phone), new Object[]{m10671g()}));
        this.f7702G.setText("0%");
        this.f7703H.setText("0.00 KB/s");
        this.f7697B.setMaxCount((float) ((WifiDistanceFile) this.f7705J.get(this.f7710l.getCurrentItem())).getSize());
        this.f7697B.setCurrentCount(0.0f);
        this.f7724z.m7808a(m10672h(), (String) this.f7719u.get(this.f7710l.getCurrentItem()), ((WifiDistanceFile) this.f7705J.get(this.f7710l.getCurrentItem())).getSize(), Boolean.valueOf(false), b);
        this.f7724z.m7807a((C1127o) this);
    }

    private void m10664d() {
        new C1989c().m12813a((Context) this, getString(C1205R.string.download_delete_file), getString(C1205R.string.cancel), getString(C1205R.string.delete), Boolean.valueOf(false), getString(C1205R.string.remerber_me_choose), new C1595i(this));
    }

    private void m10667e() {
        ShareInfo shareInfo = new ShareInfo();
        if (this.f7709k) {
            shareInfo.setUrl(C1276b.m8692d(m10671g()));
        } else {
            shareInfo.setUrl(((String) this.f7717s.get(this.f7710l.getCurrentItem())).replace("file://", C2915a.f14760f));
        }
        this.f7698C.m9197a(new C1596j(this));
        this.f7698C.m9198a(shareInfo);
    }

    private void m10669f() {
        m10690a(false);
        this.f7724z = C1122k.m7798a(getApplicationContext());
        this.f7724z.m7807a((C1127o) this);
        this.f7696A = this.f7724z.m7803a((Context) this, m10672h());
        long i;
        long k;
        if (this.f7696A != null && (this.f7696A.m7785g() == C1115d.Wait || this.f7696A.m7785g() == C1115d.Downloading)) {
            m10690a(true);
            this.f7700E.setVisibility(4);
            this.f7699D.setVisibility(0);
            i = this.f7696A.m7787i();
            k = this.f7696A.m7789k();
            this.f7697B.setMaxCount((float) i);
            this.f7697B.setCurrentCount((float) k);
            this.f7701F.setText(String.format(getString(C1205R.string.download_file_phone), new Object[]{m10671g()}));
            this.f7702G.setText((k / (i / 100)) + "%");
            this.f7703H.setText("0.00 KB/s");
            this.f7696A.m7776a((C1119h) this);
        } else if (this.f7696A == null) {
        } else {
            if (this.f7696A.m7785g() == C1115d.Pause || this.f7696A.m7785g() == C1115d.Error) {
                m10690a(true);
                this.f7700E.setVisibility(0);
                this.f7699D.setVisibility(8);
                i = this.f7696A.m7787i();
                k = this.f7696A.m7789k();
                this.f7697B.setMaxCount((float) i);
                this.f7697B.setCurrentCount((float) k);
                this.f7701F.setText(String.format(getString(C1205R.string.download_file_phone), new Object[]{m10671g()}));
                this.f7702G.setText((k / (i / 100)) + "%");
                this.f7703H.setText(C2915a.f14760f);
                this.f7696A.m7776a((C1119h) this);
            }
        }
    }

    private String m10671g() {
        return m10656a(this.f7710l.getCurrentItem());
    }

    private String m10672h() {
        String str = (String) this.f7717s.get(this.f7710l.getCurrentItem());
        return str.contains("&&") ? str.split("&&")[1] : str;
    }

    private void m10675i() {
        for (int i = 0; i < this.f7717s.size(); i++) {
            if (C1276b.m8689b(m10656a(i))) {
                String format = String.format("file://%s", new Object[]{C1276b.m8693e(m10656a(i))});
                Log.d("Good", "Downloaded:" + format);
                this.f7717s.set(i, format);
            }
        }
    }

    public void m10688a(C1115d c1115d, C1113b c1113b) {
        if (!this.f7709k || !c1113b.m7786h().equals(C1969i.m12478b(m10671g()))) {
            return;
        }
        if (c1115d == C1115d.Completed) {
            m10690a(false);
            this.f7710l.setScrollble(true);
            m10661b();
            this.f7697B.setCurrentCount(0.0f);
            this.f7701F.setText("0%");
            if (ay.m12293a((Context) this).getBoolean(C1236a.f5593Q, false)) {
                this.f7708j.m8874s().m8777c(m10671g());
            }
            C1122k.m7798a((Context) this).m7809a(this.f7696A);
            return;
        }
        this.f7697B.setMaxCount((float) c1113b.m7787i());
        this.f7697B.setCurrentCount((float) c1113b.m7789k());
        long l = c1113b.m7790l();
        if (l >= 0 && l < 102400) {
            this.f7703H.setText(ab.m8016b((double) ((float) (l / FimiMediaMeta.AV_CH_SIDE_RIGHT)), 2) + " KB/s");
        } else if (l >= 102400 && l < 1024000) {
            this.f7703H.setText(((long) ((int) (l / FimiMediaMeta.AV_CH_SIDE_RIGHT))) + " KB/s");
        } else if (l >= 1024000) {
            this.f7703H.setText(ab.m8016b((double) (((float) l) / 1048576.0f), 2) + " MB/s");
        }
        this.f7702G.setText((c1113b.m7789k() / (c1113b.m7787i() / 100)) + "%");
    }

    public void m10689a(List<C1113b> list, boolean z, C1113b c1113b) {
        if (c1113b != null) {
            this.f7696A = c1113b;
            if (!isFinishing()) {
                this.f7696A.m7776a((C1119h) this);
            }
        }
    }

    protected void m10690a(boolean z) {
        if (z) {
            this.f7713o.setVisibility(8);
            this.f7715q.setVisibility(0);
            return;
        }
        this.f7713o.setVisibility(0);
        this.f7715q.setVisibility(4);
    }

    public void m10691a(boolean z, X11RespCmd x11RespCmd) {
        if (z && this.f7709k) {
            switch (x11RespCmd.getMsg_id()) {
                case C1314u.f5863P /*1281*/:
                    if (this.f7718t.get(this.f7710l.getCurrentItem()) != null) {
                        File file = new File(((String) this.f7718t.get(this.f7710l.getCurrentItem())).replace("file://", C2915a.f14760f));
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                    this.f7708j.m8839a(true);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    if (this.f7705J.size() > this.f7710l.getCurrentItem()) {
                        bundle.putSerializable(C1236a.f5591O, (Serializable) this.f7705J.get(this.f7710l.getCurrentItem()));
                    }
                    intent.putExtras(bundle);
                    setResult(2, intent);
                    finish();
                default:
            }
        } else if (!z && this.f7709k) {
            switch (x11RespCmd.getMsg_id()) {
                case C1314u.f5863P /*1281*/:
                    ak.m8085a((Context) this, getResources().getString(C1205R.string.del_fail), ak.f5302b);
                default:
            }
        }
    }

    protected void m10693b(boolean z) {
        this.f7713o.setVisibility(z ? 0 : 8);
    }

    protected void m10694c(boolean z) {
        this.f7714p.setVisibility(z ? 0 : 8);
    }

    public void finish() {
        overridePendingTransition(17432576, 17432577);
        super.finish();
    }

    public void onClick(View view) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.activity_image_pager);
        ShareSDK.initSDK(this);
        this.f7722x = (ImageButton) findViewById(C1205R.id.playButton);
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        this.f7708j = (C1313t) C1276b.m8680a().m8699d();
        this.f7708j.m8832a((C1213e) this);
        findViewById(C1205R.id.prll_tab_top).setBackgroundResource(C1205R.color.light_black_80);
        findViewById(C1205R.id.ll_progressBottom).setBackgroundResource(C1205R.color.light_black_80);
        this.f7714p = findViewById(C1205R.id.rl_tab_top);
        this.f7714p.setOnClickListener(this);
        this.f7713o = findViewById(C1205R.id.ll_actionBottom);
        this.f7713o.setOnClickListener(this);
        this.f7713o.setBackgroundResource(C1205R.drawable.title_bottom_share_bg);
        this.f7715q = findViewById(C1205R.id.ll_progressBottom);
        this.f7701F = (TextView) findViewById(C1205R.id.file_name_tv);
        this.f7702G = (TextView) findViewById(C1205R.id.file_percent_tv);
        this.f7703H = (TextView) findViewById(C1205R.id.down_speed);
        this.f7699D = (Button) findViewById(C1205R.id.btn_cancel);
        this.f7700E = (Button) findViewById(C1205R.id.btn_start);
        be.m12359a(getAssets(), this.f7699D, this.f7700E, this.f7701F, this.f7702G, this.f7703H);
        this.f7697B = (ProgressView) this.f7715q.findViewById(C1205R.id.pv_progress);
        this.f7697B.setFrontColor(-42978);
        this.f7705J = C1224m.f5512a;
        this.f7717s = getIntent().getStringArrayListExtra(f7691c);
        this.f7718t = getIntent().getStringArrayListExtra(f7693e);
        this.f7709k = getIntent().getBooleanExtra(f7692d, false);
        this.f7719u = getIntent().getStringArrayListExtra(f7694f);
        if (getIntent().getIntExtra(f7690b, 1) == 2) {
            this.f7722x.setVisibility(0);
        } else {
            this.f7722x.setVisibility(8);
        }
        if (bundle != null) {
            this.f7711m = bundle.getInt(f7695i);
        }
        this.f7698C = new ac(this);
        m10658a();
        m10661b();
        m10669f();
        this.f7722x.setOnClickListener(new C1589c(this));
    }

    protected void onResume() {
        super.onResume();
        if (this.f7696A == null || this.f7696A.m7785g() != C1115d.Pause) {
            this.f7700E.setVisibility(8);
            this.f7699D.setVisibility(0);
            return;
        }
        this.f7700E.setVisibility(0);
        this.f7699D.setVisibility(8);
        long i = this.f7696A.m7787i();
        long k = this.f7696A.m7789k();
        this.f7697B.setMaxCount((float) i);
        this.f7697B.setCurrentCount((float) k);
        this.f7701F.setText(String.format(getString(C1205R.string.download_file_phone), new Object[]{m10671g()}));
        this.f7702G.setText((k / (i / 100)) + "%");
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(f7695i, this.f7710l.getCurrentItem());
    }
}
