package org.p122a.p123a.p152c.p156c;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p152c.p161f.C2978a;

@C2913c
/* renamed from: org.a.a.c.c.g */
public abstract class C2950g extends C2948o implements HttpEntityEnclosingRequest {
    private HttpEntity f14859a;

    public Object clone() {
        C2950g c2950g = (C2950g) super.clone();
        if (this.f14859a != null) {
            c2950g.f14859a = (HttpEntity) C2978a.m16912a(this.f14859a);
        }
        return c2950g;
    }

    public boolean expectContinue() {
        Header firstHeader = getFirstHeader(C3004e.f15037w);
        return firstHeader != null && "100-continue".equalsIgnoreCase(firstHeader.getValue());
    }

    public HttpEntity getEntity() {
        return this.f14859a;
    }

    public void setEntity(HttpEntity httpEntity) {
        this.f14859a = httpEntity;
    }
}
