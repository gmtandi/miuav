package com.p054c.p055a.p063b.p066b;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.p054c.p055a.p063b.p064a.C0899e;
import com.p054c.p055a.p063b.p064a.C0900f;
import com.p054c.p055a.p063b.p068d.C0923d;
import com.p054c.p055a.p072c.C0955b;
import com.p054c.p055a.p072c.C0957d;
import com.p054c.p055a.p072c.C0958f;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.c.a.b.b.a */
public class C0907a implements C0906d {
    protected static final String f4783a = "Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]";
    protected static final String f4784b = "Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]";
    protected static final String f4785c = "Rotate image on %1$d\u00b0 [%2$s]";
    protected static final String f4786d = "Flip image horizontally [%s]";
    protected static final String f4787e = "No stream for image [%s]";
    protected static final String f4788f = "Image can't be decoded [%s]";
    protected final boolean f4789g;

    public C0907a(boolean z) {
        this.f4789g = z;
    }

    private boolean m7225a(String str, String str2) {
        return "image/jpeg".equalsIgnoreCase(str2) && C0923d.m7267a(str) == C0923d.FILE;
    }

    protected Bitmap m7226a(Bitmap bitmap, C0910e c0910e, int i, boolean z) {
        Matrix matrix = new Matrix();
        C0899e e = c0910e.m7240e();
        if (e == C0899e.EXACTLY || e == C0899e.EXACTLY_STRETCHED) {
            float b = C0955b.m7546b(new C0900f(bitmap.getWidth(), bitmap.getHeight(), i), c0910e.m7239d(), c0910e.m7241f(), e == C0899e.EXACTLY_STRETCHED);
            if (Float.compare(b, C2020f.f10933c) != 0) {
                matrix.setScale(b, b);
                if (this.f4789g) {
                    C0958f.m7554a(f4784b, r2, r2.m7207a(b), Float.valueOf(b), c0910e.m7236a());
                }
            }
        }
        if (z) {
            matrix.postScale(GroundOverlayOptions.NO_DIMENSION, C2020f.f10933c);
            if (this.f4789g) {
                C0958f.m7554a(f4786d, c0910e.m7236a());
            }
        }
        if (i != 0) {
            matrix.postRotate((float) i);
            if (this.f4789g) {
                C0958f.m7554a(f4785c, Integer.valueOf(i), c0910e.m7236a());
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public Bitmap m7227a(C0910e c0910e) {
        Closeable b = m7231b(c0910e);
        if (b == null) {
            C0958f.m7562d(f4787e, c0910e.m7236a());
            return null;
        }
        try {
            C0909c a = m7230a((InputStream) b, c0910e);
            b = m7232b(b, c0910e);
            Bitmap decodeStream = BitmapFactory.decodeStream(b, null, m7228a(a.f4792a, c0910e));
            if (decodeStream != null) {
                return m7226a(decodeStream, c0910e, a.f4793b.f4790a, a.f4793b.f4791b);
            }
            C0958f.m7562d(f4788f, c0910e.m7236a());
            return decodeStream;
        } finally {
            C0957d.m7547a(b);
        }
    }

    protected Options m7228a(C0900f c0900f, C0910e c0910e) {
        int i;
        C0899e e = c0910e.m7240e();
        if (e == C0899e.NONE) {
            i = 1;
        } else if (e == C0899e.NONE_SAFE) {
            i = C0955b.m7543a(c0900f);
        } else {
            i = C0955b.m7544a(c0900f, c0910e.m7239d(), c0910e.m7241f(), e == C0899e.IN_SAMPLE_POWER_OF_2);
        }
        if (i > 1 && this.f4789g) {
            C0958f.m7554a(f4783a, c0900f, c0900f.m7208a(i), Integer.valueOf(i), c0910e.m7236a());
        }
        Options j = c0910e.m7245j();
        j.inSampleSize = i;
        return j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected com.p054c.p055a.p063b.p066b.C0908b m7229a(java.lang.String r6) {
        /*
        r5 = this;
        r1 = 0;
        r0 = 1;
        r2 = new android.media.ExifInterface;	 Catch:{ IOException -> 0x002a }
        r3 = com.p054c.p055a.p063b.p068d.C0923d.FILE;	 Catch:{ IOException -> 0x002a }
        r3 = r3.m7270c(r6);	 Catch:{ IOException -> 0x002a }
        r2.<init>(r3);	 Catch:{ IOException -> 0x002a }
        r3 = "Orientation";
        r4 = 1;
        r2 = r2.getAttributeInt(r3, r4);	 Catch:{ IOException -> 0x002a }
        switch(r2) {
            case 1: goto L_0x0017;
            case 2: goto L_0x0018;
            case 3: goto L_0x0022;
            case 4: goto L_0x0023;
            case 5: goto L_0x0027;
            case 6: goto L_0x001e;
            case 7: goto L_0x001f;
            case 8: goto L_0x0026;
            default: goto L_0x0017;
        };
    L_0x0017:
        r0 = r1;
    L_0x0018:
        r2 = new com.c.a.b.b.b;
        r2.<init>(r1, r0);
        return r2;
    L_0x001e:
        r0 = r1;
    L_0x001f:
        r1 = 90;
        goto L_0x0018;
    L_0x0022:
        r0 = r1;
    L_0x0023:
        r1 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        goto L_0x0018;
    L_0x0026:
        r0 = r1;
    L_0x0027:
        r1 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        goto L_0x0018;
    L_0x002a:
        r2 = move-exception;
        r2 = "Can't read EXIF tags from file [%s]";
        r0 = new java.lang.Object[r0];
        r0[r1] = r6;
        com.p054c.p055a.p072c.C0958f.m7561c(r2, r0);
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.c.a.b.b.a.a(java.lang.String):com.c.a.b.b.b");
    }

    protected C0909c m7230a(InputStream inputStream, C0910e c0910e) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        String b = c0910e.m7237b();
        C0908b a = (c0910e.m7244i() && m7225a(b, options.outMimeType)) ? m7229a(b) : new C0908b();
        return new C0909c(new C0900f(options.outWidth, options.outHeight, a.f4790a), a);
    }

    protected InputStream m7231b(C0910e c0910e) {
        return c0910e.m7242g().m7253a(c0910e.m7237b(), c0910e.m7243h());
    }

    protected InputStream m7232b(InputStream inputStream, C0910e c0910e) {
        try {
            inputStream.reset();
            return inputStream;
        } catch (IOException e) {
            C0957d.m7547a((Closeable) inputStream);
            return m7231b(c0910e);
        }
    }
}
