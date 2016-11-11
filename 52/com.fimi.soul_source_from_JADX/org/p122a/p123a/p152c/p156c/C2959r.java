package org.p122a.p123a.p152c.p156c;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.p122a.p123a.C3004e;

/* renamed from: org.a.a.c.c.r */
class C2959r extends C2957p implements HttpEntityEnclosingRequest {
    private HttpEntity f14870a;

    public C2959r(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        super(null);
        this.f14870a = httpEntityEnclosingRequest.getEntity();
    }

    public boolean expectContinue() {
        Header firstHeader = getFirstHeader(C3004e.f15037w);
        return firstHeader != null && "100-continue".equalsIgnoreCase(firstHeader.getValue());
    }

    public HttpEntity getEntity() {
        return this.f14870a;
    }

    public void setEntity(HttpEntity httpEntity) {
        this.f14870a = httpEntity;
    }
}
