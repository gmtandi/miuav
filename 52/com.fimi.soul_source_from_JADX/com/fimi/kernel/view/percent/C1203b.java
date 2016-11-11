package com.fimi.kernel.view.percent;

import android.support.v4.view.MarginLayoutParamsCompat;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

/* renamed from: com.fimi.kernel.view.percent.b */
public class C1203b {
    public C1204c f5415a;
    public C1204c f5416b;
    public C1204c f5417c;
    public C1204c f5418d;
    public C1204c f5419e;
    public C1204c f5420f;
    public C1204c f5421g;
    public C1204c f5422h;
    public C1204c f5423i;
    public C1204c f5424j;
    public C1204c f5425k;
    public C1204c f5426l;
    public C1204c f5427m;
    public C1204c f5428n;
    public C1204c f5429o;
    public C1204c f5430p;
    public C1204c f5431q;
    final MarginLayoutParams f5432r;

    public C1203b() {
        this.f5432r = new MarginLayoutParams(0, 0);
    }

    public void m8420a(LayoutParams layoutParams) {
        layoutParams.width = this.f5432r.width;
        layoutParams.height = this.f5432r.height;
    }

    public void m8421a(LayoutParams layoutParams, int i, int i2) {
        this.f5432r.width = layoutParams.width;
        this.f5432r.height = layoutParams.height;
        if (this.f5415a != null) {
            layoutParams.width = (int) (((float) (this.f5415a.f5434b ? i : i2)) * this.f5415a.f5433a);
        }
        if (this.f5416b != null) {
            if (!this.f5416b.f5434b) {
                i = i2;
            }
            layoutParams.height = (int) (((float) i) * this.f5416b.f5433a);
        }
    }

    public void m8422a(MarginLayoutParams marginLayoutParams) {
        m8420a((LayoutParams) marginLayoutParams);
        marginLayoutParams.leftMargin = this.f5432r.leftMargin;
        marginLayoutParams.topMargin = this.f5432r.topMargin;
        marginLayoutParams.rightMargin = this.f5432r.rightMargin;
        marginLayoutParams.bottomMargin = this.f5432r.bottomMargin;
        MarginLayoutParamsCompat.setMarginStart(marginLayoutParams, MarginLayoutParamsCompat.getMarginStart(this.f5432r));
        MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams, MarginLayoutParamsCompat.getMarginEnd(this.f5432r));
    }

    public void m8423a(MarginLayoutParams marginLayoutParams, int i, int i2) {
        m8421a((LayoutParams) marginLayoutParams, i, i2);
        this.f5432r.leftMargin = marginLayoutParams.leftMargin;
        this.f5432r.topMargin = marginLayoutParams.topMargin;
        this.f5432r.rightMargin = marginLayoutParams.rightMargin;
        this.f5432r.bottomMargin = marginLayoutParams.bottomMargin;
        MarginLayoutParamsCompat.setMarginStart(this.f5432r, MarginLayoutParamsCompat.getMarginStart(marginLayoutParams));
        MarginLayoutParamsCompat.setMarginEnd(this.f5432r, MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams));
        if (this.f5417c != null) {
            marginLayoutParams.leftMargin = (int) (((float) (this.f5417c.f5434b ? i : i2)) * this.f5417c.f5433a);
        }
        if (this.f5418d != null) {
            marginLayoutParams.topMargin = (int) (((float) (this.f5418d.f5434b ? i : i2)) * this.f5418d.f5433a);
        }
        if (this.f5419e != null) {
            marginLayoutParams.rightMargin = (int) (((float) (this.f5419e.f5434b ? i : i2)) * this.f5419e.f5433a);
        }
        if (this.f5420f != null) {
            marginLayoutParams.bottomMargin = (int) (((float) (this.f5420f.f5434b ? i : i2)) * this.f5420f.f5433a);
        }
        if (this.f5421g != null) {
            MarginLayoutParamsCompat.setMarginStart(marginLayoutParams, (int) (((float) (this.f5421g.f5434b ? i : i2)) * this.f5421g.f5433a));
        }
        if (this.f5422h != null) {
            if (!this.f5422h.f5434b) {
                i = i2;
            }
            MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams, (int) (((float) i) * this.f5422h.f5433a));
        }
        if (Log.isLoggable("PercentLayout", 3)) {
            Log.d("PercentLayout", "after fillMarginLayoutParams: (" + marginLayoutParams.width + ", " + marginLayoutParams.height + ")");
        }
    }

    public String toString() {
        return "PercentLayoutInfo{widthPercent=" + this.f5415a + ", heightPercent=" + this.f5416b + ", leftMarginPercent=" + this.f5417c + ", topMarginPercent=" + this.f5418d + ", rightMarginPercent=" + this.f5419e + ", bottomMarginPercent=" + this.f5420f + ", startMarginPercent=" + this.f5421g + ", endMarginPercent=" + this.f5422h + ", textSizePercent=" + this.f5423i + ", maxWidthPercent=" + this.f5424j + ", maxHeightPercent=" + this.f5425k + ", minWidthPercent=" + this.f5426l + ", minHeightPercent=" + this.f5427m + ", paddingLeftPercent=" + this.f5428n + ", paddingRightPercent=" + this.f5429o + ", paddingTopPercent=" + this.f5430p + ", paddingBottomPercent=" + this.f5431q + ", mPreservedParams=" + this.f5432r + '}';
    }
}
