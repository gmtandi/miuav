package com.mining.app.zxing.p127b;

import android.content.Intent;
import android.net.Uri;
import com.google.zxing.BarcodeFormat;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Pattern;

/* renamed from: com.mining.app.zxing.b.c */
final class C2132c {
    static final Vector<BarcodeFormat> f11210a;
    static final Vector<BarcodeFormat> f11211b;
    static final Vector<BarcodeFormat> f11212c;
    static final Vector<BarcodeFormat> f11213d;
    private static final Pattern f11214e;

    static {
        f11214e = Pattern.compile(MiPushClient.ACCEPT_TIME_SEPARATOR);
        f11210a = new Vector(5);
        f11210a.add(BarcodeFormat.UPC_A);
        f11210a.add(BarcodeFormat.UPC_E);
        f11210a.add(BarcodeFormat.EAN_13);
        f11210a.add(BarcodeFormat.EAN_8);
        f11210a.add(BarcodeFormat.RSS14);
        f11211b = new Vector(1);
        f11211b.add(BarcodeFormat.CODE_128);
        f11212c = new Vector(1);
        f11212c.add(BarcodeFormat.QR_CODE);
        f11213d = new Vector(1);
        f11213d.add(BarcodeFormat.DATA_MATRIX);
    }

    private C2132c() {
    }

    static Vector<BarcodeFormat> m13104a(Intent intent) {
        Iterable iterable = null;
        CharSequence stringExtra = intent.getStringExtra(C2141l.f11234c);
        if (stringExtra != null) {
            iterable = Arrays.asList(f11214e.split(stringExtra));
        }
        return C2132c.m13106a(iterable, intent.getStringExtra(C2141l.f11233b));
    }

    static Vector<BarcodeFormat> m13105a(Uri uri) {
        Iterable queryParameters = uri.getQueryParameters(C2141l.f11234c);
        if (!(queryParameters == null || queryParameters.size() != 1 || queryParameters.get(0) == null)) {
            queryParameters = Arrays.asList(f11214e.split((CharSequence) queryParameters.get(0)));
        }
        return C2132c.m13106a(queryParameters, uri.getQueryParameter(C2141l.f11233b));
    }

    private static Vector<BarcodeFormat> m13106a(Iterable<String> iterable, String str) {
        if (iterable != null) {
            Vector<BarcodeFormat> vector = new Vector();
            try {
                for (String valueOf : iterable) {
                    vector.add(BarcodeFormat.valueOf(valueOf));
                }
                return vector;
            } catch (IllegalArgumentException e) {
            }
        }
        if (str != null) {
            if (C2141l.f11236e.equals(str)) {
                return f11210a;
            }
            if (C2141l.f11238g.equals(str)) {
                return f11212c;
            }
            if (C2141l.f11239h.equals(str)) {
                return f11213d;
            }
            if (C2141l.f11237f.equals(str)) {
                return f11211b;
            }
        }
        return null;
    }
}
