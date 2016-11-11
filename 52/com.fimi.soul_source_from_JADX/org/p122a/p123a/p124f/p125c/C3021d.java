package org.p122a.p123a.p124f.p125c;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;
import org.p122a.p123a.p150a.C2912b;

@C2912b
/* renamed from: org.a.a.f.c.d */
final class C3021d {
    public static final C3021d f15049a;
    private static final BitSet f15050b;
    private static final BitSet f15051c;
    private final C3022o f15052d;

    static {
        f15049a = new C3021d();
        f15050b = C3022o.m17090a(61, 44, 43);
        f15051c = C3022o.m17090a(44, 43);
    }

    C3021d() {
        this.f15052d = new C3023e();
    }

    String m17085a(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor, BitSet bitSet) {
        return this.f15052d.m17092a(charArrayBuffer, parserCursor, bitSet);
    }

    public List<NameValuePair> m17086a(String str) {
        if (str == null) {
            return null;
        }
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        return m17089b(charArrayBuffer, new ParserCursor(0, str.length()));
    }

    NameValuePair m17087a(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        String a = m17085a(charArrayBuffer, parserCursor, f15050b);
        if (parserCursor.atEnd()) {
            return new BasicNameValuePair(a, null);
        }
        char charAt = charArrayBuffer.charAt(parserCursor.getPos());
        parserCursor.updatePos(parserCursor.getPos() + 1);
        if (charAt == ',') {
            return new BasicNameValuePair(a, null);
        }
        String b = m17088b(charArrayBuffer, parserCursor, f15051c);
        if (!parserCursor.atEnd()) {
            parserCursor.updatePos(parserCursor.getPos() + 1);
        }
        return new BasicNameValuePair(a, b);
    }

    String m17088b(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor, BitSet bitSet) {
        return this.f15052d.m17096b(charArrayBuffer, parserCursor, bitSet);
    }

    public List<NameValuePair> m17089b(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        List<NameValuePair> arrayList = new ArrayList();
        this.f15052d.m17093a(charArrayBuffer, parserCursor);
        while (!parserCursor.atEnd()) {
            arrayList.add(m17087a(charArrayBuffer, parserCursor));
        }
        return arrayList;
    }
}
