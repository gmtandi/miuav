package com.amap.api.mapcore;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.opengl.GLES10;
import android.os.Build.VERSION;
import android.util.Log;
import com.amap.api.mapcore.util.bj;
import com.amap.api.mapcore.util.ce;
import com.amap.api.mapcore.util.dq;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

class ba implements ah {
    private static int f1649a;
    private boolean f1650A;
    private boolean f1651B;
    private aw f1652C;
    private FloatBuffer f1653D;
    private Object f1654E;
    private boolean f1655F;
    private CopyOnWriteArrayList<BitmapDescriptor> f1656G;
    private boolean f1657H;
    private boolean f1658I;
    private boolean f1659J;
    private int f1660K;
    private int f1661L;
    private boolean f1662M;
    private int f1663N;
    private int f1664O;
    private long f1665P;
    private boolean f1666b;
    private boolean f1667c;
    private boolean f1668d;
    private float f1669e;
    private float f1670f;
    private boolean f1671g;
    private int f1672h;
    private int f1673i;
    private int f1674j;
    private int f1675k;
    private int f1676l;
    private int f1677m;
    private FPoint f1678n;
    private float[] f1679o;
    private int[] f1680p;
    private float f1681q;
    private boolean f1682r;
    private FloatBuffer f1683s;
    private String f1684t;
    private LatLng f1685u;
    private LatLng f1686v;
    private String f1687w;
    private String f1688x;
    private float f1689y;
    private float f1690z;

    static {
        f1649a = 0;
    }

    public ba(MarkerOptions markerOptions, aw awVar) {
        this.f1666b = false;
        this.f1667c = false;
        this.f1668d = false;
        this.f1669e = 0.0f;
        this.f1670f = 0.0f;
        this.f1671g = false;
        this.f1672h = 0;
        this.f1673i = 0;
        this.f1674j = 0;
        this.f1675k = 0;
        this.f1678n = new FPoint();
        this.f1680p = null;
        this.f1681q = 0.0f;
        this.f1682r = false;
        this.f1683s = null;
        this.f1689y = 0.5f;
        this.f1690z = C2020f.f10933c;
        this.f1650A = false;
        this.f1651B = true;
        this.f1655F = false;
        this.f1656G = null;
        this.f1657H = false;
        this.f1658I = false;
        this.f1659J = true;
        this.f1660K = 0;
        this.f1661L = 20;
        this.f1662M = false;
        this.f1665P = 0;
        this.f1652C = awVar;
        this.f1685u = markerOptions.getPosition();
        IPoint iPoint = new IPoint();
        this.f1657H = markerOptions.isGps();
        if (markerOptions.getPosition() != null) {
            if (this.f1657H) {
                try {
                    double[] a = dq.m4051a(markerOptions.getPosition().longitude, markerOptions.getPosition().latitude);
                    this.f1686v = new LatLng(a[1], a[0]);
                    MapProjection.lonlat2Geo(a[0], a[1], iPoint);
                } catch (Throwable th) {
                    ce.m3829a(th, "MarkerDelegateImp", "create");
                    this.f1686v = markerOptions.getPosition();
                }
            } else {
                MapProjection.lonlat2Geo(this.f1685u.longitude, this.f1685u.latitude, iPoint);
            }
        }
        this.f1676l = iPoint.f3714x;
        this.f1677m = iPoint.f3715y;
        this.f1689y = markerOptions.getAnchorU();
        this.f1690z = markerOptions.getAnchorV();
        this.f1672h = markerOptions.getInfoWindowOffsetX();
        this.f1673i = markerOptions.getInfoWindowOffsetY();
        this.f1661L = markerOptions.getPeriod();
        this.f1681q = markerOptions.getZIndex();
        m2884r();
        m2863b(markerOptions.getIcons());
        this.f1651B = markerOptions.isVisible();
        this.f1688x = markerOptions.getSnippet();
        this.f1687w = markerOptions.getTitle();
        this.f1650A = markerOptions.isDraggable();
        this.f1684t = m2874h();
        this.f1655F = markerOptions.isPerspective();
        this.f1671g = markerOptions.isFlat();
    }

    private void m2828N() {
        if (this.f1652C.f1631a != null) {
            this.f1652C.f1631a.m2299f(false);
        }
    }

    private int m2829a(GL10 gl10) {
        int K = this.f1652C.f1631a.m2221K();
        if (K != 0) {
            return K;
        }
        int[] iArr = new int[]{0};
        gl10.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    private void m2830a(float f, float f2, IPoint iPoint) {
        float f3 = (float) ((3.141592653589793d * ((double) this.f1669e)) / 180.0d);
        iPoint.f3714x = (int) ((((double) f) * Math.cos((double) f3)) + (((double) f2) * Math.sin((double) f3)));
        iPoint.f3715y = (int) ((((double) f2) * Math.cos((double) f3)) - (Math.sin((double) f3) * ((double) f)));
    }

    private void m2831a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (i != 0 && floatBuffer != null && floatBuffer2 != null) {
            GLES10.glEnable(3042);
            GLES10.glBlendFunc(1, 771);
            GLES10.glColor4f(C2020f.f10933c, C2020f.f10933c, C2020f.f10933c, C2020f.f10933c);
            GLES10.glEnable(3553);
            GLES10.glEnableClientState(32884);
            GLES10.glEnableClientState(32888);
            GLES10.glBindTexture(3553, i);
            GLES10.glVertexPointer(3, 5126, 0, floatBuffer);
            GLES10.glTexCoordPointer(2, 5126, 0, floatBuffer2);
            GLES10.glDrawArrays(6, 0, 4);
            GLES10.glDisableClientState(32884);
            GLES10.glDisableClientState(32888);
            GLES10.glDisable(3553);
            GLES10.glDisable(3042);
        }
    }

    private void m2832a(ab abVar) {
        float[] a = bj.m3630a(abVar, this.f1671g ? 1 : 0, this.f1678n, this.f1669e, m2844K(), m2845L(), this.f1689y, this.f1690z);
        this.f1679o = (float[]) a.clone();
        if (this.f1683s == null) {
            this.f1683s = bj.m3620a(a);
        } else {
            this.f1683s = bj.m3621a(a, this.f1683s);
        }
        if (this.f1656G != null && this.f1656G.size() > 0) {
            this.f1660K++;
            if (this.f1660K >= this.f1661L * this.f1656G.size()) {
                this.f1660K = 0;
            }
            int i = this.f1660K / this.f1661L;
            if (!this.f1659J) {
                m2828N();
            }
            if (this.f1680p != null && this.f1680p.length > 0) {
                m2831a(this.f1680p[i % this.f1656G.size()], this.f1683s, this.f1653D);
            }
        }
    }

    private static String m2833c(String str) {
        f1649a++;
        return str + f1649a;
    }

    public boolean m2834A() {
        return this.f1671g;
    }

    public int m2835B() {
        return this.f1672h;
    }

    public int m2836C() {
        return this.f1673i;
    }

    public int m2837D() {
        return this.f1674j;
    }

    public int m2838E() {
        return this.f1675k;
    }

    public boolean m2839F() {
        return this.f1662M;
    }

    public float m2840G() {
        return this.f1681q;
    }

    public boolean m2841H() {
        Rect k = this.f1652C.f1631a.m2309k();
        if (this.f1662M || k == null) {
            return true;
        }
        IPoint iPoint = new IPoint();
        if (this.f1678n != null) {
            this.f1652C.f1631a.m2289c().map2Win(this.f1678n.f3693x, this.f1678n.f3694y, iPoint);
        }
        return k.contains(iPoint.f3714x, iPoint.f3715y);
    }

    public IPoint m2842I() {
        IPoint iPoint = new IPoint();
        if (!this.f1662M) {
            return new IPoint(this.f1676l, this.f1677m);
        }
        this.f1652C.f1631a.m2250a(this.f1663N, this.f1664O, iPoint);
        return iPoint;
    }

    public void m2843J() {
        this.f1658I = false;
        if (this.f1680p != null) {
            Arrays.fill(this.f1680p, 0);
        }
    }

    public int m2844K() {
        try {
            return m2846M().getWidth();
        } catch (Throwable th) {
            return 0;
        }
    }

    public int m2845L() {
        try {
            return m2846M().getHeight();
        } catch (Throwable th) {
            return 0;
        }
    }

    public synchronized BitmapDescriptor m2846M() {
        BitmapDescriptor M;
        try {
            if (this.f1656G == null || this.f1656G.size() == 0) {
                m2847a();
                this.f1656G.add(BitmapDescriptorFactory.defaultMarker());
            } else if (this.f1656G.get(0) == null) {
                this.f1656G.clear();
                M = m2846M();
            }
            M = (BitmapDescriptor) this.f1656G.get(0);
        } catch (Throwable th) {
            ce.m3829a(th, "MarkerDelegateImp", "getBitmapDescriptor");
            th.printStackTrace();
            M = null;
        }
        return M;
    }

    synchronized void m2847a() {
        if (this.f1656G == null) {
            this.f1656G = new CopyOnWriteArrayList();
        } else {
            this.f1656G.clear();
        }
    }

    public void m2848a(float f) {
        this.f1670f = f;
        this.f1669e = (((-f) % 360.0f) + 360.0f) % 360.0f;
        if (m2880n()) {
            this.f1652C.m2822e(this);
            this.f1652C.m2820d(this);
        }
        m2828N();
    }

    public void m2849a(float f, float f2) {
        if (this.f1689y != f || this.f1690z != f2) {
            this.f1689y = f;
            this.f1690z = f2;
            if (m2880n()) {
                this.f1652C.m2822e(this);
                this.f1652C.m2820d(this);
            }
            m2828N();
        }
    }

    public void m2850a(int i) {
        if (i <= 1) {
            this.f1661L = 1;
        } else {
            this.f1661L = i;
        }
    }

    public void m2851a(int i, int i2) {
        int i3 = 1;
        this.f1663N = i;
        this.f1664O = i2;
        this.f1662M = true;
        m2884r();
        try {
            ab abVar = this.f1652C.f1631a;
            if (!this.f1671g) {
                i3 = 0;
            }
            this.f1679o = bj.m3630a(abVar, i3, this.f1678n, this.f1669e, m2844K(), m2845L(), this.f1689y, this.f1690z);
        } catch (Throwable th) {
            ce.m3829a(th, "MarkerDelegateImp", "setPositionByPixels");
        }
        m2828N();
        if (m2880n()) {
            m2878l();
        }
    }

    public synchronized void m2852a(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            try {
                if (this.f1656G != null) {
                    this.f1656G.clear();
                    this.f1656G.add(bitmapDescriptor);
                    this.f1658I = false;
                    this.f1666b = false;
                    this.f1680p = null;
                    if (this.f1653D != null) {
                        this.f1653D.clear();
                        this.f1653D = null;
                    }
                    if (m2880n()) {
                        this.f1652C.m2822e(this);
                        this.f1652C.m2820d(this);
                    }
                    m2828N();
                }
            } catch (Throwable th) {
                ce.m3829a(th, "MarkerDelegateImp", "setIcon");
                th.printStackTrace();
            }
        }
    }

    public void m2853a(LatLng latLng) {
        if (latLng == null) {
            ce.m3829a(new AMapException("\u975e\u6cd5\u5750\u6807\u503c latlng is null"), "setPosition", "Marker");
            return;
        }
        this.f1685u = latLng;
        IPoint iPoint = new IPoint();
        if (this.f1657H) {
            try {
                double[] a = dq.m4051a(latLng.longitude, latLng.latitude);
                this.f1686v = new LatLng(a[1], a[0]);
                MapProjection.lonlat2Geo(a[0], a[1], iPoint);
            } catch (Throwable th) {
                this.f1686v = latLng;
            }
        } else {
            MapProjection.lonlat2Geo(latLng.longitude, latLng.latitude, iPoint);
        }
        this.f1676l = iPoint.f3714x;
        this.f1677m = iPoint.f3715y;
        this.f1662M = false;
        m2884r();
        m2828N();
    }

    public void m2854a(IPoint iPoint) {
        this.f1662M = false;
        this.f1676l = iPoint.f3714x;
        this.f1677m = iPoint.f3715y;
        DPoint dPoint = new DPoint();
        MapProjection.geo2LonLat(this.f1676l, this.f1677m, dPoint);
        this.f1685u = new LatLng(dPoint.f3692y, dPoint.f3691x, false);
        this.f1652C.f1631a.m2289c().geo2Map(this.f1676l, this.f1677m, this.f1678n);
    }

    public void m2855a(Object obj) {
        this.f1654E = obj;
    }

    public void m2856a(String str) {
        this.f1687w = str;
        m2828N();
    }

    public synchronized void m2857a(ArrayList<BitmapDescriptor> arrayList) {
        if (arrayList != null) {
            try {
                if (this.f1656G != null) {
                    m2863b((ArrayList) arrayList);
                    this.f1658I = false;
                    this.f1666b = false;
                    if (this.f1653D != null) {
                        this.f1653D.clear();
                        this.f1653D = null;
                    }
                    this.f1680p = null;
                    if (m2880n()) {
                        this.f1652C.m2822e(this);
                        this.f1652C.m2820d(this);
                    }
                    m2828N();
                }
            } catch (Throwable th) {
                ce.m3829a(th, "MarkerDelegateImp", "setIcons");
                th.printStackTrace();
            }
        }
    }

    public void m2858a(GL10 gl10, ab abVar) {
        int i = 0;
        if (this.f1651B && !this.f1682r) {
            if (this.f1685u == null && !this.f1662M) {
                return;
            }
            if (m2846M() != null || this.f1656G != null) {
                int i2;
                int i3;
                BitmapDescriptor bitmapDescriptor;
                if (!this.f1658I) {
                    try {
                        if (this.f1656G != null) {
                            this.f1680p = new int[this.f1656G.size()];
                            i2 = VERSION.SDK_INT >= 12 ? 1 : 0;
                            Iterator it = this.f1656G.iterator();
                            i3 = 0;
                            while (it.hasNext()) {
                                bitmapDescriptor = (BitmapDescriptor) it.next();
                                if (i2 != 0) {
                                    i = this.f1652C.m2805a(bitmapDescriptor);
                                }
                                if (i == 0) {
                                    Bitmap bitmap = bitmapDescriptor.getBitmap();
                                    if (!(bitmap == null || bitmap.isRecycled())) {
                                        i = m2829a(gl10);
                                        if (i2 != 0) {
                                            this.f1652C.m2809a(new be(bitmapDescriptor, i));
                                        }
                                        bj.m3632b(gl10, i, bitmap, false);
                                    }
                                }
                                int i4 = i;
                                this.f1680p[i3] = i4;
                                i3++;
                                i = i4;
                            }
                            if (this.f1656G.size() == 1) {
                                this.f1659J = true;
                            } else {
                                this.f1659J = false;
                            }
                            this.f1658I = true;
                        }
                    } catch (Throwable th) {
                        ce.m3829a(th, "MarkerDelegateImp", "loadtexture");
                        return;
                    }
                }
                try {
                    if (!this.f1666b) {
                        if (this.f1653D == null) {
                            bitmapDescriptor = m2846M();
                            if (bitmapDescriptor != null) {
                                i = bitmapDescriptor.getWidth();
                                i3 = bitmapDescriptor.getHeight();
                                i2 = bitmapDescriptor.getBitmap().getHeight();
                                float width = ((float) i) / ((float) bitmapDescriptor.getBitmap().getWidth());
                                float f = ((float) i3) / ((float) i2);
                                this.f1653D = bj.m3620a(new float[]{0.0f, f, width, f, width, 0.0f, 0.0f, 0.0f});
                            } else {
                                return;
                            }
                        }
                        m2884r();
                        this.f1665P = System.currentTimeMillis();
                        this.f1666b = true;
                    }
                    if (this.f1662M) {
                        abVar.m2249a(this.f1663N, this.f1664O, this.f1678n);
                    }
                    m2832a(abVar);
                    if (this.f1668d && m2880n()) {
                        this.f1652C.m2827j();
                        if (System.currentTimeMillis() - this.f1665P > 1000) {
                            this.f1668d = false;
                        }
                    }
                } catch (Throwable th2) {
                    ce.m3829a(th2, "MarkerDelegateImp", "drawMarker");
                }
            }
        }
    }

    public void m2859a(boolean z) {
        this.f1650A = z;
        m2828N();
    }

    public boolean m2860a(ah ahVar) {
        return equals(ahVar) || ahVar.m2114h().equals(m2874h());
    }

    public void m2861b(float f) {
        this.f1681q = f;
        this.f1652C.m2825h();
    }

    public void m2862b(String str) {
        this.f1688x = str;
        m2828N();
    }

    public synchronized void m2863b(ArrayList<BitmapDescriptor> arrayList) {
        m2847a();
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                BitmapDescriptor bitmapDescriptor = (BitmapDescriptor) it.next();
                if (bitmapDescriptor != null) {
                    this.f1656G.add(bitmapDescriptor);
                }
            }
        }
    }

    public void m2864b(boolean z) {
        this.f1667c = z;
        if (this.f1667c && this.f1662M) {
            this.f1668d = true;
        }
    }

    public boolean m2865b() {
        m2828N();
        this.f1651B = false;
        return this.f1652C != null ? this.f1652C.m2816b((ah) this) : false;
    }

    public void m2866c(boolean z) {
        if (this.f1651B != z) {
            this.f1651B = z;
            if (!z && m2880n()) {
                this.f1652C.m2822e(this);
            }
            m2828N();
        }
    }

    public boolean m2867c() {
        return this.f1659J;
    }

    public Rect m2868d() {
        if (this.f1679o == null) {
            return new Rect(0, 0, 0, 0);
        }
        try {
            Rect rect;
            MapProjection c = this.f1652C.f1631a.m2289c();
            int K = m2844K();
            int L = m2845L();
            IPoint iPoint = new IPoint();
            IPoint iPoint2 = new IPoint();
            c.map2Win(this.f1678n.f3693x, this.f1678n.f3694y, iPoint);
            if (this.f1671g) {
                c.map2Win(this.f1679o[0], this.f1679o[1], iPoint2);
                rect = new Rect(iPoint2.f3714x, iPoint2.f3715y, iPoint2.f3714x, iPoint2.f3715y);
                c.map2Win(this.f1679o[3], this.f1679o[4], iPoint2);
                rect.union(iPoint2.f3714x, iPoint2.f3715y);
                c.map2Win(this.f1679o[6], this.f1679o[7], iPoint2);
                rect.union(iPoint2.f3714x, iPoint2.f3715y);
                c.map2Win(this.f1679o[9], this.f1679o[10], iPoint2);
                rect.union(iPoint2.f3714x, iPoint2.f3715y);
            } else {
                m2830a((-this.f1689y) * ((float) K), (this.f1690z - C2020f.f10933c) * ((float) L), iPoint2);
                rect = new Rect(iPoint.f3714x + iPoint2.f3714x, iPoint.f3715y - iPoint2.f3715y, iPoint.f3714x + iPoint2.f3714x, iPoint.f3715y - iPoint2.f3715y);
                m2830a((-this.f1689y) * ((float) K), this.f1690z * ((float) L), iPoint2);
                rect.union(iPoint.f3714x + iPoint2.f3714x, iPoint.f3715y - iPoint2.f3715y);
                m2830a((C2020f.f10933c - this.f1689y) * ((float) K), this.f1690z * ((float) L), iPoint2);
                rect.union(iPoint.f3714x + iPoint2.f3714x, iPoint.f3715y - iPoint2.f3715y);
                m2830a((C2020f.f10933c - this.f1689y) * ((float) K), (this.f1690z - C2020f.f10933c) * ((float) L), iPoint2);
                rect.union(iPoint.f3714x + iPoint2.f3714x, iPoint.f3715y - iPoint2.f3715y);
            }
            this.f1674j = rect.centerX() - iPoint.f3714x;
            this.f1675k = rect.top - iPoint.f3715y;
            return rect;
        } catch (Throwable th) {
            ce.m3829a(th, "MarkerDelegateImp", "getRect");
            th.printStackTrace();
            return new Rect(0, 0, 0, 0);
        }
    }

    public void m2869d(boolean z) {
        this.f1655F = z;
    }

    public LatLng m2870e() {
        if (!this.f1662M || this.f1678n == null) {
            return this.f1685u;
        }
        DPoint dPoint = new DPoint();
        IPoint iPoint = new IPoint();
        m2884r();
        this.f1652C.f1631a.m2245a(this.f1678n.f3693x, this.f1678n.f3694y, iPoint);
        MapProjection.geo2LonLat(iPoint.f3714x, iPoint.f3715y, dPoint);
        return new LatLng(dPoint.f3692y, dPoint.f3691x);
    }

    public void m2871e(boolean z) {
        this.f1671g = z;
        m2828N();
    }

    public FPoint m2872f() {
        return this.f1678n;
    }

    public LatLng m2873g() {
        if (!this.f1662M) {
            return this.f1657H ? this.f1686v : this.f1685u;
        } else {
            this.f1652C.f1631a.m2289c().win2Map(this.f1663N, this.f1664O, this.f1678n);
            DPoint dPoint = new DPoint();
            this.f1652C.f1631a.m2248a(this.f1663N, this.f1664O, dPoint);
            return new LatLng(dPoint.f3692y, dPoint.f3692y);
        }
    }

    public String m2874h() {
        if (this.f1684t == null) {
            this.f1684t = m2833c("Marker");
        }
        return this.f1684t;
    }

    public String m2875i() {
        return this.f1687w;
    }

    public String m2876j() {
        return this.f1688x;
    }

    public boolean m2877k() {
        return this.f1650A;
    }

    public void m2878l() {
        if (this.f1651B) {
            this.f1652C.m2820d(this);
            m2828N();
        }
    }

    public void m2879m() {
        if (m2880n()) {
            this.f1652C.m2822e(this);
            m2828N();
            this.f1667c = false;
        }
        this.f1668d = false;
    }

    public boolean m2880n() {
        return this.f1667c;
    }

    public boolean m2881o() {
        return this.f1651B;
    }

    public void m2882p() {
        try {
            int i;
            this.f1682r = true;
            m2865b();
            if (this.f1652C != null) {
                this.f1652C.f1631a.m2224N();
                i = 0;
                while (this.f1680p != null && i < this.f1680p.length) {
                    this.f1652C.m2810a(Integer.valueOf(this.f1680p[i]));
                    this.f1652C.m2807a(this.f1680p[i]);
                    i++;
                }
            }
            i = 0;
            while (this.f1656G != null && i < this.f1656G.size()) {
                ((BitmapDescriptor) this.f1656G.get(i)).recycle();
                i++;
            }
        } catch (Throwable th) {
            ce.m3829a(th, "MarkerDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "MarkerDelegateImp destroy");
        }
    }

    public int m2883q() {
        return super.hashCode();
    }

    public boolean m2884r() {
        if (this.f1662M) {
            this.f1652C.f1631a.m2289c().win2Map(this.f1663N, this.f1664O, this.f1678n);
        } else {
            this.f1652C.f1631a.m2289c().geo2Map(this.f1676l, this.f1677m, this.f1678n);
        }
        return true;
    }

    public Object m2885s() {
        return this.f1654E;
    }

    public boolean m2886t() {
        return this.f1655F;
    }

    public float m2887u() {
        return this.f1670f;
    }

    public int m2888v() {
        return this.f1661L;
    }

    public synchronized ArrayList<BitmapDescriptor> m2889w() {
        ArrayList<BitmapDescriptor> arrayList;
        if (this.f1656G == null || this.f1656G.size() <= 0) {
            arrayList = null;
        } else {
            ArrayList<BitmapDescriptor> arrayList2 = new ArrayList();
            Iterator it = this.f1656G.iterator();
            while (it.hasNext()) {
                arrayList2.add((BitmapDescriptor) it.next());
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public boolean m2890x() {
        return this.f1682r;
    }

    public void m2891y() {
        if (this.f1682r) {
            try {
                if (this.f1656G != null) {
                    Iterator it = this.f1656G.iterator();
                    while (it.hasNext()) {
                        ((BitmapDescriptor) it.next()).recycle();
                    }
                    this.f1656G = null;
                }
                if (this.f1653D != null) {
                    this.f1653D.clear();
                    this.f1653D = null;
                }
                if (this.f1683s != null) {
                    this.f1683s.clear();
                    this.f1683s = null;
                }
                this.f1685u = null;
                this.f1654E = null;
                this.f1680p = null;
            } catch (Throwable th) {
                ce.m3829a(th, "MarkerDelegateImp", "realdestroy");
                th.printStackTrace();
                Log.d("destroy erro", "MarkerDelegateImp destroy");
            }
        }
    }

    public void m2892z() {
        this.f1652C.m2817c(this);
    }
}
