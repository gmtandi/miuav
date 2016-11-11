package cn.sharesdk.framework.p013b.p015b;

import com.hoho.android.usbserial.driver.FtdiSerialDriver;

/* renamed from: cn.sharesdk.framework.b.b.a */
public class C0172a extends C0171c {
    private static int f242c;
    private static long f243d;
    public int f244a;
    public String f245b;

    protected String m533a() {
        return "[API]";
    }

    protected void m534a(long j) {
        f243d = j;
    }

    protected int m535b() {
        return FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS;
    }

    protected int m536c() {
        return 50;
    }

    protected long m537d() {
        return (long) f242c;
    }

    protected long m538e() {
        return f243d;
    }

    protected void m539f() {
        f242c++;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append('|').append(this.f244a);
        stringBuilder.append('|').append(this.f245b);
        return stringBuilder.toString();
    }
}
