package org.p122a.p123a.p167i.p174d;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeaderElement;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.ParserCursor;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.d.w */
public class C3158w {
    public static final C3158w f15501a;

    static {
        f15501a = new C3158w();
    }

    private NameValuePair m17687b(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        String substringTrimmed;
        Object obj;
        Object obj2 = 1;
        Object obj3 = null;
        int pos = parserCursor.getPos();
        int pos2 = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        while (pos < upperBound) {
            char charAt = charArrayBuffer.charAt(pos);
            if (charAt == SignatureVisitor.INSTANCEOF) {
                break;
            } else if (charAt == ';') {
                int i = 1;
                break;
            } else {
                pos++;
            }
        }
        if (pos == upperBound) {
            substringTrimmed = charArrayBuffer.substringTrimmed(pos2, upperBound);
            obj = 1;
        } else {
            String substringTrimmed2 = charArrayBuffer.substringTrimmed(pos2, pos);
            pos++;
            substringTrimmed = substringTrimmed2;
            obj = obj3;
        }
        if (obj != null) {
            parserCursor.updatePos(pos);
            return new BasicNameValuePair(substringTrimmed, null);
        }
        i = pos;
        while (i < upperBound) {
            if (charArrayBuffer.charAt(i) == ';') {
                break;
            }
            i++;
        }
        obj2 = obj;
        while (pos < i && HTTP.isWhitespace(charArrayBuffer.charAt(pos))) {
            pos++;
        }
        pos2 = i;
        while (pos2 > pos && HTTP.isWhitespace(charArrayBuffer.charAt(pos2 - 1))) {
            pos2--;
        }
        substringTrimmed2 = charArrayBuffer.substring(pos, pos2);
        parserCursor.updatePos(obj2 != null ? i + 1 : i);
        return new BasicNameValuePair(substringTrimmed, substringTrimmed2);
    }

    public HeaderElement m17688a(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        C3234a.m17886a((Object) charArrayBuffer, "Char array buffer");
        C3234a.m17886a((Object) parserCursor, "Parser cursor");
        NameValuePair b = m17687b(charArrayBuffer, parserCursor);
        List arrayList = new ArrayList();
        while (!parserCursor.atEnd()) {
            arrayList.add(m17687b(charArrayBuffer, parserCursor));
        }
        return new BasicHeaderElement(b.getName(), b.getValue(), (NameValuePair[]) arrayList.toArray(new NameValuePair[arrayList.size()]));
    }
}
