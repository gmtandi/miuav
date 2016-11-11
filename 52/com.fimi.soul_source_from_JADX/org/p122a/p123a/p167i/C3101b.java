package org.p122a.p123a.p167i;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import org.p122a.p123a.p162e.C2995a;

/* renamed from: org.a.a.i.b */
public final class C3101b {
    public static CharsetDecoder m17472a(C2995a c2995a) {
        if (c2995a == null) {
            return null;
        }
        Charset c = c2995a.m17011c();
        CodingErrorAction d = c2995a.m17012d();
        CodingErrorAction e = c2995a.m17013e();
        if (c == null) {
            return null;
        }
        CharsetDecoder newDecoder = c.newDecoder();
        if (d == null) {
            d = CodingErrorAction.REPORT;
        }
        return newDecoder.onMalformedInput(d).onUnmappableCharacter(e != null ? e : CodingErrorAction.REPORT);
    }

    public static CharsetEncoder m17473b(C2995a c2995a) {
        if (c2995a == null) {
            return null;
        }
        Charset c = c2995a.m17011c();
        if (c == null) {
            return null;
        }
        CodingErrorAction d = c2995a.m17012d();
        CodingErrorAction e = c2995a.m17013e();
        CharsetEncoder newEncoder = c.newEncoder();
        if (d == null) {
            d = CodingErrorAction.REPORT;
        }
        return newEncoder.onMalformedInput(d).onUnmappableCharacter(e != null ? e : CodingErrorAction.REPORT);
    }
}
