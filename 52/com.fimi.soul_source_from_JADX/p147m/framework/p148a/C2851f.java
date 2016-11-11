package p147m.framework.p148a;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: m.framework.a.f */
public class C2851f extends InputStream {
    private ArrayList<InputStream> f14590a;
    private int f14591b;

    C2851f() {
        this.f14590a = new ArrayList();
    }

    private boolean m16428a() {
        return this.f14590a == null || this.f14590a.size() <= 0;
    }

    public void m16429a(InputStream inputStream) {
        this.f14590a.add(inputStream);
    }

    public int available() {
        return m16428a() ? 0 : ((InputStream) this.f14590a.get(this.f14591b)).available();
    }

    public void close() {
        Iterator it = this.f14590a.iterator();
        while (it.hasNext()) {
            ((InputStream) it.next()).close();
        }
    }

    public int read() {
        if (m16428a()) {
            return -1;
        }
        int read = ((InputStream) this.f14590a.get(this.f14591b)).read();
        while (read < 0) {
            this.f14591b++;
            if (this.f14591b >= this.f14590a.size()) {
                return read;
            }
            read = ((InputStream) this.f14590a.get(this.f14591b)).read();
        }
        return read;
    }

    public int read(byte[] bArr, int i, int i2) {
        if (m16428a()) {
            return -1;
        }
        int read = ((InputStream) this.f14590a.get(this.f14591b)).read(bArr, i, i2);
        while (read < 0) {
            this.f14591b++;
            if (this.f14591b >= this.f14590a.size()) {
                return read;
            }
            read = ((InputStream) this.f14590a.get(this.f14591b)).read(bArr, i, i2);
        }
        return read;
    }

    public long skip(long j) {
        throw new IOException();
    }
}
