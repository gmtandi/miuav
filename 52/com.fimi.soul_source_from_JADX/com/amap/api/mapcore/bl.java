package com.amap.api.mapcore;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.opengl.GLES10;
import android.os.Handler;
import android.util.Log;
import com.amap.api.mapcore.util.bj;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.TextOptions;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;

class bl implements ao {
    private static int f1799a;
    private Paint f1800A;
    private Handler f1801B;
    private Runnable f1802C;
    private boolean f1803D;
    private boolean f1804E;
    private float f1805b;
    private float f1806c;
    private int f1807d;
    private int f1808e;
    private FPoint f1809f;
    private int f1810g;
    private Bitmap f1811h;
    private int f1812i;
    private int f1813j;
    private FloatBuffer f1814k;
    private String f1815l;
    private LatLng f1816m;
    private float f1817n;
    private float f1818o;
    private boolean f1819p;
    private aw f1820q;
    private FloatBuffer f1821r;
    private Object f1822s;
    private String f1823t;
    private int f1824u;
    private int f1825v;
    private int f1826w;
    private Typeface f1827x;
    private float f1828y;
    private Rect f1829z;

    static {
        f1799a = 0;
    }

    public bl(TextOptions textOptions, aw awVar) {
        this.f1805b = 0.0f;
        this.f1806c = 0.0f;
        this.f1807d = 4;
        this.f1808e = 32;
        this.f1809f = new FPoint();
        this.f1814k = null;
        this.f1817n = 0.5f;
        this.f1818o = C2020f.f10933c;
        this.f1819p = true;
        this.f1829z = new Rect();
        this.f1800A = new Paint();
        this.f1801B = new Handler();
        this.f1802C = new bm(this);
        this.f1803D = false;
        this.f1804E = false;
        this.f1820q = awVar;
        if (textOptions.getPosition() != null) {
            this.f1816m = textOptions.getPosition();
        }
        m3088b(textOptions.getAlignX(), textOptions.getAlignY());
        this.f1819p = textOptions.isVisible();
        this.f1823t = textOptions.getText();
        this.f1824u = textOptions.getBackgroundColor();
        this.f1825v = textOptions.getFontColor();
        this.f1826w = textOptions.getFontSize();
        this.f1822s = textOptions.getObject();
        this.f1828y = textOptions.getZIndex();
        this.f1827x = textOptions.getTypeface();
        this.f1815l = m3103h();
        m3072a(textOptions.getRotate());
        m3043Q();
        m3113r();
    }

    private void m3043Q() {
        if (this.f1823t != null && this.f1823t.trim().length() > 0) {
            try {
                this.f1800A.setTypeface(this.f1827x);
                this.f1800A.setSubpixelText(true);
                this.f1800A.setAntiAlias(true);
                this.f1800A.setStrokeWidth(5.0f);
                this.f1800A.setStrokeCap(Cap.ROUND);
                this.f1800A.setTextSize((float) this.f1826w);
                this.f1800A.setTextAlign(Align.CENTER);
                this.f1800A.setColor(this.f1825v);
                FontMetrics fontMetrics = this.f1800A.getFontMetrics();
                int i = (int) (fontMetrics.descent - fontMetrics.ascent);
                int i2 = (int) (((((float) i) - fontMetrics.bottom) - fontMetrics.top) / 2.0f);
                this.f1800A.getTextBounds(this.f1823t, 0, this.f1823t.length(), this.f1829z);
                Bitmap createBitmap = Bitmap.createBitmap(this.f1829z.width() + 6, i, Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawColor(this.f1824u);
                canvas.drawText(this.f1823t, (float) (this.f1829z.centerX() + 3), (float) i2, this.f1800A);
                this.f1811h = createBitmap;
                this.f1812i = this.f1811h.getWidth();
                this.f1813j = this.f1811h.getHeight();
                this.f1821r = bj.m3620a(new float[]{0.0f, C2020f.f10933c, C2020f.f10933c, C2020f.f10933c, C2020f.f10933c, 0.0f, 0.0f, 0.0f});
            } catch (Throwable th) {
                ce.m3829a(th, "TextDelegateImp", "initBitmap");
            }
        }
    }

    private int m3044R() {
        return this.f1812i;
    }

    private int m3045S() {
        return this.f1813j;
    }

    private void m3046T() {
        if (this.f1820q.f1631a != null) {
            this.f1820q.f1631a.m2299f(false);
        }
    }

    private void m3047U() {
        this.f1801B.removeCallbacks(this.f1802C);
        this.f1801B.post(this.f1802C);
    }

    private int m3048a(GL10 gl10) {
        int K = this.f1820q.f1631a.m2221K();
        if (K != 0) {
            return K;
        }
        int[] iArr = new int[]{0};
        gl10.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    private void m3049a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
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

    private void m3050a(ab abVar) {
        float[] a = bj.m3630a(abVar, 0, this.f1809f, this.f1805b, m3044R(), m3045S(), this.f1817n, this.f1818o);
        if (this.f1814k == null) {
            this.f1814k = bj.m3620a(a);
        } else {
            this.f1814k = bj.m3621a(a, this.f1814k);
        }
        if (this.f1810g != 0) {
            m3049a(this.f1810g, this.f1814k, this.f1821r);
        }
    }

    private static String m3054d(String str) {
        f1799a++;
        return str + f1799a;
    }

    public boolean m3055A() {
        return false;
    }

    public int m3056B() {
        return 0;
    }

    public int m3057C() {
        return 0;
    }

    public int m3058D() {
        return 0;
    }

    public int m3059E() {
        return 0;
    }

    public boolean m3060F() {
        return false;
    }

    public float m3061G() {
        return this.f1828y;
    }

    public boolean m3062H() {
        Rect k = this.f1820q.f1631a.m2309k();
        if (k == null) {
            return true;
        }
        IPoint iPoint = new IPoint();
        if (this.f1816m != null) {
            this.f1820q.f1631a.m2281b(this.f1816m.latitude, this.f1816m.longitude, iPoint);
        }
        return k.contains(iPoint.f3714x, iPoint.f3715y);
    }

    public IPoint m3063I() {
        return null;
    }

    public void m3064J() {
        this.f1804E = false;
        this.f1810g = 0;
        m3043Q();
    }

    public int m3065K() {
        return this.f1824u;
    }

    public int m3066L() {
        return this.f1825v;
    }

    public int m3067M() {
        return this.f1826w;
    }

    public Typeface m3068N() {
        return this.f1827x;
    }

    public int m3069O() {
        return this.f1807d;
    }

    public int m3070P() {
        return this.f1808e;
    }

    public String m3071a() {
        return this.f1823t;
    }

    public void m3072a(float f) {
        this.f1806c = f;
        this.f1805b = (((-f) % 360.0f) + 360.0f) % 360.0f;
        m3046T();
    }

    public void m3073a(float f, float f2) {
    }

    public void m3074a(int i) {
    }

    public void m3075a(int i, int i2) {
    }

    public void m3076a(Typeface typeface) {
        this.f1827x = typeface;
        m3047U();
    }

    public synchronized void m3077a(BitmapDescriptor bitmapDescriptor) {
    }

    public void m3078a(LatLng latLng) {
        this.f1816m = latLng;
        m3113r();
        m3046T();
    }

    public void m3079a(IPoint iPoint) {
    }

    public void m3080a(Object obj) {
        this.f1822s = obj;
    }

    public void m3081a(String str) {
    }

    public synchronized void m3082a(ArrayList<BitmapDescriptor> arrayList) {
    }

    public void m3083a(GL10 gl10, ab abVar) {
        if (this.f1819p && !this.f1803D && this.f1816m != null && this.f1811h != null) {
            if (!this.f1804E) {
                try {
                    if (!(this.f1811h == null || this.f1811h.isRecycled())) {
                        if (this.f1810g == 0) {
                            this.f1810g = m3048a(gl10);
                        }
                        bj.m3632b(gl10, this.f1810g, this.f1811h, false);
                        this.f1804E = true;
                        this.f1811h.recycle();
                    }
                } catch (Throwable th) {
                    ce.m3829a(th, "TextDelegateImp", "loadtexture");
                    th.printStackTrace();
                    return;
                }
            }
            try {
                m3050a(abVar);
            } catch (Throwable th2) {
                ce.m3829a(th2, "TextDelegateImp", "drawMarker");
            }
        }
    }

    public void m3084a(boolean z) {
    }

    public boolean m3085a(ah ahVar) {
        return equals(ahVar) || ahVar.m2114h().equals(m3103h());
    }

    public void m3086b(float f) {
        this.f1828y = f;
        this.f1820q.m2825h();
    }

    public void m3087b(int i) {
        this.f1824u = i;
        m3047U();
    }

    public void m3088b(int i, int i2) {
        this.f1807d = i;
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f1817n = 0.0f;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f1817n = C2020f.f10933c;
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                this.f1817n = 0.5f;
                break;
            default:
                this.f1817n = 0.5f;
                break;
        }
        this.f1808e = i2;
        switch (i2) {
            case Type.DOUBLE /*8*/:
                this.f1818o = 0.0f;
                break;
            case Segment.TOKENS_PER_SEGMENT /*16*/:
                this.f1818o = C2020f.f10933c;
                break;
            case Opcodes.ACC_SYNCHRONIZED /*32*/:
                this.f1818o = 0.5f;
                break;
            default:
                this.f1818o = 0.5f;
                break;
        }
        m3046T();
    }

    public void m3089b(String str) {
    }

    public void m3090b(boolean z) {
    }

    public synchronized boolean m3091b() {
        m3046T();
        this.f1819p = false;
        return this.f1820q.m2816b((ah) this);
    }

    public void m3092c(int i) {
        this.f1825v = i;
        m3047U();
    }

    public void m3093c(String str) {
        this.f1823t = str;
        m3047U();
    }

    public void m3094c(boolean z) {
        if (this.f1819p != z) {
            this.f1819p = z;
            m3046T();
        }
    }

    public boolean m3095c() {
        return true;
    }

    public Rect m3096d() {
        return null;
    }

    public void m3097d(int i) {
        this.f1826w = i;
        m3047U();
    }

    public void m3098d(boolean z) {
    }

    public LatLng m3099e() {
        return this.f1816m;
    }

    public void m3100e(boolean z) {
    }

    public FPoint m3101f() {
        return this.f1809f;
    }

    public LatLng m3102g() {
        return this.f1816m;
    }

    public String m3103h() {
        if (this.f1815l == null) {
            this.f1815l = m3054d("Text");
        }
        return this.f1815l;
    }

    public String m3104i() {
        return null;
    }

    public String m3105j() {
        return null;
    }

    public boolean m3106k() {
        return false;
    }

    public void m3107l() {
    }

    public void m3108m() {
    }

    public boolean m3109n() {
        return false;
    }

    public boolean m3110o() {
        return this.f1819p;
    }

    public void m3111p() {
        try {
            this.f1803D = true;
            if (!(this.f1820q == null || this.f1820q.f1631a == null)) {
                this.f1820q.f1631a.m2224N();
            }
            this.f1810g = 0;
        } catch (Throwable th) {
            ce.m3829a(th, "TextDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "TextDelegateImp destroy");
        }
    }

    public int m3112q() {
        return super.hashCode();
    }

    public boolean m3113r() {
        if (this.f1816m == null) {
            return false;
        }
        this.f1820q.f1631a.m2242a(this.f1816m.latitude, this.f1816m.longitude, this.f1809f);
        return true;
    }

    public Object m3114s() {
        return this.f1822s;
    }

    public boolean m3115t() {
        return false;
    }

    public float m3116u() {
        return this.f1806c;
    }

    public int m3117v() {
        return 0;
    }

    public synchronized ArrayList<BitmapDescriptor> m3118w() {
        return null;
    }

    public boolean m3119x() {
        return this.f1803D;
    }

    public synchronized void m3120y() {
        if (this.f1803D) {
            try {
                m3091b();
                if (this.f1811h != null) {
                    this.f1811h.recycle();
                    this.f1811h = null;
                }
                if (this.f1821r != null) {
                    this.f1821r.clear();
                    this.f1821r = null;
                }
                if (this.f1814k != null) {
                    this.f1814k.clear();
                    this.f1814k = null;
                }
                this.f1816m = null;
                this.f1822s = null;
            } catch (Throwable th) {
                ce.m3829a(th, "TextDelegateImp", "realdestroy");
                th.printStackTrace();
                Log.d("destroy erro", "TextDelegateImp destroy");
            }
        }
    }

    public void m3121z() {
        this.f1820q.m2817c(this);
    }
}
