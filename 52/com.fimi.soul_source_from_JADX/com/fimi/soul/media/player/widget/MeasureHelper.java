package com.fimi.soul.media.player.widget;

import android.view.View;
import java.lang.ref.WeakReference;

public final class MeasureHelper {
    private int mCurrentAspectRatio;
    private int mMeasuredHeight;
    private int mMeasuredWidth;
    private int mVideoHeight;
    private int mVideoRotationDegree;
    private int mVideoSarDen;
    private int mVideoSarNum;
    private int mVideoWidth;
    private WeakReference<View> mWeakView;

    public MeasureHelper(View view) {
        this.mCurrentAspectRatio = 0;
        this.mWeakView = new WeakReference(view);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doMeasure(int r12, int r13) {
        /*
        r11 = this;
        r9 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r8 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        r7 = 90;
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r5 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r0 = r11.mVideoRotationDegree;
        if (r0 == r7) goto L_0x0012;
    L_0x000e:
        r0 = r11.mVideoRotationDegree;
        if (r0 != r8) goto L_0x0015;
    L_0x0012:
        r10 = r12;
        r12 = r13;
        r13 = r10;
    L_0x0015:
        r0 = r11.mVideoWidth;
        r2 = android.view.View.getDefaultSize(r0, r12);
        r0 = r11.mVideoHeight;
        r1 = android.view.View.getDefaultSize(r0, r13);
        r0 = r11.mCurrentAspectRatio;
        r3 = 3;
        if (r0 != r3) goto L_0x002d;
    L_0x0026:
        r1 = r13;
        r2 = r12;
    L_0x0028:
        r11.mMeasuredWidth = r2;
        r11.mMeasuredHeight = r1;
        return;
    L_0x002d:
        r0 = r11.mVideoWidth;
        if (r0 <= 0) goto L_0x0028;
    L_0x0031:
        r0 = r11.mVideoHeight;
        if (r0 <= 0) goto L_0x0028;
    L_0x0035:
        r3 = android.view.View.MeasureSpec.getMode(r12);
        r2 = android.view.View.MeasureSpec.getSize(r12);
        r4 = android.view.View.MeasureSpec.getMode(r13);
        r1 = android.view.View.MeasureSpec.getSize(r13);
        if (r3 != r5) goto L_0x00c8;
    L_0x0047:
        if (r4 != r5) goto L_0x00c8;
    L_0x0049:
        r0 = (float) r2;
        r3 = (float) r1;
        r4 = r0 / r3;
        r0 = r11.mCurrentAspectRatio;
        switch(r0) {
            case 4: goto L_0x0082;
            case 5: goto L_0x0091;
            default: goto L_0x0052;
        };
    L_0x0052:
        r0 = r11.mVideoWidth;
        r0 = (float) r0;
        r3 = r11.mVideoHeight;
        r3 = (float) r3;
        r0 = r0 / r3;
        r3 = r11.mVideoSarNum;
        if (r3 <= 0) goto L_0x012c;
    L_0x005d:
        r3 = r11.mVideoSarDen;
        if (r3 <= 0) goto L_0x012c;
    L_0x0061:
        r3 = r11.mVideoSarNum;
        r3 = (float) r3;
        r0 = r0 * r3;
        r3 = r11.mVideoSarDen;
        r3 = (float) r3;
        r0 = r0 / r3;
        r3 = r0;
    L_0x006a:
        r0 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x00a0;
    L_0x006e:
        r0 = 1;
    L_0x006f:
        r4 = r11.mCurrentAspectRatio;
        switch(r4) {
            case 0: goto L_0x00a2;
            case 1: goto L_0x00b0;
            case 2: goto L_0x0074;
            case 3: goto L_0x0074;
            case 4: goto L_0x00a2;
            case 5: goto L_0x00a2;
            default: goto L_0x0074;
        };
    L_0x0074:
        if (r0 == 0) goto L_0x00be;
    L_0x0076:
        r0 = r11.mVideoWidth;
        r1 = java.lang.Math.min(r0, r2);
        r0 = (float) r1;
        r0 = r0 / r3;
        r0 = (int) r0;
    L_0x007f:
        r2 = r1;
        r1 = r0;
        goto L_0x0028;
    L_0x0082:
        r0 = 1071877689; // 0x3fe38e39 float:1.7777778 double:5.295779427E-315;
        r3 = r11.mVideoRotationDegree;
        if (r3 == r7) goto L_0x008d;
    L_0x0089:
        r3 = r11.mVideoRotationDegree;
        if (r3 != r8) goto L_0x012c;
    L_0x008d:
        r0 = r9 / r0;
        r3 = r0;
        goto L_0x006a;
    L_0x0091:
        r0 = 1068149419; // 0x3faaaaab float:1.3333334 double:5.277359326E-315;
        r3 = r11.mVideoRotationDegree;
        if (r3 == r7) goto L_0x009c;
    L_0x0098:
        r3 = r11.mVideoRotationDegree;
        if (r3 != r8) goto L_0x012c;
    L_0x009c:
        r0 = r9 / r0;
        r3 = r0;
        goto L_0x006a;
    L_0x00a0:
        r0 = 0;
        goto L_0x006f;
    L_0x00a2:
        if (r0 == 0) goto L_0x00a9;
    L_0x00a4:
        r0 = (float) r2;
        r0 = r0 / r3;
        r0 = (int) r0;
        r1 = r2;
        goto L_0x007f;
    L_0x00a9:
        r0 = (float) r1;
        r0 = r0 * r3;
        r0 = (int) r0;
        r10 = r1;
        r1 = r0;
        r0 = r10;
        goto L_0x007f;
    L_0x00b0:
        if (r0 == 0) goto L_0x00b9;
    L_0x00b2:
        r0 = (float) r1;
        r0 = r0 * r3;
        r0 = (int) r0;
        r10 = r1;
        r1 = r0;
        r0 = r10;
        goto L_0x007f;
    L_0x00b9:
        r0 = (float) r2;
        r0 = r0 / r3;
        r0 = (int) r0;
        r1 = r2;
        goto L_0x007f;
    L_0x00be:
        r0 = r11.mVideoHeight;
        r0 = java.lang.Math.min(r0, r1);
        r1 = (float) r0;
        r1 = r1 * r3;
        r1 = (int) r1;
        goto L_0x007f;
    L_0x00c8:
        if (r3 != r6) goto L_0x00ee;
    L_0x00ca:
        if (r4 != r6) goto L_0x00ee;
    L_0x00cc:
        r0 = r11.mVideoWidth;
        r0 = r0 * r1;
        r3 = r11.mVideoHeight;
        r3 = r3 * r2;
        if (r0 >= r3) goto L_0x00dd;
    L_0x00d4:
        r0 = r11.mVideoWidth;
        r0 = r0 * r1;
        r2 = r11.mVideoHeight;
        r2 = r0 / r2;
        goto L_0x0028;
    L_0x00dd:
        r0 = r11.mVideoWidth;
        r0 = r0 * r1;
        r3 = r11.mVideoHeight;
        r3 = r3 * r2;
        if (r0 <= r3) goto L_0x0028;
    L_0x00e5:
        r0 = r11.mVideoHeight;
        r0 = r0 * r2;
        r1 = r11.mVideoWidth;
        r1 = r0 / r1;
        goto L_0x0028;
    L_0x00ee:
        if (r3 != r6) goto L_0x00fe;
    L_0x00f0:
        r0 = r11.mVideoHeight;
        r0 = r0 * r2;
        r3 = r11.mVideoWidth;
        r13 = r0 / r3;
        if (r4 != r5) goto L_0x00fb;
    L_0x00f9:
        if (r13 > r1) goto L_0x0028;
    L_0x00fb:
        r1 = r13;
        goto L_0x0028;
    L_0x00fe:
        if (r4 != r6) goto L_0x010e;
    L_0x0100:
        r0 = r11.mVideoWidth;
        r0 = r0 * r1;
        r4 = r11.mVideoHeight;
        r12 = r0 / r4;
        if (r3 != r5) goto L_0x010b;
    L_0x0109:
        if (r12 > r2) goto L_0x0028;
    L_0x010b:
        r2 = r12;
        goto L_0x0028;
    L_0x010e:
        r12 = r11.mVideoWidth;
        r0 = r11.mVideoHeight;
        if (r4 != r5) goto L_0x012a;
    L_0x0114:
        if (r0 <= r1) goto L_0x012a;
    L_0x0116:
        r0 = r11.mVideoWidth;
        r0 = r0 * r1;
        r4 = r11.mVideoHeight;
        r12 = r0 / r4;
    L_0x011d:
        if (r3 != r5) goto L_0x010b;
    L_0x011f:
        if (r12 <= r2) goto L_0x010b;
    L_0x0121:
        r0 = r11.mVideoHeight;
        r0 = r0 * r2;
        r1 = r11.mVideoWidth;
        r1 = r0 / r1;
        goto L_0x0028;
    L_0x012a:
        r1 = r0;
        goto L_0x011d;
    L_0x012c:
        r3 = r0;
        goto L_0x006a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fimi.soul.media.player.widget.MeasureHelper.doMeasure(int, int):void");
    }

    public int getMeasuredHeight() {
        return this.mMeasuredHeight;
    }

    public int getMeasuredWidth() {
        return this.mMeasuredWidth;
    }

    public View getView() {
        return this.mWeakView == null ? null : (View) this.mWeakView.get();
    }

    public void setAspectRatio(int i) {
        this.mCurrentAspectRatio = i;
    }

    public void setVideoRotation(int i) {
        this.mVideoRotationDegree = i;
    }

    public void setVideoSampleAspectRatio(int i, int i2) {
        this.mVideoSarNum = i;
        this.mVideoSarDen = i2;
    }

    public void setVideoSize(int i, int i2) {
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
    }
}
