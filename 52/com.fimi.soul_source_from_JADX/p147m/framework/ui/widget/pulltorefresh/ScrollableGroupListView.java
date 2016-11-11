package p147m.framework.ui.widget.pulltorefresh;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;

/* renamed from: m.framework.ui.widget.pulltorefresh.ScrollableGroupListView */
public class ScrollableGroupListView extends GroupListView implements C2875q {
    private C2881f f14653a;
    private boolean f14654b;

    public ScrollableGroupListView(Context context) {
        super(context);
        m16585a(context);
    }

    public ScrollableGroupListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m16585a(context);
    }

    public ScrollableGroupListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m16585a(context);
    }

    private void m16585a(Context context) {
        setCacheColorHint(0);
        setSelector(new ColorDrawable());
        this.f14653a = new C2893s(this);
    }

    public boolean m16587a() {
        return this.f14654b;
    }

    protected int computeVerticalScrollOffset() {
        int computeVerticalScrollOffset = super.computeVerticalScrollOffset();
        if (this.f14653a != null) {
            this.f14653a.m16602a(this, 0, computeVerticalScrollOffset, 0, 0);
        }
        return computeVerticalScrollOffset;
    }
}
