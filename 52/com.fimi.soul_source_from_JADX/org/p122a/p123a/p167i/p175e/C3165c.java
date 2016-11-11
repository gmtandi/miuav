package org.p122a.p123a.p167i.p175e;

import org.apache.http.Header;
import org.apache.http.HttpMessage;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.entity.ContentLengthStrategy;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.e.c */
public class C3165c implements ContentLengthStrategy {
    public static final C3165c f15518a;
    private final int f15519b;

    static {
        f15518a = new C3165c();
    }

    public C3165c() {
        this(-1);
    }

    public C3165c(int i) {
        this.f15519b = i;
    }

    public long determineLength(HttpMessage httpMessage) {
        C3234a.m17886a((Object) httpMessage, "HTTP message");
        Header firstHeader = httpMessage.getFirstHeader(C3004e.f15011W);
        if (firstHeader != null) {
            String value = firstHeader.getValue();
            if ("chunked".equalsIgnoreCase(value)) {
                if (!httpMessage.getProtocolVersion().lessEquals(HttpVersion.HTTP_1_0)) {
                    return -2;
                }
                throw new ProtocolException("Chunked transfer encoding not allowed for " + httpMessage.getProtocolVersion());
            } else if ("identity".equalsIgnoreCase(value)) {
                return -1;
            } else {
                throw new ProtocolException("Unsupported transfer encoding: " + value);
            }
        }
        firstHeader = httpMessage.getFirstHeader(C3004e.f15027m);
        if (firstHeader == null) {
            return (long) this.f15519b;
        }
        String value2 = firstHeader.getValue();
        try {
            long parseLong = Long.parseLong(value2);
            if (parseLong >= 0) {
                return parseLong;
            }
            throw new ProtocolException("Negative content length: " + value2);
        } catch (NumberFormatException e) {
            throw new ProtocolException("Invalid content length: " + value2);
        }
    }
}
