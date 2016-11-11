package com.p016a;

import com.fimi.kernel.p076b.p080d.C1142e;
import java.io.UnsupportedEncodingException;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.ah */
class ah {
    ah() {
    }

    static String m1011a(String str) {
        if (str == null) {
            return null;
        }
        byte[] bytes;
        try {
            bytes = str.getBytes(C1142e.f5201a);
        } catch (UnsupportedEncodingException e) {
            bytes = str.getBytes();
        }
        String a = fy.m1901a(bytes);
        return ((char) ((a.length() % 26) + 65)) + a;
    }

    static String m1012b(String str) {
        return str.length() < 2 ? C2915a.f14760f : fy.m1900a(str.substring(1));
    }
}
