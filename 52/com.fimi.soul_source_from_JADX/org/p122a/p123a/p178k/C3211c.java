package org.p122a.p123a.p178k;

import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.message.LineFormatter;
import org.apache.http.util.CharArrayBuffer;
import org.p122a.p123a.p124f.p125c.C3022o;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.k.c */
public class C3211c implements LineFormatter {
    @Deprecated
    public static final C3211c f15663a;
    public static final C3211c f15664b;

    static {
        f15663a = new C3211c();
        f15664b = new C3211c();
    }

    public static String m17808a(Header header, LineFormatter lineFormatter) {
        if (lineFormatter == null) {
            lineFormatter = f15664b;
        }
        return lineFormatter.formatHeader(null, header).toString();
    }

    public static String m17809a(ProtocolVersion protocolVersion, LineFormatter lineFormatter) {
        if (lineFormatter == null) {
            lineFormatter = f15664b;
        }
        return lineFormatter.appendProtocolVersion(null, protocolVersion).toString();
    }

    public static String m17810a(RequestLine requestLine, LineFormatter lineFormatter) {
        if (lineFormatter == null) {
            lineFormatter = f15664b;
        }
        return lineFormatter.formatRequestLine(null, requestLine).toString();
    }

    public static String m17811a(StatusLine statusLine, LineFormatter lineFormatter) {
        if (lineFormatter == null) {
            lineFormatter = f15664b;
        }
        return lineFormatter.formatStatusLine(null, statusLine).toString();
    }

    protected int m17812a(ProtocolVersion protocolVersion) {
        return protocolVersion.getProtocol().length() + 4;
    }

    protected CharArrayBuffer m17813a(CharArrayBuffer charArrayBuffer) {
        if (charArrayBuffer == null) {
            return new CharArrayBuffer(64);
        }
        charArrayBuffer.clear();
        return charArrayBuffer;
    }

    protected void m17814a(CharArrayBuffer charArrayBuffer, Header header) {
        String name = header.getName();
        String value = header.getValue();
        int length = name.length() + 2;
        if (value != null) {
            length += value.length();
        }
        charArrayBuffer.ensureCapacity(length);
        charArrayBuffer.append(name);
        charArrayBuffer.append(": ");
        if (value != null) {
            charArrayBuffer.append(value);
        }
    }

    protected void m17815a(CharArrayBuffer charArrayBuffer, RequestLine requestLine) {
        String method = requestLine.getMethod();
        String uri = requestLine.getUri();
        charArrayBuffer.ensureCapacity((((method.length() + 1) + uri.length()) + 1) + m17812a(requestLine.getProtocolVersion()));
        charArrayBuffer.append(method);
        charArrayBuffer.append(C3022o.f15055c);
        charArrayBuffer.append(uri);
        charArrayBuffer.append(C3022o.f15055c);
        appendProtocolVersion(charArrayBuffer, requestLine.getProtocolVersion());
    }

    protected void m17816a(CharArrayBuffer charArrayBuffer, StatusLine statusLine) {
        int a = ((m17812a(statusLine.getProtocolVersion()) + 1) + 3) + 1;
        String reasonPhrase = statusLine.getReasonPhrase();
        if (reasonPhrase != null) {
            a += reasonPhrase.length();
        }
        charArrayBuffer.ensureCapacity(a);
        appendProtocolVersion(charArrayBuffer, statusLine.getProtocolVersion());
        charArrayBuffer.append(C3022o.f15055c);
        charArrayBuffer.append(Integer.toString(statusLine.getStatusCode()));
        charArrayBuffer.append(C3022o.f15055c);
        if (reasonPhrase != null) {
            charArrayBuffer.append(reasonPhrase);
        }
    }

    public CharArrayBuffer appendProtocolVersion(CharArrayBuffer charArrayBuffer, ProtocolVersion protocolVersion) {
        C3234a.m17886a((Object) protocolVersion, "Protocol version");
        int a = m17812a(protocolVersion);
        if (charArrayBuffer == null) {
            charArrayBuffer = new CharArrayBuffer(a);
        } else {
            charArrayBuffer.ensureCapacity(a);
        }
        charArrayBuffer.append(protocolVersion.getProtocol());
        charArrayBuffer.append('/');
        charArrayBuffer.append(Integer.toString(protocolVersion.getMajor()));
        charArrayBuffer.append('.');
        charArrayBuffer.append(Integer.toString(protocolVersion.getMinor()));
        return charArrayBuffer;
    }

    public CharArrayBuffer formatHeader(CharArrayBuffer charArrayBuffer, Header header) {
        C3234a.m17886a((Object) header, "Header");
        if (header instanceof FormattedHeader) {
            return ((FormattedHeader) header).getBuffer();
        }
        CharArrayBuffer a = m17813a(charArrayBuffer);
        m17814a(a, header);
        return a;
    }

    public CharArrayBuffer formatRequestLine(CharArrayBuffer charArrayBuffer, RequestLine requestLine) {
        C3234a.m17886a((Object) requestLine, "Request line");
        CharArrayBuffer a = m17813a(charArrayBuffer);
        m17815a(a, requestLine);
        return a;
    }

    public CharArrayBuffer formatStatusLine(CharArrayBuffer charArrayBuffer, StatusLine statusLine) {
        C3234a.m17886a((Object) statusLine, "Status line");
        CharArrayBuffer a = m17813a(charArrayBuffer);
        m17816a(a, statusLine);
        return a;
    }
}
