package com.amap.api.mapcore;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;
import com.amap.api.mapcore.util.au;
import com.amap.api.mapcore.util.bj;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

public class aa implements af {
    private ab f1559a;
    private BitmapDescriptor f1560b;
    private LatLng f1561c;
    private float f1562d;
    private float f1563e;
    private LatLngBounds f1564f;
    private float f1565g;
    private float f1566h;
    private boolean f1567i;
    private float f1568j;
    private float f1569k;
    private float f1570l;
    private String f1571m;
    private FloatBuffer f1572n;
    private FloatBuffer f1573o;
    private int f1574p;
    private boolean f1575q;
    private boolean f1576r;

    public aa(ab abVar) {
        this.f1567i = true;
        this.f1568j = 0.0f;
        this.f1569k = 0.5f;
        this.f1570l = 0.5f;
        this.f1572n = null;
        this.f1575q = false;
        this.f1576r = false;
        this.f1559a = abVar;
        try {
            this.f1571m = m2613c();
        } catch (Throwable e) {
            ce.m3829a(e, "GroundOverlayDelegateImp", "create");
            e.printStackTrace();
        }
    }

    private void m2595a(DPoint dPoint, double d, double d2, double d3, double d4, FPoint fPoint) {
        double d5 = d - (((double) this.f1569k) * d3);
        double d6 = (((double) (C2020f.f10933c - this.f1570l)) * d4) - d2;
        double d7 = ((double) (-this.f1565g)) * 0.01745329251994329d;
        fPoint.f3693x = (float) (dPoint.f3691x + ((Math.cos(d7) * d5) + (Math.sin(d7) * d6)));
        fPoint.f3694y = (float) (((d6 * Math.cos(d7)) - (d5 * Math.sin(d7))) + dPoint.f3692y);
    }

    private void m2596a(GL10 gl10, int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (floatBuffer != null && floatBuffer2 != null) {
            gl10.glEnable(3042);
            gl10.glTexEnvf(8960, 8704, 8448.0f);
            gl10.glBlendFunc(1, 771);
            gl10.glColor4f(C2020f.f10933c, C2020f.f10933c, C2020f.f10933c, C2020f.f10933c - this.f1568j);
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

    private void m2597q() {
        if (this.f1561c != null) {
            double cos = ((double) this.f1562d) / ((6371000.79d * Math.cos(this.f1561c.latitude * 0.01745329251994329d)) * 0.01745329251994329d);
            double d = ((double) this.f1563e) / 111194.94043265979d;
            this.f1564f = new LatLngBounds(new LatLng(this.f1561c.latitude - (((double) (C2020f.f10933c - this.f1570l)) * d), this.f1561c.longitude - (((double) this.f1569k) * cos)), new LatLng((d * ((double) this.f1570l)) + this.f1561c.latitude, (cos * ((double) (C2020f.f10933c - this.f1569k))) + this.f1561c.longitude));
            m2599s();
        }
    }

    private void m2598r() {
        if (this.f1564f != null) {
            LatLng latLng = this.f1564f.southwest;
            LatLng latLng2 = this.f1564f.northeast;
            this.f1561c = new LatLng(latLng.latitude + (((double) (C2020f.f10933c - this.f1570l)) * (latLng2.latitude - latLng.latitude)), latLng.longitude + (((double) this.f1569k) * (latLng2.longitude - latLng.longitude)));
            this.f1562d = (float) (((6371000.79d * Math.cos(this.f1561c.latitude * 0.01745329251994329d)) * (latLng2.longitude - latLng.longitude)) * 0.01745329251994329d);
            this.f1563e = (float) (((latLng2.latitude - latLng.latitude) * 6371000.79d) * 0.01745329251994329d);
            m2599s();
        }
    }

    private void m2599s() {
        if (this.f1564f != null) {
            float[] fArr = new float[12];
            FPoint fPoint = new FPoint();
            FPoint fPoint2 = new FPoint();
            FPoint fPoint3 = new FPoint();
            FPoint fPoint4 = new FPoint();
            this.f1559a.m2242a(this.f1564f.southwest.latitude, this.f1564f.southwest.longitude, fPoint);
            this.f1559a.m2242a(this.f1564f.southwest.latitude, this.f1564f.northeast.longitude, fPoint2);
            this.f1559a.m2242a(this.f1564f.northeast.latitude, this.f1564f.northeast.longitude, fPoint3);
            this.f1559a.m2242a(this.f1564f.northeast.latitude, this.f1564f.southwest.longitude, fPoint4);
            if (this.f1565g != 0.0f) {
                double d = (double) (fPoint2.f3693x - fPoint.f3693x);
                double d2 = (double) (fPoint2.f3694y - fPoint3.f3694y);
                DPoint dPoint = new DPoint();
                dPoint.f3691x = ((double) fPoint.f3693x) + (((double) this.f1569k) * d);
                dPoint.f3692y = ((double) fPoint.f3694y) - (((double) (C2020f.f10933c - this.f1570l)) * d2);
                m2595a(dPoint, 0.0d, 0.0d, d, d2, fPoint);
                m2595a(dPoint, d, 0.0d, d, d2, fPoint2);
                m2595a(dPoint, d, d2, d, d2, fPoint3);
                m2595a(dPoint, 0.0d, d2, d, d2, fPoint4);
            }
            fArr[0] = fPoint.f3693x;
            fArr[1] = fPoint.f3694y;
            fArr[2] = 0.0f;
            fArr[3] = fPoint2.f3693x;
            fArr[4] = fPoint2.f3694y;
            fArr[5] = 0.0f;
            fArr[6] = fPoint3.f3693x;
            fArr[7] = fPoint3.f3694y;
            fArr[8] = 0.0f;
            fArr[9] = fPoint4.f3693x;
            fArr[10] = fPoint4.f3694y;
            fArr[11] = 0.0f;
            if (this.f1572n == null) {
                this.f1572n = bj.m3620a(fArr);
            } else {
                this.f1572n = bj.m3621a(fArr, this.f1572n);
            }
        }
    }

    private void m2600t() {
        if (this.f1560b != null) {
            int width = this.f1560b.getWidth();
            float width2 = ((float) width) / ((float) this.f1560b.getBitmap().getWidth());
            float height = ((float) this.f1560b.getHeight()) / ((float) this.f1560b.getBitmap().getHeight());
            this.f1573o = bj.m3620a(new float[]{0.0f, height, width2, height, width2, 0.0f, 0.0f, 0.0f});
        }
    }

    public void m2601a(float f) {
        this.f1566h = f;
        this.f1559a.m2223M();
        this.f1559a.m2299f(false);
    }

    public void m2602a(float f, float f2) {
        boolean z = true;
        au.m3487b(f >= 0.0f, "Width must be non-negative");
        if (f2 < 0.0f) {
            z = false;
        }
        au.m3487b(z, "Height must be non-negative");
        if (!this.f1575q || this.f1562d == f || this.f1563e == f2) {
            this.f1562d = f;
            this.f1563e = f2;
        } else {
            this.f1562d = f;
            this.f1563e = f2;
            m2597q();
        }
        this.f1559a.m2299f(false);
    }

    public void m2603a(BitmapDescriptor bitmapDescriptor) {
        this.f1560b = bitmapDescriptor;
        m2600t();
        if (this.f1575q) {
            this.f1575q = false;
        }
        this.f1559a.m2299f(false);
    }

    public void m2604a(LatLng latLng) {
        this.f1561c = latLng;
        m2597q();
        this.f1559a.m2299f(false);
    }

    public void m2605a(LatLngBounds latLngBounds) {
        this.f1564f = latLngBounds;
        m2598r();
        this.f1559a.m2299f(false);
    }

    public void m2606a(GL10 gl10) {
        if (!this.f1567i) {
            return;
        }
        if ((this.f1561c != null || this.f1564f != null) && this.f1560b != null) {
            if (!this.f1575q) {
                Bitmap bitmap = this.f1560b.getBitmap();
                if (!(bitmap == null || bitmap.isRecycled())) {
                    if (this.f1574p == 0) {
                        this.f1574p = this.f1559a.m2221K();
                        if (this.f1574p == 0) {
                            int[] iArr = new int[]{0};
                            gl10.glGenTextures(1, iArr, 0);
                            this.f1574p = iArr[0];
                        }
                    } else {
                        gl10.glDeleteTextures(1, new int[]{this.f1574p}, 0);
                    }
                    bj.m3632b(gl10, this.f1574p, bitmap, true);
                }
                this.f1575q = true;
            }
            if (this.f1562d != 0.0f || this.f1563e != 0.0f) {
                m2596a(gl10, this.f1574p, this.f1572n, this.f1573o);
                this.f1576r = true;
            }
        }
    }

    public void m2607a(boolean z) {
        this.f1567i = z;
        this.f1559a.m2299f(false);
    }

    public boolean m2608a() {
        Rect k = this.f1559a.m2309k();
        if (k == null) {
            return true;
        }
        IPoint iPoint = new IPoint();
        if (this.f1561c != null) {
            this.f1559a.m2281b(this.f1561c.latitude, this.f1561c.longitude, iPoint);
        }
        return k.contains(iPoint.f3714x, iPoint.f3715y);
    }

    public boolean m2609a(aj ajVar) {
        return equals(ajVar) || ajVar.m2574c().equals(m2613c());
    }

    public void m2610b() {
        this.f1559a.m2298f(this.f1574p);
        this.f1559a.m2279a(m2613c());
        this.f1559a.m2299f(false);
    }

    public void m2611b(float f) {
        au.m3487b(f >= 0.0f, "Width must be non-negative");
        if (!this.f1575q || this.f1562d == f) {
            this.f1562d = f;
            this.f1563e = f;
        } else {
            this.f1562d = f;
            this.f1563e = f;
            m2597q();
        }
        this.f1559a.m2299f(false);
    }

    public void m2612b(float f, float f2) {
        this.f1569k = f;
        this.f1570l = f2;
        this.f1559a.m2299f(false);
    }

    public String m2613c() {
        if (this.f1571m == null) {
            this.f1571m = C0410w.m4243a("GroundOverlay");
        }
        return this.f1571m;
    }

    public void m2614c(float f) {
        float f2 = ((f % 360.0f) + 360.0f) % 360.0f;
        if (!this.f1575q || ((double) Math.abs(this.f1565g - f2)) <= 1.0E-7d) {
            this.f1565g = f2;
        } else {
            this.f1565g = f2;
            m2599s();
        }
        this.f1559a.m2299f(false);
    }

    public float m2615d() {
        return this.f1566h;
    }

    public void m2616d(float f) {
        boolean z = f >= 0.0f && f <= C2020f.f10933c;
        au.m3487b(z, "Transparency must be in the range [0..1]");
        this.f1568j = f;
        this.f1559a.m2299f(false);
    }

    public boolean m2617e() {
        return this.f1567i;
    }

    public int m2618f() {
        return super.hashCode();
    }

    public void m2619g() {
        this.f1576r = false;
        if (this.f1561c == null) {
            m2598r();
        } else if (this.f1564f == null) {
            m2597q();
        } else {
            m2599s();
        }
    }

    public LatLng m2620h() {
        return this.f1561c;
    }

    public float m2621i() {
        return this.f1562d;
    }

    public void m2622j() {
        try {
            m2610b();
            if (this.f1560b != null) {
                Bitmap bitmap = this.f1560b.getBitmap();
                if (bitmap != null) {
                    bitmap.recycle();
                    this.f1560b = null;
                }
            }
            if (this.f1573o != null) {
                this.f1573o.clear();
                this.f1573o = null;
            }
            if (this.f1572n != null) {
                this.f1572n.clear();
                this.f1572n = null;
            }
            this.f1561c = null;
            this.f1564f = null;
        } catch (Throwable th) {
            ce.m3829a(th, "GroundOverlayDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "GroundOverlayDelegateImp destroy");
        }
    }

    public boolean m2623k() {
        return this.f1576r;
    }

    public float m2624l() {
        return this.f1563e;
    }

    public LatLngBounds m2625m() {
        return this.f1564f;
    }

    public float m2626n() {
        return this.f1565g;
    }

    public float m2627o() {
        return this.f1568j;
    }

    public void m2628p() {
        this.f1575q = false;
        this.f1574p = 0;
    }
}
