package com.amap.api.mapcore;

import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.tencent.open.yyb.TitleBar;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

class bc implements ai {
    float f1704a;
    float f1705b;
    float f1706c;
    float f1707d;
    float f1708e;
    float f1709f;
    float f1710g;
    float f1711h;
    float[] f1712i;
    private ab f1713j;
    private float f1714k;
    private int f1715l;
    private int f1716m;
    private float f1717n;
    private boolean f1718o;
    private String f1719p;
    private CopyOnWriteArrayList<IPoint> f1720q;
    private int f1721r;
    private boolean f1722s;
    private LatLngBounds f1723t;

    public bc(ab abVar) {
        this.f1714k = TitleBar.SHAREBTN_RIGHT_MARGIN;
        this.f1715l = ViewCompat.MEASURED_STATE_MASK;
        this.f1716m = ViewCompat.MEASURED_STATE_MASK;
        this.f1717n = 0.0f;
        this.f1718o = true;
        this.f1720q = new CopyOnWriteArrayList();
        this.f1721r = 0;
        this.f1722s = false;
        this.f1723t = null;
        this.f1713j = abVar;
        try {
            this.f1719p = m2924c();
        } catch (Throwable e) {
            ce.m3829a(e, "NavigateArrowDelegateImp", "create");
            e.printStackTrace();
        }
    }

    private List<LatLng> m2912n() {
        if (this.f1720q == null) {
            return null;
        }
        List<LatLng> arrayList = new ArrayList();
        Iterator it = this.f1720q.iterator();
        while (it.hasNext()) {
            IPoint iPoint = (IPoint) it.next();
            if (iPoint != null) {
                DPoint dPoint = new DPoint();
                this.f1713j.m2283b(iPoint.f3714x, iPoint.f3715y, dPoint);
                arrayList.add(new LatLng(dPoint.f3692y, dPoint.f3691x));
            }
        }
        return arrayList;
    }

    public void m2913a(float f) {
        this.f1717n = f;
        this.f1713j.m2223M();
        this.f1713j.m2299f(false);
    }

    public void m2914a(int i) {
        this.f1715l = i;
        this.f1704a = ((float) Color.alpha(i)) / 255.0f;
        this.f1705b = ((float) Color.red(i)) / 255.0f;
        this.f1706c = ((float) Color.green(i)) / 255.0f;
        this.f1707d = ((float) Color.blue(i)) / 255.0f;
        this.f1713j.m2299f(false);
    }

    public void m2915a(List<LatLng> list) {
        m2923b((List) list);
    }

    public void m2916a(GL10 gl10) {
        if (this.f1720q != null && this.f1720q.size() != 0 && this.f1714k > 0.0f) {
            if (this.f1721r == 0) {
                m2928g();
            }
            if (this.f1712i != null && this.f1721r > 0) {
                float mapLenWithWin = this.f1713j.m2289c().getMapLenWithWin((int) this.f1714k);
                this.f1713j.m2289c().getMapLenWithWin(1);
                AMapNativeRenderer.nativeDrawLineByTextureID(this.f1712i, this.f1712i.length, mapLenWithWin, this.f1713j.m2280b(), this.f1705b, this.f1706c, this.f1707d, this.f1704a, 0.0f, false, true, true);
            }
            this.f1722s = true;
        }
    }

    public void m2917a(boolean z) {
        this.f1718o = z;
        this.f1713j.m2299f(false);
    }

    public boolean m2918a() {
        if (this.f1723t == null) {
            return false;
        }
        LatLngBounds H = this.f1713j.m2218H();
        return H == null ? true : H.contains(this.f1723t) || this.f1723t.intersects(H);
    }

    public boolean m2919a(aj ajVar) {
        return equals(ajVar) || ajVar.m2574c().equals(m2924c());
    }

    public void m2920b() {
        this.f1713j.m2279a(m2924c());
        this.f1713j.m2299f(false);
    }

    public void m2921b(float f) {
        this.f1714k = f;
        this.f1713j.m2299f(false);
    }

    public void m2922b(int i) {
        this.f1716m = i;
        this.f1708e = ((float) Color.alpha(i)) / 255.0f;
        this.f1709f = ((float) Color.red(i)) / 255.0f;
        this.f1710g = ((float) Color.green(i)) / 255.0f;
        this.f1711h = ((float) Color.blue(i)) / 255.0f;
        this.f1713j.m2299f(false);
    }

    void m2923b(List<LatLng> list) {
        Builder builder = LatLngBounds.builder();
        this.f1720q.clear();
        if (list != null) {
            Object obj = null;
            for (LatLng latLng : list) {
                if (!(latLng == null || latLng.equals(r1))) {
                    IPoint iPoint = new IPoint();
                    this.f1713j.m2243a(latLng.latitude, latLng.longitude, iPoint);
                    this.f1720q.add(iPoint);
                    builder.include(latLng);
                    obj = latLng;
                }
            }
        }
        this.f1723t = builder.build();
        this.f1721r = 0;
        this.f1713j.m2299f(false);
    }

    public String m2924c() {
        if (this.f1719p == null) {
            this.f1719p = C0410w.m4243a("NavigateArrow");
        }
        return this.f1719p;
    }

    public float m2925d() {
        return this.f1717n;
    }

    public boolean m2926e() {
        return this.f1718o;
    }

    public int m2927f() {
        return super.hashCode();
    }

    public void m2928g() {
        this.f1722s = false;
        FPoint fPoint = new FPoint();
        this.f1712i = new float[(this.f1720q.size() * 3)];
        Iterator it = this.f1720q.iterator();
        int i = 0;
        while (it.hasNext()) {
            IPoint iPoint = (IPoint) it.next();
            this.f1713j.m2284b(iPoint.f3715y, iPoint.f3714x, fPoint);
            this.f1712i[i * 3] = fPoint.f3693x;
            this.f1712i[(i * 3) + 1] = fPoint.f3694y;
            this.f1712i[(i * 3) + 2] = 0.0f;
            i++;
        }
        this.f1721r = this.f1720q.size();
    }

    public float m2929h() {
        return this.f1714k;
    }

    public int m2930i() {
        return this.f1715l;
    }

    public void m2931j() {
        try {
            if (this.f1712i != null) {
                this.f1712i = null;
            }
        } catch (Throwable th) {
            ce.m3829a(th, "NavigateArrowDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "NavigateArrowDelegateImp destroy");
        }
    }

    public boolean m2932k() {
        return this.f1722s;
    }

    public int m2933l() {
        return this.f1716m;
    }

    public List<LatLng> m2934m() {
        return m2912n();
    }
}
