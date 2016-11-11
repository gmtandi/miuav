package org.p122a.p123a.p167i.p169b;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.impl.client.RequestWrapper;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2913c;

@Deprecated
@C2913c
/* renamed from: org.a.a.i.b.p */
public class C3090p extends RequestWrapper implements HttpEntityEnclosingRequest {
    private HttpEntity f15283a;
    private boolean f15284b;

    public C3090p(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        super(httpEntityEnclosingRequest);
        setEntity(httpEntityEnclosingRequest.getEntity());
    }

    public boolean expectContinue() {
        Header firstHeader = getFirstHeader(C3004e.f15037w);
        return firstHeader != null && "100-continue".equalsIgnoreCase(firstHeader.getValue());
    }

    public HttpEntity getEntity() {
        return this.f15283a;
    }

    public boolean isRepeatable() {
        return this.f15283a == null || this.f15283a.isRepeatable() || !this.f15284b;
    }

    public void setEntity(HttpEntity httpEntity) {
        this.f15283a = httpEntity != null ? new C3091q(this, httpEntity) : null;
        this.f15284b = false;
    }
}
