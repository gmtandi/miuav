package com.fimi.soul.module.update;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.entity.FirmwareInfo;
import com.fimi.soul.utils.be;
import java.util.List;

public class UpgradeResultActivity extends BaseActivity implements OnClickListener {
    public static final String f9742a = "result_list";
    StringBuffer f9743b;
    StringBuffer f9744c;
    private TextView f9745d;
    private TextView f9746e;
    private TextView f9747f;
    private TextView f9748g;
    private Button f9749h;

    private String m11958a(StringBuffer stringBuffer, int i) {
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return String.format(getString(i), new Object[]{stringBuffer.toString()});
    }

    private void m11959a() {
        this.f9745d = (TextView) findViewById(C1205R.id.tv_result);
        this.f9746e = (TextView) findViewById(C1205R.id.tv_sucess_firmware);
        this.f9747f = (TextView) findViewById(C1205R.id.tv_fail_firmwares);
        this.f9749h = (Button) findViewById(C1205R.id.bt_ensure);
        this.f9749h.setOnClickListener(this);
        this.f9748g = (TextView) findViewById(C1205R.id.warm_prompt);
        be.m12359a(getAssets(), this.f9745d, this.f9746e, this.f9747f, this.f9748g);
    }

    private void m11960b() {
        List<FirmwareInfo> list = (List) getIntent().getSerializableExtra(f9742a);
        this.f9743b = new StringBuffer();
        this.f9744c = new StringBuffer();
        if (list != null && list.size() > 0) {
            for (FirmwareInfo firmwareInfo : list) {
                if (firmwareInfo.isUpgradeSuccess().booleanValue()) {
                    this.f9743b.append(firmwareInfo.getSysName() + getString(C1205R.string.upgrade_symbol));
                } else {
                    this.f9744c.append(firmwareInfo.getSysName() + getString(C1205R.string.upgrade_symbol));
                }
            }
        }
        if (this.f9743b.length() > 0 && this.f9744c.length() == 0) {
            this.f9747f.setVisibility(8);
            this.f9748g.setVisibility(8);
            this.f9746e.setText(m11958a(this.f9743b, C1205R.string.upgrade_firmware_success));
        } else if (this.f9743b.length() <= 0 || this.f9744c.length() <= 0) {
            this.f9747f.setTextSize(2, 11.3f);
            this.f9747f.setVisibility(0);
            this.f9746e.setText(m11958a(this.f9744c, C1205R.string.upgrade_firmware_failing));
            this.f9747f.setText(getString(C1205R.string.warm_prompt));
            this.f9748g.setVisibility(4);
        } else {
            this.f9747f.setVisibility(0);
            this.f9746e.setText(m11958a(this.f9743b, C1205R.string.upgrade_firmware_success));
            this.f9747f.setText(m11958a(this.f9744c, C1205R.string.upgrade_firmware_failing));
            this.f9748g.setTextSize(2, 11.3f);
            this.f9748g.setVisibility(0);
            this.f9748g.setText(getString(C1205R.string.warm_prompt));
        }
    }

    public void onClick(View view) {
        if (view == this.f9749h) {
            finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.activity_upgrade_result);
        m11959a();
        m11960b();
    }
}
