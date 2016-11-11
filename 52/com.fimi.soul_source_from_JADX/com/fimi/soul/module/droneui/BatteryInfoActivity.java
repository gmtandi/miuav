package com.fimi.soul.module.droneui;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.kernel.p084e.ab;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p110d.C1478e;
import com.fimi.soul.utils.be;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

public class BatteryInfoActivity extends BaseActivity implements OnClickListener, C1234i {
    private Button f8545a;
    private TextView f8546b;
    private TextView f8547c;
    private TextView f8548d;
    private TextView f8549e;
    private TextView f8550f;
    private TextView f8551g;
    private TextView f8552h;
    private TextView f8553i;
    private TextView f8554j;
    private TextView f8555k;
    private TextView f8556l;
    private TextView f8557m;
    private double f8558n;
    private double f8559o;
    private boolean f8560p;

    public BatteryInfoActivity() {
        this.f8560p = true;
    }

    private String m11312a(int i) {
        return ab.m8016b((((double) i) / 100.0d) + 2.0d, 2) + " V";
    }

    private void m11313a() {
        if (this.drone.m9570Q()) {
            if (this.f8560p) {
                C1478e.m9859a(this.drone);
            }
            this.f8547c.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + ((this.drone.m9619v().m10523i() - 60) + getString(C1205R.string.temp_unit)));
            m11315c();
        }
    }

    private void m11314b() {
        this.f8545a = (Button) findViewById(C1205R.id.back_btn);
        this.f8545a.setOnClickListener(new C1740a(this));
    }

    private void m11315c() {
        this.f8558n = (double) this.drone.m9623z().m10530e();
        this.f8559o = (double) this.drone.m9623z().m10529d();
        if (this.f8558n > 0.0d) {
            int g = this.drone.m9619v().m10521g();
            if (g < 0) {
                g = 0;
            }
            int i = (int) ((((double) g) / this.f8558n) * 100.0d);
            int i2 = (int) ((((double) g) / this.f8559o) * 100.0d);
            if (i >= 0) {
                if (i > 100) {
                    this.f8558n = 0.0d;
                    return;
                }
                if (i2 <= 30) {
                    this.f8548d.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i2 + "%");
                } else {
                    this.f8548d.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i + "%");
                }
                short j = this.drone.m9619v().m10524j();
                if (j >= (short) 0 && j <= (short) 35) {
                    if (this.f8559o > this.f8558n) {
                        i = (int) ((this.f8558n / this.f8559o) * 100.0d);
                        this.f8551g.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + ((int) this.f8559o) + " mAH");
                    } else {
                        i = (int) ((this.f8559o / this.f8558n) * 100.0d);
                        this.f8551g.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + ((int) this.f8558n) + " mAH");
                    }
                    this.f8546b.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + j + getString(C1205R.string.time_unit));
                    this.f8550f.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + g + " mAH");
                    this.f8549e.setText(ab.m8016b((((double) (((this.drone.m9619v().m10514a() + this.drone.m9619v().m10516b()) + this.drone.m9619v().m10517c()) + this.drone.m9619v().m10518d())) / 100.0d) + 8.0d, 2) + " V");
                    this.f8554j.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + m11312a(this.drone.m9619v().m10514a()));
                    this.f8555k.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + m11312a(this.drone.m9619v().m10516b()));
                    this.f8556l.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + m11312a(this.drone.m9619v().m10517c()));
                    this.f8557m.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + m11312a(this.drone.m9619v().m10518d()));
                    this.f8553i.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.drone.m9623z().f7512d + C2915a.f14760f);
                    this.f8552h.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i + "%");
                }
            }
        }
    }

    public void onClick(View view) {
        view.getId();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        setContentView(C1205R.layout.battery_info);
        this.f8546b = (TextView) findViewById(C1205R.id.endurance);
        this.f8549e = (TextView) findViewById(C1205R.id.voltage);
        this.f8547c = (TextView) findViewById(C1205R.id.temperature);
        this.f8548d = (TextView) findViewById(C1205R.id.residue_capacity);
        this.f8550f = (TextView) findViewById(C1205R.id.current_capacity);
        this.f8551g = (TextView) findViewById(C1205R.id.all_capacity);
        this.f8552h = (TextView) findViewById(C1205R.id.battery_life);
        this.f8553i = (TextView) findViewById(C1205R.id.discharge_times);
        this.f8554j = (TextView) findViewById(C1205R.id.battery_one);
        this.f8555k = (TextView) findViewById(C1205R.id.battery_two);
        this.f8556l = (TextView) findViewById(C1205R.id.battery_three);
        this.f8557m = (TextView) findViewById(C1205R.id.battery_four);
        m11314b();
        m11313a();
        be.m12357a(getAssets(), getWindow().getDecorView());
        this.drone.m9590a((C1234i) this);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.drone.m9594b((C1234i) this);
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (C1741b.f8598a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                m11313a();
                this.f8560p = false;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                m11313a();
                break;
        }
        if (!c1433a.m9570Q()) {
            this.f8560p = true;
        }
    }

    public void onHandleMessage(Message message) {
    }
}
