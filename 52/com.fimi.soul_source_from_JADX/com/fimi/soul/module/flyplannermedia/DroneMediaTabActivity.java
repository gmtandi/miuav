package com.fimi.soul.module.flyplannermedia;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fimi.kernel.C1189f;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.utils.be;
import org.codehaus.jackson.smile.SmileConstants;

public class DroneMediaTabActivity extends BaseActivity implements OnClickListener {
    private DroneOnlineFragment f8664a;
    private DroneLocalFragment f8665b;
    private boolean f8666c;
    private RelativeLayout f8667d;
    private RelativeLayout f8668e;
    private ImageView f8669f;
    private ImageView f8670g;
    private TextView f8671h;
    private TextView f8672i;
    private View f8673j;
    private View f8674k;
    private View f8675l;
    private View f8676m;
    private ImageButton f8677n;

    public DroneMediaTabActivity() {
        this.f8666c = false;
    }

    private void m11420c() {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        if (this.f8666c) {
            this.f8672i.setTextColor(getResources().getColor(C1205R.color.drone_tab_gray_color));
            this.f8670g.setVisibility(4);
            this.f8671h.setTextColor(-1);
            this.f8669f.setVisibility(0);
            beginTransaction.replace(C1205R.id.ll_content, this.f8664a);
            beginTransaction.addToBackStack(null);
            beginTransaction.commitAllowingStateLoss();
            return;
        }
        this.f8671h.setTextColor(getResources().getColor(C1205R.color.drone_tab_gray_color));
        this.f8669f.setVisibility(4);
        this.f8672i.setTextColor(-1);
        this.f8670g.setVisibility(0);
        beginTransaction.replace(C1205R.id.ll_content, this.f8665b);
        beginTransaction.addToBackStack(null);
        beginTransaction.commitAllowingStateLoss();
    }

    public View m11421a() {
        return this.f8674k;
    }

    public void m11422a(boolean z) {
        this.f8676m.setVisibility(z ? 0 : 8);
    }

    public View m11423b() {
        return this.f8675l;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0 && intent != null) {
            switch (i2) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    this.f8665b.m11414a(intent);
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    this.f8664a.m11441a(intent);
                default:
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == C1205R.id.local_layout) {
            this.f8666c = false;
        }
        if (view.getId() == C1205R.id.online_layout) {
            this.f8666c = true;
        }
        m11420c();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f8664a = new DroneOnlineFragment();
        this.f8665b = new DroneLocalFragment();
        setContentView(C1205R.layout.activity_drone_media_tab);
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        this.f8673j = findViewById(C1205R.id.rl_tab_top);
        this.f8673j.findViewById(C1205R.id.ll_center_tab).setVisibility(0);
        this.f8677n = (ImageButton) this.f8673j.findViewById(C1205R.id.btn_back);
        this.f8674k = findViewById(C1205R.id.ll_actionBottom);
        this.f8674k.setVisibility(8);
        this.f8675l = findViewById(C1205R.id.ll_actionTop);
        this.f8667d = (RelativeLayout) findViewById(C1205R.id.online_layout);
        this.f8668e = (RelativeLayout) findViewById(C1205R.id.local_layout);
        this.f8667d.setOnClickListener(this);
        this.f8668e.setOnClickListener(this);
        this.f8671h = (TextView) findViewById(C1205R.id.tv_online);
        this.f8672i = (TextView) findViewById(C1205R.id.tv_local);
        be.m12358a(C1189f.m8327a().getResources().getAssets(), (ViewGroup) getWindow().getDecorView());
        this.f8669f = (ImageView) findViewById(C1205R.id.iv_onlineDot);
        this.f8670g = (ImageView) findViewById(C1205R.id.iv_localDot);
        this.f8676m = findViewById(C1205R.id.ll_empty);
        this.f8677n.setOnClickListener(new C1771k(this));
        this.f8666c = ((C1313t) C1276b.m8680a().m8699d()).m8848d();
        m11420c();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || (m11423b().getVisibility() != 0 && m11421a().getVisibility() != 0)) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.f8666c) {
            this.f8664a.m11448o();
        } else {
            this.f8665b.m11419o();
        }
        return true;
    }

    public void onSaveInstanceState(Bundle bundle) {
    }
}
