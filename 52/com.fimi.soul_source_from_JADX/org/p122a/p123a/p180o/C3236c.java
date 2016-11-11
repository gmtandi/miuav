package org.p122a.p123a.p180o;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

/* renamed from: org.a.a.o.c */
public class C3236c {
    public static Charset m17898a(String str) {
        Charset charset = null;
        if (str != null) {
            try {
                charset = Charset.forName(str);
            } catch (UnsupportedCharsetException e) {
            }
        }
        return charset;
    }

    public static Charset m17899b(String str) {
        if (str == null) {
            return null;
        }
        try {
            return Charset.forName(str);
        } catch (UnsupportedCharsetException e) {
            throw new UnsupportedEncodingException(str);
        }
    }
}
