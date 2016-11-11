package com.amap.api.mapcore;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.mapcore.util.bj;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.LatLng;

class as extends LinearLayout {
    Bitmap f1610a;
    Bitmap f1611b;
    Bitmap f1612c;
    Bitmap f1613d;
    Bitmap f1614e;
    Bitmap f1615f;
    ImageView f1616g;
    ab f1617h;
    boolean f1618i;

    /* renamed from: com.amap.api.mapcore.as.1 */
    class C03001 implements OnTouchListener {
        final /* synthetic */ as f1609a;

        C03001(as asVar) {
            this.f1609a = asVar;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.f1609a.f1618i) {
                if (motionEvent.getAction() == 0) {
                    this.f1609a.f1616g.setImageBitmap(this.f1609a.f1611b);
                } else if (motionEvent.getAction() == 1) {
                    try {
                        this.f1609a.f1616g.setImageBitmap(this.f1609a.f1610a);
                        this.f1609a.f1617h.m2314m(true);
                        Location z = this.f1609a.f1617h.m2327z();
                        if (z != null) {
                            LatLng latLng = new LatLng(z.getLatitude(), z.getLongitude());
                            this.f1609a.f1617h.m2251a(z);
                            this.f1609a.f1617h.m2253a(C0325p.m3298a(latLng, this.f1609a.f1617h.m2217F()));
                        }
                    } catch (Throwable th) {
                        ce.m3829a(th, "LocationView", "onTouch");
                        th.printStackTrace();
                    }
                }
            }
            return false;
        }
    }

    public as(Context context) {
        super(context);
        this.f1618i = false;
    }

    public as(Context context, av avVar, ab abVar) {
        super(context);
        this.f1618i = false;
        this.f1617h = abVar;
        try {
            this.f1613d = bj.m3609a(context, "location_selected.png");
            this.f1610a = bj.m3610a(this.f1613d, C0330s.f2068a);
            this.f1614e = bj.m3609a(context, "location_pressed.png");
            this.f1611b = bj.m3610a(this.f1614e, C0330s.f2068a);
            this.f1615f = bj.m3609a(context, "location_unselected.png");
            this.f1612c = bj.m3610a(this.f1615f, C0330s.f2068a);
            this.f1616g = new ImageView(context);
            this.f1616g.setImageBitmap(this.f1610a);
            this.f1616g.setClickable(true);
            this.f1616g.setPadding(0, 20, 20, 0);
            this.f1616g.setOnTouchListener(new C03001(this));
            addView(this.f1616g);
        } catch (Throwable th) {
            ce.m3829a(th, "LocationView", "create");
            th.printStackTrace();
        }
    }

    public void m2778a() {
        try {
            removeAllViews();
            if (this.f1610a != null) {
                this.f1610a.recycle();
            }
            if (this.f1611b != null) {
                this.f1611b.recycle();
            }
            if (this.f1611b != null) {
                this.f1612c.recycle();
            }
            this.f1610a = null;
            this.f1611b = null;
            this.f1612c = null;
            if (this.f1613d != null) {
                this.f1613d.recycle();
                this.f1613d = null;
            }
            if (this.f1614e != null) {
                this.f1614e.recycle();
                this.f1614e = null;
            }
            if (this.f1615f != null) {
                this.f1615f.recycle();
                this.f1615f = null;
            }
        } catch (Throwable th) {
            ce.m3829a(th, "LocationView", "destroy");
            th.printStackTrace();
        }
    }

    public void m2779a(boolean z) {
        this.f1618i = z;
        if (z) {
            try {
                this.f1616g.setImageBitmap(this.f1610a);
            } catch (Throwable th) {
                ce.m3829a(th, "LocationView", "showSelect");
                th.printStackTrace();
                return;
            }
        }
        this.f1616g.setImageBitmap(this.f1612c);
        this.f1616g.invalidate();
    }
}
