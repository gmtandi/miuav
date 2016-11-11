package cn.sharesdk.framework.p013b.p015b;

import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.utils.C0205d;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import com.mob.tools.utils.Data;
import org.p122a.p123a.C2915a;

/* renamed from: cn.sharesdk.framework.b.b.b */
public class C0173b extends C0171c {
    private static int f246n;
    private static long f247o;
    public int f248a;
    public String f249b;
    public String f250c;
    public String f251d;

    protected String m540a() {
        return "[AUT]";
    }

    protected void m541a(long j) {
        f247o = j;
    }

    protected int m542b() {
        return FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS;
    }

    protected int m543c() {
        return 5;
    }

    protected long m544d() {
        return (long) f246n;
    }

    protected long m545e() {
        return f247o;
    }

    protected void m546f() {
        f246n++;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append('|').append(this.f248a);
        stringBuilder.append('|').append(this.f249b);
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.f251d)) {
            try {
                String encodeToString = Base64.encodeToString(Data.AES128Encode(this.f.substring(0, 16), this.f251d), 0);
                if (!TextUtils.isEmpty(encodeToString) && encodeToString.contains("\n")) {
                    encodeToString = encodeToString.replace("\n", C2915a.f14760f);
                }
                stringBuilder.append(encodeToString);
            } catch (Throwable th) {
                C0205d.m752a().m738d(th);
            }
        }
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.m)) {
            stringBuilder.append(this.m);
        }
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.f250c)) {
            stringBuilder.append(this.f250c);
        }
        return stringBuilder.toString();
    }
}
