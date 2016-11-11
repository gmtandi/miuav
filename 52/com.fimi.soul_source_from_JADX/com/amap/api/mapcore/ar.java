package com.amap.api.mapcore;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.amap.api.mapcore.util.bh;
import com.fimi.kernel.p084e.C1186y;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.p122a.p123a.C2915a;

public class ar extends ScrollView {
    public static final String f1591a;
    int f1592b;
    private Context f1593c;
    private LinearLayout f1594d;
    private int f1595e;
    private List<String> f1596f;
    private int f1597g;
    private int f1598h;
    private Bitmap f1599i;
    private int f1600j;
    private int f1601k;
    private int f1602l;
    private int f1603m;
    private int f1604n;
    private int f1605o;
    private Runnable f1606p;
    private int f1607q;
    private C0286a f1608r;

    /* renamed from: com.amap.api.mapcore.ar.a */
    public interface C0286a {
        void m2205a(int i);
    }

    /* renamed from: com.amap.api.mapcore.ar.1 */
    class C02971 implements Runnable {
        final /* synthetic */ ar f1587a;

        /* renamed from: com.amap.api.mapcore.ar.1.1 */
        class C02951 implements Runnable {
            final /* synthetic */ int f1581a;
            final /* synthetic */ int f1582b;
            final /* synthetic */ C02971 f1583c;

            C02951(C02971 c02971, int i, int i2) {
                this.f1583c = c02971;
                this.f1581a = i;
                this.f1582b = i2;
            }

            public void run() {
                this.f1583c.f1587a.smoothScrollTo(0, (this.f1583c.f1587a.f1605o - this.f1581a) + this.f1583c.f1587a.f1595e);
                this.f1583c.f1587a.f1592b = (this.f1582b + this.f1583c.f1587a.f1603m) + 1;
                this.f1583c.f1587a.m2764g();
            }
        }

        /* renamed from: com.amap.api.mapcore.ar.1.2 */
        class C02962 implements Runnable {
            final /* synthetic */ int f1584a;
            final /* synthetic */ int f1585b;
            final /* synthetic */ C02971 f1586c;

            C02962(C02971 c02971, int i, int i2) {
                this.f1586c = c02971;
                this.f1584a = i;
                this.f1585b = i2;
            }

            public void run() {
                this.f1586c.f1587a.smoothScrollTo(0, this.f1586c.f1587a.f1605o - this.f1584a);
                this.f1586c.f1587a.f1592b = this.f1585b + this.f1586c.f1587a.f1603m;
                this.f1586c.f1587a.m2764g();
            }
        }

        C02971(ar arVar) {
            this.f1587a = arVar;
        }

        public void run() {
            if (this.f1587a.f1605o - this.f1587a.getScrollY() == 0) {
                int a = this.f1587a.f1605o % this.f1587a.f1595e;
                int a2 = this.f1587a.f1605o / this.f1587a.f1595e;
                if (a == 0) {
                    this.f1587a.f1592b = a2 + this.f1587a.f1603m;
                    this.f1587a.m2764g();
                    return;
                } else if (a > this.f1587a.f1595e / 2) {
                    this.f1587a.post(new C02951(this, a, a2));
                    return;
                } else {
                    this.f1587a.post(new C02962(this, a, a2));
                    return;
                }
            }
            this.f1587a.f1605o = this.f1587a.getScrollY();
            this.f1587a.postDelayed(this.f1587a.f1606p, (long) this.f1587a.f1607q);
        }
    }

    /* renamed from: com.amap.api.mapcore.ar.2 */
    class C02982 extends Drawable {
        final /* synthetic */ ar f1588a;

        C02982(ar arVar) {
            this.f1588a = arVar;
        }

        private void m2745a(Canvas canvas) {
            canvas.drawColor(this.f1588a.f1600j);
        }

        private void m2746b(Canvas canvas) {
            Paint paint = new Paint();
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            rect.left = 0;
            rect.top = 0;
            rect.right = this.f1588a.f1599i.getWidth() + 0;
            rect.bottom = this.f1588a.f1599i.getHeight() + 0;
            rect2.left = 0;
            rect2.top = this.f1588a.m2762f()[0];
            rect2.right = this.f1588a.f1598h + 0;
            rect2.bottom = this.f1588a.m2762f()[1];
            canvas.drawBitmap(this.f1588a.f1599i, rect, rect2, paint);
        }

        private void m2747c(Canvas canvas) {
            Paint paint = new Paint();
            Rect clipBounds = canvas.getClipBounds();
            paint.setColor(this.f1588a.f1601k);
            paint.setStyle(Style.STROKE);
            paint.setStrokeWidth((float) this.f1588a.f1602l);
            canvas.drawRect(clipBounds, paint);
        }

        public void draw(Canvas canvas) {
            try {
                m2745a(canvas);
                m2746b(canvas);
                m2747c(canvas);
            } catch (Throwable th) {
            }
        }

        public int getOpacity() {
            return 0;
        }

        public void setAlpha(int i) {
        }

        public void setColorFilter(ColorFilter colorFilter) {
        }
    }

    /* renamed from: com.amap.api.mapcore.ar.3 */
    class C02993 implements Runnable {
        final /* synthetic */ int f1589a;
        final /* synthetic */ ar f1590b;

        C02993(ar arVar, int i) {
            this.f1590b = arVar;
            this.f1589a = i;
        }

        public void run() {
            this.f1590b.smoothScrollTo(0, this.f1589a * this.f1590b.f1595e);
        }
    }

    static {
        f1591a = ar.class.getSimpleName();
    }

    public ar(Context context) {
        super(context);
        this.f1595e = 0;
        this.f1597g = -1;
        this.f1599i = null;
        this.f1600j = Color.parseColor("#eeffffff");
        this.f1601k = Color.parseColor("#44383838");
        this.f1602l = 4;
        this.f1603m = 1;
        this.f1592b = 1;
        this.f1607q = 50;
        m2753a(context);
    }

    public ar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1595e = 0;
        this.f1597g = -1;
        this.f1599i = null;
        this.f1600j = Color.parseColor("#eeffffff");
        this.f1601k = Color.parseColor("#44383838");
        this.f1602l = 4;
        this.f1603m = 1;
        this.f1592b = 1;
        this.f1607q = 50;
        m2753a(context);
    }

    public ar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1595e = 0;
        this.f1597g = -1;
        this.f1599i = null;
        this.f1600j = Color.parseColor("#eeffffff");
        this.f1601k = Color.parseColor("#44383838");
        this.f1602l = 4;
        this.f1603m = 1;
        this.f1592b = 1;
        this.f1607q = 50;
        m2753a(context);
    }

    public static int m2748a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static int m2749a(View view) {
        m2756b(view);
        return view.getMeasuredHeight();
    }

    private void m2752a(int i) {
        int i2 = i % this.f1595e;
        int i3 = i / this.f1595e;
        i2 = i2 == 0 ? this.f1603m + i3 : i2 > this.f1595e / 2 ? (this.f1603m + i3) + 1 : (i / this.f1595e) + this.f1603m;
        int childCount = this.f1594d.getChildCount();
        i3 = 0;
        while (i3 < childCount) {
            TextView textView = (TextView) this.f1594d.getChildAt(i3);
            if (textView != null) {
                if (i2 == i3) {
                    textView.setTextColor(Color.parseColor("#0288ce"));
                } else {
                    textView.setTextColor(Color.parseColor("#bbbbbb"));
                }
                i3++;
            } else {
                return;
            }
        }
    }

    private void m2753a(Context context) {
        this.f1593c = context;
        setVerticalScrollBarEnabled(false);
        try {
            if (this.f1599i == null) {
                InputStream open = bh.m3592a(context).open("map_indoor_select.png");
                this.f1599i = BitmapFactory.decodeStream(open);
                open.close();
            }
        } catch (Throwable th) {
        }
        this.f1594d = new LinearLayout(context);
        this.f1594d.setOrientation(1);
        addView(this.f1594d);
        this.f1606p = new C02971(this);
    }

    private TextView m2755b(String str) {
        View textView = new TextView(this.f1593c);
        textView.setLayoutParams(new LayoutParams(-1, -2));
        textView.setSingleLine(true);
        textView.setTextSize(2, 16.0f);
        textView.setText(str);
        textView.setGravity(17);
        textView.getPaint().setFakeBoldText(true);
        int a = m2748a(this.f1593c, 8.0f);
        int a2 = m2748a(this.f1593c, 6.0f);
        textView.setPadding(a, a2, a, a2);
        if (this.f1595e == 0) {
            this.f1595e = m2749a(textView);
            this.f1594d.setLayoutParams(new LayoutParams(-2, this.f1595e * this.f1604n));
            setLayoutParams(new LinearLayout.LayoutParams(-2, this.f1595e * this.f1604n));
        }
        return textView;
    }

    public static void m2756b(View view) {
        view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(536870911, C1186y.f5353a));
    }

    private void m2760e() {
        if (this.f1596f != null && this.f1596f.size() != 0) {
            this.f1594d.removeAllViews();
            this.f1604n = (this.f1603m * 2) + 1;
            for (int size = this.f1596f.size() - 1; size >= 0; size--) {
                this.f1594d.addView(m2755b((String) this.f1596f.get(size)));
            }
            m2752a(0);
        }
    }

    private int[] m2762f() {
        if (null != null) {
            return null;
        }
        return new int[]{this.f1595e * this.f1603m, this.f1595e * (this.f1603m + 1)};
    }

    private void m2764g() {
        if (this.f1608r != null) {
            try {
                this.f1608r.m2205a(m2776c());
            } catch (Throwable th) {
            }
        }
    }

    public void m2770a() {
        this.f1605o = getScrollY();
        postDelayed(this.f1606p, (long) this.f1607q);
    }

    public void m2771a(C0286a c0286a) {
        this.f1608r = c0286a;
    }

    public void m2772a(String str) {
        if (this.f1596f != null && this.f1596f.size() != 0) {
            int size = ((this.f1596f.size() - this.f1603m) - 1) - this.f1596f.indexOf(str);
            this.f1592b = this.f1603m + size;
            post(new C02993(this, size));
        }
    }

    public void m2773a(boolean z) {
        if (z) {
            if (!m2777d()) {
                setVisibility(0);
            }
        } else if (m2777d()) {
            setVisibility(8);
        }
    }

    public void m2774a(String[] strArr) {
        int i;
        if (this.f1596f == null) {
            this.f1596f = new ArrayList();
        }
        this.f1596f.clear();
        for (Object add : strArr) {
            this.f1596f.add(add);
        }
        for (i = 0; i < this.f1603m; i++) {
            this.f1596f.add(0, C2915a.f14760f);
            this.f1596f.add(C2915a.f14760f);
        }
        m2760e();
    }

    public void m2775b() {
        if (this.f1599i != null && !this.f1599i.isRecycled()) {
            this.f1599i.recycle();
            this.f1599i = null;
        }
    }

    public int m2776c() {
        if (this.f1596f == null || this.f1596f.size() == 0) {
            return 0;
        }
        return Math.min(this.f1596f.size() - (this.f1603m * 2), Math.max(0, ((this.f1596f.size() - 1) - this.f1592b) - this.f1603m));
    }

    public boolean m2777d() {
        return getVisibility() == 0;
    }

    public void fling(int i) {
        super.fling(i / 3);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        m2752a(i2);
        if (i2 > i4) {
            this.f1597g = 1;
        } else {
            this.f1597g = 0;
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f1598h = i;
        setBackgroundDrawable(null);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            m2770a();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setBackgroundColor(int i) {
        this.f1600j = i;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (this.f1598h == 0) {
            this.f1598h = ((Activity) this.f1593c).getWindowManager().getDefaultDisplay().getWidth();
        }
        super.setBackgroundDrawable(new C02982(this));
    }
}
