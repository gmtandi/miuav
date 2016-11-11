package com.amap.api.mapcore;

import android.graphics.Bitmap;
import com.amap.api.mapcore.util.av;
import com.amap.api.mapcore.util.av.C0349d;
import com.amap.api.mapcore.util.ba.C0352a;
import com.amap.api.mapcore.util.bb;
import com.amap.api.mapcore.util.bd;
import com.amap.api.mapcore.util.bd.C0353a;
import com.amap.api.mapcore.util.bg;
import com.amap.api.mapcore.util.bj;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.TileProvider;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;

public class bn implements ap {
    private static int f1858g;
    private bo f1859a;
    private TileProvider f1860b;
    private Float f1861c;
    private boolean f1862d;
    private boolean f1863e;
    private ab f1864f;
    private int f1865h;
    private int f1866i;
    private int f1867j;
    private bb f1868k;
    private CopyOnWriteArrayList<C0304a> f1869l;
    private boolean f1870m;
    private C0305b f1871n;
    private String f1872o;
    private FloatBuffer f1873p;

    /* renamed from: com.amap.api.mapcore.bn.a */
    public class C0304a implements Cloneable {
        public int f1831a;
        public int f1832b;
        public int f1833c;
        public int f1834d;
        public IPoint f1835e;
        public int f1836f;
        public boolean f1837g;
        public FloatBuffer f1838h;
        public Bitmap f1839i;
        public C0353a f1840j;
        public int f1841k;
        final /* synthetic */ bn f1842l;

        public C0304a(bn bnVar, int i, int i2, int i3, int i4) {
            this.f1842l = bnVar;
            this.f1836f = 0;
            this.f1837g = false;
            this.f1838h = null;
            this.f1839i = null;
            this.f1840j = null;
            this.f1841k = 0;
            this.f1831a = i;
            this.f1832b = i2;
            this.f1833c = i3;
            this.f1834d = i4;
        }

        public C0304a(bn bnVar, C0304a c0304a) {
            this.f1842l = bnVar;
            this.f1836f = 0;
            this.f1837g = false;
            this.f1838h = null;
            this.f1839i = null;
            this.f1840j = null;
            this.f1841k = 0;
            this.f1831a = c0304a.f1831a;
            this.f1832b = c0304a.f1832b;
            this.f1833c = c0304a.f1833c;
            this.f1834d = c0304a.f1834d;
            this.f1835e = c0304a.f1835e;
            this.f1838h = c0304a.f1838h;
        }

        public C0304a m3122a() {
            try {
                C0304a c0304a = (C0304a) super.clone();
                c0304a.f1831a = this.f1831a;
                c0304a.f1832b = this.f1832b;
                c0304a.f1833c = this.f1833c;
                c0304a.f1834d = this.f1834d;
                c0304a.f1835e = (IPoint) this.f1835e.clone();
                c0304a.f1838h = this.f1838h.asReadOnlyBuffer();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return new C0304a(this.f1842l, this);
        }

        public void m3123a(Bitmap bitmap) {
            if (bitmap != null && !bitmap.isRecycled()) {
                try {
                    this.f1840j = null;
                    this.f1839i = bj.m3611a(bitmap, bj.m3604a(bitmap.getWidth()), bj.m3604a(bitmap.getHeight()));
                    this.f1842l.f1864f.m2299f(false);
                } catch (Throwable th) {
                    ce.m3829a(th, "TileOverlayDelegateImp", "setBitmap");
                    th.printStackTrace();
                    if (this.f1841k < 3) {
                        this.f1842l.f1868k.m3557a(true, this);
                        this.f1841k++;
                    }
                }
            } else if (this.f1841k < 3) {
                this.f1842l.f1868k.m3557a(true, this);
                this.f1841k++;
            }
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }

        public void m3124b() {
            try {
                bd.m3548a(this);
                if (this.f1837g) {
                    this.f1842l.f1859a.f1879c.add(Integer.valueOf(this.f1836f));
                }
                this.f1837g = false;
                this.f1836f = 0;
                if (!(this.f1839i == null || this.f1839i.isRecycled())) {
                    this.f1839i.recycle();
                }
                this.f1839i = null;
                if (this.f1838h != null) {
                    this.f1838h.clear();
                }
                this.f1838h = null;
                this.f1840j = null;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        public /* synthetic */ Object clone() {
            return m3122a();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof C0304a)) {
                return false;
            }
            C0304a c0304a = (C0304a) obj;
            return this.f1831a == c0304a.f1831a && this.f1832b == c0304a.f1832b && this.f1833c == c0304a.f1833c && this.f1834d == c0304a.f1834d;
        }

        public int hashCode() {
            return (((this.f1831a * 7) + (this.f1832b * 11)) + (this.f1833c * 13)) + this.f1834d;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.f1831a);
            stringBuilder.append("-");
            stringBuilder.append(this.f1832b);
            stringBuilder.append("-");
            stringBuilder.append(this.f1833c);
            stringBuilder.append("-");
            stringBuilder.append(this.f1834d);
            return stringBuilder.toString();
        }
    }

    /* renamed from: com.amap.api.mapcore.bn.b */
    class C0305b extends av<ab, Void, List<C0304a>> {
        final /* synthetic */ bn f1855a;
        private int f1856e;
        private boolean f1857f;

        public C0305b(bn bnVar, boolean z) {
            this.f1855a = bnVar;
            this.f1857f = z;
        }

        protected List<C0304a> m3145a(ab... abVarArr) {
            List<C0304a> list = null;
            try {
                int l = abVarArr[0].m2311l();
                int m = abVarArr[0].m2313m();
                this.f1856e = (int) abVarArr[0].m2217F();
                if (l > 0 && m > 0) {
                    list = this.f1855a.m3150a(this.f1856e, l, m);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return list;
        }

        protected void m3147a(List<C0304a> list) {
            if (list != null) {
                try {
                    if (list.size() > 0) {
                        this.f1855a.m3155a((List) list, this.f1856e, this.f1857f);
                        list.clear();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    static {
        f1858g = 0;
    }

    public bn(TileOverlayOptions tileOverlayOptions, bo boVar) {
        this.f1863e = false;
        this.f1865h = Opcodes.ACC_NATIVE;
        this.f1866i = Opcodes.ACC_NATIVE;
        this.f1867j = -1;
        this.f1869l = new CopyOnWriteArrayList();
        this.f1870m = false;
        this.f1871n = null;
        this.f1872o = null;
        this.f1873p = null;
        this.f1859a = boVar;
        this.f1860b = tileOverlayOptions.getTileProvider();
        this.f1865h = this.f1860b.getTileWidth();
        this.f1866i = this.f1860b.getTileHeight();
        int a = bj.m3604a(this.f1865h);
        float f = ((float) this.f1865h) / ((float) a);
        float a2 = ((float) this.f1866i) / ((float) bj.m3604a(this.f1866i));
        this.f1873p = bj.m3620a(new float[]{0.0f, a2, f, a2, f, 0.0f, 0.0f, 0.0f});
        this.f1861c = Float.valueOf(tileOverlayOptions.getZIndex());
        this.f1862d = tileOverlayOptions.isVisible();
        this.f1872o = m3165c();
        this.f1864f = this.f1859a.m3174a();
        this.f1867j = Integer.valueOf(this.f1872o.substring("TileOverlay".length())).intValue();
        C0352a c0352a = new C0352a(this.f1859a.getContext(), this.f1872o);
        c0352a.m3525a(tileOverlayOptions.getMemoryCacheEnabled());
        c0352a.m3527b(tileOverlayOptions.getDiskCacheEnabled());
        c0352a.m3523a(tileOverlayOptions.getMemCacheSize());
        c0352a.m3526b(tileOverlayOptions.getDiskCacheSize());
        String diskCacheDir = tileOverlayOptions.getDiskCacheDir();
        if (!(diskCacheDir == null || diskCacheDir.equals(C2915a.f14760f))) {
            c0352a.m3524a(diskCacheDir);
        }
        this.f1868k = new bb(this.f1859a.getContext(), this.f1865h, this.f1866i);
        this.f1868k.m3575a(this.f1860b);
        this.f1868k.m3555a(c0352a);
        m3164b(true);
    }

    public bn(TileOverlayOptions tileOverlayOptions, bo boVar, boolean z) {
        this(tileOverlayOptions, boVar);
        this.f1863e = z;
    }

    private static String m3149a(String str) {
        f1858g++;
        return str + f1858g;
    }

    private ArrayList<C0304a> m3150a(int i, int i2, int i3) {
        MapProjection c = this.f1864f.m2289c();
        FPoint fPoint = new FPoint();
        IPoint iPoint = new IPoint();
        IPoint iPoint2 = new IPoint();
        c.win2Map(0, 0, fPoint);
        c.map2Geo(fPoint.f3693x, fPoint.f3694y, iPoint);
        int min = Math.min(Integer.MAX_VALUE, iPoint.f3714x);
        int max = Math.max(0, iPoint.f3714x);
        int min2 = Math.min(Integer.MAX_VALUE, iPoint.f3715y);
        int max2 = Math.max(0, iPoint.f3715y);
        c.win2Map(i2, 0, fPoint);
        c.map2Geo(fPoint.f3693x, fPoint.f3694y, iPoint);
        min = Math.min(min, iPoint.f3714x);
        max = Math.max(max, iPoint.f3714x);
        min2 = Math.min(min2, iPoint.f3715y);
        max2 = Math.max(max2, iPoint.f3715y);
        c.win2Map(0, i3, fPoint);
        c.map2Geo(fPoint.f3693x, fPoint.f3694y, iPoint);
        min = Math.min(min, iPoint.f3714x);
        max = Math.max(max, iPoint.f3714x);
        min2 = Math.min(min2, iPoint.f3715y);
        max2 = Math.max(max2, iPoint.f3715y);
        c.win2Map(i2, i3, fPoint);
        c.map2Geo(fPoint.f3693x, fPoint.f3694y, iPoint);
        min = Math.min(min, iPoint.f3714x);
        int max3 = Math.max(max, iPoint.f3714x);
        max = Math.min(min2, iPoint.f3715y);
        int max4 = Math.max(max2, iPoint.f3715y);
        int i4 = min - ((1 << (20 - i)) * this.f1865h);
        int i5 = max - ((1 << (20 - i)) * this.f1866i);
        c.getGeoCenter(iPoint2);
        max = (iPoint2.f3714x >> (20 - i)) / this.f1865h;
        min2 = (iPoint2.f3715y >> (20 - i)) / this.f1866i;
        int i6 = (max << (20 - i)) * this.f1865h;
        int i7 = (min2 << (20 - i)) * this.f1866i;
        C0304a c0304a = new C0304a(this, max, min2, i, this.f1867j);
        c0304a.f1835e = new IPoint(i6, i7);
        ArrayList<C0304a> arrayList = new ArrayList();
        arrayList.add(c0304a);
        min = 1;
        while (true) {
            Object obj = null;
            for (i6 = max - min; i6 <= max + min; i6++) {
                i7 = min2 + min;
                IPoint iPoint3 = new IPoint((i6 << (20 - i)) * this.f1865h, (i7 << (20 - i)) * this.f1866i);
                if (iPoint3.f3714x < max3 && iPoint3.f3714x > i4 && iPoint3.f3715y < max4 && iPoint3.f3715y > i5) {
                    if (obj == null) {
                        obj = 1;
                    }
                    C0304a c0304a2 = new C0304a(this, i6, i7, i, this.f1867j);
                    c0304a2.f1835e = iPoint3;
                    arrayList.add(c0304a2);
                }
                i7 = min2 - min;
                iPoint3 = new IPoint((i6 << (20 - i)) * this.f1865h, (i7 << (20 - i)) * this.f1866i);
                if (iPoint3.f3714x < max3 && iPoint3.f3714x > i4 && iPoint3.f3715y < max4 && iPoint3.f3715y > i5) {
                    if (obj == null) {
                        obj = 1;
                    }
                    c0304a2 = new C0304a(this, i6, i7, i, this.f1867j);
                    c0304a2.f1835e = iPoint3;
                    arrayList.add(c0304a2);
                }
            }
            for (i7 = (min2 + min) - 1; i7 > min2 - min; i7--) {
                i6 = max + min;
                iPoint3 = new IPoint((i6 << (20 - i)) * this.f1865h, (i7 << (20 - i)) * this.f1866i);
                if (iPoint3.f3714x < max3 && iPoint3.f3714x > i4 && iPoint3.f3715y < max4 && iPoint3.f3715y > i5) {
                    if (obj == null) {
                        obj = 1;
                    }
                    c0304a2 = new C0304a(this, i6, i7, i, this.f1867j);
                    c0304a2.f1835e = iPoint3;
                    arrayList.add(c0304a2);
                }
                i6 = max - min;
                iPoint3 = new IPoint((i6 << (20 - i)) * this.f1865h, (i7 << (20 - i)) * this.f1866i);
                if (iPoint3.f3714x < max3 && iPoint3.f3714x > i4 && iPoint3.f3715y < max4 && iPoint3.f3715y > i5) {
                    if (obj == null) {
                        obj = 1;
                    }
                    c0304a2 = new C0304a(this, i6, i7, i, this.f1867j);
                    c0304a2.f1835e = iPoint3;
                    arrayList.add(c0304a2);
                }
            }
            if (obj == null) {
                return arrayList;
            }
            min++;
        }
    }

    private void m3152a(GL10 gl10, int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (floatBuffer != null && floatBuffer2 != null) {
            gl10.glEnable(3042);
            gl10.glTexEnvf(8960, 8704, 8448.0f);
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

    private boolean m3153a(C0304a c0304a) {
        MapProjection c = this.f1864f.m2289c();
        float f = (float) c0304a.f1833c;
        int i = this.f1865h;
        int i2 = this.f1866i;
        int i3 = c0304a.f1835e.f3714x;
        int i4 = c0304a.f1835e.f3715y + ((1 << (20 - ((int) f))) * i2);
        r6 = new float[12];
        FPoint fPoint = new FPoint();
        c.geo2Map(i3, i4, fPoint);
        FPoint fPoint2 = new FPoint();
        c.geo2Map(((1 << (20 - ((int) f))) * i) + i3, i4, fPoint2);
        FPoint fPoint3 = new FPoint();
        c.geo2Map((i * (1 << (20 - ((int) f)))) + i3, i4 - ((1 << (20 - ((int) f))) * i2), fPoint3);
        FPoint fPoint4 = new FPoint();
        c.geo2Map(i3, i4 - ((1 << (20 - ((int) f))) * i2), fPoint4);
        r6[0] = fPoint.f3693x;
        r6[1] = fPoint.f3694y;
        r6[2] = 0.0f;
        r6[3] = fPoint2.f3693x;
        r6[4] = fPoint2.f3694y;
        r6[5] = 0.0f;
        r6[6] = fPoint3.f3693x;
        r6[7] = fPoint3.f3694y;
        r6[8] = 0.0f;
        r6[9] = fPoint4.f3693x;
        r6[10] = fPoint4.f3694y;
        r6[11] = 0.0f;
        if (c0304a.f1838h == null) {
            c0304a.f1838h = bj.m3620a(r6);
        } else {
            c0304a.f1838h = bj.m3621a(r6, c0304a.f1838h);
        }
        return true;
    }

    private boolean m3155a(List<C0304a> list, int i, boolean z) {
        int i2 = 0;
        if (list == null) {
            return false;
        }
        if (this.f1869l == null) {
            return false;
        }
        Iterator it = this.f1869l.iterator();
        while (it.hasNext()) {
            int i3;
            C0304a c0304a = (C0304a) it.next();
            for (C0304a c0304a2 : list) {
                if (c0304a.equals(c0304a2) && c0304a.f1837g) {
                    c0304a2.f1837g = c0304a.f1837g;
                    c0304a2.f1836f = c0304a.f1836f;
                    i3 = 1;
                    break;
                }
            }
            i3 = 0;
            if (i3 == 0) {
                c0304a.m3124b();
            }
        }
        this.f1869l.clear();
        if (i > ((int) this.f1864f.m2320s()) || i < ((int) this.f1864f.m2321t())) {
            return false;
        }
        i3 = list.size();
        if (i3 <= 0) {
            return false;
        }
        while (i2 < i3) {
            c0304a = (C0304a) list.get(i2);
            if (c0304a != null && (!this.f1863e || (c0304a.f1833c >= 10 && !bg.m3591a(c0304a.f1831a, c0304a.f1832b, c0304a.f1833c)))) {
                this.f1869l.add(c0304a);
                if (!c0304a.f1837g) {
                    this.f1868k.m3557a(z, c0304a);
                }
            }
            i2++;
        }
        return true;
    }

    public void m3158a() {
        if (this.f1871n != null && this.f1871n.m3133a() == C0349d.RUNNING) {
            this.f1871n.m3137a(true);
        }
        Iterator it = this.f1869l.iterator();
        while (it.hasNext()) {
            ((C0304a) it.next()).m3124b();
        }
        this.f1869l.clear();
        this.f1868k.m3564g();
        this.f1859a.m3180b((ap) this);
        this.f1864f.m2299f(false);
    }

    public void m3159a(float f) {
        this.f1861c = Float.valueOf(f);
        this.f1859a.m3181c();
    }

    public void m3160a(GL10 gl10) {
        if (this.f1869l != null && this.f1869l.size() != 0) {
            Iterator it = this.f1869l.iterator();
            while (it.hasNext()) {
                C0304a c0304a = (C0304a) it.next();
                if (!c0304a.f1837g) {
                    try {
                        IPoint iPoint = c0304a.f1835e;
                        if (!(c0304a.f1839i == null || c0304a.f1839i.isRecycled() || iPoint == null)) {
                            c0304a.f1836f = bj.m3606a(gl10, c0304a.f1839i);
                            if (c0304a.f1836f != 0) {
                                c0304a.f1837g = true;
                            }
                            c0304a.f1839i = null;
                        }
                    } catch (Throwable th) {
                        ce.m3829a(th, "TileOverlayDelegateImp", "drawTiles");
                    }
                }
                if (c0304a.f1837g) {
                    m3153a(c0304a);
                    m3152a(gl10, c0304a.f1836f, c0304a.f1838h, this.f1873p);
                }
            }
        }
    }

    public void m3161a(boolean z) {
        this.f1862d = z;
        this.f1864f.m2299f(false);
        if (z) {
            m3164b(true);
        }
    }

    public boolean m3162a(ap apVar) {
        return equals(apVar) || apVar.m2715c().equals(m3165c());
    }

    public void m3163b() {
        this.f1868k.m3563f();
    }

    public void m3164b(boolean z) {
        if (!this.f1870m) {
            if (this.f1871n != null && this.f1871n.m3133a() == C0349d.RUNNING) {
                this.f1871n.m3137a(true);
            }
            this.f1871n = new C0305b(this, z);
            this.f1871n.m3141c((Object[]) new ab[]{this.f1864f});
        }
    }

    public String m3165c() {
        if (this.f1872o == null) {
            this.f1872o = m3149a("TileOverlay");
        }
        return this.f1872o;
    }

    public void m3166c(boolean z) {
        if (this.f1870m != z) {
            this.f1870m = z;
            this.f1868k.m3559b(z);
        }
    }

    public float m3167d() {
        return this.f1861c.floatValue();
    }

    public boolean m3168e() {
        return this.f1862d;
    }

    public int m3169f() {
        return super.hashCode();
    }

    public void m3170g() {
        this.f1868k.m3556a(false);
    }

    public void m3171h() {
        if (this.f1869l != null && this.f1869l.size() != 0) {
            Iterator it = this.f1869l.iterator();
            while (it.hasNext()) {
                C0304a c0304a = (C0304a) it.next();
                c0304a.f1837g = false;
                c0304a.f1836f = 0;
            }
        }
    }
}
