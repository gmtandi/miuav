package org.p122a.p123a.p154h;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.h.f */
public class C3051f extends C2937a implements Cloneable {
    protected final File f15118e;

    public C3051f(File file) {
        this.f15118e = (File) C3234a.m17886a((Object) file, "File");
    }

    @Deprecated
    public C3051f(File file, String str) {
        this.f15118e = (File) C3234a.m17886a((Object) file, "File");
        m16807a(str);
    }

    public C3051f(File file, C3050e c3050e) {
        this.f15118e = (File) C3234a.m17886a((Object) file, "File");
        if (c3050e != null) {
            m16807a(c3050e.toString());
        }
    }

    public Object clone() {
        return super.clone();
    }

    public InputStream getContent() {
        return new FileInputStream(this.f15118e);
    }

    public long getContentLength() {
        return this.f15118e.length();
    }

    public boolean isRepeatable() {
        return true;
    }

    public boolean isStreaming() {
        return false;
    }

    public void writeTo(OutputStream outputStream) {
        C3234a.m17886a((Object) outputStream, "Output stream");
        InputStream fileInputStream = new FileInputStream(this.f15118e);
        try {
            byte[] bArr = new byte[Opcodes.ACC_SYNTHETIC];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                outputStream.write(bArr, 0, read);
            }
            outputStream.flush();
        } finally {
            fileInputStream.close();
        }
    }
}
