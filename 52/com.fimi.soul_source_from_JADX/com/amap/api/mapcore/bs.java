package com.amap.api.mapcore;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.mapcore.az.C0302a;
import com.amap.api.mapcore.util.bj;
import com.amap.api.mapcore.util.ce;

class bs extends LinearLayout {
    private Bitmap f1908a;
    private Bitmap f1909b;
    private Bitmap f1910c;
    private Bitmap f1911d;
    private Bitmap f1912e;
    private Bitmap f1913f;
    private Bitmap f1914g;
    private Bitmap f1915h;
    private Bitmap f1916i;
    private Bitmap f1917j;
    private Bitmap f1918k;
    private Bitmap f1919l;
    private ImageView f1920m;
    private ImageView f1921n;
    private ab f1922o;

    /* renamed from: com.amap.api.mapcore.bs.1 */
    class C03081 implements OnTouchListener {
        final /* synthetic */ bs f1906a;

        C03081(bs bsVar) {
            this.f1906a = bsVar;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.f1906a.f1922o.m2217F() < this.f1906a.f1922o.m2320s() && this.f1906a.f1922o.m2226R()) {
                if (motionEvent.getAction() == 0) {
                    this.f1906a.f1920m.setImageBitmap(this.f1906a.f1912e);
                } else if (motionEvent.getAction() == 1) {
                    this.f1906a.f1920m.setImageBitmap(this.f1906a.f1908a);
                    try {
                        this.f1906a.f1922o.m2285b(C0325p.m3304b());
                    } catch (Throwable e) {
                        ce.m3829a(e, "ZoomControllerView", "zoomin ontouch");
                        e.printStackTrace();
                    }
                }
            }
            return false;
        }
    }

    /* renamed from: com.amap.api.mapcore.bs.2 */
    class C03092 implements OnTouchListener {
        final /* synthetic */ bs f1907a;

        C03092(bs bsVar) {
            this.f1907a = bsVar;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.f1907a.f1922o.m2217F() > this.f1907a.f1922o.m2321t() && this.f1907a.f1922o.m2226R()) {
                if (motionEvent.getAction() == 0) {
                    this.f1907a.f1921n.setImageBitmap(this.f1907a.f1913f);
                } else if (motionEvent.getAction() == 1) {
                    this.f1907a.f1921n.setImageBitmap(this.f1907a.f1910c);
                    try {
                        this.f1907a.f1922o.m2285b(C0325p.m3306c());
                    } catch (Throwable e) {
                        ce.m3829a(e, "ZoomControllerView", "zoomout ontouch");
                        e.printStackTrace();
                    }
                }
            }
            return false;
        }
    }

    public bs(Context context) {
        super(context);
    }

    public bs(Context context, ab abVar) {
        super(context);
        this.f1922o = abVar;
        try {
            this.f1914g = bj.m3609a(context, "zoomin_selected.png");
            this.f1908a = bj.m3610a(this.f1914g, C0330s.f2068a);
            this.f1915h = bj.m3609a(context, "zoomin_unselected.png");
            this.f1909b = bj.m3610a(this.f1915h, C0330s.f2068a);
            this.f1916i = bj.m3609a(context, "zoomout_selected.png");
            this.f1910c = bj.m3610a(this.f1916i, C0330s.f2068a);
            this.f1917j = bj.m3609a(context, "zoomout_unselected.png");
            this.f1911d = bj.m3610a(this.f1917j, C0330s.f2068a);
            this.f1918k = bj.m3609a(context, "zoomin_pressed.png");
            this.f1912e = bj.m3610a(this.f1918k, C0330s.f2068a);
            this.f1919l = bj.m3609a(context, "zoomout_pressed.png");
            this.f1913f = bj.m3610a(this.f1919l, C0330s.f2068a);
            this.f1920m = new ImageView(context);
            this.f1920m.setImageBitmap(this.f1908a);
            this.f1920m.setClickable(true);
            this.f1921n = new ImageView(context);
            this.f1921n.setImageBitmap(this.f1910c);
            this.f1921n.setClickable(true);
            this.f1920m.setOnTouchListener(new C03081(this));
            this.f1921n.setOnTouchListener(new C03092(this));
            this.f1920m.setPadding(0, 0, 20, -2);
            this.f1921n.setPadding(0, 0, 20, 20);
            setOrientation(1);
            addView(this.f1920m);
            addView(this.f1921n);
        } catch (Throwable th) {
            ce.m3829a(th, "ZoomControllerView", "create");
            th.printStackTrace();
        }
    }

    public void m3226a() {
        try {
            removeAllViews();
            this.f1908a.recycle();
            this.f1909b.recycle();
            this.f1910c.recycle();
            this.f1911d.recycle();
            this.f1912e.recycle();
            this.f1913f.recycle();
            this.f1908a = null;
            this.f1909b = null;
            this.f1910c = null;
            this.f1911d = null;
            this.f1912e = null;
            this.f1913f = null;
            if (this.f1914g != null) {
                this.f1914g.recycle();
                this.f1914g = null;
            }
            if (this.f1915h != null) {
                this.f1915h.recycle();
                this.f1915h = null;
            }
            if (this.f1916i != null) {
                this.f1916i.recycle();
                this.f1916i = null;
            }
            if (this.f1917j != null) {
                this.f1917j.recycle();
                this.f1914g = null;
            }
            if (this.f1918k != null) {
                this.f1918k.recycle();
                this.f1918k = null;
            }
            if (this.f1919l != null) {
                this.f1919l.recycle();
                this.f1919l = null;
            }
            this.f1920m = null;
            this.f1921n = null;
        } catch (Throwable th) {
            ce.m3829a(th, "ZoomControllerView", "destory");
            th.printStackTrace();
        }
    }

    public void m3227a(float f) {
        try {
            if (f < this.f1922o.m2320s() && f > this.f1922o.m2321t()) {
                this.f1920m.setImageBitmap(this.f1908a);
                this.f1921n.setImageBitmap(this.f1910c);
            } else if (f == this.f1922o.m2321t()) {
                this.f1921n.setImageBitmap(this.f1911d);
                this.f1920m.setImageBitmap(this.f1908a);
            } else if (f == this.f1922o.m2320s()) {
                this.f1920m.setImageBitmap(this.f1909b);
                this.f1921n.setImageBitmap(this.f1910c);
            }
        } catch (Throwable th) {
            ce.m3829a(th, "ZoomControllerView", "setZoomBitmap");
            th.printStackTrace();
        }
    }

    public void m3228a(int i) {
        try {
            C0302a c0302a = (C0302a) getLayoutParams();
            if (i == 1) {
                c0302a.f1647d = 16;
            } else if (i == 2) {
                c0302a.f1647d = 80;
            }
            setLayoutParams(c0302a);
        } catch (Throwable th) {
            ce.m3829a(th, "ZoomControllerView", "setZoomPosition");
            th.printStackTrace();
        }
    }

    public void m3229a(boolean z) {
        if (z) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }
}
