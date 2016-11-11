package it.p074a.p075a;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.StringTokenizer;
import org.p122a.p123a.p124f.p125c.C3022o;

/* renamed from: it.a.a.w */
class C2810w extends Writer {
    private static final String f14313a = "\r\n";
    private OutputStream f14314b;
    private Writer f14315c;

    public C2810w(OutputStream outputStream, String str) {
        this.f14314b = outputStream;
        this.f14315c = new OutputStreamWriter(outputStream, str);
    }

    public void m16002a(String str) {
        synchronized (this) {
            this.f14315c = new OutputStreamWriter(this.f14314b, str);
        }
    }

    public void m16003b(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        StringTokenizer stringTokenizer = new StringTokenizer(str, f14313a);
        int countTokens = stringTokenizer.countTokens();
        char c = '\u0000';
        for (int i = 0; i < countTokens; i++) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.length() > 0) {
                if (c != '\u0000') {
                    stringBuffer.append(C3022o.f15053a);
                    stringBuffer.append('\u0000');
                }
                stringBuffer.append(nextToken);
                c = '\u0001';
            }
        }
        if (stringBuffer.length() > 0) {
            this.f14315c.write(stringBuffer.toString());
            this.f14315c.write(f14313a);
            this.f14315c.flush();
        }
    }

    public void close() {
        synchronized (this) {
            this.f14315c.close();
        }
    }

    public void flush() {
        synchronized (this) {
            this.f14315c.flush();
        }
    }

    public void write(char[] cArr, int i, int i2) {
        synchronized (this) {
            this.f14315c.write(cArr, i, i2);
        }
    }
}
