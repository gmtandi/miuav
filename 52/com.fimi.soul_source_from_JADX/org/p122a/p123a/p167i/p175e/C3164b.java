package org.p122a.p123a.p167i.p175e;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpMessage;
import org.apache.http.ProtocolException;
import org.apache.http.entity.ContentLengthStrategy;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.e.b */
public class C3164b implements ContentLengthStrategy {
    public static final C3164b f15516a;
    private final int f15517b;

    static {
        f15516a = new C3164b();
    }

    public C3164b() {
        this(-1);
    }

    public C3164b(int i) {
        this.f15517b = i;
    }

    public long determineLength(HttpMessage httpMessage) {
        C3234a.m17886a((Object) httpMessage, "HTTP message");
        Header firstHeader = httpMessage.getFirstHeader(C3004e.f15011W);
        if (firstHeader != null) {
            try {
                HeaderElement[] elements = firstHeader.getElements();
                int length = elements.length;
                return (!"identity".equalsIgnoreCase(firstHeader.getValue()) && length > 0 && "chunked".equalsIgnoreCase(elements[length - 1].getName())) ? -2 : -1;
            } catch (Throwable e) {
                throw new ProtocolException("Invalid Transfer-Encoding header value: " + firstHeader, e);
            }
        } else if (httpMessage.getFirstHeader(C3004e.f15027m) == null) {
            return (long) this.f15517b;
        } else {
            long parseLong;
            Header[] headers = httpMessage.getHeaders(C3004e.f15027m);
            int length2 = headers.length - 1;
            while (length2 >= 0) {
                try {
                    parseLong = Long.parseLong(headers[length2].getValue());
                    break;
                } catch (NumberFormatException e2) {
                    length2--;
                }
            }
            parseLong = -1;
            return parseLong >= 0 ? parseLong : -1;
        }
    }
}
