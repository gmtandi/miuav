package it.sephiroth.android.library.widget;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.widget.ListAdapter;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;

@TargetApi(14)
/* renamed from: it.sephiroth.android.library.widget.i */
class C2830i extends AccessibilityDelegateCompat {
    final /* synthetic */ AbsHListView f14544a;

    C2830i(AbsHListView absHListView) {
        this.f14544a = absHListView;
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        int a = this.f14544a.m16068a(view);
        ListAdapter listAdapter = (ListAdapter) this.f14544a.getAdapter();
        if (a != -1 && listAdapter != null && this.f14544a.isEnabled() && listAdapter.isEnabled(a)) {
            if (a == this.f14544a.getSelectedItemPosition()) {
                accessibilityNodeInfoCompat.setSelected(true);
                accessibilityNodeInfoCompat.addAction(8);
            } else {
                accessibilityNodeInfoCompat.addAction(4);
            }
            if (this.f14544a.isClickable()) {
                accessibilityNodeInfoCompat.addAction(16);
                accessibilityNodeInfoCompat.setClickable(true);
            }
            if (this.f14544a.isLongClickable()) {
                accessibilityNodeInfoCompat.addAction(32);
                accessibilityNodeInfoCompat.setLongClickable(true);
            }
        }
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (super.performAccessibilityAction(view, i, bundle)) {
            return true;
        }
        int a = this.f14544a.m16068a(view);
        ListAdapter listAdapter = (ListAdapter) this.f14544a.getAdapter();
        if (a == -1 || listAdapter == null) {
            return false;
        }
        if (!this.f14544a.isEnabled() || !listAdapter.isEnabled(a)) {
            return false;
        }
        long i2 = this.f14544a.m16072i(a);
        switch (i) {
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                if (this.f14544a.getSelectedItemPosition() == a) {
                    return false;
                }
                this.f14544a.setSelection(a);
                return true;
            case Type.DOUBLE /*8*/:
                if (this.f14544a.getSelectedItemPosition() != a) {
                    return false;
                }
                this.f14544a.setSelection(-1);
                return true;
            case Segment.TOKENS_PER_SEGMENT /*16*/:
                return this.f14544a.isClickable() ? this.f14544a.m16132a(view, a, i2) : false;
            case Opcodes.ACC_SYNCHRONIZED /*32*/:
                return this.f14544a.isLongClickable() ? this.f14544a.m16140c(view, a, i2) : false;
            default:
                return false;
        }
    }
}
