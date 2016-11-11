package cn.sharesdk.framework.p013b.p015b;

import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import org.p122a.p123a.C2915a;

/* renamed from: cn.sharesdk.framework.b.b.d */
public class C0174d extends C0171c {
    private static int f252d;
    private static long f253n;
    public String f254a;
    public int f255b;
    public String f256c;

    public C0174d() {
        this.f256c = C2915a.f14760f;
    }

    protected String m547a() {
        return "[EVT]";
    }

    protected void m548a(long j) {
        f253n = j;
    }

    protected int m549b() {
        return FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS;
    }

    protected int m550c() {
        return 30;
    }

    protected long m551d() {
        return (long) f252d;
    }

    protected long m552e() {
        return f253n;
    }

    protected void m553f() {
        f252d++;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append('|').append(this.f254a);
        stringBuilder.append('|').append(this.f255b);
        stringBuilder.append('|').append(this.f256c);
        return stringBuilder.toString();
    }
}
