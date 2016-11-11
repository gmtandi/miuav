package p147m.framework.ui.widget.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.BaseAdapter;
import android.widget.ListView;

/* renamed from: m.framework.ui.widget.pulltorefresh.GroupListView */
public class GroupListView extends ListView {
    public GroupListView(Context context) {
        super(context);
    }

    public GroupListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GroupListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setAdapter(C2877b c2877b) {
        BaseAdapter c2876a = new C2876a(this, c2877b);
        c2877b.m16592a(c2876a);
        super.setAdapter(c2876a);
    }
}
