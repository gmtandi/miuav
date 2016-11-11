package p147m.framework.ui.widget.pulltorefresh;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.ListView;

/* renamed from: m.framework.ui.widget.pulltorefresh.ScrollableListView */
public class ScrollableListView extends ListView implements C2875q {
    private C2881f f14655a;
    private boolean f14656b;

    public ScrollableListView(Context context) {
        super(context);
        m16588a(context);
    }

    public ScrollableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m16588a(context);
    }

    public ScrollableListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m16588a(context);
    }

    private void m16588a(Context context) {
        setCacheColorHint(0);
        setSelector(new ColorDrawable());
        this.f14655a = new C2894t(this);
    }

    public boolean m16590a() {
        return this.f14656b;
    }

    protected int computeVerticalScrollOffset() {
        int computeVerticalScrollOffset = super.computeVerticalScrollOffset();
        if (this.f14655a != null) {
            this.f14655a.m16602a(this, 0, computeVerticalScrollOffset, 0, 0);
        }
        return computeVerticalScrollOffset;
    }
}
