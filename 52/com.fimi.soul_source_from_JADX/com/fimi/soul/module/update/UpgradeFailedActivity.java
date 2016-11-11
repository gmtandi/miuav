package com.fimi.soul.module.update;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.drone.p110d.C1488o;
import com.fimi.soul.module.droneui.FlightActivity;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import org.codehaus.jackson.smile.SmileConstants;

public class UpgradeFailedActivity extends BaseActivity implements OnClickListener {
    private TextView f9735a;
    private TextView f9736b;
    private Button f9737c;
    private Button f9738d;
    private ImageView f9739e;
    private Boolean f9740f;
    private Boolean f9741g;

    public void m11957a() {
        C1488o.f7054a.f6936d = (byte) 0;
        C1488o.f7054a.f6937e = (byte) 2;
        this.drone.m9569P().m9993a(C1488o.f7054a.m9794a());
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.retry_btn:
                if (this.drone.m9569P().m9995a()) {
                    startActivity(new Intent(this, UpgradingActivity.class));
                    finish();
                    overridePendingTransition(17432576, 17432577);
                    return;
                }
                finish();
            case C1205R.id.noconnect_bt:
                if (this.f9740f.booleanValue()) {
                    Editor edit = ay.m12293a((Context) this).edit();
                    edit.putBoolean(C1236a.f5586J, true);
                    edit.commit();
                    this.dpa.m8525d();
                    return;
                }
                startActivity(new Intent(this, FlightActivity.class));
            default:
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.dpa.m8523b((Activity) this);
        SharedPreferences a = ay.m12293a((Context) this);
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        this.f9740f = Boolean.valueOf(a.getBoolean(C1236a.f5588L, false));
        setContentView(C1205R.layout.activity_upgrade_failed);
        this.f9735a = (TextView) findViewById(C1205R.id.upgradeResult);
        be.m12359a(getAssets(), this.f9735a);
        this.f9736b = (TextView) findViewById(C1205R.id.failedText);
        be.m12359a(getAssets(), this.f9736b);
        this.f9738d = (Button) findViewById(C1205R.id.noconnect_bt);
        this.f9738d.setOnClickListener(this);
        this.f9737c = (Button) findViewById(C1205R.id.retry_btn);
        this.f9737c.setOnClickListener(this);
        this.f9739e = (ImageView) findViewById(C1205R.id.upgrade_iv);
        this.f9741g = Boolean.valueOf(getIntent().getBooleanExtra(C1236a.f5586J, true));
        if (!this.f9741g.booleanValue()) {
            this.f9736b.setText(getIntent().getStringExtra("reson"));
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        m11957a();
    }
}
