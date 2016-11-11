package com.fimi.soul.module.setting;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.utils.be;
import java.text.DecimalFormat;
import org.codehaus.jackson.smile.SmileConstants;

public class FlyRecordActivity extends BaseActivity implements OnClickListener {
    TextView f9066a;
    TextView f9067b;
    TextView f9068c;
    TextView f9069d;
    TextView f9070e;
    TextView f9071f;
    DroidPlannerApp f9072g;
    C1433a f9073h;
    ImageView f9074i;

    private void m11584b() {
        try {
            new Thread(new C1892v(this)).start();
        } catch (Exception e) {
            Log.d("moweiru", "e=" + e.getMessage());
            e.printStackTrace();
        }
    }

    void m11585a() {
        this.f9068c.setText(String.format("%d", new Object[]{Integer.valueOf(this.f9073h.m9571R())}));
        this.f9067b.setText(String.format("%d", new Object[]{Long.valueOf(this.f9073h.m9574U())}));
        double S = ((double) this.f9073h.m9572S()) / 1000.0d;
        DecimalFormat decimalFormat = new DecimalFormat("######0.00");
        this.f9066a.setText(String.format("%s", new Object[]{decimalFormat.format(S)}));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.back_btn:
                finish();
            default:
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.fly_record_layout);
        this.f9066a = (TextView) findViewById(C1205R.id.fly_Killometers);
        this.f9067b = (TextView) findViewById(C1205R.id.fly_long);
        this.f9068c = (TextView) findViewById(C1205R.id.fly_times);
        this.f9069d = (TextView) findViewById(C1205R.id.killo_v);
        this.f9070e = (TextView) findViewById(C1205R.id.fly_min_v);
        this.f9071f = (TextView) findViewById(C1205R.id.fly_count_v);
        this.f9074i = (ImageView) findViewById(C1205R.id.back_btn);
        this.f9074i.setOnClickListener(this);
        be.m12368b(getAssets(), this.f9066a, this.f9067b, this.f9068c);
        be.m12359a(getAssets(), this.f9069d, this.f9070e, this.f9071f);
        getWindow().addFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
    }

    protected void onResume() {
        super.onResume();
        this.f9072g = (DroidPlannerApp) getApplication();
        this.f9073h = this.f9072g.f5570a;
        m11585a();
        m11584b();
    }
}
