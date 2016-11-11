package com.fimi.soul.view.myhorizontalseebar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class SeekBar extends AbsSeekBar {
    private C1684l f10899n;

    public SeekBar(Context context) {
        this(context, null);
    }

    public SeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842875);
    }

    public SeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    void m12880a() {
        super.m12875a();
        if (this.f10899n != null) {
            this.f10899n.m10890a(this);
        }
    }

    void m12881a(float f, boolean z) {
        super.m12876a(f, z);
        if (this.f10899n != null) {
            this.f10899n.m10891a(this, getProgress(), z);
        }
    }

    void m12882b() {
        super.m12878b();
        if (this.f10899n != null) {
            this.f10899n.m10892b(this);
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(SeekBar.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(SeekBar.class.getName());
    }

    public void setOnSeekBarChangeListener(C1684l c1684l) {
        this.f10899n = c1684l;
    }
}
