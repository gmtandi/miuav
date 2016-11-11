package com.tencent.open.utils;

import android.support.v4.view.MotionEventCompat;
import com.tencent.mm.sdk.platformtools.Util;

public final class ZipLong implements Cloneable {
    private long f12126a;

    public ZipLong(long j) {
        this.f12126a = j;
    }

    public ZipLong(byte[] bArr) {
        this(bArr, 0);
    }

    public ZipLong(byte[] bArr, int i) {
        this.f12126a = ((long) (bArr[i + 3] << 24)) & 4278190080L;
        this.f12126a += (long) ((bArr[i + 2] << 16) & 16711680);
        this.f12126a += (long) ((bArr[i + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
        this.f12126a += (long) (bArr[i] & Util.MASK_8BIT);
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof ZipLong) && this.f12126a == ((ZipLong) obj).getValue();
    }

    public byte[] getBytes() {
        return new byte[]{(byte) ((int) (this.f12126a & 255)), (byte) ((int) ((this.f12126a & 65280) >> 8)), (byte) ((int) ((this.f12126a & 16711680) >> 16)), (byte) ((int) ((this.f12126a & 4278190080L) >> 24))};
    }

    public long getValue() {
        return this.f12126a;
    }

    public int hashCode() {
        return (int) this.f12126a;
    }
}
