package org.p122a.p123a.p167i.p172g;

import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.io.SessionOutputBuffer;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p150a.C2913c;

@C2913c
/* renamed from: org.a.a.i.g.d */
public class C3184d extends OutputStream {
    private final SessionOutputBuffer f15589a;
    private final byte[] f15590b;
    private int f15591c;
    private boolean f15592d;
    private boolean f15593e;

    public C3184d(int i, SessionOutputBuffer sessionOutputBuffer) {
        this.f15591c = 0;
        this.f15592d = false;
        this.f15593e = false;
        this.f15590b = new byte[i];
        this.f15589a = sessionOutputBuffer;
    }

    @Deprecated
    public C3184d(SessionOutputBuffer sessionOutputBuffer) {
        this((int) Opcodes.ACC_STRICT, sessionOutputBuffer);
    }

    @Deprecated
    public C3184d(SessionOutputBuffer sessionOutputBuffer, int i) {
        this(i, sessionOutputBuffer);
    }

    protected void m17735a() {
        if (this.f15591c > 0) {
            this.f15589a.writeLine(Integer.toHexString(this.f15591c));
            this.f15589a.write(this.f15590b, 0, this.f15591c);
            this.f15589a.writeLine(C2915a.f14760f);
            this.f15591c = 0;
        }
    }

    protected void m17736a(byte[] bArr, int i, int i2) {
        this.f15589a.writeLine(Integer.toHexString(this.f15591c + i2));
        this.f15589a.write(this.f15590b, 0, this.f15591c);
        this.f15589a.write(bArr, i, i2);
        this.f15589a.writeLine(C2915a.f14760f);
        this.f15591c = 0;
    }

    protected void m17737b() {
        this.f15589a.writeLine(Constants.VIA_RESULT_SUCCESS);
        this.f15589a.writeLine(C2915a.f14760f);
    }

    public void m17738c() {
        if (!this.f15592d) {
            m17735a();
            m17737b();
            this.f15592d = true;
        }
    }

    public void close() {
        if (!this.f15593e) {
            this.f15593e = true;
            m17738c();
            this.f15589a.flush();
        }
    }

    public void flush() {
        m17735a();
        this.f15589a.flush();
    }

    public void write(int i) {
        if (this.f15593e) {
            throw new IOException("Attempted write to closed stream.");
        }
        this.f15590b[this.f15591c] = (byte) i;
        this.f15591c++;
        if (this.f15591c == this.f15590b.length) {
            m17735a();
        }
    }

    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        if (this.f15593e) {
            throw new IOException("Attempted write to closed stream.");
        } else if (i2 >= this.f15590b.length - this.f15591c) {
            m17736a(bArr, i, i2);
        } else {
            System.arraycopy(bArr, i, this.f15590b, this.f15591c, i2);
            this.f15591c += i2;
        }
    }
}
