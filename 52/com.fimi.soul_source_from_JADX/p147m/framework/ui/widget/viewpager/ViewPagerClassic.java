package p147m.framework.ui.widget.viewpager;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import p147m.framework.p149b.C2863g;

/* renamed from: m.framework.ui.widget.viewpager.ViewPagerClassic */
public class ViewPagerClassic extends ViewGroup {
    private static final int f14741a = 500;
    private static final int f14742g = 0;
    private static final int f14743h = 1;
    private int f14744b;
    private Scroller f14745c;
    private VelocityTracker f14746d;
    private float f14747e;
    private float f14748f;
    private int f14749i;
    private int f14750j;
    private int f14751k;
    private C2909a f14752l;

    public ViewPagerClassic(Context context) {
        this(context, null);
    }

    public ViewPagerClassic(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f14742g);
    }

    public ViewPagerClassic(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f14749i = f14742g;
        m16710a(context);
    }

    private void m16709a(int i, boolean z) {
        int i2 = i != this.f14744b ? f14743h : f14742g;
        View focusedChild = getFocusedChild();
        if (!(focusedChild == null || i2 == 0 || focusedChild != getChildAt(this.f14744b))) {
            focusedChild.clearFocus();
        }
        int width = (getWidth() * i) - getScrollX();
        this.f14745c.startScroll(getScrollX(), f14742g, width, f14742g, z ? f14742g : Math.abs(width) / 2);
        invalidate();
    }

    private void m16710a(Context context) {
        this.f14745c = new Scroller(getContext(), new C2910b(this));
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f14750j = viewConfiguration.getScaledTouchSlop();
        this.f14751k = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    private void m16711a(MotionEvent motionEvent) {
        int i = f14742g;
        float x = motionEvent.getX();
        int abs = (int) Math.abs(motionEvent.getY() - this.f14748f);
        int i2 = ((int) Math.abs(x - this.f14747e)) > this.f14750j ? f14743h : f14742g;
        if (abs > this.f14750j) {
            i = f14743h;
        }
        if ((i2 != 0 || r2 != 0) && i2 != 0) {
            this.f14749i = f14743h;
            this.f14747e = x;
        }
    }

    private void m16712b(MotionEvent motionEvent) {
        if (this.f14752l != null) {
            float x = motionEvent.getX();
            int i = (int) (this.f14747e - x);
            this.f14747e = x;
            if (i < 0) {
                if (getScrollX() > 0) {
                    scrollBy(Math.max(-getScrollX(), i), f14742g);
                }
            } else if (i > 0 && getChildCount() != 0) {
                int right = (getChildAt(getChildCount() - 1).getRight() - getScrollX()) - getWidth();
                if (right > 0) {
                    scrollBy(Math.min(right, i), f14742g);
                }
            }
        }
    }

    public void m16713a() {
        if (this.f14752l != null && this.f14744b > 0 && this.f14745c.isFinished()) {
            m16714a(this.f14744b - 1);
        }
    }

    public void m16714a(int i) {
        m16709a(i, false);
    }

    public void m16715b() {
        if (this.f14752l != null && this.f14744b < getChildCount() - 1 && this.f14745c.isFinished()) {
            m16714a(this.f14744b + f14743h);
        }
    }

    public void computeScroll() {
        if (this.f14752l != null) {
            if (this.f14745c.computeScrollOffset()) {
                scrollTo(this.f14745c.getCurrX(), this.f14745c.getCurrY());
                postInvalidate();
                return;
            }
            int i = this.f14744b;
            int currX = this.f14745c.getCurrX();
            int width = getWidth();
            int i2 = currX / width;
            if (currX % width > width / 2) {
                i2 += f14743h;
            }
            this.f14744b = Math.max(f14742g, Math.min(i2, getChildCount() - 1));
            if (i != this.f14744b && this.f14752l != null) {
                this.f14752l.m16718a(this.f14744b, i);
            }
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        if (this.f14752l != null && getChildCount() > 0) {
            long drawingTime = getDrawingTime();
            if (this.f14744b > 0) {
                drawChild(canvas, getChildAt(this.f14744b - 1), drawingTime);
            }
            drawChild(canvas, getChildAt(this.f14744b), drawingTime);
            if (this.f14744b < getChildCount() - 1) {
                drawChild(canvas, getChildAt(this.f14744b + f14743h), drawingTime);
            }
        }
    }

    public boolean dispatchUnhandledMove(View view, int i) {
        if (this.f14752l == null) {
            return super.dispatchUnhandledMove(view, i);
        }
        if (i == 17) {
            if (this.f14744b > 0) {
                m16714a(this.f14744b - 1);
                return true;
            }
        } else if (i == 66 && this.f14744b < getChildCount() - 1) {
            m16714a(this.f14744b + f14743h);
            return true;
        }
        return super.dispatchUnhandledMove(view, i);
    }

    public int getCurrentScreen() {
        return this.f14744b;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 2 && this.f14749i != 0) {
            return true;
        }
        if (this.f14746d == null) {
            this.f14746d = VelocityTracker.obtain();
        }
        this.f14746d.addMovement(motionEvent);
        switch (action) {
            case f14742g /*0*/:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.f14747e = x;
                this.f14748f = y;
                this.f14749i = this.f14745c.isFinished() ? f14742g : f14743h;
                break;
            case f14743h /*1*/:
            case Type.BYTE /*3*/:
                if (this.f14746d != null) {
                    this.f14746d.recycle();
                    this.f14746d = null;
                }
                this.f14749i = f14742g;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                m16711a(motionEvent);
                break;
        }
        return this.f14749i != 0;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f14752l != null) {
            int i5 = i3 - i;
            int i6 = i4 - i2;
            int childCount = getChildCount();
            int i7 = f14742g;
            for (int i8 = f14742g; i8 < childCount; i8 += f14743h) {
                View childAt = getChildAt(i8);
                if (childAt.getVisibility() != 8) {
                    childAt.layout(i7, f14742g, i7 + i5, i6);
                    i7 += i5;
                }
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        if (this.f14752l == null) {
            super.onMeasure(i, i2);
            return;
        }
        int childCount = getChildCount();
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(C2863g.m16519a(getContext()), 1073741824);
        int i3 = f14742g;
        int i4 = f14742g;
        while (i3 < childCount) {
            View childAt = getChildAt(i3);
            childAt.measure(makeMeasureSpec, f14742g);
            int measuredHeight = childAt.getMeasuredHeight();
            if (measuredHeight <= i4) {
                measuredHeight = i4;
            }
            i3 += f14743h;
            i4 = measuredHeight;
        }
        i4 = MeasureSpec.makeMeasureSpec(i4, 1073741824);
        super.onMeasure(makeMeasureSpec, i4);
        for (measuredHeight = f14742g; measuredHeight < childCount; measuredHeight += f14743h) {
            getChildAt(measuredHeight).measure(makeMeasureSpec, i4);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f14752l == null) {
            return false;
        }
        if (this.f14746d == null) {
            this.f14746d = VelocityTracker.obtain();
        }
        this.f14746d.addMovement(motionEvent);
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        switch (action) {
            case f14742g /*0*/:
                if (this.f14749i != 0) {
                    if (!this.f14745c.isFinished()) {
                        this.f14745c.abortAnimation();
                    }
                    this.f14747e = x;
                    break;
                }
                break;
            case f14743h /*1*/:
                if (this.f14749i == f14743h) {
                    VelocityTracker velocityTracker = this.f14746d;
                    velocityTracker.computeCurrentVelocity(XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER, (float) this.f14751k);
                    action = (int) velocityTracker.getXVelocity();
                    if (action > f14741a && this.f14744b > 0) {
                        m16714a(this.f14744b - 1);
                    } else if (action >= -500 || this.f14744b >= getChildCount() - 1) {
                        action = getWidth();
                        m16714a((getScrollX() + (action / 2)) / action);
                    } else {
                        m16714a(this.f14744b + f14743h);
                    }
                    if (this.f14746d != null) {
                        this.f14746d.recycle();
                        this.f14746d = null;
                    }
                }
                this.f14749i = f14742g;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (this.f14749i != f14743h) {
                    if (onInterceptTouchEvent(motionEvent) && this.f14749i == f14743h) {
                        m16712b(motionEvent);
                        break;
                    }
                }
                m16712b(motionEvent);
                break;
            case Type.BYTE /*3*/:
                this.f14749i = f14742g;
                break;
        }
        return true;
    }

    public void setAdapter(C2909a c2909a) {
        int i = f14742g;
        this.f14752l = c2909a;
        removeAllViews();
        this.f14744b = f14742g;
        if (this.f14752l != null) {
            int a = c2909a.m16716a();
            while (i < a) {
                addView(c2909a.m16717a(i, (ViewGroup) this));
                i += f14743h;
            }
        }
    }

    public void setCurrentScreen(int i) {
        if (this.f14752l != null) {
            if (!this.f14745c.isFinished()) {
                this.f14745c.abortAnimation();
            }
            int i2 = this.f14744b;
            this.f14744b = Math.max(f14742g, Math.min(i, getChildCount()));
            this.f14752l.m16718a(this.f14744b, i2);
            i2 = C2863g.m16519a(getContext()) * this.f14744b;
            this.f14745c.startScroll(f14742g, f14742g, i2, f14742g);
            scrollTo(i2, f14742g);
        }
    }
}
