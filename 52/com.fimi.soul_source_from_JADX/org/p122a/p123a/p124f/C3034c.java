package org.p122a.p123a.p124f;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.conn.EofSensorWatcher;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.f.c */
public class C3034c extends InputStream implements ConnectionReleaseTrigger {
    protected InputStream f15083a;
    private boolean f15084b;
    private final EofSensorWatcher f15085c;

    public C3034c(InputStream inputStream, EofSensorWatcher eofSensorWatcher) {
        C3234a.m17886a((Object) inputStream, "Wrapped stream");
        this.f15083a = inputStream;
        this.f15084b = false;
        this.f15085c = eofSensorWatcher;
    }

    protected void m17124a(int i) {
        if (this.f15083a != null && i < 0) {
            boolean z = true;
            try {
                if (this.f15085c != null) {
                    z = this.f15085c.eofDetected(this.f15083a);
                }
                if (z) {
                    this.f15083a.close();
                }
                this.f15083a = null;
            } catch (Throwable th) {
                this.f15083a = null;
            }
        }
    }

    boolean m17125a() {
        return this.f15084b;
    }

    public void abortConnection() {
        this.f15084b = true;
        m17129e();
    }

    public int available() {
        int i = 0;
        if (m17127c()) {
            try {
                i = this.f15083a.available();
            } catch (IOException e) {
                m17129e();
                throw e;
            }
        }
        return i;
    }

    InputStream m17126b() {
        return this.f15083a;
    }

    protected boolean m17127c() {
        if (!this.f15084b) {
            return this.f15083a != null;
        } else {
            throw new IOException("Attempted read on closed stream.");
        }
    }

    public void close() {
        this.f15084b = true;
        m17128d();
    }

    protected void m17128d() {
        if (this.f15083a != null) {
            boolean z = true;
            try {
                if (this.f15085c != null) {
                    z = this.f15085c.streamClosed(this.f15083a);
                }
                if (z) {
                    this.f15083a.close();
                }
                this.f15083a = null;
            } catch (Throwable th) {
                this.f15083a = null;
            }
        }
    }

    protected void m17129e() {
        if (this.f15083a != null) {
            boolean z = true;
            try {
                if (this.f15085c != null) {
                    z = this.f15085c.streamAbort(this.f15083a);
                }
                if (z) {
                    this.f15083a.close();
                }
                this.f15083a = null;
            } catch (Throwable th) {
                this.f15083a = null;
            }
        }
    }

    public int read() {
        if (!m17127c()) {
            return -1;
        }
        try {
            int read = this.f15083a.read();
            m17124a(read);
            return read;
        } catch (IOException e) {
            m17129e();
            throw e;
        }
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (!m17127c()) {
            return -1;
        }
        try {
            int read = this.f15083a.read(bArr, i, i2);
            m17124a(read);
            return read;
        } catch (IOException e) {
            m17129e();
            throw e;
        }
    }

    public void releaseConnection() {
        close();
    }
}
