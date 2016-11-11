package com.fimi.soul.module.update;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.kernel.C1189f;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.update.C1403p;
import com.fimi.soul.biz.update.ak;
import com.fimi.soul.module.droneui.FlightActivity;
import com.fimi.soul.module.setting.newhand.NewHandActivity;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import org.codehaus.jackson.smile.SmileConstants;

public class FindOnlineFirmwareAvtivity extends BaseActivity implements OnClickListener {
    private Button f9714a;
    private Button f9715b;
    private Boolean f9716c;
    private ImageView f9717d;
    private TextView f9718e;
    private TextView f9719f;

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.btn_down_after:
                if (!C1189f.m8333c().m7937d(C1236a.f5621s)) {
                    startActivity(new Intent(this, NewHandActivity.class));
                    finish();
                    overridePendingTransition(17432576, 17432577);
                } else if (this.f9716c.booleanValue()) {
                    Editor edit = ay.m12293a((Context) this).edit();
                    edit.putBoolean(C1236a.f5586J, true);
                    edit.commit();
                    this.dpa.m8525d();
                } else {
                    startActivity(new Intent(this, FlightActivity.class));
                }
            case C1205R.id.btn_down:
                startActivity(new Intent(this, DowningActivity.class));
                finish();
                overridePendingTransition(17432576, 17432577);
            default:
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.dpa.m8523b((Activity) this);
        setContentView(C1205R.layout.activity_online_new_firmware);
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        this.f9714a = (Button) findViewById(C1205R.id.btn_down_after);
        this.f9714a.setOnClickListener(this);
        this.f9715b = (Button) findViewById(C1205R.id.btn_down);
        this.f9715b.setOnClickListener(this);
        this.f9717d = (ImageView) findViewById(C1205R.id.down_img_an);
        C1403p.f6310c = true;
        C1403p.f6309b = true;
        this.f9718e = (TextView) findViewById(C1205R.id.down_firmware_title);
        this.f9719f = (TextView) findViewById(C1205R.id.down_firmware_detail);
        this.f9719f.setText(String.format(getString(C1205R.string.down_firmwares_detail), new Object[]{ak.m9431b()}));
        be.m12359a(getAssets(), this.f9714a, this.f9715b, this.f9718e, this.f9719f);
        this.f9716c = Boolean.valueOf(ay.m12293a((Context) this).getBoolean(C1236a.f5588L, false));
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.f9717d.setBackgroundResource(C1205R.drawable.down_anim);
        ((AnimationDrawable) this.f9717d.getBackground()).start();
    }
}
