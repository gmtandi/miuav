package p147m.framework.ui.widget.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: m.framework.ui.widget.pulltorefresh.PullToRefreshView */
public class PullToRefreshView extends RelativeLayout {
    private static final long f14640a = 1000;
    private C2882g f14641b;
    private View f14642c;
    private View f14643d;
    private int f14644e;
    private int f14645f;
    private float f14646g;
    private boolean f14647h;
    private boolean f14648i;
    private Runnable f14649j;
    private long f14650k;

    public PullToRefreshView(Context context) {
        super(context);
        m16573d();
    }

    public PullToRefreshView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m16573d();
    }

    public PullToRefreshView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m16573d();
    }

    private MotionEvent m16570a(MotionEvent motionEvent) {
        return MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), 3, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState());
    }

    private void m16573d() {
        this.f14649j = new C2891p(this);
    }

    private void m16574e() {
        this.f14650k = System.currentTimeMillis();
        this.f14647h = true;
        if (this.f14641b != null) {
            this.f14641b.m16610g();
        }
    }

    private void m16575f() {
        this.f14647h = false;
    }

    private void m16576g() {
        this.f14645f = 0;
        scrollTo(0, 0);
        if (this.f14641b != null) {
            this.f14641b.m16611h();
        }
    }

    private boolean m16577h() {
        return !this.f14648i && this.f14641b.m16609f();
    }

    public void m16578a() {
        long currentTimeMillis = System.currentTimeMillis() - this.f14650k;
        if (currentTimeMillis < f14640a) {
            postDelayed(this.f14649j, f14640a - currentTimeMillis);
        } else {
            post(this.f14649j);
        }
    }

    public void m16579a(boolean z) {
        this.f14645f = this.f14644e;
        scrollTo(0, -this.f14645f);
        if (z) {
            m16574e();
        }
    }

    public void m16580b() {
        this.f14648i = true;
    }

    public void m16581c() {
        this.f14648i = false;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.f14646g = motionEvent.getY();
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case Type.BYTE /*3*/:
                if (!this.f14647h) {
                    if (this.f14645f <= this.f14644e) {
                        if (this.f14645f != 0) {
                            m16576g();
                            if (this.f14641b != null) {
                                this.f14641b.m16604a(0);
                                break;
                            }
                        }
                    }
                    this.f14645f = this.f14644e;
                    scrollTo(0, -this.f14645f);
                    if (this.f14641b != null) {
                        this.f14641b.m16604a(100);
                    }
                    m16574e();
                    motionEvent = m16570a(motionEvent);
                    break;
                }
                this.f14645f = this.f14644e;
                scrollTo(0, -this.f14645f);
                break;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                float y = motionEvent.getY();
                if (this.f14647h || m16577h()) {
                    this.f14645f = (int) (((float) this.f14645f) + ((y - this.f14646g) / 2.0f));
                    if (this.f14645f > 0) {
                        scrollTo(0, -this.f14645f);
                        if (!(this.f14647h || this.f14641b == null)) {
                            this.f14641b.m16604a((this.f14645f * 100) / this.f14644e);
                        }
                        motionEvent = m16570a(motionEvent);
                    } else {
                        this.f14645f = 0;
                        scrollTo(0, 0);
                    }
                }
                this.f14646g = y;
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setAdapter(C2882g c2882g) {
        this.f14641b = c2882g;
        removeAllViews();
        this.f14643d = (View) c2882g.m16608e();
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(11, -1);
        layoutParams.addRule(10, -1);
        addView(this.f14643d, layoutParams);
        this.f14642c = c2882g.m16607d();
        this.f14642c.measure(0, 0);
        this.f14644e = this.f14642c.getMeasuredHeight();
        layoutParams = new RelativeLayout.LayoutParams(-2, this.f14644e);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(11, -1);
        layoutParams.addRule(10, -1);
        layoutParams.topMargin = -this.f14644e;
        addView(this.f14642c, layoutParams);
    }
}
