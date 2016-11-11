package com.amap.api.mapcore;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import com.amap.api.mapcore.util.bj;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.WeightedLatLng;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import com.tencent.open.yyb.TitleBar;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

class bg implements al {
    private float f1745A;
    private float f1746B;
    private float f1747C;
    private float f1748D;
    private float f1749E;
    private float f1750F;
    private float f1751G;
    private float f1752H;
    private float[] f1753I;
    private int[] f1754J;
    private int[] f1755K;
    private double f1756L;
    private C0410w f1757a;
    private String f1758b;
    private List<IPoint> f1759c;
    private List<FPoint> f1760d;
    private List<LatLng> f1761e;
    private List<BitmapDescriptor> f1762f;
    private List<Integer> f1763g;
    private List<Integer> f1764h;
    private FloatBuffer f1765i;
    private BitmapDescriptor f1766j;
    private LatLngBounds f1767k;
    private Object f1768l;
    private boolean f1769m;
    private boolean f1770n;
    private boolean f1771o;
    private boolean f1772p;
    private boolean f1773q;
    private boolean f1774r;
    private boolean f1775s;
    private boolean f1776t;
    private boolean f1777u;
    private int f1778v;
    private int f1779w;
    private int f1780x;
    private int f1781y;
    private float f1782z;

    public bg(C0410w c0410w) {
        this.f1759c = new ArrayList();
        this.f1760d = new ArrayList();
        this.f1761e = new ArrayList();
        this.f1762f = new ArrayList();
        this.f1763g = new ArrayList();
        this.f1764h = new ArrayList();
        this.f1766j = null;
        this.f1767k = null;
        this.f1768l = new Object();
        this.f1769m = true;
        this.f1770n = true;
        this.f1771o = false;
        this.f1772p = false;
        this.f1773q = false;
        this.f1774r = true;
        this.f1775s = false;
        this.f1776t = false;
        this.f1777u = true;
        this.f1778v = 0;
        this.f1779w = 0;
        this.f1780x = ViewCompat.MEASURED_STATE_MASK;
        this.f1781y = 0;
        this.f1782z = TitleBar.SHAREBTN_RIGHT_MARGIN;
        this.f1745A = 0.0f;
        this.f1746B = 0.0f;
        this.f1751G = 0.0f;
        this.f1752H = 0.0f;
        this.f1756L = 5.0d;
        this.f1757a = c0410w;
        try {
            this.f1758b = m3007c();
        } catch (Throwable e) {
            ce.m3829a(e, "PolylineDelegateImp", "create");
            e.printStackTrace();
        }
    }

    private double m2974a(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = ((d5 - d3) * (d - d3)) + ((d6 - d4) * (d2 - d4));
        if (d7 <= 0.0d) {
            return Math.sqrt(((d - d3) * (d - d3)) + ((d2 - d4) * (d2 - d4)));
        }
        double d8 = ((d5 - d3) * (d5 - d3)) + ((d6 - d4) * (d6 - d4));
        if (d7 >= d8) {
            return Math.sqrt(((d - d5) * (d - d5)) + ((d2 - d6) * (d2 - d6)));
        }
        d7 /= d8;
        d8 = ((d5 - d3) * d7) + d3;
        d7 = (d7 * (d6 - d4)) + d4;
        return Math.sqrt(((d7 - d2) * (d7 - d2)) + ((d - d8) * (d - d8)));
    }

    private double m2975a(FPoint fPoint, FPoint fPoint2, FPoint fPoint3) {
        return m2974a((double) fPoint.f3693x, (double) fPoint.f3694y, (double) fPoint2.f3693x, (double) fPoint2.f3694y, (double) fPoint3.f3693x, (double) fPoint3.f3694y);
    }

    private void m2976a(GL10 gl10, float f) {
        int i = 0;
        if (!this.f1773q) {
            this.f1755K = new int[this.f1762f.size()];
            for (int i2 = 0; i2 < this.f1755K.length; i2++) {
                int i3;
                int K = this.f1757a.f2548a.m2221K();
                if (K == 0) {
                    int[] iArr = new int[]{0};
                    gl10.glGenTextures(1, iArr, 0);
                    i3 = iArr[0];
                } else {
                    i3 = K;
                }
                bj.m3632b(gl10, i3, ((BitmapDescriptor) this.f1762f.get(i2)).getBitmap(), true);
                this.f1755K[i2] = i3;
            }
            this.f1773q = true;
        }
        int[] iArr2 = new int[this.f1763g.size()];
        while (i < iArr2.length) {
            iArr2[i] = this.f1755K[((Integer) this.f1763g.get(i)).intValue()];
            i++;
        }
        AMapNativeRenderer.nativeDrawLineByMultiTextureID(this.f1753I, this.f1753I.length, f, iArr2, iArr2.length, this.f1754J, this.f1754J.length, this.f1751G);
    }

    private boolean m2977a(FPoint fPoint, FPoint fPoint2) {
        return Math.abs(fPoint2.f3693x - fPoint.f3693x) >= this.f1752H || Math.abs(fPoint2.f3694y - fPoint.f3694y) >= this.f1752H;
    }

    private void m2978b(GL10 gl10) {
        float mapLenWithWin = this.f1757a.f2548a.m2289c().getMapLenWithWin((int) this.f1782z);
        switch (this.f1778v) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                m2985f(gl10, mapLenWithWin);
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                m2982d(gl10, mapLenWithWin);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                m2983e(gl10, mapLenWithWin);
            case Type.BYTE /*3*/:
                m2981c(gl10, mapLenWithWin);
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                m2979b(gl10, mapLenWithWin);
            case Type.INT /*5*/:
                m2976a(gl10, mapLenWithWin);
            default:
        }
    }

    private void m2979b(GL10 gl10, float f) {
        int[] iArr = new int[this.f1764h.size()];
        for (int i = 0; i < this.f1764h.size(); i++) {
            iArr[i] = ((Integer) this.f1764h.get(i)).intValue();
        }
        AMapNativeRenderer.nativeDrawGradientColorLine(this.f1753I, this.f1753I.length, f, iArr, this.f1764h.size(), this.f1754J, this.f1754J.length, this.f1757a.f2548a.m2280b());
    }

    private FPoint m2980c(LatLng latLng) {
        IPoint iPoint = new IPoint();
        this.f1757a.f2548a.m2243a(latLng.latitude, latLng.longitude, iPoint);
        FPoint fPoint = new FPoint();
        this.f1757a.f2548a.m2284b(iPoint.f3715y, iPoint.f3714x, fPoint);
        return fPoint;
    }

    private void m2981c(GL10 gl10, float f) {
        int[] iArr = new int[this.f1764h.size()];
        for (int i = 0; i < this.f1764h.size(); i++) {
            iArr[i] = ((Integer) this.f1764h.get(i)).intValue();
        }
        AMapNativeRenderer.nativeDrawLineByMultiColor(this.f1753I, this.f1753I.length, f, this.f1757a.f2548a.m2280b(), iArr, this.f1764h.size(), this.f1754J, this.f1754J.length);
    }

    private void m2982d(GL10 gl10, float f) {
        if (!this.f1773q) {
            this.f1779w = this.f1757a.f2548a.m2221K();
            if (this.f1779w == 0) {
                int[] iArr = new int[]{0};
                gl10.glGenTextures(1, iArr, 0);
                this.f1779w = iArr[0];
            }
            if (this.f1766j != null) {
                bj.m3632b(gl10, this.f1779w, this.f1766j.getBitmap(), true);
            }
            this.f1773q = true;
        }
        try {
            List list = this.f1760d;
            if (m2988q()) {
                synchronized (this.f1768l) {
                    list = bj.m3622a(this.f1757a.f2548a, this.f1760d, false);
                }
            }
            m2984f(list);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AMapNativeRenderer.nativeDrawLineByTextureID(this.f1753I, this.f1753I.length, f, this.f1779w, this.f1748D, this.f1749E, this.f1750F, this.f1747C, this.f1751G, false, false, false);
    }

    private void m2983e(GL10 gl10, float f) {
        AMapNativeRenderer.nativeDrawLineByTextureID(this.f1753I, this.f1753I.length, f, this.f1757a.f2548a.m2316p(), this.f1748D, this.f1749E, this.f1750F, this.f1747C, 0.0f, true, true, false);
    }

    private void m2984f(List<FPoint> list) {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        if (size >= 2) {
            FPoint fPoint = (FPoint) list.get(0);
            arrayList.add(fPoint);
            int i = 1;
            FPoint fPoint2 = fPoint;
            while (i < size - 1) {
                fPoint = (FPoint) list.get(i);
                if (m2977a(fPoint2, fPoint)) {
                    arrayList.add(fPoint);
                } else {
                    fPoint = fPoint2;
                }
                i++;
                fPoint2 = fPoint;
            }
            arrayList.add(list.get(size - 1));
            this.f1753I = new float[(arrayList.size() * 3)];
            this.f1754J = null;
            this.f1754J = new int[arrayList.size()];
            Iterator it = arrayList.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                fPoint = (FPoint) it.next();
                this.f1754J[i2] = i2;
                this.f1753I[i2 * 3] = fPoint.f3693x;
                this.f1753I[(i2 * 3) + 1] = fPoint.f3694y;
                this.f1753I[(i2 * 3) + 2] = 0.0f;
                i2++;
            }
        }
    }

    private void m2985f(GL10 gl10, float f) {
        try {
            List list = this.f1760d;
            if (m2988q()) {
                synchronized (this.f1768l) {
                    list = bj.m3622a(this.f1757a.f2548a, this.f1760d, false);
                }
            }
            m2984f(list);
            AMapNativeRenderer.nativeDrawLineByTextureID(this.f1753I, this.f1753I.length, f, this.f1757a.f2548a.m2280b(), this.f1748D, this.f1749E, this.f1750F, this.f1747C, 0.0f, false, true, false);
        } catch (Throwable th) {
        }
    }

    private List<Integer> m2986g(List<Integer> list) {
        Object obj = new int[list.size()];
        List<Integer> arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            int intValue = ((Integer) list.get(i3)).intValue();
            if (i3 == 0) {
                arrayList.add(Integer.valueOf(intValue));
            } else if (intValue != i2) {
                arrayList.add(Integer.valueOf(intValue));
            } else {
            }
            obj[i] = i3;
            i++;
            i2 = intValue;
        }
        this.f1754J = new int[arrayList.size()];
        System.arraycopy(obj, 0, this.f1754J, 0, this.f1754J.length);
        return arrayList;
    }

    private void m2987p() {
        if (this.f1781y <= FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS || this.f1746B > 12.0f) {
            this.f1752H = this.f1757a.f2548a.m2289c().getMapLenWithWin(10);
            return;
        }
        float f = (this.f1782z / 2.0f) + (this.f1746B / 2.0f);
        if (f > 200.0f) {
            f = 200.0f;
        }
        this.f1752H = this.f1757a.f2548a.m2289c().getMapLenWithWin((int) f);
    }

    private boolean m2988q() {
        try {
            this.f1746B = this.f1757a.f2548a.m2319r().zoom;
            m2987p();
            if (this.f1746B <= TitleBar.SHAREBTN_RIGHT_MARGIN || this.f1778v > 2) {
                return false;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            if (this.f1757a.f2548a == null) {
                return false;
            }
            Rect rect = new Rect(-100, -100, this.f1757a.f2548a.m2311l() + 100, this.f1757a.f2548a.m2313m() + 100);
            LatLng latLng = this.f1767k.northeast;
            LatLng latLng2 = this.f1767k.southwest;
            IPoint iPoint = new IPoint();
            this.f1757a.f2548a.m2281b(latLng.latitude, latLng2.longitude, iPoint);
            IPoint iPoint2 = new IPoint();
            this.f1757a.f2548a.m2281b(latLng.latitude, latLng.longitude, iPoint2);
            IPoint iPoint3 = new IPoint();
            this.f1757a.f2548a.m2281b(latLng2.latitude, latLng.longitude, iPoint3);
            IPoint iPoint4 = new IPoint();
            this.f1757a.f2548a.m2281b(latLng2.latitude, latLng2.longitude, iPoint4);
            return (rect.contains(iPoint.f3714x, iPoint.f3715y) && rect.contains(iPoint2.f3714x, iPoint2.f3715y) && rect.contains(iPoint3.f3714x, iPoint3.f3715y) && rect.contains(iPoint4.f3714x, iPoint4.f3715y)) ? false : true;
        } catch (Throwable th) {
            return false;
        }
    }

    private ArrayList<FPoint> m2989r() {
        ArrayList<FPoint> arrayList = new ArrayList();
        int i = 0;
        while (i < this.f1753I.length) {
            float f = this.f1753I[i];
            i++;
            float f2 = this.f1753I[i];
            i++;
            arrayList.add(new FPoint(f, f2));
            i++;
        }
        return arrayList;
    }

    public LatLng m2990a(LatLng latLng) {
        int i = 0;
        if (latLng == null) {
            return null;
        }
        if (this.f1761e == null || this.f1761e.size() == 0) {
            return null;
        }
        float f = 0.0f;
        int i2 = 0;
        while (i < this.f1761e.size()) {
            try {
                float calculateLineDistance;
                int i3;
                if (i == 0) {
                    calculateLineDistance = AMapUtils.calculateLineDistance(latLng, (LatLng) this.f1761e.get(i));
                    i3 = i2;
                } else {
                    calculateLineDistance = AMapUtils.calculateLineDistance(latLng, (LatLng) this.f1761e.get(i));
                    if (f > calculateLineDistance) {
                        i3 = i;
                    } else {
                        calculateLineDistance = f;
                        i3 = i2;
                    }
                }
                i++;
                i2 = i3;
                f = calculateLineDistance;
            } catch (Throwable th) {
                ce.m3829a(th, "PolylineDelegateImp", "getNearestLatLng");
                th.printStackTrace();
                return null;
            }
        }
        return (LatLng) this.f1761e.get(i2);
    }

    IPoint m2991a(IPoint iPoint, IPoint iPoint2, IPoint iPoint3, double d, int i) {
        IPoint iPoint4 = new IPoint();
        double d2 = (double) (iPoint2.f3714x - iPoint.f3714x);
        double d3 = (double) (iPoint2.f3715y - iPoint.f3715y);
        iPoint4.f3715y = (int) (((((double) i) * d) / Math.sqrt(((d3 * d3) / (d2 * d2)) + WeightedLatLng.DEFAULT_INTENSITY)) + ((double) iPoint3.f3715y));
        iPoint4.f3714x = (int) (((d3 * ((double) (iPoint3.f3715y - iPoint4.f3715y))) / d2) + ((double) iPoint3.f3714x));
        return iPoint4;
    }

    public void m2992a(float f) {
        this.f1745A = f;
        this.f1757a.m4252b();
        this.f1757a.f2548a.m2299f(false);
    }

    public void m2993a(int i) {
        if (this.f1778v == 0 || this.f1778v == 2) {
            this.f1780x = i;
            this.f1747C = ((float) Color.alpha(i)) / 255.0f;
            this.f1748D = ((float) Color.red(i)) / 255.0f;
            this.f1749E = ((float) Color.green(i)) / 255.0f;
            this.f1750F = ((float) Color.blue(i)) / 255.0f;
            if (this.f1770n) {
                this.f1778v = 0;
            }
            this.f1757a.f2548a.m2299f(false);
        }
    }

    public void m2994a(BitmapDescriptor bitmapDescriptor) {
        this.f1770n = false;
        this.f1778v = 1;
        this.f1766j = bitmapDescriptor;
        this.f1757a.f2548a.m2299f(false);
    }

    void m2995a(LatLng latLng, LatLng latLng2, List<IPoint> list, Builder builder) {
        double abs = (Math.abs(latLng.longitude - latLng2.longitude) * 3.141592653589793d) / 180.0d;
        LatLng latLng3 = new LatLng((latLng2.latitude + latLng.latitude) / 2.0d, (latLng2.longitude + latLng.longitude) / 2.0d, false);
        builder.include(latLng).include(latLng3).include(latLng2);
        int i = latLng3.latitude > 0.0d ? -1 : 1;
        IPoint iPoint = new IPoint();
        this.f1757a.f2548a.m2243a(latLng.latitude, latLng.longitude, iPoint);
        IPoint iPoint2 = new IPoint();
        this.f1757a.f2548a.m2243a(latLng2.latitude, latLng2.longitude, iPoint2);
        IPoint iPoint3 = new IPoint();
        this.f1757a.f2548a.m2243a(latLng3.latitude, latLng3.longitude, iPoint3);
        double cos = Math.cos(0.5d * abs);
        IPoint a = m2991a(iPoint, iPoint2, iPoint3, (Math.hypot((double) (iPoint.f3714x - iPoint2.f3714x), (double) (iPoint.f3715y - iPoint2.f3715y)) * 0.5d) * Math.tan(0.5d * abs), i);
        List arrayList = new ArrayList();
        arrayList.add(iPoint);
        arrayList.add(a);
        arrayList.add(iPoint2);
        m2997a(arrayList, (List) list, cos);
    }

    public void m2996a(List<LatLng> list) {
        try {
            this.f1761e = list;
            synchronized (this.f1768l) {
                m3004b((List) list);
            }
            this.f1774r = true;
            this.f1757a.f2548a.m2299f(false);
        } catch (Throwable th) {
            ce.m3829a(th, "PolylineDelegateImp", "setPoints");
            this.f1759c.clear();
            th.printStackTrace();
        }
    }

    void m2997a(List<IPoint> list, List<IPoint> list2, double d) {
        if (list.size() == 3) {
            for (int i = 0; i <= 10; i = (int) (((float) i) + C2020f.f10933c)) {
                float f = ((float) i) / TitleBar.SHAREBTN_RIGHT_MARGIN;
                IPoint iPoint = new IPoint();
                double d2 = ((((WeightedLatLng.DEFAULT_INTENSITY - ((double) f)) * (WeightedLatLng.DEFAULT_INTENSITY - ((double) f))) * ((double) ((IPoint) list.get(0)).f3714x)) + (((((double) (2.0f * f)) * (WeightedLatLng.DEFAULT_INTENSITY - ((double) f))) * ((double) ((IPoint) list.get(1)).f3714x)) * d)) + ((double) (((float) ((IPoint) list.get(2)).f3714x) * (f * f)));
                double d3 = ((((WeightedLatLng.DEFAULT_INTENSITY - ((double) f)) * (WeightedLatLng.DEFAULT_INTENSITY - ((double) f))) * ((double) ((IPoint) list.get(0)).f3715y)) + (((((double) (2.0f * f)) * (WeightedLatLng.DEFAULT_INTENSITY - ((double) f))) * ((double) ((IPoint) list.get(1)).f3715y)) * d)) + ((double) (((float) ((IPoint) list.get(2)).f3715y) * (f * f)));
                double d4 = (double) (f * f);
                double d5 = (((WeightedLatLng.DEFAULT_INTENSITY - ((double) f)) * (WeightedLatLng.DEFAULT_INTENSITY - ((double) f))) + ((((double) (2.0f * f)) * (WeightedLatLng.DEFAULT_INTENSITY - ((double) f))) * d)) + r0;
                iPoint.f3714x = (int) (d2 / ((((WeightedLatLng.DEFAULT_INTENSITY - ((double) f)) * (WeightedLatLng.DEFAULT_INTENSITY - ((double) f))) + ((((double) (2.0f * f)) * (WeightedLatLng.DEFAULT_INTENSITY - ((double) f))) * d)) + ((double) (f * f))));
                iPoint.f3715y = (int) (d3 / d5);
                list2.add(iPoint);
            }
        }
    }

    public void m2998a(GL10 gl10) {
        if (this.f1759c != null && this.f1759c.size() != 0 && this.f1782z > 0.0f) {
            if (this.f1774r) {
                m3018g();
                this.f1774r = false;
            }
            if (this.f1753I != null && this.f1781y > 0) {
                if (this.f1777u) {
                    m2978b(gl10);
                } else {
                    if (this.f1765i == null) {
                        this.f1765i = bj.m3620a(this.f1753I);
                    }
                    C0333u.m3341a(gl10, 3, this.f1780x, this.f1765i, this.f1782z, this.f1781y);
                }
            }
            this.f1776t = true;
        }
    }

    public void m2999a(boolean z) {
        this.f1769m = z;
        this.f1757a.f2548a.m2299f(false);
    }

    public boolean m3000a() {
        return true;
    }

    public boolean m3001a(aj ajVar) {
        return equals(ajVar) || ajVar.m2574c().equals(m3007c());
    }

    public void m3002b() {
        this.f1757a.m4255c(m3007c());
        if (this.f1755K != null && this.f1755K.length > 0) {
            for (int valueOf : this.f1755K) {
                this.f1757a.m4250a(Integer.valueOf(valueOf));
            }
        }
        if (this.f1779w > 0) {
            this.f1757a.m4250a(Integer.valueOf(this.f1779w));
        }
        this.f1757a.f2548a.m2299f(false);
    }

    public void m3003b(float f) {
        this.f1782z = f;
        this.f1757a.f2548a.m2299f(false);
    }

    void m3004b(List<LatLng> list) {
        List arrayList = new ArrayList();
        Builder builder = LatLngBounds.builder();
        if (list != null) {
            LatLng latLng = null;
            for (LatLng latLng2 : list) {
                if (!(latLng2 == null || latLng2.equals(latLng))) {
                    IPoint iPoint;
                    if (!this.f1771o) {
                        iPoint = new IPoint();
                        this.f1757a.f2548a.m2243a(latLng2.latitude, latLng2.longitude, iPoint);
                        arrayList.add(iPoint);
                        builder.include(latLng2);
                    } else if (latLng != null) {
                        if (Math.abs(latLng2.longitude - latLng.longitude) < 0.01d) {
                            iPoint = new IPoint();
                            this.f1757a.f2548a.m2243a(latLng.latitude, latLng.longitude, iPoint);
                            arrayList.add(iPoint);
                            builder.include(latLng);
                            iPoint = new IPoint();
                            this.f1757a.f2548a.m2243a(latLng2.latitude, latLng2.longitude, iPoint);
                            arrayList.add(iPoint);
                            builder.include(latLng2);
                        } else {
                            m2995a(latLng, latLng2, arrayList, builder);
                        }
                    }
                    latLng = latLng2;
                }
            }
        }
        this.f1759c = arrayList;
        this.f1781y = 0;
        if (this.f1759c.size() > 0) {
            this.f1767k = builder.build();
        }
        this.f1757a.f2548a.m2299f(false);
    }

    public void m3005b(boolean z) {
        this.f1771o = z;
        this.f1757a.f2548a.m2299f(false);
    }

    public boolean m3006b(LatLng latLng) {
        Object obj = new float[this.f1753I.length];
        System.arraycopy(this.f1753I, 0, obj, 0, this.f1753I.length);
        if (obj.length / 3 < 2) {
            return false;
        }
        try {
            ArrayList r = m2989r();
            if (r == null || r.size() < 1) {
                return false;
            }
            double mapLenWithWin = (double) this.f1757a.f2548a.m2289c().getMapLenWithWin(((int) this.f1782z) / 4);
            double mapLenWithWin2 = (double) this.f1757a.f2548a.m2289c().getMapLenWithWin((int) this.f1756L);
            FPoint c = m2980c(latLng);
            FPoint fPoint = null;
            int i = 0;
            while (i < r.size() - 1) {
                FPoint fPoint2 = i == 0 ? (FPoint) r.get(i) : fPoint;
                fPoint = (FPoint) r.get(i + 1);
                if ((mapLenWithWin2 + mapLenWithWin) - m2975a(c, fPoint2, fPoint) >= 0.0d) {
                    r.clear();
                    return true;
                }
                i++;
            }
            r.clear();
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public String m3007c() {
        if (this.f1758b == null) {
            this.f1758b = C0410w.m4243a("Polyline");
        }
        return this.f1758b;
    }

    public void m3008c(float f) {
        this.f1751G = f;
        this.f1757a.f2548a.m2299f(false);
    }

    public void m3009c(List<BitmapDescriptor> list) {
        if (list != null && list.size() != 0) {
            if (list.size() > 1) {
                this.f1770n = false;
                this.f1778v = 5;
                this.f1762f = list;
                this.f1757a.f2548a.m2299f(false);
                return;
            }
            m2994a((BitmapDescriptor) list.get(0));
        }
    }

    public void m3010c(boolean z) {
        if (this.f1778v == 2 || this.f1778v == 0) {
            this.f1772p = z;
            if (z && this.f1770n) {
                this.f1778v = 2;
            }
            this.f1757a.f2548a.m2299f(false);
        }
    }

    public float m3011d() {
        return this.f1745A;
    }

    public void m3012d(List<Integer> list) {
        if (list != null && list.size() != 0) {
            this.f1763g = m2986g(list);
        }
    }

    public void m3013d(boolean z) {
        this.f1777u = z;
        this.f1757a.f2548a.m2299f(false);
    }

    public void m3014e(List<Integer> list) {
        if (list != null && list.size() != 0) {
            if (list.size() > 1) {
                this.f1770n = false;
                this.f1764h = m2986g(list);
                this.f1778v = 3;
                this.f1757a.f2548a.m2299f(false);
                return;
            }
            m2993a(((Integer) list.get(0)).intValue());
        }
    }

    public void m3015e(boolean z) {
        if (z && this.f1764h != null && this.f1764h.size() > 1) {
            this.f1775s = z;
            this.f1778v = 4;
            this.f1757a.f2548a.m2299f(false);
        }
    }

    public boolean m3016e() {
        return this.f1769m;
    }

    public int m3017f() {
        return super.hashCode();
    }

    public void m3018g() {
        synchronized (this.f1768l) {
            this.f1760d.clear();
            this.f1776t = false;
            this.f1753I = new float[(this.f1759c.size() * 3)];
            int i = 0;
            for (IPoint iPoint : this.f1759c) {
                FPoint fPoint = new FPoint();
                this.f1757a.f2548a.m2284b(iPoint.f3715y, iPoint.f3714x, fPoint);
                this.f1753I[i * 3] = fPoint.f3693x;
                this.f1753I[(i * 3) + 1] = fPoint.f3694y;
                this.f1753I[(i * 3) + 2] = 0.0f;
                this.f1760d.add(fPoint);
                i++;
            }
        }
        if (!this.f1777u) {
            this.f1765i = bj.m3620a(this.f1753I);
        }
        this.f1781y = this.f1759c.size();
        m2987p();
    }

    public float m3019h() {
        return this.f1782z;
    }

    public int m3020i() {
        return this.f1780x;
    }

    public void m3021j() {
        try {
            m3002b();
            if (this.f1753I != null) {
                this.f1753I = null;
            }
            if (this.f1765i != null) {
                this.f1765i.clear();
                this.f1765i = null;
            }
            if (this.f1762f != null && this.f1762f.size() > 0) {
                for (BitmapDescriptor recycle : this.f1762f) {
                    recycle.recycle();
                }
            }
            if (this.f1766j != null) {
                this.f1766j.recycle();
            }
            if (this.f1764h != null) {
                this.f1764h.clear();
                this.f1764h = null;
            }
            if (this.f1763g != null) {
                this.f1763g.clear();
                this.f1763g = null;
            }
        } catch (Throwable th) {
            ce.m3829a(th, "PolylineDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "PolylineDelegateImp destroy");
        }
    }

    public boolean m3022k() {
        return this.f1776t;
    }

    public List<LatLng> m3023l() {
        return this.f1761e;
    }

    public boolean m3024m() {
        return this.f1771o;
    }

    public boolean m3025n() {
        return this.f1772p;
    }

    public void m3026o() {
        this.f1773q = false;
        this.f1779w = 0;
        if (this.f1755K != null) {
            Arrays.fill(this.f1755K, 0);
        }
    }
}
