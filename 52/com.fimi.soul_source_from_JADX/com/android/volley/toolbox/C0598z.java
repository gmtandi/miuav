package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.widget.ImageView.ScaleType;
import com.android.volley.C0558f;
import com.android.volley.C0566n;
import com.android.volley.C0568p;
import com.android.volley.C0570r;
import com.android.volley.C0599u;
import com.android.volley.C0604z;
import com.android.volley.aa;
import com.android.volley.ab;
import com.android.volley.ah;
import com.fimi.soul.view.photodraweeview.C2020f;

/* renamed from: com.android.volley.toolbox.z */
public class C0598z extends C0570r<Bitmap> {
    private static final int f3659a = 1000;
    private static final int f3660b = 2;
    private static final float f3661c = 2.0f;
    private static final Object f3662i;
    private final ab<Bitmap> f3663d;
    private final Config f3664e;
    private final int f3665f;
    private final int f3666g;
    private ScaleType f3667h;

    static {
        f3662i = new Object();
    }

    @Deprecated
    public C0598z(String str, ab<Bitmap> abVar, int i, int i2, Config config, aa aaVar) {
        this(str, abVar, i, i2, ScaleType.CENTER_INSIDE, config, aaVar);
    }

    public C0598z(String str, ab<Bitmap> abVar, int i, int i2, ScaleType scaleType, Config config, aa aaVar) {
        super(0, str, aaVar);
        m5100a(new C0558f(f3659a, f3660b, f3661c));
        this.f3663d = abVar;
        this.f3664e = config;
        this.f3665f = i;
        this.f3666g = i2;
        this.f3667h = scaleType;
    }

    static int m5272a(int i, int i2, int i3, int i4) {
        double min = Math.min(((double) i) / ((double) i3), ((double) i2) / ((double) i4));
        float f = C2020f.f10933c;
        while (((double) (f * f3661c)) <= min) {
            f *= f3661c;
        }
        return (int) f;
    }

    private static int m5273a(int i, int i2, int i3, int i4, ScaleType scaleType) {
        if (i == 0 && i2 == 0) {
            return i3;
        }
        if (scaleType == ScaleType.FIT_XY) {
            return i == 0 ? i3 : i;
        } else {
            if (i == 0) {
                return (int) ((((double) i2) / ((double) i4)) * ((double) i3));
            }
            if (i2 == 0) {
                return i;
            }
            double d = ((double) i4) / ((double) i3);
            return scaleType == ScaleType.CENTER_CROP ? ((double) i) * d < ((double) i2) ? (int) (((double) i2) / d) : i : ((double) i) * d > ((double) i2) ? (int) (((double) i2) / d) : i;
        }
    }

    private C0604z<Bitmap> m5274b(C0566n c0566n) {
        Object decodeByteArray;
        byte[] bArr = c0566n.f3542b;
        Options options = new Options();
        if (this.f3665f == 0 && this.f3666g == 0) {
            options.inPreferredConfig = this.f3664e;
            decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        } else {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int i = options.outWidth;
            int i2 = options.outHeight;
            int a = C0598z.m5273a(this.f3665f, this.f3666g, i, i2, this.f3667h);
            int a2 = C0598z.m5273a(this.f3666g, this.f3665f, i2, i, this.f3667h);
            options.inJustDecodeBounds = false;
            options.inSampleSize = C0598z.m5272a(i, i2, a, a2);
            Bitmap decodeByteArray2 = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            if (decodeByteArray2 == null || (decodeByteArray2.getWidth() <= a && decodeByteArray2.getHeight() <= a2)) {
                Bitmap bitmap = decodeByteArray2;
            } else {
                decodeByteArray = Bitmap.createScaledBitmap(decodeByteArray2, a, a2, true);
                decodeByteArray2.recycle();
            }
        }
        return decodeByteArray == null ? C0604z.m5292a(new C0568p(c0566n)) : C0604z.m5293a(decodeByteArray, C0587m.m5226a(c0566n));
    }

    protected C0604z<Bitmap> m5275a(C0566n c0566n) {
        C0604z<Bitmap> b;
        synchronized (f3662i) {
            try {
                b = m5274b(c0566n);
            } catch (Throwable e) {
                Object[] objArr = new Object[f3660b];
                objArr[0] = Integer.valueOf(c0566n.f3542b.length);
                objArr[1] = m5116g();
                ah.m5060c("Caught OOM for %d byte image, url=%s", objArr);
                b = C0604z.m5292a(new C0568p(e));
            }
        }
        return b;
    }

    protected void m5276a(Bitmap bitmap) {
        this.f3663d.m5048a(bitmap);
    }

    protected /* synthetic */ void m5277b(Object obj) {
        m5276a((Bitmap) obj);
    }

    public C0599u m5278x() {
        return C0599u.LOW;
    }
}
