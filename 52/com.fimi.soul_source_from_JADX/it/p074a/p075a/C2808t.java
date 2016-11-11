package it.p074a.p075a;

import org.codehaus.jackson.util.MinimalPrettyPrinter;

/* renamed from: it.a.a.t */
public class C2808t {
    private int f14308a;
    private String[] f14309b;

    C2808t(int i, String[] strArr) {
        this.f14308a = 0;
        this.f14308a = i;
        this.f14309b = strArr;
    }

    public int m15997a() {
        return this.f14308a;
    }

    public boolean m15998b() {
        int i = this.f14308a - 200;
        return i >= 0 && i < 100;
    }

    public String[] m15999c() {
        return this.f14309b;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getClass().getName());
        stringBuffer.append(" [code=");
        stringBuffer.append(this.f14308a);
        stringBuffer.append(", message=");
        for (int i = 0; i < this.f14309b.length; i++) {
            if (i > 0) {
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
            stringBuffer.append(this.f14309b[i]);
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
