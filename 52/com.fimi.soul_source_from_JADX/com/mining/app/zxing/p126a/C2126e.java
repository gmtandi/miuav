package com.mining.app.zxing.p126a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.v4.view.ViewCompat;
import com.google.zxing.LuminanceSource;
import com.tencent.mm.sdk.platformtools.Util;

/* renamed from: com.mining.app.zxing.a.e */
public final class C2126e extends LuminanceSource {
    private final byte[] f11181a;
    private final int f11182b;
    private final int f11183c;
    private final int f11184d;
    private final int f11185e;

    public C2126e(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6) {
        super(i5, i6);
        if (i3 + i5 > i || i4 + i6 > i2) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.f11181a = bArr;
        this.f11182b = i;
        this.f11183c = i2;
        this.f11184d = i3;
        this.f11185e = i4;
    }

    public int m13093a() {
        return this.f11182b;
    }

    public int m13094b() {
        return this.f11183c;
    }

    public Bitmap m13095c() {
        int width = getWidth();
        int height = getHeight();
        int[] iArr = new int[(width * height)];
        byte[] bArr = this.f11181a;
        int i = (this.f11185e * this.f11182b) + this.f11184d;
        for (int i2 = 0; i2 < height; i2++) {
            int i3 = i2 * width;
            for (int i4 = 0; i4 < width; i4++) {
                iArr[i3 + i4] = ((bArr[i + i4] & Util.MASK_8BIT) * 65793) | ViewCompat.MEASURED_STATE_MASK;
            }
            i += this.f11182b;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return createBitmap;
    }

    public byte[] getMatrix() {
        int i = 0;
        int width = getWidth();
        int height = getHeight();
        if (width == this.f11182b && height == this.f11183c) {
            return this.f11181a;
        }
        int i2 = width * height;
        Object obj = new byte[i2];
        int i3 = (this.f11185e * this.f11182b) + this.f11184d;
        if (width == this.f11182b) {
            System.arraycopy(this.f11181a, i3, obj, 0, i2);
            return obj;
        }
        Object obj2 = this.f11181a;
        while (i < height) {
            System.arraycopy(obj2, i3, obj, i * width, width);
            i3 += this.f11182b;
            i++;
        }
        return obj;
    }

    public byte[] getRow(int i, byte[] bArr) {
        if (i < 0 || i >= getHeight()) {
            throw new IllegalArgumentException("Requested row is outside the image: " + i);
        }
        Object obj;
        int width = getWidth();
        if (bArr == null || bArr.length < width) {
            obj = new byte[width];
        }
        System.arraycopy(this.f11181a, ((this.f11185e + i) * this.f11182b) + this.f11184d, obj, 0, width);
        return obj;
    }

    public boolean isCropSupported() {
        return true;
    }
}
