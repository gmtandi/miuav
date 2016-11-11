package it.sephiroth.android.library.p143a.p144a;

import android.annotation.TargetApi;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import it.sephiroth.android.library.widget.AbsHListView;

/* renamed from: it.sephiroth.android.library.a.a.b */
public class C2813b implements C2812a {
    private C2812a f14316a;
    private AbsHListView f14317b;

    public C2813b(AbsHListView absHListView) {
        this.f14317b = absHListView;
    }

    @TargetApi(11)
    public void m16005a(ActionMode actionMode, int i, long j, boolean z) {
        this.f14316a.m16004a(actionMode, i, j, z);
        if (this.f14317b.getCheckedItemCount() == 0) {
            actionMode.finish();
        }
    }

    public void m16006a(C2812a c2812a) {
        this.f14316a = c2812a;
    }

    public boolean m16007a() {
        return this.f14316a != null;
    }

    @TargetApi(11)
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.f14316a.onActionItemClicked(actionMode, menuItem);
    }

    @TargetApi(11)
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        if (!this.f14316a.onCreateActionMode(actionMode, menu)) {
            return false;
        }
        this.f14317b.setLongClickable(false);
        return true;
    }

    @TargetApi(11)
    public void onDestroyActionMode(ActionMode actionMode) {
        this.f14316a.onDestroyActionMode(actionMode);
        this.f14317b.f14387u = null;
        this.f14317b.m16121a();
        this.f14317b.aJ = true;
        this.f14317b.m16067A();
        this.f14317b.requestLayout();
        this.f14317b.setLongClickable(true);
    }

    @TargetApi(11)
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return this.f14316a.onPrepareActionMode(actionMode, menu);
    }
}
