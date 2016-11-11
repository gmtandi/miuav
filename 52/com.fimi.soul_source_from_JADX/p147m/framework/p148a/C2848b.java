package p147m.framework.p148a;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/* renamed from: m.framework.a.b */
public class C2848b extends C2846c {
    private File f14586a;

    protected InputStream m16421a() {
        return new FileInputStream(this.f14586a);
    }

    public void m16422a(File file) {
        this.f14586a = file;
    }

    public void m16423a(String str) {
        this.f14586a = new File(str);
    }

    protected long m16424b() {
        return this.f14586a.length();
    }

    public String toString() {
        return this.f14586a.toString();
    }
}
