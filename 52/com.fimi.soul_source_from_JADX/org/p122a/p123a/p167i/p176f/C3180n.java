package org.p122a.p123a.p167i.p176f;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.p122a.p123a.p150a.C2912b;

@C2912b
/* renamed from: org.a.a.i.f.n */
public class C3180n extends HttpException {
    private static final long serialVersionUID = -8646722842745617323L;
    private final HttpResponse f15567a;

    public C3180n(String str, HttpResponse httpResponse) {
        super(str);
        this.f15567a = httpResponse;
    }

    public HttpResponse m17727a() {
        return this.f15567a;
    }
}
