package p147m.framework.p148a;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* renamed from: m.framework.a.k */
public class C2856k extends C2846c {
    private StringBuilder f14596a;

    public C2856k() {
        this.f14596a = new StringBuilder();
    }

    protected InputStream m16443a() {
        return new ByteArrayInputStream(this.f14596a.toString().getBytes("utf-8"));
    }

    public C2856k m16444a(String str) {
        this.f14596a.append(str);
        return this;
    }

    protected long m16445b() {
        return (long) this.f14596a.toString().getBytes("utf-8").length;
    }

    public String toString() {
        return this.f14596a.toString();
    }
}
