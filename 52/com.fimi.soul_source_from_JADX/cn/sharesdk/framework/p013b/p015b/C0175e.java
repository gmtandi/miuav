package cn.sharesdk.framework.p013b.p015b;

import android.content.Context;
import android.text.TextUtils;
import cn.sharesdk.framework.p013b.p014a.C0169e;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;

/* renamed from: cn.sharesdk.framework.b.b.e */
public class C0175e extends C0171c {
    private static int f257b;
    private static long f258c;
    public long f259a;

    protected String m554a() {
        return "[EXT]";
    }

    protected void m555a(long j) {
        f258c = j;
    }

    public boolean m556a(Context context) {
        C0169e a = C0169e.m467a(context);
        f257b = a.m495j("insertExitEventCount");
        f258c = a.m493i("lastInsertExitEventTime");
        return super.m526a(context);
    }

    protected int m557b() {
        return FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS;
    }

    public void m558b(Context context) {
        super.m528b(context);
        C0169e a = C0169e.m467a(context);
        a.m472a("lastInsertExitEventTime", Long.valueOf(f258c));
        a.m471a("insertExitEventCount", f257b);
    }

    protected int m559c() {
        return 5;
    }

    protected long m560d() {
        return (long) f257b;
    }

    protected long m561e() {
        return f258c;
    }

    protected void m562f() {
        f257b++;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.m)) {
            stringBuilder.append(this.m);
        }
        stringBuilder.append('|').append(Math.round(((float) this.f259a) / 1000.0f));
        return stringBuilder.toString();
    }
}
