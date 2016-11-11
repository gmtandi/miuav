package com.amap.api.mapcore;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.view.View;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import com.p054c.p055a.p063b.p068d.C0921a;
import com.tencent.open.yyb.TitleBar;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.util.BufferRecycler;
import org.p122a.p123a.C2915a;

class bj extends View {
    private String f1784a;
    private int f1785b;
    private ab f1786c;
    private Paint f1787d;
    private Paint f1788e;
    private Rect f1789f;
    private final int[] f1790g;

    public bj(Context context) {
        super(context);
        this.f1784a = C2915a.f14760f;
        this.f1785b = 0;
        this.f1790g = new int[]{10000000, 5000000, 2000000, 1000000, 500000, 200000, 100000, FDSClientConfiguration.DEFAULT_SOCKET_TIMEOUT_MS, 30000, C0921a.f4833b, C1873o.ak, FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS, BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN, XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER, C2799f.f14263a, C2799f.f14282t, 100, 50, 25, 10, 5};
    }

    public bj(Context context, ab abVar) {
        super(context);
        this.f1784a = C2915a.f14760f;
        this.f1785b = 0;
        this.f1790g = new int[]{10000000, 5000000, 2000000, 1000000, 500000, 200000, 100000, FDSClientConfiguration.DEFAULT_SOCKET_TIMEOUT_MS, 30000, C0921a.f4833b, C1873o.ak, FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS, BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN, XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER, C2799f.f14263a, C2799f.f14282t, 100, 50, 25, 10, 5};
        this.f1786c = abVar;
        this.f1787d = new Paint();
        this.f1789f = new Rect();
        this.f1787d.setAntiAlias(true);
        this.f1787d.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f1787d.setStrokeWidth(2.0f * C0330s.f2068a);
        this.f1787d.setStyle(Style.STROKE);
        this.f1788e = new Paint();
        this.f1788e.setAntiAlias(true);
        this.f1788e.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f1788e.setTextSize(TitleBar.BACKBTN_LEFT_MARGIN * C0330s.f2068a);
    }

    public void m3034a() {
        this.f1787d = null;
        this.f1788e = null;
        this.f1789f = null;
        this.f1784a = null;
    }

    public void m3035a(int i) {
        this.f1785b = i;
    }

    public void m3036a(String str) {
        this.f1784a = str;
    }

    public void m3037a(boolean z) {
        if (z) {
            setVisibility(0);
            m3038b();
            return;
        }
        m3036a(C2915a.f14760f);
        m3035a(0);
        setVisibility(8);
    }

    void m3038b() {
        if (this.f1786c != null) {
            try {
                CameraPosition r = this.f1786c.m2319r();
                if (r != null) {
                    LatLng latLng = r.target;
                    float F = this.f1786c.m2217F();
                    double cos = (double) ((float) ((((Math.cos((latLng.latitude * 3.141592653589793d) / 180.0d) * 2.0d) * 3.141592653589793d) * 6378137.0d) / (256.0d * Math.pow(2.0d, (double) F))));
                    int W = (int) (((double) this.f1790g[(int) F]) / (((double) this.f1786c.m2229W()) * cos));
                    String b = com.amap.api.mapcore.util.bj.m3633b(this.f1790g[(int) F]);
                    m3035a(W);
                    m3036a(b);
                    invalidate();
                }
            } catch (Throwable th) {
                ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "changeScaleState");
                th.printStackTrace();
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.f1784a != null && !this.f1784a.equals(C2915a.f14760f) && this.f1785b != 0) {
            Point I = this.f1786c.m2219I();
            if (I != null) {
                this.f1788e.getTextBounds(this.f1784a, 0, this.f1784a.length(), this.f1789f);
                int i = I.x;
                int height = (I.y - this.f1789f.height()) + 5;
                canvas.drawText(this.f1784a, (float) i, (float) height, this.f1788e);
                int height2 = height + (this.f1789f.height() - 5);
                canvas.drawLine((float) i, (float) (height2 - 2), (float) i, (float) (height2 + 2), this.f1787d);
                canvas.drawLine((float) i, (float) height2, (float) (this.f1785b + i), (float) height2, this.f1787d);
                canvas.drawLine((float) (this.f1785b + i), (float) (height2 - 2), (float) (this.f1785b + i), (float) (height2 + 2), this.f1787d);
            }
        }
    }
}
