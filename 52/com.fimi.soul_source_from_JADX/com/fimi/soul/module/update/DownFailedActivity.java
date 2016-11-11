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
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.module.droneui.FlightActivity;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import org.codehaus.jackson.smile.SmileConstants;

public class DownFailedActivity extends BaseActivity implements OnClickListener {
    private TextView f9677a;
    private TextView f9678b;
    private Button f9679c;
    private Button f9680d;
    private ImageView f9681e;
    private Boolean f9682f;
    private Boolean f9683g;

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.download_later:
                if (this.f9682f.booleanValue()) {
                    Editor edit = ay.m12293a((Context) this).edit();
                    edit.putBoolean(C1236a.f5586J, true);
                    edit.commit();
                    this.dpa.m8525d();
                    return;
                }
                startActivity(new Intent(this, FlightActivity.class));
            case C1205R.id.retry_btn:
                if (be.m12370b((Context) this)) {
                    startActivity(new Intent(this, DowningActivity.class));
                    finish();
                    overridePendingTransition(17432576, 17432577);
                    return;
                }
                ak.m8082a((Context) this, (int) C1205R.string.no_available_network);
            default:
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.dpa.m8523b((Activity) this);
        SharedPreferences a = ay.m12293a((Context) this);
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        this.f9682f = Boolean.valueOf(a.getBoolean(C1236a.f5588L, false));
        setContentView(C1205R.layout.activity_down_failed);
        this.f9677a = (TextView) findViewById(C1205R.id.downResult);
        this.f9678b = (TextView) findViewById(C1205R.id.downFailedText);
        be.m12359a(getAssets(), this.f9677a, this.f9678b);
        this.f9680d = (Button) findViewById(C1205R.id.download_later);
        this.f9680d.setOnClickListener(this);
        this.f9679c = (Button) findViewById(C1205R.id.retry_btn);
        this.f9679c.setOnClickListener(this);
        this.f9681e = (ImageView) findViewById(C1205R.id.upgrade_iv);
        this.f9683g = Boolean.valueOf(getIntent().getBooleanExtra(C1236a.f5586J, true));
        if (!this.f9683g.booleanValue()) {
            this.f9678b.setText(getIntent().getStringExtra("reson"));
        }
    }
}
