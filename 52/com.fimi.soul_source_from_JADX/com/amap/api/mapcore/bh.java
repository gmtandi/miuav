package com.amap.api.mapcore;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;
import com.amap.api.mapcore.util.bj;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

class bh implements ah {
    private boolean f1432a;
    private int f1433b;
    private int f1434c;
    private FloatBuffer f1435d;
    private String f1436e;
    private FPoint f1437f;
    private BitmapDescriptor f1438g;
    private boolean f1439h;
    private FloatBuffer f1440i;
    private Object f1441j;
    private int f1442k;
    private ab f1443l;
    private MapProjection f1444m;
    private float f1445n;
    private float f1446o;
    private boolean f1447p;
    private boolean f1448q;
    private boolean f1449r;
    private int f1450s;

    public bh(MarkerOptions markerOptions, ab abVar) {
        this.f1432a = false;
        this.f1433b = 0;
        this.f1434c = 0;
        this.f1435d = null;
        this.f1439h = true;
        this.f1443l = null;
        this.f1444m = null;
        this.f1445n = 0.5f;
        this.f1446o = C2020f.f10933c;
        this.f1448q = false;
        this.f1449r = true;
        this.f1450s = 20;
        this.f1443l = abVar;
        this.f1444m = abVar.m2289c();
        m2136b(markerOptions.getIcon());
        this.f1433b = markerOptions.getInfoWindowOffsetX();
        this.f1434c = markerOptions.getInfoWindowOffsetY();
        this.f1439h = markerOptions.isVisible();
        this.f1436e = m2179h();
        m2189r();
    }

    private void m2133N() {
        if (this.f1443l != null) {
            this.f1443l.m2299f(false);
        }
    }

    private void m2134a(GL10 gl10, int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (floatBuffer != null && floatBuffer2 != null) {
            gl10.glEnable(3042);
            gl10.glBlendFunc(1, 771);
            gl10.glColor4f(C2020f.f10933c, C2020f.f10933c, C2020f.f10933c, C2020f.f10933c);
            gl10.glEnable(3553);
            gl10.glEnableClientState(32884);
            gl10.glEnableClientState(32888);
            gl10.glBindTexture(3553, i);
            gl10.glVertexPointer(3, 5126, 0, floatBuffer);
            gl10.glTexCoordPointer(2, 5126, 0, floatBuffer2);
            gl10.glDrawArrays(6, 0, 4);
            gl10.glDisableClientState(32884);
            gl10.glDisableClientState(32888);
            gl10.glDisable(3553);
            gl10.glDisable(3042);
        }
    }

    private int m2135b(GL10 gl10) {
        int K = this.f1443l.m2221K();
        if (K != 0) {
            return K;
        }
        int[] iArr = new int[]{0};
        gl10.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    private void m2136b(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            this.f1442k = 0;
            this.f1438g = bitmapDescriptor;
        }
    }

    public boolean m2137A() {
        return false;
    }

    public int m2138B() {
        return this.f1433b;
    }

    public int m2139C() {
        return this.f1434c;
    }

    public int m2140D() {
        return 0;
    }

    public int m2141E() {
        return 0;
    }

    public boolean m2142F() {
        return false;
    }

    public float m2143G() {
        return 0.0f;
    }

    public boolean m2144H() {
        return false;
    }

    public IPoint m2145I() {
        return null;
    }

    public void m2146J() {
        this.f1448q = false;
        this.f1442k = 0;
    }

    public int m2147K() {
        try {
            return m2149M().getWidth();
        } catch (Throwable th) {
            return 0;
        }
    }

    public int m2148L() {
        try {
            return m2149M().getHeight();
        } catch (Throwable th) {
            return 0;
        }
    }

    public BitmapDescriptor m2149M() {
        return this.f1438g;
    }

    public void m2150a() {
    }

    public void m2151a(float f) {
    }

    public void m2152a(float f, float f2) {
        if (this.f1445n != f || this.f1446o != f2) {
            this.f1445n = f;
            this.f1446o = f2;
        }
    }

    public void m2153a(int i) {
        if (i <= 1) {
            this.f1450s = 1;
        } else {
            this.f1450s = i;
        }
    }

    public void m2154a(int i, int i2) {
    }

    public void m2155a(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            this.f1438g = bitmapDescriptor;
            this.f1448q = false;
            if (this.f1440i != null) {
                this.f1440i.clear();
                this.f1440i = null;
            }
            m2133N();
        }
    }

    public void m2156a(LatLng latLng) {
    }

    public void m2157a(FPoint fPoint) {
        if (fPoint == null || !fPoint.equals(this.f1437f)) {
            this.f1437f = fPoint;
        }
    }

    public void m2158a(IPoint iPoint) {
    }

    public void m2159a(Object obj) {
        this.f1441j = obj;
    }

    public void m2160a(String str) {
    }

    public void m2161a(ArrayList<BitmapDescriptor> arrayList) {
    }

    public void m2162a(GL10 gl10) {
        if (this.f1439h && this.f1437f != null && m2149M() != null) {
            if (!this.f1448q) {
                try {
                    if (this.f1442k != 0) {
                        gl10.glDeleteTextures(1, new int[]{this.f1442k}, 0);
                        this.f1443l.m2298f(this.f1442k);
                    }
                    this.f1442k = m2135b(gl10);
                    if (this.f1438g != null) {
                        Bitmap bitmap = this.f1438g.getBitmap();
                        if (!(bitmap == null || bitmap.isRecycled())) {
                            bj.m3632b(gl10, this.f1442k, bitmap, false);
                        }
                        this.f1448q = true;
                    }
                } catch (Throwable th) {
                    ce.m3829a(th, "PopupOverlay", "drawMarker");
                    th.printStackTrace();
                    return;
                }
            }
            if (m2189r()) {
                gl10.glLoadIdentity();
                gl10.glViewport(0, 0, this.f1443l.m2311l(), this.f1443l.m2313m());
                gl10.glMatrixMode(5889);
                gl10.glLoadIdentity();
                gl10.glOrthof(0.0f, (float) this.f1443l.m2311l(), 0.0f, (float) this.f1443l.m2313m(), C2020f.f10933c, GroundOverlayOptions.NO_DIMENSION);
                m2134a(gl10, this.f1442k, this.f1435d, this.f1440i);
                if (this.f1447p) {
                    m2150a();
                    this.f1447p = false;
                }
            }
        }
    }

    public void m2163a(GL10 gl10, ab abVar) {
    }

    public void m2164a(boolean z) {
    }

    public boolean m2165a(ah ahVar) {
        return equals(ahVar) || ahVar.m2114h().equals(m2179h());
    }

    public void m2166b(float f) {
    }

    public void m2167b(int i, int i2) {
        this.f1433b = i;
        this.f1434c = i2;
    }

    public void m2168b(String str) {
    }

    public void m2169b(boolean z) {
    }

    public boolean m2170b() {
        m2133N();
        if (this.f1442k != 0) {
            this.f1443l.m2298f(this.f1442k);
        }
        return true;
    }

    public void m2171c(boolean z) {
        if (!this.f1439h && z) {
            this.f1447p = true;
        }
        this.f1439h = z;
    }

    public boolean m2172c() {
        return this.f1449r;
    }

    public Rect m2173d() {
        return null;
    }

    public void m2174d(boolean z) {
    }

    public LatLng m2175e() {
        return null;
    }

    public void m2176e(boolean z) {
        m2133N();
    }

    public FPoint m2177f() {
        return this.f1437f;
    }

    public LatLng m2178g() {
        return null;
    }

    public String m2179h() {
        if (this.f1436e == null) {
            this.f1436e = "PopupOverlay";
        }
        return this.f1436e;
    }

    public String m2180i() {
        return null;
    }

    public String m2181j() {
        return null;
    }

    public boolean m2182k() {
        return false;
    }

    public void m2183l() {
    }

    public void m2184m() {
    }

    public boolean m2185n() {
        return false;
    }

    public boolean m2186o() {
        return this.f1439h;
    }

    public void m2187p() {
    }

    public int m2188q() {
        return super.hashCode();
    }

    public boolean m2189r() {
        if (this.f1437f == null) {
            return false;
        }
        IPoint iPoint = new IPoint();
        this.f1443l.m2289c().map2Win(this.f1437f.f3693x, this.f1437f.f3694y, iPoint);
        int K = m2147K();
        int L = m2148L();
        int i = (int) (((float) (iPoint.f3714x + this.f1433b)) - (((float) K) * this.f1445n));
        int i2 = (int) (((float) (iPoint.f3715y + this.f1434c)) + (((float) L) * (C2020f.f10933c - this.f1446o)));
        if (i - K > this.f1443l.m2311l() || i < (-K) * 2 || i2 < (-L) * 2 || i2 - L > this.f1443l.m2313m() || this.f1438g == null) {
            return false;
        }
        K = this.f1438g.getWidth();
        float width = ((float) K) / ((float) this.f1438g.getBitmap().getWidth());
        float height = ((float) this.f1438g.getHeight()) / ((float) this.f1438g.getBitmap().getHeight());
        if (this.f1440i == null) {
            this.f1440i = bj.m3620a(new float[]{0.0f, height, width, height, width, 0.0f, 0.0f, 0.0f});
        }
        float[] fArr = new float[]{(float) i, (float) (this.f1443l.m2313m() - i2), 0.0f, (float) (i + K), (float) (this.f1443l.m2313m() - i2), 0.0f, (float) (K + i), (float) ((this.f1443l.m2313m() - i2) + L), 0.0f, (float) i, (float) ((this.f1443l.m2313m() - i2) + L), 0.0f};
        if (this.f1435d == null) {
            this.f1435d = bj.m3620a(fArr);
        } else {
            this.f1435d = bj.m3621a(fArr, this.f1435d);
        }
        return true;
    }

    public Object m2190s() {
        return this.f1441j;
    }

    public boolean m2191t() {
        return false;
    }

    public float m2192u() {
        return 0.0f;
    }

    public int m2193v() {
        return this.f1450s;
    }

    public ArrayList<BitmapDescriptor> m2194w() {
        return null;
    }

    public boolean m2195x() {
        return this.f1432a;
    }

    public void m2196y() {
        if (this.f1432a) {
            try {
                m2170b();
                if (this.f1438g != null) {
                    Bitmap bitmap = this.f1438g.getBitmap();
                    if (bitmap != null) {
                        bitmap.recycle();
                        this.f1438g = null;
                    }
                }
                if (this.f1440i != null) {
                    this.f1440i.clear();
                    this.f1440i = null;
                }
                if (this.f1435d != null) {
                    this.f1435d.clear();
                    this.f1435d = null;
                }
                this.f1437f = null;
                this.f1441j = null;
                this.f1442k = 0;
            } catch (Throwable th) {
                ce.m3829a(th, "PopupOverlay", "realDestroy");
                th.printStackTrace();
                Log.d("destroy erro", "MarkerDelegateImp destroy");
            }
        }
    }

    public void m2197z() {
    }
}
