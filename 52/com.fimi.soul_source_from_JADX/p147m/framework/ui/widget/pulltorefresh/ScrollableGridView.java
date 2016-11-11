package p147m.framework.ui.widget.pulltorefresh;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.GridView;

/* renamed from: m.framework.ui.widget.pulltorefresh.ScrollableGridView */
public class ScrollableGridView extends GridView implements C2875q {
    private C2881f f14651a;
    private boolean f14652b;

    public ScrollableGridView(Context context) {
        super(context);
        m16582a(context);
    }

    public ScrollableGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m16582a(context);
    }

    public ScrollableGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m16582a(context);
    }

    private void m16582a(Context context) {
        setCacheColorHint(0);
        setSelector(new ColorDrawable());
        this.f14651a = new C2892r(this);
    }

    public boolean m16584a() {
        return this.f14652b;
    }

    protected int computeVerticalScrollOffset() {
        int computeVerticalScrollOffset = super.computeVerticalScrollOffset();
        if (this.f14651a != null) {
            this.f14651a.m16602a(this, 0, computeVerticalScrollOffset, 0, 0);
        }
        return computeVerticalScrollOffset;
    }
}
