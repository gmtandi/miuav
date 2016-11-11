package com.fimi.soul.module.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.kernel.C1189f;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.module.update.C1921n;
import com.fimi.soul.module.update.p121a.C1902b;
import com.fimi.soul.module.update.p121a.C1907g;
import com.fimi.soul.utils.be;

public class AboutActivity extends BaseActivity implements OnClickListener {
    private static final int f9023A = 0;
    private static final int f9024B = 1;
    private static final int f9025z = 1;
    private int f9026C;
    C1907g f9027a;
    private Button f9028b;
    private Button f9029c;
    private TextView f9030d;
    private TextView f9031e;
    private ImageView f9032f;
    private TextView f9033g;
    private C1902b f9034h;
    private TextView f9035i;
    private TextView f9036j;
    private TextView f9037k;
    private TextView f9038l;
    private TextView f9039m;
    private TextView f9040n;
    private TextView f9041o;
    private TextView f9042p;
    private TextView f9043q;
    private TextView f9044r;
    private TextView f9045s;
    private TextView f9046t;
    private TextView f9047u;
    private TextView f9048v;
    private TextView f9049w;
    private TextView f9050x;
    private C1313t f9051y;

    public AboutActivity() {
        this.f9026C = f9023A;
    }

    private String m11578a(int i) {
        return String.valueOf(i);
    }

    private void m11579a() {
        this.f9028b = (Button) findViewById(C1205R.id.aboutBtn);
        this.f9028b.setOnClickListener(new C1845a(this));
    }

    private void m11580b() {
        this.f9033g = (TextView) findViewById(C1205R.id.copyright);
        this.f9030d = (TextView) findViewById(C1205R.id.aboutVersion);
        this.f9031e = (TextView) findViewById(C1205R.id.agreementshengming_tv);
        this.f9031e.setOnClickListener(this);
        this.f9031e.getPaint().setFlags(8);
        this.f9031e.getPaint().setAntiAlias(true);
        TextView textView = this.f9030d;
        Object[] objArr = new Object[f9025z];
        objArr[f9023A] = be.m12378d((Context) this);
        textView.setText(getString(C1205R.string.about_version, objArr));
        this.f9029c = (Button) findViewById(C1205R.id.scoreBtn);
        this.f9029c.setOnClickListener(new C1846b(this));
        this.f9032f = (ImageView) findViewById(C1205R.id.aboutImg);
        this.f9039m = (TextView) findViewById(C1205R.id.tv_camera);
        this.f9035i = (TextView) findViewById(C1205R.id.tv_fc);
        this.f9037k = (TextView) findViewById(C1205R.id.tv_x2);
        this.f9036j = (TextView) findViewById(C1205R.id.tv_x6);
        this.f9038l = (TextView) findViewById(C1205R.id.tv_rc);
        this.f9040n = (TextView) findViewById(C1205R.id.tv_gimbal);
        this.f9041o = (TextView) findViewById(C1205R.id.tv_servo);
        this.f9042p = (TextView) findViewById(C1205R.id.tv_nofly);
        this.f9043q = (TextView) findViewById(C1205R.id.tv_fc_tip);
        this.f9044r = (TextView) findViewById(C1205R.id.tv_x6_tip);
        this.f9045s = (TextView) findViewById(C1205R.id.tv_x2_tip);
        this.f9046t = (TextView) findViewById(C1205R.id.tv_rc_tip);
        this.f9047u = (TextView) findViewById(C1205R.id.tv_camera_tip);
        this.f9048v = (TextView) findViewById(C1205R.id.tv_gimbal_tip);
        this.f9049w = (TextView) findViewById(C1205R.id.tv_servo_tip);
        this.f9050x = (TextView) findViewById(C1205R.id.tv_nofly_tip);
        this.f9027a = new C1907g(this, this.drone);
    }

    private void m11581c() {
        this.f9034h = (C1902b) C1189f.m8333c().m7926a(C1921n.f9846k, C1902b.class);
        if (this.f9034h != null && this.f9034h.m12014b() > 0) {
            this.f9038l.setText(m11578a(this.f9034h.m12014b()));
        } else if (this.f9026C == f9025z) {
            this.f9038l.setText(C1205R.string.no_get_versin);
        }
        if (this.f9034h != null && this.f9034h.m12012a() > 0) {
            this.f9035i.setText(m11578a(this.f9034h.m12012a()));
        } else if (this.f9026C == f9025z) {
            this.f9035i.setText(C1205R.string.no_get_versin);
        }
        if (this.f9034h != null && this.f9034h.m12016c() > 0) {
            this.f9040n.setText(m11578a(this.f9034h.m12016c()));
        } else if (this.f9026C == f9025z) {
            this.f9040n.setText(C1205R.string.no_get_versin);
        }
        if (this.f9034h != null && this.f9034h.m12026h() > 0) {
            this.f9041o.setText(m11578a(this.f9034h.m12026h()));
        } else if (this.f9026C == f9025z) {
            this.f9041o.setText(C1205R.string.no_get_versin);
        }
        if (this.f9034h != null && this.f9034h.m12022f() > 0) {
            this.f9037k.setText(m11578a(this.f9034h.m12022f()));
        } else if (this.f9026C == f9025z) {
            this.f9037k.setText(C1205R.string.no_get_versin);
        }
        if (this.f9034h != null && this.f9034h.m12024g() > 0) {
            this.f9042p.setText(m11578a(this.f9034h.m12024g()));
        } else if (this.f9026C == f9025z) {
            this.f9042p.setText(C1205R.string.no_get_versin);
        }
        if (this.f9034h != null && this.f9034h.m12020e() > 0) {
            this.f9036j.setText(m11578a(this.f9034h.m12020e()));
        } else if (this.f9026C == f9025z) {
            this.f9036j.setText(C1205R.string.no_get_versin);
        }
        if (this.f9034h != null && this.f9034h.m12018d() > 0) {
            this.f9039m.setText(m11578a(this.f9034h.m12018d()));
        } else if (this.f9026C == f9025z) {
            this.f9039m.setText(C1205R.string.no_get_versin);
        }
        if (this.f9026C != f9025z) {
            m11582d();
        }
    }

    private void m11582d() {
        this.f9026C = f9025z;
        new Thread(new C1847c(this)).start();
        getHandler().sendEmptyMessageDelayed(f9025z, 2200);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.agreementshengming_tv:
                Intent intent = new Intent(this, ShowTextActivity.class);
                intent.putExtra(ShowTextActivity.f9165c, C1236a.f5610h);
                intent.putExtra(ShowTextActivity.f9163a, C1205R.string.agreement_shengming);
                intent.putExtra(ShowTextActivity.f9164b, "file:///android_asset/statement.html");
                startActivity(intent);
            default:
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.activity_about);
        this.f9051y = (C1313t) C1276b.m8680a().m8699d();
        m11579a();
        m11580b();
        m11581c();
        be.m12359a(getAssets(), this.f9030d, this.f9031e, this.f9028b, this.f9033g, this.f9035i, this.f9036j, this.f9037k, this.f9038l, this.f9039m, this.f9040n, this.f9041o, this.f9042p, this.f9043q, this.f9044r, this.f9045s, this.f9046t, this.f9047u, this.f9048v, this.f9049w, this.f9050x);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f9027a != null) {
            this.f9027a.m12074c();
        }
    }

    public void onHandleMessage(Message message) {
        if (message.what == f9025z) {
            m11581c();
        }
    }
}
