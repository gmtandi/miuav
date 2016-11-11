package com.fimi.soul.module.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p103k.C1396u;
import com.fimi.soul.biz.p103k.ba;
import com.fimi.soul.service.InitAppService;
import com.fimi.soul.utils.aa;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import it.p074a.p075a.C2799f;

public class LaunchedActivity extends BaseActivity implements Callback, C1396u {
    private static final int f8759e = 2;
    private static final int f8760f = 3;
    boolean f8761a;
    Intent f8762b;
    SharedPreferences f8763c;
    private final int f8764d;
    private Context f8765g;
    private ba f8766h;
    private Handler f8767i;
    private boolean f8768j;
    private TextView f8769k;
    private TextView f8770l;

    public LaunchedActivity() {
        this.f8764d = C2799f.f14263a;
        this.f8768j = false;
        this.f8761a = false;
    }

    private void m11472a(boolean z) {
        this.f8768j = z;
        Message message = new Message();
        message.obj = Boolean.valueOf(this.f8768j);
        message.what = f8759e;
        this.f8767i.sendMessageDelayed(message, 500);
    }

    private void m11474b(boolean z) {
        if (!this.f8761a) {
            be.m12393k(this);
            this.f8761a = true;
            this.f8767i.removeMessages(f8759e);
            this.f8767i.removeMessages(f8760f);
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("login", z);
            intent.putExtra("islaunchexit", true);
            startActivity(intent);
            finish();
            overridePendingTransition(17432576, 17432577);
        }
    }

    public void m11475a(AMapLocation aMapLocation) {
    }

    public boolean handleMessage(Message message) {
        m11474b(((Boolean) message.obj).booleanValue());
        return false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.activity_launch);
        aa.m12215d();
        Editor edit = ay.m12293a((Context) this).edit();
        edit.putBoolean(C1236a.f5588L, false);
        edit.commit();
        this.f8767i = new Handler(this);
        this.mTintManager.m8047a(true, (Activity) this);
        this.f8765g = this;
        this.f8762b = new Intent(this, InitAppService.class);
        startService(this.f8762b);
        this.f8763c = ay.m12293a((Context) this);
        Message message = new Message();
        message.obj = Boolean.valueOf(!this.f8763c.contains("isfirstloading"));
        message.what = f8760f;
        this.f8767i.sendMessageDelayed(message, 2000);
        this.f8769k = (TextView) findViewById(C1205R.id.launch_center_tv);
        this.f8770l = (TextView) findViewById(C1205R.id.launch_bottom_tv);
        AssetManager assets = getAssets();
        View[] viewArr = new View[f8759e];
        viewArr[0] = this.f8769k;
        viewArr[1] = this.f8770l;
        be.m12359a(assets, viewArr);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f8761a = true;
    }

    protected void onResume() {
        super.onResume();
        this.f8761a = false;
        ah.m8075a(new C1790b(this));
    }
}
