package com.google.zxing.qrcode.detector;

import com.fimi.soul.view.photodraweeview.C2020f;
import com.google.zxing.ResultPoint;

public final class AlignmentPattern extends ResultPoint {
    private final float estimatedModuleSize;

    AlignmentPattern(float f, float f2, float f3) {
        super(f, f2);
        this.estimatedModuleSize = f3;
    }

    boolean aboutEquals(float f, float f2, float f3) {
        if (Math.abs(f2 - getY()) > f || Math.abs(f3 - getX()) > f) {
            return false;
        }
        float abs = Math.abs(f - this.estimatedModuleSize);
        return abs <= C2020f.f10933c || abs / this.estimatedModuleSize <= C2020f.f10933c;
    }
}
