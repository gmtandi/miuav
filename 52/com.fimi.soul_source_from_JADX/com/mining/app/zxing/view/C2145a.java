package com.mining.app.zxing.view;

import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;

/* renamed from: com.mining.app.zxing.view.a */
public final class C2145a implements ResultPointCallback {
    private final ViewfinderView f11277a;

    public C2145a(ViewfinderView viewfinderView) {
        this.f11277a = viewfinderView;
    }

    public void foundPossibleResultPoint(ResultPoint resultPoint) {
        this.f11277a.m13114a(resultPoint);
    }
}
