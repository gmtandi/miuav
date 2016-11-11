package com.amap.api.mapcore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.amap.api.mapcore.util.bj;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.CameraPosition;
import com.autonavi.amap.mapcore.MapProjection;
import com.fimi.soul.view.photodraweeview.C2020f;

/* renamed from: com.amap.api.mapcore.r */
class C0328r extends LinearLayout {
    Bitmap f2058a;
    Bitmap f2059b;
    Bitmap f2060c;
    ImageView f2061d;
    ab f2062e;

    /* renamed from: com.amap.api.mapcore.r.1 */
    class C03271 implements OnTouchListener {
        final /* synthetic */ C0328r f2057a;

        C03271(C0328r c0328r) {
            this.f2057a = c0328r;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            try {
                if (this.f2057a.f2062e.m2226R()) {
                    if (motionEvent.getAction() == 0) {
                        this.f2057a.f2061d.setImageBitmap(this.f2057a.f2059b);
                    } else if (motionEvent.getAction() == 1) {
                        this.f2057a.f2061d.setImageBitmap(this.f2057a.f2058a);
                        CameraPosition r = this.f2057a.f2062e.m2319r();
                        this.f2057a.f2062e.m2285b(C0325p.m3296a(new CameraPosition(r.target, r.zoom, 0.0f, 0.0f)));
                    }
                }
            } catch (Throwable th) {
                ce.m3829a(th, "CompassView", "onTouch");
                th.printStackTrace();
            }
            return false;
        }
    }

    public C0328r(Context context) {
        super(context);
    }

    public C0328r(Context context, av avVar, ab abVar) {
        super(context);
        this.f2062e = abVar;
        try {
            this.f2060c = bj.m3609a(context, "maps_dav_compass_needle_large.png");
            this.f2059b = bj.m3610a(this.f2060c, C0330s.f2068a * 0.8f);
            this.f2060c = bj.m3610a(this.f2060c, C0330s.f2068a * 0.7f);
            this.f2058a = Bitmap.createBitmap(this.f2059b.getWidth(), this.f2059b.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(this.f2058a);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            canvas.drawBitmap(this.f2060c, ((float) (this.f2059b.getWidth() - this.f2060c.getWidth())) / 2.0f, ((float) (this.f2059b.getHeight() - this.f2060c.getHeight())) / 2.0f, paint);
            this.f2061d = new ImageView(context);
            this.f2061d.setScaleType(ScaleType.MATRIX);
            this.f2061d.setImageBitmap(this.f2058a);
            this.f2061d.setClickable(true);
            m3338b();
            this.f2061d.setOnTouchListener(new C03271(this));
            addView(this.f2061d);
        } catch (Throwable th) {
            ce.m3829a(th, "CompassView", "create");
            th.printStackTrace();
        }
    }

    public void m3336a() {
        try {
            removeAllViews();
            if (this.f2058a != null) {
                this.f2058a.recycle();
            }
            if (this.f2059b != null) {
                this.f2059b.recycle();
            }
            if (this.f2060c != null) {
                this.f2060c.recycle();
            }
            this.f2060c = null;
            this.f2058a = null;
            this.f2059b = null;
        } catch (Throwable th) {
            ce.m3829a(th, "CompassView", "destroy");
            th.printStackTrace();
        }
    }

    public void m3337a(boolean z) {
        if (z) {
            setVisibility(0);
            m3338b();
            return;
        }
        setVisibility(8);
    }

    public void m3338b() {
        try {
            MapProjection c = this.f2062e.m2289c();
            float mapAngle = c.getMapAngle();
            float cameraHeaderAngle = c.getCameraHeaderAngle();
            Matrix matrix = new Matrix();
            matrix.postRotate(-mapAngle, ((float) this.f2061d.getDrawable().getBounds().width()) / 2.0f, ((float) this.f2061d.getDrawable().getBounds().height()) / 2.0f);
            matrix.postScale(C2020f.f10933c, (float) Math.cos((((double) cameraHeaderAngle) * 3.141592653589793d) / 180.0d), ((float) this.f2061d.getDrawable().getBounds().width()) / 2.0f, ((float) this.f2061d.getDrawable().getBounds().height()) / 2.0f);
            this.f2061d.setImageMatrix(matrix);
        } catch (Throwable th) {
            ce.m3829a(th, "CompassView", "invalidateAngle");
            th.printStackTrace();
        }
    }
}
