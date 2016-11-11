package com.amap.api.mapcore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.support.v4.view.ViewCompat;
import android.view.View;
import com.amap.api.mapcore.C0330s.C0329a;
import com.amap.api.mapcore.util.bh;
import com.amap.api.mapcore.util.bj;
import com.amap.api.mapcore.util.ce;
import java.io.InputStream;

class br extends View {
    int f1896a;
    private Bitmap f1897b;
    private Bitmap f1898c;
    private Bitmap f1899d;
    private Bitmap f1900e;
    private Paint f1901f;
    private boolean f1902g;
    private int f1903h;
    private AMapDelegateImp f1904i;
    private int f1905j;

    public br(Context context) {
        super(context);
        this.f1901f = new Paint();
        this.f1902g = false;
        this.f1903h = 0;
        this.f1905j = 0;
        this.f1896a = 10;
    }

    public br(Context context, AMapDelegateImp aMapDelegateImp) {
        super(context);
        this.f1901f = new Paint();
        this.f1902g = false;
        this.f1903h = 0;
        this.f1905j = 0;
        this.f1896a = 10;
        this.f1904i = aMapDelegateImp;
        try {
            InputStream open = C0330s.f2074g == C0329a.ALIBABA ? bh.m3592a(context).open("apl.data") : bh.m3592a(context).open("ap.data");
            this.f1899d = BitmapFactory.decodeStream(open);
            this.f1897b = bj.m3610a(this.f1899d, C0330s.f2068a);
            open.close();
            open = C0330s.f2074g == C0329a.ALIBABA ? bh.m3592a(context).open("apl1.data") : bh.m3592a(context).open("ap1.data");
            this.f1900e = BitmapFactory.decodeStream(open);
            this.f1898c = bj.m3610a(this.f1900e, C0330s.f2068a);
            open.close();
            this.f1903h = this.f1898c.getHeight();
            this.f1901f.setAntiAlias(true);
            this.f1901f.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.f1901f.setStyle(Style.STROKE);
        } catch (Throwable th) {
            ce.m3829a(th, "WaterMarkerView", "create");
            th.printStackTrace();
        }
    }

    public void m3214a() {
        try {
            if (this.f1897b != null) {
                this.f1897b.recycle();
            }
            if (this.f1898c != null) {
                this.f1898c.recycle();
            }
            this.f1897b = null;
            this.f1898c = null;
            if (this.f1899d != null) {
                this.f1899d.recycle();
                this.f1899d = null;
            }
            if (this.f1900e != null) {
                this.f1900e.recycle();
                this.f1900e = null;
            }
            this.f1901f = null;
        } catch (Throwable th) {
            ce.m3829a(th, "WaterMarkerView", "destory");
            th.printStackTrace();
        }
    }

    public void m3215a(int i) {
        this.f1905j = i;
    }

    public void m3216a(boolean z) {
        try {
            this.f1902g = z;
            if (z) {
                this.f1901f.setColor(-1);
            } else {
                this.f1901f.setColor(ViewCompat.MEASURED_STATE_MASK);
            }
            invalidate();
        } catch (Throwable th) {
            ce.m3829a(th, "WaterMarkerView", "changeBitmap");
            th.printStackTrace();
        }
    }

    public Bitmap m3217b() {
        return this.f1902g ? this.f1898c : this.f1897b;
    }

    public Point m3218c() {
        return new Point(this.f1896a, (getHeight() - this.f1903h) - 10);
    }

    public void onDraw(Canvas canvas) {
        try {
            if (this.f1898c != null) {
                int width = this.f1898c.getWidth();
                if (this.f1905j == 1) {
                    this.f1896a = (this.f1904i.m2536n() - width) / 2;
                } else if (this.f1905j == 2) {
                    this.f1896a = (this.f1904i.m2536n() - width) - 10;
                } else {
                    this.f1896a = 10;
                }
                if (C0330s.f2074g == C0329a.ALIBABA) {
                    canvas.drawBitmap(m3217b(), (float) (this.f1896a + 15), (float) ((getHeight() - this.f1903h) - 8), this.f1901f);
                } else {
                    canvas.drawBitmap(m3217b(), (float) this.f1896a, (float) ((getHeight() - this.f1903h) - 8), this.f1901f);
                }
            }
        } catch (Throwable th) {
            ce.m3829a(th, "WaterMarkerView", "onDraw");
            th.printStackTrace();
        }
    }
}
