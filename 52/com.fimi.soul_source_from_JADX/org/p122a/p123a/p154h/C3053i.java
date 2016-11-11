package org.p122a.p123a.p154h;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.h.i */
public class C3053i extends C2937a {
    private byte[] f15121e;
    private Serializable f15122f;

    public C3053i(Serializable serializable) {
        C3234a.m17886a((Object) serializable, "Source object");
        this.f15122f = serializable;
    }

    public C3053i(Serializable serializable, boolean z) {
        C3234a.m17886a((Object) serializable, "Source object");
        if (z) {
            m17169a(serializable);
        } else {
            this.f15122f = serializable;
        }
    }

    private void m17169a(Serializable serializable) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(serializable);
        objectOutputStream.flush();
        this.f15121e = byteArrayOutputStream.toByteArray();
    }

    public InputStream getContent() {
        if (this.f15121e == null) {
            m17169a(this.f15122f);
        }
        return new ByteArrayInputStream(this.f15121e);
    }

    public long getContentLength() {
        return this.f15121e == null ? -1 : (long) this.f15121e.length;
    }

    public boolean isRepeatable() {
        return true;
    }

    public boolean isStreaming() {
        return this.f15121e == null;
    }

    public void writeTo(OutputStream outputStream) {
        C3234a.m17886a((Object) outputStream, "Output stream");
        if (this.f15121e == null) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this.f15122f);
            objectOutputStream.flush();
            return;
        }
        outputStream.write(this.f15121e);
        outputStream.flush();
    }
}
