package org.p122a.p123a.p154h;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2913c;

@C2913c
/* renamed from: org.a.a.h.a */
public abstract class C2937a implements HttpEntity {
    protected static final int f14844a = 4096;
    protected Header f14845b;
    protected Header f14846c;
    protected boolean f14847d;

    protected C2937a() {
    }

    public void m16807a(String str) {
        Header header = null;
        if (str != null) {
            header = new BasicHeader(C3004e.f15031q, str);
        }
        m16808a(header);
    }

    public void m16808a(Header header) {
        this.f14845b = header;
    }

    public void m16809a(boolean z) {
        this.f14847d = z;
    }

    public void m16810b(String str) {
        Header header = null;
        if (str != null) {
            header = new BasicHeader(C3004e.f15025k, str);
        }
        m16811b(header);
    }

    public void m16811b(Header header) {
        this.f14846c = header;
    }

    @Deprecated
    public void consumeContent() {
    }

    public Header getContentEncoding() {
        return this.f14846c;
    }

    public Header getContentType() {
        return this.f14845b;
    }

    public boolean isChunked() {
        return this.f14847d;
    }
}
