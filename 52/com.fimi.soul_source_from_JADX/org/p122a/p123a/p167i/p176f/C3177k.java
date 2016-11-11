package org.p122a.p123a.p167i.p176f;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.conn.EofSensorWatcher;
import org.p122a.p123a.p124f.C3034c;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p154h.C2928g;

@C2913c
/* renamed from: org.a.a.i.f.k */
class C3177k extends C2928g implements EofSensorWatcher {
    private final C3169c f15560b;

    C3177k(HttpEntity httpEntity, C3169c c3169c) {
        super(httpEntity);
        this.f15560b = c3169c;
    }

    public static void m17722a(HttpResponse httpResponse, C3169c c3169c) {
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null && entity.isStreaming() && c3169c != null) {
            httpResponse.setEntity(new C3177k(entity, c3169c));
        }
    }

    private void m17723b() {
        if (this.f15560b != null) {
            this.f15560b.abortConnection();
        }
    }

    public void m17724a() {
        if (this.f15560b != null) {
            try {
                if (this.f15560b.m17703b()) {
                    this.f15560b.releaseConnection();
                }
                m17723b();
            } catch (Throwable th) {
                m17723b();
            }
        }
    }

    @Deprecated
    public void consumeContent() {
        m17724a();
    }

    public boolean eofDetected(InputStream inputStream) {
        try {
            inputStream.close();
            m17724a();
            return false;
        } finally {
            m17723b();
        }
    }

    public InputStream getContent() {
        return new C3034c(this.a.getContent(), this);
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean streamAbort(InputStream inputStream) {
        m17723b();
        return false;
    }

    public boolean streamClosed(InputStream inputStream) {
        boolean z;
        try {
            z = (this.f15560b == null || this.f15560b.m17706e()) ? false : true;
            inputStream.close();
            m17724a();
        } catch (SocketException e) {
            if (z) {
                throw e;
            }
        } catch (Throwable th) {
            m17723b();
        }
        m17723b();
        return false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ResponseEntityProxy{");
        stringBuilder.append(this.a);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public void writeTo(OutputStream outputStream) {
        try {
            this.a.writeTo(outputStream);
            m17724a();
        } finally {
            m17723b();
        }
    }
}
