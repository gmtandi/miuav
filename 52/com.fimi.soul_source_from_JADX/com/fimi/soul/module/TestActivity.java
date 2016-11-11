package com.fimi.soul.module;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.EVview;
import java.util.Timer;

public class TestActivity extends Activity {
    private EVview f7836a;
    private int f7837b;
    private Handler f7838c;

    public TestActivity() {
        this.f7837b = 0;
        this.f7838c = new C1666b(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.ev_layout);
        this.f7836a = (EVview) findViewById(C1205R.id.ev_v);
        new Timer().schedule(new C1665a(this), 500, 1000);
    }
}
