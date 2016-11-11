package com.fimi.soul.module.remote;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.kernel.p084e.ak;
import com.fimi.kernel.view.percent.PercentRelativeLayout;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.module.calibcompass.C1683q;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.aq;
import com.tencent.mm.sdk.platformtools.Util;
import org.codehaus.jackson.smile.SmileConstants;

public class RemoteModelActivity extends BaseActivity implements OnClickListener, C1234i {
    public static final String f8968a = "remodel";
    private PercentRelativeLayout f8969b;
    private Button f8970c;
    private Button f8971d;
    private Button f8972e;
    private TextView f8973f;
    private TextView f8974g;
    private TextView f8975h;
    private SharedPreferences f8976i;
    private Editor f8977j;
    private ImageView f8978k;
    private ImageView f8979l;
    private C1664h f8980m;
    private boolean f8981n;

    private void m11570a() {
        this.f8981n = false;
        this.f8978k.setImageResource(C1205R.drawable.switchover_japan_right);
        this.f8979l.setImageResource(C1205R.drawable.switchover_japan__leftbg);
        m11572a(this.f8972e, 75);
        m11572a(this.f8971d, Util.MASK_8BIT);
        this.f8972e.setBackgroundResource(C1205R.drawable.btn_switchover_left);
        this.f8971d.setBackgroundResource(C1205R.drawable.btn_switchover_right_on);
    }

    private void m11571b() {
        this.f8981n = true;
        this.f8978k.setImageResource(C1205R.drawable.switchover_usa_right_bg);
        this.f8979l.setImageResource(C1205R.drawable.switchover_usa_left_bg);
        m11572a(this.f8972e, Util.MASK_8BIT);
        m11572a(this.f8971d, 75);
        this.f8972e.setBackgroundResource(C1205R.drawable.btn_switchover_left_on);
        this.f8971d.setBackgroundResource(C1205R.drawable.btn_switchover_right);
    }

    public void m11572a(Button button, int i) {
        button.setTextColor(button.getTextColors().withAlpha(i));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.usabutton:
                if (!this.f8981n) {
                    if (!this.drone.m9569P().m9995a()) {
                        ak.m8085a((Context) this, getString(C1205R.string.switch_mode_fail_please_connect_remote), ak.f5302b);
                    } else if (this.drone.m9570Q()) {
                        ak.m8085a((Context) this, getString(C1205R.string.close_plane_switch_rocket), ak.f5302b);
                    } else {
                        new aq(this).m12748a(getString(C1205R.string.choose_recoker_mode_msg)).m12754c(17).m12747a(getResources().getColor(C1205R.color.dialog_ensure_hot)).m12753b(getString(C1205R.string.ensure), new C1832m(this)).m12749a(getString(C1205R.string.cancel), new C1831l(this)).m12746a().show();
                    }
                }
            case C1205R.id.jpabutton:
                if (!this.f8981n) {
                    return;
                }
                if (!this.drone.m9569P().m9995a()) {
                    ak.m8085a((Context) this, getString(C1205R.string.switch_mode_fail_please_connect_remote), ak.f5302b);
                } else if (this.drone.m9570Q()) {
                    ak.m8085a((Context) this, getString(C1205R.string.close_plane_switch_rocket), ak.f5302b);
                } else {
                    new aq(this).m12748a(getString(C1205R.string.choose_recoker_mode_msg)).m12754c(17).m12747a(getResources().getColor(C1205R.color.dialog_ensure_hot)).m12753b(getString(C1205R.string.ensure), new C1834o(this)).m12749a(getString(C1205R.string.cancel), new C1833n(this)).m12746a().show();
                }
            case C1205R.id.black_btn:
                finish();
            default:
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.droneremotemodel);
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        this.f8969b = (PercentRelativeLayout) findViewById(C1205R.id.heardView);
        this.f8970c = (Button) this.f8969b.findViewById(C1205R.id.black_btn);
        this.f8970c.setOnClickListener(this);
        this.f8972e = (Button) findViewById(C1205R.id.usabutton);
        this.f8972e.setOnClickListener(this);
        this.f8971d = (Button) findViewById(C1205R.id.jpabutton);
        this.f8971d.setOnClickListener(this);
        this.f8978k = (ImageView) findViewById(C1205R.id.remoteviewright);
        this.f8979l = (ImageView) findViewById(C1205R.id.remoteviewleft);
        this.f8973f = (TextView) findViewById(C1205R.id.tv_settingTitle);
        this.f8974g = (TextView) findViewById(C1205R.id.leftremotetext);
        this.f8975h = (TextView) findViewById(C1205R.id.rightremotetext);
        this.f8973f.setText(C1205R.string.switch_remote_mode);
        be.m12359a(getAssets(), this.f8973f, this.f8974g, this.f8975h, this.f8972e, this.f8971d);
        this.f8976i = ay.m12293a((Context) this);
        if (this.f8976i.getInt(f8968a, 0) == 0) {
            m11571b();
        } else {
            m11570a();
        }
        this.f8977j = this.f8976i.edit();
        this.f8980m = C1664h.m10813a(this.drone);
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        super.onDroneEvent(c1584h, c1433a);
        switch (C1835p.f9019a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                C1683q.m10886a().m10887a("98");
                if (c1433a.m9561H().m10512c() == (byte) 2) {
                    if (c1433a.m9561H().m10513d() == (byte) 2) {
                        this.f8977j.putInt(f8968a, 1);
                        m11570a();
                    } else if (c1433a.m9561H().m10513d() == (byte) 1) {
                        this.f8977j.putInt(f8968a, 0);
                        m11571b();
                    }
                    this.f8977j.commit();
                }
            default:
        }
    }

    protected void onResume() {
        super.onResume();
        this.drone.m9590a((C1234i) this);
    }

    protected void onStop() {
        super.onStop();
        this.drone.m9594b((C1234i) this);
    }
}
