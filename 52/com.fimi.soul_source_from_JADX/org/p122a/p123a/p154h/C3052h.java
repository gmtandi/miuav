package org.p122a.p123a.p154h;

import com.fimi.soul.media.player.FimiMediaMeta;
import java.io.InputStream;
import java.io.OutputStream;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.h.h */
public class C3052h extends C2937a {
    private final InputStream f15119e;
    private final long f15120f;

    public C3052h(InputStream inputStream) {
        this(inputStream, -1);
    }

    public C3052h(InputStream inputStream, long j) {
        this(inputStream, j, null);
    }

    public C3052h(InputStream inputStream, long j, C3050e c3050e) {
        this.f15119e = (InputStream) C3234a.m17886a((Object) inputStream, "Source input stream");
        this.f15120f = j;
        if (c3050e != null) {
            m16807a(c3050e.toString());
        }
    }

    public C3052h(InputStream inputStream, C3050e c3050e) {
        this(inputStream, -1, c3050e);
    }

    public InputStream getContent() {
        return this.f15119e;
    }

    public long getContentLength() {
        return this.f15120f;
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean isStreaming() {
        return true;
    }

    public void writeTo(OutputStream outputStream) {
        C3234a.m17886a((Object) outputStream, "Output stream");
        InputStream inputStream = this.f15119e;
        try {
            byte[] bArr = new byte[Opcodes.ACC_SYNTHETIC];
            if (this.f15120f < 0) {
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                }
            } else {
                long j = this.f15120f;
                while (j > 0) {
                    int read2 = inputStream.read(bArr, 0, (int) Math.min(FimiMediaMeta.AV_CH_TOP_FRONT_LEFT, j));
                    if (read2 == -1) {
                        break;
                    }
                    outputStream.write(bArr, 0, read2);
                    j -= (long) read2;
                }
            }
            inputStream.close();
        } catch (Throwable th) {
            inputStream.close();
        }
    }
}
