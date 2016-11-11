package org.p122a.p123a.p167i;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestFactory;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.RequestLine;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicHttpRequest;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p152c.p156c.C2949f;
import org.p122a.p123a.p152c.p156c.C2951i;
import org.p122a.p123a.p152c.p156c.C2952j;
import org.p122a.p123a.p152c.p156c.C2953k;
import org.p122a.p123a.p152c.p156c.C2955m;
import org.p122a.p123a.p152c.p156c.C2956n;
import org.p122a.p123a.p152c.p156c.C2960s;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.h */
public class C3205h implements HttpRequestFactory {
    public static final C3205h f15645a;
    private static final String[] f15646b;
    private static final String[] f15647c;
    private static final String[] f15648d;

    static {
        f15645a = new C3205h();
        f15646b = new String[]{C2951i.f14860a};
        f15647c = new String[]{C2955m.f14864a, C2956n.f14865a};
        f15648d = new String[]{C2952j.f14861a, C2953k.f14862a, C2949f.f14858a, C2960s.f14871a, "CONNECT"};
    }

    private static boolean m17786a(String[] strArr, String str) {
        for (String equalsIgnoreCase : strArr) {
            if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public HttpRequest newHttpRequest(String str, String str2) {
        if (C3205h.m17786a(f15646b, str)) {
            return new BasicHttpRequest(str, str2);
        }
        if (C3205h.m17786a(f15647c, str)) {
            return new BasicHttpEntityEnclosingRequest(str, str2);
        }
        if (C3205h.m17786a(f15648d, str)) {
            return new BasicHttpRequest(str, str2);
        }
        throw new MethodNotSupportedException(str + " method not supported");
    }

    public HttpRequest newHttpRequest(RequestLine requestLine) {
        C3234a.m17886a((Object) requestLine, "Request line");
        String method = requestLine.getMethod();
        if (C3205h.m17786a(f15646b, method)) {
            return new BasicHttpRequest(requestLine);
        }
        if (C3205h.m17786a(f15647c, method)) {
            return new BasicHttpEntityEnclosingRequest(requestLine);
        }
        if (C3205h.m17786a(f15648d, method)) {
            return new BasicHttpRequest(requestLine);
        }
        throw new MethodNotSupportedException(method + " method not supported");
    }
}
