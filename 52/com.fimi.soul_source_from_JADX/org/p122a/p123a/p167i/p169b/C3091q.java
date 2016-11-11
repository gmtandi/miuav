package org.p122a.p123a.p167i.p169b;

import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.p122a.p123a.p154h.C2928g;

/* renamed from: org.a.a.i.b.q */
class C3091q extends C2928g {
    final /* synthetic */ C3090p f15285b;

    C3091q(C3090p c3090p, HttpEntity httpEntity) {
        this.f15285b = c3090p;
        super(httpEntity);
    }

    public void consumeContent() {
        this.f15285b.f15284b = true;
        super.consumeContent();
    }

    public InputStream getContent() {
        this.f15285b.f15284b = true;
        return super.getContent();
    }

    public void writeTo(OutputStream outputStream) {
        this.f15285b.f15284b = true;
        super.writeTo(outputStream);
    }
}
