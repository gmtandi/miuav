package com.google.zxing.qrcode.detector;

import com.fimi.soul.view.photodraweeview.C2020f;
import com.google.zxing.ResultPoint;

public final class FinderPattern extends ResultPoint {
    private int count;
    private final float estimatedModuleSize;

    FinderPattern(float f, float f2, float f3) {
        super(f, f2);
        this.estimatedModuleSize = f3;
        this.count = 1;
    }

    boolean aboutEquals(float f, float f2, float f3) {
        if (Math.abs(f2 - getY()) > f || Math.abs(f3 - getX()) > f) {
            return false;
        }
        float abs = Math.abs(f - this.estimatedModuleSize);
        return abs <= C2020f.f10933c || abs / this.estimatedModuleSize <= C2020f.f10933c;
    }

    int getCount() {
        return this.count;
    }

    public float getEstimatedModuleSize() {
        return this.estimatedModuleSize;
    }

    void incrementCount() {
        this.count++;
    }
}
