package p147m.framework.ui.widget.slidingmenu;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.HorizontalScrollView;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: m.framework.ui.widget.slidingmenu.a */
public class C2895a extends HorizontalScrollView {
    private static final int f14707a = 500;
    private static final int f14708b = 230;
    private float f14709c;
    private SlidingMenu f14710d;
    private int f14711e;
    private VelocityTracker f14712f;

    public C2895a(SlidingMenu slidingMenu) {
        super(slidingMenu.getContext());
        this.f14709c = 2.14748365E9f;
        this.f14710d = slidingMenu;
        this.f14711e = ViewConfiguration.get(slidingMenu.getContext()).getScaledMaximumFlingVelocity();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.f14709c = motionEvent.getX();
                if (this.f14710d.m16686e() && this.f14709c > ((float) this.f14710d.getMenuWidth()) && motionEvent.getY() > ((float) this.f14710d.getMenuConfig().f14722i)) {
                    super.onInterceptTouchEvent(motionEvent);
                    return true;
                }
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case Type.BYTE /*3*/:
                this.f14709c = 2.14748365E9f;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (!this.f14710d.m16686e() && this.f14709c > ((float) this.f14710d.getShowMenuWidth())) {
                    super.onInterceptTouchEvent(motionEvent);
                    return false;
                }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        this.f14710d.getMenuCover().setBackgroundColor(Color.argb((i * f14708b) / this.f14710d.getMenuWidth(), 0, 0, 0));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f14712f == null) {
            this.f14712f = VelocityTracker.obtain();
        }
        this.f14712f.addMovement(motionEvent);
        switch (motionEvent.getAction()) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case Type.BYTE /*3*/:
                if (this.f14710d.m16686e() && this.f14709c < ((float) this.f14710d.getMenuWidth())) {
                    return false;
                }
                this.f14709c = 2.14748365E9f;
                this.f14712f.computeCurrentVelocity(XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER, (float) this.f14711e);
                float xVelocity = this.f14712f.getXVelocity();
                this.f14712f.recycle();
                this.f14712f = null;
                int scrollX = getScrollX();
                if (xVelocity - 500.0f > 0.0f) {
                    this.f14710d.m16684c();
                } else if (xVelocity + 500.0f < 0.0f) {
                    this.f14710d.m16685d();
                } else if (scrollX > this.f14710d.getMenuWidth() / 2) {
                    this.f14710d.m16685d();
                } else {
                    this.f14710d.m16684c();
                }
                return true;
            default:
                return (!this.f14710d.m16686e() || this.f14709c >= ((float) this.f14710d.getMenuWidth())) ? super.onTouchEvent(motionEvent) : false;
        }
    }
}
