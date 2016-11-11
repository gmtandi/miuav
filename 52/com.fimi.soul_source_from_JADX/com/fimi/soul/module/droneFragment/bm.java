package com.fimi.soul.module.droneFragment;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.maps.model.LatLng;
import com.fimi.kernel.p084e.ab;
import com.fimi.kernel.p084e.ak;
import com.fimi.kernel.view.percent.PercentRelativeLayout;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1309p;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.biz.p090b.C1253k;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.p118a.C1552a;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.module.setting.newhand.ae;
import com.fimi.soul.utils.ac;
import com.fimi.soul.utils.ad;
import com.fimi.soul.utils.aj;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.SwitchButtonStoke;
import com.fimi.soul.view.bj;
import com.fimi.soul.view.myhorizontalseebar.SeekBar;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.util.BufferRecycler;

public class bm implements OnClickListener, bj {
    private int f8223A;
    private int f8224B;
    private int f8225C;
    private int f8226D;
    private int f8227E;
    private int f8228F;
    private int f8229G;
    private double f8230H;
    private double f8231I;
    private double f8232J;
    private double f8233K;
    private double f8234L;
    private C1313t f8235M;
    private int f8236N;
    private boolean f8237O;
    private boolean f8238P;
    private int f8239Q;
    private Button f8240a;
    private Button f8241b;
    private Button f8242c;
    private TextView f8243d;
    private TextView f8244e;
    private TextView f8245f;
    private TextView f8246g;
    private TextView f8247h;
    private TextView f8248i;
    private TextView f8249j;
    private TextView f8250k;
    private TextView f8251l;
    private TextView f8252m;
    private SeekBar f8253n;
    private SeekBar f8254o;
    private SwitchButtonStoke f8255p;
    private C1433a f8256q;
    private PercentRelativeLayout f8257r;
    private ImageView f8258s;
    private int f8259t;
    private int f8260u;
    private int f8261v;
    private int f8262w;
    private int f8263x;
    private int f8264y;
    private int f8265z;

    public bm(View view, C1433a c1433a) {
        this.f8259t = 1;
        this.f8260u = 30;
        this.f8261v = 10;
        this.f8262w = 10;
        this.f8263x = Opcodes.ISHL;
        this.f8264y = C2799f.f14263a;
        this.f8265z = 4;
        this.f8223A = 8;
        this.f8228F = 20;
        this.f8229G = 1;
        this.f8236N = ae.f9482j;
        this.f8238P = false;
        this.f8239Q = 0;
        this.f8256q = c1433a;
        m11049a(view);
        this.f8235M = (C1313t) C1276b.m8680a().m8699d();
    }

    private int m11045a(double d) {
        int i = 1;
        this.f8230H = d;
        double sin = (double) ((int) (((double) this.f8265z) / Math.sin(Math.toRadians(d))));
        double cos = (double) ((int) (((double) this.f8223A) / Math.cos(Math.toRadians(d))));
        if (Math.cos(Math.toRadians(d)) * sin <= ((double) this.f8223A) && Math.sin(Math.toRadians(d)) * cos > ((double) this.f8265z)) {
            this.f8224B = (int) sin;
        }
        if (Math.cos(Math.toRadians(d)) * sin > ((double) this.f8223A) && Math.sin(Math.toRadians(d)) * cos <= ((double) this.f8265z)) {
            this.f8224B = (int) cos;
        }
        if (Math.sin(Math.toRadians(d)) * cos <= ((double) this.f8265z) && Math.cos(Math.toRadians(d)) * sin <= ((double) this.f8223A)) {
            if (sin >= cos) {
                this.f8224B = (int) sin;
            } else {
                this.f8224B = (int) cos;
            }
        }
        this.f8254o.setMax(this.f8224B);
        this.f8252m.setText(String.format(this.f8256q.f6507c.getString(C1205R.string.fly_angle), new Object[]{Integer.valueOf((int) d)}));
        int progress = this.f8254o.getProgress();
        if (progress >= 1) {
            i = progress;
        }
        m11046a(m11054j(), d, (int) (((double) this.f8264y) - m11053i()), i);
        return this.f8224B;
    }

    private int m11046a(int i, double d, int i2, int i3) {
        double sin = ((double) i) / Math.sin(Math.toRadians(d));
        double d2 = (double) this.f8236N;
        int i4 = this.f8236N;
        if ((Math.cos(Math.toRadians(d)) * sin) - m11053i() <= ((double) i4) && (Math.sin(Math.toRadians(d)) * d2) - ((this.f8256q.m9617t().m10362f() - 677216.0d) / 10.0d) <= ((double) i)) {
            if (sin <= d2) {
                this.f8227E = (int) (d2 / ((double) i3));
                this.f8225C = (int) d2;
            } else {
                this.f8227E = (int) (sin / ((double) i3));
                this.f8225C = (int) sin;
            }
        }
        if ((Math.cos(Math.toRadians(d)) * sin) - m11053i() <= ((double) i4) && (Math.sin(Math.toRadians(d)) * d2) - ((this.f8256q.m9617t().m10362f() - 677216.0d) / 10.0d) > ((double) i)) {
            this.f8227E = (int) (sin / ((double) i3));
            this.f8225C = (int) sin;
        }
        if ((sin * Math.cos(Math.toRadians(d))) - m11053i() > ((double) i4) && (Math.sin(Math.toRadians(d)) * d2) - ((this.f8256q.m9617t().m10362f() - 677216.0d) / 10.0d) <= ((double) i)) {
            this.f8227E = (int) (d2 / ((double) i3));
            this.f8225C = (int) d2;
        }
        m11055k();
        this.f8253n.setMax(this.f8227E);
        return this.f8227E;
    }

    private void m11049a(View view) {
        int i = 0;
        this.f8249j = (TextView) view.findViewById(C1205R.id.guestDestinceDes);
        this.f8250k = (TextView) view.findViewById(C1205R.id.height_diatance_guest);
        this.f8251l = (TextView) view.findViewById(C1205R.id.drone_fly_direction);
        this.f8252m = (TextView) view.findViewById(C1205R.id.drone_fly_angle);
        this.f8258s = (ImageView) view.findViewById(C1205R.id.targeImage);
        this.f8257r = (PercentRelativeLayout) view.findViewById(C1205R.id.takephoto_operaRL);
        this.f8240a = (Button) view.findViewById(C1205R.id.fly_action_cancal_btn);
        this.f8241b = (Button) view.findViewById(C1205R.id.fly_action_execute_btn);
        this.f8243d = (TextView) view.findViewById(C1205R.id.show_camera_angle);
        this.f8244e = (TextView) view.findViewById(C1205R.id.heighdes);
        this.f8245f = (TextView) view.findViewById(C1205R.id.height_value);
        this.f8246g = (TextView) view.findViewById(C1205R.id.speekdes);
        this.f8247h = (TextView) view.findViewById(C1205R.id.speek_value);
        this.f8253n = (SeekBar) view.findViewById(C1205R.id.heightseebar_value);
        this.f8253n.setOnSeekBarChangeListener(new bn(this));
        this.f8254o = (SeekBar) view.findViewById(C1205R.id.speek_seebar_value);
        this.f8254o.setOnSeekBarChangeListener(new bo(this));
        this.f8255p = (SwitchButtonStoke) view.findViewById(C1205R.id.is_auto_back);
        this.f8255p.setOnSwitchListener(new bp(this));
        this.f8248i = (TextView) view.findViewById(C1205R.id.sbbuttondes);
        this.f8242c = (Button) view.findViewById(C1205R.id.setflyphoto);
        be.m12359a(this.f8256q.f6507c.getAssets(), this.f8240a, this.f8241b, this.f8243d, this.f8244e, this.f8246g, this.f8248i, this.f8242c, this.f8249j, this.f8250k, this.f8251l, this.f8252m);
        be.m12368b(this.f8256q.f6507c.getAssets(), this.f8245f, this.f8247h);
        View[] viewArr = new View[]{this.f8240a, this.f8241b, this.f8242c};
        int length = viewArr.length;
        while (i < length) {
            View view2 = viewArr[i];
            if (view2 instanceof SwitchButtonStoke) {
                ((SwitchButtonStoke) view2).setOnSwitchListener(this);
            } else {
                view2.setOnClickListener(this);
            }
            i++;
        }
    }

    private void m11050a(TextView textView, int i, double d) {
        if (!textView.getText().toString().equals(String.format(this.f8256q.f6507c.getString(i), new Object[]{Double.valueOf(d)}))) {
            textView.setText(String.format(this.f8256q.f6507c.getString(i), new Object[]{Double.valueOf(d)}));
        }
    }

    private double m11053i() {
        aj a = ad.m12227a(this.f8256q.m9618u().m10301b(), this.f8256q.m9618u().m10302c());
        LatLng latLng = new LatLng(a.m12250a(), a.m12252b());
        a = ad.m12227a((double) this.f8256q.m9617t().m10361e(), (double) this.f8256q.m9617t().m10360d());
        return ac.m12223c(latLng, new LatLng(a.m12250a(), a.m12252b())).m12254a();
    }

    private int m11054j() {
        return (int) (((double) this.f8263x) - ((this.f8256q.m9617t().m10362f() - 677216.0d) / 10.0d));
    }

    private void m11055k() {
        int progress = this.f8254o.getProgress();
        if (progress < 1) {
            progress = 1;
        }
        progress *= this.f8253n.getProgress();
        double a = ab.m8015a((((double) progress) * Math.sin(Math.toRadians(this.f8230H))) + ((this.f8256q.m9617t().m10362f() - 677216.0d) / 10.0d), 1);
        double a2 = ab.m8015a(((double) progress) * Math.cos(Math.toRadians(this.f8230H)), 1);
        m11050a(this.f8250k, (int) C1205R.string.take_phone_fly_time, a);
        m11050a(this.f8249j, (int) C1205R.string.guest_distance_value, a2);
    }

    public LatLng m11056a(LatLng latLng, double d, double d2) {
        return ac.m12217a(latLng, d, d2);
    }

    public void m11057a() {
        if (this.f8256q.ah().m10172g().isEnforcementAtti()) {
            ak.m8083a(this.f8256q.f6507c, (int) C1205R.string.cant_fly_takePhoto_atti, 3000);
        } else if (this.f8256q.ah().m10172g().isLightStream()) {
            ak.m8083a(this.f8256q.f6507c, (int) C1205R.string.cant_fly_takePhoto_VPU, 3000);
        } else if (this.f8227E <= 0 || this.f8253n.getProgress() != 0) {
            if (this.f8235M == null || this.f8235M.m8850f() != C1309p.Recoding) {
                this.f8235M.m8876u().m8716e();
            } else {
                m11061b();
            }
            this.f8256q.m9589a(C1584h.SHOWOUTTIMEPROBAR);
        } else {
            ak.m8083a(this.f8256q.f6507c, (int) C1205R.string.set_flyTime, 3000);
        }
    }

    public void m11058a(int i) {
        this.f8226D = i;
        this.f8243d.setText(this.f8226D + this.f8256q.f6507c.getString(C1205R.string.take_photo_degree));
        m11045a((double) i);
    }

    public void m11059a(View view, boolean z) {
    }

    public void m11060a(C1552a c1552a) {
        if (c1552a != null) {
            try {
                if (this.f8232J == 0.0d) {
                    this.f8232J = (double) c1552a.m10141d();
                }
                if (this.f8233K == 0.0d) {
                    this.f8233K = (double) c1552a.m10142e();
                }
                if (this.f8234L == 0.0d) {
                    this.f8234L = (double) c1552a.m10140c();
                }
                if (this.f8231I == 0.0d) {
                    this.f8231I = (double) (c1552a.m10143f() / 10);
                }
                this.f8254o.setProgress(c1552a.m10144g() / 10);
                this.f8253n.setProgress(c1552a.m10145h() / c1552a.m10144g());
            } catch (Exception e) {
            }
        }
    }

    public void m11061b() {
        if (this.f8238P) {
            if (this.f8232J == 0.0d) {
                this.f8232J = this.f8256q.m9617t().m10362f() - 677216.0d;
            }
            if (this.f8233K == 0.0d) {
                this.f8233K = (double) this.f8256q.m9617t().m10360d();
            }
            if (this.f8234L == 0.0d) {
                this.f8234L = (double) this.f8256q.m9617t().m10361e();
            }
            if (this.f8231I == 0.0d) {
                this.f8231I = this.f8230H;
            }
            m11062c();
        }
    }

    public void m11062c() {
        int progress = this.f8254o.getProgress();
        int i = progress < 1 ? 1 : progress;
        C1664h.m10813a(this.f8256q).m10817a((byte) 18, (byte) this.f8239Q, (short) ((this.f8253n.getProgress() * i) * 10), (short) ((int) (this.f8231I * 100.0d)), (byte) (i * 10), (short) ((int) this.f8232J), (float) this.f8233K, (float) this.f8234L);
    }

    public void m11063d() {
        ak.m8083a(this.f8256q.f6507c, (int) C1205R.string.take_photo_tip, (int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
    }

    public void m11064e() {
        this.f8242c.setVisibility(0);
        this.f8258s.setVisibility(0);
    }

    public void m11065f() {
        this.f8242c.setVisibility(8);
        this.f8242c.setVisibility(8);
        if (this.f8257r.isShown()) {
            this.f8257r.setVisibility(8);
        }
    }

    public void m11066g() {
        if (this.f8227E >= 20) {
            this.f8253n.setProgress(this.f8228F);
            this.f8245f.setText(this.f8228F + "s");
            return;
        }
        if (this.f8227E == 0 && this.f8257r.isShown() && m11054j() < 2) {
            ak.m8085a(this.f8256q.f6507c, this.f8256q.f6507c.getString(C1205R.string.takephoto_cloud_adjust), 3000);
        }
        this.f8253n.setProgress(this.f8227E);
        this.f8245f.setText(this.f8227E + "s");
    }

    public void m11067h() {
        m11066g();
        if (this.f8224B >= 1) {
            this.f8254o.setProgress(this.f8229G);
            this.f8247h.setText(this.f8229G + "m/s");
        } else {
            this.f8254o.setProgress(this.f8224B);
            this.f8247h.setText(this.f8224B + "m/s");
        }
        this.f8257r.setVisibility(8);
        this.f8255p.setSwitchState(false);
        this.f8239Q = 0;
        this.f8234L = 0.0d;
        this.f8232J = 0.0d;
        this.f8233K = 0.0d;
        this.f8231I = 0.0d;
        this.f8237O = false;
        this.f8238P = false;
        m11055k();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.fly_action_cancal_btn:
                this.f8238P = false;
                m11065f();
                C1253k.m8598a(this.f8256q.f6507c).m8600a(0);
                this.f8256q.m9589a(C1584h.CANCALFLYACTION);
            case C1205R.id.fly_action_execute_btn:
                if (this.f8237O) {
                    this.f8238P = true;
                    m11057a();
                    return;
                }
                ak.m8083a(this.f8256q.f6507c, (int) C1205R.string.on_next, 3000);
            case C1205R.id.setflyphoto:
                this.f8237O = true;
                if (!this.f8257r.isShown()) {
                    this.f8257r.setVisibility(0);
                }
                if (this.f8258s.isShown()) {
                    this.f8258s.setVisibility(8);
                }
                if (this.f8242c.isShown()) {
                    this.f8242c.setVisibility(8);
                }
            default:
        }
    }
}
