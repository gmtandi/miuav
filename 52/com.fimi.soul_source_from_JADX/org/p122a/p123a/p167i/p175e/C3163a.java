package org.p122a.p123a.p167i.p175e;

import org.apache.http.HttpMessage;
import org.apache.http.ProtocolException;
import org.apache.http.entity.ContentLengthStrategy;
import org.p122a.p123a.p150a.C2912b;

@C2912b
/* renamed from: org.a.a.i.e.a */
public class C3163a implements ContentLengthStrategy {
    public static final C3163a f15514a;
    private final ContentLengthStrategy f15515b;

    static {
        f15514a = new C3163a(new C3164b(0));
    }

    public C3163a(ContentLengthStrategy contentLengthStrategy) {
        this.f15515b = contentLengthStrategy;
    }

    public long determineLength(HttpMessage httpMessage) {
        long determineLength = this.f15515b.determineLength(httpMessage);
        if (determineLength != -1) {
            return determineLength;
        }
        throw new ProtocolException("Identity transfer encoding cannot be used");
    }
}
