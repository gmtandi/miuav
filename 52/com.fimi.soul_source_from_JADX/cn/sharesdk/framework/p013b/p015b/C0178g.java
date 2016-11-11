package cn.sharesdk.framework.p013b.p015b;

import android.content.Context;
import android.text.TextUtils;
import cn.sharesdk.framework.p013b.p014a.C0169e;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;

/* renamed from: cn.sharesdk.framework.b.b.g */
public class C0178g extends C0171c {
    private static int f275a;
    private static long f276b;

    protected String m570a() {
        return "[RUN]";
    }

    protected void m571a(long j) {
        f276b = j;
    }

    public boolean m572a(Context context) {
        C0169e a = C0169e.m467a(context);
        f275a = a.m495j("insertRunEventCount");
        f276b = a.m493i("lastInsertRunEventTime");
        return super.m526a(context);
    }

    protected int m573b() {
        return FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS;
    }

    public void m574b(Context context) {
        super.m528b(context);
        C0169e a = C0169e.m467a(context);
        a.m472a("lastInsertRunEventTime", Long.valueOf(f276b));
        a.m471a("insertRunEventCount", f275a);
    }

    protected int m575c() {
        return 5;
    }

    protected long m576d() {
        return (long) f275a;
    }

    protected long m577e() {
        return f276b;
    }

    protected void m578f() {
        f275a++;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.m)) {
            stringBuilder.append(this.m);
        }
        return stringBuilder.toString();
    }
}
