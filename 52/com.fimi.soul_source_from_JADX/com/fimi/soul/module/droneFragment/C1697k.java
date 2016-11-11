package com.fimi.soul.module.droneFragment;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.baidu.tts.loopj.AsyncHttpClient;
import com.fimi.kernel.p084e.ak;
import com.fimi.kernel.view.percent.PercentRelativeLayout;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p090b.C1247f;
import com.fimi.soul.biz.p090b.C1255m;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.entity.FlyActionBean;
import com.fimi.soul.utils.C1966f;
import com.fimi.soul.utils.ac;
import com.fimi.soul.utils.ad;
import com.fimi.soul.utils.aj;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.myhorizontalseebar.SeekBar;
import it.p074a.p075a.C2799f;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.module.droneFragment.k */
public class C1697k implements OnClickListener {
    private boolean f8286A;
    private C1433a f8287B;
    private int f8288C;
    private Circle f8289D;
    private C1247f f8290E;
    private Marker f8291F;
    private Marker f8292G;
    private AMap f8293H;
    private Polyline f8294I;
    private TextView f8295a;
    private TextView f8296b;
    private TextView f8297c;
    private TextView f8298d;
    private TextView f8299e;
    private TextView f8300f;
    private TextView f8301g;
    private TextView f8302h;
    private TextView f8303i;
    private SeekBar f8304j;
    private SeekBar f8305k;
    private SeekBar f8306l;
    private SeekBar f8307m;
    private RadioGroup f8308n;
    private Context f8309o;
    private PercentRelativeLayout f8310p;
    private RadioButton f8311q;
    private RadioButton f8312r;
    private Button f8313s;
    private Button f8314t;
    private Button f8315u;
    private PopupWindow f8316v;
    private String[] f8317w;
    private boolean f8318x;
    private boolean f8319y;
    private boolean f8320z;

    public C1697k(View view, C1433a c1433a) {
        this.f8287B = c1433a;
        this.f8293H = c1433a.m9579Z();
        this.f8309o = c1433a.f6507c;
        this.f8290E = C1247f.m8565k();
        this.f8317w = this.f8309o.getResources().getStringArray(C1205R.array.drone_head);
        this.f8301g = (TextView) view.findViewById(C1205R.id.pointheight);
        this.f8302h = (TextView) view.findViewById(C1205R.id.pointsppek);
        this.f8303i = (TextView) view.findViewById(C1205R.id.pointridus);
        this.f8300f = (TextView) view.findViewById(C1205R.id.poianglevalue);
        this.f8299e = (TextView) view.findViewById(C1205R.id.poiangle);
        this.f8307m = (SeekBar) view.findViewById(C1205R.id.angle_seebar_value);
        this.f8307m.setMax(360);
        this.f8307m.setOnSeekBarChangeListener(new C1698l(this));
        this.f8315u = (Button) view.findViewById(C1205R.id.setPoi);
        this.f8315u.setOnClickListener(this);
        this.f8298d = (TextView) view.findViewById(C1205R.id.spend_time);
        this.f8314t = (Button) view.findViewById(C1205R.id.delete_poi);
        this.f8314t.setOnClickListener(this);
        this.f8313s = (Button) view.findViewById(C1205R.id.myspinner);
        this.f8313s.setText(this.f8317w[0]);
        this.f8313s.setOnClickListener(this);
        this.f8308n = (RadioGroup) view.findViewById(C1205R.id.drone_direction);
        this.f8311q = (RadioButton) view.findViewById(C1205R.id.clockwise);
        this.f8312r = (RadioButton) view.findViewById(C1205R.id.anticlockwise);
        this.f8308n.setOnCheckedChangeListener(new C1699m(this));
        this.f8308n.check(C1205R.id.clockwise);
        this.f8310p = (PercentRelativeLayout) view.findViewById(C1205R.id.poi_operaRL);
        this.f8295a = (TextView) view.findViewById(C1205R.id.poi_height_value);
        this.f8296b = (TextView) view.findViewById(C1205R.id.poispeek);
        this.f8297c = (TextView) view.findViewById(C1205R.id.poiridusvalue);
        this.f8304j = (SeekBar) view.findViewById(C1205R.id.height_seebar_value);
        this.f8305k = (SeekBar) view.findViewById(C1205R.id.speek_seebar_value);
        this.f8306l = (SeekBar) view.findViewById(C1205R.id.ridus_seebar_value);
        this.f8306l.setMax(C2799f.f14263a);
        this.f8305k.setMax(10);
        this.f8304j.setMax(Opcodes.ISHL);
        this.f8304j.setOnSeekBarChangeListener(new C1700n(this));
        this.f8305k.setOnSeekBarChangeListener(new C1701o(this));
        this.f8306l.setOnSeekBarChangeListener(new C1702p(this));
        be.m12368b(this.f8309o.getAssets(), this.f8300f, this.f8295a, this.f8296b, this.f8297c);
        be.m12359a(this.f8309o.getAssets(), this.f8302h, this.f8303i, this.f8301g, this.f8311q, this.f8312r, this.f8298d, this.f8299e);
    }

    private Point m11089a(double d) {
        FlyActionBean j = this.f8290E.m8582j();
        this.f8293H = this.f8287B.m9579Z();
        if (j == null || this.f8293H == null) {
            return null;
        }
        float ridus = (this.f8309o.getResources().getDisplayMetrics().density * ((float) j.getRidus())) / this.f8293H.getScalePerPixel();
        Point toScreenLocation = this.f8293H.getProjection().toScreenLocation(j.getLatLng());
        return new Point(((int) (((double) ridus) * Math.sin(d))) + toScreenLocation.x, (-((int) (((double) ridus) * Math.cos(d)))) + toScreenLocation.y);
    }

    private void m11090a(FlyActionBean flyActionBean, AMap aMap, boolean z) {
        LatLng a = z ? m11118a(flyActionBean, (double) flyActionBean.getStart_point_agle()) : m11118a(flyActionBean, (double) (-flyActionBean.getStart_point_agle()));
        if (this.f8291F == null) {
            this.f8291F = aMap.addMarker(new MarkerOptions().position(a).icon(C1255m.m8605a(C1205R.drawable.icn_rotate_point)));
            this.f8291F.setZIndex(10000.0f);
            this.f8291F.setAnchor(0.5f, 0.5f);
            this.f8291F.setDraggable(false);
            this.f8291F.setRotateAngle(m11116p());
        } else {
            this.f8291F.setPosition(a);
            this.f8291F.setRotateAngle(m11116p());
        }
        m11107k();
    }

    private void m11094b(FlyActionBean flyActionBean, AMap aMap, boolean z) {
        LatLng a = z ? m11118a(flyActionBean, (double) (flyActionBean.getStart_point_agle() + 90)) : m11118a(flyActionBean, (double) (270 - flyActionBean.getStart_point_agle()));
        if (this.f8292G == null) {
            this.f8292G = aMap.addMarker(new MarkerOptions().position(a).icon(C1255m.m8605a(C1205R.drawable.icon_fly_direction)));
            this.f8292G.setZIndex(10000.0f);
            if (z) {
                this.f8292G.setRotateAngle(((float) ((-flyActionBean.getStart_point_agle()) - 180)) + aMap.getCameraPosition().bearing);
            } else {
                this.f8292G.setRotateAngle(((float) (flyActionBean.getStart_point_agle() - 180)) + aMap.getCameraPosition().bearing);
            }
            this.f8292G.setAnchor(0.5f, 0.5f);
            return;
        }
        this.f8292G.setPosition(a);
        if (z) {
            this.f8292G.setRotateAngle(((float) ((-flyActionBean.getStart_point_agle()) - 180)) + m11116p());
        } else {
            this.f8292G.setRotateAngle(((float) (flyActionBean.getStart_point_agle() - 180)) + m11116p());
        }
    }

    private void m11107k() {
        FlyActionBean j = C1247f.m8565k().m8582j();
        if (this.f8291F != null && j != null) {
            if (this.f8294I == null) {
                this.f8294I = this.f8293H.addPolyline(new PolylineOptions().add(this.f8291F.getPosition(), j.getLatLng()).color(this.f8287B.f6507c.getResources().getColor(C1205R.color.ridus_color)).width(4.0f));
                return;
            }
            List arrayList = new ArrayList();
            arrayList.add(j.getLatLng());
            arrayList.add(this.f8291F.getPosition());
            this.f8294I.setPoints(arrayList);
        }
    }

    private double m11108l() {
        aj a = ad.m12227a(this.f8287B.m9618u().m10301b(), this.f8287B.m9618u().m10302c());
        LatLng latLng = new LatLng(a.m12250a(), a.m12252b());
        a = ad.m12227a((double) this.f8287B.m9617t().m10361e(), (double) this.f8287B.m9617t().m10360d());
        return ac.m12223c(latLng, new LatLng(a.m12250a(), a.m12252b())).m12254a();
    }

    private void m11111m() {
        if (this.f8316v == null) {
            this.f8313s.setBackgroundResource(C1205R.drawable.bg_fly_combobox_more_up);
            View inflate = LayoutInflater.from(this.f8309o).inflate(C1205R.layout.myspinner_item, null);
            ListView listView = (ListView) inflate.findViewById(C1205R.id.list_head_angle);
            listView.setAdapter(new C1705s(this, this.f8309o, this.f8317w));
            listView.setSelection(1);
            listView.setOnItemClickListener(new C1703q(this));
            this.f8316v = new PopupWindow(listView, this.f8313s.getWidth(), this.f8313s.getHeight() * 4);
            this.f8316v.setContentView(inflate);
            this.f8316v.showAsDropDown(this.f8313s, 0, C1966f.m12466b(this.f8309o, -8.0f));
            this.f8316v.setOutsideTouchable(true);
            this.f8316v.setTouchable(true);
            this.f8316v.setFocusable(true);
            this.f8316v.setBackgroundDrawable(new BitmapDrawable());
            this.f8316v.setOnDismissListener(new C1704r(this));
        } else if (this.f8316v.isShowing()) {
            this.f8316v.dismiss();
            this.f8313s.setBackgroundResource(C1205R.drawable.bg_fly_combobox_more_down);
        } else {
            this.f8313s.setBackgroundResource(C1205R.drawable.bg_fly_combobox_more_up);
            this.f8316v.showAsDropDown(this.f8313s, 0, C1966f.m12466b(this.f8309o, -8.0f));
        }
    }

    private void m11113n() {
        this.f8310p.getWidth();
        FlyActionBean j = this.f8290E.m8582j();
        int i = this.f8309o.getResources().getDisplayMetrics().widthPixels;
        int i2 = this.f8309o.getResources().getDisplayMetrics().heightPixels;
        AMap Z = this.f8287B.m9579Z();
        if (Z != null) {
            Point toScreenLocation = Z.getProjection().toScreenLocation(j.getLatLng());
            if (toScreenLocation.x > i / 2) {
                Z.moveCamera(CameraUpdateFactory.changeLatLng(Z.getProjection().fromScreenLocation(new Point((i / 2) + (toScreenLocation.x - (i / 3)), i2 / 2))));
            }
        }
        m11123b(j);
    }

    private void m11115o() {
        FlyActionBean j = C1247f.m8565k().m8582j();
        if (this.f8289D != null && j != null) {
            int radius = (int) (((this.f8289D.getRadius() * 2.0d) * 3.141592653589793d) / ((double) j.getSpeek()));
            this.f8298d.setText(String.format(this.f8309o.getString(C1205R.string.spend_timereminder), new Object[]{Integer.valueOf(radius)}));
        }
    }

    private synchronized float m11116p() {
        float f = 0.0f;
        synchronized (this) {
            try {
                CameraPosition cameraPosition = this.f8293H.getCameraPosition();
                if (cameraPosition != null) {
                    f = cameraPosition.bearing;
                }
            } catch (Exception e) {
            }
        }
        return f;
    }

    public LatLng m11118a(FlyActionBean flyActionBean, double d) {
        return (flyActionBean == null || this.f8289D == null) ? null : ac.m12217a(flyActionBean.getLatLng(), d, this.f8289D.getRadius());
    }

    public void m11119a() {
        if (!this.f8315u.isShown()) {
            this.f8315u.setVisibility(0);
            ak.m8083a(this.f8309o, (int) C1205R.string.set_poi_tip, (int) AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS);
        }
    }

    public void m11120a(FlyActionBean flyActionBean) {
        if (this.f8313s.getText().toString().equals(this.f8317w[0])) {
            flyActionBean.setYaw_mode(1);
        } else if (this.f8313s.getText().toString().equals(this.f8317w[1])) {
            flyActionBean.setYaw_mode(0);
        } else if (this.f8313s.getText().toString().equals(this.f8317w[2])) {
            flyActionBean.setYaw_mode(2);
        } else if (this.f8313s.getText().toString().equals(this.f8317w[3])) {
            flyActionBean.setYaw_mode(3);
        } else if (this.f8313s.getText().toString().equals(this.f8317w[4])) {
            flyActionBean.setYaw_mode(4);
        }
        switch (this.f8308n.getCheckedRadioButtonId()) {
            case C1205R.id.clockwise:
                flyActionBean.setPara1(3);
                break;
            case C1205R.id.anticlockwise:
                flyActionBean.setPara1(1);
                break;
        }
        m11127f();
        m11115o();
    }

    public void m11121a(boolean z) {
        FlyActionBean j = this.f8290E.m8582j();
        this.f8293H = this.f8287B.m9579Z();
        if (j != null && this.f8293H != null) {
            switch (this.f8308n.getCheckedRadioButtonId()) {
                case C1205R.id.clockwise:
                    if (!z) {
                        m11090a(j, this.f8293H, true);
                    }
                    m11094b(j, this.f8293H, true);
                case C1205R.id.anticlockwise:
                    if (!z) {
                        m11090a(j, this.f8293H, false);
                    }
                    m11094b(j, this.f8293H, false);
                default:
            }
        }
    }

    public void m11122b() {
        if (this.f8310p.isShown()) {
            this.f8310p.setVisibility(8);
        }
    }

    public void m11123b(FlyActionBean flyActionBean) {
        this.f8293H = this.f8287B.m9579Z();
        if (this.f8293H != null && flyActionBean != null) {
            if (this.f8289D == null) {
                this.f8289D = this.f8293H.addCircle(new CircleOptions().radius((double) flyActionBean.getRidus()).strokeWidth(4.0f).strokeColor(this.f8309o.getResources().getColor(C1205R.color.ridus_color)).center(this.f8290E.m8582j().getLatLng()));
                this.f8290E.m8568a(this.f8289D);
                return;
            }
            this.f8289D.setRadius((double) flyActionBean.getRidus());
        }
    }

    public void m11124c() {
        if (!this.f8310p.isShown()) {
            this.f8310p.setVisibility(0);
        }
    }

    public void m11125d() {
        m11122b();
        if (this.f8316v != null && this.f8316v.isShowing()) {
            this.f8316v.dismiss();
            this.f8313s.setBackgroundResource(C1205R.drawable.bg_fly_combobox_more_down);
        }
        if (this.f8289D != null) {
            this.f8289D.remove();
            this.f8289D = null;
        }
        if (this.f8291F != null) {
            this.f8291F.destroy();
            this.f8291F = null;
        }
        if (this.f8292G != null) {
            this.f8292G.destroy();
            this.f8292G = null;
        }
        if (this.f8294I != null) {
            this.f8294I.remove();
            this.f8294I = null;
        }
    }

    public void m11126e() {
        if (this.f8315u.isShown()) {
            this.f8315u.setVisibility(8);
        }
    }

    public void m11127f() {
        if (this.f8311q.isChecked()) {
            this.f8311q.setTextColor(this.f8309o.getResources().getColor(C1205R.color.color_height));
            this.f8312r.setTextColor(this.f8309o.getResources().getColor(C1205R.color.black));
            return;
        }
        this.f8312r.setTextColor(this.f8309o.getResources().getColor(C1205R.color.color_height));
        this.f8311q.setTextColor(this.f8309o.getResources().getColor(C1205R.color.black));
    }

    public void m11128g() {
        m11124c();
        m11113n();
        m11129h();
    }

    public void m11129h() {
        FlyActionBean j = C1247f.m8565k().m8582j();
        if (j != null) {
            double l = m11108l();
            if (500.0d - l < 5.0d) {
                ak.m8083a(this.f8309o, (int) C1205R.string.cant_setPoi, 3000);
                return;
            }
            this.f8304j.setProgress(j.getHeight());
            this.f8305k.setProgress(j.getSpeek());
            this.f8306l.setProgress(j.getRidus());
            this.f8307m.setProgress(j.getStart_point_agle());
            this.f8306l.setMax((int) (500.0d - l));
            this.f8295a.setText(j.getHeight() + "m");
            this.f8296b.setText(j.getSpeek() + "m/s");
            this.f8297c.setText(j.getRidus() + "m");
            this.f8300f.setText(j.getStart_point_agle() + "deg");
            if (j.getPara1() == 0 || j.getPara1() == 1) {
                this.f8308n.check(this.f8312r.getId());
            } else if (j.getPara1() == 2 || j.getPara1() == 3) {
                this.f8308n.check(this.f8311q.getId());
            }
            m11120a(j);
            m11121a(false);
            m11107k();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void m11130i() {
        /*
        r6 = this;
        r0 = 0;
        monitor-enter(r6);
        r2 = com.fimi.soul.biz.p090b.C1247f.m8565k();	 Catch:{ all -> 0x007d }
        r2 = r2.m8582j();	 Catch:{ all -> 0x007d }
        r3 = r6.f8289D;	 Catch:{ all -> 0x007d }
        if (r3 == 0) goto L_0x005f;
    L_0x000f:
        r3 = r6.f8291F;	 Catch:{ all -> 0x007d }
        if (r3 == 0) goto L_0x005f;
    L_0x0013:
        if (r2 == 0) goto L_0x005f;
    L_0x0015:
        r3 = r2.getLatLng();	 Catch:{ all -> 0x007d }
        r4 = r6.f8291F;	 Catch:{ all -> 0x007d }
        r4 = r4.getPosition();	 Catch:{ all -> 0x007d }
        r3 = com.fimi.soul.utils.ac.m12223c(r3, r4);	 Catch:{ all -> 0x007d }
        r4 = r3.m12254a();	 Catch:{ all -> 0x007d }
        r3 = (int) r4;	 Catch:{ all -> 0x007d }
        r2.setRidus(r3);	 Catch:{ all -> 0x007d }
        r6.m11123b(r2);	 Catch:{ all -> 0x007d }
        r4 = 0;
        r3 = r6.m11118a(r2, r4);	 Catch:{ all -> 0x007d }
        r4 = r6.f8308n;	 Catch:{ all -> 0x007d }
        r4 = r4.getCheckedRadioButtonId();	 Catch:{ all -> 0x007d }
        switch(r4) {
            case 2131427709: goto L_0x0061;
            case 2131427710: goto L_0x006c;
            default: goto L_0x003d;
        };	 Catch:{ all -> 0x007d }
    L_0x003d:
        r3 = (int) r0;	 Catch:{ all -> 0x007d }
        r3 = (short) r3;	 Catch:{ all -> 0x007d }
        r2.setStart_point_agle(r3);	 Catch:{ all -> 0x007d }
        r2 = r6.f8300f;	 Catch:{ all -> 0x007d }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x007d }
        r3.<init>();	 Catch:{ all -> 0x007d }
        r0 = (int) r0;	 Catch:{ all -> 0x007d }
        r0 = r3.append(r0);	 Catch:{ all -> 0x007d }
        r1 = "deg";
        r0 = r0.append(r1);	 Catch:{ all -> 0x007d }
        r0 = r0.toString();	 Catch:{ all -> 0x007d }
        r2.setText(r0);	 Catch:{ all -> 0x007d }
        r0 = 0;
        r6.m11121a(r0);	 Catch:{ all -> 0x007d }
    L_0x005f:
        monitor-exit(r6);
        return;
    L_0x0061:
        r0 = r6.f8291F;	 Catch:{ all -> 0x007d }
        r0 = r0.getPosition();	 Catch:{ all -> 0x007d }
        r0 = com.fimi.soul.utils.ac.m12224d(r3, r0);	 Catch:{ all -> 0x007d }
        goto L_0x003d;
    L_0x006c:
        r0 = 4645040803167600640; // 0x4076800000000000 float:0.0 double:360.0;
        r4 = r6.f8291F;	 Catch:{ all -> 0x007d }
        r4 = r4.getPosition();	 Catch:{ all -> 0x007d }
        r4 = com.fimi.soul.utils.ac.m12224d(r3, r4);	 Catch:{ all -> 0x007d }
        r0 = r0 - r4;
        goto L_0x003d;
    L_0x007d:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fimi.soul.module.droneFragment.k.i():void");
    }

    public void m11131j() {
        if (this.f8316v != null && this.f8316v.isShowing()) {
            this.f8316v.dismiss();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.myspinner:
                m11111m();
            case C1205R.id.delete_poi:
                m11125d();
                this.f8287B.m9589a(C1584h.CLEARDATA);
                m11119a();
            case C1205R.id.setPoi:
                this.f8287B.m9589a(C1584h.CREATEPOI);
                this.f8315u.setVisibility(8);
            default:
        }
    }
}
