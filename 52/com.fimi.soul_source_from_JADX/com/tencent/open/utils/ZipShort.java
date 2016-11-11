package com.tencent.open.utils;

import android.support.v4.view.MotionEventCompat;
import com.tencent.mm.sdk.platformtools.Util;

public final class ZipShort implements Cloneable {
    private int f12127a;

    public ZipShort(int i) {
        this.f12127a = i;
    }

    public ZipShort(byte[] bArr) {
        this(bArr, 0);
    }

    public ZipShort(byte[] bArr, int i) {
        this.f12127a = (bArr[i + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
        this.f12127a += bArr[i] & Util.MASK_8BIT;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof ZipShort) && this.f12127a == ((ZipShort) obj).getValue();
    }

    public byte[] getBytes() {
        return new byte[]{(byte) (this.f12127a & Util.MASK_8BIT), (byte) ((this.f12127a & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8)};
    }

    public int getValue() {
        return this.f12127a;
    }

    public int hashCode() {
        return this.f12127a;
    }
}
