package it.p074a.p075a;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* renamed from: it.a.a.v */
class C2809v extends Reader {
    private static final String f14310a;
    private InputStream f14311b;
    private Reader f14312c;

    static {
        f14310a = System.getProperty("line.separator");
    }

    public C2809v(InputStream inputStream, String str) {
        this.f14311b = inputStream;
        this.f14312c = new InputStreamReader(inputStream, str);
    }

    public String m16000a() {
        StringBuffer stringBuffer = new StringBuffer();
        int i = -1;
        while (true) {
            int read = this.f14312c.read();
            if (read == -1) {
                break;
            } else if (read == 10) {
                return stringBuffer.toString();
            } else {
                if (i == 13 && read == 0) {
                    stringBuffer.append(f14310a);
                } else if (!(read == 0 || read == 13)) {
                    stringBuffer.append((char) read);
                }
                i = read;
            }
        }
        return stringBuffer.length() == 0 ? null : stringBuffer.toString();
    }

    public void m16001a(String str) {
        synchronized (this) {
            this.f14312c = new InputStreamReader(this.f14311b, str);
        }
    }

    public void close() {
        synchronized (this) {
            this.f14312c.close();
        }
    }

    public int read(char[] cArr, int i, int i2) {
        int read;
        synchronized (this) {
            read = this.f14312c.read(cArr, i, i2);
        }
        return read;
    }
}
