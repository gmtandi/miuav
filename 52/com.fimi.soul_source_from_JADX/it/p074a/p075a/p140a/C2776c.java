package it.p074a.p075a.p140a;

import com.tencent.mm.sdk.platformtools.Util;
import java.io.OutputStream;

/* renamed from: it.a.a.a.c */
class C2776c extends OutputStream {
    private OutputStream f14155a;
    private int f14156b;
    private int f14157c;
    private int f14158d;
    private int f14159e;

    public C2776c(OutputStream outputStream) {
        this(outputStream, 76);
    }

    public C2776c(OutputStream outputStream, int i) {
        this.f14155a = null;
        this.f14156b = 0;
        this.f14157c = 0;
        this.f14158d = 0;
        this.f14159e = 0;
        this.f14155a = outputStream;
        this.f14159e = i;
    }

    protected void m15831a() {
        if (this.f14157c > 0) {
            if (this.f14159e > 0 && this.f14158d == this.f14159e) {
                this.f14155a.write("\r\n".getBytes());
                this.f14158d = 0;
            }
            char charAt = C2774a.f14149a.charAt((this.f14156b << 8) >>> 26);
            char charAt2 = C2774a.f14149a.charAt((this.f14156b << 14) >>> 26);
            int charAt3 = this.f14157c < 2 ? C2774a.f14150b : C2774a.f14149a.charAt((this.f14156b << 20) >>> 26);
            int charAt4 = this.f14157c < 3 ? C2774a.f14150b : C2774a.f14149a.charAt((this.f14156b << 26) >>> 26);
            this.f14155a.write(charAt);
            this.f14155a.write(charAt2);
            this.f14155a.write(charAt3);
            this.f14155a.write(charAt4);
            this.f14158d += 4;
            this.f14157c = 0;
            this.f14156b = 0;
        }
    }

    public void close() {
        m15831a();
        this.f14155a.close();
    }

    public void write(int i) {
        this.f14156b = ((i & Util.MASK_8BIT) << (16 - (this.f14157c * 8))) | this.f14156b;
        this.f14157c++;
        if (this.f14157c == 3) {
            m15831a();
        }
    }
}
