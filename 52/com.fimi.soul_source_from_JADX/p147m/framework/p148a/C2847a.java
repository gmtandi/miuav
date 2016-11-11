package p147m.framework.p148a;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import p147m.framework.p149b.C2857a;

/* renamed from: m.framework.a.a */
public class C2847a extends C2846c {
    private byte[] f14585a;

    protected InputStream m16418a() {
        return (this.f14585a == null || this.f14585a.length <= 0) ? new ByteArrayInputStream(new byte[0]) : new ByteArrayInputStream(this.f14585a);
    }

    public C2847a m16419a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (this.f14585a != null && this.f14585a.length > 0) {
            byteArrayOutputStream.write(this.f14585a);
        }
        byteArrayOutputStream.write(bArr);
        byteArrayOutputStream.flush();
        this.f14585a = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return this;
    }

    protected long m16420b() {
        return (long) (this.f14585a == null ? 0 : this.f14585a.length);
    }

    public String toString() {
        return C2857a.m16449a(this.f14585a);
    }
}
