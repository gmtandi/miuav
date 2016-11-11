package org.p122a.p123a.p124f.p125c;

import java.util.BitSet;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

/* renamed from: org.a.a.f.c.o */
class C3022o {
    public static final char f15053a = '\r';
    public static final char f15054b = '\n';
    public static final char f15055c = ' ';
    public static final char f15056d = '\t';
    public static final char f15057e = '\"';
    public static final char f15058f = '\\';
    public static final C3022o f15059g;

    static {
        f15059g = new C3022o();
    }

    C3022o() {
    }

    public static BitSet m17090a(int... iArr) {
        BitSet bitSet = new BitSet();
        for (int i : iArr) {
            bitSet.set(i);
        }
        return bitSet;
    }

    public static boolean m17091a(char c) {
        return c == f15055c || c == f15056d || c == f15053a || c == f15054b;
    }

    public String m17092a(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor, BitSet bitSet) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = null;
        while (!parserCursor.atEnd()) {
            char charAt = charArrayBuffer.charAt(parserCursor.getPos());
            if (bitSet != null && bitSet.get(charAt)) {
                break;
            } else if (C3022o.m17091a(charAt)) {
                m17093a(charArrayBuffer, parserCursor);
                obj = 1;
            } else {
                if (obj != null && stringBuilder.length() > 0) {
                    stringBuilder.append(f15055c);
                }
                m17097b(charArrayBuffer, parserCursor, bitSet, stringBuilder);
                obj = null;
            }
        }
        return stringBuilder.toString();
    }

    public void m17093a(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        int pos = parserCursor.getPos();
        int pos2 = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        while (pos2 < upperBound && C3022o.m17091a(charArrayBuffer.charAt(pos2))) {
            pos++;
            pos2++;
        }
        parserCursor.updatePos(pos);
    }

    public void m17094a(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor, StringBuilder stringBuilder) {
        if (!parserCursor.atEnd()) {
            int pos = parserCursor.getPos();
            int pos2 = parserCursor.getPos();
            int upperBound = parserCursor.getUpperBound();
            if (charArrayBuffer.charAt(pos) == f15057e) {
                int i = pos + 1;
                pos2++;
                Object obj = null;
                while (pos2 < upperBound) {
                    char charAt = charArrayBuffer.charAt(pos2);
                    if (obj != null) {
                        if (!(charAt == f15057e || charAt == f15058f)) {
                            stringBuilder.append(f15058f);
                        }
                        stringBuilder.append(charAt);
                        obj = null;
                    } else if (charAt == f15057e) {
                        pos = i + 1;
                        break;
                    } else if (charAt == f15058f) {
                        obj = 1;
                    } else if (!(charAt == f15053a || charAt == f15054b)) {
                        stringBuilder.append(charAt);
                    }
                    pos2++;
                    i++;
                }
                pos = i;
                parserCursor.updatePos(pos);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m17095a(org.apache.http.util.CharArrayBuffer r6, org.apache.http.message.ParserCursor r7, java.util.BitSet r8, java.lang.StringBuilder r9) {
        /*
        r5 = this;
        r1 = r7.getPos();
        r0 = r7.getPos();
        r2 = r7.getUpperBound();
    L_0x000c:
        if (r0 >= r2) goto L_0x0024;
    L_0x000e:
        r3 = r6.charAt(r0);
        if (r8 == 0) goto L_0x001a;
    L_0x0014:
        r4 = r8.get(r3);
        if (r4 != 0) goto L_0x0024;
    L_0x001a:
        r4 = org.p122a.p123a.p124f.p125c.C3022o.m17091a(r3);
        if (r4 != 0) goto L_0x0024;
    L_0x0020:
        r4 = 34;
        if (r3 != r4) goto L_0x0028;
    L_0x0024:
        r7.updatePos(r1);
        return;
    L_0x0028:
        r1 = r1 + 1;
        r9.append(r3);
        r0 = r0 + 1;
        goto L_0x000c;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.a.f.c.o.a(org.apache.http.util.CharArrayBuffer, org.apache.http.message.ParserCursor, java.util.BitSet, java.lang.StringBuilder):void");
    }

    public String m17096b(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor, BitSet bitSet) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = null;
        while (!parserCursor.atEnd()) {
            char charAt = charArrayBuffer.charAt(parserCursor.getPos());
            if (bitSet != null && bitSet.get(charAt)) {
                break;
            } else if (C3022o.m17091a(charAt)) {
                m17093a(charArrayBuffer, parserCursor);
                obj = 1;
            } else if (charAt == f15057e) {
                if (obj != null && stringBuilder.length() > 0) {
                    stringBuilder.append(f15055c);
                }
                m17094a(charArrayBuffer, parserCursor, stringBuilder);
                obj = null;
            } else {
                if (obj != null && stringBuilder.length() > 0) {
                    stringBuilder.append(f15055c);
                }
                m17095a(charArrayBuffer, parserCursor, bitSet, stringBuilder);
                obj = null;
            }
        }
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m17097b(org.apache.http.util.CharArrayBuffer r6, org.apache.http.message.ParserCursor r7, java.util.BitSet r8, java.lang.StringBuilder r9) {
        /*
        r5 = this;
        r1 = r7.getPos();
        r0 = r7.getPos();
        r2 = r7.getUpperBound();
    L_0x000c:
        if (r0 >= r2) goto L_0x0020;
    L_0x000e:
        r3 = r6.charAt(r0);
        if (r8 == 0) goto L_0x001a;
    L_0x0014:
        r4 = r8.get(r3);
        if (r4 != 0) goto L_0x0020;
    L_0x001a:
        r4 = org.p122a.p123a.p124f.p125c.C3022o.m17091a(r3);
        if (r4 == 0) goto L_0x0024;
    L_0x0020:
        r7.updatePos(r1);
        return;
    L_0x0024:
        r1 = r1 + 1;
        r9.append(r3);
        r0 = r0 + 1;
        goto L_0x000c;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.a.f.c.o.b(org.apache.http.util.CharArrayBuffer, org.apache.http.message.ParserCursor, java.util.BitSet, java.lang.StringBuilder):void");
    }
}
