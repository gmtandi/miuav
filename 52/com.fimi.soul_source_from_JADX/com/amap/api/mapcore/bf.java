package com.amap.api.mapcore;

import android.graphics.Rect;
import android.util.Log;
import com.amap.api.mapcore.util.az;
import com.amap.api.mapcore.util.bi;
import com.amap.api.mapcore.util.bj;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.services.poisearch.PoiSearch.SearchBound;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import com.tencent.open.yyb.TitleBar;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

class bf implements ak {
    private static float f1726s;
    private ab f1727a;
    private float f1728b;
    private boolean f1729c;
    private String f1730d;
    private float f1731e;
    private int f1732f;
    private int f1733g;
    private List<LatLng> f1734h;
    private CopyOnWriteArrayList<IPoint> f1735i;
    private List<FPoint> f1736j;
    private FloatBuffer f1737k;
    private FloatBuffer f1738l;
    private int f1739m;
    private int f1740n;
    private LatLngBounds f1741o;
    private boolean f1742p;
    private float f1743q;
    private Object f1744r;

    static {
        f1726s = 1.0E10f;
    }

    public bf(ab abVar) {
        this.f1728b = 0.0f;
        this.f1729c = true;
        this.f1735i = new CopyOnWriteArrayList();
        this.f1736j = new ArrayList();
        this.f1739m = 0;
        this.f1740n = 0;
        this.f1741o = null;
        this.f1742p = false;
        this.f1743q = 0.0f;
        this.f1744r = new Object();
        this.f1727a = abVar;
        try {
            this.f1730d = m2963c();
        } catch (Throwable e) {
            ce.m3829a(e, "PolygonDelegateImp", "create");
            e.printStackTrace();
        }
    }

    private boolean m2946a(FPoint fPoint, FPoint fPoint2) {
        return Math.abs(fPoint2.f3693x - fPoint.f3693x) >= this.f1743q || Math.abs(fPoint2.f3694y - fPoint.f3694y) >= this.f1743q;
    }

    static FPoint[] m2947a(FPoint[] fPointArr) {
        int i = 0;
        int length = fPointArr.length;
        float[] fArr = new float[(length * 2)];
        for (int i2 = 0; i2 < length; i2++) {
            fArr[i2 * 2] = fPointArr[i2].f3693x * f1726s;
            fArr[(i2 * 2) + 1] = fPointArr[i2].f3694y * f1726s;
        }
        bi a = new az().m3503a(fArr);
        length = a.f2202b;
        FPoint[] fPointArr2 = new FPoint[length];
        while (i < length) {
            fPointArr2[i] = new FPoint();
            fPointArr2[i].f3693x = fArr[a.m3593a(i) * 2] / f1726s;
            fPointArr2[i].f3694y = fArr[(a.m3593a(i) * 2) + 1] / f1726s;
            i++;
        }
        return fPointArr2;
    }

    private void m2948c(List<FPoint> list) {
        int i = 0;
        m2950o();
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        if (size >= 2) {
            FPoint fPoint = (FPoint) list.get(0);
            arrayList.add(fPoint);
            int i2 = 1;
            FPoint fPoint2 = fPoint;
            while (i2 < size - 1) {
                fPoint = (FPoint) list.get(i2);
                if (m2946a(fPoint2, fPoint)) {
                    arrayList.add(fPoint);
                } else {
                    fPoint = fPoint2;
                }
                i2++;
                fPoint2 = fPoint;
            }
            arrayList.add(list.get(size - 1));
            float[] fArr = new float[(arrayList.size() * 3)];
            FPoint[] fPointArr = new FPoint[arrayList.size()];
            Iterator it = arrayList.iterator();
            int i3 = 0;
            while (it.hasNext()) {
                fPoint = (FPoint) it.next();
                fArr[i3 * 3] = fPoint.f3693x;
                fArr[(i3 * 3) + 1] = fPoint.f3694y;
                fArr[(i3 * 3) + 2] = 0.0f;
                fPointArr[i3] = fPoint;
                i3++;
            }
            FPoint[] a = m2947a(fPointArr);
            if (a.length == 0) {
                if (f1726s == 1.0E10f) {
                    f1726s = 1.0E8f;
                } else {
                    f1726s = 1.0E10f;
                }
                a = m2947a(fPointArr);
            }
            float[] fArr2 = new float[(a.length * 3)];
            int length = a.length;
            i3 = 0;
            while (i < length) {
                FPoint fPoint3 = a[i];
                fArr2[i3 * 3] = fPoint3.f3693x;
                fArr2[(i3 * 3) + 1] = fPoint3.f3694y;
                fArr2[(i3 * 3) + 2] = 0.0f;
                i3++;
                i++;
            }
            this.f1739m = fPointArr.length;
            this.f1740n = a.length;
            this.f1737k = bj.m3620a(fArr);
            this.f1738l = bj.m3620a(fArr2);
        }
    }

    private boolean m2949n() {
        float F = this.f1727a.m2217F();
        m2950o();
        if (F <= TitleBar.SHAREBTN_RIGHT_MARGIN) {
            return false;
        }
        try {
            if (this.f1727a == null) {
                return false;
            }
            Rect rect = new Rect(-100, -100, this.f1727a.m2311l() + 100, this.f1727a.m2313m() + 100);
            LatLng latLng = this.f1741o.northeast;
            LatLng latLng2 = this.f1741o.southwest;
            IPoint iPoint = new IPoint();
            this.f1727a.m2281b(latLng.latitude, latLng2.longitude, iPoint);
            IPoint iPoint2 = new IPoint();
            this.f1727a.m2281b(latLng.latitude, latLng.longitude, iPoint2);
            IPoint iPoint3 = new IPoint();
            this.f1727a.m2281b(latLng2.latitude, latLng.longitude, iPoint3);
            IPoint iPoint4 = new IPoint();
            this.f1727a.m2281b(latLng2.latitude, latLng2.longitude, iPoint4);
            return (rect.contains(iPoint.f3714x, iPoint.f3715y) && rect.contains(iPoint2.f3714x, iPoint2.f3715y) && rect.contains(iPoint3.f3714x, iPoint3.f3715y) && rect.contains(iPoint4.f3714x, iPoint4.f3715y)) ? false : true;
        } catch (Throwable th) {
            return false;
        }
    }

    private void m2950o() {
        float F = this.f1727a.m2217F();
        if (this.f1735i.size() <= FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS || F > 12.0f) {
            this.f1743q = this.f1727a.m2289c().getMapLenWithWin(10);
            return;
        }
        F = (F / 2.0f) + (this.f1731e / 2.0f);
        if (F > 200.0f) {
            F = 200.0f;
        }
        this.f1743q = this.f1727a.m2289c().getMapLenWithWin((int) F);
    }

    public void m2951a(float f) {
        this.f1728b = f;
        this.f1727a.m2223M();
        this.f1727a.m2299f(false);
    }

    public void m2952a(int i) {
        this.f1732f = i;
        this.f1727a.m2299f(false);
    }

    public void m2953a(List<LatLng> list) {
        synchronized (this.f1744r) {
            this.f1734h = list;
            m2962b((List) list);
            m2967g();
            this.f1727a.m2299f(false);
        }
    }

    public void m2954a(GL10 gl10) {
        if (this.f1735i != null && this.f1735i.size() != 0) {
            if (this.f1737k == null || this.f1738l == null || this.f1739m == 0 || this.f1740n == 0) {
                m2967g();
            }
            List list = this.f1736j;
            if (m2949n()) {
                synchronized (this.f1744r) {
                    list = bj.m3622a(this.f1727a, this.f1736j, true);
                }
            }
            if (list.size() > 2) {
                m2948c(list);
                if (this.f1737k != null && this.f1738l != null && this.f1739m > 0 && this.f1740n > 0) {
                    C0333u.m3342a(gl10, this.f1732f, this.f1733g, this.f1737k, this.f1731e, this.f1738l, this.f1739m, this.f1740n);
                }
            }
            this.f1742p = true;
        }
    }

    public void m2955a(boolean z) {
        this.f1729c = z;
        this.f1727a.m2299f(false);
    }

    public boolean m2956a() {
        if (this.f1741o == null) {
            return false;
        }
        LatLngBounds H = this.f1727a.m2218H();
        return H == null ? true : this.f1741o.contains(H) || this.f1741o.intersects(H);
    }

    public boolean m2957a(aj ajVar) {
        return equals(ajVar) || ajVar.m2574c().equals(m2963c());
    }

    public boolean m2958a(LatLng latLng) {
        try {
            return bj.m3628a(latLng, m2972l());
        } catch (Throwable th) {
            ce.m3829a(th, "PolygonDelegateImp", "contains");
            th.printStackTrace();
            return false;
        }
    }

    public void m2959b() {
        this.f1727a.m2279a(m2963c());
        this.f1727a.m2299f(false);
    }

    public void m2960b(float f) {
        this.f1731e = f;
        this.f1727a.m2299f(false);
    }

    public void m2961b(int i) {
        this.f1733g = i;
        this.f1727a.m2299f(false);
    }

    void m2962b(List<LatLng> list) {
        Builder builder = LatLngBounds.builder();
        this.f1735i.clear();
        if (list != null) {
            Object obj = null;
            for (LatLng latLng : list) {
                if (!latLng.equals(obj)) {
                    IPoint iPoint = new IPoint();
                    this.f1727a.m2243a(latLng.latitude, latLng.longitude, iPoint);
                    this.f1735i.add(iPoint);
                    builder.include(latLng);
                    obj = latLng;
                }
            }
            int size = this.f1735i.size();
            if (size > 1) {
                IPoint iPoint2 = (IPoint) this.f1735i.get(0);
                IPoint iPoint3 = (IPoint) this.f1735i.get(size - 1);
                if (iPoint2.f3714x == iPoint3.f3714x && iPoint2.f3715y == iPoint3.f3715y) {
                    this.f1735i.remove(size - 1);
                }
            }
        }
        this.f1741o = builder.build();
        if (this.f1737k != null) {
            this.f1737k.clear();
        }
        if (this.f1738l != null) {
            this.f1738l.clear();
        }
        this.f1739m = 0;
        this.f1740n = 0;
        this.f1727a.m2299f(false);
    }

    public String m2963c() {
        if (this.f1730d == null) {
            this.f1730d = C0410w.m4243a(SearchBound.POLYGON_SHAPE);
        }
        return this.f1730d;
    }

    public float m2964d() {
        return this.f1728b;
    }

    public boolean m2965e() {
        return this.f1729c;
    }

    public int m2966f() {
        return super.hashCode();
    }

    public void m2967g() {
        synchronized (this.f1744r) {
            this.f1736j.clear();
            this.f1742p = false;
            Iterator it = this.f1735i.iterator();
            while (it.hasNext()) {
                IPoint iPoint = (IPoint) it.next();
                FPoint fPoint = new FPoint();
                this.f1727a.m2284b(iPoint.f3715y, iPoint.f3714x, fPoint);
                this.f1736j.add(fPoint);
            }
            m2950o();
        }
    }

    public float m2968h() {
        return this.f1731e;
    }

    public int m2969i() {
        return this.f1732f;
    }

    public void m2970j() {
        try {
            if (this.f1737k != null) {
                this.f1737k.clear();
                this.f1737k = null;
            }
            if (this.f1738l != null) {
                this.f1738l = null;
            }
        } catch (Throwable th) {
            ce.m3829a(th, "PolygonDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "PolygonDelegateImp destroy");
        }
    }

    public boolean m2971k() {
        return this.f1742p;
    }

    public List<LatLng> m2972l() {
        return this.f1734h;
    }

    public int m2973m() {
        return this.f1733g;
    }
}
