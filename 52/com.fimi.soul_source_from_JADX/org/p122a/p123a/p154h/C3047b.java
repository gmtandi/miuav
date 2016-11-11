package org.p122a.p123a.p154h;

import java.io.InputStream;
import java.io.OutputStream;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3235b;

@C2913c
/* renamed from: org.a.a.h.b */
public class C3047b extends C2937a {
    private InputStream f15094e;
    private long f15095f;

    public C3047b() {
        this.f15095f = -1;
    }

    public void m17154a(long j) {
        this.f15095f = j;
    }

    public void m17155a(InputStream inputStream) {
        this.f15094e = inputStream;
    }

    public InputStream getContent() {
        C3235b.m17895a(this.f15094e != null, "Content has not been provided");
        return this.f15094e;
    }

    public long getContentLength() {
        return this.f15095f;
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean isStreaming() {
        return this.f15094e != null;
    }

    public void writeTo(OutputStream outputStream) {
        C3234a.m17886a((Object) outputStream, "Output stream");
        InputStream content = getContent();
        try {
            byte[] bArr = new byte[Opcodes.ACC_SYNTHETIC];
            while (true) {
                int read = content.read(bArr);
                if (read == -1) {
                    break;
                }
                outputStream.write(bArr, 0, read);
            }
        } finally {
            content.close();
        }
    }
}
